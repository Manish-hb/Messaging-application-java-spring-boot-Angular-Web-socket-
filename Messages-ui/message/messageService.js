MessagesLayoutApp.service('messageService',['$rootScope','$http',function($rootScope,$http){

var service = {};
var responseData = {};


service.contactsList = function(id,callback){
    var headers = {headers:{'Accept': 'application/json','Content-type':'application/json'}}
    $http.defaults.useXDomain = true;
    $http.get("http://localhost:8080/comunicationChannelSorted/"+id,headers)
    .success(function(response){
        responseData.call = true;
        responseData.data = response;
        callback(responseData);
    }).error(function(response){
        responseData.call = false;
        responseData.data = response;
        callback(responseData);
    })

}

service.sendMessage = function(data,callback){
    var headers = {headers:{'Accept': 'application/json','Content-type':'application/json'}}
    $http.defaults.useXDomain = true;
    $http.post("http://localhost:8080/messages/send",data,headers)
    .success(function(response){
        responseData.call = true;
        responseData.data = response;
        callback(responseData);
    }).error(function(response){
        responseData.call = false;
        responseData.data = response;
        callback(responseData);
    })

}

service.findMessages = function(ccId,callback){
    var headers = {headers:{'Accept': 'application/json','Content-type':'application/json'}}
    $http.defaults.useXDomain = true;
    $http.get("http://localhost:8080/messages/"+ccId,headers)
    .success(function(response){
        responseData.call = true;
        responseData.data = response;
        callback(responseData);
    }).error(function(response){
        responseData.call = false;
        responseData.data = response;
        callback(responseData);
    })

}

service.findGroupMembers = function(userId,ccId,callback){
    var headers = {headers:{'Accept': 'application/json','Content-type':'application/json'}}
    $http.defaults.useXDomain = true;
    $http.get("http://localhost:8080/comunicationChannel/"+userId+"/groupMembers/"+ccId,headers)
    .success(function(response){
        responseData.call = true;
        responseData.data = response;
        callback(responseData);
    }).error(function(response){
        responseData.call = false;
        responseData.data = response;
        callback(responseData);
    })

}

service.findAllConnected = function(userId,callback){
    var headers = {headers:{'Accept': 'application/json','Content-type':'application/json'}}
    $http.defaults.useXDomain = true;
    $http.get("http://localhost:8080/comunicationChannel/"+userId+"/allConnected",headers)
    .success(function(response){
        responseData.call = true;
        responseData.data = response;
        callback(responseData);
    }).error(function(response){
        responseData.call = false;
        responseData.data = response;
        callback(responseData);
    })

}

service.createGroup =function(userId,data,callback){

    var headers = {headers:{'Accept': 'application/json','Content-type':'application/json'}}
    $http.defaults.useXDomain = true;
    $http.post("http://localhost:8080/comunicationChannel/"+userId+"/createGroup",data,headers)
    .success(function(response){
        responseData.call = true;
        responseData.data = response;
        callback(responseData);
    }).error(function(response){
        responseData.call = false;
        responseData.data = response;
        callback(responseData);
    })

}

    return service;

}]);