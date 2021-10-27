//define(['jquery','underscore','backbone'],
//	function($, _, Backbone){
//		var chartGenerator = Backbone.View.extend({
//			chart : function(id, title, colors, data){
//				$(id).highcharts({
//				    chart : {
//				    	type : 'pie'
//				    },
//					title : {
//				       text : title,
//				       x : -20,
//				       style : {
//				    	   align : 'center',
//				    	   fontWeight : 'normal'
//				       }
//				    },
//                    plotOptions: {
//                        pie: {
//                            colors : colors
//                        }
//                    },
//				    series : [{
//                        name: 'rate',
//                        colorByPoint: true,
//                        dataLabels : {
//                            color : 'white',
//                            distance : -20,
//                            formatter : function(){
//                                if(this.percentage!=0) return Math.round(this.percentage) + '%';
//                            }
//                        },
//                        data: data
//                    }]
//	            });
//			}
//		});
//	return chartGenerator;
//});

/**
 * Created by junyoungPark on 2017. 1. 10..
 */

define(['jquery', 'highchart'], function($){
        var pieChart = {
            chart : function(view, title, colors, data){
                Highcharts.chart(view, {
                    chart: {
                        type: 'pie'
                    },
                    credits:{
				    	enabled : false
				    },
                    title: {
                        text: title
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                format: '<b>{point.name}</b><br>{point.percentage:.1f} %'
                            },
                            colors : colors
                        }
                    },
                    series: [{
                        name: 'rate',
                        colorByPoint: true,
                        dataLabels : {
                            color : 'black',
                            distance : -50,
                            formatter : function(){
                                if(this.percentage!=0) return Math.round(this.percentage) + '%';
                            }
                        },
                        data: data
                    }]
                });
            },
            
            zeroChart : function(view, title, colors, data){
            	Highcharts.chart(view, {
            		chart : {
            			type : 'pie'
            		},
            		credits:{
 				    	enabled : false
 				    },
            		title : {
            			text : title
            		},
            		tooltip : {
            			enabled : false
            		},
            		plotOptions : {
            			pie : {
            				colors : colors
            			}
            		},
            		series : [{
            			name : 'rate',       
            			dataLabels : {
            				enabled : false
            			},
            			data : data
            		}]
            	});
            }
        }
        return pieChart;
});