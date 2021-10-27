<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.hiball.common.security.domain.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String context = (String)request.getContextPath();
	User user = (User)request.getAttribute("user");
	String userName = "";
	if (context == null) 
		context = "";
	if (user != null){
		userName = user.getUsername();
	}
%>
<script data-main="<%=context %>/js/app_config" src="js/libs/require/require_2.2.0.js"></script>
<script>	
	define('global', {
		context:'<%=context%>',
		userId:'<%=userName%>'
	});
</script>
<div id="content" class="container-fluid contents_wrapper">
</div>