/**
 * 영상 구현
 * @author CWH
 */
define(['common', 'cookieUtil', 'hiballView'
		, './dataGroupingUtils'
		, 'text!./views/mediaComponent.html'
		, 'text!./views/mediaGameInfoTable.html'
		, 'text!./views/mediaSaveForm.html'
		, 'mousetrap'
		, './shortenCall'
		, './logging'
		, 'jquery', 'underscore', 'backbone', 'sortable'], 
function(Common, Cookie, HiballView, DataGroupingUtils, MediaComponent
			, MediaGameInfoTable, MediaSaveForm, Mousetrap, ShortenCall, Logging) {
	var dataGroupingUtils = new DataGroupingUtils();
	var immutablePitchTimeData = null;
	var pitchTimeData = null;
	//default size
	var appendTargetId = null;
	var width = 400;
	var height = 400;
	var frontTime = 2;
	var playTime = 5000; //재생간격 기본 1초로 지정
	var calcedPlayTime = playTime;
	//changeEventDetect, pause, play는 이벤트가 발생하는 실시간의 flag값을 저장하기 위한 변수
	var pause = false;
	//setTimeout을 사용하게 되므로 이전에 발생한 이벤트 플래그 값을 저장하기 위해 사용되는 array
	var videoTagId = "analysisMediaMain";
	var playListTagId = "playList";
	var subPlayListTagId = "subPlayList";
	var playIntervalTagId = "playInterval";
	var frontTimeTagId = "frontTime";
	var pitchInfoAreaTagId = "pitchInfoArea";
	var videoSlowBtnTagId = "videoSlowBtn";
	var videoBasicSlowBtnTagId = "videoBasicSlowBtn";
	var setTimeoutWrapper = null;
	var mainRemainTime = 0;
	var subRemainTime = 0;
	var mainStopTime = 0;
	var subStopTime = 0;
	var globalPitchTimeInfo = null;
	var globalSubPitchTimeInfo = null;
	var mainStartTime = 0;
	var subStartTime = 0;
	var saveMovieInfos = {};
	//로컬에 존재하는 비디오를 재생 할것인지 체크
	var globalPlayTimesArray = null;
	var globalPlayTimes = null;
	var playIntervalChangeFlag = false;
	var finalPitchTimeData = null;
	var dragCompareObj = {}; //drag 전용 key: value
	var slowValue = 1.0;
	var videoLocatedMap = {};
	var gShortenCall = new ShortenCall();
	var taggingTimeSettingObj = {}; //태깅 정보 시간 설정 변수 지정
	var gLogging = new Logging();
	
	var mediaComponent = HiballView.extend({
		init: function(_appendTargetId, _data, _width, _height) {
			initEntry(_appendTargetId, _data, _width, _height);
			window.onhashchange = function() {
				var video = document.getElementById(videoTagId);
				video.pause();
				clearTimeout(setTimeoutWrapper);
				window.onhashchange = function() {}
			}
		},
		render: function() {
			slowValue = 1.0;
			htmlSetUp(); // default render
			modalRender();
			// eventRegist();
			function videoPlayNPauseEventHandler(event) {
				var video = document.getElementById(videoTagId);
				//정지 상태면 재생으로
				if(video.paused) {
					$("#videoPlayNPause").text('정지');
					var video = document.getElementById(videoTagId);
					video.currentTime = mainStopTime;
					video.play();
					//err 발생 할거 같은 소스 0으로 주면 안돼고 정지 당시에 index값을 전달 해야한.
					videoPlayAsync(video, globalIndex, event);
					pause = false;
				} else {
					$("#videoPlayNPause").text('재생');
					var video = document.getElementById(videoTagId);
					video.pause();
					clearTimeout(setTimeoutWrapper);
					pause = true;
					//정지 될때의 시간 
	//				if(mainStopTime == 0) {
						mainStopTime = video.currentTime;
	//				}
					
					//정지 될때의 남은 재생시간.
					mainRemainTime = (calcedPlayTime * 1) - mainStopTime * 1 - mainStartTime * 1;
					subRemainTime = (calcedPlayTime * 1) - subStopTime * 1 - subStartTime * 1;
				}
			}
			//같은날의 경기 영상을 재생하고 있을때와 아닐때를 구분 해야한다. 
			function playListChangeHandler(event) {
				mainStopTime = 0;
				// $("#playList").attr("disabled", "true");
				clearTimeout(setTimeoutWrapper);
				var video = document.getElementById(videoTagId);
				var targetValue = event.currentTarget.value;
				var videoLocationIndex = videoLocatedMap[targetValue];
				globalPitchTimeInfo = globalPlayTimes[targetValue];
				
				if(globalPitchTimeInfo[0]['taggingYn']) {
					mediaPlayEntryFunc(playTimes);
					video.onloadstart = function(e) {
						$("#pitchInfoDiv").scrollTop(0);
						playInfoListTableRender(globalPitchTimeInfo);
						videoPlayAsync(video, videoLocationIndex);
						e.preventDefault();
					}
				} else {
					$("#" + pitchInfoAreaTagId).empty();
					$("#" + pitchInfoAreaTagId).append("<tr>");
					$("#" + pitchInfoAreaTagId).append("<td>");
					$("#" + pitchInfoAreaTagId).append("<h5 align='center'>태깅데이터없음</h5>");
					$("#" + pitchInfoAreaTagId).append("</td>");
					$("#" + pitchInfoAreaTagId).append("<tr/>");
					video.onplay = function(e) {
						e.stopPropagation();
					}
					video.onloadstart = function(e) {
						video.play();
						e.stopPropagation();
					}
				}
				
				var localPath = localPathMap[targetValue];
	
				video.src = localPath;
				video.load();
			}
			
			function playIntervalChangeHandler(event) {
				var value = event.currentTarget.value;
				if(value != 61) {
					playIntervalChangeFlag = true;
				} else {
					playIntervalChangeFlag = false;
				}
				playTime = value * 1000;
				calcedPlayTime = playTime + (frontTime * 1000);
			}
			
			function fronTimeChangeHandler(event) {
				var value = event.currentTarget.value;
				frontTime = value;
				calcedPlayTime = playTime + (frontTime * 1000);
			}
			
			function videoBasicSlowBtnHandler(event) {
				var video = document.getElementById(videoTagId);
				
				video.defaultPlaybackRate = 1.0;
				calcedPlayTime = playTime + (frontTime * 1000);
				video.load();
			}
			
			
			function videoSlowBtnHandler(event) {
				slowValue= event.currentTarget.value * 1;
				
				var video = document.getElementById(videoTagId);
				
				video.defaultPlaybackRate = slowValue;
				
				if(slowValue == 0.5) {
					calcedPlayTime *= 2;
				} else if(slowValue == 0.1) {
					calcedPlayTime *= 10;
				} else if(slowValue == 0.3) {
					calcedPlayTime *= 30;
				}
				calcedPlayTime += (frontTime * 1000);
				video.load();
			} 
			
			function videoSaveBtnHandler(event) {
				$("#selectedSaveMovieListContent").html(MediaSaveForm);
				$("#selectedSaveMovieList").jqxWindow("open");
				var selectedItem = [];
				// saveTaggingInfos
				_.each(saveTaggingInfos, function(item) {
					selectedItem.push(item);
				});
	
				selectedItem.sort(
					firstBy((i1, i2) => {
						return i1.inning * 1 - i2.inning * 1;
					})
					.thenBy("videoType")
					.thenBy("inningTb")
					.thenBy("batInningTurn")
					.thenBy("pitchTime")
					
				);

				selectedItem.sort(
					firstBy('gameDay')
				);

				var selectedItemSize = selectedItem.length;
				if(selectedItemSize > 0) {
					$("#dataNoExist").css("display", "none");
					$("#dataExist").css("display", "block");
					
					var source = {
						datatype: 'json',
						localData : selectedItem,
						pagesize : 10,
						dataFields : [
							{name: 'videoType'},
							{name: 'gameDay'},
							{name: 'inning'},
							{name: 'pitcherName'},
							{name: 'batterName'},
							{name: 'ballCodeName'},
							{name: 'ballSpeed'}
						],
						asyns: false
					}
					
					var dataAdapter = new $.jqx.dataAdapter(source);
					$("#selectedMediaList2").jqxGrid({
						source : dataAdapter,
	//					autowidth: '100%',
						width: '100%',
						pageable: true,
						autoheight: true,
						pagermode: 'simple',
						columns: [
							{text: '유형', datafield: 'videoType', align: 'center', cellsalign: 'center', width: '10%'},
							{text: '날짜', datafield: 'gameDay', align: 'center', cellsalign: 'center', width: '20%'},
							{text: '이닝', datafield: 'inning', align: 'center', cellsalign: 'center', width: '10%'},
							{text: '투수', datafield: 'pitcherName', align: 'center', cellsalign: 'center', width: '20%'},
							{text: '타자', datafield: 'batterName', align: 'center', cellsalign: 'center', width: '20%'},
							{text: '구종', datafield: 'ballCodeName', align: 'center', cellsalign: 'center', width: '10%'},
							{text: '구속', datafield: 'ballSpeed', align: 'center', cellsalign: 'center', width: '10%'}
						]
					});
				} else {
					$("#dataNoExists").css("display", "block");
					$("#dataExist").css("display", "none");
				}
				
				$("#movieSaveBtn").on("click", function(event) {
					var savedTaggingTimeSettingObj = {};
					var mediaSaveParam = {};
					var savedKey = [];
					_.each(saveTaggingInfos, function(item) {
						savedKey.push(item.gameRecordId);
					});
					var title = $("#mediaSaveTitle").val();
					//url에 의해 만들어진 gameRecordId들을 다 붙여서 보내면.. get방식에서 길이제한에 걸리는것을 방지하기 위함
					var uuid = new Date().getTime() + "-" + getUUID();
					var data = JSON.stringify(mediaSaveParam);
					
					var currentURL = window.location.href;
					//정규표현식을 이용해 도메인이냐 로컬호스트냐 지정
					if(currentURL.indexOf('8080') > -1) {
						var localhostURL = localhostType(currentURL);
						localhostURL += "media/baseAnalysis?&menuTitle=조건별영상검색&menuId=null&fav=null&rep=Y&uuid="+uuid;
						currentURL = localhostURL;
					} else {
						var domainURL = regUrlType(currentURL);
						domainURL += "record/media/baseAnalysis?&menuTitle=조건별영상검색&menuId=null&fav=null&rep=Y&uuid="+uuid;
						currentURL = domainURL;
					}
					
					$("#selectedSaveMovieList").jqxWindow("close");
					$("#mediaMakeReqLoader").jqxLoader("open");

					var token = Cookie.getCookie('hiball_token');
					var title = $("#mediaSaveTitle").val();
					mediaSaveParam['mediaGameRecords'] = JSON.stringify(savedKey);

					_.each(savedKey, (item) => {
						savedTaggingTimeSettingObj[item] = taggingTimeSettingObj[item];
					});

					mediaSaveParam['taggingTimeSet'] = JSON.stringify(savedTaggingTimeSettingObj);
					mediaSaveParam['title'] = title;
					mediaSaveParam['uuid'] = uuid;
					mediaSaveParam['shortURL'] = currentURL;
					mediaSaveParam['mediaType'] = 'normalMedia';
					mediaSaveParam['authToken'] = token;
					var data = JSON.stringify(mediaSaveParam);
					var taggingInfoObj = mediaMakeServer(uuid, title, slowValue, saveTaggingInfos);
					
					var callURL = Common.userURL + 'main/saveMedia.do';
					Common.getAjax(callURL, data, function(result) {
						$("#mediaMakeReqLoader").jqxLoader("close");
						$("#selectedSaveMovieList").jqxWindow("close");
						var execute = result.execute;
						if(execute == 'failure') {
							$.alert({
								title: '저장실패',
								content: '저장에 실패 하였습니다. 관리자에게 문의 해주세요.'
							});
						} else {
							var taggingInfoJson = JSON.stringify(taggingInfoObj);
							Common.getMediaServerAjaxByJSON("originMedia", taggingInfoJson, function(result) {
								mediaMakeLoggingCall();
								$.alert({
									title: title + " 영상 정보 저장",
									// content: title + " 영상 정보 저장 성공<br> 바로보기주소 : " + shortURL + "<br>" + result.message
									content: title + " 영상 정보 저장 성공"
								});
							});
						}
					});
				});
			}

			function mediaMakeLoggingCall() {
				gLogging.mediaMake();
			}
			
			function mediaMakeServer(uuid, title, slowValue, saveInfos) {
				var taggingMediaInfos = {"0" : {"targetMedia" : [], "taggingInfos" : {}}};

				var arrayTypeInfos = [];
				_.each(saveInfos, function(item) {
					arrayTypeInfos.push(item);
				});

				//비디오를 일자별로 정렬하고 역정렬 하면 최신 순으로 나온다.... -1이 파라미터 동작안해서 일단 이렇게
				arrayTypeInfos.sort(firstBy('gameDay')
									.thenBy("videoType")
									.thenBy("inning")
									.thenBy("inningTb")
									.thenBy("batInningTurn")
									.thenBy("pitchTime"));
				// arrayTypeInfos.reverse(); //역정렬

				var indexObj = taggingMediaInfos[0];
				var taggingCnt = 0;
				var targetMediaInfos = getTargetMedia(arrayTypeInfos);
	
	//			taggingMediaInfos["0"]["targetMedia"] = targetMediaInfos;
				
				_.each(targetMediaInfos, function(taggingInfos, videoName) {
					indexObj['targetMedia'].push(videoName);
					_.each(taggingInfos, function(taggingInfo) {
						var taggingInfosObj = indexObj["taggingInfos"];
						if(!taggingInfosObj.hasOwnProperty(videoName)) {
							taggingInfosObj[videoName] = [];
							if(taggingInfo.startTime > 0) {
								taggingInfosObj[videoName].push({"startTime" : taggingInfo.startTime - frontTime
																	, "durationTime" : (calcPlayTimeByHitResult(taggingInfo) / 1000)
																	, 'mediaTrackText' : taggingInfo.mediaTrackText});
								taggingCnt += 1;
							} else {
								console.log("태깅 정보 0 미만 존재");
							}
						} else {
							if(taggingInfo.startTime > 0) {
								taggingInfosObj[videoName].push({"startTime" : taggingInfo.startTime - frontTime
																	, "durationTime" : (calcPlayTimeByHitResult(taggingInfo) / 1000)
																	, 'mediaTrackText' : taggingInfo.mediaTrackText});
								taggingCnt += 1;
							} else {
								console.log("태깅 정보 0 미만 존재");
							}
						}
					});
				});
				
				taggingMediaInfos['videoTitle'] = title;
				taggingMediaInfos['taggingCnt'] = taggingCnt;
				taggingMediaInfos['uuid'] = uuid;
				taggingMediaInfos['slowValue'] = slowValue;
				
				var htmlHeight = 500;
				var htmlWidth = 800;
				
				 var x1 = $("#xDrag1").position().left;
					 var x2 = $("#xDrag2").position().left;
					 var y1 = $("#yDrag1").position().top;
					 var y2 = $("#yDrag2").position().top;
	
					 var cropHeightRate = "1.0";
					 var cropWidthRate = "1.0";
					 
					 var x = Math.min(x1, x2);
					 var y = Math.min(y1, y2);
					 
					 if(x1 != 0 || x2 != 0) {
						 var cropWidth = Math.abs(x2 - x1);
						 cropWidthRate = cropWidth / htmlWidth;
					 }
					 
					 if(y1 != 0 || y2 != 0) {
						 var cropHeight = Math.abs(y2 - y1);
						 cropHeightRate = cropHeight / htmlHeight;
					 }
					 
					 taggingMediaInfos['cropWidthRate'] = cropWidthRate;
					 taggingMediaInfos['cropHeightRate'] = cropHeightRate;
					 taggingMediaInfos['cropX'] = x;
					 taggingMediaInfos['cropY'] = y;
					 taggingMediaInfos['htmlWidth'] = htmlWidth;
					taggingMediaInfos['htmlHeight'] = htmlHeight;
					 
				return taggingMediaInfos;
			}
			
			function getTargetMedia(item) {
				return _.groupBy(item, "videoPath");
			}
			
			function regUrlType(data) {
				var regex = new RegExp("^http:\/\/([a-z0-9-_\.]*)(:?[0-9]*)[\/\?]", "g");
				var domain = regex.exec(data);
				
				return domain[0];
			}
			
			function localhostType(data) {
				var regex = new RegExp("(http|https)\:\/\/localhost:8080/[0-9a-zA-Z\_]*/", 'g');
				var domain = regex.exec(data);
				
				return domain[0];
			}
			
			function getUUID() {
				  function s4() {
					return Math.floor((1 + Math.random()) * 0x10000)
					  .toString(16)
					  .substring(1);
				  }
				  return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
			}
			
			function videoAllChoiceBtnHandler(event) {
				$("[name='saveMovieCheckBox']").prop("checked", true);
				
				_.each(pitchTimeData, function(item) {
					var startTime = item.pitchTime - item.taggingGapTime;
					var durationTime = calcPlayTimeByHitResult(item) / 1000;
					item['startTime'] = startTime;
					item['durationTime'] = durationTime;
					item['mediaTrackText'] = trackTextMake(item);
				});

				var filterPitchTimeData = _.filter(pitchTimeData, (item) => {
					return item.startTime > 0 
				})
				
				//saveTaggingInfos 새로 생성
				saveTaggingInfos = dragCompareObjMake(pitchTimeData);
			}
			
			//선택되어 저장된 객체 초기화 및 체크박스 체크 해제 
			function videoChoiceRemoveBtnHandler(event) {
				$("[name='saveMovieCheckBox']").prop("checked", false);
				saveTaggingInfos = {};
			}

			function mediaDeleteEvent() {
				var choiceIndexs = JSON.parse(JSON.stringify($("#savedMovieListContent").jqxGrid("getselectedrowindexes")));
				
				if(choiceIndexs.length > 0) {
					var deleteUUIDS = [];
					_.each(choiceIndexs, function(index) {
						deleteUUIDS.push(gMediaInfos[index].uuid);
					});

					var token = Cookie.getCookie("hiball_token");
					var url = Common.userURL + "main/saveMediaDelete.do";
					var data = JSON.stringify({"uuids" : deleteUUIDS.toString(), "authToken" : token});
					Common.getAjax(url, data, function(res) {
						var code = res.resultMap;
						if(code == 'success') {
							_.each(choiceIndexs, function(index) {
								$("#savedMovieListContent").jqxGrid("unselectrow", index);
							});

							alert("정상 반영되었습니다.");
							$("#savedMovieList").jqxWindow("close");
						}
					});
				}
			}

			var gMediaInfos = [];

			function videoSaveListBtnHandler(event) {
				var token = Cookie.getCookie("hiball_token");
				var data = JSON.stringify({'mediaType' : 'normalMedia', 'authToken' : token});
				$("#savedMovieList").jqxWindow("open");
				$("#mediaDeleteEvent").off("click");
				$("#mediaDeleteEvent").on("click", mediaDeleteEvent);
				var userUrl = Common.userURL + "main/saveMediaList.do";
				Common.getAjax(userUrl, data, function(res) {
					var medias = res.result;
					gMediaInfos = medias;
					var source = {
						datatype: 'json',
						localData: medias,
						pagesize : 10,
						dataFields: [
							{name : 'title'},
							{name : 'regiDate'},
							{name : 'shortURL'},
							{name : 'downURL'},
							{name : 'executeState'},
							{name : 'concatState'}, 
							{name : 'fileSize'}
						]
					}
					
					var dataAdapter = new $.jqx.dataAdapter(source);
					
					var urlRenderer = function(row, columnfield, value, defaulthtml, columnproperties) {
						var shortURL = value;
						
						return "<div style='color:red;height:29px; margin-top:7px;'align='center'>" + "<a target='_blank' id='shortURLTag' href='" + shortURL + "'>" + "바로보기" + "</a>" + "<div>";
					}
					
					var downUrlRenderer = function(row, columnfield, value, defaulthtml, columnproperties) {
						if(value == "") {
							return "<div style='height:29px; margin-top:7px;'align='center'>" + ""  + "<div>"
						} else {
							return "<div style='color:red;height:29px; margin-top:7px;'align='center'>" + "<a id='downURLTag' href='" + value + "'>" + "다운로드" + "</a>" + "<div>";
						}
					}
				
					var stateRender = function(row, columnfield, value, defaulthtml, columnproperties) {
						var executeState = value.split("/")[0];
						var processState = value.split("/")[1];
						if(executeState == 'null') {
							if(processState == 'start') {
								return "<div class='progress'><div class='progress-bar' role='progressbar' style='width:0%;' aria-valuenow='0' aria-valuemin='0' aria-valuemax='100'>0%</div>"
							} else if(processState == 'quater') {
								return "<div class='progress'><div class='progress-bar' role='progressbar' style='width:25%;' aria-valuenow='25' aria-valuemin='0' aria-valuemax='100'>25%</div>"
							} else if(processState == 'half') {
								return "<div class='progress'><div class='progress-bar' role='progressbar' style='width:50%;' aria-valuenow='50' aria-valuemin='0' aria-valuemax='100'>50%</div>"
							} else if(processState == 'threeQuater') {
								return "<div class='progress'><div class='progress-bar' role='progressbar' style='width:75%;' aria-valuenow='75' aria-valuemin='0' aria-valuemax='100'>75%</div>"
							} else if(processState == 'complete') {
								return "<div class='progress'><div class='progress-bar' role='progressbar' style='width:50%;' aria-valuenow='100' aria-valuemin='0' aria-valuemax='100'>100%</div>"
							} 
						} else {
							if(executeState == "success") {
								return "<div style='color:green;height:29px; margin-top:7px;'align='center'>" + "파일생성완료"  + "<div>"
							} else {
								return "<div style='color:red;height:29px; margin-top:7px;'align='center'>" + "파일생성실패"  + "<div>"
							}
						}
					}

					//@FIXME:
					// var deleteMedia = function(row, columnfield, value, defaulthtml, columnproperties) { 
					// 	console.log(row);
					// 	return "<div><button>삭제</button></div>"
					// }
					
					$("#savedMovieListContent").jqxGrid({
						source: dataAdapter,
						autowidth: true,
						pageable: true,
						autoheight: true,
						pagermode: 'simple',
						selectionmode: 'checkbox',
						columns: [
							// { text: '삭제', datafield: 'available', columntype: 'checkbox', width: 40 },
							{text: '영상제목', datafield: 'title', align: 'center', cellsalign: 'center', width: 145},
							{text: '저장주소', datafield: 'shortURL', align: 'center', cellsalign: 'center', width: 145, cellsrenderer : urlRenderer},
							{text: '다운로드', datafield: 'downURL', align: 'center', cellsalign: 'center', width: 145, cellsrenderer : downUrlRenderer},
							{text: '용량(MB)', datafield: 'fileSize', align: 'center', cellsalign: 'center', width: 145},
							{text: '상태', datafield: 'concatState', align: 'center', cellsalign: 'center', width: 145, cellsrenderer : stateRender},
							{text: '등록일', datafield: 'regiDate', align: 'center', cellsalign: 'center', width: 145},
							// {text: '삭제', align: 'center', cellsalign: 'center', width: 145, cellsrenderer: deleteMedia}
						]
					});
					
					$("#savedMovieListContent").on("rowdoubleclick", function(event) {
						var args = event.args;
						var boundIndex = args.rowindex;
						var media = medias[boundIndex];
						
						var mediaGameRecords = JSON.parse(media.mediaGameRecords);
						var mediaGameRecordsStr = mediaGameRecords.toString();
						
						var jsonParam = JSON.stringify([{
							"serviceName" : "gameRecordService", "subServiceName" : "pbpWithPitchTime",
							"sortColumn" : "", "returnName" : "pbpWithPitchTime",
							"parameterSet" : {"gameRecordId" : mediaGameRecordsStr, "teamId":"0", "inning":""}
						}]);
						
						var callApi = Common.callApi;
						callApi.fetch('POST', jsonParam, 'JSON', mediaSuccessHandler);
					});

					// $("#savedMovieListContent").on("rowselect", function(event) {
					// 	var chioceDeleteMedia = $("#savedMovieListContent").jqxGrid("getselectedrows");
					// 	console.log(chioceDeleteMedia);
					// });
				});
				
			}
			
			function videoForwardBtnHandler(event) {
				var moveTime = $("#moveTimeUnit").val() * 1;
				if(moveTime == "") alert("이동할 초를 입력하세요.");
				else {
					var video = document.getElementById(videoTagId);
					var currentTime = video.currentTime;
					video.currentTime = (currentTime + moveTime);
					
					//정지상태면
					if(video.paused) {
						mainStopTime = (currentTime + moveTime);
					} else {
						mainStartTime = (currentTime + moveTime);
					}
				}
			}
			
			function videoBackwardBtnHandler(event) {
				var moveTime = $("#moveTimeUnit").val() * 1;
				if(moveTime == "") alert("이동할 초를 입력하세요.");
				else {
					var video = document.getElementById(videoTagId);
					var currentTime = video.currentTime;
					video.currentTime = (currentTime - moveTime);
					
					//정지상태면
					if(video.paused) {
						mainStopTime = (currentTime - moveTime);
					} else {
						mainStartTime = (currentTime - moveTime);
					}
				}
			}
			
			function choiceViewBtnEventReist(event) {
				var size = _.size(saveTaggingInfos);
				
				if(size > 0) {
					var items = [];
					_.each(saveTaggingInfos, function(item) {
						items.push(item);
					});

					pitchTimeData = items;
					
					clearTimeout(setTimeoutWrapper);
					

					pitchTimeData.sort(
						firstBy((i1, i2) => {
							return i1.inning * 1 - i2.inning * 1;
						})
						.thenBy("inningTb")
						.thenBy("batInningTurn")
						.thenBy("pitchTime")
					);

					// pitchTimeData.sort(
					// 	firstBy('gameDay')
					// );
					
					renderEntry();
				} else {
					alert("선택 된 항목이 없습니다.");
				}
				
			}
			
			function choiceViewResetBtnEventRegist(event) {
				pitchTimeData = finalPitchTimeData;
				dragCompareObj = dragCompareObjMake(pitchTimeData);
				clearTimeout(setTimeoutWrapper);
				renderEntry();
			}
			
			function forwardBtnEventHandler(event) {
				var moveTime = event.currentTarget.value * 1;
				
				var video = document.getElementById("analysisMediaMain");
				var currTime = video.currentTime;
				video.currentTime = currTime + moveTime;
				
				if(video.paused) {
					mainStopTime = (currTime + moveTime);
				} else {
					mainStartTime = (currTime + moveTime);
				}
			}
			
			function backwardBtnEventHandler(event) {
				var moveTime = event.currentTarget.value * 1;
				var video = document.getElementById("analysisMediaMain");
				var currTime = video.currentTime;
				video.currentTime = currTime - moveTime;
				
				if(video.paused) {
					mainStopTime = (currTime - moveTime);
				} else {
					mainStartTime = (currTime - moveTime);
				}
			}
			
			Mousetrap.bind('m', mShortKeyBind);
			Mousetrap.bind('ㅡ', mShortKeyBind);
			
			function mShortKeyBind(event) {
				var video = document.getElementById("analysisMediaMain");
				var dataSize = _.size(pitchTimeData);
				var nextIndex = (globalIndex * 1) + 1;
				
				clearTimeout(setTimeoutWrapper);
				if(nextIndex < dataSize) {
					videoPlayAsync(video, nextIndex)
				} else {
					videoPlayAsync(video, 0);
				}
				
				mainStopTime = 0;
				mainRemainTime = calcedPlayTime;
				
				Mousetrap.unbind('ㅡ');
				Mousetrap.unbind('m');
				Mousetrap.unbind('w');
				Mousetrap.unbind('ㅈ');
				
				setTimeout(function() {
					Mousetrap.bind('w', zShortKeyBind);
					Mousetrap.bind('ㅈ', zShortKeyBind);
					Mousetrap.bind('m', mShortKeyBind);
					Mousetrap.bind('ㅡ', mShortKeyBind);
				}, 500);
				
				return false;
			}
			
			Mousetrap.bind('z', zShortKeyBind);
			Mousetrap.bind('ㅋ', zShortKeyBind);
			
			function zShortKeyBind(event) {
				var video = document.getElementById("analysisMediaMain")
				//정지 상태에서 타임이동을 할 수 있으므로 
				var dataSize = _.size(pitchTimeData);
				clearTimeout(setTimeoutWrapper);
				var beforeIndex = (globalIndex*1) - 1;
				
				if(beforeIndex >= 0) {
					videoPlayAsync(video, beforeIndex);
				} else if(beforeIndex < 0){
					videoPlayAsync(video, dataSize - 1);
				}
				
				Mousetrap.unbind('ㅡ');
				Mousetrap.unbind('m');
				Mousetrap.unbind('z');
				Mousetrap.unbind('ㅋ');
				
				
				setTimeout(function() {
					Mousetrap.bind('z', zShortKeyBind);
					Mousetrap.bind('ㅋ', zShortKeyBind);
					Mousetrap.bind('m', mShortKeyBind);
					Mousetrap.bind('ㅡ', mShortKeyBind);
				}, 500);
				
				return false;
			}
	
			Mousetrap.bind('b', bShortKeyBind);
	
			//TODO: 슬로우 앞으로 가기 이벤트
			function bShortKeyBind(event) {
				var moveTime = 0.1;
				
				var video = document.getElementById("analysisMediaMain");
				var currTime = video.currentTime;
				video.currentTime = currTime + moveTime;
				
				if(video.paused) {
					mainStopTime = (currTime + moveTime);
				} else {
					mainStartTime = (currTime + moveTime);
				}
			}
	
			Mousetrap.bind('c', cShortKeyBind);
	
			function cShortKeyBind(event) {
				var moveTime = 0.1;
				
				var video = document.getElementById("analysisMediaMain");
				var currTime = video.currentTime;
				video.currentTime = currTime - moveTime;
				
				if(video.paused) {
					mainStopTime = (currTime - moveTime);
				} else {
					mainStartTime = (currTime - moveTime);
				}
			}
			
			Mousetrap.unbind("space");
			Mousetrap.bind("space", function(event) {
				event.preventDefault();
				var video = document.getElementById(videoTagId);
				if(video.paused) {
					$("#videoPlayNPause").text('정지');
					var video = document.getElementById(videoTagId);
					video.currentTime = mainStopTime;
					video.play();
					//err 발생 할거 같은 소스 0으로 주면 안돼고 정지 당시에 index값을 전달 해야한.
					videoPlayAsync(video, globalIndex, event);
					pause = false;
				} else {
					$("#videoPlayNPause").text('재생');
					var video = document.getElementById(videoTagId);
					video.pause();
					clearTimeout(setTimeoutWrapper);
					pause = true;
					//정지 될때의 시간 
	//				if(mainStopTime == 0) {
						mainStopTime = video.currentTime;
	//				}
					
					//정지 될때의 남은 재생시간.
					mainRemainTime = (calcedPlayTime * 1) - mainStopTime * 1 - mainStartTime * 1;
					subRemainTime = (calcedPlayTime * 1) - subStopTime * 1 - subStartTime * 1;
				}
			});
			
			Mousetrap.unbind(['s', 'ㄴ']);
			Mousetrap.bind(['s', 'ㄴ'], function(event) {
				var moveTime = 5;
				backwardKeyEventLogic(moveTime);
			});
			Mousetrap.unbind(["d", "ㅇ"]);
			Mousetrap.bind(["d", "ㅇ"], function(event) {
				var moveTime = 1;
				backwardKeyEventLogic(moveTime);
			});
			Mousetrap.unbind(["f", "ㄹ"]);
			Mousetrap.bind(["f", "ㄹ"], function(event) {
				var moveTime = 0.1;
				backwardKeyEventLogic(moveTime);
			});
			
			Mousetrap.unbind(["g", "ㅎ"]);
			Mousetrap.bind(["g", "ㅎ"], function(event) {
				var moveTime= 0.1;
				forwardKeyEventLogic(moveTime);
			});
			
			Mousetrap.unbind(["h", "ㅗ"]);
			Mousetrap.bind(["h", "ㅗ"], function(event) {
				var moveTime= 1;
				forwardKeyEventLogic(moveTime);
			});
			
			Mousetrap.unbind(["j", "ㅓ"]);
			Mousetrap.bind(["j", "ㅓ"], function(event) {
				var moveTime= 5;
				forwardKeyEventLogic(moveTime);
			});
			
			function backwardKeyEventLogic(moveTime) {
				var video = document.getElementById("analysisMediaMain");
				var currTime = video.currentTime;
				video.currentTime = currTime - moveTime;
				
				if(video.paused) {
					mainStopTime = (currTime - moveTime);
				} else {
					mainStartTime = (currTime - moveTime);
				}
			}
			
			function forwardKeyEventLogic(moveTime) {
				var video = document.getElementById("analysisMediaMain");
				var currTime = video.currentTime;
				video.currentTime = currTime + moveTime;
				
				if(video.paused) {
					mainStopTime = (currTime + moveTime);
				} else {
					mainStartTime = (currTime + moveTime);
				}
			}
			// var contxt = GlobalVar.context+'/';
			function videoExeFileDownBtnHandler(event) {
				var token = Cookie.getCookie('hiball_token');
				var url = Common.userURL;
				window.location.href = url + "localmediaserver?authToken=" + encodeURI(token);
			}

			function videoInfoListMouseOutEventHandler(event) {
				var nodeName = $(event.relatedTarget)[0].nodeName;
			
				if(nodeName != 'TD') {
					$("[name='dragTd']").off("mouseover");
					$("[name='dragTd']").off("mouseup");
				}
			}

			function taggingTimeSettingAdaptedBtnEventHandler(event) {
				_.each(pitchTimeData, (item) => {
					var gameRecordId = item.gameRecordId;
					setCurrVideoCustomTime(gameRecordId);
				});

				$("[name='taggingTimeSettingBtn']").val('cancel');
				$("[name='taggingTimeSettingBtn']").text('취소');
			}

			function taggingTimeSettingCancelBtnEventHandler(event) {
				$("[name='taggingTimeSettingBtn']").val('adapted');
				$("[name='taggingTimeSettingBtn']").text('적용');
				$(".pitch_info_tr").css('background', '');
				taggingTimeSettingObj = {};
			}
			
			$("[name='playListSelector']").off("change");
			$("#videoPause").off("click");
			$("#videoPlay").off("click");
			$("#" + playIntervalTagId).off("change");
			$("#" + frontTimeTagId).off("change");
			$("#videoAllChoiceBtn").off("click");
			$("#videoChoiceRemoveBtn").off("click");
			$("#videoSaveListBtn").off("click");
			$("#forwardBtn").off("click");
			$("#backwardBtn").off("click");
			$("#choiceViewBtn").off("click");
			$("#choiceViewResetBtn").off("click");
			$("[name='forwardBtn']").off("click");
			$("[name='backwardBtn']").off("click");
			$("#videoExeFileDownBtn").off("click");
			$("#pitchInfoDiv").off("mouseout");
			$("#taggingTimeSettingAdaptedBtn").off("click");
			$("#taggingTimeSettingCancelBtn").off("click");
			
			
			$("[name='playListSelector']").on("change", playListChangeHandler);
			$("#videoPlayNPause").on("click", videoPlayNPauseEventHandler);
			$("#" + playIntervalTagId).on("change", playIntervalChangeHandler);
			$("#" + frontTimeTagId).on("change", fronTimeChangeHandler);
			$("#" + videoBasicSlowBtnTagId).on("click", videoBasicSlowBtnHandler);
			$("[name='videoSlowBtn']").on("click", videoSlowBtnHandler);
			$("#videoSaveBtn").on("click", videoSaveBtnHandler);
			$("#videoAllChoiceBtn").on("click", videoAllChoiceBtnHandler);
			$("#videoChoiceRemoveBtn").on("click", videoChoiceRemoveBtnHandler);
			$("#videoSaveListBtn").on("click", videoSaveListBtnHandler);
			$("#forwardBtn").on("click", videoForwardBtnHandler);
			$("#backwardBtn").on("click", videoBackwardBtnHandler);
			$("#choiceViewBtn").on("click", choiceViewBtnEventReist);
			$("#choiceViewResetBtn").on("click", choiceViewResetBtnEventRegist);
			$("[name='forwardBtn']").on("click", forwardBtnEventHandler);
			$("[name='backwardBtn']").on("click", backwardBtnEventHandler);
			$("#videoViewCutter").on("click", videoViewCutterHandler);
			$("#pitchInfoDiv").on("mouseout", videoInfoListMouseOutEventHandler);
			$("#taggingTimeSettingAdaptedBtn").on("click", taggingTimeSettingAdaptedBtnEventHandler);
			$("#taggingTimeSettingCancelBtn").on("click", taggingTimeSettingCancelBtnEventHandler);
	
			$("#xDrag1").draggable({	 containment: "#xDragContainer1" });
			
			$("#xDrag2").draggable({ containment: "#xDragContainer2" });
			
			$("#yDrag1").draggable({ containment: "#yDragContainer1" });
			
			$("#yDrag2").draggable({ containment: "#yDragContainer1" });
			
			$("#videoExeFileDownBtn").on("click", videoExeFileDownBtnHandler)
			
			$("#shortkey_view_btn").on("click", function(event) {
				var value = event.currentTarget.value;
				if(value == 'open') {
					$("#shortkey_view_btn").text("영상단축키 닫기")
					$(".short_key_content").css("display", "block");
					$("#shortkey_view_btn").val("close")
				} else {
					$("#shortkey_view_btn").text("영상단축키 보기")
					$(".short_key_content").css("display", "none");
					$("#shortkey_view_btn").val("open")
				}
				
			});
			
			$("#shortkey_close_btn").on("click", function(event) {
				$(".short_key_content").css("display", "none");
			})
	
			$("#videoKindBtn").on("click", function(event) {
				addInfoWindowOpenNClose(event, 'videoKindBtn', 'video_kind_content');
			});
	
			$("#video_kind_choice").off("click");
			$("#video_kind_choice").on("click", function(event) {
				$("#videoChoiceRemoveBtn").trigger("click");
				clearTimeout(setTimeoutWrapper);
				var el = $("[name='videoKindItem']");
				var existChecked = el.is(':checked');
				if(existChecked) {
					var filterData = [];
					var groupByData = _.groupBy(immutablePitchTimeData, (item) => {
						return item['videoPath'] + "/";
					});
					
					_.each(el, (item) => {
						var checked = $(item).prop("checked");
						if(checked) {
							var videoPath = $(item).val();
							var choiceData = groupByData[videoPath];
							filterData = filterData.concat(choiceData);
						}
					});
					pitchTimeData= filterData;
					renderEntry(event);	
				} else {
					if(!event.isTrigger) {
						alert("최소 하나 이상의 영상을 선택세요.");
					}
				}	
			});
	
			$("[name='videoTypeBtn']").off("click");
			$("[name='videoTypeBtn']").on("click", (event) => {
				var value = event.currentTarget.value;
	
				var el = $("[name='videoKindItem']");
				_.each(el, (item) => {
					console.log(item.placeholder);
				});
			});

			var uuid = getParameterByName('uuid');
			
			if(uuid == null) {
				renderEntry();
				$("#video_kind_choice").trigger("click"); //default cam제외를 위한 이벤트 발생
			} else {
				urlAccessEntry(uuid);
			}
		},
		destroy: function() {
			//다른 모듈에서 창을 닫을 경우 비디오를 정지 시켜 버린다.
			var video = document.getElementById(videoTagId);
			video.pause();
			clearTimeout(setTimeoutWrapper);
		}
	});
	//{"videoPath`gameRecordId" : {}}
	
	function initEntry(_appendTargetId, _data, _width, _height) {
		mainRemainTime = 0, subRemainTime = 0, subStopTime = 0;
		globalPitchTimeInfo = null, globalSubPitchTimeInfo = null;
		mainStartTime = 0, subStartTime = 0;
		globalPlayTimesArray = null, globalPlayTimes = null;
		frontTime = 2, playTime = 5000;
		calcedPlayTime = playTime;
		clearTimeout(setTimeoutWrapper);
		appendTargetId = _appendTargetId;
		immutablePitchTimeData = pitchTimeData = _data;
		try {
			pitchTimeData.sort(
				firstBy((i1, i2) => {
					return i1.inning * 1 - i2.inning * 1;
				}).thenBy("inningTb")
				.thenBy("batInningTurn")
				.thenBy("pitchTime")
			);
		} catch(err) {
			
		}
		
		finalPitchTimeData = _data;
		width = _width;
		height = _height;
		saveMovieInfos = {};
		saveTaggingInfos = {};
		dragCompareObj = dragCompareObjMake(pitchTimeData);
	}
	
	function dragCompareObjMake(pitchTimeData) {
		dragCompareObj = {};
		_.each(pitchTimeData, function(item) {
			var key = item.videoPath + "`" + item.gameRecordId;
			dragCompareObj[key] = item;
		});

		return dragCompareObj;
	}
	//serverPath : localPath
	var localPathMap = {};
	function renderEntry(event) {
		if(pitchTimeData != null) {
			globalPlayTimes = playTimes = _.groupBy(pitchTimeData, item => {
				return item['videoPath'];
			});
			//서버 경로가 key값이 되고 로컬 경로가 value가 되는 localPathMap
			_.each(playTimes, function(item, index) {
				var localPath = _.uniq(_.pluck(item, 'localPath'));
				localPathMap[index] = localPath[0];
			});
			
			var playPaths = getPlayPaths(pitchTimeData);
			var immutablePlayPaths = getPlayPaths(immutablePitchTimeData);
			if(pitchTimeData[0].taggingYn) { //태깅이 되어 있다면 
				playListSelectTagRender(playPaths);
				videoKindAreaRender(immutablePlayPaths, playPaths, event);
				mediaPlayEntryFunc(playTimes);
			} else {
				//태깅이 되어 있지 않다면 영상만 쭉 재생한다. 
				var videoPath = pitchTimeData[0].videoPath;
				var video = document.getElementById(videoTagId);
				video.src = makeVideoPath(videoPath);
				video.load();
				video.play();
				$("#videoPlayNPause").text("정지");
				videoKindAreaRender(immutablePlayPaths, playPaths);
				makePlayList(playTimes);
			}
		} 
	}
	
	
	function makeVideoPath(videoPath) {
		return videoPath;
	}
	
	function getParameterByName(name, url) {
	    if (!url) url = window.location.href;
	    name = name.replace(/[\[\]]/g, "\\$&");
	    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
	        results = regex.exec(url);
	    if (!results) return null;
		if (!results[2]) return '';
		
	    return decodeURIComponent(results[2].replace(/\+/g, " "));
	}
	
	function urlAccessEntry(uuid) {
		var token = Cookie.getCookie('hiball_token');
		var uuidObj = {'uuid' : uuid, 'mediaType' : 'normalMedia', 'authToken' : token};
		var data = JSON.stringify(uuidObj);
		var userURL = Common.userURL;
		Common.getAjax(userURL + 'main/saveMediaList.do', data, function(res) {
			var mediaGameRecords = JSON.parse(res.result[0].mediaGameRecords);
			taggingTimeSettingObj = JSON.parse(res.result[0].taggingTimeSet);
			
			var mediaGameRecordsStr = mediaGameRecords.toString();
			
			var jsonParam = JSON.stringify([{
				"serviceName" : "gameRecordService", "subServiceName" : "pbpWithPitchTime",
				"sortColumn" : "", "returnName" : "pbpWithPitchTime",
				"parameterSet" : {"gameRecordId" : mediaGameRecordsStr, "teamId":"0", "inning":""}
			}]);
			
			var callApi = Common.callApi;
			callApi.fetch('POST', jsonParam, 'JSON', mediaSuccessHandler);
		});
	}
	
	function modalRender() {
		var videoSaveBtnOffset = $("#videoSaveBtn").offset();
		$('#selectedSaveMovieList').jqxWindow({
			title: "영상 저장",
			position: { x: 0 + document.body.offsetWidth/2 - 444/2
				, y: 0 + document.body.offsetHeight/2 - 890/2} ,
			autoOpen:false,
			maxHeight: 444, maxWidth: 600, 
			minHeight: 400, minWidth: 600, 
			height: 444, width: 1000,
			isModal:true
        });
		$("#selectedSaveMovieListContent").html("저장을 위해 선택한 데이터가 없습니다.");
		
		var saveListBtnOffset = $("#videoSaveListBtn").offset();
		$("#savedMovieList").jqxWindow({
			title: "영상 저장 목록 <button id='mediaDeleteEvent' class='.btn-warning'>선택 항목 제거</button>",
			position: { x: 0 + document.body.offsetWidth/2 - 878/2
				, y: 0 + document.body.offsetHeight/2 - 600/2} ,
			autoOpen: false, 
			maxHeight: 600, maxWidth: 1100, 
			minHeight: 450, minWidth: 200, 
			width: 910, height: 425,
			isModal:true
		});
	}
	
	/**
	 * 영상 목록 데이터 추출 메소드
	 * 영상목록 데이터 [{videoPath : "", taggingYn: true, downloadFilename : ""}, ...n]
	 */
	function getPlayPaths(pitchTimeData) {
		var playPaths = [];

		var playTimes = _.groupBy(pitchTimeData, (item) => {
			return item['videoPath'];
		});

		_.each(playTimes, function(item, index) {
			var playPath = {};
			
			if(item.length > 0) {
				playPath['videoPath'] = index;
				playPath['taggingYn'] = item[0]['taggingYn'];
				playPath['downloadFilename'] = item[0]['downloadFilename'];
				var gameVideoTypeSeq = item[0]['gameVideoTypeSeq'];
				var videoType = item[0]['videoType'];
				playPath['videoType'] = videoType + gameVideoTypeSeq;
			}
			
			playPaths.push(playPath);
		});
		
		return playPaths;
	}

	function playListSorting(playList) {
		playList.sort(firstBy(('downloadFilename')));
		// playList.reverse();
	}
	
	function playListSelectTagRender(playList) {
		playListSorting(playList);
		$("#" + playListTagId).empty();
		
		var downloadFilename = '영상제목없음';
		var videoPath = "";
		
		_.each(playList, function(item) {
			if(item.length != 0) {
				downloadFilename = item['downloadFilename'];
				videoPath = item['videoPath'];
				videoPath = makeVideoPath(videoPath);
			}
			
			$("#" + playListTagId).append("<option value="+ videoPath +">" + downloadFilename + "</option>");
		});
	}

	function videoKindAreaRender(playList, playPaths, event) {
		var videoKindNameMap = {
			"broadCast0" : "중계", "cam1" : "캠1", "cam2" : "캠2", "cam3" : "캠3"
			, "cam4" : "캠4", "cam5" : "캠5"
		};
		playListSorting(playList);
		$("#video_kind_header").empty();
		$("#video_kind_list").empty();

		var videoPath = "";
		var downloadFilename = "";
		var videoType = "";
		var videoTypeArr = _.uniq(_.pluck(playList, 'videoType'));
		
		_.each(videoTypeArr, (item) => {
			$("#video_kind_header").append("<span class='videoKindHeaderItem' id='"+item+"'>" + videoKindNameMap[item] + "</span>");
		});

		_.each(playList, function(item) {
			var checked = "";
			if(item.length != 0) {
				downloadFilename = item['downloadFilename'];
				videoPath = item['videoPath'];
				videoType = item['videoType'];
				videoPath = makeVideoPath(videoPath);
			}

			_.each(playPaths, (item) => {
				if(event) {
					if(item.downloadFilename == downloadFilename) {
						checked = "checked";
					}
				} else {
					var leagueType = window.localStorage.getItem('leagueType');
					if(leagueType == 4206 || leagueType == 4212) {
						if(item.downloadFilename == downloadFilename && videoType != 'broadCast0') {
							checked = "checked";
						}
					} else {
						if(item.downloadFilename == downloadFilename && videoType == 'broadCast0') {
							checked = "checked";
						}
					}
					
				}
			});
			

			$("#video_kind_list").append("<label><input class='video_kind_list_" + videoType +"' " + checked + " type='checkbox' name='videoKindItem' value="+ videoPath +"/>" + downloadFilename +"</label><br>");
		});

		$(".videoKindHeaderItem").off("click");
		$(".videoKindHeaderItem").on("click", function(event) {
			var videoType = event.currentTarget.id;
			var curChecked = $(".video_kind_list_" + videoType).prop("checked");
			$(".video_kind_list_" + videoType).prop("checked", !curChecked);
		});
	}
	
	function playListPartition(playList, partitionSize) {
		var partitionArr = [];
		var loopCnt = Math.ceil(playList.length / partitionSize);
		for(var i = 1; i <= loopCnt; i++) {
			var tmpArr = [];
			for(var index = (i-1)*partitionSize; (index < (i * partitionSize) && index < playList.length); index++) {
				tmpArr.push(playList[index]);
			}
			partitionArr.push(tmpArr);
		}
		
		return partitionArr;
	}
	
//	var viewData = {}; //안쓰는 변수입니다.
	function playInfoListTableRender(playList) {
//		viewData = {};
		$("#" + pitchInfoAreaTagId).empty();
		var id = 0;
		var beforeVideoPath = "";
		
		var partitionPlayList = playListPartition(playList, 1000);
		$("#" + pitchInfoAreaTagId).empty();
		_.each(partitionPlayList, function(item, index) {
			setTimeout(function() {
				var template = _.template(MediaGameInfoTable);
				var htmlData = new Backbone.Model({
					partitionValue: index,
					saveTaggingInfos: saveTaggingInfos,
					taggingTimeSettingObj: taggingTimeSettingObj,
					playList: item
				});
				$("#" + pitchInfoAreaTagId).append(template(htmlData.toJSON()));
				$("[name='pitchInfoname']").off("click");
				$("[name='pitchInfoname']").on("click", function(event) {
					//정지 상태에서 타임이동을 할 수 있으므로 
					$("[name='pitchInfoname']").css("color", "#555").css("font-weight", "normal");
					var video = document.getElementById(videoTagId);
					clearTimeout(setTimeoutWrapper);
					var targetId = event.currentTarget.id;
					var index = targetId.substring(targetId.lastIndexOf("_") + 1, targetId.length);
					mainStopTime = 0;
					mainRemainTime = calcedPlayTime
					globalIndex = index;
					videoPlayAsync(video, index);
				});

				$("[name='saveMovieCheckBox']").off("click");
				$("[name='saveMovieCheckBox']").on("click", saveMovieCheckBoxHandler);
				
				$("[name='dragTd']").off("mousedown");
				$("[name='dragTd']").on("mousedown", dragEventTest);

				$("[name='taggingTimeSettingBtn']").off("click");
				$("[name='taggingTimeSettingBtn']").on("click", taggingTimeSettingBtnEventHandler);
			}, 1000 * index);
		});

		
		_.each(playList, function(item, index) {
			if(index == 0) {
				beforeVideoPath = item.videoPath;
				videoLocatedMap[item.videoPath] = index;
			} else {
				if(beforeVideoPath != item.videoPath) {
					beforeVideoPath = item.videoPath;
					videoLocatedMap[item.videoPath] = index;
				}
			}

		});
	}
	
	function dragEventTest(event) {
		var eventType = event.type;
		var childInput = $(event.currentTarget).siblings("td").find("input");
		var checkInputTag = $(childInput[0]);
		var checkInputId = checkInputTag.prop("id");
		var checkBool = $(checkInputTag).prop("checked");
		if(eventType == 'mousedown') {
			$("[name='dragTd']").on("mouseover", dragEventTest);
			$("[name='dragTd']").on("mouseup", dragEventTest);
			saveTaggingInfoByDrag(checkInputId);
			if(checkBool) {
				$(checkInputTag).prop("checked", false);
			} else {
				$(checkInputTag).prop("checked", true);
			}
		} else if(eventType == 'mouseover') {
			saveTaggingInfoByDrag(checkInputId);
			if(checkBool) {
				$(checkInputTag).prop("checked", false);
			} else {
				$(checkInputTag).prop("checked", true);
			}
		} else if(eventType == 'mouseup') {
			$("[name='dragTd']").off("mouseover");
			$("[name='dragTd']").off("mouseup");
		}
	}
	
	function saveTaggingInfoByDrag(checkInputId) {
		if(checkInputId !== undefined) {
			var item = dragCompareObj[checkInputId];
			var mediaTrackText = trackTextMake(item);
			var startTime = item.pitchTime - item.taggingGapTime;
			var durationTime = calcPlayTimeByHitResult(item) / 1000;
			item['startTime'] = startTime;
			item['durationTime'] = durationTime;
			item['mediaTrackText'] = mediaTrackText;

			saveObj = item;
		
			if(saveTaggingInfos.hasOwnProperty(checkInputId)) {
				saveTaggingInfos = _.omit(saveTaggingInfos, checkInputId);
			} else {
				saveTaggingInfos[checkInputId] = saveObj;
			}
		} else {
			alert("태깅 정보가 없습니다.");
		}
		
	}

	
	
	var saveTaggingInfos = {};
	
	function saveMovieCheckBoxHandler(event) {
		var currentVideoPath = $("#playList").val();
		var saveTaggingInfosKey = event.currentTarget.id;
		var key = event.currentTarget.id;
		key = key.split("`")[1];
		var value = event.currentTarget.value;
		//존재한다면 삭제!
		if(saveMovieInfos.hasOwnProperty(key)) {
			saveMovieInfos = _.omit(saveMovieInfos, key);
		} else {
			saveMovieInfos[key] = value
		}
		var saveObj = null;
		for(var i=0; i<pitchTimeData.length; i++) {
			var item = pitchTimeData[i];
			if(item.videoPath == currentVideoPath && item.gameRecordId == key) {
				var startTime = item.pitchTime - item.taggingGapTime;
				var durationTime = calcPlayTimeByHitResult(item) / 1000;
//				saveObj = {"videoPath" : item.videoPath, "startTime" : startTime, "durationTime" : durationTime}
				item['startTime'] = startTime;
				item['durationTime'] = durationTime;
				var mediaTrackText = trackTextMake(item);
				item['mediaTrackText'] = mediaTrackText;
				saveObj = item;
				break;
			}
		}
		
		if(saveTaggingInfos.hasOwnProperty(saveTaggingInfosKey)) {
			saveTaggingInfos = _.omit(saveTaggingInfos, saveTaggingInfosKey);
		} else {
			saveTaggingInfos[saveTaggingInfosKey] = saveObj;
		}
	}

	function taggingTimeSettingBtnEventHandler(event) {
		var id = event.currentTarget.id;
		var value = event.currentTarget.value;
		//적용처리
		if(value == 'adapted') {
			event.currentTarget.value = 'cancel';
			$(event.currentTarget).text('취소');
			
			// var frontTime = $("#frontTime").val();
			// var playInterval = $("#playInterval").val();

			// taggingTimeSettingObj[id] = {
			// 	"frontTime" : frontTime,
			// 	"playInterval" : playInterval
			// };
			// $("#pitch_info_tr_" + id).css('background', '#FEE5CE');
			setCurrVideoCustomTime(id);
		} else {
			event.currentTarget.value = 'adapted';
			$(event.currentTarget).text('적용');
			$("#pitch_info_tr_" + id).css('background', '');
			delete taggingTimeSettingObj[id];
		}
	}

	/**
	 * fontTime & playInterval 설정
	 */
	function setCurrVideoCustomTime(gameRecordId) {
		var frontTime = $("#frontTime").val();
		var playInterval = $("#playInterval").val();

		taggingTimeSettingObj[gameRecordId] = {
			"frontTime" : frontTime,
			"playInterval" : playInterval
		};
		$("#pitch_info_tr_" + gameRecordId).css('background', '#FEE5CE');
	}
	
	/**
	 * 이벤트를 등록한다. 
	 */
	// function eventRegist(playTimes) {
	// }

	function addInfoWindowOpenNClose(event, id, clazz) {
		var value = event.currentTarget.value;
		if(value == 'open') {
			$("." + clazz).css("display", "block");
			$("#" + id).val("close")
		} else {
			$("." + clazz).css("display", "none");
			$("#" + id).val("open")
		}
	}
	
	function videoViewCutterHandler(event) {
		var value = event.currentTarget.value;
		
		if(value == 'off') {
			$(event.currentTarget).text("영역지정(보기)");
			$(event.currentTarget).val("on");
			$("#ruler_area").css("display", "none");
		} else if(value == 'on') {
			$(event.currentTarget).text("영역지정(가리기)");
			$(event.currentTarget).val("off");
			$("#ruler_area").css("display", "block");
		}
	}
	
	var alertCnt = 0;
	function mediaPlayEntryFunc(playTimes, nextVideoPath) {
		var videoValue = $("#" + playListTagId).val();
		var videoText = $("#" + playListTagId + " option:selected").text();
		if(videoText != '') {
			if(typeof nextVideoPath != 'undefined') {
				$("#" + playListTagId).val(nextVideoPath);
				$("#" + subPlayListTagId).val(nextVideoPath);
			}
			var video = document.getElementById(videoTagId);
			globalPitchTimeInfo = playTimes[videoValue];
			video.muted = true;
//			video.src = videoValue;
			var localPath = localPathMap[videoValue];
			
			video.src = localPath;

			video.onplay = function(e) {
				var video = document.getElementById(videoTagId);
				pause = false;
				play = true;
				
				if(mainStopTime != 0) {
					video.currentTime = mainStopTime;
					videoPlayAsync(video, globalIndex, event);
				}
				
			}

			video.onpause = function(e) {
				var video = document.getElementById(videoTagId);
				pause = true;
				play = false;
				clearTimeout(setTimeoutWrapper);
				mainStopTime = video.currentTime;
				mainRemainTime = (calcedPlayTime * 1) - mainStopTime * 1 - mainStartTime * 1;
				subRemainTime = (calcedPlayTime * 1) - subStopTime * 1 - subStartTime * 1;
			}
			
			video.onerror = function(e) {
				clearTimeout(setTimeoutWrapper);
				var errVideoPath = $("#" + playListTagId).val();
				
				if(alertCnt <= 2) {
					video.src = errVideoPath;
					alertCnt+=1;
				} else {
					if(alertCnt > 2) alertCnt = 0;
					// alert(errVideoPathText + "영상 호출 시 오류가 발생 하여 목록에서 제거됩니다.")
					pitchTimeData = _.filter(pitchTimeData, function(item) {
						return item.videoPath != errVideoPath;
					});
					var immutablePlayPaths = getPlayPaths(immutablePitchTimeData);
					var playPaths = getPlayPaths(pitchTimeData);
					playListSelectTagRender(playPaths);
					videoKindAreaRender(immutablePlayPaths, playPaths);
					
					var videoValue = $("#" + playListTagId).val();
					globalPitchTimeInfo = globalPlayTimes[videoValue];
					
					playInfoListTableRender(globalPitchTimeInfo);
					var localPath = localPathMap[videoValue];
					
					if(localPath !== undefined) {
						video.src = localPath;	
					} else {
						alert('영상이 없습니다.');
					}
				}
			}
			
			video.onloadstart = function(e) {
				$("#videoPlayNPause").text('정지');
				videoPlayAsync(video, 0);
				playInfoListTableRender(globalPitchTimeInfo);
			}
		} else {
			alert("영상데이터가 없습니다. 영상 갱신 또는 재생이 되지 않습니다.");
		}
	}
	
	/**
	 * 비디오 재생 리스트가 끝날 경우다음 영상으로 재생
	 */
	function videoSearch() {
		var currentMainPlayListValue = $("#" + playListTagId).val();
		var nextVideo = $("#" + playListTagId + " option:selected").next().val();
		
		if(nextVideo === undefined) {
			currentMainPlayListValue = $("#" + playListTagId + " option:eq(0)").val();
		} else {
			currentMainPlayListValue = nextVideo;
		}
		
		$("#" + playListTagId).val(currentMainPlayListValue);
		$("#pitchInfoDiv").scrollTop(0);
		mediaPlayEntryFunc(globalPlayTimes);
	}
	
	function calcPlayTimeByHitResult(item) {
		var defaultPlayInterval = 5;
		var hitResultCode = item.hitResultCode;
		var runnerState = item.beforeRunnerState;

		if(taggingTimeSettingObj.hasOwnProperty(item.gameRecordId)) {
			var taggingChangeTimeInfo = taggingTimeSettingObj[item.gameRecordId];
			defaultPlayInterval = taggingChangeTimeInfo['playInterval'];
			inFrontTime = taggingChangeTimeInfo['frontTime'];
			calcedPlayTime = calcPlayTimeFunc(defaultPlayInterval, inFrontTime);

			$("#frontTime").val(inFrontTime);
			$("#playInterval").val(defaultPlayInterval);
		} else if(!playIntervalChangeFlag) { //자동 시간 설정
			if(hitResultCode == 6604 || hitResultCode == 6607 || hitResultCode == 6610 || hitResultCode == 6613) {
				$("#playInterval").val(10);
				defaultPlayInterval = 10;
			}  else {
				if(runnerState == 0) {
					$("#playInterval").val(7);
					defaultPlayInterval = 7;
				} else {
					$("#playInterval").val(6);
					defaultPlayInterval = 6;
				}
			}
			
			// playTime = defaultPlayInterval * 1000;
			
			// if(slowValue == 0.5) {
			// 	playTime *= 2;
			// } else if(slowValue == 0.1) {
			// 	playTime *= 10;
			// } else if(slowValue == 0.3) {
			// 	playTime *= 3;
			// }
			
			// calcedPlayTime = playTime + (frontTime * 1000);
			calcedPlayTime = calcPlayTimeFunc(defaultPlayInterval, frontTime);

			$("#frontTime").val(frontTime);
			$("#playInterval").val(defaultPlayInterval);
		}
		
		return calcedPlayTime;
	}

	function calcPlayTimeFunc(playInterval, frontTime) {
		playTime = playInterval * 1000;
			
		if(slowValue == 0.5) {
			playTime *= 2;
		} else if(slowValue == 0.1) {
			playTime *= 10;
		} else if(slowValue == 0.3) {
			playTime *= 3;
		}
		
		calcedPlayTime = playTime + (frontTime * 1000);
		return calcedPlayTime;
	}
	
	function videoChangeCheck(videoPath, index) {
		var videoTag = document.getElementById(videoTagId);
		
		$("#playList").attr("disabled", "true");
		
		videoTag.onloadstart = function(e) {
			clearTimeout(setTimeoutWrapper);
			e.preventDefault();
			videoPlayAsync(videoTag, index);
		}

		var playListTag = $("#playList");
		var currentVideoPath = playListTag.val();
		
		if(currentVideoPath != videoPath) {
			clearTimeout(setTimeoutWrapper);
			var localPath = localPathMap[videoPath];
			videoTag.src = localPath;
			videoTag.load();
			playListTag.val(videoPath);
			return true;
		} 
		
		return false;
	}
	
	/**
	 * globalPitchTimeInfo에 접근한다. 
	 */
	var globalIndex = 0;
	function videoPlayAsync(video, index, eventCall) {
		var videoSrc = video.src;
		if(videoSrc.indexOf("localhost") > -1) {
			$("#videoLocationSpan").css("color", "black");
			$("#videoLocationSpan").html("내 컴퓨터 영상");
		} else {
			$("#videoLocationSpan").css("color", "red");
			$("#videoLocationSpan").html("하이볼 서버 영상(네트워크 상황에 따라 영상 재생에 문제가 생길 수 있습니다.)");
		}
		
		try {
			var mainPitchTimeInfo = globalPitchTimeInfo[index];
			globalIndex = index;
			if(typeof eventCall == 'undefined') {
				var mainItem = globalPitchTimeInfo[index];
				if(mainItem['pitchTime'] - mainItem['taggingGapTime'] < 0) {
					clearTimeout(setTimeoutWrapper);
					setTimeoutWrapper = setTimeout(function() {
						videoPlayAsync(video, index+1);
					}, 100);
				} else {
					mainStartTime = mainItem['pitchTime'] - mainItem['taggingGapTime'];
					// mainStartTime -= frontTime;
					if(taggingTimeSettingObj.hasOwnProperty(mainPitchTimeInfo.gameRecordId)) {
						var inFromtTime = taggingTimeSettingObj[mainPitchTimeInfo.gameRecordId]['frontTime'];
						mainStartTime -= inFromtTime;
					} else {
						mainStartTime -= frontTime;
					}
					
					var videoState = video.readyState;
					var currentId = "pitchInfo_" + index;
					
					if(videoState != 4) {
						setTimeoutWrapper = setTimeout(function() {
							videoPlayAsync(video, index);
						}, 1000);
					} else if(videoState == 4) {
						clearTimeout(setTimeoutWrapper); //누적되는 setTimeoutWrapper제외
						alertCnt = 0;
						$("#playList").removeAttr("disabled");
						$("[name='pitchInfoname']").css("color", "#555")
										.css("font-weight", "normal");
						
						var imgs = $("[name='pitchInfoname']").children("img");
						
						_.each(imgs, function(item) {
							$(item).attr("src", "/src/assets/images/videos/list_un_play2.png")
						});
						
						var currentVideoTag = $("#" + currentId).children("img")[0];
						
						$(currentVideoTag).attr("src", "/src/assets/images/videos/list_play2.png");
						
						//영상 재생 
						/**
						 * 시간을 움직여 놓고 재생 순서를 지켜야한다. 
						 */
						$("#videoPlayNPause").text('정지');
						videoTrackRender(video, mainStartTime, calcedPlayTime, mainPitchTimeInfo);
						video.currentTime = mainStartTime;
						clearTimeout(setTimeoutWrapper);
						
						if(!pause) video.play(); 

						calcPlayTimeByHitResult(mainPitchTimeInfo);
						
						setTimeoutWrapper = setTimeout(function() {
							if(video.readyState != 4) {
								var newStartTime = video.currentTime;
								var timeGap = (video.currentTime - mainStartTime) *1000;
								var remainTime = calcedPlayTime - timeGap;
								if(timeGap == calcedPlayTime) {
									if(!pause && globalPitchTimeInfo.length != (index*1) + 1) {
										videoPlayAsync(video, (index * 1 + 1)); //네트워크 상태가 좋지 않아도 하나 넘어가버린다. 
									} else {
										videoPlayAsync(video, 0);
									}
								} else {
									videoAsyncReadyStateCheck(video, index, newStartTime, remainTime);
								}
							} else {
								if(!pause && globalPitchTimeInfo.length != (index*1) + 1) {
									videoPlayAsync(video, (index * 1 + 1)); //네트워크 상태가 좋지 않아도 하나 넘어가버린다. 
								} else if(!pause && globalPitchTimeInfo.length == (index*1) + 1) {
									videoSearch();
									videoPlayAsync(video, 0);
								}
							}
						}, calcedPlayTime);
					}
				}
			} else {
				var mainItem = globalPitchTimeInfo[index];
				mainStartTime = mainItem['pitchTime'] - mainItem['taggingGapTime'];
				if(taggingTimeSettingObj.hasOwnProperty(mainItem.gameRecordId)) {
					var inFromtTime = taggingTimeSettingObj[mainItem.gameRecordId]['frontTime'];
					mainStartTime -= inFromtTime;
				} else {
					mainStartTime -= frontTime;
				}
				//event에 의한 호출인 경우 
				if(mainStopTime != 0) video.currentTime = mainStopTime;
				else video.currentTime = mainStartTime;
				clearTimeout(setTimeoutWrapper);
				setTimeoutWrapper = setTimeout(function() {
					//영상이 재생된 부분을 제외하고 하나를 줄여서 전달한다. 
					if(!pause && globalPitchTimeInfo.length != index + 1) {
						videoPlayAsync(video, (index * 1 + 1));
					} else if(!pause && globalPitchTimeInfo.length == index + 1) {
						videoSearch();
						videoPlayAsync(video, 0);
					}
				}, mainRemainTime);
			}
		} catch(err) {
			videoPlayAsync(video, 0);
		}
	}
	
	function videoAsyncReadyStateCheck(video, index, startTime, remainTime) {
		//이전 재생 삭제
		clearTimeout(setTimeoutWrapper);
		video.currentTime = startTime;
		video.play();
		setTimeoutWrapper = setTimeout(function() {
			if(video.readyState != 4) {
				var newStartTime = video.currentTime;
				var timeGap = (video.currentTime - mainStartTime) * 1000;
				var remainTime = calcedPlayTime - timeGap;
				if(timeGap == calcedPlayTime) {
					if(!pause && pitchTimeData.length != (index*1) + 1) {
						videoPlayAsync(video, (index * 1 + 1)); //네트워크 상태가 좋지 않아도 하나 넘어가버린다. 
					}
				} else {
					videoAsyncReadyStateCheck(video, index, newStartTime, remainTime);
				}
			} else {
				videoPlayAsync(video, (index * 1 + 1));
			}
		}, remainTime);
	}
	
	function tableScrollAutoMove(id) {
		var index = id.split("_")[1];
		
		var idAreaHeight = $("#" + id).height();
		$("#pitchInfoDiv").scrollTop(index * idAreaHeight);
	}
	
	var cue1 = null;
	function videoTrackRender(video, mainStartTime, calcedPlayTime, pitchInfo) {
		var text = trackTextMake(pitchInfo);
		var mainVideoTrack = null;
		var videoTextTracks = video.textTracks;
		if(_.size(videoTextTracks) > 1) {
			var beforeTrack = videoTextTracks[_.size(videoTextTracks) - 2];
			var beforeTrack2 = videoTextTracks[_.size(videoTextTracks) - 1];
			beforeTrack.mode = 'disabled';
			beforeTrack2.mode = 'disabled';
		}
		
		mainVideoTrack = video.addTextTrack('subtitles', 'hiballtrack', 'kr');
		mainVideoTrack.mode = 'showing';
		
		if(cue1 == null) {
			cue1 = new VTTCue(mainStartTime, mainStartTime + (calcedPlayTime/1000), trackTextMake(pitchInfo));
		} else {
			cue1.endTime = 0;
			cue1 = new VTTCue(mainStartTime, mainStartTime + (calcedPlayTime/1000), trackTextMake(pitchInfo));
		}
		mainVideoTrack.addCue(cue1);
//		mainVideoTrack.cues[0].line = 13;
	}
	
	function trackTextMake(info) {
		var checkBase = info.checkBase;
		var gameDayFormat = info.gameDayFormat;
		var inning = info.inning;
		var inningTb = info.inningTb == 1 ? '말' : '초';
		var batterName = info.batterName;
		var pitcherName = info.pitcherName;
		var beforeStrikeCount = info.beforeStrikeCount;
		var beforeOutCount = info.beforeOutCount;
		var beforeBallCount = info.beforeBallCount;
		var ballCodeName = info.ballCodeName == null ? "" : info.ballCodeName;
		var ballSpeed = info.ballSpeed == null ? "0" : info.ballSpeed;
		var hitResultName = info.hitResultName == null ? "" : info.hitResultName;
		var beforeRunnerState = info.beforeRunnerState == null ? "" : Common.runnerStateName(info.beforeRunnerState);
		var result = "";
		if(checkBase < 1) {
			accumCheckBase = 0;
			result = "[" + gameDayFormat + "]  " + inning + "회" + inningTb + ")" + batterName + "vs" + pitcherName
						+ " " + beforeBallCount + "B-" + beforeStrikeCount +"S" + " " + beforeOutCount + "OUT"
						+ " " + ballCodeName + ", " + ballSpeed + "km"
						+ " " + hitResultName + ", " + beforeRunnerState;
		} else {
			result = "[" + gameDayFormat + "]  " + + inning + inningTb + ")" + batterName + "vs" + pitcherName
			+ " " + beforeBallCount + "B-" + beforeStrikeCount + "S"
			+ " " + beforeRunnerState + ", " + "견제";
		}
		
		
		return result;
	}
	
	function htmlSetUp() {
		var renderData = makeRenderData();
		var htmlFrame = _.template(MediaComponent);
		$("#" + appendTargetId).empty();
		$("#" + appendTargetId).html(htmlFrame(renderData.toJSON()));
	}
	
	function makeRenderData() {
		return new Backbone.Model({
			width : width,
			subWidth : width / 2,
			height : height
		});
	}
	
	function mediaSuccessHandler(collection, data){
		var mediaData = data.resultMap.pbpWithPitchTime;
		
		if(mediaData.length == 0) {
			alert("영상 데이터가 없습니다.");
		} else {
			initEntry(appendTargetId, mediaData, width, height);
			renderEntry();
		}
	}
	
	function makePlayList(playTimes) {
		$("#" + playListTagId).empty();
		$("#" + subPlayListTagId).empty();
		var loopCnt = 0;
		var firstItem = null;
		var downloadFilename = '영상제목없음';
		_.each(playTimes, function(item, index) {
			if(loopCnt == 0) {
				firstItem = item;
				loopCnt += 1;
			}
			if(item.length != 0) {
				downloadFilename = item[0]['downloadFilename']
			}
			index = makeVideoPath(index);
			
			$("#" + playListTagId).append("<option value="+ index +">" + downloadFilename + "</option>");
			$("#" + subPlayListTagId).append("<option disabled value="+ index +">" + downloadFilename + "</option>");
		});

		var video = document.getElementById(videoTagId);
		video.onerror = function(e) {
			clearTimeout(setTimeoutWrapper);
			var errVideoPath = $("#" + playListTagId).val();
			
			if(alertCnt <= 2) {
				video.src = errVideoPath;
				alertCnt+=1;
			} else {
				if(alertCnt > 2) alertCnt = 0;
				// alert(errVideoPathText + "영상 호출 시 오류가 발생 하여 목록에서 제거됩니다.")
				pitchTimeData = _.filter(pitchTimeData, function(item) {
					return item.videoPath != errVideoPath;
				});
				var immutablePlayPaths = getPlayPaths(immutablePitchTimeData);
				var playPaths = getPlayPaths(pitchTimeData);
				playListSelectTagRender(playPaths);
				videoKindAreaRender(immutablePlayPaths, playPaths);
				
				var videoValue = $("#" + playListTagId).val();
				globalPitchTimeInfo = globalPlayTimes[videoValue];
				
				
				var localPath = localPathMap[videoValue];
				
				if(localPath !== undefined) {
					video.src = localPath;	
				} else {
					alert('영상이 없습니다.');
				}
			}
		}

		video.onplay = function(e) {
			e.stopPropagation();
		}
		video.onloadstart = function(e) {
			video.play();
			e.stopPropagation();
		}
		
		return firstItem;
	}
		
	return mediaComponent;
});