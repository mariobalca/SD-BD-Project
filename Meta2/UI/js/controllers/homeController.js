myApp.controller('homeController', ['$scope', '$log', '$http', 'authService', function($scope, $log, $http, authService){
	if(authService.loggedIn){
		$scope.user = authService.user;
	}
	$scope.$watch('loggedIn', function(newValue){
		authService.loggedIn = $scope.loggedIn;
		console.log(authService.loggedIn);
	}, true);

}])