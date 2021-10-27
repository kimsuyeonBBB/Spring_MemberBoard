define(['jquery','underscore','backbone'],
	function($, _){
		var chartGenerator = {
			chart : function(id, title, colors, categories, yAxisTitle, series){
				$(id).highcharts({
				    title : {
				       text : title,
				       x : -20,
				       style : {
				     	  fontWeight : 'bold'
				       }
				    },
				    colors : colors,
				    xAxis : {
				       categories : categories
				    },
				    yAxis : {
				       title : yAxisTitle
				    },
				    legend : {
				        layout : 'vertical',
				        align : 'right',
				        verticalAlign : 'middle',
				        borderWidth : 0
				     },
				    series : series
	            });
			}
		}
		
		return chartGenerator
});

/*view.highcharts({
    title : {
       text : title,
       x : -20,
       style : {
     	  fontWeight : 'bold'
       }
    },
    colors : ['#a6a6a6', '#9fc93c'],
    xAxis : {
       categories : categories
    },
    yAxis : {
       title : 'speed'
    },
    legend : {
       layout : 'vertical',
       align : 'right',
       verticalAlign : 'middle',
       borderWidth : 0
    },
    series : [{
       type : 'column',
       name : legend1,
       data : dataSet1
    },
    {
 	   type : 'line',
 	   name : legend2,
 	   data : dataSet2
    }
    ]*/