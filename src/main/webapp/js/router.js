define(['global', 'backbone', 'viewController', 'common'],
function(GlobalVar, Backbone, viewController, commUtils) {
	var app_router = null;
	
	var AppRouter = Backbone.Router.extend({
		routes: {
			'*actions':'defaultAction'
		}
	});
	
	var initialize = function() {
		app_router = new AppRouter();
		app_router.on('route:defaultAction', function(path, params) {
			var context = GlobalVar.context+'/';
			path = location.pathname;
			var menu = path.replace(context, '');
			if (menu == '') {
				menu = 'index';
			}
			goOnRoutes(menu, path, params);
		});
		
		Backbone.history.start();
	};
	
	var goOnRoutes = function (menu, path, params) {
		var paramStr = location.search;
		if (paramStr.indexOf('?')>-1) {
			paramStr = paramStr.substring(1);
			if (params != '') {
				params += "&"+paramStr;
			} else {
				params = paramStr;
			}
		}
		
		var paramController = commUtils.paramController;
		var paramObj = paramController.parsing(params);
		console.log(paramObj);
		viewController.control(menu, path, paramObj);
	}
	
	return {initialize:initialize};
});