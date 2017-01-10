package com.nhserver.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nhserver.model.dao.BoardDao;
import com.nhserver.model.dto.Board;
import com.nhserver.model.dto.BoardComment;
import com.nhserver.model.dto.Member;
import com.nhserver.ui.ThePager;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardDao dao;

	@RequestMapping(value = "list.action", method = RequestMethod.GET)
	public ModelAndView showBoardList(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		//로그인 상태가 아닌 경우 로그인 페이지로 이동
		if (request.getSession().getAttribute("loginuser") == null) {
			mav.setViewName(
				"redirect:/loginform.action?" + 
				"returnuri=" + request.getRequestURI());
			return mav;
		}
		
		int currentPage = 1;
		int pageSize = 2;
		int dataCount = 0;
		int pagerSize = 2;
		String url = "list.action";
		
		//
		String page = request.getParameter("pageno");		
		if (page != null && page.length() > 0) {
			currentPage = Integer.parseInt(page);
		}		
		int startRow = (currentPage - 1) * pageSize + 1;		
		//데이터베이스에서 데이터 조회
		List<Board> boards = dao.selectBoardList2(startRow, startRow + pageSize);
		dataCount = dao.getBoardCount();
		
		ThePager pager = 
			new ThePager(dataCount, currentPage, pageSize, pagerSize, url);
		
		mav.setViewName("board/list");
		mav.addObject("boards", boards);
		mav.addObject("pageno", currentPage);
		mav.addObject("pager", pager);
		
		return mav;
				
	}
	
	@RequestMapping(value = "detail.action", method = RequestMethod.GET)
	public ModelAndView showBoardByBoardNo(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		//로그인 상태가 아닌 경우 로그인 페이지로 이동
		if (request.getSession().getAttribute("loginuser") == null) {
			mav.setViewName(
				"redirect:/loginform.action?" + 
				"returnuri=" + request.getRequestURI());
			return mav;
		}
				
		//요청 정보에서 내용을 표시할 글번호를 읽고 변수에 저장
		//(없으면 목록으로 이동)
		String boardNo = request.getParameter("boardno");
		if (boardNo == null || boardNo.length() == 0) {
			mav.setViewName("redirect:/board/list.action");					
			return mav;
		}
		int no = Integer.parseInt(boardNo);
				
		//데이터베이스에서 데이터 조회
		Board board = dao.selectBoardByBoardNo(no);
		
		//조회 실패하면 목록으로 이동
		if (board == null) {
			mav.setViewName("redirect:/board/list.action");
			return mav;
		}
		
		dao.updateBoardReadCount(board.getBoardNo());
		board.setReadCount(board.getReadCount() + 1);
		
		String pageNo = "1";
		if (request.getParameter("pageno") != null) {
			pageNo = request.getParameter("pageno");
		}
		
		//조회된 데이터를 jsp 처리할 수 있도록 request 객체에 저장
		mav.setViewName("board/detail");
		mav.addObject("board", board);
		mav.addObject("pageno", pageNo);

		return mav;
		
	}
	
	@RequestMapping(value = "writeform.action", method = RequestMethod.GET)
	public String getBoardWriteForm() {
		return "board/writeform";
	}
	
	@RequestMapping(value = "write.action", method = RequestMethod.POST)
	public String writeBoard(HttpServletRequest req) {
		//1. 브라우저에 사용자가 입력한 데이터를 읽어서 변수에 저장 (요청 정보에서 데이터 읽기)
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		Member member = 
			(Member)req.getSession().getAttribute("loginuser");
		
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(member.getMemberId());
		board.setContent(content);
		
		dao.insertBoard(board);

		return "redirect:/board/list.action";
	}
	
	@RequestMapping(value = "edit.action", method = RequestMethod.GET)
	public ModelAndView showBoardEditForm(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		
		String boardNo = request.getParameter("boardno");
		if (boardNo == null || boardNo.length() == 0) {
			mav.setViewName("redirect:/board/list.action");
			return mav;
		}
		
		Board board = dao.selectBoardByBoardNo(Integer.parseInt(boardNo));
		
		if (board == null) {
			mav.setViewName("redirect:/board/list.action");
			return mav;
		}
		
		String pageNo = "1";
		if (request.getParameter("pageno") != null) {
			pageNo = request.getParameter("pageno");
		}
				
		mav.addObject("board", board);
		mav.addObject("pageno", pageNo);
		mav.setViewName("board/editform");
		return mav;
	}
	
	@RequestMapping(value = "update.action", method = RequestMethod.POST)
	public String updateBoard(HttpServletRequest req) {
		//1. Board 객체를 만들고 사용자가 입력한 데이터를 저장
		Board board = new Board();
		board.setBoardNo(
			Integer.parseInt(req.getParameter("boardno")));
		board.setTitle(req.getParameter("title"));
		board.setContent(req.getParameter("content"));

		//2. 데이터베이스에 변경된 내용 적용
		dao.updateBoard(board);
		
		//3. 목록 페이지로 이동
		return "redirect:/board/detail.action" +
			"?boardno=" + board.getBoardNo() + 
			"&pageno=" + req.getParameter("pageno");
	}
	
	@RequestMapping(value = "delete.action", method = RequestMethod.GET)
	public String deleteBoard(HttpServletRequest req) {
		//1. 요청 데이터 읽기 (글번호)
		String boardNo = req.getParameter("boardno");
		if (boardNo == null || boardNo.length() == 0) {
			return "redirect:/board/list.action";
		}
		
		//2. 데이터 처리 (db에서 데이터 변경)
		dao.deleteBoard(Integer.parseInt(boardNo));
		
		//3. 목록으로 이동 		
		return "redirect:/board/list.action";
	}
	
	@RequestMapping(value = "replyform.action", method = RequestMethod.GET)
	public ModelAndView showBoardReplyForm(HttpServletRequest req) {
		
		ModelAndView mav = new ModelAndView();
		//댓글의 대상 글번호를 읽어서 변수에 저장
		//(대상 글번호는 boardview.jsp에서 전송된 데이터)
		if (req.getParameter("boardno") == null) {
			mav.setViewName("redirect:/board/list.action");
			return mav;
		}
		int number = Integer.parseInt(req.getParameter("boardno"));
		
		//댓글  대상글의 번호로 데이터베이스에서 조회
		Board board = dao.selectBoardByBoardNo(number);
		
		//조회 실패하면 목록으로 이동
		if (board == null) {
			mav.setViewName("redirect:/board/list.action");
			return mav;			
		}
		
		String pageNo = "1";
		if (req.getParameter("pageno") != null) {
			pageNo = req.getParameter("pageno");
		}
		
		mav.addObject("board", board);
		mav.addObject("pageno", pageNo);
		mav.setViewName("board/replyform");
		
		return mav;
	}
	
	@RequestMapping(value = "reply.action", method = RequestMethod.POST)
	public String writeBoardReply(HttpServletRequest req) {
		
		Member member = 
			(Member)req.getSession().getAttribute("loginuser");
		
		//2. Board 객체를 생성하고 입력된 데이터를 읽어서 객체에 저장
		Board board = new Board();
		board.setBoardNo(
			Integer.parseInt(req.getParameter("boardno")));
		board.setTitle(req.getParameter("title"));
		board.setWriter(member.getMemberId());
		board.setContent(req.getParameter("content"));
		Board board2 = 
			dao.selectBoardByBoardNo(board.getBoardNo());
		board.setGroup(board2.getGroup());
		board.setStep(board2.getStep() + 1);
		board.setDepth(board2.getDepth() + 1);
		
		//3. 데이터베이스에 변경 사항 적용
		dao.insertReply(board);

		//4. view.action 이동
		String pageNo = "1";
		if (req.getParameter("pageno") != null)
			pageNo = req.getParameter("pageno");

		return String.format("redirect:/board/detail.action?boardno=%d&pageno=%s",
			board.getBoardNo(), pageNo);
	}
	
	@RequestMapping(value = "writecomment.action", method = RequestMethod.POST)
	public String writeComment(HttpServletRequest req) {
		//1. dto객체 생성 -> 입력 정보 읽어서 dto객체에 저장
		int boardNo = Integer.parseInt(req.getParameter("boardno"));

		BoardComment comment = new BoardComment();
		comment.setContent(req.getParameter("content"));
		comment.setBoardNo(boardNo);
		comment.setWriter(
			((Member)req.getSession().getAttribute("loginuser")).getMemberId());

		//2. 데이터베이스에 데이터 insert
		dao.insertComment(comment);

		//3. boardview로 이동
		String pageNo = req.getParameter("pageno");
		return "redirect:/board/detail.action?boardno=" + boardNo + "&pageno=" + pageNo;
	}
	
	@RequestMapping(value = "deletecomment.action", method = RequestMethod.GET)
	public String deleteComment(HttpServletRequest req) {
		//1. 댓글번호 읽어서 변수에 저장
		int commentNo = Integer.parseInt(req.getParameter("commentno"));
		
		//2. 댓글번호에 해당하는 댓글 삭제
		dao.deleteComment(commentNo);
		
		//3. view.action로 이동
		return "redirect:/board/detail.action?" + 
			"boardno=" + req.getParameter("boardno") + 
			"&pageno=" + req.getParameter("pageno");
		
	}
	
	@RequestMapping(value = "updatecomment.action", method = RequestMethod.POST)
	public String updateComment(HttpServletRequest req) {
		//1. BoardComment 객체 생성 -> 전송된 데이터를 읽어서 저장
		BoardComment comment = new BoardComment();
		comment.setCommentNo(
			Integer.parseInt(req.getParameter("commentno")));
		comment.setContent(req.getParameter("content"));

		//2. 데이터베이스 업데이트
		dao.updateComment(comment);

		//3. view.action으로 이동
		return "redirect:/board/detail.action?" + 
			"boardno=" + req.getParameter("boardno") + 
			"&pageno=" + req.getParameter("pageno");
	}
	
}
















