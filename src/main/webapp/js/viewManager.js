define(['../js/menutree/indexMenuTree',
	'jquery', 'underscore'],
function(contentTree){
	var viewManager = {
		getPath:function(viewIds) {
			var viewInfos = getPath(viewIds);
			return viewInfos; 
		}
	}
	
	var getPath = function(viewIds) {
		var viewId = "";
		var viewInfos = new Object();
		var tempObj = null;
		var viewInfo = null;
		for (var i=0; i<viewIds.length; i++) {
			viewId = viewIds[i];
			console.log(contentTree);
			console.log(viewId);
			console.log(viewInfos);
			if (viewInfo == null) {
				viewInfo = contentTree[viewId];
			} else {
				viewInfo = viewInfo[viewId];
			}
			console.log(viewInfo);
			
			if (_.has(viewInfo, "path")) {
				viewInfos[viewInfo.viewId] = viewInfo.path;
			}
			if (_.has(viewInfo, "tabs")) {
				viewInfos['tabs'] = viewInfo.tabs;
			}
			if (_.has(viewInfo, "children")) {
				viewInfo = viewInfo.children;
			}
		}
		
		return viewInfos;
	}
	
	return viewManager;
});