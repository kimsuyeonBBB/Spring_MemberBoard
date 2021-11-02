<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 페이지</title>
</head>
<body>

<div style="background-color: #5D5D5D; color: #ffffff; font-size: 40px; height: 70px; padding: 5px; valign:middle;">Home Page </div>
<br>

	<div class="container" style="border:1px solid #cecece; margin-top:130px; width:500px; height:450px; display: flex; justify-content: center; align-items: center;">
		<div>
			<h2 style="text-align:center;">사용자 로그인</h2>
			<br>

			<input class="form-control" id="inputId" type="text" name="id" style="width:350px;" placeholder="ID">
			<br>
			<input class="form-control" id="inputPassword" type="password" name="password" style="width:350px;" placeholder="Password">

			<br><br>
			<input class="btn btn-outline-primary" id="submit" type="button" value="Login" style="width:350px; height: 40px; font-size: 13px;">
		
			<br><br><br>
			<p style='font-size: 15px;'>
				<a href='memberAdd'>&emsp;회원 가입</a><a>&nbsp;&emsp; / &nbsp;&emsp;</a><a href='findId'>ID 찾기</a><a>&emsp;&nbsp; / &nbsp;&emsp;</a><a href='findPw'>Password 찾기</a>
			</p>
		</div>
	</div>

	<div style="background-color:#5D5D5D;height:50px;padding:5px; bottom:0; position:fixed; width:100%;"></div>
	
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