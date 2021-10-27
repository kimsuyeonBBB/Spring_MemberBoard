<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div style="background-color: #5D5D5D; color: #ffffff; font-size: 40px; height: 60px; padding: 5px; valign:middle">Home Page 
	
</div>
<br>
<div style='margin-left:20px;'>
<h2>사용자 로그인</h2>
<br>

<div class="row">
<div class="col-1.5">
<span style='font-size:15px; margin-left:10px;'>ID : <input id="inputId" type="text" name="id" style="width:210px;"></span><br><br>
<span style='font-size:15px; margin-left:10px;'>Password : <input id="inputPassword" type="password" name="password" style="width:160px;"></span>
</div>
<div class="col-1">
<input id="submit" type="button" value="Login" style="width:60px; height:60px; font-size:13px;">
</div>
</div>
<br>
<p style='font-size:13px;'><a href='memberAdd'>회원 가입</a><a>&nbsp; / &nbsp;</a><a href='findId'>ID 찾기</a><a>&nbsp; / &nbsp;</a><a href='findPw'>Password 찾기</a></p>

</div>

<div style="background-color:#5D5D5D;height:40px;padding:5px;margin-top:700px"></div>
	
<script>
    
	$(function() {
		$('#submit').click( function() {
			submitForm();
		});
	});
	
	function submitForm() {
		console.log("Submit Form!!!");
		
		var $form = $('<form></form>');
		$form.attr('action', 'j_spring_security_check');
		$form.attr('method', 'post');
		$form.appendTo('body');
		
		var userId = $("#inputId").val();
		var userPw = $("#inputPassword").val();
		
		var inputUser = $("<input type='hidden' value='"+userId+"' name='user_id'>");
		var inputPw = $("<input type='hidden' value='"+userPw+"' name='user_pw'>");
		
		$form.append(inputUser).append(inputPw);
		$form.submit();
	}
</script>	
</body>
</html>