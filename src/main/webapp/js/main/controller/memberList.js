define(['common','hiballView', 'cookieUtil',
		'text!main/views/memberList.html',
		'jquery', 'underscore', 'backbone', 'jqueryUi', 'bootstrap', 'popper', 'jqueryCookie', 'jqueryConfirm',
		'jqxCore',
], function(Common, HiballView, Cookie, MemberList ){
	
	var MenuView = HiballView.extend({
		chainEventHandler:function (collection, data){
			var result = data.resultMap.memberInfo;
			var memCnt = data.resultMap.memberCountInfo[0].membercount;
			var totCnt = Math.ceil(memCnt/10);
			
			var template = _.template(MemberList);
			
			var url_string = window.location.href;
			var url = new URL(url_string);
			var pagenum = url.searchParams.get("pagenum");	
				
			if(pagenum == null){
				pagenum = 1;
			} 	
			
			var model = new Backbone.Model({
				data:result,
				pagenum:parseInt(pagenum),
				totalCount:totCnt
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
		
		var url_string = window.location.href;
		var url = new URL(url_string);
		var pagenum = url.searchParams.get("pagenum");	
				
			if(pagenum == null){
				pagenum = 1;
			} 
			else{
				
			}		
			console.log(pagenum);	
			
		var beginIdx = (pagenum - 1) * 10;
		var listSize = 10;
		
		var memberInfo = {
			"serviceName":"codeService","returnName":"memberInfo",
			"subServiceName":"memberInfo",
			"sortColumn":"","parameterSet":{beginIdx:beginIdx, listSize:listSize}
		}
		
		var memberCountInfo = {
			"serviceName":"codeService","returnName":"memberCountInfo",
			"subServiceName":"memberCountInfo",
			"sortColumn":"","parameterSet":{}
		}
		
		paramArr.push(memberInfo);
		paramArr.push(memberCountInfo);
		
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