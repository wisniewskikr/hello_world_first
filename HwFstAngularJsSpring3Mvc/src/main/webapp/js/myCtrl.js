app.controller('myCtrl', function($scope, $http) {
			
	
	
	// Init values
	$scope.inputContent = true;
	$scope.outputContent = false;
	
	
	
	// Function send()
	$scope.send = function() {
		
		var json = {
			name: $scope.name					
		};
		
		var httpRequest = $http({
			method: 'POST',
            url: 'app/ajax',
            data: JSON.stringify(json)
		});
		
		httpRequest.success(function(response, status) {
            
        	if(response.status == "SUCCESS") {
        		$scope.inputContent = false;
    			$scope.outputContent = true;
    			$scope.result = response.name;
    			$scope.errors = "";
        	}
        	
        	if(response.status == "FAIL") {
        		$scope.errors = response.message;
        	}
            
        });
		
	};
	
	
	
	// Function back()
	$scope.back = function() {
		$scope.inputContent = true;
		$scope.outputContent = false;
		$scope.name = "";
	};
	
	
	
});