myApp.controller('authController', ['$scope', '$log', '$http', 'authService', '$location', function($scope, $log, $http, authService, $location){
	$scope.username = '';
	$scope.password = '';

	$scope.login = function(){
		$http.post('http://localhost:8080/api/login', {'user':{'username': $scope.username, 'password': $scope.password}}).success(function(result){
			$log.info(result);
			if(result.response.success){
				authService.user.userId = result.user.userId;
				authService.user.username = result.user.username;
				authService.user.requests = result.user.requests;
				authService.user.balance = result.user.balance;
				$location.path('/dashboard').replace();
			}
		});
	}
	$scope.register = function(){
		$http.post('http://localhost:8080/api/register', {'user':{'username': $scope.username, 'password': $scope.password}}).success(function(result){
			$log.info(result);
			if(result.response.success) {
				authService.user.userId = result.user.userId;
				authService.user.username = result.user.username;
				authService.user.requests = result.user.requests;
				authService.user.balance = result.user.balance;
				$location.path('/dashboard').replace();
			}
		});
	}
}])
