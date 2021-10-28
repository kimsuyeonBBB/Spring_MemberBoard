define(['common','hiballView','cookieUtil',
		'text!main/views/boardList.html',
		'jquery', 'underscore', 'backbone', 'jqueryUi', 'bootstrap', 'popper', 'jqueryCookie', 'jqueryConfirm',
		'jqxCore',
], function(Common, HiballView, Cookie, BoardList){
	
	var MenuView = HiballView.extend({
		chainEventHandler:function(collection,data){
			var result = data.resultMap.boardListInfo;
			var brdCnt = data.resultMap.boardCountInfo[0].boardcount;
			var totCnt = Math.ceil(brdCnt/10);
			
			var template = _.template(BoardList);
			
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
			
			console.log(data);
			
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
		
		var url_string = window.location.href;
		var url = new URL(url_string);
		var pagenum = url.searchParams.get("pagenum");
		
		if(pagenum == null){
			pagenum = 1;
		} 
		else{
				
		}
		
		var beginIdx = (pagenum-1) * 10;
		var listSize = 10;
		
		var userName = $("#getUserName").text();

		var boardListInfo = {
			"serviceName":"codeService","returnName":"boardListInfo",
			"subServiceName":"boardListInfo",
			"sortColumn":"","parameterSet":{name:userName, beginIdx:beginIdx, listSize:listSize}
		}
		
		var boardCountInfo = {
			"serviceName":"codeService","returnName":"boardCountInfo",
			"subServiceName":"boardCountInfo",
			"sortColumn":"","parameterSet":{name:userName}
		}
		
		paramArr.push(boardListInfo);
		paramArr.push(boardCountInfo);
		
		return paramArr;
	}
	return MenuView;
});