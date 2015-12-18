myApp.controller('dashboardController', ['$scope', '$log', '$http', 'authService', '$location', function($scope, $log, $http, authService, $location){
	$http.post('http://localhost:8080/api/getAdminProjects', {'userId': authService.user.id }).success(function(result){
		$scope.projects = result.projects;
	});

	$http.post('http://localhost:8080/api/getUserRewards', {'userId': authService.user.id }).success(function(result){
		$scope.rewards = result.rewards;
	});

	$scope.project = {};
	$scope.project.rewards = [];
	$scope.project.paths = [];

	$scope.createProject = function(){
		$log.info($scope.project);
		$http.post('http://localhost:8080/api/createProject', {'project': $scope.project, 'userId': authService.user.id, 'requestId': 124}).success(function(result){
			$scope.projects.push(result.project);
		});
	}

	$scope.clearProject = function(){
		$scope.project = {};
	}

	$scope.showProject = function(project){
		$location.path('/projects/' + project.id).replace();
	}
}])