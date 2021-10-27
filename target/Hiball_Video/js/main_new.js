require.config({
	urlArgs: "v=" + (new Date()).getTime(),
	paths:{
		jquery : 			'libs/jquery/jquery-1.12.4.min',
		jqueryUi:			'libs/jquery/jquery-ui',
		jqueryNum:			'libs/jquery/jquery.number',
		jqueryCanvas:		'libs/jquery/jquery-canvas',
		jqueryCookie:		'libs/jquery/jquery.cookie',
		jqueryConfirm:		'libs/jquery/jquery-confirm',
		jqueryForm:			'libs/jquery/jquery.form',
		jFileDownload:		'libs/jquery/jquery.fileDownload',
		jqxCore:			'libs/jqlibs/jqxcore',
		jqxButtons:			'libs/jqlibs/jqxbuttons',
		jqxScrollBar:		'libs/jqlibs/jqxscrollbar',
		jqxListBox:			'libs/jqlibs/jqxlistbox',
		jqxDropdownList:	'libs/jqlibs/jqxdropdownlist',
		jqxData:			'libs/jqlibs/jqxdata',
		jqxDataTable:		'libs/jqlibs/jqxdatatable',
		jqxMenu:			'libs/jqlibs/jqxmenu',
		jqxGrid:			'libs/jqlibs/jqxgrid',
		jqxGridPager:		'libs/jqlibs/jqxgrid.pager',
	 	jqxGridSort:		'libs/jqlibs/jqxgrid.sort',
		jqxGridFilter:		'libs/jqlibs/jqxgrid.filter',
		jqxGridStorage:		'libs/jqlibs/jqxgrid.storage',
		jqxGridColumnsResize:		'libs/jqlibs/jqxgrid.columnsresize',
		jqxGridColumnsOrder:		'libs/jqlibs/jqxgrid.columnsorder',
		jqxGridSelection:	'libs/jqlibs/jqxgrid.selection',
		jqxPanel:			'libs/jqlibs/jqxpanel',
		jqxCheckbox:		'libs/jqlibs/jqxcheckbox',
		jqxLoader:			'libs/jqlibs/jqxloader',
		jqxDateTimeInput:	'libs/jqlibs/jqxdatetimeinput',
		jqxCalendar:		'libs/jqlibs/jqxcalendar',
		jqxToolTip:			'libs/jqlibs/jqxtooltip',
		underscore:			'libs/underscore/underscore_1.8.3',
		backbone:			'libs/backbone/backbone_1.3.2',
		hiballView:			'libs/backbone/backbone_hiball_view',
		heatmap : 			'libs/heatmap/heatmap.min',
		viewController:		'viewController',
		viewManager: 		'viewManager',
		cookieUtil:			'libs/utils/cookieUtils',
		common:				'libs/utils/common',
		"popper":			'libs/bootstrap/popper.min',
		bootstrap:			'libs/bootstrap/bootstrap',
		calcstat:			'libs/utils/calcurateStat',
		statGrade:			'libs/utils/statColorGrade'
	},
	shim:{
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
		jqxButtons:{
			export:"$",
			deps:['jquery', 'jqxCore']
		},
		jqxScrollBar:{
			export:"$",
			deps:['jquery', 'jqxCore']
		},
		jqxListBox:{
			export:"$",
			deps:['jquery', 'jqxCore']
		},
		jqxDropdownList:{
			export:"$",
			deps:['jquery', 'jqxCore']
		},
		jqxData:{
			export:"$",
			deps:['jquery', 'jqxCore']
		},
		jqxDataTable:{
			export:"$",
			deps:['jquery', 'jqxCore']
		},
		jqxMenu:{
			export:"$",
			deps:['jquery', 'jqxCore']
		},
		jqxGrid:{
			export:"$",
			deps:['jquery', 'jqxCore']
		},
		jqxGridPager:{
			export:"$",
			deps:['jquery', 'jqxCore']
		},
	 	jqxGridSort:{
			export:"$",
			deps:['jquery', 'jqxCore']
		},
		jqxGridFilter:{
			export:"$",
			deps:['jquery', 'jqxCore']
		},
		jqxGridStorage:{
			export:"$",
			deps:['jquery', 'jqxCore']
		},
		jqxGridColumnsResize:{
			export:"$",
			deps:['jquery', 'jqxCore']
		},
		jqxGridColumnsOrder:{
			export:"$",
			deps:['jquery', 'jqxCore']
		},
		jqxGridSelection:{
			export:"$",
			deps:['jquery', 'jqxCore']
		},
		jqxPanel:{
			export:"$",
			deps:['jquery', 'jqxCore']
		},
		jqxCheckbox:{
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