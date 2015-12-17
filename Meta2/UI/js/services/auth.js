myApp.service('authService', function(){
    this.user = {
        userId: 0,
        username: '',
        requests: 0,
        balance: 0
    }
    
    $http.post('http://localhost:8080/api/getSession', {'user':{'username': $scope.username, 'password': $scope.password}}).success(function(result){
		$log.info(result);
		if(result.response.success) {
			this.user.userId = result.user.userId;
			this.user.username = result.user.username;
			this.user.requests = result.user.requests;
			this.user.balance = result.user.balance;
		}
	});
})