package com.nhserver.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nhserver.common.Util;
import com.nhserver.model.dao.nhGCMDao;
import com.nhserver.model.dao.nhMemberDao;
import com.nhserver.model.dto.ArrayListMember;
import com.nhserver.model.dto.GCM;
import com.nhserver.model.dto.Member;
import com.nhserver.model.mapper.nhGcmMapper;

@Controller
@RequestMapping(value = "/nhmember/")
public class nhMemberController {

	@Autowired
	@Qualifier("oraclenhMemberDao")
	private nhMemberDao dao;

	@Autowired
	@Qualifier("oraclenhGCMDao")
	private nhGCMDao GCMdao;

	private final String SENDER_ID = "AIzaSyCbr4Ti4FGZ04GZcwjgO0rvD0kH688kSjI";

	public void setMemberDao(nhMemberDao nhmemberDao) {
		this.dao = nhmemberDao;
	}

	@RequestMapping(value = "/login.action", method = RequestMethod.GET)
	public void login(String memberId, String passwd, HttpServletRequest req, HttpServletResponse resp) {
		passwd = Util.getHashedString(passwd, "SHA-256");
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter writer;
		try {
			writer = resp.getWriter();

			if (memberId == null || memberId.length() == 0 || passwd == null || passwd.length() == 0) {
				writer.println("failed");
			} else {
				Member member = dao.getMemberByIdAndPasswd(memberId, passwd);
				String json = null;
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

				if (member != null) {
					// 로그인 성공 보내기
					json = gson.toJson(member);
					writer.println(json);
				} else {
					// 로그인 실패 보내기
					writer.println("failed");
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@RequestMapping(value = "/registerId.action", method = RequestMethod.GET)
	public void register(String registerId, String registerPasswd, String cId, HttpServletRequest req,
			HttpServletResponse resp) {
		resp.setContentType("text/plain;charset=utf-8");
		System.out.println(registerId + "&" + cId + "&");
		PrintWriter writer;
		try {
			writer = resp.getWriter();

			Member pMember = dao.getMemberByIdAndPasswd(registerId, Util.getHashedString(registerPasswd, "SHA-256"));
			Member cMember = dao.getMemberById(cId);
			if (pMember == null || cMember == null) {
				writer.println("fail");
			} else {
				GCM gcm = new GCM();
				gcm.setcId(cMember.getMemberId());
				gcm.setcToken(cMember.getInstanceId());
				gcm.setpId(registerId);
				gcm.setpToken(pMember.getInstanceId());
				GCMdao.insertGCM(gcm);
				writer.println("success");
			}
			String json = null;
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@RequestMapping(value = "push.action", method = RequestMethod.GET)
	public void push(HttpServletRequest req, HttpServletResponse resp) {
		ServletContext application = req.getSession().getServletContext();
		int iCollapseKey = (int) application.getAttribute("collapse-key");
		application.setAttribute("collapse-key", iCollapseKey + 1);

		String collapseKey = String.valueOf(iCollapseKey);
		String userMessage = "장시간 움직임이 없습니다.";
		
		Sender sender = new Sender(SENDER_ID);

		Message message = new Message.Builder().collapseKey(collapseKey).timeToLive(30).delayWhileIdle(true)
				.addData("message", userMessage).addData("title", userMessage).build();
		List<GCM> GCMs = (List<GCM>) GCMdao.selectGCMBycId("uu");
		ArrayList<String> idTokens = new ArrayList<>();
		for (GCM gcm : GCMs) {
			idTokens.add(gcm.getcToken());
			System.out.println("cId: " + gcm.getcToken());
			System.out.println("pId: " + gcm.getpToken());
		}

		try {
			MulticastResult result = sender.send(message, idTokens, 1);

			if (result.getResults() != null) {
				int canonicalRegId = result.getCanonicalIds();
				if (canonicalRegId != 0) {

				}
			} else {
				int error = result.getFailure();
				System.out.println("Broadcast failure: " + error);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "register.action", method = RequestMethod.POST)
	public void register(@RequestBody Member member, HttpServletRequest req, HttpServletResponse resp) {
		boolean sw = true;
		PrintWriter writer = null;
		resp.setContentType("text/plain;charset=utf-8");
		if (member != null) {
			try {
				req.setCharacterEncoding("utf-8");
				writer = resp.getWriter();
				List<Member> oMember = dao.getList();
				for (Member member2 : oMember) {
					if (member2.getMemberId().equals(member.getMemberId())) {
						writer.println("fail");
						sw = false;
					}
				}
				if (sw == true) {
					member.setPasswd(Util.getHashedString(member.getPasswd(), "SHA-256"));

					if (member.getImage() == null) {
						member.setImage("not image");
					}
					dao.insert(member);
					writer.println("success");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}

	@RequestMapping(value = "update.action", method = RequestMethod.POST)
	public void update(@RequestBody Member member, HttpServletRequest req, HttpServletResponse resp) {
		String json = null;
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		if (member != null) {
			System.out.println(gson.toJson(member));

			GCM gcm = new GCM();
			gcm.setcId(member.getMemberId());
			gcm.setcToken(member.getInstanceId());
			gcm.setpId(member.getMemberId());
			gcm.setpToken(member.getInstanceId());
			dao.updateInstanceId(member);
			GCMdao.updateTokenByCid(gcm);
			GCMdao.updateTokenByPid(gcm);
		}
	}
	
	@RequestMapping(value = "getMembersById.action", method = RequestMethod.GET)
	public void getMembers(@RequestParam("cId") String memberId, HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("I'm comming!!!!!!");
		List<GCM> gMembers = GCMdao.selectGCMBycId(memberId);
		ArrayList<Member> members = new ArrayList<>(); 
		if(gMembers == null || gMembers.size() ==0){
			
		} else {
			for(GCM gcm : gMembers){
				Member m = new Member();
				m = dao.getMemberById(gcm.getpId());
				if(m == null){
					
				} else {
					members.add(m);
				}
			}
		}
		ArrayListMember aMembers = new ArrayListMember();
		aMembers.setMembers((ArrayList<Member>) members);
		
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter writer;
		String json = null;
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		try {
			writer = resp.getWriter();
			System.out.println(gson.toJson(aMembers));
			writer.println(gson.toJson(aMembers));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
