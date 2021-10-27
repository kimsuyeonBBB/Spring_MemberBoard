define([
        'jquery','underscore','backbone','common',
        'text!../html/statChart.html', 'c3', 'd3'
], function($, _, Backbone, Common, StatChart, C3, D3) {
	var chartObj = null;
	var chartData = null;
	var config = null;
	var hidenAxis = new Array();
	var showingAxis = "";
	
	var yAxisLabel = function (key) {
		var label = "도루시도율";
		if (key =="stealAttemptRate") label = "도루시도율";
		else if (key =="stealFailureRate") label = "도루저지율";
		else if (key =="stealSuccessRate") label = "도루허용율";
		else if (key =="stealAttemptDivided9") label = "도루시도/9";
		else if (key =="stealSuccessDivided9") label = "도루허용/9";
		else if (key =="catcherAvgEr") label = "포수평균자책점";
		else if (key =="catcherAvgSelectionPitcherBallCnt") label = "선발투수평균투구수";
		else if (key =="catcherAvgRescuePitcherBallCnt") label ="구원투수평균투구수"
		else if (key =="kRate") label = "포수삼진율";
		else if (key =="foGoRate") label = "FO/GO비율";
		else if (key =="standingRate") label = "스탠딩비율";
		else if (key =="contactRate") label = "컨텍트비율";
		else if (key =="swingRate") label = "헛스윙비율";
		else if (key =="wildPitchDividedGameCnt") label ="경기당폭투";
		else if (key =="fastBallDividedGameCnt") label ="경기당패스트볼";
		
		return label;
	}
	
	var chartInit = function(conf) {
		if (typeof conf !="undefined"&& conf != null) {
			config = conf;
		} else {
			config = new Object({
				height:300,
				width:950,
				chartType:"bar",
				xAxis:"batterTeamName",
				yAxis:["stealAttemptRate"],
				xAxisLabel: "선수 명단",
				yAxisLabel: {stealAttemptRate:"도루시도율"},
				chartTitle:"그래프",
				chartColor:"#000000",
				data:null,
				sortBy:"stealAttemptRate",
				orderBy:"desc"
			});
		}
	}
	
	var StatChartView = Backbone.View.extend ({
		chartInit:chartInit,
		render: function(view) {
			makeChartFrame(view);
			hidenAxis.push(config.sortBy);
			showingAxis = config.sortBy;
			drawChart();
		}, 
		redrawChart:loadingChartData,
		changeShownChartData:changeShownChartData
	});
	
	function makeChartFrame(view) {
		view.html(StatChart);
	}
	
	function changeShownChartData(data, key) {
		config.data = data;
		loadingChartData(key);
	}
	
	function loadingChartData(key) {
		var drawnData = makeDrawnDataSet(config.data, key);
		chartObj.axis.labels({y:chooseLabel(key)});
		
		if (showingAxis!=key)
			chartObj.hide(showingAxis);
		
		if (_.contains(hidenAxis, key)){
			chartObj.show([key]);
		} else {
			hidenAxis.push(key);
		}
		chartObj.load ({
			columns:drawnData
		});
		
		showingAxis = key;
	}
	
	function chooseLabel(key) {
		var label = "";
		var label = config.yAxisLabel[key];
		return label;
	}
	
	function drawChart() {
		var drawnData = makeDrawnDataSet(config.data, config.sortBy);
		
		chartObj = C3.generate({
			bindto: "#bar_chart",
			size: {
				height:config.height,
				width:config.width
			},
		    data: {
		    	x : 'x',
		        columns: drawnData,
		    	color :function(color, d) {
		    		color = config.chartColor;
		    		return color;
		    	},
		        type: config.chartType,
		        labels:true 
		    },
		    axis:{
		    	x:{
		    		type:'category',
		    		label: {
		    			text : config.xAxisLabel,
		    			position : 'outer-center'
		    		}
		    	},
		    	y: {
		    		label:{
		    			text:chooseLabel(config.sortBy),
		    			position:'outer-middle'
		    		}
		    	}
		    },
		    legend: {
		    	show:false,
		    	position:"inset"
		    },
		    tooltip: {
		    	show:true,
		    	format: {
		    		name:function(name, ratio, id, index) {
		    			return config.yAxisLabel[name];
		    		}
		    	}
		    }
		});
	}
	
	function makeDrawnDataSet(data, criterion) {
		var orderBy = config.orderBy;
		var matrix = new Array();
		var categories = new Array();
		var rows = new Array();
		categories.push("x");
		rows.push(criterion);
		
		var sortedData = _.sortBy(data, function(x){
			var compare = "";
			if (criterion == 'hit') {
				compare = x.b1b+x.b2b+x.b3b;
			} else {
				compare = x[criterion];
			}
			
			if (orderBy == "desc") {
				compare*=-1
			}
			
			return compare;
		});
		
		_.each(sortedData, function(item){
			categories.push(item[config.xAxis]);
			if (criterion=='hit') {
				rows.push(item.b1b+item.b2b+item.b3b);
			} else {
				rows.push(item[criterion]);
			}
		});
		
		matrix.push(categories);
		matrix.push(rows);
		return matrix;
	}
	
	function configColorForVal(data, key, tn) {
		var keyVal = data[tn][key];
		var color = "#00ff00";
		_.each(data, function(item, idx){
			if (idx != tn) {
				if (item[key] < keyVal) {
					color = "rgba(255, 0, 0, 0.8)";
				} else if (item[key]>keyVal){
					color = "rgba(0,0,255, 0.8)";
				};
			}
		});
		return color;
	}
	
	return StatChartView;
});
