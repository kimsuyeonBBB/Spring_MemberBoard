define(['common', 'hiballView', 'cookieUtil',
        'text!main/views/videoList.html',
        'text!main/views/playList.html',
        'text!main/views/criteria.html',
        'jquery', 'underscore', 'backbone', 'jqueryUi', 'bootstrap', 'popper', 'jqueryCookie', 'jqueryConfirm'
], function(Common, HiballView, Cookie, VideoList, PlayList, Criteria) {
	var listByType = null;
	var playList = null;
	
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
			var gameVideoRecord = data.gameVideoRecord;
			listByType = gameVideoRecord;
			$("#logoutBtn").on("click", Common.logoutHandler);
			configEventHandler();
			
			// Grouped videos by type
			// 호출된 데이터는 flat 하지만 내부는 날짜>팀>비디오타입으로 분류할 수 있는 형태 따라 날짜>팀&상대팀>비디오타입 기준으로 재정렬한다.
			configedPlayer = document.getElementById("videoPlayer");
			configedPlayer.muted = true;
			//playList = listByType[key];
			playList = listByType;
			
			displayVideo();
			resizeDone();
		},
		render:function(params, chain) {
			initParams = params;
			console.log(params);
			var paramSet = JSON.stringify(configRetrieveParams(params));
			var callApi = Common.callApi;
			callApi.fetch('POST', paramSet, 'JSON', chain);
		}
	});
	
	var configRetrieveParams = function (params) {
		var paramArr = new Array();
		var videoRecordParam = {
			"serviceName":"gameScheduleService","returnName":"gameVideoRecord",
			"subServiceName":"gameVideoRecord",
			"sortColumn":"",
			"parameterSet":{
				"gameInfoIds":params.gameInfoId, 'videoType':params.videoType,
				'position':params.poisition, 'checkbase':params.isCheckbase,
				'batterIds':params.batterIds, 'pitcherIds':params.pitcherIds,
				'innings':params.innings, 'ballCodes':params.ballCodes, 
				'runnerStatus':params.runnerStatus, 'ballCounts':params.ballCounts,
				'hitTypes':params.hitTypes, 'hitResults':params.hitResults, 'checkbases':params.checkbases
			}
		};
		
		paramArr.push(videoRecordParam);
		
		return paramArr;
	}
	
	function configEventHandler() {
		$("#returnCriteria").on("click", returnCriteriaHandler);
		$("#returnList").on("click", returnCriteriaHandler);
		$("[name='playingType']").on('click', changePlayingTypeHandler);
	}
	
	var configContentsSize = function () {
		var width = window.innerWidth;
		var height = window.innerHeight;
		$("#content").height(height-80);
	}
	
	var resizingTimerId = null;
	
	var windowResizingHandler = function (evt) {
		clearTimeout( resizingTimerId );
		resizingTimerId = setTimeout( resizeDone, 300);
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
	var loadedVideo = '';
	var displayVideo = function () {
		loadedVideo = playList[0].videoPath;
		videoLoading(configedPlayer, loadedVideo);
		
		setClipList();
	}
	
	var setClipList = function () {
		$("#clipList").empty();
		var model = new Backbone.Model({
			playList:playList
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
	
	var selectClipEventHandler = function (evt) {
		var target = $(evt.currentTarget);
		var selectedPitch = target.attr("rel");
		var currentKey = '';
		
		if (!target.hasClass('disable')) {
			$("[name='videoClips']").removeClass('active');
			target.addClass('active');
			
			var findPitch = _.find(playList, function (pitch){
				currentKey = pitch.gameInfoId+''+pitch.inning+''+pitch.inningTb+''+pitch.batInningTurn+''+pitch.batPitchSeq;
				return currentKey == selectedPitch;
			});
			
			var selectedVideoPath = findPitch.videoPath;
			
			if (loadedVideo != selectedVideoPath) {
				loadedVideo = selectedVideoPath;
				videoLoading(configedPlayer, loadedVideo);
			}
			
			var startTime = findPitch.pitchTime - findPitch.taggingGapTime;
			
			playVideo(configedPlayer, startTime, currentKey);
		}
	}
	
	var playNextClipHandler = function (currentKey) {
		$("[name='videoClips']").removeClass('active');
		var indexing = _.indexBy(playList, function (pitch){
			return pitch.gameInfoId+''+pitch.inning+''+pitch.inningTb+''+pitch.batInningTurn+''+pitch.batPitchSeq;
		});
		
		var keys = _.keys(indexing);
		var currentKeyIdx = _.indexOf(keys, currentKey);
		
		var nextKeyIdx = currentKeyIdx +1;
		var lastIdx = _.size(playList)-1;
		if (nextKeyIdx > lastIdx) {
			playerPause();
			$.alert({
				title:'알림',
				content:'리스트 재생이 완료 되었습니다.'
			});
		} else {
			var nextKey = keys[nextKeyIdx];
			var findPitch = _.find(playList, function (pitch){
				return nextKey == pitch.gameInfoId+''+pitch.inning+''+pitch.inningTb+''+pitch.batInningTurn+''+pitch.batPitchSeq;
			});
			
			var selectedVideoPath = findPitch.videoPath;
			
			if (loadedVideo != selectedVideoPath) {
				loadedVideo = selectedVideoPath;
				videoLoading(configedPlayer, loadedVideo);
			}
			
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