define([
        'jquery', 'underscore', 'backbone', 'common', 'calcstat',
        'text!../html/pitchNHitChart.html',
        'text!../html/pitchDetail.html',
        './template/hitCourseZoneChart',
        './template/pitchZoneChart',
        'bootstrap'
], function($, _,Backbone, Common, CalcStat, PitchNHitChartView, PitchDetailView, HitCourseZoneChart, PitchZoneChart) {
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
	
	var configuration = function(viewObj, name, size, notFilterData, gridData) {
		this.$el = viewObj;
		pitchZoneChart = new PitchZoneChart();
		hitCourseZoneChart = new HitCourseZoneChart();
		chartName = name;
		chartSize = size;
		wholeData = notFilterData;
		
		gridTitles = new Array();
		_.each(gridData, function(item, title){
			var gridTitle = new Object();
			gridTitle.title = title;
			gridTitle.id = item.id;
			gridTitles.push(gridTitle);
		});
		
		configGroundPadding(size);
	}
	var ballCountForZone = null;
	var PitchNHitChart = Backbone.View.extend({
		config:configuration,
		render:function(data) {
			
			zoneId = null;
			zoneRel = null;
			
			initData = data;	//처음 전체데이터
			ballCountForZone = CalcStat.calcPitchingCntForZone(data);
			
			var model = new Backbone.Model({
				gridTitle:gridTitles
			});
			
			var template = _.template(PitchNHitChartView)
			this.$el.html(template(model.toJSON()));
			
			if(typeof initData !='undefined' && initData.length != 0) {
				var battingHand = initData[0].battingHand;
			}
			
			pitchZoneChart.init($("#pitchZoneChart"), chartName, chartSize, battingHand, mouseEventHandler, zoneClickEventHandler, pitchZoneEventHandler);
			hitCourseZoneChart.init($("#hitCourseZoneChart"), chartName, chartSize, mouseEventHandler);
			
			this.initView();
			
			var initArr = ["contact"];
			
			chartHandler(initArr);
		}, 
		redrawChart:redrawChartHandler,
		externalEvtHandler:externalEventHandler,
		initView: function() {
			$("[name='hitType']").on("click", mainFilterChangeHandler);
			$("[name='hitResult']").on("click", subFilterChangeHandler);
			$("#hitCate").on("click", hitCateEventHandler);
			$("#outCate").on("click", outCateEventHandler)
			$("#categoryBtn").on("change", this.externalEvtHandler);
			
			filterHandler("contact");
		}
	});

	var chartHandler = function (mainClass, subClass) {
		
		displayData = dataFilter(mainClass, subClass);
		
		if(zoneId != null && zoneRel != null){
			pitchZoneChart.render(displayData);
			
			$("[name='"+chartName+"_pitchZone']").css('opacity', 0.3);
			$("#"+zoneId).css('opacity', 1);
			$("[name='"+chartName+"_pitchZone']").off("mouseenter");
			$(".pitchZoneArea").off("mouseleave");
			
			var cateType = [];
			$("[name='hitResult']:checked").each(function(){
				cateType.push($(this).val());
			});
			
			dataCalCulate(zoneRel);
		}else{
			hitCourseZoneChart.render(displayData);
			pitchZoneChart.render(displayData);
			$("#tableDetail").html("총 투구수 : "+initData.length);
			$("[name='"+chartName+"_pitchZone']").css('opacity', 1);
		}
	}

	function redrawChartHandler(data, selectedCategory) {
		
		var hitTypes = $("[name='hitType']");
		var activeElem = "";
		
		$("#categoryBtn").val(selectedCategory);
		for (var i=0; i<hitTypes.length; i++) {
			if ($(hitTypes[i]).attr("checked").indexOf("checked")>-1) {
				activeElem = $(hitTypes[i]).attr("id");
				break;
			}
		}
		initData = data;
		baseData = data;
		
		if (activeElem!="contact") {
			chartHandler(activeElem);
		} else {
			subFilterChangeHandler();
		}
	}
	
	var externalEventHandler = function (event) {
		console.log("externalEventHandler");
	}
	
	function filterHandler(type) {
		if(typeof type == "string"){
			var type = type.split(",");
		}
		
		$("[name='hitType']").attr("checked", false);
		
		_.each(type, function(item){
			$("#"+item).attr("checked", true);
		});
		
		if (type.includes("contact") && type.length == 1) {
			$("#hitCate").prop("disabled", false);
			$("#outCate").prop("disabled", false);
			$("#hitCate").prop("checked", true);
			$("#outCate").prop("checked", true);
			$("[name='hitResult']").prop("disabled", false);
			$("[name='hitResult']").prop("checked", true);
		} else if(type.includes("contact") && type.length != 1){
			$("#hitCate").prop("disabled", false);
			$("#outCate").prop("disabled", false);
			$("[name='hitResult']").prop("disabled", false);
		} else {
			$("#hitCate").prop("disabled", true);
			$("#outCate").prop("disabled", true);
			$("#hitCate").prop("checked", false);
			$("#outCate").prop("checked", false);
			$("[name='hitResult']").prop("disabled", true);
			$("[name='hitResult']").prop("checked", false);
		}
	}
	
	function hitCateEventHandler(event) {
		var isChecked = $(event.currentTarget).is(":checked");
		var hitResult = $("[name='hitResult']");

		_.each(hitResult, function(item){
			var val = $(item).val();
			if(val == '6604'||val == '6607'||val == '6610'||val == '6613') {
				$(item).prop('checked', isChecked);
			}
		});
		
		subFilterChangeHandler();
	}
	
	function outCateEventHandler(event) {
		var isChecked = $(event.currentTarget).is(":checked");
		var hitResult = $("[name='hitResult']");

		_.each(hitResult, function(item){
			var val = $(item).val();
			if(val == '6605'||val == '6602') {
				$(item).prop("checked", isChecked);
			}
		});
		
		subFilterChangeHandler();
	}
	
	var mainFilterChangeHandler = function(event) {
		zoneId = null;
		zoneRel = null;
		
		var filterId = $("[name='hitType']:checked");
		var selectedHitTypeArr = [];
		
		_.each(filterId, function(item){
			selectedHitTypeArr.push(item.id); 
		});
		
//		var filterId = $(event.currentTarget).attr("id");
		filterHandler(selectedHitTypeArr);
		chartHandler(selectedHitTypeArr);
	}
	
	var subFilterChangeHandler = function(event) {
		var hitTypes = $("[name='hitType']:checked");
		var selectedHitType = []
		
		_.each(hitTypes, function(item){
			selectedHitType.push(item.id);
		});
		
		var hitResult = $("[name='hitResult']:checked");
		var checkedHitResult = [];
		var hit = 0;
		var out = 0;
		_.each(hitResult, function(item){
			var val = $(item).val();
			checkedHitResult.push(val);
			
			if (val=='6604'||val=='6607'||val=='6610'||val=='6613') {
				hit++;
			}
			if (val=='6605'||val=='6602') {
				out++;
			}
		});
		
		if (hit == 4) {
			$("#hitCate").prop("checked", true);
		} else {
			$("#hitCate").prop("checked", false);
		}
		if (out == 2) {
			$("#outCate").prop("checked", true);
		} else {
			$("#outCate").prop("checked", false);
		}

		chartHandler(selectedHitType, checkedHitResult)
	}
	
	var dataFilter = function(mainFilter, subFilter) {
		var filteredBySub = null;
		var filteredByMain = null;
		var finalData = null;
		
		filteredByMain = _.filter(initData, function(item){
			var isFilter = false;
			var hitResult = item.hitResultCode;
			var hitType = item.hitTypeCode;
			
			if (mainFilter.includes("standing") && (hitType==6301)){
				isFilter = true;
			} else if (mainFilter.includes("contact") && (hitType==6302||hitType==6306)){
				isFilter = true;
			} else if (mainFilter.includes("swing") && (hitType==6303||hitType==6304)){
				isFilter = true;
			}
			
			return isFilter;
		});
		
		if (typeof subFilter != "undefined") {
			finalData = _.filter(filteredByMain, function(item){
				if ($.inArray((item.hitResultCode).toString(), subFilter)>-1) {
					return true;
				}
				return false;
			});
		} else {
			finalData = filteredByMain;
		}
		
		finalData.mainFilLength = filteredByMain.length;
		finalData.classification = mainFilter;
		return finalData;
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

	function calcRateForBallCode(data, cate) {
		var rateNCount = new Object();
		var pitCountForBallCode = CalcStat.calcPitchingCntForBallCode(baseData);
		var rateForBallCode = CalcStat.calcRateForBallCode(data, pitCountForBallCode, cate);
		rateNCount ={"pitCountForBallCode":pitCountForBallCode, "rateForBallCode":rateForBallCode};
		
		return rateNCount;
	}

	var mouseEventHandler = function(layer, item, type) {
		var x = 0;
		var y = 0;
		
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
			
			pitZoneCanvas.animateLayer(symLayer, {
				fillStyle: color,
				x: x, y: y,
				radius: radius
			});
			if(type=="over"){
				$("#batter").html(item.batterName);
				$("#pitcher").html(item.pitcherName);
			}
		}
	}
	
	var zoneClickEventHandler = function (event) {
		if(event.type == "click"){
			if(zoneId == $(event.currentTarget).attr("id")){
				$("[name='"+chartName+"_pitchZone']").on("mouseenter", zoneClickEventHandler);
				$(".pitchZoneArea").on("mouseleave", pitchZoneEventHandler);
				$("[name='"+chartName+"_pitchZone']").fadeTo(200, 1);
				zoneId = $(event.currentTarget).attr("id");
				zoneRel = $(event.currentTarget).attr("rel");
			}else{
				$("[name='"+chartName+"_pitchZone']").off("mouseenter");
				$(".pitchZoneArea").off("mouseleave");
				$("[name='"+chartName+"_pitchZone']").fadeTo(200, 0.3);
				$(event.currentTarget).fadeTo(200, 1);
				zoneId = $(event.currentTarget).attr("id");
				zoneRel = $(event.currentTarget).attr("rel");
			}
		}else if(event.type =="mouseenter"){
			$("[name='"+chartName+"_pitchZone']").fadeTo(10, 0.3);
			$(event.currentTarget).fadeTo(10, 1);
		}
		
		var zoneName = $(event.currentTarget).attr('rel');
		dataCalCulate(zoneName);
		
//		var filteredData = CalcStat.filteringDataByZoneCode(displayData, zoneName);
//		var zoneFullData = CalcStat.filteringDataByZoneCode(initData, zoneName);
//		var pitCntForBCInZfd = CalcStat.calcPitchingCntForBallCode(zoneFullData);
//		var rateForBallCode = CalcStat.calcRateForBallCode(filteredData, pitCntForBCInZfd, cateType);
//		var ballCodeName = "";
//		
//		_.each(pitCntForBCInZfd, function(item, key){
//			var ballCode = Common.ballCodeMultiName(key);
//			var rateVal = rateForBallCode[ballCode.eng];
//
//			$("#"+ballCode.eng+"_ori").html(((item/zoneFullData.length)*100).toFixed(1)+"%<br>("+item+"/"+zoneFullData.length+")");
//			if (typeof rateVal!= "undefined") {
//				$("#"+ballCode.eng).html(rateVal+"%<br>("+rateForBallCode[ballCode.eng+"_operation"]+")");
//			} else {
//				$("#"+ballCode.eng).html("0.0%<br>(0/0)");
//			}
//		});
//		hitCourseZoneChart.render(filteredData, chartSize);
		
//		var model = new Backbone.Model({
//			rateForBallCode:rateForBallCode
//		});
//		
//		var template = _.template(PitchDetailView);
//		$("#pitchZoneDetail").html(template(model.toJSON()));
	}
	
	var pitchZoneEventHandler = function (event) {
		$("#tableDetail").html("총 투구수 : "+initData.length);
		hitCourseZoneChart.render(displayData, chartSize);
		$(".ballRecord").empty().html("&nbsp;<br>&nbsp;");
		$("[name='"+chartName+"_pitchZone']").fadeTo(200, 1);
	}
	
	function dataCalCulate(zoneName){
		var cateType = [];
		$("[name='hitResult']:checked").each(function(){
			cateType.push($(this).val());
		});
		
		var filteredData = CalcStat.filteringDataByZoneCode(displayData, zoneName);
		var zoneFullData = CalcStat.filteringDataByZoneCode(initData, zoneName);
		var pitCntForBCInZfd = CalcStat.calcPitchingCntForBallCode(zoneFullData);
		var rateForBallCode = CalcStat.calcRateForBallCode(filteredData, pitCntForBCInZfd, cateType);
//		titleData['value'] = zoneFullData.length;
//		titleData['rate'] = zoneFullData.length/initData.length;
		
		var ballCodeName = "";
		
		_.each(pitCntForBCInZfd, function(item, key){
			var ballCode = Common.ballCodeMultiName(key);
			var rateVal = rateForBallCode[ballCode.eng];

			$("#"+ballCode.eng+"_ori").html(((item/zoneFullData.length)*100).toFixed(1)+"%<br>("+item+"/"+zoneFullData.length+")");
			if (typeof rateVal!= "undefined") {
				$("#"+ballCode.eng).html(rateVal+"%<br>("+rateForBallCode[ballCode.eng+"_operation"]+")");
			} else {
				$("#"+ballCode.eng).html("0.0%<br>(0/0)");
			}
		});
		
//		$("#tableDetail").html("총 투구수 : "+initData.length+"&nbsp; 존 투구수 : "+titleData.value+"&nbsp; 비율 : "+(titleData.rate).toFixed(3));
		$("#tableDetail").html("총 투구수 : "+initData.length);
		hitCourseZoneChart.render(filteredData, chartSize);
	}
	
	return PitchNHitChart;
});