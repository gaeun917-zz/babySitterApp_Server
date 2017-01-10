<%-- <%@page import="com.babySitter.model.dto.Board"%>
 --%><%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<title>달력 보여주기</title>
<!-- <meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<link href="/babySitter/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="/babySitter/resources/bootstrap/js/bootstrap.min.js"></script> -->


<!-- <script src='/babySitter/resources/js/jquery.min.js'></script> -->
<!-- <link href='/babySitter/resources/styles/fullcalendar.css' rel='stylesheet' />
<link href='/babySitter/resources/styles/fullcalendar.print.css'	rel='stylesheet' media='print' />
<script src='/babySitter/resources/js/moment.min.js'></script>
<script src='/babySitter/resources/js/fullcalendar.min.js'></script> -->

<%-- <script>

	$(document).ready(function() {

		alert(JSON.stringify(<%= request.getAttribute("calendarJson") %>));
		
		$('#calendar').fullCalendar({
			
			defaultDate: '2016-06-13',
			editable: true,
			eventLimit: true, // allow "more" link when too many events
			
			events: <%= request.getAttribute("calendarJson") %>
		
		});
		/* '${ calendar.calendarNo }' */
		$('.fc-content').on('click', function(event) {
			location.href = '/babySitter/calendar/view.action?calendarno=' + ${calendar.calendarNo};
		})<%= request.getAttribute("c2") %>
		
		$('#addSchedule').on('click', function(event) {
			location.href = '/babySitter/calendar/writeform.action';
		})
		//click, mouseover : 요소 안에 들어올 때 발생, mouseout : 요소를 벗어날 때 발생
				
	});

</script> --%>
<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 900px;
	margin: 0 auto;
}
</style>
<link href='/babySitter/resources/styles/fullcalendar.css' rel='stylesheet' />
<link href='/babySitter/resources/styles/fullcalendar.print.css'	rel='stylesheet' media='print' />
</head>
<body>
		<div>
			<c:import url="/WEB-INF/views/page/index.jsp" />
			<script src='/babySitter/resources/js/moment.min.js'></script>
			<script src='/babySitter/resources/js/fullcalendar.min.js'></script>
			<script>
				$(document).ready(function() {
			
					<%-- alert(JSON.stringify(<%= request.getAttribute("calendarJson") %>)); --%>
					
					$('#calendar').fullCalendar({
						
						defaultDate: '2016-06-13',
						editable: true,
						eventLimit: true, // allow "more" link when too many events
						
						events: <%= request.getAttribute("calendarJson") %>
					
					});
					/* '${ calendar.calendarNo }' */
					<%-- $('.fc-content').on('click', function(event) {
						location.href = '/babySitter/calendar/view.action?calendarno=' + ${calendar.calendarNo};
					})<%= request.getAttribute("c2") %> --%>
					
					$('#addSchedule').on('click', function(event) {
						location.href = '/babySitter/calendar/writeform.action?memberpageno=' + ${memberpageno} + '&noticemenu=' + ${noticemenu.menuNo};
					})
					//click, mouseover : 요소 안에 들어올 때 발생, mouseout : 요소를 벗어날 때 발생
							
				});
			</script>
			<div class="col-xs-12 col-sm-9" style="text-align: center">
				<button id="addSchedule" type="button" class="btn btn-danger">+
					일정 추가하기</button>
			<div id='calendar'></div>
			</div>
		</div>
	</div>
</div>
<br/><br/><br/><br/>
</body>
</html>













