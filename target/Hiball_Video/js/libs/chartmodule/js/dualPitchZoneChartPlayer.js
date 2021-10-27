define([
        'jquery', 'underscore', 'backbone', 'common', 'calcstat',
        'text!../html/dualPitchZoneChart.html',
        './template/pitchZoneChart'
], function($, _,Backbone, Common, CalcStat, DualPitchZoneChart, PitchZoneChart) {
	var fPitchZoneChart = null;
	var sPitchZoneChart = null;
	var chartType = "rateChart";
	var chartSize = 0;
    var pinZoneOnOff = "off";
    var pitchingHand = "";
    var battingHand = "";
	var firstChartName = "";
	var secondChartName = "";
	var chartData = "";
	var pinOnOff = "off";
   
	var chartInitialHandler = function(viewObj, fChartName, sChartName, size, bHand, type, eventHandler) {
		this.$el = viewObj;
		fPitchZoneChart = new PitchZoneChart();
		sPitchZoneChart = new PitchZoneChart();
		firstChartName = fChartName;
		secondChartName = sChartName;
		battingHand = bHand;
		chartSize = size;
		
		if (typeof eventHandler != "undefined" && eventHandler != null) {
			zoneClickEventHandler = eventHandler;
		}
		
		if (typeof type !="undefined"&& type!=null) {
			chartType = type;
		}
	}
	
	var DualPitchZoneChartView = Backbone.View.extend({
		init:chartInitialHandler,
		render:function(summaryData, cData, onOff) {
			chartData = cData;
			var summaryTableHtml = displaySummary(summaryData);
			
			var template = _.template(DualPitchZoneChart);
			this.$el.html(template(summaryData));
			$("#wholeDisplay").on("click", displayWholeData);
			$("#summaryList").html(summaryTableHtml);
			chartHandler(chartData, chartType);
		}
	});
	
	var zoneClickEventHandler = function (event) {
		if(event.type == 'click'){
			$("[name='"+firstChartName+"_pitchZone']").off("mouseenter");
			$("[name='"+secondChartName+"_pitchZone']").off("mouseenter");
			$(".pitchZoneArea").off("mouseleave");
		}
		$("[name='"+firstChartName+"_pitchZone']").fadeTo(20, 0.3);
		$("[name='"+secondChartName+"_pitchZone']").fadeTo(20, 0.3);
		var selectedZoneId = ($(event.currentTarget).attr("id"));
		var selectedZone = ($(event.currentTarget).attr("rel"));
		
		var tmpArr = selectedZoneId.split("_");
		var oppZoneId = "";
		if (tmpArr[0]==firstChartName) {
			oppZoneId = secondChartName+"_"+tmpArr[1]+"_"+tmpArr[2];
		} else {
			oppZoneId = firstChartName+"_"+tmpArr[1]+"_"+tmpArr[2];
		}
		
		$("#"+selectedZoneId).fadeTo(20, 1.0);
		$("#"+oppZoneId).fadeTo(20, 1.0);
		
		var player = null;
		
		if(typeof chartData.playerPattern == 'undefined'){
			player = indexBySbZone(chartData.teamPattern);
		}else{
			player = indexBySbZone(chartData.playerPattern);
		}

		var all = indexBySbZone(chartData.allPattern);
		
		var pZoneData = player[selectedZone];
		var aZoneData = all[selectedZone];
		
		if (typeof pZoneData!='undefined'&& pZoneData != null) {
			$("#playerS").html(pZoneData.sJudgeRate+"%<br/>("+pZoneData.sJudgeCnt+"/"+(pZoneData.sJudgeCnt+pZoneData.bJudgeCnt)+")");
			$("#playerB").html(pZoneData.bJudgeRate+"%<br/>("+pZoneData.bJudgeCnt+"/"+(pZoneData.sJudgeCnt+pZoneData.bJudgeCnt)+")");
		} else {
			$("#playerS").html("0.0%<br/>(0/0)");
			$("#playerB").html("0.0%<br/>(0/0)");
		}
		
		if (typeof aZoneData!='undefined'&&aZoneData!=null) {
			$("#allS").html(aZoneData.sJudgeRate+"%<br/>("+aZoneData.sJudgeCnt+"/"+(aZoneData.sJudgeCnt+aZoneData.bJudgeCnt)+")");
			$("#allB").html(aZoneData.bJudgeRate+"%<br/>("+aZoneData.bJudgeCnt+"/"+(aZoneData.sJudgeCnt+aZoneData.bJudgeCnt)+")");
		} else {
			$("#allS").html("0.0%<br/>(0/0)");
			$("#allB").html("0.0%<br/>(0/0)");
		}
	} 
	
	var displayWholeData = function(event) {
		$("[name='"+firstChartName+"_pitchZone']").fadeTo("fast", 1.0).off("mouseenter").on("mouseenter", zoneClickEventHandler);
		$("[name='"+secondChartName+"_pitchZone']").fadeTo("fast", 1.0).off("mouseenter").on("mouseenter", zoneClickEventHandler);
		$(".pitchZoneArea").on("mouseleave", pitchZoneEventHandler);
		$("[name='sZoneVal']").empty();
	}
	
	var chartHandler = function (chartData, cateType) {
		firstChartName = 'playerPitchPattern';
		secondChartName = 'allPitchPattern';

		var drawData = null;
		
		if(typeof chartData.playerPattern == 'undefined'){
			drawData =indexBySbZone(chartData.teamPattern);
		}else{
			drawData =indexBySbZone(chartData.playerPattern);
		}
		
		drawData["gradeType"] = "judge";
		fPitchZoneChart.init($("#pitchZoneChart"), firstChartName, chartSize, "S", null, zoneClickEventHandler, pitchZoneEventHandler);
		fPitchZoneChart.render(drawData, "rateChart");

		var drawDataForAll =indexBySbZone(chartData.allPattern);
		
		drawDataForAll["gradeType"] = "judge";
		sPitchZoneChart.init($("#allPlayerPitchZoneChart"), secondChartName, chartSize, "S", null, zoneClickEventHandler, pitchZoneEventHandler);
		sPitchZoneChart.render(drawDataForAll, "rateChart");
	}
	
	function displaySummary(data) {
		var displayData = calcTotalSummary(data.chartData);
		//configTitle();
		
		var summaryHtml = "";
		
		if(typeof displayData.playerPattern == 'undefined'){
			summaryHtml  += "<table class='height20'>";
			summaryHtml  += "	<tr>";
			summaryHtml  += "		<th colspan='4' class='text-center'>스트라이크(S)/볼(B) 판정 요약</th>";
			summaryHtml  += "	</tr>";
			summaryHtml  += "	<tr>";
			summaryHtml  += "		<th colspan='2'>&nbsp;</th>";
			summaryHtml  += "		<th>선수 판정 비율</th>";
			summaryHtml  += "		<th class='text-center'>모든 선수<br>판정 비율</th>";
			summaryHtml  += "	</tr>";
			summaryHtml  += "	<tr>";
			summaryHtml  += "		<th rowspan='2' width='20%'>전체Zone</th>";
			summaryHtml  += "		<th width='20%'>S 판정</th>";
			summaryHtml  += "		<td width='30%' align='center'>"+displayData['teamPattern'].sJudgeRate+"%<br/>";
			summaryHtml  += "				("+displayData['teamPattern'].sJudgeCnt+"/"+(displayData['teamPattern'].sJudgeCnt+displayData['teamPattern'].bJudgeCnt)+")";
			summaryHtml  += "		</td>";
			summaryHtml  += "		<td width='30%' align='center'>"+displayData['allPattern'].sJudgeRate+"%<br/>";
			summaryHtml  += 				"("+displayData['allPattern'].sJudgeCnt+"/"+(displayData['allPattern'].sJudgeCnt+displayData['allPattern'].bJudgeCnt)+")";
			summaryHtml  += "		</td>";
			summaryHtml  += "	</tr>";
			summaryHtml  += "	<tr>";
			summaryHtml  += "		<th>B 판정</th>";
			summaryHtml  += "		<td align='center'>"+displayData['teamPattern'].bJudgeRate+"%<br/>";
			summaryHtml  += "			("+displayData['teamPattern'].bJudgeCnt+"/"+(displayData['teamPattern'].bJudgeCnt+displayData['teamPattern'].sJudgeCnt)+")";
			summaryHtml  += "		</td>";
			summaryHtml  += "		<td align='center'>"+displayData['allPattern'].bJudgeRate+"%<br/>";
			summaryHtml  += "			("+displayData['allPattern'].bJudgeCnt+"/"+(displayData['allPattern'].bJudgeCnt+displayData['allPattern'].sJudgeCnt)+")";
			summaryHtml  += "		</td>";
			summaryHtml  += "	</tr>";
			summaryHtml  += "	<tr>";
			summaryHtml  += "		<th rowspan='2'>Pin Zone</th>";
			summaryHtml  += "		<th>S 판정</th>";
			summaryHtml  += "		<td align='center'>"+displayData['teamPattern'].pinPointStrikeRate+"%<br/>";
			summaryHtml  += "			("+displayData['teamPattern'].pinPointStrikeCnt+"/"+(displayData['teamPattern'].pinPointStrikeCnt+displayData['teamPattern'].pinPointBallCnt)+")";
			summaryHtml  += "		</td>";
			summaryHtml  += "		<td align='center'>"+displayData['allPattern'].pinPointStrikeRate+"%<br/>";
			summaryHtml  += "			("+displayData['allPattern'].pinPointStrikeCnt+"/"+(displayData['allPattern'].pinPointStrikeCnt+displayData['allPattern'].pinPointBallCnt)+")";
			summaryHtml  += "		</td>";
			summaryHtml  += "	</tr>";
			summaryHtml  += "	<tr>";
			summaryHtml  += "		<th>B 판정</th>";
			summaryHtml  += "		<td align='center'>"+displayData['teamPattern'].pinPointBallRate+"%<br/>";
			summaryHtml  += "			("+displayData['teamPattern'].pinPointBallCnt+"/"+(displayData['teamPattern'].pinPointStrikeCnt+displayData['teamPattern'].pinPointBallCnt)+")";
			summaryHtml  += "		</td>";
			summaryHtml  += "		<td align='center'>"+displayData['allPattern'].pinPointBallRate+"%<br/>";
			summaryHtml  += "			("+displayData['allPattern'].pinPointBallCnt+"/"+(displayData['allPattern'].pinPointStrikeCnt+displayData['allPattern'].pinPointBallCnt)+")";
			summaryHtml  += "		</td>";
			summaryHtml  += "	</tr>";
			summaryHtml  += "</table>";
		}else{
			summaryHtml  += "<table class='height20'>";
			summaryHtml  += "	<tr>";
			summaryHtml  += "		<th colspan='4' class='text-center'>스트라이크(S)/볼(B) 판정 요약</th>";
			summaryHtml  += "	</tr>";
			summaryHtml  += "	<tr>";
			summaryHtml  += "		<th colspan='2'>&nbsp;</th>";
			summaryHtml  += "		<th>선수 판정 비율</th>";
			summaryHtml  += "		<th class='text-center'>모든 선수<br>판정 비율</th>";
			summaryHtml  += "	</tr>";
			summaryHtml  += "	<tr>";
			summaryHtml  += "		<th rowspan='2' width='20%'>전체Zone</th>";
			summaryHtml  += "		<th width='20%'>S 판정</th>";
			summaryHtml  += "		<td width='30%' align='center'>"+displayData['playerPattern'].sJudgeRate+"%<br/>";
			summaryHtml  += "				("+displayData['playerPattern'].sJudgeCnt+"/"+(displayData['playerPattern'].sJudgeCnt+displayData['playerPattern'].bJudgeCnt)+")";
			summaryHtml  += "		</td>";
			summaryHtml  += "		<td width='30%' align='center'>"+displayData['allPattern'].sJudgeRate+"%<br/>";
			summaryHtml  += 				"("+displayData['allPattern'].sJudgeCnt+"/"+(displayData['allPattern'].sJudgeCnt+displayData['allPattern'].bJudgeCnt)+")";
			summaryHtml  += "		</td>";
			summaryHtml  += "	</tr>";
			summaryHtml  += "	<tr>";
			summaryHtml  += "		<th>B 판정</th>";
			summaryHtml  += "		<td align='center'>"+displayData['playerPattern'].bJudgeRate+"%<br/>";
			summaryHtml  += "			("+displayData['playerPattern'].bJudgeCnt+"/"+(displayData['playerPattern'].bJudgeCnt+displayData['playerPattern'].sJudgeCnt)+")";
			summaryHtml  += "		</td>";
			summaryHtml  += "		<td align='center'>"+displayData['allPattern'].bJudgeRate+"%<br/>";
			summaryHtml  += "			("+displayData['allPattern'].bJudgeCnt+"/"+(displayData['allPattern'].bJudgeCnt+displayData['allPattern'].sJudgeCnt)+")";
			summaryHtml  += "		</td>";
			summaryHtml  += "	</tr>";
			summaryHtml  += "	<tr>";
			summaryHtml  += "		<th rowspan='2'>Pin Zone</th>";
			summaryHtml  += "		<th>S 판정</th>";
			summaryHtml  += "		<td align='center'>"+displayData['playerPattern'].pinPointStrikeRate+"%<br/>";
			summaryHtml  += "			("+displayData['playerPattern'].pinPointStrikeCnt+"/"+(displayData['playerPattern'].pinPointStrikeCnt+displayData['playerPattern'].pinPointBallCnt)+")";
			summaryHtml  += "		</td>";
			summaryHtml  += "		<td align='center'>"+displayData['allPattern'].pinPointStrikeRate+"%<br/>";
			summaryHtml  += "			("+displayData['allPattern'].pinPointStrikeCnt+"/"+(displayData['allPattern'].pinPointStrikeCnt+displayData['allPattern'].pinPointBallCnt)+")";
			summaryHtml  += "		</td>";
			summaryHtml  += "	</tr>";
			summaryHtml  += "	<tr>";
			summaryHtml  += "		<th>B 판정</th>";
			summaryHtml  += "		<td align='center'>"+displayData['playerPattern'].pinPointBallRate+"%<br/>";
			summaryHtml  += "			("+displayData['playerPattern'].pinPointBallCnt+"/"+(displayData['playerPattern'].pinPointStrikeCnt+displayData['playerPattern'].pinPointBallCnt)+")";
			summaryHtml  += "		</td>";
			summaryHtml  += "		<td align='center'>"+displayData['allPattern'].pinPointBallRate+"%<br/>";
			summaryHtml  += "			("+displayData['allPattern'].pinPointBallCnt+"/"+(displayData['allPattern'].pinPointStrikeCnt+displayData['allPattern'].pinPointBallCnt)+")";
			summaryHtml  += "		</td>";
			summaryHtml  += "	</tr>";
			summaryHtml  += "</table>";
		}
		
		return summaryHtml;
	}
	
	function calcTotalSummary(data) {
		var summaryTotalData = new Object();
		var sJudgeCnt = 0;
		var bJudgeCnt = 0;
		var sJudgeCntForPin = 0;
		var bJudgeCntForPin = 0;
		var sJudgeRate = 0.0;
		var bJudgeRate = 0.0;
		var sJudgeRateForPin = 0.0;
		var bJudgeRateForPin = 0.0;
		
		_.each(data, function(item, key){
			var tmpObj = new Object();
			sJudgeCnt = 0;
			bJudgeCnt = 0;
			sJudgeCntForPin = 0;
			bJudgeCntForPin = 0;
			
			_.each(item, function(subItem){
				sJudgeCnt += subItem.sJudgeCnt;
				bJudgeCnt += subItem.bJudgeCnt;
				sJudgeCntForPin += subItem.pinPointStrikeCnt;
				bJudgeCntForPin += subItem.pinPointBallCnt;
			});
			
			tmpObj['sJudgeCnt'] = sJudgeCnt;
			tmpObj['bJudgeCnt'] = bJudgeCnt;
			tmpObj['pinPointStrikeCnt'] = sJudgeCntForPin;
			tmpObj['pinPointBallCnt'] = bJudgeCntForPin;
			
			sJudgeRate = (sJudgeCnt/(sJudgeCnt+bJudgeCnt))*100;
			bJudgeRate = (bJudgeCnt/(sJudgeCnt+bJudgeCnt))*100;
			sJudgeRateForPin = (sJudgeCntForPin/(sJudgeCntForPin+bJudgeCntForPin))*100;
			bJudgeRateForPin = (bJudgeCntForPin/(sJudgeCntForPin+bJudgeCntForPin))*100;
			
			tmpObj['sJudgeRate'] = sJudgeRate.toFixed(1);
			tmpObj['bJudgeRate'] = bJudgeRate.toFixed(1);
			tmpObj['pinPointStrikeRate'] = sJudgeRateForPin.toFixed(1);
			tmpObj['pinPointBallRate'] = bJudgeRateForPin.toFixed(1);
			
			summaryTotalData[key] = tmpObj;
		});
		
		return summaryTotalData;
	}
	
	function indexBySbZone(data) {
		
		var tmpObj = _.indexBy (data, function(item){
			return item.pitchSbZoneCode;
		});
		
		return tmpObj;
	}
	
	var pitchZoneEventHandler = function (event) {
		$("[name='"+firstChartName+"_pitchZone']").fadeTo(200, 1);
		$("[name='"+secondChartName+"_pitchZone']").fadeTo(200, 1);
		$("[name='sZoneVal']").empty();
	}
	
	return DualPitchZoneChartView;
});