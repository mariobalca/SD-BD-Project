myApp.controller('navController', ['$scope', '$log', '$http', 'authService', '$cookieStore', '$location', function($scope, $log, $http, authService, $cookieStore, $location){
	$scope.loggedIn = authService.loggedIn;
	$scope.$watch(function(){ return authService.loggedIn;}, function(newValue){
		$scope.loggedIn = newValue;
		if($scope.loggedIn) {
			$scope.user = authService.user;
			$scope.getBalance();		
		}
	},true);
	
	$scope.getBalance = function(){
		$http.post('http://localhost:8080/api/getBalance', {'user': {'id': $scope.user.id}}).success(function(result){
			$scope.user.balance = result.balance;
		});
	}

	$scope.logout = function(){
		delete $scope.user;
		authService.loggedIn = false;
		$cookieStore.remove('user');
		$location.path('/').replace();
	}
}])