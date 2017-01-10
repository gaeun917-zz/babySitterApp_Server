<%@page import="com.babySitter.model.dto.Mother"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.babySitter.model.dao.MotherDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
    <title></title>
    <link rel="Stylesheet" href="/babySitter/resources/styles/default.css" />
</head>
<body>

    <div id="pageContainer">

    	<c:import url="/WEB-INF/views/include/header.jsp" />
        
        <div id="content">
        	<br /><br />
        	<div style='text-align:center'>
        		[&nbsp;<a href="register.action">사용자 등록</a>&nbsp;]
        	</div>
        	<br /><br />        	
        	<table border="1" align="center" width="700px">
        		<tr style="height:30px;background-color:orange">
        			<td>아이디</td>
        			<td>이름</td>
        			<!-- <td>패스워드</td> -->
        			<td>이메일</td>
        			<td>등록일자</td>
        			<td>성별</td>
        			<!-- <td>페북아이디</td>
        			<td>카카오아이디</td>
        			<td>상태</td>
        			<td>등급</td> -->
        		</tr>        	
        	<c:forEach var="mother" items="${ mothers }">
        		<tr style="height:30px">
        			<td>
        				<!-- view.action?motherid=...... 경로를 만들고 변수에 저장 -->
        				<c:url value="view.action" var="viewUrl">
        					<c:param name="motherid" value="${ mother.motherId }" />
        				</c:url>
        				<a href="${ viewUrl }">${ mother.motherId }</a>
        			</td>
        			<td>${ mother.name }</td>
        			<%-- <td>${ mother.passwd }</td> --%>
        			<td>${ mother.email }</td>
        			<td>${ mother.regDate }</td>
        			<td>${ mother.gender }</td>
        			<%-- <td>${ mother.facebookId }</td>
        			<td>${ mother.kakaoId }</td>
        			<td>${ mother.status }</td>
        			<td>${ mother.gradeLevel }</td> --%>
        		</tr>
        	</c:forEach>
        	</table>
        	
        </div>
    </div>
    
</body>
</html>
