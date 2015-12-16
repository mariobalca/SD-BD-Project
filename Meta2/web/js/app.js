var myApp = angular.module('myApp', []);

myApp.controller('mainController', ['$scope', '$log', '$http', function($scope, $log, $http){
	$scope.username = '';
	$scope.password = '';

	$scope.login = function(){
		$http.post('http://localhost:8080/api/login', {'user':{'username': $scope.username, 'password': $scope.password}}).success(function(result){
			$log.info(result);
		});		
	}
}])