<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%
	String context = (String)request.getContextPath();
	//String version = "20190722";
	long version = System.currentTimeMillis() ;
	if (context == null) 
		context = "";
%>
<html>
<head>
	<title>회원 관리 페이지</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/bootstrap/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/css/hiball_login_style.css?ver=<%=version%>">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/css/hiball_style.css?ver=<%=version%>"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/css/font-config.css?ver=<%=version%>"/>
	<script type="text/javascript" src="<%=context %>/js/libs/jquery/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="<%=context %>/js/libs/jquery/jquery.cookie.js"></script>
	
	<link rel="icon" type="image/png" sizes="72*72" href="<%=context %>/assets/images/410GPhKeg-L2.png" />
	
</head> 
<body style="height:100%">
	<decorator:body></decorator:body>
	<!-- End of Content 영역 -->
</body> 
</html>