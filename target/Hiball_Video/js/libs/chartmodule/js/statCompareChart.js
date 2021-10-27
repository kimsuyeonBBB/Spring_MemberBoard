define([
        'jquery','underscore','backbone','common',
        'text!../html/statCompareChart.html', 'c3', 'd3'
], function($, _, Backbone, Common, StatCompareChart, C3, D3) {
	var comparingPros = {};
	var selectRecord = null;
	var renderedFlag = {};
	var chartLength = 0;
	var isCompared = false;
	var chartObj = new Object();
	var chartScaleStart = 0.0;
	var chartScaleEnd = 0.0;
	var config = null;
	var drawArea = "";
	var cData = null;
	
	var chartInit = function(conf) {
		config = {
				width:900,
				height:25
		};
		if(typeof conf!="undefined"&& conf!=null) {
			config = conf;
		}
	}
	
	var StatCompareChartView = Backbone.View.extend ({
		chartInit: chartInit,
		chartData : new Object(),
		viewProperty : new Backbone.Model(),
		chartGroups : new Object(),
		initialize: function() {
			chartInit();
			this.viewProperty.set("isCompared", false);
		},
		render: function(view) {
			var viewProp = this.viewProperty;
			if (viewProp.length == 0) {
				alert("Configurate categories First!");
			} else {
				if (typeof viewProp.get("isCompared") != "undefined") {
					isCompared = viewProp.get("isCompared");
				} else {
					viewProp.set("isCompared", isCompared);
				}
				drawArea = view.attr("id");
				/* Drawing chart frame */
				var chartView = _.template(StatCompareChart);
				viewProp.set("drawArea", view.attr("id"));
				view.html(chartView(viewProp.toJSON()));
				/* end drawing chart frame */
				
				this.drawChart(drawArea);
			}
		},
		drawChart:function(drawArea) {
			var chartProp = this.chartProperty;
			var viewProp = this.viewProperty;
			var group = this.chartGroups;
			var _this = this;
			_.each(viewProp.get('categories'), function(item, key){
				group[drawArea+"_"+key] = C3.generate(chartProp(_this, drawArea, key));
			});
		},
		load:function(data) {
			var tmpChart = {};
			this.chartData = data;
			var prop = this.viewProperty.toJSON();
			var cData = this.chartData;
			var chartGroup = this.chartGroups;
			
			_.each(prop.categories, function(item, key){
				var tmpKey = drawArea+"_"+key;
				tmpChart = chartGroup[tmpKey];
				tmpChart.load({
					columns:makeColumnRatioData(key, data)
				});
			});
		},
		chartProperty:function(parent, drawArea, key) {
			var cData = parent.chartData;
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
			    	columns:makeColumnRatioData(key, cData),
			        type: 'bar',
			        labels:{
			        	format:function(v, id, i, j) {
			        		var value = "";
			        		if (id) {
			        			v = parent.chartData[id][key];
			        			value = Common.containTeamName(id).kor+" : "+v
			        		} else {
			        			value = v;
			        		}
			        		
			        		return value;
			        	}
			        },
			        groups : makeGroups(cData),
			        color:function(color, d) {
			        	var configColor = color;
			        	
			        	if (d.id) {
			        		if (isCompared) {
			        			configColor = configColorForVal(key, d.id, cData);
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
		},
		close:function() {
			this.unbind();
			this.remove();
			_.each(chartObj, function(item, key){
				delete item;
			});
		}
	});
	
	function makeGroups(cData) {
    	var groups = [];
		var members = [];
		_.each(cData, function(item, key){
			members.push(key);
		});
		groups.push(members);
		return groups;
	}
	
	function makeColumnRatioData(prop, cData) {
		var column = null;
		var row = [];
		var keys = _.keys(cData);
		var numerator = 0.0;
		var denominator = 0.0;
		var domain = [];
		_.each(keys, function(key){
			column = [];
			column.push(key);
			_.each(cData, function(vals, k){
				if (k != key) {
					denominator = vals[prop];
				}else {
					numerator = vals[prop];
				}
			});
			var v = (numerator/(numerator+denominator))*10000;
			if (prop == "k" || prop == "era") {
				column.push((10000-v).toFixed(1));
				domain.push((10000-v).toFixed(1))
			} else {
				column.push(v.toFixed(1));
				domain.push(v.toFixed(1));
			}
			
			
			if(isNaN(column[1])) {
				column[1] = 5000.0;
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
	
	function configColorForVal(key, tn, cData) {
		var keyVal = cData[tn][key];
		var color = "#d5d5d5";
		_.each(cData, function(item, idx){
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
	
	return StatCompareChartView;
});
