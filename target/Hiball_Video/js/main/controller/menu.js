define(['common', 'hiballView', 'cookieUtil',
        'text!main/views/menu.html',
        'text!main/views/gameList.html',
        'jquery', 'underscore', 'backbone', 'jqueryUi', 'bootstrap', 'popper', 'jqueryCookie', 'jqueryConfirm',
        'jqxCore', 
], function(Common, HiballView, Cookie, MenuFrame, GameList) {
	var localParam = null;
	
	var MenuView = HiballView.extend({
		chainEventHandler:function (collection, data) {
			var result = data.resultMap;
			configContentsSize();
			console.log(data);
			$("#content").html(MenuFrame);
			$("#retrieveGame").on("click", retrievingEventHandler);
			$("#retrieveVideo").on("click", movingEventHandler);
			pagination(0);
			configTeamInfo(result.teamInfo, 2001);
			this.afterRender();
		},
		render:function(params, chain) {
			var paramSet = JSON.stringify(configCriteriaParams(params));
			
			var callApi = Common.callApi;
			callApi.fetch('POST', paramSet, 'JSON', chain);
		}
	});
	
	var configContentsSize = function () {
		var width = window.innerWidth;
		var height = window.innerHeight;
		$("#content").height(height-80);
	}
	
	function configTeamInfo(teamInfos, setTeam) {
		$("#teamId").empty();
		$("#oppTeamId").empty();
		$("#oppTeamId").append("<option value='0' selected>전체</option>");
		
		_.each(teamInfos, function (team){
			if (team.codeId == setTeam) {
				$("#teamId").append("<option value='"+team.codeId+"' selected>"+team.name+"</option>");
			} else {
				$("#teamId").append("<option value='"+team.codeId+"'>"+team.name+"</option>");
			}
			$("#oppTeamId").append("<option value='"+team.codeId+"'>"+team.name+"</option>");
		});
	}
	
	var retrievingEventHandler = function (evt) {
		var params = {
			gameType:$("#gameType").val(),
			season:$("#season").val(),
			fromDate:$("#fromDate").val(),
			toDate:$("#toDate").val(),
			teamId:$("#teamId").val(),
			oppTeamId:$("#oppTeamId").val()
		};
		var jsonParams = JSON.stringify(configRetrieveParams(params));
		
		var callApi = Common.callApi;
		callApi.fetch('POST', jsonParams, 'JSON', successHandler);
	}
	var movingEventHandler = function (evt) {
		var gameInfoId = $("[name='gameList']:checked").val();
		console.log(gameInfoId);
		if (typeof(gameInfoId)!='undefined'&& gameInfoId != '') {
			var params = {'viewId':'videoList', 'gameInfoId':gameInfoId};
			Common.linkingPage("videoList", params);
		} else {
			$.alert({
				title:'경고',
				content:"<span style='font-size:15px;font-weight:bolder;'>경기를 먼저 선택해 주세요.</span>"
			})
		}
	}
	
	var retrievedGames = null;
	var successHandler = function (collection, data) {
		var result = data.resultMap;
		retrievedGames = result.gameInfo;
		
		pagination(0);
		console.log(result.gameInfo);
	}
	var displaySize = 10;
	var pagination = function (idx) {
		$("#gameList").empty();
		
		var startIdx = idx*displaySize;
		var stopIdx = startIdx + displaySize;
		
		var displayRange = _.range(startIdx, stopIdx);
		console.log(displayRange);
		var template = _.template(GameList);
		var model = new Backbone.Model({
			list:retrievedGames,
			range:displayRange
		});
		
		$("#gameList").html(template(model.toJSON()));
	}
	
	var configRetrieveParams = function (params) {
		var paramArr = new Array();
		var gameInfo = {
			"serviceName":"gameScheduleService","returnName":"gameInfo",
			"subServiceName":"gameScheduleByTeam",
			"sortColumn":"",
			"parameterSet":{
				"year":params.season, "gameType":params.gameType, 
				"teamId":params.teamId, 'oppTeamId':params.oppTeamId,
				'fromDate':params.fromDate, 'toDate':params.toDate}
		};
		
		paramArr.push(gameInfo);
		
		return paramArr;
	}
	var configCriteriaParams = function (params) {
		var paramArr = new Array();
		var teamInfo = {
			"serviceName":"codeService","returnName":"teamInfo",
			"subServiceName":"teamCodeInfo",
			"sortColumn":"","parameterSet":{"gameType":4201}
		}
		
		paramArr.push(teamInfo);
		
		return paramArr;
	}
	
	return MenuView;
});