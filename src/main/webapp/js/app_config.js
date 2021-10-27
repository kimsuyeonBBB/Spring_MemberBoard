require.config({
	urlArgs: "v=" + (new Date()).getTime(),
	paths:{
		require:			'libs/require/require_2.2.0',
		jquery : 			'libs/jquery/jquery-1.12.4.min',
		jqueryUi:			'libs/jquery/jquery-ui',
		jqueryNum:			'libs/jquery/jquery.number',
		jqueryCanvas:		'libs/jquery/jquery-canvas',
		jqueryCookie:		'libs/jquery/jquery.cookie',
		jqueryConfirm:		'libs/jquery/jquery-confirm',
		jqueryForm:			'libs/jquery/jquery.form',
		jFileDownload:		'libs/jquery/jquery.fileDownload',
		jqxCore:			'libs/jqlibs/jqxcore',
		jqxLoader:			'libs/jqlibs/jqxloader',
		underscore:			'libs/underscore/underscore_1.8.3',
		backbone:			'libs/backbone/backbone_1.3.2',
		hiballView:			'libs/backbone/backbone_hiball_view',
		heatmap : 			'libs/heatmap/heatmap.min',
		viewController:		'viewController',
		viewManager: 		'viewManager',
		cookieUtil:			'libs/utils/cookieUtils',
		common:				'libs/utils/common',
		popper:				'libs/bootstrap/popper.min',
		bootstrap:			'libs/bootstrap/bootstrap',
		calcstat:			'libs/utils/calcurateStat',
		statGrade:			'libs/utils/statColorGrade',
		videojshls:			'libs/videojs/videojs-contrib-hls',
		videojs:			'libs/videojs/video',
		'global/document':	'libs/videojs/document',
		'global/window':	'libs/videojs/window',
		languageSet:		'languages/languageSet'
	},
	shim:{
		require:{
			export:"require"
		},
		jquery: {
			export:"$"
		},
		jqueryCookie: {
			export:"$",
			deps:['jquery']
		},
		jqueryConfirm: {
			export:"$",
			deps:['jquery']
		},
		jqueryForm: {
			export:"$",
			deps:['jquery']
		},
		jFileDownload: {
			export:"$",
			deps:['jquery']
		},
		underscore: {
			export:"_"
		},
		backbone: {
			export:"Backbone"
		},
		jqxCore:  {
			export:"$",
			deps:['jquery']
		},
		jqxLoader:  {
			export:"$",
			deps:['jquery', 'jqxCore']
		},
        bootstrap:{
        	export:"$",
        	deps:['jquery', 'popper']
        }
	}
});

require(['app'], function(App){
	App.initialize();
})