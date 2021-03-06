define([
        'jquery','underscore','backbone','common', 'statGrade',
        'text!../html/pitchingZoneChart.html',
        'jqueryNum', 'jqueryCanvas'
], function($, _, Backbone, Common, StatGrade, PitchingZoneChart) {
	var model = Backbone.Model;
	var chartType = "spreadPoint";
	var chartName = "";
	var zoneClickEventHandler = null;
	var chartSize = 0;
	var battingHand = "R";
	
	var chartInitialHandler = function(viewObj, name, size, bHand, eventHandler, clickEventHandler){
		if(name!=null && name!="") {
			chartName = name;
		} else {
			chartName = "pit";
		}
		chartSize = size;
		battingHand = bHand; 
		
		if (typeof eventHandler != 'undefined'&&eventHandler != null) {
			mouseEventHandler = eventHandler;
		}
		if (typeof clickEventHandler!="undefined"&&clickEventHandler!=null) {
			zoneClickEventHandler = clickEventHandler;
		}
		this.$el = viewObj;
	}
	
	var ZoneChartView = Backbone.View.extend({
		init:chartInitialHandler,
		render: function(data, type, pinZoneOnOff) {
			chartType = type;
			var instanceModel = new model({
				chartName:chartName,
				size:chartSize,
				battingHand:battingHand
			});
			
			var template = _.template(PitchingZoneChart);
			this.$el.html(template(instanceModel.toJSON()));
			
			pinZoneHandler(pinZoneOnOff);
			$("[name='"+chartName+"_pitchZone']").on("click", zoneClickEventHandler);
			chartHandler(data);
		}, redrawChart : function(data, type) {
			chartType = type;
			chartHandler(data);
		}, clearChart:clearZone
	});
	
	function chartHandler(data) {
		clearZone();
		drawZoneChart(data);
		$("#forZoneLegend").css("display", "");
	}
	
	function pinZoneHandler(btn) {
		if (btn=="on") {
			$(".pin_zone").show();
		} else if (btn == "off") {
			$(".pin_zone").hide();
		}
	}

	function clearZone() {
		$("#" + chartName + "_pitZoneCanvas").clearCanvas();
		$("[name='pitchZone']").empty();
	}
	
	var drawZoneChart = function(data) {
		$("#" + chartName + "_pitZoneCanvas").css("display", "none");
		$("#pitchedInfoDisplay").css("display", "none");
		var pitchZone = $("[name='"+chartName+"_pitchZone']");
		var gradeType = data.gradeType;

		_.each(pitchZone, function(item){
			var gradeObj = null;
			var fontColor = "#fff";
			var color = "";
			var id = $(item).attr("rel");
			var zoneData = data[id];
			
			if (typeof gradeType != "undefined"&& gradeType!=null) {
				if (gradeType=="standing"){
					gradeObj = StatGrade.standingRateGrade(zoneData);
				} else if (gradeType=="contact"){
					gradeObj = StatGrade.contactRateGrade(zoneData);
				} else if (gradeType=="swing"){
					gradeObj = StatGrade.swingRateGrade(zoneData);
				} else if (gradeType=="judge") {
					gradeObj = StatGrade.sbJudgedRateGrade(zoneData, id);
				}
			} else {
				gradeObj = StatGrade.hitRateGrade(zoneData);
			}
			
			
			$(item).html("<span style='font-weight:bold; color:"+gradeObj.fontColor+"'>"+gradeObj.value+"%</span>");
			$(item).css("background", gradeObj.color);
		});
	};

	var drawSpreadChart = function(data) {
		$("#" + chartName + "_pitZoneCanvas").css("display", "");
		var x = 0;
		var y = 0;

		var pitchPositionX = 0;
        var pitchPositionY = 0;
		var gameRecordId = 0;
        var gridX = chartSize;
        var gridY = chartSize;
        var color = "#";
		_.each(data, function(item){
			x = item.pitchZoneX;
			y = item.pitchZoneY;
			gameRecordId = item.gameRecordId;

			color = chooseColorCode(item.hitResultCode);
			if(x!=0 && y!=0 && x!=null && y!=null) {

				pitchPositionX = (x * gridX / 1000);
				pitchPositionY = gridY - (y * gridY / 1000);

				$("#" + chartName + "_pitZoneCanvas").drawArc({
					layer: true,
					fillStyle: color,
					x: pitchPositionX, y: pitchPositionY,
					radius: 3,
					name: "pit_"+gameRecordId,
					oriColor:color,
					mouseover: function(point) {
						$(this).animateLayer(point, {
							radius: 7,
						}, 'slow');
						mouseEventHandler(point, item, 'over');
					},
					mouseout: function (point) {
						$(this).animateLayer(point, {
							radius: 3,
							fillStyle: point.oriColor
						});
						mouseEventHandler(point, item, 'out');
					}
				});

			}
		});
	}
	
	var mouseEventHandler = function (item, type) {
		var contentHtml = "";
		var radiusValue = 3;
		var beforeRunnerState = Common.runnerStateName(item.beforeRunnerState);
		var ballSpeed = item.ballSpeed;
		var ballCodeName = Common.ballCodeName(item.ballCode);
		var strikeYN = item.strikeYn;
		var strikeYNValue ='';
		var hitResult = Common.hitResultName(item.hitResultCode);
		
		if(strikeYN=='Y') {
			strikeYNValue='???????????????'
		} else if(strikeYN=='N') {
			strikeYNValue='???'
		} else {
			strikeYNValue=''
		}
		if(ballCodeName==null || ballCodeName=='') {
			ballCodeName='';
		} else {
			ballCodeName = '('+ballCodeName+')';
		}

		contentHtml = "???????????? :"+item.gameDay+'<br>';
		contentHtml+= "????????? :"+item.pitcherTeamName+'<br>';
		contentHtml+= "???????????? :"+item.pitcherName+'<br>';
		contentHtml+= "S :"+item.beforeStrikeCount+', B : '+ item.beforeBallCount+', Out : '+ item.beforeOutCount+'<br>';
		contentHtml+= "???????????? :"+hitResult+"<br/>";
		contentHtml += "???????????? :" + beforeRunnerState + '<br>';
		contentHtml += "?????? :" + item.ballSpeed + 'Km<br>';
		contentHtml += "?????? :"+ ballCodeName + '<br>';
		contentHtml += "S/B :" + strikeYNValue + '<br>';
		if(type=='over') {
			radiusValue = 7;
			$('#pitchedInfoDisplay').attr("style", "background-color:rgba(255,255,255,0.5)");
		} else {
			radiusValue = 3;
			contentHtml = '';
			$('#pitchedInfoDisplay').attr("style", "");
		}
		
		$('#pitchedInfoDisplay').html(contentHtml);
	}

	var zoneClickEventHandler = function(event) {
		console.log("There is no setted event");
	}
	
	function chooseColorCode(code) {
		var colorCode = 'RGB(0,0,0)';
		if (code == 6601) {//??????
			colorCode = 'RGB(0,255,255)';
		} else if (code==6602) {//???????????????
			colorCode = 'RGB(123,123,123)';
		} else if (code==6603) {//????????????
			colorCode = 'RGB(188,58,58)';
		} else if (code==6604) {//1??????
			colorCode = 'RGB(0,0,255)';
		} else if (code==6605) {//????????????
			colorCode = 'RGB(79,167,167)';
		} else if (code==6607) {//2??????
			colorCode = 'RGB(0,255,0)';
		} else if (code==6610) {//3??????
			colorCode = 'RGB(255,0,0)';
		} else if (code==6613) {//??????
			colorCode = 'RGB(255,0,255)';
		}
	
		return colorCode;
	}
	
	return ZoneChartView;
});