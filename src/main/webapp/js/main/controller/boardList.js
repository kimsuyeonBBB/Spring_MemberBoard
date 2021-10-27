define(['common','hiballView','cookieUtil',
		'text!main/views/boardList.html',
		'jquery', 'underscore', 'backbone', 'jqueryUi', 'bootstrap', 'popper', 'jqueryCookie', 'jqueryConfirm',
		'jqxCore',
], function(Common, HiballView, Cookie, BoardList){
	
	var MenuView = HiballView.extend({
		chainEventHandler:function(collection,data){
			var result = data.resultMap.boardListInfo;
			var template = _.template(BoardList);
			
			var model = new Backbone.Model({
				data:result
			});
			
			$("#content").html(template(model.toJSON()));
			$("#logoutBtn").on("click",Common.logoutHandler);
			
		},
		
		render:function(params,chain){
			var jsonParams = JSON.stringify(configBoardListParams(params));
			var callApi = Common.callApi;
			callApi.fetch('POST',jsonParams,'JSON',chain);
			
			this.afterRender();
			
		},
		
		renderLanguage:function(){
			
		}
	});
	
	var configBoardListParams = function(params){
		var paramArr = new Array();
		
		var userName = $("#getUserName").text();

		var boardListInfo = {
			"serviceName":"codeService","returnName":"boardListInfo",
			"subServiceName":"boardListInfo",
			"sortColumn":"","parameterSet":{name:userName}
		}
		
		paramArr.push(boardListInfo);
		
		return paramArr;
	}
	return MenuView;
});