<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.hiball.common.security.domain.User" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>회원 관리 페이지</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1 maximum-scale=1 minimum-scale=1" />
	<%
		String context = (String)request.getContextPath();
		//String version = "20190722";
		long version = System.currentTimeMillis() ;
		if (context == null) 
			context = "";
	%>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/bootstrap/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/jqwidgets/jqx.base.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/jqwidgets/jqx.bootstrap.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/jquery/jquery-confirm.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/css/jquery-ui.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/css/hiball_style.css?ver=<%=version%>"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/css/hiball_game_style.css?ver=<%=version%>"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/css/hiball_video_style.css?ver=<%=version%>"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/css/hiball_mode_style.css?ver=<%=version%>"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/css/font-config.css?ver=<%=version%>"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/video/video-js.css"/>
	<script src="https://kit.fontawesome.com/2cb4ce65ff.js"></script>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<link rel="icon" type="image/png" sizes="72*72" href="<%=context %>/assets/images/410GPhKeg-L2.png" />
	
</head> 
<body>
	<div id="layout_main" class="container-fluid layout_container">
		<!-- header -->
		<%-- <header class="row layout_row">
            <div class="col-12 bar"></div>
            <div class="col-6 logo-wrap">
                <img width="60" src="<%=context %>/assets/images/main_logo.png" alt="Hi Ball" />
			</div>
			<div class="col-5 lang-info">
				 <select id="useLang">
				    <option value="kr">한국어</option>
				    <option value="en">English</option>
				 </select> 
			</div>
			<div class="col-1 loging-info">
			<c:if test="${not empty user}">
				<img id="logoutBtn" width="25" src="<%=context %>/assets/images/design/012_power-512.png"/>
			</c:if>
			</div>
		</header> --%>
		<!-- //header -->
		
		<!-- New Header -->
		<div style="background-color: #5D5D5D; color: #ffffff; font-size: 40px; height: 60px; padding: 5px; valign:middle">Home Page 

		</div>
		<!-- //New Header -->

		<!-- Contents -->
		
		<div style="width:1200px; height:600px; float:left; margin-left:10px">
		<div class="row layout_row">
			<decorator:body></decorator:body>
		</div>
		</div>
		<!-- // Contents -->
		
		<!-- New Footer -->
		<div style="background-color:#5D5D5D;height:40px;padding:5px;margin-top:700px"></div>
		<!-- //New Footer -->
		
		<!-- footer -->
		<!-- <footer class="row layout_row">
			<div class="col-12 box_footlist">
				<div class="foot_right">
					COPYRIGHT ⓒ 2017 by hiball. ALL RIGHTS RESERVED.
				</div>
			</div>
		</footer>
	</div> -->
	<!-- //footer -->
	<div id="jqxLoader"></div>
</body> 
</html>