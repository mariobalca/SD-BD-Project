myApp.controller('projectController', ['IPAddress', '$scope', '$log', '$http', '$routeParams', 'authService', function(IPAddress, $scope, $log, $http, $routeParams, authService){
	var id = $routeParams.id;
	$scope.loggedIn = authService.loggedIn;
	$scope.project = {}
	$scope.admins = [];
	$scope.users = [];
	$scope.pledge = {}
	$scope.extra = {}
	$scope.newAdmin = {}
	$scope.admin = {}
	$scope.message = {}


	$http.post('http://' + IPAddress + ':8080/api/getProject', {'projectId': id}).success(function(result){
		$scope.project = result.project;
		$scope.project.value = 0;
		$scope.project.isActive = function(){		
			if($scope.project.active)
				return true;
			else
				return false;
		}
		angular.forEach(result.project.paths, function(path){
			$scope.project.value += path.value;
		});
		$log.info(result.project);
	});

	$http.post('http://' + IPAddress + ':8080/api/getAdmins', {'projectId': id}).success(function(result){
		$scope.admins = result.admins;
		$http.get('http://' + IPAddress + ':8080/api/getUsers').success(function(result){
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

	$scope.pledgeProject = function(){
		authService.updateRequestId();
		$http.post('http://' + IPAddress + ':8080/api/financeProject', {'projectId': $scope.project.id, 'requestId': authService.user.requestId, 'userId': authService.user.id, 'pathId': $scope.pledge.path, 'value': $scope.pledge.value}).success(function(result){
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

	$scope.changedValue = function(value) {
		$scope.newAdmin = value;
	}
	
	$scope.addAdmin = function(){
		authService.updateRequestId();
		$http.post('http://' + IPAddress + ':8080/api/addAdmin', {'projectId': $scope.project.id, 'requestId': authService.user.requestId, 'userId': authService.user.id, 'newAdminName': $scope.newAdmin.username}).success(function(result){
			$scope.admins.push($scope.newAdmin);
		});
	}

	$scope.askQuestion = function(){
		authService.updateRequestId();
		$scope.message.fromUserId = authService.user.id;
		$http.post('http://' + IPAddress + ':8080/api/sendMessage', {'projId': $scope.project.id, 'requestId': authService.user.requestId, 'userId': authService.user.id, 'message': $scope.message}).success(function(result){
			$scope.project.messages.push()
		});
	}

	$scope.answerQuestion = function(messageId){
		authService.updateRequestId();
		$scope.message.response = $scope.message.draftResponse;
		$http.post('http://' + IPAddress + ':8080/api/answerMessage', {'messageId': messageId, 'requestId': authService.user.requestId, 'userId': authService.user.id, 'resposta': $scope.message.response}).success(function(result){
			
		});
	}

	$scope.openMessageModal = function(message){
		$('#viewQuestionModal').on('show.bs.modal', function(event){
			var modal = $(this);
			$scope.message = message;
		});
		$('#viewQuestionModal').modal();
	}

	$scope.openRewardModal = function(reward){
		$('#viewRewardModal').on('show.bs.modal', function(event){
			var modal = $(this);
			$scope.reward = reward;
		});
		$('#viewRewardModal').modal();
	}

	$scope.openAdminModal = function(admin){
		$('#viewAdminModal').on('show.bs.modal', function(event){
			var modal = $(this);
			$scope.admin = admin;
		});
		$('#viewAdminModal').modal();
	}

	$scope.addExtraLevel = function(){
		authService.updateRequestId();
		$http.post('http://' + IPAddress + ':8080/api/createExtra', {'projectId': $scope.project.id, 'requestId': authService.user.requestId, 'userId': authService.user.id, 'extra': $scope.extra}).success(function(result){
		});
	}

	$scope.addReward = function(){
		authService.updateRequestId();
		$http.post('http://' + IPAddress + ':8080/api/createReward', {'projectId': $scope.project.id, 'requestId': authService.user.requestId, 'userId': authService.user.id, 'reward': $scope.reward}).success(function(result){
		});
	}

	$scope.removeReward = function(rewardId){
		authService.updateRequestId();
		$http.post('http://' + IPAddress + ':8080/api/removeReward', {'rewardId': rewardId, 'requestId': authService.user.requestId, 'userId': authService.user.id}).success(function(result){
		});
	}

	$scope.removeAdmin = function(adminId){
		authService.updateRequestId();
		$http.post('http://' + IPAddress + ':8080/api/removeAdmin', {'projectId': $scope.project.id, 'requestId': authService.user.requestId, 'userId': authService.user.id, 'adminId': adminId}).success(function(result){
		});
	}

	$scope.cancelProject = function(projectId){
		authService.updateRequestId();
		$http.post('http://' + IPAddress + ':8080/api/cancelProject', {'projectId': projectId, 'userId': authService.user.id, 'requestId': authService.user.requestId}).success(function(){
		});
	}
}])