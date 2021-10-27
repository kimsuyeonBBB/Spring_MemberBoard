define([
	'jquery', 'backbone'
], 
function($, Backbone) {
	var modelObj = new Object({
		link:function(url) {
			$(location).attr("href", url);
		},
		retrievingCont:function(serviceName, subServiceName, returnName, sortColumn, parameterSet){
			var params = {
				"serviceName":serviceName,
				"subServiceName":subServiceName,
				"returnName":returnName,
				"sortColumn":sortColumn,
				"parameterSet":parameterSet
			};
			
			return params
		}
	});
	
	
	return modelObj;
});