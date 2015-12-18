myApp.config(['$routeProvider', function ($routeProvider){
	$routeProvider
		.otherwise('/')
		.when('/',{
			templateUrl: 'pages/frontpage.html',
			controller: 'homeController',
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
			controller: 'homeController',
			access: {
				requiresLogin: true
			}
		})
		.when('/projects', {
			templateUrl: 'pages/projects.html',
			controller: 'projectsController'
		})
		.when('/projects/:id', {
			templateUrl: 'pages/project.html',
			controller: 'projectController'
		})
}]);

myApp.run(['$rootScope', '$location', 'authService', function($rootScope, $location, authService){
	$rootScope.$on('$routeChangeStart', function (event, next) {
	    var authorised;
	    if(next.access != undefined){
	    	authorised = authService.authorize(next.access.requiresLogin);
            if(!authorised){
            	$location.path('/login');
            }
	    }
    });
}])