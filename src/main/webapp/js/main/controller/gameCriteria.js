define(['common', 'hiballView', 'cookieUtil',
        'text!main/views/gameCriteria.html',
        'jquery', 'underscore', 'backbone', 'jqueryUi', 'bootstrap', 'popper', 'jqueryCookie', 'jqueryConfirm',
        'jqxCore', 
], function(Common, HiballView, Cookie, SelectMode) {
	var localParam = null;
	var players = null;
	var teams = null;
	
	var MenuView = HiballView.extend({
		chainEventHandler:function (collection, data) {
			var result = data.resultMap;
			console.log(result);
			teams = result.teamInfo;
			players = result.playerInfo;
			
			configTeamInfo(teams, 2001);
			configPlayerInfo();
			configPlayerTitle();
			
			configEventHandler();
			configCriteriaVal(localParams);
			this.afterRender();
		},
		render:function(params, chain) {
			console.log(params);
			console.log(chain);
			$("#content").html(SelectMode);
			$("#logoutBtn").on("click", Common.logoutHandler);
			
			configDefaultSet(params);

			var paramSet = JSON.stringify(configCriteriaParams(params));
			console.log(configCriteriaParams(params));
			var callApi = Common.callApi;
			callApi.fetch('POST', paramSet, 'JSON', chain);
		},
		renderLanguage:function () {
			configPlayerTitle();
			configTeamInfo(teams, 2001);
			configPlayerInfo();
		}
	});
	
	function configDefaultSet (params) {
		if (_.isEmpty(params)) {
			params.year = 2021;
			params.gameType = 4201;
			params.position = 'batter';
			//params.videoType = 'broadCast';
			params.location = 'kr';
		}
		
		localParams = params;
		
		$("#season").val(params.year);
		$("#gameType").val(params.gameType);
		$("#selectedPosi").val(params.position);
		//$("#videoType").val(params.videoType);
	}
	
	function configEventHandler () {
		$("#season").on("change", changeTypeHandler);
		$("#gameType").on("change", changeTypeHandler);
		$("#selectedPosi").on("change", changePosiHandler);
		$("#teamId").on("change", changeTeamHandler);
		$("#oppTeamId").on("change", changeTeamHandler);
		$("#latestGames").on("change", retrivingTypeHandler);
		$("#checkbase").on("change", retrivingTypeHandler);
		$("#retrieveGame").on("click", retrievingEventHandler);
		$("#resetCriteria").on('click', resetEventHandler);
		$("#openCriteria").on("click", openDetailCriteriaHandler);
	}
	
	function configCriteriaVal(params) {
		var posi = params.position;
		
		$("#teamId").val(params.teamId);
		$("#oppTeamId").val(params.oppTeamId);
		$("#location").find('option[value='+params.location+']').prop('selected', 'selected');
		
		if (params.isLatestGames === 'true' ? true : false)
			$("#latestGames").attr("checked", true);
		if (params.isCheckbase === 'true' ? true : false)
			$("#checkbase").attr("checked", true);
		
		if (posi == 'batter') {
			multiDefaultSet(params.batterIds, 'selectedPlayers');
			multiDefaultSet(params.pitcherIds, 'selectedOppPlayers');
		} else if (posi == 'pitcher') {
			multiDefaultSet(params.pitcherIds, 'selectedPlayers');
			multiDefaultSet(params.batterIds, 'selectedOppPlayers');
		}
		
		
		multiDefaultSet(params.innings, 'inning');
		multiDefaultSet(params.ballCodes, 'ballCode');
		multiDefaultSet(params.runnerStatus, 'beforeRunnerState');
		multiDefaultSet(params.ballCounts, 'ballCount');
		multiDefaultSet(params.hitTypes, 'hitTypeCode');
		multiDefaultSet(params.hitResults, 'hitResultCode');
	}
	
	function multiDefaultSet(val, elemName) {
		$("#"+elemName).val('');
		
		if (val != '' && val != null) {
			var arr = val.split(',');
			_.each(arr, function (value) {
				$("#"+elemName).find('option[value='+value+']').prop("selected", "selected");
			})
		}
	}
	
	function configPlayerTitle() {
		var posi = $("#selectedPosi").val();
		
		var useLang = $("#useLang").val();
		console.log(useLang);
		var pitcher = Cookie.getLanguage(useLang, 'pitcher');
		var hitter = Cookie.getLanguage(useLang, 'hitter');
		
		if (posi == 'pitcher') {
			$("#playerTit").html(pitcher);
			$("#oppPlayerTit").html(hitter);
		} else {
			$("#playerTit").html(hitter);
			$("#oppPlayerTit").html(pitcher);
		}
	}
	
	var configCriteriaParams = function (params) {
		var paramArr = new Array();
		var teamInfo = {
			"serviceName":"codeService","returnName":"teamInfo",
			"subServiceName":"teamInfo",
			"sortColumn":"","parameterSet":{"gameType":params.gameType}
		}
		var playerInfo = {
			"serviceName":"codeService","returnName":"playerInfo",
			"subServiceName":"playerInfo",
			"sortColumn":"","parameterSet":{"year":params.year,"gameType":params.gameType}
		}
		console.log(teamInfo);
		console.log(playerInfo);
		paramArr.push(playerInfo);
		paramArr.push(teamInfo);
		
		return paramArr;
	}

	var retrievePlayerInfo = function () {
		var season = $("#season").val();
		var gameType = $("#gameType").val();
		
		var paramObj = new Object();
		paramObj.year = season;
		paramObj.gameType = gameType;
		
		var jsonParams = JSON.stringify(configCriteriaParams(paramObj));
		
		var callApi = Common.callApi;
		callApi.fetch('POST', jsonParams, 'JSON', successTeamHandler);
	}
	
	function configTeamInfo(teamInfos, setTeam) {
		$("#teamId").empty();
		var useLang = $("#useLang").val();
		
		_.each(teamInfos, function (team){
			var teamName = team.teamName;
			if (useLang == 'en') 
				teamName = team.engTeamName;
			
			if (team.teamId == setTeam) {
				$("#teamId").append("<option value='"+team.teamId+"' selected>"+teamName+"</option>");
			} else {
				$("#teamId").append("<option value='"+team.teamId+"'>"+teamName+"</option>");
			}
		});
		
		configOppTeam(teamInfos, setTeam);
	}

	function configPlayerInfo(params) {
		var posi = $("#selectedPosi").val();
		var teamId = $("#teamId").val();
		
		$("#selectedPlayers").empty();
		
		var displayPlayers = _.filter(players, function (player) { return player.position == posi && player.teamId == teamId});
		displayPlayers = _.sortBy(displayPlayers, function (player) {return Number(player.backnumber);});
		var useLang = $("#useLang").val();
		
		var options = "";
		_.each(displayPlayers, function (player){
			var name = player.playerName;
			
			if (useLang == 'en') {
				name = player.lastName+","+player.firstName;
			}
			options += '<option value="'+player.playerId+'">'+'#'+player.backnumber+' '+name+'</option>';
		});
		
		$("#selectedPlayers").append(options);
		
		configOppPlayerInfo(teamId, "team");
	}
	
	function configOppTeam(teamInfos, teamId) {
		$("#oppTeamId").empty();
		var useLang = $("#useLang").val();
		if (useLang == 'en') {			
			$("#oppTeamId").append("<option value='' selected>Whole</option>");
		} else {
			$("#oppTeamId").append("<option value='' selected>전체</option>");
		}
		_.each(teamInfos, function (team){
			var teamName = team.teamName;
			if (useLang == 'en') 
				teamName = team.engTeamName;
			
			if (team.teamId != teamId)
				$("#oppTeamId").append("<option value='"+team.teamId+"'>"+teamName+"</option>");
		});
		
		
		$("#selectedOppPlayers").empty();
		if (useLang == 'en')
			$("#selectedOppPlayers").append("<option value='' selected>Whole</option>");
		else 
			$("#selectedOppPlayers").append("<option value='' selected>전체</option>");
	}
	
	function configOppPlayerInfo(teamId, flag) {
		var posi = $("#selectedPosi").val();
		var useLang = $("#useLang").val();
		
		var displayPlayers = null;
		$("#selectedOppPlayers").empty();
		if(flag == 'team') {
			var oppTeamId = $("#oppTeamId").val();
			if (oppTeamId != '') {
				displayPlayers = _.filter(players, function (player){return player.position != posi && player.teamId == oppTeamId;});
			} else {
				displayPlayers = _.filter(players, function (player) {return player.position != posi && player.teamId != teamId;});
			}
		} else {
			var oppTeamId = $("#teamId").val();
			displayPlayers = _.filter(players, function (player){
				if (typeof(teamId) != 'undefined' && teamId != null && teamId !== '')
					return player.position != posi && player.teamId == teamId;
				else 
					return player.position != posi && player.teamId != oppTeamId; 
			});
		}
		
		var options = "";
		_.each(displayPlayers, function (player){
			var name = player.playerName;
			
			if (useLang == 'en') {
				name = player.lastName+","+player.firstName;
			}
			options += '<option value="'+player.playerId+'">'+'#'+player.backnumber+" "+name+'</option>';
		});
		
		$("#selectedOppPlayers").append(options);
	}
	
	//Handler
	var successTeamHandler = function (collection, data) {
		var result = data.resultMap;
		players = result.playerInfo;
		teams = result.teamInfo;
		
		configTeamInfo(teams, 2001);
		configPlayerInfo();
	}
	
	var changeTypeHandler = function (evt) {
		retrievePlayerInfo();
	}
	
	var changePosiHandler = function (evt) {
		configPlayerTitle();
		configPlayerInfo();
	}
	
	var changeTeamHandler = function (evt) {
		var target = $(evt.currentTarget);
		var type = target.attr('rel');
		var teamId = target.val();
		
		if (type == 'mine') {
			configTeamInfo(teams, teamId);
			configPlayerInfo();
		} else if (type == 'opp') {
			configOppPlayerInfo(teamId);
		}
	}
	
	var retrivingTypeHandler = function (evt) {
		var isLatest = $("#latestGames").is(":checked");
		var isCheckbase = $("#checkbase").is(":checked");
		var tit = "경기조회";
		var useLang = Cookie.getLocalStorage("language");
		if (useLang == 'en') tit = "Search"
		
		if (isLatest || isCheckbase) {
			tit = "영상조회";
			if (useLang == 'en') tit = "Search Clip"
			if(isCheckbase && isLatest) {
				$("#latestGames").prop("checked", false);
			}
		}
		
		$("#retrieveGame").html(tit);
	}
	
	var retrievingEventHandler = function () {
		var params = new Object();
		var playerIds = $("#selectedPlayers").val();
		var oppPlayerIds = $("#selectedOppPlayers").val();
		var position = $("#selectedPosi").val();
		
		if (playerIds == null) {
			playerIds = ''; 
		}
		if (oppPlayerIds == null) {
			oppPlayerIds = '';
		}
		params.year = $("#season").val();
		params.gameType = $("#gameType").val();
		params.position = position;
		params.teamId = $("#teamId").val();
		params.oppTeamId = $("#oppTeamId").val();
		//params.videoType = $("#videoType").val();
		var innings = $("#inning").val();
		params.innings = innings ? innings : '';
		params.ballCodes = $("#ballCode").val() ? $("#ballCode").val(): '';
		params.runnerStatus = $("#beforeRunnerState").val() ? $("#beforeRunnerState").val() : '';
		params.ballCounts = $("#ballCount").val() ? $("#ballCount").val() : '';
		params.hitTypes = $("#hitTypeCode").val() ? $("#hitTypeCode").val() : '';
		params.hitResults = $("#hitResultCode").val()?$("#hitResultCode").val(): '';
		params.checkbases = $("#checkBase").val()? $("#checkBase").val(): '';
		params.location = $("#location").val();
		
		if (position == 'batter') {
			params.batterIds = playerIds;
			params.pitcherIds = oppPlayerIds;
		} else if (position == 'pitcher') {
			params.pitcherIds = playerIds;
			params.batterIds = oppPlayerIds;
		}
		
		params.isLatestGames = $("#latestGames").is(":checked");
		var checkbase = $("#checkbase").is(":checked");
		
		params.isCheckbase = checkbase;
		
		if (checkbase && (params.position == 'batter' || params.pitcherIds == '')) {
			let useLang = Cookie.getLocalStorage('language');
			if (useLang == 'en') {
				$.alert({
					title:'Warning',
					content:"Choosing <Pickoffs>, " +
					"<span class='empasis'>Change 'Search By' to 'Pitcher' And Then " +
					"<span class='empasis'>must to choose pitcher</span>"
				});
			} else {				
				$.alert({
					title:'경고',
					content:"견제 선택 시" +
					"<span class='empasis'>'선수 유형'을 '투수'로 변경 </span>하고 " +
					"<span class='empasis'>투수를 선택</span>해야 합니다."
				});
			}
			return;
		}
		//console.log(params);
		Common.linkingPage('gameList', params);
	}
	
	var openDetailCriteriaHandler = function (evt) {
		var target = $(evt.currentTarget);
		var area = $('#detailCriteriaArea');
		var areaState = area.css('display');
		target.empty();
		
		var useLang = $("#useLang").val();
		var btnTit = Cookie.getLanguage(useLang, "detailSearch")
		
		if (areaState == 'none') {
			area.show('slow');
			target.addClass('active');
			target.html(btnTit+' &xutri;')
		} else {
			area.hide('slow');
			target.removeClass('active');
			target.html(btnTit+' &xdtri;')
		}
	}
	
	var resetEventHandler = function (evt) {
		localParams = new Object();
		
		localParams.year = 2021;
		localParams.gameType = 4201;
		localParams.position = 'batter';
		//localParams.videoType = 'broadCast';
		
		$("#season").val(localParams.year);
		$("#gameType").val(localParams.gameType);
		$("#selectedPosi").val(localParams.position);
		//$("#videoType").val(localParams.videoType);
		$("#teamId").val(2001);
		$("#oppTeamId").val('');
		
		configPlayerInfo();
		configPlayerTitle();
		
		configCriteriaVal(localParams);
	}
	
	return MenuView;
});