define(["underscore", "backbone", "jquery"], function(_, Backbone){
	var parameters = new Object();
	var viewId = "";
	var hiballView = {
		viewId: viewId,
		extend: function(options) {
			var parentView = Backbone.View.extend(options);
			parentView.prototype.children = {};
			parentView.prototype.initialize = function() {
				_.bindAll(this, 'beforeRender', 'render', 'chainEventHandler');
				var _this = this;
				this.render = _.wrap(this.render, function(render, arguments){
					_this.beforeRender();
					//console.log("rendering...");
					render(arguments, _this.chainEventHandler);
					return _this;
				});
			};
			
			parentView.prototype.beforeRender = function() {
				//console.log("before Render in parent");
			};
			parentView.prototype.afterRender = function() {
				//console.log("after Render in parent");
			};
			
			if (!_.has(options, "chainEventHandler")) {
				parentView.prototype.chainEventHandler = function(params) {
					this.afterRender(params);
				}
			}
			
			if(!_.has(options, "close")) {
				parentView.prototype.close = function() {
					this.remove();
					this.unbind();
					this.undelegateEvents();
				};
			}
			
			
			return parentView;
		}
	}
	
	return hiballView;
});