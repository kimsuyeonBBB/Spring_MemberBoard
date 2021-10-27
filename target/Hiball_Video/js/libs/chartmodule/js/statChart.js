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
		var label = "타율";
		if (key =="avg") label = "타율";
		else if (key =="hit") label = '안타';
		else if (key =="hr") label = '홈런';
		else if (key =="rbi") label = '타점';
		else if (key =="r") label = '득점';
		else if (key =="bb") label = '볼넷';
		else if (key =="k") label = '삼진';
		else if (key =="scoringPositionAvg") label = '득점권 타율';
		else if (key =="ops") label = 'OPS';
		else if (key =="successSteal") label = '도루';
		else if (key =="runRate") label = '추가진루율';
		else if (key =="hit") label = '안타';
		
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
				xAxis:"batterName",
				yAxis:["avg"],
				xAxisLabel: "선수 명단",
				yAxisLabel: {avg:"타율"},
				chartTitle:"그래프",
				chartColor:"#000000",
				data:null,
				sortBy:"avg",
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
		changeShownChartData:changeShownChartData,
		downEventHandler:downEventHandler
	});
	
	function downEventHandler(event) {
		 
//		var currentEventHref = event.currentTarget.href;
//		var hrefValue = currentEventHref.substr(currentEventHref.length-1);
//		var tagId = event.currentTarget.id;
//		
//		if(hrefValue == "#") {
//			event.preventDefault();
//		}
//		
//		$("svg").attr({version:'1.1', xmlns:"http://www.w3.org/2000/svg"});
//		
//		var svg = document.querySelector("svg");
//		var svgData = new XMLSerializer().serializeToString(svg);
//		
//		var image = new Image();
//		
//		image.src = "data:image/svg+xml;base64," + window.btoa(unescape(encodeURIComponent(svgData)));
//		image.style = "z-index:8";
//		$("#downImage").append(image);
//		var downTypeValue = $("#imageType").val();
//		var captureDownloader = new CaptureDownloader();
//		
//		captureDownloader.downImage(downTypeValue);
		
		var imageTypeValue = $("#imageType").val();
		
		if(imageTypeValue != "empty") {
			var svg = document.querySelector("svg");
			
			var svgData = new XMLSerializer().serializeToString(svg);
			
			var base64Data = window.btoa(unescape(encodeURIComponent(svgData))); 
			
			var form = document.createElement("form");
			form.setAttribute("id", "imageForm");
			form.setAttribute("method", "post");
			form.setAttribute("action", "http://192.168.0.13:8080/posting/imageDown.do");
			
			var base64InputHiddenTag = document.createElement("input");
			base64InputHiddenTag.setAttribute("type", "hidden");
			base64InputHiddenTag.setAttribute("id", "base64Data");
			base64InputHiddenTag.setAttribute("name", "base64Data");
			base64InputHiddenTag.setAttribute("value", base64Data);
			
			var imageTypeTag = document.createElement("input");
			imageTypeTag.setAttribute("type", "hidden");
			imageTypeTag.setAttribute("name", "imageType");
			imageTypeTag.setAttribute("value", imageTypeValue);
			
			
			form.appendChild(base64InputHiddenTag);
			form.appendChild(imageTypeTag);
			form.submit();
			
		}
		
	
	}
	
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
		    	contents:function(d, dTitle, dValue, color) {
		    		var name = "";
		    		var value = "";
		    		var valLable = "";
		    		if (drawnData != null) {
		    			xAxisVal = drawnData[0];
		    			name = xAxisVal[d[0].index+1];
		    			valLable = config.yAxisLabel[d[0].name];
		    			value = d[0].value;
		    		}
		    		var html = "<div style='border:1px solid black; background-color:rgba(255, 255,255,0.5); padding:2px 2px 2px 2px'>"; 
		    		html += "<strong>&lt; "+name+" &gt;</strong><br/>";
		    		html += "<strong>"+valLable+":"+value+"</strong>";
		    		html += "</div>";
		    		return html;
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
