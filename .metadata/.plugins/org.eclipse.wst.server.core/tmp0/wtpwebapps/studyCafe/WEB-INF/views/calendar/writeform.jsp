<%@page import="com.studycafe.model.dto.Calendar"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8" />
<title>일정 작성하기</title>

<!-- jQuery UI CSS파일  -->
<!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" /> -->
<!-- jQuery 기본 js파일 -->
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script> -->
<!-- jQuery UI 라이브러리 js파일 -->
<!-- <script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> -->

<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />

<link rel="apple-touch-icon" sizes="114x114" href="/apple-touch-icon-114x114.png">
<link rel="apple-touch-icon" sizes="72x72" href="/apple-touch-icon-72x72.png">
<link rel="apple-touch-icon" sizes="144x144" href="/apple-touch-icon-144x144.png">
<link rel="apple-touch-icon" sizes="60x60" href="/apple-touch-icon-60x60.png">
<link rel="apple-touch-icon" sizes="120x120" href="/apple-touch-icon-120x120.png">
<link rel="apple-touch-icon" sizes="76x76" href="/apple-touch-icon-76x76.png">
<link rel="apple-touch-icon" sizes="152x152" href="/apple-touch-icon-152x152.png">
<link rel="icon" type="image/png" href="/favicon-196x196.png" sizes="196x196">
<link rel="icon" type="image/png" href="/favicon-160x160.png" sizes="160x160">
<link rel="icon" type="image/png" href="/favicon-96x96.png" sizes="96x96">
<link rel="icon" type="image/png" href="/favicon-16x16.png" sizes="16x16">
<link rel="icon" type="image/png" href="/favicon-32x32.png" sizes="32x32">
<meta name="msapplication-TileColor" content="#2b5797">
<meta name="msapplication-TileImage" content="/mstile-144x144.png">
<link rel="stylesheet" type="text/css" media="screen" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href="./css/prettify-1.0.css" rel="stylesheet">
<link href="./css/base.css" rel="stylesheet">
<link href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" rel="stylesheet">

<link href="/studyCafe/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.min.js" charset="utf-8"></script>
<script type="text/javascript" src="/studyCafe/resources/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	$(function() {
		var oEditors = [];

		// 추가 글꼴 목록
		//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];

		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditors,
			elPlaceHolder : "ir1",
			sSkinURI : "/studyCafe/resources/SmartEditor2Skin.html",
			htParams : {
				bUseToolbar : true, // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseVerticalResizer : true, // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseModeChanger : true, // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
				//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
				fOnBeforeUnload : function() {
					//alert("완료!");
				}
			}, //boolean
			fOnAppLoad : function() {
				//예제 코드
				//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
			},
			fCreator : "createSEditor2"
		});
		function pasteHTML() {
			var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
			oEditors.getById["ir1"].exec("PASTE_HTML", [ sHTML ]);
		}

		function showHTML() {
			var sHTML = oEditors.getById["ir1"].getIR();
			alert(sHTML);
		}

		function submitContents(elClickedObj) {
			oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []); // 에디터의 내용이 textarea에 적용됩니다.

			// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("ir1").value를 이용해서 처리하면 됩니다.
			try {
				elClickedObj.form.submit();
				/* var url = '/studyCafe/page/board/list.action?menuno=' + ${menu.menuNo} + '&memberpageno=' + ${memberpageno}; 
		    	$(location).attr('href', url); */
			} catch (e) {
			}
		}
		function setDefaultFont() {
			var sDefaultFont = '궁서';
			var nFontSize = 24;
			oEditors.getById["ir1"].setDefaultFont(sDefaultFont, nFontSize);
		}

		$('#btnSubmit').on('click', function(event) {
			submitContents(this);
		})

	})
</script>
</head>

<body>
		<div>
			<c:import url="/WEB-INF/views/page/index.jsp" />
			<script type="text/javascript" src="//code.jquery.com/jquery-2.1.1.min.js"></script>
			<script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
			<script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
			<script src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>
			<script type="text/javascript">
			    $(function () {
			        $('#datetimepicker6').datetimepicker();
			        $('#datetimepicker7').datetimepicker({
			            useCurrent: false //Important! See issue #1075
			        });
			        $("#datetimepicker6").on("dp.change", function (e) {
			            $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
			        });
			        $("#datetimepicker7").on("dp.change", function (e) {
			            $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
			        });
			    });
			</script>

			
			<!-- <script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> -->
			
			<div class="col-xs-12 col-sm-9" style="text-align: center">
				<div class="panel panel-default">
					<div class="panel-heading"><h4><strong>일정 등록</strong></h4></div>
						<form action="write.action" method="post">
							<table class="table">
								<tr>
									<th style="text-align: center">시작·마감일</th>
									<td style="text-align: left;">
										<input type="text" id="datetimepicker6" name="startDate" style="width: 49.5%" placeholder="클릭하세요"/>
										<input type="text" id="datetimepicker7" name="dueDate" style="width: 49.4%" placeholder="클릭하세요"/>
									</td>
								</tr>
								<tr>
									<th style="text-align: center">작성자</th>
									<td style="text-align: left;">
					                	<input type="hidden" name="memberNo" value='${ sessionScope.loginuser.memberNo }' />
					                	${ sessionScope.loginuser.memberId }
					                </td>
								</tr>
								<tr>
									<th style="text-align: center">제목</th>
									<td style="text-align: left;">
										<input type="text" name="title" style="width:100%">
										 <input type="hidden" name="noticemenu" value="${noticemenu.menuNo}" />
										<input type="hidden" name="memberpageno" value="${memberpageno}" />
									</td>
								</tr>
								<tr>
									<th style="text-align: center">내용</th>
									<td style="text-align: left;">
										<textarea name="content" id="ir1" rows="10" cols="100"
											style="width: 100%; height: 300px; display: none;">
										</textarea>
									</td>
								</tr>
							</table>
							<div class="buttons" style="text-align: center;">
								<!-- 아래 a 링크는 input type='submit' 버튼을 누르는 효과 발생 -->
								<!-- <a href="javascript:document.forms[0].submit();">글쓰기</a>
								&nbsp;&nbsp; <a href="/studyCafe/board/list.action">목록보기</a> -->
								<button type="submit" value="등록" class="btn btn-primary btn-sm" id="btnSubmit"
										onclick="document.forms[0].submit();">등록</button>	
								<button type="button" value="취소" class="btn btn-primary btn-sm"
									onclick="location.href='list.action?memberpageno=${memberpageno}&noticemenu=${noticemenu.menuNo}';">취소</button>
							</div>
							<br/>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<br/><br/><br/><br/>
</body>
</html>