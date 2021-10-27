define(['underscore',  'jquery'],
function() {
	var menuTree = {
		"index": {
			viewId:"index",
			path:"main/controller/menu"
		},
		'videoList':{
			viewId:'videoList',
			path:"main/controller/videoList"
		}
	};
	menuTree = _.extend(menuTree);
	
	return menuTree;
});