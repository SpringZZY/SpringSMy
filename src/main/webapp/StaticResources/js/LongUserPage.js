var login = angular.module('loginApp',[]);
	login.controller("loginController",function($scope,$http,$rootScope){
		$scope.UserData = {
				userName : "",
				Password : ""
		}
		$scope.submitLogins = function(){
			$http({
				method  : 'POST',
				url     : 'login.do',
				//headers : {  'Content-Type' : 'application/json;charset=utf-8' }  ,//'Content-Type': 'application/json;charset=utf-8'
				params  : {
					
					//这样写 后台就不需要再转json再去拿值了
					"wdName"   : $scope.UserData.userName,
					"password" : $scope.UserData.Password
					
					//这样写 传给后台就是一个json数据，需要转json同时需要加@requestBody注解
					/*	wdName   : $scope.UserData.userName,
						password : $scope.UserData.Password*/
					
				}
			}).success(function(data){
				   if (data == "") {
					   window.open("http://localhost:8080/SpringSMy/index.do");
					   window.close();
				   } else {
					   alert("错的")
				   };
			});
		}
})
/*$scope.UserData={
				userName : '',
				Password : ''
		}
		var jsonData = '';
		$scope.submitLogins = function(){
			jsonData = JSON.stringify($scope.UserData);
		}
		data = {
				json : jsonData,
		},
		postCfg = {
			headers : {'Content-Type' : 'applictaion/x-www-form-urlencoded;charset=UTF-8'},
			params : {'jsonstr' : jsonData}
		}
		var url = 'http//localhost:8080/SpringSMy/login.do';
		$http.post(url,data,postCfg).success(function(response){
			var result = response.result;
			if(result == "success"){
				alert("123");
			}else{
				alert("456");
			}
		})*/