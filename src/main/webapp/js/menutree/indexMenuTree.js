define(['underscore',  'jquery'],
function() {
	var menuTree = {
		"index": {
			viewId:"gameCriteria",
			path:"main/controller/gameCriteria"
		},
		"gameCriteria": {
			viewId:"gameCriteria",
			path:"main/controller/gameCriteria"
		},
		"gameList": {
			viewId:"gameList",
			path:"main/controller/gameList"
		},
		'videoList':{
			viewId:'videoList',
			path:"main/controller/videoList_hls"
			//path:"main/controller/videoList"
		},
		'memberList':{
			viewId:'memberList',
			path:"main/controller/memberList"
		},
		'memberAdd':{
			viewId:'memberAdd',
			path:"main/controller/memberAdd"
		},
		'memberUpdate':{
			viewId:'memberUpdate',
			path:"main/controller/memberUpdate"
		},
		'boardList':{
			viewId:'boardList',
			path:"main/controller/boardList"
		},
		'boardAdd':{
			viewId:'boardAdd',
			path:"main/controller/boardAdd"
		},
		'boardUpdate':{
			viewId:'boardUpdate',
			path:"main/controller/boardUpdate"
		},
		'findId':{
			viewId:'findId',
			path:"main/controller/findId"
		},
		'findPw':{
			viewId:'findPw',
			path:"main/controller/findPw"
		}
	};
	menuTree = _.extend(menuTree);
	
	return menuTree;
});