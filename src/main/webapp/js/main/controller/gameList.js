define(['common', 'hiballView', 'cookieUtil',
        'text!main/views/gameList.html',
        'text!main/views/list-template.html',
        'jquery', 'underscore', 'backbone', 'jqueryUi', 'bootstrap', 'popper', 'jqueryCookie', 'jqueryConfirm',
        'jqxCore', 
], function(Common, HiballView, Cookie, GameList, listTemplate, detailCriteria) {
	var localParams = null;
	var page = 0;
	var listSize = 30;
	
	var MenuView = HiballView.extend({
		chainEventHandler:function (collection, data) {
			$("#content").html(GameList);
			var result = data.resultMap;
			
			$(window).resize(windowResizingHandler);
			configEventHandler();
			retrievedGames = result.gameInfo;
			pagination(0);
			
			$("#playingGame").html("(0/6) 선택보기");
			
			var isCheckbase = localParams.isCheckbase ==='true';
			var isLatestGames = localParams.isLatestGames === 'true';
			var fromView = localParams.from;
			
			if ((typeof(fromView) == 'undefined' || fromView !='video') && (isCheckbase || isLatestGames)) {
				skipSelectingGames();
			}
			this.afterRender();
		},
		render:function(params, chain) {
			$("#logoutBtn").on("click", Common.logoutHandler);
			localParams = params;
			var jsonParams = JSON.stringify(configRetrieveParams(params));
			var callApi = Common.callApi;
			callApi.fetch('POST', jsonParams, 'JSON', chain);
		},
		renderLanguage: function () {
			let useLang = $("#useLang").val();
			pagination(0);
		}
	});
	
	function skipSelectingGames() {
		var isValid = true;
		var limit = 5;
		var size = _.size(retrievedGames)-1;
		
		if (size < limit) {
			limit = size; 
		}
	
		var i = 0;
		var selectedGames = new Array();
		var selectedVideoTypes = new Array();
		
		while(isValid) {
			if (i <= limit) {
				var videoTypes = retrievedGames[i].videoType;
				var gameInfoId = retrievedGames[i].gameInfoId;
				var videoType = '';
				// 게임에 포함된 영상 유형 중 가장 앞에 있는 영상유형을 포함 시킴
				if (videoTypes != null) {
					if (videoTypes.indexOf('/') > 0) 
						videoType = videoTypes.substring(0, videoTypes.indexOf('/'));
					else
						videoType = videoTypes;
					i++;
				}
				selectedGames.push(gameInfoId+'_'+videoType);
			} else {
				isValid = false;
			}
		}
		
		if (selectedGames.length > 0) {
			$("#selectedGames").val(selectedGames.toString());
			$("#videoTypes").val(selectedVideoTypes.toString());
			playingGameHandler();
		}
	}
	
	function configEventHandler () {
		$("#playingGame").on("click", playingGameHandler);
		$("#sorting").on('change', changeSortingHandler);
		$("#returnCriteria").on('click', returnCriteriaHandler);
	}
	
	function resizeDone() {
		configContentsSize();
		var width = window.innerWidth;
		var height = window.innerHeight;
		
		$("#gameListWrap").height(height-160);
	}
	
	var configContentsSize = function () {
		var width = window.innerWidth;
		var height = window.innerHeight;
		$("#content").height(height-80);
	}
	
	var retrievedGames = null;
	
	var pagination = function (idx) {
		$("[name='gameItems']").off('click');
		$("[name='retrieveVideo']").off('click');
		$("#gameList").empty();
		
		var sorting = $("#sorting").val();
		
		retrievedGames = _.sortBy(retrievedGames, function (game) {
			var date = new Date(game.gameDay);
			var videoTypes = game.videoType;
			
			if (typeof(videoTypes) == 'string') {
				if (videoTypes != null && videoTypes != '') {
					game.videoType = videoTypes.split('/');
				} else {
					game.videoType = null;
				}
			}
			
			if (sorting == 'latest') {
				date *= -1;
			}
			
			return date;
		});
		
		var template = _.template(listTemplate);
		var useLang = Cookie.getLocalStorage("language");
		console.log(retrievedGames);
		var model = new Backbone.Model({
			list:retrievedGames,
			sorting:sorting,
			useLang:useLang
		});
		
		$("#gameList").html(template(model.toJSON()));
		$("[name='gameItems']").on('click', selectGameHandler);
		$("[name='selectedVideoTypes']").on("change", changeVideoTypeHandler);
		
		renderLanguage();
		resizeDone();
	}
	
	var renderLanguage = function () {
		Cookie.renderLanguage();
	}
	
	var resizingTimerId = null;
	
	var windowResizingHandler = function (evt) {
		clearTimeout( resizingTimerId );
		resizingTimerId = setTimeout(resizeDone, 300);
		resizeCriteriaHeight();
	}
	
	var selectGameHandler = function (evt) {
		var target = $(evt.currentTarget);
		var targetName = target.attr("name");
		var targets = $("[name='"+targetName+"']");
		
		if (target.hasClass('select')) {
			target.removeClass('select');
		} else {
			target.addClass('select');
		}
		
		var selectedItems = _.filter(targets, function (item) {return $(item).hasClass('select');})
		
		if (selectedItems.length > 720) {
			$.alert({
				title:'알림',
				content:"최대 6 경기까지 선택 가능합니다."
			});
			target.removeClass('select');
		} else {
			var gameArr = new Array();
			var videoTypes = new Array();
			_.each (selectedItems, function (item){
				var gameInfoId = $(item).attr('rel');
				var videoType = $("#videoType_"+gameInfoId).val();
				
				gameArr.push(gameInfoId+'_'+videoType);
			});
			
			$("#selectedGames").val(gameArr.toString());
			//$("#playingGame").html('('+gameArr.length+"/6) 선택보기");
			let useLang = Cookie.getLocalStorage("language");
			if (useLang == 'en') {				
				$("#playingGame").html("Watch "+gameArr.length+" Games");
			} else {				
				$("#playingGame").html(gameArr.length+" 경기 선택보기");
			}
		}
	}
	
	var changeVideoTypeHandler = function (evt) {
		var targets = $("[name='gameItems']");
		var selectedItems = _.filter(targets, function (item) {return $(item).hasClass('select');})
		var gameArr = new Array();
		var videoTypes = new Array();
		_.each (selectedItems, function (item){
			var gameInfoId = $(item).attr('rel');
			var videoType = $("#videoType_"+gameInfoId).val();
			
			gameArr.push(gameInfoId+'_'+videoType);
		});
		
		$("#selectedGames").val(gameArr.toString());
	}
	
	var playingGameHandler = function (evt) {
		var selectedGameIds = $("#selectedGames").val();
		var position = localParams.position;
		var batterIds = localParams.batterIds;
		var pitcherIds = localParams.pitcherIds;		
		var teamId = localParams.teamId;
		var oppTeamId = localParams.oppTeamId;
		var season = localParams.year;
		var gameType = localParams.gameType;
		var checkbase = localParams.isCheckbase;
		var isLatestGame = localParams.isLatestGames
		var innings = localParams.innings;
		var ballCodes = localParams.ballCodes;
		var runnerStatus = localParams.runnerStatus;
		var ballCounts = localParams.ballCounts;
		var hitTypes = localParams.hitTypes;
		var hitResults = localParams.hitResults;
		var checkbases = localParams.checkbases;
		var location = localParams.location;
		
		if (selectedGameIds != '') {
			var params = {'year':season, 'gameType':gameType, 'gameInfoIds':selectedGameIds, 
					'batterIds':batterIds, 'pitcherIds':pitcherIds, 'position':position, 
					'teamId':teamId, 'oppTeamId':oppTeamId, 'season':season, 'isCheckbase':checkbase,
					'isLatestGames':isLatestGame,
					'innings':innings, 'ballCodes':ballCodes, 'runnerStatus':runnerStatus, 'ballCounts':ballCounts,
					'hitTypes':hitTypes, 'hitResults':hitResults, 'checkbases':checkbases, 'location':location};
			//console.log(params);
			Common.linkingPage("videoList", params);
		} else {
			$.alert({
				title:'알림',
				content:"경기를 먼저 선택해 주십시오."
			});
		}
	}
	
	var changeSortingHandler = function (evt) {
		var sorting = $(evt.currentTarget).val();
		pagination(0);
	}
	
	var configRetrieveParams = function (params) {
		var paramArr = new Array();
		var position = params.position;
		var playerIds = '';
		var oppPlayerIds = '';
		
		var beginIdx = page*listSize;
		
		if (position == 'batter') {
			playerIds = params.batterIds;
			oppPlayerIds = params.pitcherIds;
		} else if (position == 'pitcher') {
			playerIds = params.pitcherIds;
			oppPlayerIds = params.batterIds;
		}
		console.log(params);
		var gameInfo = {
			"serviceName":"gameScheduleService","returnName":"gameInfo",
			"subServiceName":"gameScheduleByTeam",
			"sortColumn":"",
			"parameterSet":{
				"season":params.year, "gameType":params.gameType, 
				"teamId":params.teamId, 'oppTeamId':params.oppTeamId, 
				'playerIds':playerIds, 'oppPlayerIds':oppPlayerIds,
				'checkbase':params.isCheckbase, 'position':position,
				'videoType':params.videoType,
				'innings':params.innings, 'ballCodes':params.ballCodes, 
				'runnerStatus':params.runnerStatus, 'ballCounts':params.ballCounts,
				'hitTypes':params.hitTypes, 'hitResults':params.hitResults, 
				'checkbases':params.checkbases, 'beginIdx':beginIdx, 'listSize':listSize}
		};
		console.log(gameInfo);
		paramArr.push(gameInfo);
		
		return paramArr;
	}

	var displayPlayers = null;
	var players = null;
	var openType = '';
	
	function resetGameList() {
		$("#gameList").empty();
		
		var template = _.template(listTemplate);
		var model = new Backbone.Model({
			list:null,
			range:0
		});
		
		$("#gameList").html(template(model.toJSON()));
		resizeDone();
	}
	
	function resizeCriteriaHeight(evt) {
		var width = window.innerWidth;
		var height = window.innerHeight;
		
		if (height >= 576) {
			$(".jconfirm-content-pane").css({'height':'350px', 'max-height':'350px'});
		} else {
			height = height/2;
			$(".jconfirm-title-c").css({'line-height':'2px'});
			$(".jconfirm-content-pane").css({'height':height+'px', 'max-height':height+'px'});
		}
	}
	
	var returnCriteriaHandler = function (evt) {
		Common.linkingPage('gameCriteria', localParams);
	}
	
	return MenuView;
});