myApp.controller('dashboardController', ['$scope', '$log', '$http', 'authService', '$location', function($scope, $log, $http, authService, $location){
	$http.post('http://localhost:8080/api/getAdminProjects', {'userId': authService.user.id }).success(function(result){
		$scope.projects = result.projects;
	});

	$http.post('http://localhost:8080/api/getUserRewards', {'userId': authService.user.id }).success(function(result){
		$scope.rewards = result.rewards;
		$log.info($scope.rewards);
	});

	$scope.users = [];
	$http.get('http://localhost:8080/api/getUsers').success(function(result){
		angular.forEach(result.users, function(user){
			$scope.users.push(user);
		});
	});

	$scope.project = {};
	$scope.project.rewards = [];
	$scope.project.paths = [];

	$scope.createProject = function(){
		$log.info($scope.project);
		authService.updateRequestId();
		$http.post('http://localhost:8080/api/createProject', {'project': $scope.project, 'userId': authService.user.id, 'requestId': authService.user.requestId}).success(function(result){
			$scope.projects.push(result.project);
		});
	}

	$scope.clearProject = function(){
		$scope.project = {};
	}

	$scope.showProject = function(project){
		$location.path('/projects/' + project.id).replace();
	}

	$scope.reward = {};
	$scope.userToGive = {};
	$scope.changedReward = function(reward){
		$scope.reward = reward;
	}
	$scope.changedUserToGive = function(user){
		$scope.userToGive = user;
	}

	$scope.giveReward = function(){
		authService.updateRequestId();
		$http.post('http://localhost:8080/api/giveReward', {'rewardUserId': $scope.reward.id, 'userId': authService.user.id, 'requestId': authService.user.requestId, 'receiverUserName': $scope.userToGive.username}).success(function(result){
			if(result.response.success){
				angular.forEach($scope.rewards, function(item){
					if(item.id == $scope.reward.id)
						var index = $scope.rewards.indexOf(item);
						$scope.rewards.splice(index, 1);
				});
			}
		});
	}
}])