myApp.controller('projectController', ['$scope', '$log', '$http', '$routeParams', 'authService', function($scope, $log, $http, $routeParams, authService){
	var id = $routeParams.id;
	$scope.loggedIn = authService.loggedIn;
	$scope.project = {}
	$scope.admins = [];
	$scope.users = [];
	$scope.pledge = {}
	$scope.extra = {}
	$scope.newAdmin = {}
	$scope.message = {}


	$http.post('http://localhost:8080/api/getProject', {'projectId': id}).success(function(result){
		$scope.project = result.project;
		$scope.project.value = 200;
		$log.info(result.project);
	});

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

	$scope.pledgeProject = function(){
		authService.updateRequestId();
		$http.post('http://localhost:8080/api/financeProject', {'projectId': $scope.project.id, 'requestId': authService.user.requestId, 'userId': authService.user.id, 'pathId': $scope.pledge.path, 'value': $scope.pledge.value}).success(function(result){
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
		$http.post('http://localhost:8080/api/addAdmin', {'projectId': $scope.project.id, 'requestId': authService.user.requestId, 'userId': authService.user.id, 'newAdminName': $scope.newAdmin.username}).success(function(result){
			$scope.admins.push($scope.newAdmin);
		});
	}

	$scope.askQuestion = function(){
		authService.updateRequestId();
		$scope.message.fromUserId = authService.user.id;
		$http.post('http://localhost:8080/api/sendMessage', {'projId': $scope.project.id, 'requestId': authService.user.requestId, 'userId': authService.user.id, 'message': $scope.message}).success(function(result){
			$scope.project.messages.push()
		});
	}

	$scope.answerQuestion = function(messageId){
		authService.updateRequestId();
		$scope.message.response = $scope.message.draftResponse;
		$http.post('http://localhost:8080/api/answerMessage', {'messageId': messageId, 'requestId': authService.user.requestId, 'userId': authService.user.id, 'resposta': $scope.message.response}).success(function(result){
			
		});
	}

	$scope.openModal = function(message){
		$('#viewQuestionModal').on('show.bs.modal', function(event){
			var modal = $(this);
			$scope.message = message;
		});
		$('#viewQuestionModal').modal();
	}


	$scope.addExtraLevel = function(){

	}
}])