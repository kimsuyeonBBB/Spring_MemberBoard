<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String context = (String)request.getContextPath();
	if (context == null) 
		context = "";
%>
<div class="container">
	<!-- Outer Row -->
	<div class="row justify-content-center">
		<div class="col-xl-10 col-lg-12 col-md-9">
			<div class="card o-hidden border-0 shadow-lg my-5">
				<div class="card-body p-0">
					<!-- Nested Row within Card Body -->
					<div class="row">
						<div class="col-lg-5 d-none d-lg-block bg-login-image"></div>
						<div class="col-lg-7">
							<div class="p-5">
								<div class="text-center">
									<img width="150" src ="<%=context %>/assets/images/main_logo.png" style="margin-bottom:20px;">
									<br>
									<h1 id="systemTit" class="h3 text-gray-900 mb-4">이글스 영상 조회 시스템</h1>
								</div>
								<form class="user">
									<div class="form-group">
										<input type="text" class="form-control form-control-user" id="inputId" placeholder="ID를 입력하세요.">
									</div>
									<div class="form-group">
										<input type="password" class="form-control form-control-user" id="inputPassword" placeholder="Password를 입력하세요.">
									</div>
									<div class="form-group">
										<div class="custom-control custom-checkbox small">
										</div>
									</div>
									<a href="javascript:void(0);" id="submit" class="btn btn-primary btn-user btn-block"> Login </a>
									<hr>
								</form>
								<hr>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>

	</div>
</div>
<script>
    $(document).ready(function () {
    	var lang = localStorage.getItem("language");
    	if (lang == 'en') {
    		$('#systemTit').html("Hanwha In-game Tactic Solution<br/>(H.I.T.S)");
    		$("#inputId").attr("placeholder", "Please Enter your ID...");
    		$("#inputPassword").attr("placeholder", "Please Enter your Pasword...");
    	} else {
    		$('#systemTit').html("이글스 영상 조회 시스템");
            $("#inputId").attr("placeholder", "ID를 입력하세요.");
            $("#inputPassword").attr("placeholder", "Password를 입력하세요.");
    	}
    });
	$(function() {
		$('#inputId').on("click", function(){
			$("#inputId").attr("placeholder", "");
		});
		$('#inputPassword').on("click", function(){
			$("#inputPassword").attr("placeholder", "");
		});
		
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
