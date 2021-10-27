define([ 'global', 'cookieUtil', 'jquery', 'backbone', 'jqueryCookie',
		'jqxCore', 'jqxLoader' ], function(GlobalVar, CookieUtils) {
	var contxt = GlobalVar.context + '/';
	var modelObj = new Object();

	var collection = Backbone.Collection.extend({
		url : "http://localhost:8080/memberboard/apiMultiCall.do",
	// url:"http://192.168.0.18:8080/video_clip/apiMultiCall.do",
	// url:"http://localhost:8080/video_clip/apiMultiCall.do",
	});
	var callApi = ({
		fetch : function(type, param, dataType, successHandler) {
			var col = new collection();
			col.fetch({
				type : type,
				data : param,
				dataType : dataType,
				success : successHandler,
				error : errorHandler
			});
			console.log(param);
			return col;
		}
	});
	var getAjax = function(url, data, successHandler) {
		url = contxt + url;
		$('#jqxLoader').jqxLoader('open');
		$.ajax({
			url : url,
			type : 'POST',
			dataType : "json",
			contentType : "application/json; charset=UTF-8",
			data : data,
			success : successHandler,
			error : function(xhr, ajaxOptions, thrownError) {
				console.log("FAILURE");
				console.log("code:" + xhr.status + "\n" + "message:"
						+ xhr.responseText + "\n" + "error:" + thrownError);
				errorHandler();
			},
			complete : function() {
				$('#jqxLoader').jqxLoader('close');
			}
		});
	}

	var errorHandler = function(e) {
		linkingPage('/', null);
	}

	var logoutHandler = function(e) {
		linkingPage('logout', null);
	}

	var paramController = ({
		parsing : function(params) {
			var paramObj = new Object();
			if (params != null) {
				var tmpArr = params.split("&");
				for (var i = 0; i < tmpArr.length; i++) {
					var tmp = tmpArr[i].split("=");
					if (tmp.length == 2) {
						paramObj[tmp[0]] = decodeURIComponent(tmp[1]);
					} else if (tmp.length == 3) {
						paramObj[tmp[0]] = tmp[1] + "=";
					}
				}
			}
			return paramObj;
		}
	});

	var linkingPage = function(uri, params) {
		console.log("########## Linking page ##########");
		var paramStr = "";
		var url = "http://" + document.location.host;
		url += contxt;

		if (uri != '/')
			url += uri;

		if (typeof params != 'undefined' && params != null) {
			paramStr += "?";
			var keySet = _.keys(params);

			_.each(keySet, function(key) {
				paramStr += key + "=" + encodeURIComponent(params[key]);
				if (key != _.last(keySet)) {
					paramStr += "&";
				}
			});
		}

		url += paramStr;
		console.log(url);
		$(location).attr("href", url);
	}

	modelObj['getAjax'] = getAjax;
	modelObj['callApi'] = callApi;
	modelObj['paramController'] = paramController;
	modelObj['linkingPage'] = linkingPage;
	modelObj['logoutHandler'] = logoutHandler;
	modelObj['context'] = contxt;

	return modelObj;
});