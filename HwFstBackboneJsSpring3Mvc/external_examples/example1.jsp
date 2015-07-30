<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Hello World in Backbone.js</title>
</head>
<body>
  <!-- ========= -->
  <!-- Your HTML -->
  <!-- ========= -->

  <div id="container">Loading...</div>

  <!-- ========= -->
  <!-- Libraries -->
  <!-- ========= -->
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" type="text/javascript"></script>
  <script src="http://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.3.3/underscore-min.js" type="text/javascript"></script>
  <script src="http://cdnjs.cloudflare.com/ajax/libs/backbone.js/0.9.2/backbone-min.js" type="text/javascript"></script>
  <script src="http://cdnjs.cloudflare.com/ajax/libs/backbone-localstorage.js/1.0/backbone.localStorage-min.js" type="text/javascript"></script>  
  
  <!-- =============== -->
  <!-- Javascript code -->
  <!-- =============== -->
  <script type="text/javascript">
    var AppView = Backbone.View.extend({
      // el - stands for element. Every view has a element associate in with HTML content will be rendered.
      el: '#container',
      // It's the first function called when this view it's instantiated.
      initialize: function(){
        this.render();
      },
      // $el - it's a cached jQuery object (el), in which you can use jQuery functions to push content. Like the Hello World in this case.
      render: function(){
        this.$el.html("Hello World");
      }
    });

    var appView = new AppView();
  </script>
  
</body>
</html>