define([
        'jquery','underscore','backbone', 'common',
        'text!../../html/template/hitCourseZoneChart.html',
        'jqueryNum', 'jqueryCanvas'
], function($, _, Backbone, Common, HitCourseZoneChart) {
	var model = Backbone.Model;
	var chartType = "spreadPoint";
	var chartName = "";
	var zoneClickEventHandler = null;
	var chartSize = 0;
	var chartData = null;
	
	var playgroundImgBorder=10;
    var playgroundImgBottom=35;
    var playgroundImgTop=41;
	
	var chartInitialHandler = function(viewObj, name, size, eventHandler, clickHandler){
		chartName = name;
		chartSize = size;
//		if (eventHandler != 'undefined'&& eventHandler != null) {
//			mouseEventHandler = eventHandler;
//		}
		if (clickHandler != 'undefined' && clickHandler != null) {
			clickEventHandler = clickHandler;
		}
		this.$el = viewObj;
		configGroundPadding(size);
	}
	
	var ZoneChartView = Backbone.View.extend({
		init:chartInitialHandler,
		render: function(data, type) {
			this.$el.empty();
//			chartType = type;
			chartData = data;
			var instanceModel = new model({
				chartName:chartName,
				size:chartSize
			});
			
			var chartTemplate = _.template(HitCourseZoneChart);
			this.$el.prepend(chartTemplate(instanceModel.toJSON()));
			
			var cateList = [];
			_.each($("[name='hitResult']"), function(item, index){
				cateList.push(item.id);
			});
			
			chartHandler(cateList);
			$("[name='hitResult']").on("click", clickEventHandler);
		},
		redrawChart : redrawChartHandler,
		clearChart:clearChart
	});
	
	function redrawChartHandler(type){
//		chartData = data;
//		chartType = type;
		chartHandler(type);
	}
	
	function configGroundPadding(size) {
		if (size == 80) {
			playgroundImgBorder = 2.4;
		    playgroundImgBottom = 8;
		    playgroundImgTop = 41;
		} else if (size == 200) {
			playgroundImgBorder = 6;
		    playgroundImgBottom = 20;
		    playgroundImgTop = 41;
		} else if (size == 350) {
			playgroundImgBorder = 10;
		    playgroundImgBottom = 35;
		    playgroundImgTop = 41;
		} else if (size == 450) {
			playgroundImgBorder = 14;
		    playgroundImgBottom = 45;
		    playgroundImgTop = 54;
		} 
	}
	
	function clearChart () {
		$("#" + chartName + "_hitZoneCanvas").clearCanvas();
		$("#" + chartName + "_hitZoneCanvas").removeLayers();
	}
	
	function chartHandler(list) {
		clearChart();
		drawSpreadChart(list);
	}
	
	var drawSpreadChart = function(list) {
		var x = 0;
		var y = 0;
		var hitPositionX = 0;
        var hitPositionY = 0;
		var gameRecordId = 0;
		var gridHitX = chartSize;
		var gridHitY = chartSize;
		
		var color = "";
		
		_.each(chartData, function(item){
			hitPositionX = item.hitCourseX;
			hitPositionY = item.hitCourseY;

			if(hitPositionX!=0 && hitPositionY!=0) {
				_.each(list, function(data){
					if(data == item.hitResultCode){
						color = chooseColorCode(item.hitResultCode);
		                x = ((hitPositionX)*((gridHitX/2)-playgroundImgBorder)/1000)+(gridHitX/2);
		                y = (gridHitY-playgroundImgBottom)-((hitPositionY/1000)*(gridHitY-playgroundImgBottom-playgroundImgTop));
		               
		                $("#" + chartName + "_hitZoneCanvas").drawArc({
		                	layer:true,
							fillStyle:color,
							x:x, y:y,
							radius:3,
							name: "hit_"+item.gameRecordId,
//							name: "hit_"+item.hitResultCode,
							oriColor:color,
							mouseover: function(layer) {
								$(this).animateLayer(layer, {
									radius : 5,
									strokeWidth : 2,
									strokeStyle : '#000000',
								}, 0);
								mouseEventHandler(layer, item, 'over');
							},
							mouseout: function (layer) {
								$(this).animateLayer(layer, {
									radius : 3,
									strokeWidth : 0
								}, 0);
								mouseEventHandler(layer, item, 'out');
							}
						});
	        		}
				});
			}
		});
	}
	
	var clickEventHandler = function(event){
		
		var eventClass = $(event.currentTarget).attr("class");
		if(eventClass == "active"){
			$(event.currentTarget).attr("class", "");
		}else{
			$(event.currentTarget).attr("class", "active");
		}
		
		var hitTypes = $("[name='hitResult']");
		var activeList = [];
		
		var numberCnt = 0;
		for (var i=0; i < hitTypes.length; i++) {
			if($(hitTypes[i]).attr("class").indexOf("active") > -1 ) {
				activeList[numberCnt] = $(hitTypes[i]).attr("id");
				numberCnt++;
			}
		}
		
		redrawChartHandler(activeList);
	}


	var mouseEventHandler = function (layer, item, type) {
		var contentHtml = "";
		var radiusValue = 3;
//		var beforeRunnerState = Common.runnerStateName(item.beforeDecidedBallCount);
		var ballSpeed = item.ballSpeed;
		var ballCodeName = Common.ballCodeName(item.ballCode);
		var strikeYN = item.strikeYn;
		var strikeYNValue ='';
		var hitResult = Common.hitResultName(item.hitResultCode);
		
		if(strikeYN=='Y') {
			strikeYNValue='스트라이크'
		} else if(strikeYN=='N') {
			strikeYNValue='볼'
		} else {
			strikeYNValue=''
		}
//		if(ballCodeName==null || ballCodeName=='') {
//			ballCodeName='';
//		} else {
//			ballCodeName = '('+ballCodeName+')';
//		}

//		contentHtml = "경기일자 :"+item.gameDay+'<br>';
//		contentHtml+= "상대팀 :"+item.pitcherTeamName+'<br>';
//		contentHtml+= "상대투수 :"+item.pitcherName+'<br>';
//		contentHtml+= "S :"+item.beforeStrikeCount+', B : '+ item.beforeBallCount+', Out : '+ item.beforeOutCount+'<br>';
//		contentHtml+= "타격결과 :"+hitResult+"<br/>";
//		contentHtml += "주자상황 :" + beforeRunnerState + '<br>';
//		contentHtml += "구속 :" + item.ballSpeed + 'Km<br>';
//		contentHtml += "구질 :"+ ballCodeName + '<br>';
//		contentHtml += "S/B :" + strikeYNValue + '<br>';
		$("#date").html(item.gameDay);
		$("#detailBatterName").html(item.batterName);
		$("#detailPitcherName").html(item.pitcherName);
		$("#ballSpeed").html(ballSpeed+'km');
		$("#ballType").html(ballCodeName);
		$("#hitResult").html(hitResult);
//		$("#runnerStatus").html(beforeRunnerState);
		$("#opponentTeam").html(item.opponentTeamName);
		$("#ballCount").html(item.beforeDecidedBallCount);
		if(type=='over'){
			$("#"+item.gameRecordId).children().css("border-style", 'solid');
			$("#"+item.gameRecordId).children().css("border-width", '2px 0px');
			$("#"+item.gameRecordId).children().css("border-color", '#1E75E9');
		}else{
			$("#"+item.gameRecordId).children().css("border", '');
		}
//		if(type=='over') {
//			radiusValue = 7;
//			$('#hittedInfoDisplay').attr("style", "background-color:rgba(255,255,255,0.5)");
//		}
//		else {
//			radiusValue = 3;
//			contentHtml = '';
//			$('#hittedInfoDisplay').attr("style", "");
//		}
//		$('#hittedInfoDisplay').html(contentHtml);
	}

	function chooseColorCode(code) {
		var colorCode = 'RGB(0,0,0)';
		if (code == 6601) {//파울
			colorCode = 'RGB(6,186,65)';
		} else if (code==6602) {//플라이아웃
			colorCode = 'RGB(255,132,0)';
		} else if (code==6603) {//삼진아웃
			colorCode = 'RGB(188,58,58)';
		} else if (code==6604) {//1루타
			colorCode = 'RGB(46,218,254)';
		} else if (code==6605) {//땅볼아웃
			colorCode = 'RGB(255,193,0)';
		} else if (code==6607) {//2루타
			colorCode = 'RGB(0,166,255)';
		} else if (code==6610) {//3루타
			colorCode = 'RGB(0,99,245)';
		} else if (code==6613) {//홈런
			colorCode = 'RGB(255,24,0)';
		}
	
		return colorCode;
	}
	
	return ZoneChartView;
});