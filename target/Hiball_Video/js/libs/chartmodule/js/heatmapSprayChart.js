define([
        'jquery', 'underscore', 'backbone', 'common', 'calcstat',
        'text!../html/heatmapSprayChart.html',
        'text!../html/pitchDetail.html',
        './template/hitCourseZoneChart',
        './template/pitchZoneChart',
        'bootstrap'
], function($, _,Backbone, Common, CalcStat, HeatmpaSprayChart, PitchDetailView, HitCourseZoneChart, PitchZoneChart) {
	var mouseEventHandler = null;
	var zoneClickEventHandler = null;
	var pitchZoneChart = null;
	var hitCourseZoneChart = null;
	var chartName = "";
	var chartType = "";
	var chartSize = 0;
	var playgroundImgBorder = 10;
    var playgroundImgBottom = 35;
    var playgroundImgTop=41;
    var initData = null;
    var pinZoneOnOff = "off";
    var gridTitles = null;
    
    var displayData = null;
    var zoneId = null;
    var zoneRel = null;
   
    function configGroundPadding(size) {
		if (size == 80) {
			playgroundImgBorder = 2.4;
		    playgroundImgBottom = 8;
		    playgroundImgTop = 10;
		} else if (size == 200) {
			playgroundImgBorder = 6;
		    playgroundImgBottom = 20;
		    playgroundImgTop = 24;
		}  else if (size == 250) {
			playgroundImgBorder = 7.5;
		    playgroundImgBottom = 25;
		    playgroundImgTop = 30;
		} else if (size == 300) {
			playgroundImgBorder = 9;
		    playgroundImgBottom = 30;
		    playgroundImgTop = 36;
		}  else if (size == 350) {
			playgroundImgBorder = 10;
		    playgroundImgBottom = 35;
		    playgroundImgTop = 42;
		} else if (size == 450) {
			playgroundImgBorder = 14;
		    playgroundImgBottom = 45;
		    playgroundImgTop = 54;
		} 
	}
	
	var configuration = function(viewObj, name, size, notFilterData) {
		this.$el = viewObj;
		pitchZoneChart = new PitchZoneChart();
		hitCourseZoneChart = new HitCourseZoneChart();
		chartName = name;
		chartSize = size;
		wholeData = notFilterData;
		
		configGroundPadding(size);
	}
	var PitchNHitChart = Backbone.View.extend({
		config:configuration,
		render:function(data, filterType) {
			zoneId = null;
			zoneRel = null;
			
			var model = new Backbone.Model({
				battingHand : data.battingHand,
				gridTitle : gridTitles
			});
			
			var template = _.template(HeatmpaSprayChart)
			this.$el.html(template(model.toJSON()));
			
			if(typeof initData !='undefined' && data.length != 0) {
				var battingHand = data[0].battingHand;
			}
			
			pitchZoneChart.init($("#pitchZoneChart"), chartName, chartSize, battingHand, mouseEventHandler, zoneClickEventHandler, pitchZoneEventHandler);
			hitCourseZoneChart.init($("#hitCourseZoneChart"), chartName, 350, mouseEventHandler);
			
//			this.initView();
			chartHandler(data, filterType);
		},
//		redrawChart:redrawChartHandler,
		externalEvtHandler:externalEventHandler,
//		initView: function() {
//		}
	});

	var chartHandler = function (data, filterType) {
		if(zoneId != null && zoneRel != null){
			console.log('dddd');
			pitchZoneChart.render(data);
			
			$("[name='"+chartName+"_pitchZone']").css('opacity', 0.3);
			$("#"+zoneId).css('opacity', 1);
			$("[name='"+chartName+"_pitchZone']").off("mouseenter");
			$(".pitchZoneArea").off("mouseleave");
			
			var cateType = [];
			$("[name='hitResult']:checked").each(function(){
				cateType.push($(this).val());
			});
			
			dataCalCulate(zoneRel, cateType);
		}
		else{
			displayData = data;
			hitCourseZoneChart.render(data);
			var calculatedData = calculateDataForZone(data, filterType);
			pitchZoneChart.render(calculatedData);
			$("[name='"+chartName+"_pitchZone']").css('opacity', 1);
		}
		
		$("#tableDetail").html(" "+data.length);
	}
//
//	function redrawChartHandler(data, selectedCategory) {
//		var hitTypes = $("[name='hitType']");
//		var activeElem = "";
//		
//		$("#hitCate").val(selectedCategory);
//		for (var i=0; i<hitTypes.length; i++) {
//			if ($(hitTypes[i]).attr("class").indexOf("active")>-1) {
//				activeElem = $(hitTypes[i]).attr("id");
//				break;
//			}
//		}
//		initData = data;
//		baseData = data;
//		
//		if (activeElem!="contact") {
//			chartHandler(activeElem);
//		} else {
//			subFilterChangeHandler();
//		}
//	}
	
	var externalEventHandler = function (event) {
		console.log("externalEventHandler");
	}
	
	function chooseColorCode(code) {
		var colorCode = 'RGB(0,0,0)';
		if (code == 6601) {//파울
			colorCode = 'RGB(0,255,255)';
		} else if (code==6602) {//플라이아웃
			colorCode = 'RGB(123,123,123)';
		} else if (code==6603) {//삼진아웃
			colorCode = 'RGB(188,58,58)';
		} else if (code==6604) {//1루타
			colorCode = 'RGB(0,0,255)';
		} else if (code==6605) {//땅볼아웃
			colorCode = 'RGB(79,167,167)';
		} else if (code==6607) {//2루타
			colorCode = 'RGB(0,255,0)';
		} else if (code==6610) {//3루타
			colorCode = 'RGB(255,0,0)';
		} else if (code==6613) {//홈런
			colorCode = 'RGB(255,0,255)';
		}
	
		return colorCode;
	}

	function calculateDataForZone(data, filterType) {
		var rateNCount = new Object();
		var rateForZone = CalcStat.calcRateForZone(data, filterType);
		
		return rateForZone;
	}

	var mouseEventHandler = function(layer, item, type) {
		console.log(layer, item, type);
//		console.log('ddasdzxcasdqwe');
		var x = 0;
		var y = 0;
		var ballCodeName = Common.ballCodeName(item.ballCode);
		var hitResult = Common.hitResultName(item.hitResultCode);
		var beforeRunnerState = Common.runnerStateName(item.beforeRunnerState);
		
		var layerName = layer.name;
		var selectedRecord = layer.name.substring(layer.name.indexOf("_")+1);
		var radius = 3;
		var symLayer = null;
		var hitZoneCanvas = $("#"+chartName+"_hitZoneCanvas");
		var pitZoneCanvas = $("#"+chartName+"_pitZoneCanvas");
		var color = chooseColorCode(item.hitResultCode);
		
		if (type== "over") {
			radius = 7;
		} else {
			radius = 3;
		}
		
		if (layerName.indexOf("pit")>-1) {
			symLayer = hitZoneCanvas.getLayer("hit_"+selectedRecord)
			
			x = ((item.hitCourseX)*((chartSize/2)-playgroundImgBorder)/1000)+(chartSize/2);
            y = (chartSize-playgroundImgBottom)-((item.hitCourseY/1000)*(chartSize-playgroundImgBottom-playgroundImgTop));

            hitZoneCanvas.animateLayer(symLayer, {
            	fillStyle: color,
				x: x, y: y,
				radius: radius
			});
		} else if (layerName.indexOf("hit")>-1) {
			symLayer = pitZoneCanvas.getLayer("pit_"+selectedRecord);
			x = (item.pitchZoneX * chartSize / 1000);
			y = chartSize - (item.pitchZoneY * chartSize / 1000);
			console.log(symLayer);
			pitZoneCanvas.animateLayer(symLayer, {
				fillStyle: color,
				x: x, y: y,
				radius: radius
			});
			if(type=="over"){
				$("#batter").html(item.batterName);
				$("#pitcher").html(item.pitcherName);
				$("#ballSpeed").html(item.ballSpeed+"km");
				$("#ballType").html(ballCodeName);
				$("#hitResult").html(hitResult);
				$("#runnerStatus").html(beforeRunnerState);
			}
		}
	}
	
	var zoneClickEventHandler = function (event) {
		if(event.type == 'click'){
			$("[name='"+chartName+"_pitchZone']").off("mouseenter");
			$(".pitchZoneArea").off("mouseleave");
		}
		
		if(event.type == "click"){
			$("[name='"+chartName+"_pitchZone']").fadeTo(200, 0.3);
			$(event.currentTarget).fadeTo(200, 1);
			zoneId = $(event.currentTarget).attr("id");
			zoneRel = $(event.currentTarget).attr("rel");
		}else if(event.type =="mouseenter"){
			$("[name='"+chartName+"_pitchZone']").fadeTo(20, 0.3);
			$(event.currentTarget).fadeTo(20, 1);
		}
		
		var zoneName = $(event.currentTarget).attr('rel');
		dataCalCulate(zoneName);
	}
	
	var pitchZoneEventHandler = function (event) {
		hitCourseZoneChart.render(displayData, chartSize);
		$(".ballRecord").empty().html("&nbsp;<br>&nbsp;");
		$("[name='"+chartName+"_pitchZone']").fadeTo(200, 1);
	}
	
	function dataCalCulate(zoneName){
		var cateType = [];
		$("[name='hitResult']").each(function(){
			cateType.push($(this).val());
		});
		
		var filteredData = CalcStat.filteringDataByZoneCode(displayData, zoneName);
		console.log(filteredData);
		hitCourseZoneChart.render(filteredData, chartSize);
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
	
	return PitchNHitChart;
});