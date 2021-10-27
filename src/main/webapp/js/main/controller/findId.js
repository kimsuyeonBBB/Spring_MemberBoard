define(['common','hiballView','cookieUtil',
		'text!main/views/findId.html',
		'jquery', 'underscore', 'backbone', 'jqueryUi', 'bootstrap', 'popper', 'jqueryCookie', 'jqueryConfirm',
		'jqxCore',
], function(Common, HiballView, Cookie, FindId){
	
	var MenuView = HiballView.extend({
		chainEventHandler:function(collection,data){
			
			
		},
		
		render:function(params,chain){
			$("#content").html(FindId);
			$("#findIdBtn").on("click",findIdBtnEventHandler);
			
			this.afterRender();
		},
		
		renderLanguage:function(){
			
		}
	});
	
	function findIdBtnEventHandler(params){
		var jsonParams = JSON.stringify(configFindIdParams(params));
		var callApi = Common.callApi;
		callApi.fetch('POST',jsonParams,'JSON',successEventHandler);
	}
	
	function configFindIdParams(params){
		var paramArr = new Array();
		
		var name = $("#findId_name").val();
		var email = $("#findId_email").val();
		
		var findIdInfo = {
			"serviceName":"codeService","returnName":"findIdInfo",
			"subServiceName":"findIdInfo",
			"sortColumn":"","parameterSet":{name:name, email:email}
		}
		paramArr.push(findIdInfo);
		
		return paramArr;
	}
	
	function successEventHandler(collection,data){
		var result = data.resultMap.findIdInfo;
		var length = result.length;
		console.log(result);
		if(length == 1){
			alert("아이디 찾기 성공! \n사용자 ID는 '" + result[0].id + "' 입니다.");
			location.href = "login.jsp";
		} 
		else if(length == 0){
			alert("입력한 내용을 다시 확인해주세요");
		}
		
	}
	
	return MenuView;
})