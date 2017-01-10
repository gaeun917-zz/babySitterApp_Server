<%@ page import="com.nhserver.model.dto.Member"%>
<%@ page language="java" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<div id="header">
            <div class="title">
                <a href="/nhserver/home.action">DEMO WEBSITE</a>
            </div>
            <div class="links">
            <c:choose>
            	<%-- <c:when test="${ sessionScope.loginuser == null }"> --%>
            	<c:when test="${ empty sessionScope.loginuser }">
            	<a href="/nhserver/account/login.action">로그인</a>
                <a href="#">등록</a>
            	</c:when>
            	<c:otherwise>
            	${ loginuser.memberId }님 환영합니다.
            	<a href="/nhserver/account/logout.action">로그아웃</a>
            	</c:otherwise>
            </c:choose>
            </div>
        </div>        
        <div id="menu">
            <div>
                <ul>
                	<!-- a : 페이지 이동을 처리하는 마크업 -->
                    <li><a href="/demoweb-spring-compact5/member/list.action">사용자관리</a></li>
					<li><a href="#">메일보내기</a></li>
					<li><a href="/demoweb-spring-compact5/upload/list.action">자료실</a></li>
					<li><a href="/demoweb-spring-compact5/board/list.action">게시판</a></li>
                </ul>
            </div>
        </div>
        <div style="text-align:right;margin-top:1px;
        	border:solid 1px;padding:5px">
        	[ TOTAL : ${ total } 명 ]
        	[ CURRENT : ${ current } 명 ]
        </div>
        
        
        
        
        
        
        
        
        
        
        
        