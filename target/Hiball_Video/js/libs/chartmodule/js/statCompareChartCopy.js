define([
        'jquery','underscore','backbone','common',
        'text!../html/statCompareChart.html', 'c3', 'd3'
], function($, _, Backbone, Common, StatCompareChart, C3, D3) {
	var categoryObj = new Backbone.Model();
	var category = null;
	var comparingPros = {};
	var selectRecord = null;
	var renderedFlag = {};
	var chartLength = 0;
	var isCompared = false;
	var chartObj = null;
	var chartScaleStart = 0.0;
	var chartScaleEnd = 0.0;
	var config = {
			width:900,
			height:25
	};
	
	var chartInit = function(conf) {
		if(typeof conf!="undefined"&& conf!=null) {
			config = conf;
		}
	}
	
	var StatCompareChartView = Backbone.View.extend ({
		chartInit:chartInit,
		render: function(view, data) {
			if (categoryObj.length == 0) {
				alert("Configurate categories First!");
			} else {
				var chartData = data;

				if (typeof categoryObj.get("isCompared") != "undefined") {
					isCompared = categoryObj.get("isCompared");
				} else {
					categoryObj.set("isCompared", isCompared);
				}
				drawArea = view.attr("id");
				makeChartFrame(view);
				drawChart(drawArea, chartData);
			}
		}, 
		categories : categoryObj,
		load:loadingChartData
	});
	
	
	function makeChartFrame(view) {
		var frame = _.template(StatCompareChart);
		categoryObj.set("drawArea", view.attr("id"));
		category = categoryObj.toJSON();
		view.html(frame(category));
	}
	
	function loadingChartData(data) {
		var tmpChart = {};
		originData = data;
		//configScaleStartEnd(chartData);
		_.each(category.categories, function(item, key){
			console.log(item);
			tmpChart = chartObj[key];
			tmpChart.load({
				columns:makeColumnRatioData(originData, key)
			});
		});
	}
	
	function drawChart(drawArea, chartData) {
		originData = chartData;
		chartObj = {};
		_.each(category.categories, function(item, key){
			chartObj[key] = C3.generate(configCombineChart(drawArea, chartData, key));
		});
	}
	
	function configCombineChart (drawArea, chartData, key) {
		var prop = {
			onrendered:tunningLabelPosition,
			bindto: "#"+drawArea+"_"+key+"_chart",
			padding:{
				top:0,right:0,left:0,bottom:0
			},
			size:{
				height:config.height,
				width:config.width
			},
		    grid:{
		    	y:{
		    		lines:[{value:5000, text:""}]
		    	}
		    },
		    data: {
		    	order:null,
		        columns:makeColumnRatioData(chartData, key),
		        type: 'bar',
		        labels:{
		        	format:function(v, id) {
		        		var value = "";
		        		if (id) {
		        			v = originData[id][key];
		        			value = Common.containTeamName(id).kor+" : "+v
		        		} else {
		        			value = v;
		        		}
		        		
		        		return value;
		        	}
		        },
		        groups : makeGroups(chartData),
		        color:function(color, d) {
		        	var configColor = color;
		        	
		        	if (d.id) {
		        		if (isCompared) {
		        			configColor = configColorForVal(chartData, key, d.id);
		        		} else {
		        			var colorObj = Common.teamColor(d.id); 
		        			configColor = colorObj.team;
		        		}
		        	}
		        	
		        	return configColor; 
		        } 
		    },bar:{
		    	width:20
		    },
		    axis:{
		        rotated:true,
		        x:{
		            show:false
		        },
		        y:{
		            show:false,
		            min: 2500,
		            max: 7500
		        }
		    },
		    legend: {
		    	hide:true,
		    	position:"inset"
		    },
		    tooltip: {
		    	show:false
		    }
		};
		
		return prop;
	}
	
	function tunningLabelPosition() {
		var textElements = D3.selectAll(".c3-texts").select("text");
		_.each(textElements, function(texts, key){
			var frontX = 0;
			_.each(texts, function(text, idx){
				frontX = $(text).attr("x");
				if (idx%2==0) {
					var bText = texts[idx+1];
					if (frontX != 5) {
						$(bText).attr("x", config.width-80);
						$(text).attr("x", 10);
					}
					$(bText).attr("style", "font-weight:bold; font-size:12px; fill:#ffffff");
					$(text).attr("style", "font-weight:bold; font-size:12px; fill:#ffffff");
				}
			});
		});
	}
	
	function makeColumnRatioData(data, prop) {
		var column = null;
		var row = [];
		var keys = _.keys(data);
		var numerator = 0.0;
		var denominator = 0.0;
		var domain = [];
		_.each(keys, function(key){
			column = [];
			column.push(key);
			_.each(data, function(vals, k){
				if (k != key) {
					denominator = vals[prop];
				}else {
					numerator = vals[prop];
				}
			});
			var v = (numerator/(numerator+denominator))*10000;
			if (prop == "k") {
				column.push((10000-v).toFixed(1));
				domain.push((10000-v).toFixed(1))
			} else {
				column.push(v.toFixed(1));
				domain.push(v.toFixed(1));
			}
			row.push(column);
		});
		
		return row;
	}
	
	function configScaleStartEnd(chartData) {
		var mArr = [];
		var sArr = null;
		_.each(category.categories, function(item, key){
			var sArr = [];
			_.each(chartData, function(item){
				sArr.push(item[key]);
			});
			mArr.push(sArr);
		});
		var scale = [];
		_.each(mArr, function(item){
			scale.push((item[0]/(item[0]+item[1]))*10000);
			scale.push((item[1]/(item[0]+item[1]))*10000);
		});
		
		var min = _.min(scale);
		var max = _.max(scale);
		if ( 5000-min > max-5000 ) {
			if (min >= 4000 && min <= 5000) {
				chartScaleStart = 4000;
				chartScaleEnd = 6000;
			} else if (min >= 3000 && min <=5000) {
				chartScaleStart = 3000;
				chartScaleEnd = 7000;
			} else if (min >= 2500 && min <= 5000) {
				chartScaleStart = 2500;
				chartScaleEnd = 7500;
			} else if (min >= 2000) {
				chartScaleStart = 2000;
				chartScaleEnd = 7000;
			}
		} else {
			if (max > 5000 && max <= 6000) {
				chartScaleStart = 4000;
				chartScaleEnd = 6000;
			} else if (max > 5000 && max <= 7500) {
				chartScaleStart = 2500;
				chartScaleEnd = 7500;
			} else if (max > 5000 && max <= 8000) {
				chartScaleStart = 2000;
				chartScaleEnd = 8000;
			}
		}
	}
	
	function makeGroups(data) {
		var groups = [];
		var members = [];
		_.each(data, function(item, key){
			members.push(key);
		});
		groups.push(members);
		
		return groups;
	}
	
	function configColorForVal(data, key, tn) {
		var keyVal = data[tn][key];
		var color = "#00ff00";
		_.each(data, function(item, idx){
			if (idx != tn) {
				if (item[key] < keyVal) {
					color = "#eb421c";
				} else if (item[key]>keyVal){
					color = "#54565e";
				};
			}
		});
		return color;
	}
	
	return StatCompareChartView;
});
