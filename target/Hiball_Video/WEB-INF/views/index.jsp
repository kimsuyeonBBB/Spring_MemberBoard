<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String context = (String)request.getContextPath();
	String userId = (String)request.getAttribute("user");
	if (context == null) 
		context = "";
%>
<script data-main="<%=context %>/js/main_new" src="<%=context %>/js/libs/require/require_2.2.0.js"></script>
<script>
	define('global', {
		context:'<%=context%>',
		userId:'<%=userId%>'
	});
</script>
<div id="content" class="container-fluid contents_wrapper">
</div>