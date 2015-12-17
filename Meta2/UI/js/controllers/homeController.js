myApp.controller('homeController', ['$scope', '$log', '$http', 'authService', function($scope, $log, $http, authService){
	$log.info(authService);
	$scope.auth = authService;
	$log.info($scope.auth);
}])