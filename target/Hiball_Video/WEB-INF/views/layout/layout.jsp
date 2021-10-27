<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html>
<head>
	<title><decorator:title default="HIBALL" /></title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1 maximum-scale=1 minimum-scale=1" />
	<%
		String context = (String)request.getContextPath();
		if (context == null) 
			context = "";
	%>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/bootstrap/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/jqwidgets/jqx.base.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/jqwidgets/jqx.bootstrap.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/jquery/jquery-confirm.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/css/hiball_style.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/css/hiball_menu_style.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/css/hiball_video_style.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/css/font-config.css"/>
</head> 
<body>
	<div class="container-fluid layout_container">
		<!-- header -->
		<header class="row layout_row">
			<div class="col-12 bar"></div>
			<div class="col-12 logo-wrap">
				<img width="79" src="<%=context %>/assets/images/main_logo.png" alt="Hi Ball" />
			</div>
		</header>
		<!-- //header -->
		<!-- Contents -->
		<div class="row layout_row">
			<decorator:body></decorator:body>
		</div>
		<!-- // Contents -->
		<!-- footer -->
		<footer class="row layout_row">
			<div class="col-12 box_footlist">
				<!-- <ul>
					<li><a href="javascript:void(0)">이용약관</a></li>
					<li><a href="javascript:void(0)">묻고답하기(FAQ)</a></li>
					<li><a href="javascript:void(0)">1:1문의하기</a></li>
					<li><a href="javascript:void(0)">사업제휴</a></li>
				</ul> -->
				<div class="foot_right">
					COPYRIGHT ⓒ 2017 by hiball. ALL RIGHTS RESERVED.
				</div>
			</div>
		</footer>
	</div>
	<!-- //footer -->
	<div id="jqxLoader"></div>
</body> 
</html>