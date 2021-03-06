myApp.service('authService', ['$cookieStore', '$log', '$http', '$location', function($cookieStore, $log, $http, $location){
	this.user = {
		id: 0,
		username: '',
		requestId: 0,
		balance: 0,
		
	};
	this.loggedIn = false;

	this.updateRequestId = function(){
		this.user.requestId++;
		$cookieStore.put('user', this.user);
	}

	if($cookieStore.get('user')){
		this.loggedIn = true;
		this.user = $cookieStore.get('user');
	}

	this.authorize = function(loginRequired, requiredPermissions, permissionCheckType){
		if(loginRequired == true && this.loggedIn == false)
			return false;
		else
			return true;
	}
}])