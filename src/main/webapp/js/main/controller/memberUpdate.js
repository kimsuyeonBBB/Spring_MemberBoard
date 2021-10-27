define(['common','hiballView','cookieUtil',
		'text!main/views/memberUpdate.html',
		'jquery', 'underscore', 'backbone', 'jqueryUi', 'bootstrap', 'popper', 'jqueryCookie', 'jqueryConfirm',
		'jqxCore',
], function(Common, HiballView, Cookie, MemberUpdate){
	
	var MenuView = HiballView.extend({
		chainEventHandler:function(collection,data){
			var result = data.resultMap.memberUpdateInfo;
			var template = _.template(MemberUpdate);
			console.log(result);
			
			var model = new Backbone.Model({
				data:result[0]
			});
			
			$("#content").html(template(model.toJSON()));
			$("#logoutBtn").on("click", Common.logoutHandler);	
			$('#updatebtn').on('click',updatebtnEventHandler);
			$('#removebtn').on('click',deletebtnEventHandler);
			
		},
		
		render:function(params,chain){			

			var jsonParams = JSON.stringify(configMemberCallParams(params));
			var callApi = Common.callApi;
			callApi.fetch('POST',jsonParams,'JSON',chain);
			
			this.afterRender();

		},
		
		renderLanguage:function(){
			
		}
		
	});
	
	var configMemberCallParams = function(params){
		var paramArr = new Array();
		
		var url_string = window.location.href;
		var url = new URL(url_string);
		var tab = url.searchParams.get("no");
		console.log(tab);
		
		var memberUpdateInfo = {
			"serviceName":"codeService","returnName":"memberUpdateInfo",
			"subServiceName":"memberUpdateInfo",
			"sortColumn":"","parameterSet":{no:tab}
		}
		
		paramArr.push(memberUpdateInfo);
		
		return paramArr;
	}
	
	function updatebtnEventHandler(params){
		var jsonParams = JSON.stringify(configMemberUpdateParams(params));
		console.log(jsonParams);
		var callApi = Common.callApi;
		callApi.fetch('POST',jsonParams,'JSON',successEventHandler);
	}
	
	function configMemberUpdateParams(params){
		var paramArr = new Array();
		
		var no = $('#updateNo').val();
		var name = $('#updateName').val();
		var email = $('#updateEmail').val();
		var id = $('#updateId').val();
		var password = $('#updatePwd').val();
		
		var memberNewUpdateInfo = {
			"serviceName":"codeService","returnName":"memberNewUpdateInfo",
			"subServiceName":"memberNewUpdateInfo",
			"sortColumn":"","parameterSet":{no:no, name:name, email:email, id:id, password:password}
		}
		paramArr.push(memberNewUpdateInfo);
		
		return paramArr;
	}
	
	function deletebtnEventHandler(evt){
		var target = $(evt.currentTarget);
		var no = target.attr('rel');
		
		if (confirm("삭제하시겠습니까?")) {
			var jsonParams = JSON.stringify(configMemberDeleteParams(no));
			var callApi = Common.callApi;
			callApi.fetch('POST', jsonParams, 'JSON', successEventHandler);
		} else {
			return false;
		}
	}
	
	function configMemberDeleteParams(no){
		var paramArr = new Array();
		var memberDeleteInfo = {
			"serviceName": "codeService", "returnName": "memberDeleteInfo",
			"subServiceName": "memberDeleteInfo",
			"sortColumn": "", "parameterSet": { no: no }
		}
		paramArr.push(memberDeleteInfo);

		return paramArr;
	}
	
	function successEventHandler(collection,data){
		location.href='memberList';
	}
	
	return MenuView;
})