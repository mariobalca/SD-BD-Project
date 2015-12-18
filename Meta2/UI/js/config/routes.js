myApp.config(['$routeProvider', function ($routeProvider){
	$routeProvider
		.otherwise('/')
		.when('/',{
			templateUrl: 'pages/frontpage.html',
			controller: 'homeController'
		})
		.when('/login', {
			templateUrl: 'pages/login.html',
			controller: 'authController'
		})
		.when('/register', {
			templateUrl: 'pages/register.html',
			controller: 'authController'
		})
		.when('/dashboard', {
			templateUrl: 'pages/dashboard.html',
			controller: 'homeController'
		})
}]);