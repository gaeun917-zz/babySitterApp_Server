<%-- <%@page import="com.mvcdemoweb.model.dto.Board"%>
<%@page import="com.mvcdemoweb.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>글수정</title>
	<link rel="Stylesheet" href="/demoweb-spring-compact5/resources/styles/default.css" />
	<link rel="Stylesheet" href="/demoweb-spring-compact5/resources/styles/input2.css" />	
</head>
<body>

	<div id="pageContainer">
	
		<% pageContext.include("/WEB-INF/views/include/header.jsp"); %>
		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">게시판 글 수정하기</div>
		        <form action="update.action" 
		        	  method="post">
		       	<% Board board = (Board)request.getAttribute("board"); %>
		       	<input type='hidden' name="boardno" 
		       		value="<%= board.getBoardNo() %>" />
		       	<input type='hidden' name="pageno" value="${ pageno }" />
		        <table>
		            <tr>
		                <th>제목</th>
		                <td>
		                    <input type="text" name="title" style="width:480px"
		                    	value='<%= board.getTitle() %>' />
		                </td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td>
		                	<%= ((Member)session.getAttribute("loginuser")).getMemberId() %>
		                </td>
		            </tr>		            
		            <tr>
		                <th>내용</th>
		                <td>		                    
		                    <textarea 
		                    	name="content" cols="76" rows="15"><%= board.getContent() %></textarea>
		                </td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<!-- 아래 a 링크는 input type='submit' 버튼을 누르는 효과 발생 -->		        	
		        	<a href="javascript:document.forms[0].submit();">글쓰기</a>
		        	&nbsp;&nbsp;
		        	<a href='detail.action?boardno=<%= board.getBoardNo() %>&pageno=<%= request.getAttribute("pageno") %>'>취소</a>
		        </div>
		        </form>
		    </div>
		</div>   	
	
	</div>
	</div>

</body>
</html> --%>