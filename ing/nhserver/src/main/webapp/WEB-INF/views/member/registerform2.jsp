<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="form" 
	uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>사용자등록</title>
	<link rel="Stylesheet" href="/demoweb-spring-compact5/resources/styles/default.css" />
	<link rel="Stylesheet" href="/demoweb-spring-compact5/resources/styles/input.css" />
	
	<style type="text/css">
	.error { color : red; font-weight: bold }
	</style>
</head>
<body>

	<div id="pageContainer">
	
		<c:import url="/WEB-INF/views/include/header.jsp" />
		
		<div id="inputcontent">
			<br /><br />
		    <div id="inputmain">
		        <div class="inputsubtitle">회원기본정보</div>
		        
		        <form:form action="register.action" method="post" 
		        	modelAttribute="member">
		        <table>
		            <tr>
		                <th>아이디(ID)</th>
		                <td>		                    
		                    <form:input path="memberId" cssStyle="width:280px" />
		                    <form:errors path="memberId" cssClass="error" element="div" />
		                    <%-- 영문자, 숫자 조합 8개 15개 (대소문자, 숫자 반드시 혼용)  --%>
		                </td>
		            </tr>
		            <tr>
		                <th>비밀번호</th>
		                <td>
		                	<input type="password" name="passwd" style="width:280px" />
		                	<form:errors path="passwd" cssClass="error" element="div" />
		                	<%-- 영문자, 숫자 조합 8개 15개 (대소문자, 숫자 반드시 혼용)  --%>
		                </td>
		            </tr>
		            <tr>
		                <th>비밀번호 확인</th>
		                <td>
		                    <input type="password" name="confirm" style="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>이메일</th>
		                <td>
		                	<form:input type="text" path="email" cssStyle="width:280px" />
		                	<form:errors path="email" cssClass="error" element="div" />
		                	<%-- 이메일 형식 --%>
		                </td>
		            </tr>
		            <tr>
		                <th>사용자구분</th>
		                <td>
		                	<form:radiobutton path="userType" value="user" checked="checked" label="사용자" />
		                	<form:radiobutton path="userType" value="admin" label="관리자" />
		                </td>
		            </tr>
		            <tr>
		                <th>활성화여부</th>
		                <td>
		                	<form:checkbox path="active" value="true" label="활성사용자" />
		                </td>
		            </tr>
		            <%-- 모든 입력 요소에 대해 필수 입력 항목 처리 --%>
		        </table>
		        <div class="buttons">
		        	<input type="submit" value="등록" style="height:25px" />
		        	<input type="button" value="취소" style="height:25px"
		        		onclick="location.href='list.action';" />
		        	<!-- location.href='path' : path로 이동하는 javascript 명령 -->
		        </div>
		        </form:form>
		    </div>
		</div>   	
	
	</div>

</body>
</html>