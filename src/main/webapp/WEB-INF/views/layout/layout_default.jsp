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
	<title>영상 조회 시스템</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/bootstrap/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/css/hiball_login_style.css?ver=<%=version%>">
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/css/hiball_style.css?ver=<%=version%>"/>
	<link rel="stylesheet" type="text/css" media="screen" href="<%=context %>/assets/css/font-config.css?ver=<%=version%>"/>
	<script type="text/javascript" src="<%=context %>/js/libs/jquery/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="<%=context %>/js/libs/jquery/jquery.cookie.js"></script>
	
	<script>
		var userAgent = navigator.userAgent.toLowerCase(); // 접속 핸드폰 정보 
		// 모바일 홈페이지 바로가기 링크 생성 
		if (userAgent.match('iphone') == 'iphone') {
		    document.write('<meta name="apple-mobile-web-app-capable" content="yes">');
			document.write('<link rel="apple-touch-icon" type="image/png" sizes="72*72" href="<%=context %>/assets/images/h_icon_iphone.png?ver=<%=version%>" />')
		} else if (userAgent.match('ipad') == 'ipad') {
		    document.write('<meta name="apple-mobile-web-app-capable" content="yes">');
			document.write('<link rel="apple-touch-icon-precomposed" type="image/png" sizes="72*72" href="<%=context %>/assets/images/h_icon_iphone.png?ver=<%=version%>" />')
		} else if (userAgent.match('ipod') == 'ipod') {
		    document.write('<meta name="apple-mobile-web-app-capable" content="yes">');
			document.write('<link rel="apple-touch-icon" type="image/png" sizes="72*72" href="<%=context %>/assets/images/h_icon_iphone.png?ver=<%=version%>" />')
		} else if (userAgent.match('android') == 'android') {
    		document.write('<meta name="mobile-web-app-capable" content="yes">');
			document.write("<link rel='icon' type='image/png' sizes='192x192' href='<%=context %>/assets/images/h_icon.png?ver=<%=version%>' />")
		} else {
    		document.write('<meta name="mobile-web-app-capable" content="yes">');
			document.write("<link rel='icon' type='image/png' sizes='192x192' href='<%=context %>/assets/images/h_icon.png?ver=<%=version%>' />")
		}
	</script>
</head> 
<body style="height:100%">
	<decorator:body></decorator:body>
	<!-- End of Content 영역 -->
</body> 
</html>