define(['jquery','underscore','backbone'],
	function($, _, Backbone){
		var chartGenerator = Backbone.View.extend({
			chart : function(id, type, title, colors, categories, yAxisTitle, series, reverseType, labelsHandler){
				$(id).highcharts({
				    chart : {
				    	type : type
				    },
				    credits:{
				    	enabled : false
				    },
					title : {
				       text : title,
				       x : -20,
				       style : {
				     	  fontWeight : 'bold'
				       }
				    },
				    colors : colors,
				    dataLabels : {
				    	formatter : function(){
				    		return labelsHandler(this);
				    	}
				    },
//				    plotOptions : {
//				    	series : {
//				    		cursor : 'pointer',
//				    		events : {
//				    			click : function (event) {
////				    				console.log(this);
//				    				chartEventHandler(this, event);
//				    			}
//				    		}
//				    	}
//				    },
				    xAxis : {
				       categories : categories
				    },
				    yAxis : {
				    	reversed : reverseType,
				    	title : {
				    		text : yAxisTitle
				    	}
				    },
				    legend : {
				        layout : 'vertical',
				        align : 'right',
				        verticalAlign : 'middle',
				        borderWidth : 0
				     },
				    series : series
	            });
			},
			
			doubleChart : function(id, title, colors, categories, series, dataWidth, max){
				$(id).highcharts({
					chart : {
						type : 'bar'
					},
					credits : {
						enabled : false
					},
					title :{
						text : title
					},
					xAxis: [{
		                categories: categories,
		                reversed : false,
		                labels: {
		                    step: 1
		                }
		            }, {
		                opposite: true,
		                reversed : false,
		                categories: categories,
		                linkedTo: 0,
		                labels: {
		                    step: 1
		                }
		            }],
		            yAxis: {
		                title: {
		                    text: null
		                },
		                labels: {
		                    formatter: function () {
		                        return Math.abs(this.value);
		                    }
		                },
		                max : max,
		                min : -max
		            },
		            colors : colors,
		            plotOptions: {
		                series: {
		                	pointWidth :dataWidth,
		                    stacking: 'normal'
		                }
		            },
		            tooltip: {
		                formatter: function () {
		                    return '<b>' + this.series.name +' '+ this.point.category + '</b><br/>' +
		                        'Value: ' + Highcharts.numberFormat(Math.abs(this.point.y), -1);
		                }
		            },
		            series : series
				})
			}
		});
	return chartGenerator;
});