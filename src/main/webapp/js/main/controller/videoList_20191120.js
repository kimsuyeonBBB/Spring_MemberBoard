define(['common', 'hiballView', 'cookieUtil',
        'text!main/views/videoList.html',
        'text!main/views/playList.html',
        'text!main/views/criteria.html',
        'jquery', 'underscore', 'backbone', 'jqueryUi', 'bootstrap', 'popper', 'jqueryCookie', 'jqueryConfirm'
], function(Common, HiballView, Cookie, VideoList, PlayList, Criteria) {
	var listByType = null;
	var playList = null;
	var playingList = null;
	var playerInfo = {
		'position':null, 'teamId':null, 'oppTeamId':null, 'teams':null, 'players':null
	};
	
	var confirmedCriteria =  {
		'preSet':'default'
	};
	
	var configedPlayer = null;
	var videoCategory = null;
	var initParams = null;
	
	var MenuView = HiballView.extend({
		chainEventHandler: function (collection, data) {
			var result = data.resultMap;
			
			$("#content").html(VideoList);
			$(window).resize(windowResizingHandler);
			
			this.initModule(result);
			this.afterRender();
		},
		initModule :function (data) {
			var videoInfo = data.videoInfo;
			var gameVideoRecord = data.gameVideoRecord;
			
			videoCategory = _.groupBy(videoInfo, function(info){
				return info.gameInfoId;
			});
			listByType = _.groupBy(gameVideoRecord, function (record){
				return record.gameInfoId +'_'+ record.videoType;
			});
			$("#logoutBtn").on("click", Common.logoutHandler);
			configVideoCate(videoCategory);
			configEventHandler();
			
			// Grouped videos by type
			// 호출된 데이터는 flat 하지만 내부는 날짜>팀>비디오타입으로 분류할 수 있는 형태 따라 날짜>팀&상대팀>비디오타입 기준으로 재정렬한다.
			configedPlayer = document.getElementById("videoPlayer");
			configedPlayer.muted = true;
			
			// Default video type is BROADCAST
			var selectedGameId = $("#videoCate").val();
			var selectedType = '';
			_.each($("[name='videoTypes']"), function (elem){
				if ($(elem).hasClass("selected")) 
					selectedType = $(elem).val();
			});
			
			var key = selectedGameId+'_'+selectedType;
			playList = listByType[key];
			
			configDefaultCriteria();
			configCriteriaInfos(playList);
			
			displayVideo();
			resizeDone();
		},
		render:function(params, chain) {
			initParams = params;
			
			var paramSet = JSON.stringify(configRetrieveParams(params));
			var callApi = Common.callApi;
			callApi.fetch('POST', paramSet, 'JSON', chain);
		}
	});
	
	var configCriteriaInfos = function (displayList) {
		var basisPosi = initParams.position;
		var teamId = initParams.teamId;
		var oppTeamId = initParams.oppTeamId;
		
		playerInfo.position = basisPosi;
		playerInfo.teamId = teamId;
		playerInfo.oppTeamId = oppTeamId;
		
		var pitchers = new Array();
		var batters = new Array();
		var teams = _.indexBy(displayList, function(record) { return record.batterTeamId; });
		var groupedByTeam = _.groupBy(displayList, function (record){ return record.batterTeamId; });
		
		var players = new Object();
		var perPosition = null;
		var idxPitchers = null;
		var idxBatters = null;
		
		_.each (groupedByTeam, function(teamRecords, teamId){
			perPosition = new Object();
			idxPitchers = _.indexBy(teamRecords, function (record) {return record.pitcherId});
			idxBatters = _.indexBy(teamRecords, function (record) {return record.batterId});
			
			perPosition['oppPlayers'] = idxPitchers;
			perPosition['players'] = idxBatters;
			players[teamId] = perPosition;
		});

		var playerSet = null;
		if (playerInfo.position == 'batter') {
			playerInfo.teamId = teams[teamId].batterTeamId;
			playerInfo.teamName = teams[teamId].batterTeamName;
			playerInfo.oppTeamName = teams[teamId].pitcherTeamName;
			playerInfo.oppTeamId = teams[teamId].pitcherTeamId;
		} else {
			playerInfo.teamId = teams[teamId].pitcherTeamId;
			playerInfo.teamName = teams[teamId].pitcherTeamName;
			playerInfo.oppTeamName = teams[teamId].batterTeamName;
			playerInfo.oppTeamId = teams[teamId].batterTeamId;
		}
		
		playerSet = players[playerInfo.teamId];
		playerInfo.players = playerSet['players'];
		playerInfo.oppPlayers = playerSet['oppPlayers'];
	}
	
	var configRetrieveParams = function (params) {
		var paramArr = new Array();
		var videoRecordParam = {
			"serviceName":"gameScheduleService","returnName":"gameVideoRecord",
			"subServiceName":"gameVideoRecord",
			"sortColumn":"",
			"parameterSet":{
				"gameInfoIds":params.gameInfoId
			}
		};
		var videoInfoParam = {
			"serviceName":"gameScheduleService","returnName":"videoInfo",
			"subServiceName":"videoInfo",
			"sortColumn":"",
			"parameterSet":{
				"gameInfoIds":params.gameInfoId
			}
		};
		
		paramArr.push(videoRecordParam);
		paramArr.push(videoInfoParam);
		
		return paramArr;
	}
	
	function configEventHandler() {
		$("#videoCate").on("change", changeCategoryHandler);
		$("#returnCriteria").on("click", returnCriteriaHandler);
		$("#returnList").on("click", returnCriteriaHandler);
		$("#openCriteria").on('click', openCriteriaHandler);
		$("[name='playingType']").on('click', changePlayingTypeHandler);
	}

	
	var configVideoCate = function (categories) {
		$("#videoCate").empty();
		$("#playVideoBtn").empty();
		var idx = 0;
		//var options = "<option value='' selected>전체</option>";
		var options = '';
		
		_.each(categories, function (infos, gameId) {
			var firstInfo = infos[0];
			var name = firstInfo.gameDay+"_"+firstInfo.awayTeamName+"_"+firstInfo.homeTeamName;
			
			if (idx == 0) {
				options += "<option value='"+gameId+"' selected>"+name+"</option>";
				configVideoTypeBtn(infos);
			} else {
				options += "<option value='"+gameId+"'>"+name+"</option>";
			}
			idx++;
		});
		$("#videoCate").append(options);
	}
	
	var configVideoTypeBtn = function (types) {
		var btnElem = "";
		$("[name='videoTypes']").off("click");
		var idx = 0;
		_.each(types, function (info){
			var title = '중계';
			var videoType = info.videoType;
			var dataKey = info.gameInfoId+'_'+info.videoType;
			if ((info.videoType).indexOf('cam') != -1) {
				title = videoType.toUpperCase();
			}
			
			if (idx == 0) {
				btnElem +="<button name='videoTypes' class='btn btn-default btn-videoType selected' rel='"+dataKey+"' value='"+videoType+"'>"+title+'</button>';
			} else {
				btnElem +="<button name='videoTypes' class='btn btn-default btn-videoType' rel='"+dataKey+"' value='"+videoType+"'>"+title+'</button>';
			}
			idx++;
		});
		$("#playVideoBtn").html(btnElem);
		$("[name='videoTypes']").on("click", selectVideoTypeHandler);
	}
	
	var configContentsSize = function () {
		var width = window.innerWidth;
		var height = window.innerHeight;
		$("#content").height(height-80);
	}
	
	function configDefaultCriteria() {
		var position = initParams.position;
		var checkBase = initParams.isCheckbase === 'true';
		
		confirmedCriteria.position = initParams.position;
		
		if (initParams.batterIds != null && initParams.batterIds != 'null' && initParams.batterIds != '') {
			confirmedCriteria.batterId = (initParams.batterIds).split(',');
		}
		if (initParams.pitcherIds != null && initParams.pitcherIds != 'null' && initParams.pitcherIds != '') {
			confirmedCriteria.pitcherId = (initParams.pitcherIds).split(',');
		}
		
		if (position == 'batter') {
			confirmedCriteria.batterTeamId = initParams.teamId;
			if (initParams.oppTeamId != null && initParams.oppTeamId != '' && initParams.oppTeamId != '0') {
				confirmedCriteria.pitcherTeamId = initParams.oppTeamId;
			}
			
		} else if (position == 'pitcher'){
			confirmedCriteria.pitcherTeamId = initParams.teamId;
			if (initParams.oppTeamId != null && initParams.oppTeamId != '' && initParams.oppTeamId != '0') {
				confirmedCriteria.batterTeamId = initParams.oppTeamId;
			}
		}
		
		// 선수가 설정 되어 있지 않은 경우 가장 마지막 투구만 출력 되도록 한다.
		if (!checkBase && 
				(initParams.batterIds == null || initParams.batterIds == 'null' || initParams.batterIds == '') &&
				(initParams.pitcherIds == null || initParams.pitcherIds == 'null' || initParams.pitcherIds == '')) {
			confirmedCriteria.lastPitchYn = ['Y'];
		}
		// 견제 설정 시
		if (checkBase) {
			confirmedCriteria.checkBase = ['1','2','3'];
		}
		
		return confirmedCriteria;
	}
	
	var resizingTimerId = null;
	
	var windowResizingHandler = function (evt) {
		clearTimeout( resizingTimerId );
		resizingTimerId = setTimeout( resizeDone, 300);
	}
	
	function displayPlayingList() {
		var videoTypeObjs = $("[name='videoTypes']");
		var selectedType = '';
		
		_.each(videoTypeObjs, function(elem){
			if($(elem).hasClass('selected')){
				selectedType = $(elem).attr("rel");
			}
		});
		playList = listByType[selectedType];
		configCriteriaInfos(playList);
		displayVideo();
		resizeDone();
	}
	
	function resizeDone() {
		configContentsSize();
		var width = window.innerWidth;
		var height = window.innerHeight;
		
		$("#videoPlayer").css('margin', '0 auto');
		var screenHeight = $(".screen_wrap")[0].clientWidth*9/16;
		$("#videoPlayer").attr("width", '100%');
		$("#videoPlayer").attr("height", screenHeight);
		
		if (height > 1200) {
			$("#videoClipList").height(window.innerHeight-720);
		} else if (height >= 1024) {
			$("#videoClipList").height(window.innerHeight-580);
		} else  if (height >= 992) {
			$("#videoClipList").height(window.innerHeight-120);
		} else if (height >= 800) {
			$("#videoClipList").height(window.innerHeight-370);
		} else if (height >= 736) {
			$("#videoClipList").height(window.innerHeight-390);
		} else if (height >= 640) {
			$("#videoClipList").height(window.innerHeight-370);
		} else if (height >= 568) {
			$("#videoClipList").height(window.innerHeight-320);
		} else {
			$("#videoClipList").height(window.innerHeight-120);
		}
	}
	
	var displayVideo = function () {
		var loadingVideoPath = '';
		// Grouped list by INNING
		if (confirmedCriteria != null) {
			loadingVideoPath = playList[0].videoPath;
			//playingList = _.filter(playList, function (list){ return list.inning == confirmedCriteria.inning;});
			var filtered = playList;
			_.each(confirmedCriteria, function (item, key) {
				var filter = {'key':key, 'item':item};
				filtered = filteringData (filtered, filter);
			});
			playingList = filtered;
		} else {
			var listByInn = _.groupBy(playList, function(record){
				loadingVideoPath = record.videoPath;
				return record.inning;
			});
			
			playingList = listByInn['1'];
		}
		videoLoading(configedPlayer, loadingVideoPath);
		
		setClipList();
	}
	
	var setClipList = function () {
		$("#clipList").empty();
		var model = new Backbone.Model({
			playList:playingList
		});
		
		var template = _.template(PlayList);
		$("[name='videoClips']").off("click");
		$("#clipList").html(template(model.toJSON()));
		$("[name='videoClips']").on("click", selectClipEventHandler);
		
		resizeDone();
	}

	var duration = 7;
	var frontTime = 2;
	var currentPlayingId = null;
	
	var videoLoading = function (player, videoPath) {
		console.log(videoPath);
		videoPath = videoPath.replace('http://movie', 'http://m.movie');
		$("#clip_source").attr("src", videoPath);
		
		player.load();
	}

	function playerPause() {
		if (currentPlayingId != null) {
			clearTimeout(currentPlayingId);
		}
		configedPlayer.pause();
	}
	
	function playVideo(player, startTime, currentDataKey) {
		var playingType = 'single';
		var types = $("[name='playingType']");
		_.each(types, function (type){
			if ($(type).hasClass('selected')) {
				playingType = $(type).val();
			}
		});
		
		if (currentPlayingId != null)
			clearTimeout(currentPlayingId);
		
		player.currentTime = startTime-frontTime;
		player.play();
		
		currentPlayingId = setTimeout(function(){
			player.pause()
			if (playingType == 'sequence') {
				playNextClipHandler(currentDataKey);
			} else if (playingType == 'repeat') {
				playVideo(configedPlayer, startTime, currentDataKey);
			}
		}, duration*1000);
	}
	
	function isValidVideo(player) {
		var mp4Codecs ='video/mp4;codcs=avc1.42E01E, mp4a.40.2'
		var mess = player.canPlayType(mp4Codecs);
		$.alert({title:'Notice', content:'isValid? '+mess});
	}
	
	var changeCategoryHandler = function (evt) {
		var selected = $("#videoCate option:selected");
		var val = selected.val();
		var videoInfos = videoCategory[val];
		
		configVideoTypeBtn(videoInfos);
		displayPlayingList();
		playerPause();
	}
	
	var selectClipEventHandler = function (evt) {
		var target = $(evt.currentTarget);
		var selectedPitch = target.attr("rel");
		var currentKey = '';
		
		if (!target.hasClass('disable')) {
			$("[name='videoClips']").removeClass('active');
			target.addClass('active');
			
			var findPitch = _.find(playingList, function (pitch){
				currentKey = pitch.inning+''+pitch.inningTb+''+pitch.batInningTurn+''+pitch.batPitchSeq;
				return currentKey == selectedPitch;
			});
			
			var startTime = findPitch.pitchTime - findPitch.taggingGapTime;
			
			playVideo(configedPlayer, startTime, currentKey);
		}
	}
	
	var playNextClipHandler = function (currentKey) {
		$("[name='videoClips']").removeClass('active');
		var indexing = _.indexBy(playingList, function (pitch){
			return pitch.inning+''+pitch.inningTb+''+pitch.batInningTurn+''+pitch.batPitchSeq;
		});
		
		var keys = _.keys(indexing);
		var currentKeyIdx = _.indexOf(keys, currentKey);
		
		var nextKeyIdx = currentKeyIdx +1;
		var lastIdx = _.size(playingList)-1;
		
		if (nextKeyIdx > lastIdx) {
			var categories = $("#videoCate")[0];
			var cateSize = categories.length;
			var selected = $("#videoCate option:selected")[0];
			var selectedIdx = selected.index;
			
			if (selectedIdx < cateSize) {
				var nextCate = $(categories[selectedIdx+1]);
				var val = nextCate.val();
				if (typeof(val) != 'undefined') {
					nextCate.attr('selected', 'selected');
					var videoInfos = videoCategory[val];
					configVideoTypeBtn(videoInfos);
					displayPlayingList();
					
					var findPitch = playingList[0];
					if (typeof(findPitch) != 'undefined' && findPitch != null) {
						var nextKey = findPitch.inning+''+findPitch.inningTb+''+findPitch.batInningTurn+''+findPitch.batPitchSeq;
						var startTime = findPitch.pitchTime - findPitch.taggingGapTime;
						
						highlightListHandler(nextKey);
						
						playVideo(configedPlayer, startTime, nextKey);
					} else {
						configVideoCate(videoCategory);
					}
				} else {
					configVideoCate(videoCategory);
					var selected = $("#videoCate option:selected");
					var val = selected.val();
					var videoInfos = videoCategory[val];
					configVideoTypeBtn(videoInfos);
					displayPlayingList();
					
					playerPause();
					$.alert({
						title:'알림',
						content:'리스트 재생이 완료 되었습니다.'
					});
				}
			} else {
				playerPause();
				$.alert({
					title:'알림',
					content:'리스트 재생이 완료 되었습니다.'
				});
			}
			
		} else {
			var nextKey = keys[nextKeyIdx];
			var findPitch = _.find(playingList, function (pitch){
				return nextKey == pitch.inning+''+pitch.inningTb+''+pitch.batInningTurn+''+pitch.batPitchSeq;
			});
			
			var startTime = findPitch.pitchTime - findPitch.taggingGapTime;
			
			highlightListHandler(nextKey);
			playVideo(configedPlayer, startTime, nextKey);
		}
	}
	
	var highlightListHandler = function (nextKey) {
		var height = 0;
		var list = $("[name='videoClips']");
		
		for (var i=0; i<list.length; i++) {
			var elem = list[i];
			var key = $(elem).attr('rel');
			if (key == nextKey) {
				$(elem).addClass('active');
				$("#clipListWrap").animate({scrollTop:height}, 400);
				break;
			}
			height += $(elem)[0].clientHeight;
		}
	}
	
	var selectVideoTypeHandler = function (evt) {
		var target = $(evt.currentTarget);
		var selectedType = target.val();
		$("[name='videoTypes']").removeClass("selected");
		target.addClass('selected');
		
		displayPlayingList();
	}
	
	var openCriteriaHandler = function (evt) {
		var criteria = _.template(Criteria);
		
		var model = new Backbone.Model({
			playerInfo:playerInfo,
			confirmed:confirmedCriteria
		});
		
		var view = criteria(model.toJSON());
		
		$.confirm({
			title:"조건 설정",
			content:view,
			theme:'supervan',
			boxWidth:'90%',
			useBootstrap:false,
			onOpen:function () {
				$("[name='preSet']").on("click", preSetHandler);
				setPreCriteria(confirmedCriteria);
			},
			onClose:function () {
				$("[name='preSet']").off("click");
			},
			buttons: {
				confirm:{
					text:'확인',
					action:confirmCriteriaHandler
				},
				cancel:{
					text:'취소',
					action:null
				}
			}
		});
	}
	var setCriteria = null;
	var confirmCriteriaHandler = function (evt) {
		var preSets = $("[name='preSet']");
		var preSetVal = 'default';
		_.each(preSets, function (preSet){
			var preSetObj = $(preSet);
			
			if (preSetObj.hasClass("active")) {
				preSetVal = preSetObj.val();
			}
		});
		var lastPitchYn = ['Y', 'N'];
		if (setCriteria!= null) {
			lastPitchYn = setCriteria.lastPitchYn;
		}
		
		var gatherCriteria = {
			'preSet':preSetVal,
			'lastPitchYn':lastPitchYn,
			'batterTeamId':$("#batterTeamId").val(),
			'pitcherTeamId':$("#pitcherTeamId").val(),
			'inning':$("#inning").val(),
			'pitcherId':$("#pitcherId").val(),
			'batterId':$("#batterId").val(),
			'ballCode':$("#ballCode").val(),
			'beforeRunnerState':$("#beforeRunnerState").val(),
			'ballCount':$("#ballCount").val(),
			'hitTypeCode':$("#hitTypeCode").val(),
			'hitResultCode':$("#hitResultCode").val(),
			'checkBase':$("#checkBase").val()
		}
		
		var filtered = playList;
		_.each(gatherCriteria, function (item, key) {
			var filter = {'key':key, 'item':item};
			filtered = filteringData (filtered, filter);
		});
		
		confirmedCriteria = gatherCriteria;
		playingList = filtered;
		playerPause();
		setClipList();
	}
	
	var filteringData = function (data, filter) {
		var key = filter.key;
		var item = filter.item;
		var filteredData = null;
		
		if (key != 'preSet' && key !='position' && (typeof(item) != 'undefined' && item != null && item != '' && item.length > 0)) {
			filteredData = _.filter(data, function (target){
				if (item!= null && typeof(item) == 'object') {
					if (item.length != 0) {
						if (key == 'ballCount') {
							return _.contains(item, target['beforeBallCount']+'-'+target['beforeStrikeCount']);
						} else {
							return _.contains(item, target[key]);
						}
					} else {
						return true;
					}
				} else if (typeof(item) == 'string') {
					if (item != -1) {
						return item == target[key];
					} else {
						return true;
					}
				} else {
					return true;
				}
			});
		} else {
			filteredData = data;
		}
		
		return filteredData
	}
	
	var preSetHandler = function (evt) {
		var target = $(evt.currentTarget);
		var targetId = target.val();
		setCriteria = {
			'preSet':'whole',
			'lastPitchYn':['Y', 'N'],
			'batterTeamId':'-1',
			'pitcherTeamId':'-1',
			'inning':[],
			'pitcherId':[],
			'batterId':[],
			'ballCode':[],
			'beforeRunnerState':[],
			'ballCount':[],
			'hitTypeCode':[],
			'hitResultCode':[],
			'checkBase':'[]'
		}
		
		if (targetId == 'default') {
			setCriteria = configDefaultCriteria();
			setCriteria['preSet'] = targetId;
		} else if (targetId == 'hit') {
			setCriteria['preSet'] = targetId;
			setCriteria['hitResultCode'] = ['6604', '6607', '6610', '6613', '6629'];
		} else if (targetId == 'batting') {
			setCriteria['preSet'] = targetId;
			setCriteria['hitTypeCode'] = ['6302', '6303', '6306'];
		} else if (targetId == 'miss-swing') {
			setCriteria['preSet'] = targetId;
			setCriteria['hitTypeCode'] = ['6301', '6303'];
		}
		
		
		var criteriaElems = $("[name='criteria-elem']");
		_.each(criteriaElems, function (elem){
			var elemObj = $(elem);
			var elemId = elemObj.attr("id");
			elemObj.val(setCriteria[elemId]);
		});
		
		setPreCriteria(setCriteria);
	}
	
	var setPreCriteria = function (criteria) {
		$("[name='preSet']").removeClass("active");
		var preSet = $("[name='preSet']");
		var preSetVal = criteria.preSet;
		
		_.each(preSet, function (set){
			var preSetObj = $(set);
			if (preSetObj.val() == preSetVal) {
				preSetObj.addClass('active');
			}
			
		});
	}
	
	var changePlayingTypeHandler = function (evt) {
		$("[name='playingType']").removeClass("selected");
		$(evt.currentTarget).addClass('selected');
		playerPause();
	}
	
	var returnCriteriaHandler = function (evt) {
		//window.history.back();
		var target = $(evt.currentTarget);
		var id = target.attr("id");
		
		delete initParams.viewId;
		delete initParams.gameInfoId;
		initParams.from = 'video';
		if (id == 'returnList') {
			Common.linkingPage("gameList", initParams);
		} else if (id == 'returnCriteria') {
			Common.linkingPage("gameCriteria", initParams);
		}
	}
	
	function afterLoadingHandler(evt) {
		var media = evt.target;
	}
	
	return MenuView;
});