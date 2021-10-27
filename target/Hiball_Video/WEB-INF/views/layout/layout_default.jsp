<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%
	String context = (String)request.getContextPath();
	if (context == null) 
		context = "";
%>
<html>
<head>
	<title><decorator:title default="HIBALL" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

	<link rel="stylesheet" type="text/css" href="<%=context %>/assets/css/design/include.css">
	<link rel="stylesheet" type="text/css" href="<%=context %>/assets/css/design/layout_w.css">
	<script src="<%=context %>/js/libs/jquery/jquery-1.12.4.min.js"></script>
</head> 
<body>
	<!-- header -->
	<div id="header">
		<div class="box_top">
			<h1><a href="javascript:void(0)" clientId="record" uri="#" id="homeBtn"><img width="158" src="<%=context %>/assets/images/main_logo.png" alt="Hi Ball" /></a></h1>
			<div style="position:absolute; bottom:10px; left:190px; margin-top:15px; margin-bottom:5px; color:#c8c8c8; font-size:25px; font-weight:bold;">관리 시스템</div>
		</div>
	</div>
	<!-- //header -->
	<!-- Content 영역 -->
	<div class="bodyWrapper">
		<div id="content" class="ie8-margBM">
			<decorator:body></decorator:body>
		</div>
	</div>
	<!-- End of Content 영역 -->
	<!-- footer -->
	<div id="footer">
		<div class="box_footlist">
			<ul>
				<li><a href="javascript:void(0)">이용약관</a></li>
				<li><a href="javascript:void(0)">묻고답하기(FAQ)</a></li>
				<li><a href="javascript:void(0)">1:1문의하기</a></li>
				<li><a href="javascript:void(0)">사업제휴</a></li>
				<li><a href="javascript:void(0)">이메일무단수집거부</a></li>
			</ul>
		</div>
		<div class="box_foot">
			<div>
				<div class="foot_right">
					<span>상호명 : 하이볼(주)</span>
					<span>대표 : 김재승</span>
					<span>주소 : 경남 창원시 의창구 창원대학로 20 82호관 320호 (사림동, 창원대학교)</span>
					<span class="baknone">우편번호 : 51140</span>
					<span>대표전화 : 055-289-0351</span>
					<span>FAX : 055-289-0350</span>
					<p>COPYRIGHT ⓒ 2017 by hiball. ALL RIGHTS RESERVED.</p>
				</div>
			</div>
		</div>
	</div>
	<!-- footer -->
</body> 
</html>