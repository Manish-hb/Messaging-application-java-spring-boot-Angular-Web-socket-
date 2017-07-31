   
   MessagesLayoutApp.controller('loginCtrl',['$scope','$route', '$rootScope','$timeout','$location','$window','loginService','localStorageService', function($scope,$route, $rootScope, $timeout,$location,$window,loginService,localStorageService) {

        localStorageService.remove('user');
        $rootScope.user = {};
        $scope.user = $rootScope.user;

       $scope.name = "";
       $scope.password = "";
       $scope.loginSucess = "";
       $scope.user = "";
       
       $scope.login = function(){
        console.log($scope.name);
        console.log($scope.password);
           
        loginService.login($scope.name,function(response){
            if(response.call){
               console.log(response);
               $rootScope.user = response.data;
               localStorageService.set("user",$rootScope.user);
               $scope.loginSucess = "Login sucess";

                $timeout(function(){
                    $window.location.href ='#!/messages';
                },1000);


               }else{
                   console.log(response);
               }
        });
      }
      
   }]);