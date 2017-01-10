<%@page import="com.babySitter.model.dto.Mother"%>
<%@page import="com.babySitter.model.dao.MotherDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>사용자 정보</title>
	<link rel="Stylesheet" href="/babySitter/resources/styles/default.css" />
	<link rel="Stylesheet" href="/babySitter/resources/styles/input.css" />		
</head>
<body>

	<div id="pageContainer">
		
		<c:import url="/WEB-INF/views/include/header.jsp" />
		
		<div id="inputcontent">
			<br /><br />
		    <div id="inputmain">
		        <div class="inputsubtitle">회원기본정보</div>

		        <table>
		            <tr>
		                <th>아이디(ID)</th>
		                <%-- ((mother)request.getAttribute("mother")).getmotherId() --%>
		                <td>${ requestScope.mother.motherId }</td>
		            </tr>
		            <tr>
		                <th>이메일</th>
		                <td>${ requestScope["mother"].email }</td>
		            </tr>
		            <tr>
		                <th>등록일</th>
		                <td>${ mother.regDate }</td>
		            </tr>		            
		          <%--   <tr>
		                <th>사용자구분</th>
		                <td>${ mother.userType eq 'uesr' or mother.userType eq 'user' ? "사용자" : "관리자" }</td>
		            </tr>
		            <tr>
		                <th>활성화여부</th>
		                <td>${ mother.active eq true ? "활성사용자" : "비활성사용자" }</td>
		            </tr> --%>		            		            
		        </table>
		        <div class="buttons">
		        	<a href="list.action">목록</a>
		        	&nbsp;&nbsp;
		        	<a href="edit.action?motherid=${ mother.motherId }">정보수정</a>
		        </div>
		    </div>
		</div>   		
		
	</div>

</body>
</html>