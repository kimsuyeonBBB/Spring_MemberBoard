define(['common','hiballView', 'cookieUtil',
		'text!main/views/memberList.html',
		'jquery', 'underscore', 'backbone', 'jqueryUi', 'bootstrap', 'popper', 'jqueryCookie', 'jqueryConfirm',
		'jqxCore',
], function(Common, HiballView, Cookie, MemberList ){
	
	var MenuView = HiballView.extend({
		chainEventHandler:function (collection, data){
			var result = data.resultMap.memberInfo;
			console.log(data);
			var template = _.template(MemberList);
			
			var model = new Backbone.Model({
				data:result
			});
				
			$("#content").html(template(model.toJSON()));
			$("#logoutBtn").on("click", Common.logoutHandler);	
			$('.removebtn').on('click',deletebtnEventHandler);

		},
		
		render:function(params, chain){		
			
			var jsonParams = JSON.stringify(configMemberListParams(params));
			console.log(jsonParams);
			var callApi = Common.callApi;
			callApi.fetch('POST',jsonParams, 'JSON', chain);
			
			this.afterRender();
		},
		
		renderLanguage:function(){
			
		}
	});
	
	var configMemberListParams = function(params){
		var paramArr = new Array();
		
		var memberInfo = {
			"serviceName":"codeService","returnName":"memberInfo",
			"subServiceName":"memberInfo",
			"sortColumn":"","parameterSet":{}
		}
		
		paramArr.push(memberInfo);
		
		return paramArr;
	}
	
	function deletebtnEventHandler(evt){
		console.log(evt);
		var target = $(evt.currentTarget);
		var no = target.attr('rel');
		
		if(confirm("삭제하시겠습니까?")){
			var jsonParams = JSON.stringify(configMemberDeleteParams(no));
			var callApi = Common.callApi;
			callApi.fetch('POST',jsonParams,'JSON',successEventHandler);
		} else{
			return false;
		}
	}
	
	function configMemberDeleteParams(no){
		var paramArr = new Array();
		var memberDeleteInfo = {
			"serviceName":"codeService","returnName":"memberDeleteInfo",
			"subServiceName":"memberDeleteInfo",
			"sortColumn":"","parameterSet":{ no:no }
		}
		paramArr.push(memberDeleteInfo);
		
		return paramArr;
	}
	
	function successEventHandler(collection,data){
		console.log(data);

	}

	
	return MenuView;
});