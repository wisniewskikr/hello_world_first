// App Model
var AppModel = Backbone.Model.extend({
    urlRoot: 'app/ajax'
});
  
// Input View
var InputView = Backbone.View.extend({
	
  el: '#container',
  
  template: _.template($('#inputContent').html()),

  initialize: function(){
    this.render();
  },

  render: function(){
    this.$el.html(this.template());
  },
  
  events: {
      'click #ok': 'okAction'
  },
  
  okAction: function(e){ 
	  var name = this.$('#name').val();
	  var appModel = new AppModel({ name: name });
	  appModel.save({}, {
		  success :function(model, response) {
			  
			  if(response.status == "SUCCESS") {
				  var outputView = new OutputView(name);
		    	  this.$el.html(outputView.render());
			  }
			  
			  if(response.status == "FAIL") {    				 
				  this.$('#errors').text(response.message);
			  }
			  
    	}
	  });    	     	  
  }
  
});