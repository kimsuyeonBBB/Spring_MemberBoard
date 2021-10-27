define(['common','hiballView','cookieUtil',
		'text!main/views/boardUpdate.html',
		'jquery', 'underscore', 'backbone', 'jqueryUi', 'bootstrap', 'popper', 'jqueryCookie', 'jqueryConfirm',
		'jqxCore',
], function(Common, HiballView, Cookie, BoardUpdate){
	
	var MenuView = HiballView.extend({
		chainEventHandler:function(collection,data){
			var result = data.resultMap.boardUpdateInfo;
			var template = _.template(BoardUpdate);
			console.log(result);
			
			var model = new Backbone.Model({
				data:result[0]
			});
			
			$("#content").html(template(model.toJSON()));
			$("#logoutBtn").on("click",Common.logoutHandler);
			$("#postUpdateBtn").on("click",updatePostbtnEventHandler);
			$("#removePostBtn").on("click",deletePostEventHandler);
		},
		
		render:function(params,chain){
			var jsonParams = JSON.stringify(configBoardCallParams(params));
			var callApi = Common.callApi;
			callApi.fetch('POST',jsonParams,'JSON',chain);
			
			this.afterRender();
		},
		
		renderLanguage:function(){
			
		}
	});
	
	var configBoardCallParams = function(params){
		var paramArr = new Array();
		
		var url_string = window.location.href;
		var url = new URL(url_string);
		var tab = url.searchParams.get("no");
		console.log(tab);
		
		var boardUpdateInfo = {
			"serviceName":"codeService","returnName":"boardUpdateInfo",
			"subServiceName":"boardUpdateInfo",
			"sortColumn":"","parameterSet":{no:tab}
		}
		paramArr.push(boardUpdateInfo);
		
		return paramArr;
	}
	
	function updatePostbtnEventHandler(params){
		var jsonParams = JSON.stringify(configBoardUpdateParams(params));
		var callApi = Common.callApi;
		callApi.fetch('POST',jsonParams,'JSON',successEventHandler);
	}
	
	function configBoardUpdateParams(params){
		var paramArr = new Array();
		
		var no = $("#postNo").val();
		var title = $("#postTitle").val();
		var story = $("#postStory").val();
		
		var boardNewUpdateInfo = {
			"serviceName":"codeService","returnName":"boardNewUpdateInfo",
			"subServiceName":"boardNewUpdateInfo",
			"sortColumn":"","parameterSet":{no:no, title:title, story:story}
		}
		paramArr.push(boardNewUpdateInfo);
		
		return paramArr;
	}
	
	function deletePostEventHandler(evt){
		var target = $(evt.currentTarget);
		var no = target.attr('rel');
		
		if(confirm("삭제하시겠습니까?")){
			var jsonParams = JSON.stringify(configBoardDeleteParams(no));
			var callApi = Common.callApi;
			callApi.fetch('POST',jsonParams,'JSON',successEventHandler);
		} else{
			return false;
		}
	}
	
	function configBoardDeleteParams(no){
		var paramArr = new Array();
		var boardDeleteInfo = {
			"serviceName":"codeService","returnName":"boardDeleteInfo",
			"subServiceName":"boardDeleteInfo",
			"sortColumn":"","parameterSet":{no:no}
		}
		paramArr.push(boardDeleteInfo);
		
		return paramArr;
	}
	
	function successEventHandler(collection,data){
		location.href = "boardList";
	}
	
	return MenuView;
})