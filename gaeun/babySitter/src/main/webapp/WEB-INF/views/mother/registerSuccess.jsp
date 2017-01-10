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
<link rel="Stylesheet" href="/babySitter/resources/bootstrap/css/bootstrap.min.css" />
<link rel="Stylesheet" href="/babySitter/resources/bootstrap/js/bootstrap.min.js" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript">




</script>
</head>
<body>

    <div id="pageContainer">
    	<c:import url="/WEB-INF/views/include/header2.jsp" />    
    	<br /><br /><br /><br />    
        	<div id="content">
        		<br /><br />
        	<div style='text-align:center'>	
        	<div>
        		<section class="col-sm-4" >
         			<img class="img-circle" src="/babySitter/resources/image/teacher1.jpg"  height="250px" width="250px" align="middle" alt="Icon">
          			<br/><br/><br/>
          		</section>

          	</div>
          	<br /><br /><br />
          	<section>
          	<div>
        		<c:if test="${ not empty mother }">
				<a style="font-size: 50px">${ mother.name } 님 사용자 등록이 완료되었습니다 .</a>
                 </c:if>
			</div>
			</section>
				</div>	
        	
        	<br /><br /><br /><br />
        	 <div class="button" align="center">
			        <input type="button" id="btnHome" class="btn btn-info center" value="HOME" onclick="location.href='/babySitter/home.action';" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			       <input type="button"  id="btnmypage"class="btn btn-info center" value="MY PAGE" onclick="location.href='/babySitter/mother/mypage.action';"/>
		        </div>
        	<br /><br /> 
       	
        <%-- 	<table border="1" align="center" width="700px">
        		<!-- <tr style="height:30px;background-color:orange">
        			<td>아이디</td>
        			<td>이름</td>
        			<td>패스워드</td>
        			<td>이메일</td>
        			<td>등록일자</td>
        			<td>성별</td>
        			<td>페북아이디</td>
        			<td>카카오아이디</td>
        			<td>상태</td>
        			<td>등급</td>
        		</tr>  -->       	
        
        		<tr style="height:30px">
        			<td>
        				<!-- view.action?motherid=...... 경로를 만들고 변수에 저장 -->
        				<c:url value="view.action" var="viewUrl">
        					<c:param name="motherid" value="${ mother.motherId }" />
        				</c:url>
        			<a href="${ viewUrl }"> 아이디:  ${ mother.motherId }</a>
        			</td>
        			<td>이름 : ${ mother.name }</td>
        			
        			
        		  filled in form 
        			
				<form class="form-horizontal">        						
        			
        			<div class="form-group">
        			    <label class="col-sm-2 control-label"> 이름 </label> 
     						 <div class ="col-sm-10">
								  <p>${ mother.name } </p>
						  	</div>	
					<div> 
					
				</form>	  

        			<td>${ mother.passwd }</td>
        			<td>이메일: ${ mother.email }</td>
        			<td>등록일자: ${ mother.regDate }</td>
        			<td>성별: ${ mother.gender }</td>
        			<td>${ mother.facebookId }</td>
        			<td>${ mother.kakaoId }</td>
        			<td>${ mother.status }</td>
        			<td>${ mother.gradeLevel }</td>
        		</tr>
        
        	</table> --%>
   
        	
        </div>
    </div>
    
</body>
</html>
