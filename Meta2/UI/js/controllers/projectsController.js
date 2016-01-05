myApp.controller('projectsController', ['IPAddress', '$scope', '$log', '$http', '$location', function(IPAddress, $scope, $log, $http, $location){
	$http.get('http://' + IPAddress + ':8080/api/getCurrentProjects').success(function(result){
		$scope.currentProjects = result.projects;
		angular.forEach(result.projects, function(project){
			angular.forEach(result.projects.paths, function(path){
				project.value += path.value;
			});
		});
		$log.info($scope.currentProjects);
	});
	$http.get('http://' + IPAddress + ':8080/api/getOlderProjects').success(function(result){
		$scope.olderProjects = result.projects;
		angular.forEach(result.projects, function(project){
			angular.forEach(result.projects.paths, function(path){
				project.value += path.value;
			});
		});
		$log.info($scope.olderProjects);
	});

	$scope.showProject = function(project){
		$location.path('/projects/' + project.id).replace();
	}
}])