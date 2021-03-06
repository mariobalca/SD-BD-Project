myApp.controller('authController', ['IPAddress', '$scope', '$log', '$http', 'authService', '$location', '$cookieStore', function(IPAddress, $scope, $log, $http, authService, $location, $cookieStore){
	$scope.username = '';
	$scope.password = '';

	$scope.login = function(){
		$http.post('http://' + IPAddress + ':8080/api/login', {'user':{'username': $scope.username, 'password': $scope.password}}).success(function(result){			
			if(result.response.success) {
				authService.loggedIn = true;
				authService.user.id = result.user.id;
				authService.user.username = result.user.username;
				authService.user.requestId = result.user.requests;
				authService.user.balance = result.user.balance;
				$cookieStore.put('user', authService.user);
				$location.path('/dashboard').replace();
			}
			else {
				$scope.response = result.response;
			}
		});
	}
	$scope.register = function(){
		$http.post('http://' + IPAddress + ':8080/api/register', {'user':{'username': $scope.username, 'password': $scope.password}}).success(function(result){			
			if(result.response.success) {
				authService.loggedIn = true;
				authService.user.id = result.user.id;
				authService.user.username = result.user.username;
				authService.user.requestId = result.user.requests;
				authService.user.balance = result.user.balance;
				$cookieStore.put('user', authService.user);
				$location.path('/dashboard').replace();		
			}
			else {
				$scope.response = result.response;
			}
		});
	}
}])
