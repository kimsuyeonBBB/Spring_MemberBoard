<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="container-fluid">
	<!-- 404 Error Text -->
	<div class="row justify-content-center">
		<div class="col-xl-10 col-lg-12 col-md-9">
			<div class="card o-hidden border-0 shadow-lg my-5">
				<div class="card-body p-0">
					<!-- Nested Row within Card Body -->
					<div class="row">
						<div class="col-lg-7">
							<div class="p-5">
								<h1 id="loginMsg" class="h1 text-gray-900 mb-4">Login 을 할 수 없습니다.</h1>
								<p class="lead text-gray-800 mb-5">
									<h1 id="failureMsg" class="h5 text-gray-900 mb-4">사용자 로그인 정보를 확인해 주세요.!</h1>
								</p>
								<h1 class="h5 text-gray-900 mb-4"><a href="login">&larr; Back to Login Page</a></h1>
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
        console.log(lang);
        if (lang == 'en') {
            $('#loginMsg').html("Failed Login");
            $("#failureMsg").html("Please Check your ID and Password...");
        } else {
        	$('#loginMsg').html("Login 을 할 수 없습니다.");
            $("#failureMsg").html("사용자 로그인 정보를 확인해 주세요.!");
        }
    });
</script>