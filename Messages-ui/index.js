var MessagesLayoutApp = angular.module('MessagesLayoutApp',
            [
                'ngRoute',
                'ngStomp',
                'selectize',
                'LocalStorageModule'
            ]
    )
MessagesLayoutApp.config(['$routeProvider',function($routeProvider){

    $routeProvider
        .when('/',{
            templateUrl:'login/login.html',
            controller:'loginCtrl'
        })

         .when('/messages',{
             templateUrl:'message/messages.html',
             controller:'messageCtrl'
         })

}]);

MessagesLayoutApp.config([  
          '$locationProvider',
          function($locationProvider) {
              $locationProvider.html5Mode(false);
              $locationProvider.hashPrefix('!');
          }
      ]);