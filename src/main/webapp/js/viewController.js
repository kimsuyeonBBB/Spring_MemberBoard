define(
      [ 'common', 'viewManager', 'cookieUtil',
         'jquery', 'underscore', 'jqueryCookie', 'jqueryForm'],
function(Common, ViewManager, Cookie) {
	var viewPathArr = new Array();
	var renderingViews = null;
	var renderedViews = new Array();
	var viewParams = null;

	var controller = {
		control : function(menu, path, params) {
			var viewIds = null;
			
			if (renderedViews.length != 0) {
				_.each(renderedViews, function(view) {
					view.close();
				});
				renderedViews = new Array();
			}
			
			viewIds = [ menu ];
			
			// get rendering view path
			
			renderingViews = ViewManager.getPath(viewIds);
			console.log(renderingViews);
			_.each(renderingViews, function(item) {
				var path = "";
				if (typeof item === 'object') {
					if (_.has(params, "tabId")) {
						path = item[params.tabId].path;
					} else {
						path = item[0].path;
					}
				} else {
					path = item;
				}
				viewPathArr.push(path);
			});

			// config loader
			$('#jqxLoader').jqxLoader({
				isModal : true,
				width : 100,
				height : 60,
				imagePosition : 'top'
			});
			viewParams = params;
			console.log(params);
			//언어 변경 기능 ON
			$("#useLang").on("change", changeLangSet);
			renderView(params);
		}
	};
	
	function commonEvtSetting() {
		$("[name='homeBtn'").on("click", commBtnEventHandler);
		$("[name='siteNavBtn']").on("click", commBtnEventHandler);
	}
	
	function commBtnEventHandler(evt) {
		var clickedTarget = $(evt.currentTarget);
		var selectedURI = clickedTarget.attr("uri");
		
		if (selectedURI != null) {
			param = null;
			Common.linkingPage(selectedURI, param);
		}
	}

	var getConfigedCookie = function() {
		var mainCookie = Cookie.getCookie("hiball_config");
		var cookies = new Object();
		var configInfos = null;

		if (mainCookie != null) {
			configInfos = $.parseJSON(mainCookie);
		} else {
			configInfos = {
				"season" : 2017,
				"defaultTeam" : 2001
			};
		}
		cookies.teamId = configInfos.defaultTeam;
		cookies.season = configInfos.season;
		cookies.hitColor = configInfos.hitColor;
		cookies.swingColor = configInfos.swingColor;
		cookies.foulColor = configInfos.foulColor;
		cookies.outColor = configInfos.outColor;
		cookies.ballColor = configInfos.ballColor;
		cookies.standColor = configInfos.standColor;
		cookies.fastBallTypeColor = configInfos.fastBallTypeColor;
		cookies.offSpeedTypeColor = configInfos.offSpeedTypeColor;
		cookies.changeTypeColor = configInfos.changeTypeColor;
		cookies.etcBallTypeColor = configInfos.etcBallTypeColor;
		
		return cookies;
	}

	var deductRenderedView = function(view) {
		var viewIds = _.keys(renderingViews);
		view.cid = viewIds[0];
		
		renderingViews = _.omit(renderingViews, function(value) {
			return viewPathArr[0] == value;
		});

		viewPathArr = _.rest(viewPathArr);
		view.children = renderingViews;
	}

	// Rendering view function
	var renderView = function(params) {
		$('#jqxLoader').jqxLoader('open');
		if (viewPathArr != null && viewPathArr.length > 0) {
			require(viewPathArr, function(renderingView) {
				var view = new renderingView();
				deductRenderedView(view); // rendered view 처리
				renderedViews.push(view); // rendered view 상태 저장
				var cookies = getConfigedCookie();
				/** 여기서 서브 필터가 api를 call할지  */
				view.afterRender = chainNextView;
				view.render(params);
			});
			
		}
	}

	var chainNextView = function(params) {
		//마지막 View render 후 동작
		if (viewPathArr.length == 0) {
			var view = renderedViews[0];
			$("#jqxLoader").jqxLoader("close");
		} else {
			renderView(params);
		}
		
		// View 로딩 후 언어 변경
		renderLanguage();
	}
	
	function changeLangSet(evt) {
		var target = $(evt.currentTarget);
		var lang = target.val();
		
		//Cookie.addItemOnCookie("language", lang);
		Cookie.addItemOnLocalStorage("language", lang);
		renderLanguage();
	}
	
	function renderLanguage() {
		//var configedLang = Cookie.getCookie("language");
		var configedLang = Cookie.getLocalStorage("language");
		console.log("##### Cookie(language): "+configedLang);
		
		if (typeof(configedLang) == 'undefined' || configedLang == null) {	
			configedLang = $('#useLang').val();
		} else {
			$("#useLang").val(configedLang).prop("selected", true);
		}
		// 화면 노출언어 설정
		Cookie.renderLanguage();
		// 화면출력 후 변경되는 언어 설정
		renderedViews.forEach((view) => {
			if (typeof (view.renderLanguage) != 'undefined') view.renderLanguage();
		});
	}

	return controller;
});