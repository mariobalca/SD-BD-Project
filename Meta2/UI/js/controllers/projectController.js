myApp.controller('projectController', ['$scope', '$log', '$http', '$routeParams', 'authService', function($scope, $log, $http, $routeParams, authService){
	var id = $routeParams.id;
	$scope.loggedIn = authService.loggedIn;
	$http.post('http://localhost:8080/api/getProject', {'projectId': id}).success(function(result){
		$scope.project = result.project;
		$log.info(result.project);
	});
}])