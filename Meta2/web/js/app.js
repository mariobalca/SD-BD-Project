var myApp = angular.module('myApp', ['ngRoute']);

myApp.config(function ($routeProvider){
	$routeProvider
	.when('/',{
		templateUrl: 'pages/main.html',
		controller: 'mainController'
	})
});

myApp.controller('mainController', ['$scope', '$log', '$http', function($scope, $log, $http){
}])

myApp.controller('authController', ['$scope', '$log', '$http', function($scope, $log, $http){
	$scope.username = '';
	$scope.password = '';

	$scope.login = function(){
		$http.post('http://localhost:8080/api/login', {'user':{'username': $scope.username, 'password': $scope.password}}).success(function(result){
			$log.info(result);
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