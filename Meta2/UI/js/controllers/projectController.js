myApp.controller('projectController', ['$scope', '$log', '$http', '$routeParams', 'authService', function($scope, $log, $http, $routeParams, authService){
	var id = $routeParams.id;
	$scope.loggedIn = authService.loggedIn;
	$http.post('http://localhost:8080/api/getProject', {'projectId': id}).success(function(result){
		$scope.project = result.project;
		$log.info(result.project);
	});

	$scope.admins = [];
	$scope.users = [];

	$http.post('http://localhost:8080/api/getAdmins', {'projectId': id}).success(function(result){
		$scope.admins = result.admins;
		$http.get('http://localhost:8080/api/getUsers').success(function(result){
			angular.forEach(result.users, function(user){
				var bool = true;
				angular.forEach($scope.admins, function(admin){
					if(user.id == admin.id)
						bool = false 
				});
				if(bool)
					$scope.users.push(user);
			});
			$log.info($scope.users);
		});
	});	

	$scope.pledge = {}
	$scope.pledge.value = 0;

	$scope.pledgeProject = function(){
		authService.updateRequestId();
		$http.post('http://localhost:8080/api/financeProject', {'projectId': $scope.project.id, 'requestId': authService.user.requestId, 'userId': authService.user.id, 'pathId': 1, 'value': $scope.pledge.value}).success(function(result){
		});
	}
	$scope.isAdmin = function(){
		var result = false;
		angular.forEach($scope.admins, function(admin){
			if(admin.id == authService.user.id)
				result = true;
		});
		return result;
	}

	$scope.newAdmin = {}
	$scope.changedValue = function(value) {
		$scope.newAdmin = value;
	}
	
	$scope.addAdmin = function(){
		authService.updateRequestId();
		$http.post('http://localhost:8080/api/addAdmin', {'projectId': $scope.project.id, 'requestId': authService.user.requestId, 'userId': authService.user.id, 'newAdminName': $scope.newAdmin.username}).success(function(result){
			$scope.admins.push($scope.newAdmin);
		});
	}
}])