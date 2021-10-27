define(['common','hiballView','cookieUtil',
		'text!main/views/findPw.html',
		'jquery', 'underscore', 'backbone', 'jqueryUi', 'bootstrap', 'popper', 'jqueryCookie', 'jqueryConfirm',
		'jqxCore',
], function(Common, HiballView, Cookie, FindPw){
	
	var MenuView = HiballView.extend({
		chainEventHandler:function(collection,data){
			
			
		},
		
		render:function(params,chain){
			$("#content").html(FindPw);
			$("#findPwBtn").on("click",findPwBtnEventHandler);
			
			this.afterRender();
		},
		
		renderLanguage:function(){
			
		}
	});
	
	function findPwBtnEventHandler(params){
		var jsonParams = JSON.stringify(configFindPwParams(params));
		var callApi = Common.callApi;
		callApi.fetch('POST',jsonParams,'JSON',successEventHandler);
	}
	
	function configFindPwParams(params){
		var paramArr = new Array();
		
		var name = $("#findPw_name").val();
		var email = $("#findPw_email").val();
		var id = $("#findPw_id").val();
		
		var findPwInfo = {
			"serviceName":"codeService","returnName":"findPwInfo",
			"subServiceName":"findPwInfo",
			"sortColumn":"","parameterSet":{name:name, email:email, id:id}
		}
		paramArr.push(findPwInfo);
		
		return paramArr;
	}
	
	function successEventHandler(collection,data){
		var result = data.resultMap.findPwInfo;
		var length = result.length;
		if(length == 1){
			alert("비밀번호 찾기 성공! \n사용자 비밀번호는 '" + result[0].password + "' 입니다.");
			location.href = "login.jsp";
		}
		else if(length == 0){
			alert("입력한 내용을 다시 확인해주세요");
		}
	}
	
	return MenuView;
})