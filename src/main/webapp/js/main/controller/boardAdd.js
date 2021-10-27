define(['common','hiballView','cookieUtil',
		'text!main/views/boardAdd.html',
		'jquery','underscore','backbone','jqueryUi','bootstrap','popper','jqueryCookie','jqueryConfirm',
		'jqxCore',
], function(Common,HiballView, Cookie, BoardAdd){
	
	var MenuView = HiballView.extend({
		chainEventHandler:function(collection, data){
			
		},
		
		render:function(params,chain){
			$("#content").html(BoardAdd);
			$("#logoutBtn").on("click",Common.logoutHandler);
			$("#addpost").on('click',addPostBtnEventHandler);
			
			this.afterRender();
		},
		
		renderLanguage:function(){
			
		}
	});
	
	function addPostBtnEventHandler(params){
		var jsonParams = JSON.stringify(configBoardAddParams(params));
		var callApi = Common.callApi;
		callApi.fetch('POST',jsonParams,'JSON',successEventHandler)
	}
	
	function configBoardAddParams(params){
		var paramArr = new Array();
		
		var userName = $("#getUserName").text();
		var title = $('#postTitle').val();
		var story = $('#postStory').val();
		
		var boardAddInfo = {
			"serviceName":"codeService","returnName":"boardAddInfo",
			"subServiceName":"boardAddInfo",
			"sortColumn":"","parameterSet":{name:userName, title:title, story:story}
		}
		paramArr.push(boardAddInfo);
		
		return paramArr;
	}
	
	function successEventHandler(collection,data){
		location.href="boardList";
		
	}
	
	return MenuView;
})