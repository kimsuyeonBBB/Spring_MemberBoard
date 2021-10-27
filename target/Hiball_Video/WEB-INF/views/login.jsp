<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String context = (String)request.getContextPath();
	if (context == null) 
		context = "";
%>
<div class="container">
	<div id="content" class="row">
	<form id="loginForm">
		<div class="wrap_login">
			<div class="box_loginp">
				<h1><img src="<%=context %>/assets/images/design/login_title.png" alt="로그인" /></h1>
				<div class="box_login">
					<div class="left">
						<input type="text" name="" class="inp_id"  id="inputId" placeholder="  아이디를 입력해주세요."/><!-- class off추가 배경사라짐 -->
						<input type="password" name="" class="inp_pass" id="inputPassword" placeholder="  비밀번호를 입력해주세요."/><!-- class off추가 배경사라짐 -->
					</div>
					<div class="right">
						<div class="log_chk">
							<input type="checkbox" name="" id="idsave" /><label for="idsave">아이디저장</label>
						</div>
						<a href="javascript:void(0)" id="submit"><img src="<%=context %>/assets/images/design/btn_login.gif" alt="로그인" /></a>
					</div>
				</div>
				<div class="loginsearch">
					<span><a href="javascript:void(0)">아이디 찾기 &gt;</a></span> <span><a href="javascript:void(0)">비밀번호 찾기 &gt;</a></span>
				</div>
			</div>
		</div>
	</form>		
	</div>
</div>
<script>
	$(function() {
		$('.inp_id').on("click", function(){
			$("#inputId").attr("placeholder", "");
		});
		$('.inp_pass').on("click", function(){
			$("#inputPassword").attr("placeholder", "");
		});
		
		$("#inputPassword").keydown(function (key) {
	        if(key.keyCode == 13){//키가 13이면 실행 (엔터는 13)
	            submitForm();
	        }
	    });
		
		$('#submit').click( function() {
			submitForm();
		});
	});
	
	function submitForm() {
		console.log("Submit Form!!!");
		
		var $form = $('<form></form>');
		$form.attr('action', 'loginProcess');
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
