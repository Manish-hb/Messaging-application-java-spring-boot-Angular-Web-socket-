MessagesLayoutApp.service('loginService',['$rootScope','$http',function($rootScope,$http){
    
    var service = {};
    var responseData = {};
    
    service.login= function(name,callback){ 
        var headers = {headers:{'Accept': 'application/json','Content-type':'application/json'}};
        $http.defaults.useXDomain=true; 
        $http.get('http://localhost:8080/user/'+name,headers)
        .success(function(response){
    			responseData.call=true;
    			responseData.data=response;
    			callback(responseData);   			
    		}).error(function(response){
    			responseData.call=false;
    			responseData.data=response;
    			callback(responseData);  			
    		});
    };
    
    
    return service;
}]);