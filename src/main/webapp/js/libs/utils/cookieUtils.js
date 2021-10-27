define(['jquery', 'languages/languageSet', 'backbone', 'jqueryCookie'], 
function($, languages) {
	var validTime = 30; //minutes
	var date = new Date();
	date.setTime(date.getTime()+(validTime*60*1000));
	
	var modelObj = {
		"getCookie": function (key) {
			return $.cookie(key);
		},
		"getLocalStorage": function (key) {
			return localStorage.getItem(key);
		},
		"addItemOnCookie": function (key, value) {
			var cookie = $.cookie(key);
			if (cookie != null && cookie != '') {
				$.removeCookie(key);
			}
			
			$.cookie(key, value,{expires:date});
		},
		"addItemOnLocalStorage": function (key, value) {
			localStorage.setItem(key, value);
		},
		"modifyCookie":function (key, value, cookieId) {
			var cookie = this.getCookie(cookieId);
			var cookieJson = {};
			//console.log(cookie);
			if (cookie != null && cookie != '') {
				cookieJson = $.parseJSON(cookie);
				cookieJson[key] = value;
				console.log(cookieJson);
				console.log("modify cookie");
				
				//$.removeCookie(cookieId);
			} else {
				cookieJson[key] = value;
			}
			//console.log(cookieJson);
			//console.log("not modify");
			$.cookie(cookieId, JSON.stringify(cookieJson),{path:'/'});
		},
		"getLanguage":function (useLang, prop) {
			return languages[useLang][prop];
		},
		"renderLanguage":function () {
			var useLang = this.getLocalStorage("language");
			//console.log(useLang);
			if (useLang == null) {
				useLang = "kr";
			}
			const $lang = document.querySelectorAll("[data-lang]");
			
			$lang.forEach(el => {
				let prop = el.dataset.lang;
				let language = this.getLanguage(useLang, prop);
				if (language) {
					el.textContent = language;
				} else {
					el.textContent = 'C';
				}; 
			});
		}
	};
	
	return modelObj;
});