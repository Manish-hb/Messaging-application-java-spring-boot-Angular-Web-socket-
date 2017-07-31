   
   MessagesLayoutApp.controller("messageCtrl",['$scope','$route', '$rootScope','localStorageService', '$timeout','$location','$window', 'messageService', '$stomp',
   function($scope,$route, $rootScope, localStorageService, $timeout,$location,$window,messageService,$stomp) {
		
		$rootScope.user=localStorageService.get("user");

		if($rootScope.user==null){
            $window.location.href ='#!/'
        }
		
		$scope.user = $rootScope.user;
		//console.log($scope.user);
		$scope.clist = [];
		$scope.selectedCcId = "";
		$scope.selectedCc = "";
		$scope.selectedContact = "";
		$scope.selectedContactUserId = "";
		$scope.chats = [];
		$scope.chattext = "";
		$scope.groupName = "";
		$scope.stompConnectionEstablished = false;

		$stomp.connect('http://localhost:8080/realTimeMessage-websocket', {})
            .then(function (frame) {
				$scope.stompConnectionEstablished = true;
                $scope.subscribeUserId();
				$scope.subscribeCcId();
         });

		 $scope.subscribeUserId = function(){
			$stomp.subscribe('/topic/user/'+$scope.user.id, 
                    function (payload, headers, res) {
                        //console.log(payload);
						if(payload.ccId != $scope.selectedCcId && payload.fromUser.userId!=$scope.user.id){
							$scope.clist.forEach(function(item){
								if(item.id==payload.ccId){
									item.unRead = item.unRead+1; 
									$scope.$apply();
								}
							})
						}
                });	 
		 }

		 $scope.subscribeCcId = function(){
			$stomp.subscribe('/topic/ccId/'+$scope.selectedCcId, 
                    function (payload, headers, res) {
                        //console.log(payload);
						if(payload.ccId == $scope.selectedCcId && payload.fromUser.userId!=$scope.user.id){
							$scope.chats.push(payload);

							$scope.$apply();

							$timeout(function(){
							$("#messagebox").animate({ scrollTop: $("#messagebox")[0].scrollHeight}, 10);
							},80);
						}
						
                });	 
		 }

		messageService.contactsList($scope.user.id,function(response){
			if(response.call){

				$scope.clist = response.data;
				$scope.findMessages($scope.clist[0]);
				//console.log(response.data);
			}

		});

		$scope.findMessages = function(comChannel){
			$scope.selectedCc = comChannel;
			$scope.selectedCcId= comChannel.id;
			$scope.groupMembers = [];
			$scope.editGroupCheck = false;

			if($scope.stompConnectionEstablished){
				$scope.subscribeCcId();
			}

			if(comChannel.group!=true){
				
				$scope.selectedContact = comChannel.contact;
				$scope.selectedContactUserId = comChannel.contact.userId;

			}else{
				messageService.findGroupMembers($scope.user.id,$scope.selectedCcId,function(response){
					if(response.call){
						$scope.groupMembers = response.data;
						//console.log(response.data);
					}
				});
			}

			messageService.findMessages($scope.selectedCcId,function(response){
				if(response.call){
					//console.log(response.data);
					$scope.chats =response.data;
				}
			});

			$timeout(function(){
				$("#messagebox").animate({ scrollTop: $("#messagebox")[0].scrollHeight}, 10);
				
				$scope.clist.forEach(function(item){
					if(item.id==$scope.selectedCcId){
						item.unRead = 0; 
						$scope.$apply();
					}
				});
			},80);

		}

		$scope.sendMessage = function(){
			
			var fromUser = {
				userId : $scope.user.id,
				name   : $scope.user.name
			};
			var message = {
				fromUser : fromUser,
				ccId 	 : $scope.selectedCcId,
				message  : $scope.chattext
			}

			if($scope.selectedCc.group!=true){
				message.toUserId = $scope.selectedContactUserId;
			}

			messageService.sendMessage(message,function(response){
				if(response.call){
					$scope.chattext = "";
					$scope.chats.push(response.data);

					$timeout(function(){
						$("#messagebox").animate({ scrollTop: $("#messagebox")[0].scrollHeight}, 10);
					},80);
				}
			});
		}

		$scope.myModel = [];

		$scope.myOptions = [];

		$scope.myConfig = {
		create: false,
		valueField: 'id',
		labelField: 'name',
		delimiter: '|',
		placeholder: 'Pick friends',
		onInitialize: function(selectize){
			// receives the selectize object as an argument
		},
		// maxItems: 1
		};

		$scope.createGroupPopUp = function(){

			messageService.findAllConnected($scope.user.id,function(response){
				if(response.call){
					$scope.AllConnected = response.data;
					$scope.AllConnected.forEach(function(item){
								$scope.myOptions.push(item);	
							})
				}
			})

			$('#newGroup').modal("show");
		}

		$scope.editGroup = function(){
			$scope.groupName ="";
			if($scope.selectedCc.group==true){
				$scope.groupName = $scope.selectedCc.groupName;
				$scope.editGroupCheck = true;
				$scope.createGroupPopUp();

				$scope.groupMembers.forEach(function(item){
					$scope.myModel.push(item.id);
				});
			}
		}

		$scope.createGroup = function(){

			var name = $scope.groupName;

			var data = {
						"group":true,
						"groupName":name,
						"userIds":$scope.myModel
					}
			if($scope.editGroupCheck == true && $scope.selectedCc.group==true){
				data.id = $scope.selectedCc.id;
			}

			messageService.createGroup($scope.user.id,data,function(response){
				if(response.call){
					//console.log(response.data);
					if($scope.editGroupCheck != true){
						$scope.clist.unshift(response.data);
					}else{
						$scope.findMessages(response.data);
					}
					$scope.editGroupCheck = false;
				}
				$('#newGroup').modal("hide");
			})
			
		}

		$scope.logOut = function(){
        localStorageService.remove('user');
        $rootScope.user = {};
        $scope.user = $rootScope.user;

        $window.location.href ='#!/'
      }
      
   }]);