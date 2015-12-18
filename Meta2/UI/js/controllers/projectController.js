myApp.controller('projectController', ['$scope', '$log', '$http', '$routeParams', 'authService', function($scope, $log, $http, $routeParams, authService){
	var id = $routeParams.id;
	$scope.loggedIn = authService.loggedIn;
	$http.post('http://localhost:8080/api/getProject', {'projectId': id}).success(function(result){
		$scope.project = result.project;
		$log.info(result.project);
	});

	$scope.pledge = {}
	$scope.pledge.value = 0;

	$scope.pledgeProject = function(project){
		$http.post('http://localhost:8080/api/financeProject', {'projectId': project.id, 'requestId': authService.user.requestId, 'userId': authService.user.id, 'pathId': 1, 'value': $scope.pledge.value}).success(function(result){
			authService.user.requestId++;
		});
	}
}])