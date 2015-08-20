// Output View
var OutputView = Backbone.View.extend({
	
  name: null,
	
  el: '#container',
  
  template: _.template($('#outputContent').html()),

  initialize: function(name){
	this.name = name;  
    this.render();
  },

  render: function(){
    this.$el.html(this.template()); 
    this.$('#result').text(this.name);
  },
  
  events: {
      'click #back': 'backAction'
  },
  
  backAction: function(e){
	  var inputView = new InputView();
	  this.$el.html(inputView.render());
  }
  
});