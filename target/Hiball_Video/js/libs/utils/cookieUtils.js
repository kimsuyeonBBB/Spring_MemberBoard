define(['jquery', 'backbone', 'jqueryCookie'], 
function($) {
	var validTime = 30; //minutes
	var date = new Date();
	date.setTime(date.getTime()+(validTime*60*1000));
	
	var modelObj = {
		"getCookie": function (key) {
			return $.cookie(key);
		},
		"addItemOnCookie": function (key, value) {
			var cookie = $.cookie(key);
			if (cookie != null && cookie != '') {
				$.removeCookie(key);
			}
			
			$.cookie(key, value,{expires:date});
		},
		"modifyCookie":function (key, value, cookieId) {
			var cookie = this.getCookie(cookieId);
			var cookieJson = {};
			console.log(cookie);
			if (cookie != null && cookie != '') {
				cookieJson = $.parseJSON(cookie);
				cookieJson[key] = value;
				console.log(cookieJson);
				console.log("modify cookie");
				
				//$.removeCookie(cookieId);
			} else {
				cookieJson[key] = value;
			}
			console.log(cookieJson);
			console.log("not modify");
			$.cookie(cookieId, JSON.stringify(cookieJson),{path:'/'});
		}
	};
	
	return modelObj;
});