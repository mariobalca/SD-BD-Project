myApp.controller('projectController', ['$scope', '$log', '$http', '$routeParams', function($scope, $log, $http, $routeParams){
	var id = $routeParams.id;
	$http.post('http://localhost:8080/api/getProject', {'projectId': id}).success(function(result){
		$scope.project = result.project;
		$log.info(result.project);
	});
}])