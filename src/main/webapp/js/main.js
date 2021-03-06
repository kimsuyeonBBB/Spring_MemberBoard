require.config({
	urlArgs: "v=" + (new Date()).getTime(),
	paths:{
		jquery:'./libs/jquery/jquery-3.1.0.min',
		jqueryUi:'./libs/jquery/jquery-ui',
		jqueryNum:'./libs/jquery/jquery.number',
		jqueryCanvas:'./libs/jquery/jquery-canvas',
		jqueryModal:'./libs/jquery/jquery-modal',
		underscore:'./libs/underscore/underscore_1.8.3',
		backbone:'./libs/backbone/backbone_1.3.2',
		hiballView:'./libs/backbone/backbone_hiball_view',
		highchart:'./libs/highchart/highcharts',
		highchart_more:'./libs/highchart/highcharts-more',
		drawChart:'./libs/highchart/drawChart',
		viewController:'viewController',
		spin:'./libs/spin',
		d3:'./libs/chart/d3.v3.min',
		c3:'./libs/chart/c3',
		common:'./libs/utils/common',
		service:'./libs/utils/serviceUtils',
		calcstat:'./libs/utils/calcurateStat',
		statGrade:'./libs/utils/statColorGrade',
		bootstrap:'../assets/bootstrap/js/bootstrap'
	},
	shim:{
		highchart_more:{
			deps:['highchart']
		},
		c3:{
			deps:['d3']
		},
		jqueryModal:{
			deps:['spin']
		},
		bootstrap:{
			deps:['jquery']
		}
	}
});

require(['app'], function(App){
	App.initialize();
})