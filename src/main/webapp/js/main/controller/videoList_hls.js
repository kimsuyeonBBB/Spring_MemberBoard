define(['common', 'hiballView', 'cookieUtil', 'videojs',
        'text!main/views/videoList_hls.html',
        'text!main/views/playList.html',
        'text!main/views/criteria.html',
        'require', 'jquery', 'underscore', 'backbone', 'jqueryUi', 'bootstrap', 'popper', 'jqueryCookie', 'jqueryConfirm'
], function(Common, HiballView, Cookie, video, VideoList, PlayList, Criteria) {
	var playList = null;
	var location = '';
	var page = 0;
	var listSize = 50;
	
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
			
			var gameVideoRecord = result.gameVideoRecord;
			var records = 0; 
			records = _.size(gameVideoRecord);
			if (records != 0) {
				this.initModule(result);
			} else {
				configEventHandler();
				var useLang = Cookie.getLocalStorage("language");
				if (useLang == 'en') {
					$.alert({
						title:"Warning",
						content:"Check tagging on clips!"
					});
				} else {					
					$.alert({
						title:"영상태깅경보",
						content:"영상 태깅 정보를 확인 하여 주십시오!"
					});
				}
			}
			this.afterRender();
		},
		initModule :function (data) {
			var gameVideoRecord = data.gameVideoRecord;
			playList = gameVideoRecord;
			
			$("#logoutBtn").on("click", Common.logoutHandler);
			configEventHandler();
			
			// Grouped videos by type
			// 호출된 데이터는 flat 하지만 내부는 날짜>팀>비디오타입으로 분류할 수 있는 형태 따라 날짜>팀&상대팀>비디오타입 기준으로 재정렬한다.
			configedPlayer = video("videoPlayer", {
				html5: {
					hls: {
						overrideNative: true
					},
					nativeAudioTracks: false,
					nativeVideoTracks: false
				},
				'controls':false,
				'autoplay':false,
				'preload':true,
				'muted':true,
				'poster':'assets/images/main_logo.png',
				'loop':false,
				'liveui':true,
				'width':340
			});
			
			$("#screenController").on("click", screenHandler);

			displayVideo();
			resizeDone();
		},
		render:function(params, chain) {
			initParams = params;
			location = params.location;
			
			var paramSet = JSON.stringify(configRetrieveParams(params));
			var callApi = Common.callApi;
			callApi.fetch('POST', paramSet, 'JSON', chain);
		},
		renderLanguage: function () {
			setClipList();
		}
	});
	
	var configRetrieveParams = function (params) {
		var paramArr = new Array();
		var beginIdx = (page*listSize);
		
		var videoRecordParam = {
			"serviceName":"gameScheduleService","returnName":"gameVideoRecord",
			"subServiceName":"gameVideoRecord",
			"sortColumn":"",
			"parameterSet":{
				"gameInfoIds":params.gameInfoIds, 'videoType':params.videoType,
				'position':params.poisition, 'checkbase':params.isCheckbase,
				'batterIds':params.batterIds, 'pitcherIds':params.pitcherIds,
				'innings':params.innings, 'ballCodes':params.ballCodes, 
				'runnerStatus':params.runnerStatus, 'ballCounts':params.ballCounts,
				'hitTypes':params.hitTypes, 'hitResults':params.hitResults, 'checkbases':params.checkbases,
				'beginIdx':beginIdx, 'listSize':listSize
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
		
		//$("#videoPlayer").css('margin', '0 auto');
		var screenHeight = $(".screen_wrap")[0].clientWidth*9/16;
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
	var loadedGoogleVideo = '';
	var displayVideo = function () {
		loadedVideo = playList[0].videoPath;
		loadedGoogleVideo = playList[0].googleVideoPath;
		videoLoading(configedPlayer, loadedVideo, loadedGoogleVideo);
		
		setClipList();
	}
	
	var videoLoading = function (player, videoPath, googleVideoPath) {
		if (location ==='us') {
			videoPath = videoPath.replace('http://movie', 'http://usmovie');
		}
		var season = initParams.season;
		if (season == '2020') {
			videoPath = videoPath.replace('http://movie', 'http://m.movie');
		}
		
		
		//현재 경로 : http://m.movie.kbostats.net/kbo/broadCast/2020/20201030_10_02.mp4
		//변경 경로 : http://hanwha_media.storage.googleapis.com/mobile/kbo/broadCast/2020/20200216_30038_30037/20200216_30038_30037.m3u8
		// Test 영상
		//videoPath = 'http://m.movie.kbostat.net/kbo/broadCast/2020/20200323_30037_30038_1920.mp4';
		//videoPath = 'http://m.movie.kbostat.net/kbo/broadCast/2020/20200323_30037_30038_1920.mp4/mp4hls/index.m3u8';
//		var mp4VideoPath = videoPath.replace("/mp4hls/index.m3u8", '');
//		console.log(mp4VideoPath);
//		player.src({type:"video/mp4", src:mp4VideoPath});
		player.src({type:"application/x-mpegURL", src:googleVideoPath});
		player.on('error', function (evt){
			var mp4VideoPath = videoPath.replace("/mp4hls/index.m3u8", '');
			player.src({type:"video/mp4", src:mp4VideoPath});
		});
	}
	
	var setClipList = function () {
		$("#clipListWrap").off("scroll");
		$("#clipList").empty();
		var useLang = Cookie.getLocalStorage("language");
		if (typeof(useLang) == 'undefined' || useLang == null) {
			useLang = 'kr';
		}
		
		var model = new Backbone.Model({
			playList:playList,
			useLang:useLang,
			cookie:Cookie
		});
		
		var template = _.template(PlayList);
		$("[name='videoClips']").off("click");
		
		$("#clipList").html(template(model.toJSON()));
		$("[name='videoClips']").on("click", selectClipEventHandler);
		$("#clipListWrap").on("scroll", listScrollHandler);
		Cookie.renderLanguage();
		
		if (currentIdx != 0) {
			highlightHandler(currentIdx);
		}
		
		resizeDone();
		$("#clipListWrap").scrollTop(currentScrollTop);
	}
	
	var highlightHandler = function (idx) {
		var height = 0;
		var list = $("[name='videoClips']");
		
		for (var i=0; i<list.length; i++) {
			var elem = list[i];
			var key = $(elem).attr('rel');
			if (key == idx) {
				$(elem).addClass('active');
				currentIdx = idx;
				break;
			}
			height += $(elem)[0].clientHeight;
		}
	}
	
	var currentScrollTop = 0;
	var isEndList = false;
	var listScrollHandler = function(evt){
		var target = $(evt.currentTarget);

		currentScrollTop = $(this).scrollTop(); //스크롤바의 상단위치
        var scrollH = $(this).height(); //스크롤바를 갖는 div의 높이
        var contentH = $("#clipContents").height(); //문서 전체 내용을 갖는 div의 높이
       
        if(!isEndList && currentScrollTop + scrollH +1 >= contentH) { // 스크롤바가 아래 쪽에 위치할 때
            //console.log("End of scroll "+playList.length);
            page += 1;
            var paramSet = JSON.stringify(configRetrieveParams(initParams));
			//Common.getAjax("/apiMultiCall.do", paramSet, appendListHandler);
			var callApi = Common.callApi;
			callApi.fetch('POST', paramSet, 'JSON', appendListHandler);
        }
	}
	
	var appendListHandler = function (collection, data) {
		var result = data.resultMap;
		var gameVideoRecord = result.gameVideoRecord;
		var retrievedSize = gameVideoRecord.length;
		
		if (retrievedSize < listSize) {
			isEndList = true;
		}
		
		if (playList != null) {
			playList = $.merge(playList, gameVideoRecord);
		} else {
			playList = gameVideoRecord;
		}
		
		setClipList();
	}
	
	var duration = 7;
	var frontTime = 2;
	var currentPlayingId = null;
	var currentPlayingTime = 0;
	var dataKey = '';
	
	function playerPause() {
		if (currentPlayingId != null) {
			clearTimeout(currentPlayingId);
		}
		configedPlayer.pause();
	}
	
	var screenHandler = function (evt){
		if (dataKey != '') {
			var playingType = getPlayingType();
			var isPlaying = !configedPlayer.paused();
			$(evt.currentTarget).animate({opacity:0.5}, 500);
			$('#playIcon').animate({opacity:0.8}, 500);
			
			if (isPlaying) {
				$("#playIcon").removeClass('fa-play');
				$("#playIcon").addClass('fa-pause');
				playerPause();
			} else {
				$("#playIcon").removeClass('fa-pause');
				$("#playIcon").addClass('fa-play');
				//console.log(currentPlayingTime);
				configedPlayer.currentTime(currentPlayingTime);
				configedPlayer.play();
				$('#playIcon').animate({opacity:0}, 500);
				$(evt.currentTarget).animate({opacity:0}, 500);
				if (playingType != 'justplay') {
					currentPlayingId = setTimeout(timerHandler, duration*1000);
				}
			}
		} else {
			$.alert({
				title:'알림',
				content:'투구를 먼저 선택하여 영상을 재생해 주세요.!'
			});
		}
	};
	
	var timerHandler = function () {
		var playingType = getPlayingType();
		
		configedPlayer.pause();
		//console.log(currentPlayingTime);
		if (playingType == 'sequence') {
			playNextClipHandler();
		} else if (playingType == 'repeat') {
			configedPlayer.currentTime(currentPlayingTime);
			configedPlayer.play();
			currentPlayingId = setTimeout(timerHandler, duration*1000);
		}
	}
	
	var getPlayingType = function() {
		var playingType = '';
		var types = $("[name='playingType']");
		_.each(types, function (type){
			if ($(type).hasClass('selected')) {
				playingType = $(type).val();
			}
		});
		
		return playingType;
	}
	
	function isValidVideo(player) {
		var mp4Codecs ='video/mp4;codcs=avc1.42E01E, mp4a.40.2'
		var mess = player.canPlayType(mp4Codecs);
		$.alert({title:'Notice', content:'isValid? '+mess});
	}
	
	var selectClipEventHandler = function (evt) {
		var target = $(evt.currentTarget);
		var selectedPitch = target.attr("rel");
		var currentKey = dataKey;
		
		var isPausing = configedPlayer.paused();
		
		if (isPausing) {
			$("#playIcon").removeClass('fa-pause');
			$("#playIcon").addClass('fa-play');
			//console.log(currentPlayingTime);
			$('#playIcon').animate({opacity:0}, 500);
			$('#screenController').animate({opacity:0}, 500);
		}
		
		if (!target.hasClass('disable')) {
			dataKey = selectedPitch;
			$("[name='videoClips']").removeClass('active');
//			target.addClass('active');
			highlightListHandler();
			
			var findPitch = _.find(playList, function (pitch){
				currentKey = pitch.gameInfoId+''+pitch.inning+''+pitch.inningTb+''+pitch.batInningTurn+''+pitch.batPitchSeq;
				return currentKey == selectedPitch;
			});
			
			var selectedVideoPath = findPitch.videoPath;
			var selectedGoogleVideoPath = findPitch.googleVideoPath;
			
			var startTime = findPitch.pitchTime - findPitch.taggingGapTime;
			
			if (loadedVideo != selectedVideoPath) {
				loadedVideo = selectedVideoPath;
				videoLoading(configedPlayer, loadedVideo, selectedGoogleVideoPath);
				
				configedPlayer.on("loadeddata", function (evt) {
					playClipHandler(configedPlayer, startTime, findPitch);
				});
			} else {
				playClipHandler(configedPlayer, startTime, findPitch);
			} 
			
		}
	}

	var playClipHandler = function (player, startTime, pitchInfo) {
		var playingType = getPlayingType();
		
		if (currentPlayingId != null)
			clearTimeout(currentPlayingId);
		
		//상황 별 frontTime & playing durationTime 설정
		var checkBase = pitchInfo.checkBase;
		var hitResultCode = pitchInfo.hitResultCode;
		var beforeOutCount = pitchInfo.beforeOutCount;
		var beforeRunnerState = pitchInfo.beforeRunnerState;
		var outCount = pitchInfo.outCount;
		
		var forStaff = $("#forStaff").is(":checked");
		
		// Default frontTime = 2 & duration = 7
		frontTime = 2;
		duration = 7;
		
		if (typeof(checkBase) != 'undefined' && (checkBase != '0' || checkBase != 0 )) {
			//견제 일경우
			//console.log("#### When checkBase");
			frontTime = 5;
			duration = 10;
		}
		
		if (typeof (hitResultCode) != 'undefined' && (hitResultCode != '0' || hitResultCode != 0)) {
			//console.log("##### When with hitResult");
			// 타격 결과가 있는 경우 frontTime = 2 & duration = 10
			frontTime = 2;
			duration = 10;
			if (beforeOutCount != outCount) {
				//console.log("##### When with changing outCount");
				//타격 결과가 있고 아웃카운트에 변동이 있을 경우 frontTime = 2 & duration = 17
				duration = 17;
			}
		}
		//console.log("##### forStaff: "+forStaff);
		//console.log("##### beforeRunnerState: "+ beforeRunnerState);
		if(forStaff && (beforeRunnerState != 0 || beforeRunnerState!= '0')) {
			// 코치용 재생시간 설정
			//console.log("##### Config timer for staff");
			frontTime = 7;
			duration = 17;
		}
		
		//console.log("##### frontTime: "+frontTime+"/ Duration Time: "+duration);
		currentPlayingTime = startTime - frontTime;
		player.currentTime(currentPlayingTime);
		player.play();
		
		if (playingType != 'justplay')
			currentPlayingId = setTimeout(timerHandler, duration*1000);
	}
	
	var playNextClipHandler = function () {
		var currentKey = dataKey;
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
			var selectedGoogleVideoPath = findPitch.googleVideoPath;
			var startTime = findPitch.pitchTime - findPitch.taggingGapTime;
			dataKey = nextKey;
			
			if (loadedVideo != selectedVideoPath) {
				loadedVideo = selectedVideoPath;
				videoLoading(configedPlayer, loadedVideo, selectedGoogleVideoPath);
				configedPlayer.on("loadeddata", function (evt){
					highlightListHandler();
					playClipHandler(configedPlayer, startTime, findPitch);
				});
			} else {
				highlightListHandler();
				playClipHandler(configedPlayer, startTime, findPitch);
			}
		}
	}
	var currentIdx = 0;
	var highlightListHandler = function () {
		var nextKey = dataKey;
		var height = 0;
		var list = $("[name='videoClips']");
		
		for (var i=0; i<list.length; i++) {
			var elem = list[i];
			var key = $(elem).attr('rel');
			if (key == nextKey) {
				$(elem).addClass('active');
				currentIdx = nextKey;
				$("#clipListWrap").animate({scrollTop:height}, 400);
				break;
			}
			height += $(elem)[0].clientHeight;
		}
	}
	
	var changePlayingTypeHandler = function (evt) {
		$("[name='playingType']").removeClass("selected");
		$(evt.currentTarget).addClass('selected');
		var playingType = $(evt.currentTarget).val();
		if (playingType != 'justplay') {
			playerPause();
		} else {
			if (currentPlayingId != null) {
				clearTimeout(currentPlayingId);
			}
		}
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