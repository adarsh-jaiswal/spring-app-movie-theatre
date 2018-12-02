var app=angular.module('myApp',["ngRoute"])
.run(function($rootScope,$http,$location) {
	$rootScope.current_user ='';
	$rootScope.authenticated=false;

	$rootScope.signout=function(){

		$http({
			method:'GET',
			url:'auth/signout'
		}).then(function(response) {
			$rootScope.current_user ='';
			$rootScope.authenticated=false;
			$location.path("/login");
		})
	}

	$rootScope.sessionValidation = function() {
		$http({
			method:'GET',
			url:''
		}).then(function(response) {
			$rootScope.current_user = response.data;
			$rootScope.authenticated=true;
		})
	}
	$rootScope.showUi = function () {
	    let loader = document.getElementById('loader');
	    loader.classList.remove("loader");
	}
	$rootScope.blockUi =  function () {
	    let loader = document.getElementById('loader');
	    loader.classList.add("loader");
	}
});

app.config(function($routeProvider){
	$routeProvider .when('/', {
		templateUrl: 'resources/html/main.html',
		controller: 'authController'
	}).when("/screens",{
			/*resolve : {
			"check" : function($rootScope, $location) {
				$rootScope.sessionValidation();
			}
		},*/
		templateUrl:"resources/html/screens.html",
		controller:"screensController"
	});
});

app.controller('authController',function($scope,$http,$rootScope,$location){

	$scope.friendPhnNo='';
	$scope.selectedFriend={};
	$scope.loginAuthentication=function() {
		$location.path("/screens");
		/*$http({
			method:'POST',
			url:'authenticateUser',
			data:$scope.customer
		}).then(function(response) {
			$scope.customer={};
			if(response.data != '') {
				
				$rootScope.current_user = response.data;
				console.log($rootScope.current_user .name);
				$rootScope.authenticated=true;
				$location.path("/screens");
			}
		});*/
	}


});

app.controller('screensController', function($scope,$http,$rootScope,$location){
	$scope.divPart = 0;
	$scope.selectedScreen = '';
	$scope.availableSeatsJson ='';
	$scope.reserveSeatsJson = '';
	$scope.customAvailableSeatsJson = '';
	$scope.choice = '';
	$scope.numberOfSeats == ''
		
	$scope.divPartition = function(s) {
		console.log("Within user controller partition method");
		$scope.divPart = s;
		if (s == 1) {
			console.log(s);
			$rootScope.blockUi();
			$http({
				method:'GET',
				url:'screens',
			}).then(function(response){
				console.log("Within response");
				if(response.data != '') {
					console.log("response not null");
					console.log(response.data);
					$scope.allScreens = response.data;
				}

			});
			$rootScope.showUi();
		} else if (s!= 2 && $scope.selectedScreen =='') {
			$scope.divPart = 6;
		} else if (s == 3) {
			$rootScope.blockUi();
			$http({
				method:'GET',
				url:'screens/' + $scope.selectedScreen + '/seats?status=unreserved',
			}).then(function(response){
				console.log(response);
				if(response.data != '') {
					$scope.availableSeatsJson = JSON.stringify(response.data);
				}

			});
			$rootScope.showUi();
		} 
	};
	$scope.saveScreenDetails = function() {
		$http({
			method:'POST',
			url:'screens',
			data:$scope.screenDetailsJson
		}).then(function(response) {
			console.log(response);
			if(response.data != '') {
			
			} else {
				
			}
		});
	}
	$scope.reserveSeatDetails = function() {
		$http({
			method:'POST',
			url:'screens/'+ $scope.selectedScreen +'/reserve',
			data:$scope.reserveSeatsJson
		}).then(function(response) {
			console.log(response);
			if(response.data != '') {
				console.log(response.data);
				
			} else {
				
			}
		});
	}
	
	$scope.findCustomSeatDetails = function() {
		if ($scope.numberOfSeats == undefined || $scope.numberOfSeats =='') {
			alert("Enter number of Seats");
			return false;
		}
		if ($scope.choice == undefined || $scope.choice == '') {
			alert("Enter Choice");
			return false
		}
		$rootScope.blockUi();
		$http({
			method:'GET',
			url:'screens/'+ $scope.selectedScreen +'/seats?numSeats=' + $scope.numberOfSeats + '&choice='+ $scope.choice,
			data:$scope.reserveSeatsJson
		}).then(function(response) {
			console.log(response);
			if(response.data != '') {
				$scope.customAvailableSeatsJson = JSON.stringify(response.data);
				
			} else {
				
			}
		});
		$rootScope.showUi();
	}
	
});

