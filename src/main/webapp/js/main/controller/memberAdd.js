define(['common','hiballView','cookieUtil',
		'text!main/views/memberAdd.html',
		'jquery','underscore','backbone','jqueryUi','bootstrap','popper','jqueryCookie','jqueryConfirm',
		'jqxCore',
], function(Common, HiballView, Cookie, MemberAdd){

	var MenuView = HiballView.extend({
		chainEventHandler:function(collection, data){
			var result = data.resultMap.memberAddInfo;
		
		},
		
		render:function(params, chain){
			$("#content").html(MemberAdd);	
			$('#addbtn').on('click',addbtnEventHandler);
			
			this.afterRender();
		},
		
		renderLanguage:function(){
			
		}
	});
	
	function addbtnEventHandler(params){
		var jsonParams = JSON.stringify(configMemberAddParams(params));
		var callApi = Common.callApi;
		callApi.fetch('POST',jsonParams,'JSON',successEventHandler);
	}
	
	function configMemberAddParams(params){
		var paramArr = new Array();
		
		var name = $('#userName').val();
		var email = $('#userEmail').val();
		var id = $('#userId').val();
		var password = $('#userPwd').val();
		
		var memberAddInfo = {
			"serviceName":"codeService","returnName":"memberAddInfo",
			"subServiceName":"memberAddInfo",
			"sortColumn":"","parameterSet":{name:name, email:email, id:id, password:password}
		}
		paramArr.push(memberAddInfo);
		return paramArr;
	}
	
	
	function successEventHandler(collection, data){
		$('#jqxLoader').jqxLoader('close');

		var result = data.execute;
		console.log(result);
		
		if(result == "success"){
			location.href="login.jsp";
		}
		if(result == "failure"){
			alert("사용할 수 없는 정보입니다.");
		}
		
	}
	
	return MenuView;
})