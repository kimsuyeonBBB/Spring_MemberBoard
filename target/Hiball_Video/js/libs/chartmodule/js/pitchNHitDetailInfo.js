define([
        'jquery', 'underscore', 'backbone', 'common', 'calcstat',
        'text!../html/pitchNHitDetailInfo.html',
        './template/hitCourseZoneChart',
        './template/pitchZoneChart'
], function($, _,Backbone, Common, CalcStat, PitchNHitDetailInfo, HitCourseZoneChart, PitchZoneChart) {
	var mouseEventHandler = null;
	var zoneClickEventHandler = null;
	var pitchZoneChart = null;
	var hitCourseZoneChart = null;
	var chartName = "";
	var chartType = "";
	var chartSize = 0;
	var playgroundImgBorder=10;
    var playgroundImgBottom=35;
    var playgroundImgTop=41;
    var baseData = null;
    var displayData = null;
    var wholeData = null;
    var pinZoneOnOff = "off";
   
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
	
	var chartInitialHandler = function(viewObj, name, size, notFilterData) {
		this.$el = viewObj;
		pitchZoneChart = new PitchZoneChart();
		hitCourseZoneChart = new HitCourseZoneChart();
		chartName = name;
		chartSize = size;
		wholeData = notFilterData;
		
		configGroundPadding(size);
	}
	
	var PitchNHitDetailInfoView = Backbone.View.extend({
		init:chartInitialHandler,
		render:function(data,type) {
			baseData = data;
			chartType = type;
			
			this.$el.html(PitchNHitDetailInfo);
			
			displayWholeRateForBallCode(baseData, "hit");
			if(typeof baseData !='undefined' && baseData.length != 0) {
				var battingHand = baseData[0].battingHand;
			}
			pitchZoneChart.init($("#pitchZoneChart"), chartName, chartSize, battingHand, mouseEventHandler, zoneClickEventHandler);
			hitCourseZoneChart.init($("#hitCourseZoneChart"), chartName, chartSize, mouseEventHandler);
			
			chartHandler("hit");
			
			$("[name='visibleMethod']").on("change", visibleEventHandler);
			$("[name='hitfilter']").on("change", changeCategoryEventHandler);
		}, redrawChart:redrawChartHandler
	});
	
	function displayWholeRateForBallCode(data, cate) {
		var displayTitle = "";
		var subTitle = "";
		if (cate == "hit") {
			displayTitle = "구종 별 안타 비율";
			subTitle = "안타비율";
		} else if (cate=="hr") {
			displayTitle = "구종 별 홈런 비율";
			subTitle = "홈런비율";
		} else if (cate=="fo") {
			displayTitle = "구종 별 플라이아웃 비율";
			subTitle = "플라이아웃비율";
		} else if (cate=="go") {
			displayTitle = "구종 별 땅볼아웃 비율";
			subTitle = "땅볼아웃비율";
		} else if (cate=="standing") {
			displayTitle = "구종 별 스탠딩 비율";
			subTitle = "스탠딩비율";
		} else if (cate=="contact") {
			displayTitle = "구종 별 컨택트 비율";
			subTitle = "컨택트비율";
		} else if (cate=="swing") {
			displayTitle = "구종 별 스윙 비율";
			subTitle = "스윙비율";
		}
		
		var firstData = data[0];
		if (typeof firstData != 'undefined') {
			$(".nameInTitle").html(firstData.batterTeamName+" "+firstData.batterName+"")
		}
		
		var pitCountForBallCode = CalcStat.calcPitchingCntForBallCode(data);
		var rateForBallCode = CalcStat.calcRateForBallCode(data, pitCountForBallCode, cate); 
		
		var tableHtml = "";
		tableHtml =  "<table class='categoryList'>";
		tableHtml += "	<tr>";
		tableHtml += "		<th colspan='5'>";
		tableHtml += "			<strong id='cateTitle'>"+displayTitle+"</strong>";
		tableHtml += "		</th>";
		tableHtml += "	</tr>";
		tableHtml += "	<tr>";
		tableHtml += "		<td id='wholeData' class='oncursor notactive' colspan='5' align='center'>전체</td>";
		tableHtml += "	</tr>";
		tableHtml += "	<tr>";
		tableHtml += "		<th width='50' rowspan='2'>구종</th><th colspan='2'>전체</th><th colspan='2'>선택된 Zone</th>";
		tableHtml += "	</tr>";
		tableHtml += "	<tr>";
		tableHtml += "		<th width='20%'>구사비율</th><th width='20%'>"+subTitle+"</th><th width='20%'>구사비율</th><th width='20%'>"+subTitle+"</th>";
		tableHtml += "	</tr>";
		
		if (pitCountForBallCode.length != 0) {
			//console.log(rateForBallCode);
			_.each(pitCountForBallCode, function(item, key){
				var codeName = Common.ballCodeMultiName(key);
				tableHtml += "<tr>";
				tableHtml += "	<th>"+codeName.kor+"</th>";
				tableHtml += "  <td align='center'>"+((item/data.length)*100).toFixed(1)+"%<br/>("+item+"/"+data.length+")</td>";
				tableHtml += "	<td id='whole_"+codeName.eng+"' align='center'>"+rateForBallCode[codeName.eng]+"%<br/>("+rateForBallCode[codeName.eng+"_operation"]+")</td>"
				tableHtml += "	<td name='zoneDetail' id='"+codeName.eng+"_ori' align='center'>&nbsp;</td>";
				tableHtml += "	<td name='zoneDetail' id='"+codeName.eng+"' align='center'>&nbsp;</td>";
				tableHtml += "</tr>";
			});	
		} else {
			tableHtml += "	<tr>";
			tableHtml += "		<td colspan='3'>관련 데이터가 없습니다.</td>";
			tableHtml += "	</tr>";
		}
		
		tableHtml += "</table>";
		
		$("#rateDetailArea").html(tableHtml);
		$("#wholeData").on("click",displayWholeData);
	}
	
	function calcRateForBallCode(data, cate) {
		var rateNCount = new Object();
		var pitCountForBallCode = CalcStat.calcPitchingCntForBallCode(baseData);
		var rateForBallCode = CalcStat.calcRateForBallCode(data, pitCountForBallCode, cate);
		rateNCount ={"pitCountForBallCode":pitCountForBallCode, "rateForBallCode":rateForBallCode};
		
		return rateNCount;
	}
	
	function initDetailInfo() {
		$("[name='zoneDetail']").empty();
		$("[name='pitchDetail']").empty();
	}
	
	function calcRateForZone(data, cate) {
		var pitCountForZone = CalcStat.calcPitchingCntForZone(baseData);
		var rateForZone = CalcStat.calcRateForZone(data, pitCountForZone, cate); 
		return rateForZone;
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
		}
		$("#spotInfoDisplay").html(displaySelectedInfo(item, type));
	}
	
	function displaySelectedInfo(item, type) {
		$("[name='pitchDetail']").empty();
		var strikeYN = item.strikeYn;
		var strikeYNValue ='';
		
		if(strikeYN=='Y') {
			strikeYNValue='스트라이크'
		} else if(strikeYN=='N') {
			strikeYNValue='볼'
		} else {
			strikeYNValue=''
		}

		$("#gameDay").html(item.gameDay);
		$("#oppTeam").html(item.pitcherTeamName);
		$("#oppPitcher").html(item.pitcherName);
		$("#countStat").html(item.beforeBallCount+"B-"+item.beforeStrikeCount+"S"+' '+"Out:"+item.beforeOutCount);
		$("#hitResult").html(Common.hitResultName(item.hitResultCode));
		$("#runnerStat").html(Common.runnerStateName(item.beforeRunnerState));
		$("#pitchSpeed").html(item.ballSpeed + 'Km');
		$("#pitchType").html(Common.ballCodeName(item.ballCode));
		$("#judgedForPitch").html(strikeYNValue);
	}
	
	var zoneClickEventHandler = function (event) {
		initDetailInfo();
		var cateType = $("[name='hitfilter']:checked").val();
		$("[name='"+chartName+"_pitchZone']").fadeTo("fast", 0.3);
		$(event.currentTarget).fadeTo("fast", 1);
		var zoneName = $(event.currentTarget).attr('rel');
		
		var filteredData = CalcStat.filteringDataByZoneCode(displayData, zoneName);
		var zoneFullData = CalcStat.filteringDataByZoneCode(baseData, zoneName);
		var pitCntForBCInZfd = CalcStat.calcPitchingCntForBallCode(zoneFullData);
		
		var rateForBallCode = CalcStat.calcRateForBallCode(filteredData, pitCntForBCInZfd, cateType);
		var ballCodeName = "";
		
		_.each(pitCntForBCInZfd, function(item, key){
			var ballCode = Common.ballCodeMultiName(key);
			var rateVal = rateForBallCode[ballCode.eng];
			$("#"+ballCode.eng+"_ori").html(((item/zoneFullData.length)*100).toFixed(1)+"%<br/>"+item+"/"+zoneFullData.length);
			if (typeof rateVal!= "undefined") {
				$("#"+ballCode.eng).html(rateVal+"%<br/>("+rateForBallCode[ballCode.eng+"_operation"]+")");
			} else {
				$("#"+ballCode.eng).html("0.0%<br/>");
			}
		});
		hitCourseZoneChart.render(filteredData, chartSize);
	} 
	
	var visibleEventHandler = function(event) {
		chartType = $(event.currentTarget).val();
		displayData = baseData;
		
		var cate = $("[name='hitfilter']:checked").val();
		
		if (chartType == "rateChart") {
			pinZoneOnOff = "off";
		} else {
			pinZoneOnOff = "on";
		}
		chartHandler(cate);
	}
	
	var displayWholeData = function(event) {
		var category = $("[name='hitfilter']:checked").val();
		chartHandler(category);
	}
	
	var changeCategoryEventHandler = function(event) {
		var category = $(event.currentTarget).val();
		displayWholeRateForBallCode(baseData, category);
		chartHandler(category);
	}
	
	function redrawChartHandler(data) {
		var category = $("[name='hitfilter']:checked").val();
		baseData = data;
		displayWholeRateForBallCode(baseData, category);
		chartHandler(category);
	}
	
	var chartHandler = function (cateType) {
		var tmpData = null;
		initDetailInfo();
		dataFilter(cateType);
		if (chartType == "rateChart") {
			tmpData = calcRateForZone (displayData, cateType);
			tmpData["gradeType"] = cateType;
		} else {
			tmpData = displayData;
		}
		
		hitCourseZoneChart.render(displayData);
		pitchZoneChart.render(tmpData, chartType, pinZoneOnOff);
	}
	
	var dataFilter = function(filter) {
		displayData = _.filter(baseData, function(item){
			var isFilter = false;
			var hitResult = item.hitResultCode;
			var hitType = item.hitTypeCode;
			
			if (filter == "hit" && (hitResult==6604||hitResult==6607||hitResult==6610)){
				isFilter = true;
			} else if (filter == "hr" && (hitResult==6613)){
				isFilter = true;
			} else if (filter == "go" && (hitResult==6605)){
				isFilter = true;
			} else if (filter == "fo" && (hitResult==6602)){
				isFilter = true;
			} else if (filter == "standing" && (hitType==6301)){
				isFilter = true;
			} else if (filter == "contact" && (hitType==6302||hitType==6306)){
				isFilter = true;
			} else if (filter == "swing" && (hitType==6303||hitType==6304)){
				isFilter = true;
			}
			
			return isFilter;
		});
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
	
	return PitchNHitDetailInfoView;
});