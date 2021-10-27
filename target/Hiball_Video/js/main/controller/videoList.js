define(['common', 'hiballView', 'cookieUtil',
        'text!main/views/videoList.html',
        'text!main/views/playList.html',
        'jquery', 'underscore', 'backbone', 'jqueryUi', 'bootstrap', 'popper', 'jqueryCookie', 'jqueryConfirm'
], function(Common, HiballView, Cookie, VideoList, PlayList) {
	var listByType = null;
	var playList = null;
	var configedPlayer = null;
	var videoCategory = null;
	var MenuView = HiballView.extend({
		chainEventHandler: function (collection, data) {
			var result = data.resultMap;
			
			$("#content").html(VideoList);
			configContentsSize();
			$(window).resize(windowResizingHandler);
			
			this.initModule(result);
			this.afterRender();
		},
		initModule :function (data) {
			var videoInfo = data.videoInfo;
			var gameVideoRecord = data.gameVideoRecord;
			
			var videoCate = _.groupBy(videoInfo, function(info){
				return info.gameDay+"_"+info.awayTeamName+"_"+info.homeTeamName;
			});
			
			configVideoCate(videoCate);
			configEventHandler();
			// Grouped videos by type
			// 호출된 데이터는 flat 하지만 내부는 날짜>팀>비디오타입으로 분류할 수 있는 형태 따라 날짜>팀&상대팀>비디오타입 기준으로 재정렬한다.
			listByType = _.groupBy(gameVideoRecord, function (record){
				var title = record.videoType;
				if (record.videoSeq !=  null && record.videoSeq != 0) {
					title+record.videoSeq;
				}
				return title;
			});
			var keys = _.keys(listByType);
			configedPlayer = document.getElementById("videoPlayer");
			
			// Default video type is BROADCAST
			playList = listByType[_.first(keys)];
			displayVideo(configedPlayer);
		},
		render:function(params, chain) {
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
				"gameInfoId":params.gameInfoId
			}
		};
		var videoInfoParam = {
			"serviceName":"gameScheduleService","returnName":"videoInfo",
			"subServiceName":"videoInfo",
			"sortColumn":"",
			"parameterSet":{
				"gameInfoId":params.gameInfoId
			}
		};
		
		paramArr.push(videoRecordParam);
		paramArr.push(videoInfoParam);
		
		return paramArr;
	}
	
	function configEventHandler() {
		$("#returnCriteria").on("click", returnCriteriaHandler);
		$("#videoCate").on("change", changeCategoryHandler);
	}
//	function removeEventHandler() {
//		$("#videoCate").off('change');
//	}
	
	var configVideoCate = function (cate) {
		var keys = _.keys(cate);
		videoCategory = cate;
		
		$("#videoCate").empty();
		$("#playVideoBtn").empty();
	
		_.each(keys, function (key, idx) {
			if (idx == 0) {
				$("#videoCate").append("<option value='"+key+"' selected>"+key+"</option>");
			} else {
				$("#videoCate").append("<option value='"+key+"'>"+key+"</option>");
			}
		});
		
		changeCategoryHandler();
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

		if (width > 576) {
			$("#videoClipList").height(window.innerHeight-145);
		} else {
			$("#videoClipList").height(window.innerHeight-400);
		}
	}
	
	var displayVideo = function () {
		$("#clipList").empty();
		
		var inning = 1;
		var loadingVideoPath = '';
		// Grouped list by INNING
		var listByInn = _.groupBy(playList, function(record){
			loadingVideoPath = record.videoPath;
			return record.inning;
		});
	
		var model = new Backbone.Model({
			playList:listByInn,
			inning:inning
		});
		
		playList = listByInn[inning];
		
		var template = _.template(PlayList);
		$("[name='videoClips']").off("click");
		
		$("#clipList").html(template(model.toJSON()));
		
		resizeDone();
		videoLoading(configedPlayer, loadingVideoPath);
		
		$("[name='videoClips']").on("click", selectClipEventHandler);
	}
	

	var duration = 5;
	var currentPlayingId = null;
	
	var videoLoading = function (player, videoPath) {
		$("#clip_source").attr("src", videoPath);
		player.load();
		player.oncanplaythrough = afterLoadingHandler;
	}
	
	function playVideo(player, startTime) {
		if (currentPlayingId != null)
			clearTimeout(currentPlayingId);
		
		player.currentTime = startTime;
		player.play();
		
		currentPlayingId = setTimeout(function(){
			player.pause()
		}, duration*1000);
	}
	
	var changeCategoryHandler = function (evt) {
		var selectedCate = $("#videoCate option:selected");
		$("#playVideoBtn").empty();
		
		var val = selectedCate.val();
		var videoTypes = videoCategory[val];
		console.log(videoTypes);
		var table = '<table>';
		table += '<tr>';
		_.each(videoTypes, function (type){
			table +='<td>';
			var title = '중계';
			var videoType = type.videoType;
			if ((type.videoType).indexOf('cam') != -1) {
				title = videoType.toUpperCase();
			}
			
			table +="<button name='types' class='btn' rel='"+videoType+"'>"+title+'</button>';
			table +='</td>';
		});
		table += '</tr>';
		table += '</table>';
		
		$("#playVideoBtn").html(table);
	}

	var selectClipEventHandler = function (evt) {
		var target = $(evt.currentTarget);
		var selectedPitch = target.attr("rel");
		
		$("[name='videoClips']").removeClass('active');
		target.addClass('active');
		
		var findPitch = _.find(playList, function (pitch){
			var key = pitch.inning+''+pitch.inningTb+''+pitch.batInningTurn+''+pitch.batPitchSeq;
			return key == selectedPitch;
		});
		
		var startTime = findPitch.pitchTime - findPitch.taggingGapTime;
		playVideo(configedPlayer, startTime);
	}
	
	var returnCriteriaHandler = function () {
		window.history.back();
	}
	
	function afterLoadingHandler(evt) {
		var media = evt.target;
	}
	
	return MenuView;
});