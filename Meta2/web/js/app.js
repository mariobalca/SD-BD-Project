var myApp = angular.module('myApp', ['ngRoute']);

myApp.config(['$routeProvider', function ($routeProvider){
	$routeProvider
		.when('/',{
			templateUrl: 'pages/frontpage.html',
			controller: 'homeController'
		})
		.when('/login', {
			templateUrl: 'pages/login.html',
			controller: 'authController'
		})
		.when('/register', {
			templateUrl: 'pages/register.html',
			controller: 'authController'
		})
		.when('/dashboard', {
			templateUrl: 'pages/dashboard.html',
			controller: 'homeController'
		})

}]);

myApp.controller('homeController', ['$scope', '$log', '$http', 'authService', function($scope, $log, $http, authService){
	$log.info(authService.user);
}])

myApp.controller('authController', ['$scope', '$log', '$http', 'authService', '$location', function($scope, $log, $http, authService, $location){
	$scope.username = '';
	$scope.password = '';

	$scope.login = function(){
		$http.post('http://localhost:8080/api/login', {'user':{'username': $scope.username, 'password': $scope.password}}).success(function(result){
			$log.info(result);
			$scope.response = result.response;
			if(result.response.success){
				authService.user.userId = result.user.userId;
				authService.user.username = result.user.username;
				authService.user.requests = result.user.requests;
				authService.user.balance = result.user.balance;
				$location.path('#/dashboard').replace();
			}
		});
	}
	$scope.register = function(){
		$http.post('http://localhost:8080/api/register', {'user':{'username': $scope.username, 'password': $scope.password}}).success(function(result){
			$log.info(result);
			$scope.response = result.response;
			if(result.response.success) {
				authService.user.userId = result.user.userId;
				authService.user.username = result.user.username;
				authService.user.requests = result.user.requests;
				authService.user.balance = result.user.balance;
			}
		});
	}
}])

myApp.controller('projectsController', ['$scope', '$log', '$http', function($scope, $log, $http){
}])

myApp.controller('pathsController', ['$scope', '$log', '$http', function($scope, $log, $http){
}])

myApp.controller('rewardsController', ['$scope', '$log', '$http', function($scope, $log, $http){
}])

myApp.controller('extrasController', ['$scope', '$log', '$http', function($scope, $log, $http){
}])

myApp.controller('messagesController', ['$scope', '$log', '$http', function($scope, $log, $http){
}])

myApp.controller('projectsController', ['$scope', '$log', '$http', function($scope, $log, $http){
}])