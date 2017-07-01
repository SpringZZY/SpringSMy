 //这里因为在<HTML>标签中 对ng-app赋值了，那么就需要在脚本中对其声明。（必须步骤）
var app = angular.module('AddUser', []);
	app.controller('uerController',function($scope,$http){
		
//分页变量	开始
		$scope.count = 0,	//总记录数
		$scope.page = 0;
		$scope.pageData = {
				pageSize  : 10,	//分页大小
				pageNo   : 1,	//第一页
			};
		
//分页变量     结束
		
		
		
//初始化值（“双向绑定Input”）
		$scope.AddUser = {
			UserName  : "",
			UserId	  : "",
			UserStart : "",
		};
//提交
		$scope.processForm = function() {
		 $http({
		  method : 'POST',
		  url  	 : 'Add.do',
		  data 	 :
			  {
			  	UserData : $scope.AddUser,//JSON.stringify($scope.AddUser),
			  },
			  
		  headers : { 'Content-Type': 'application/json;charset=utf-8' },
		  
		}).success(function(data){
			   if (!data.Result) {
			   } else {
				   $scope.AddUser = {
							UserName  : "",
							UserId	  : "",
							UserStart : "",
						};
				   selectUser();
				   pageCount();
			   };
		});
		 
		};
		
		
//当前页面初始化Load事件
		 $scope.$watch('$viewContentLoaded', function() {  
			 	selectUser();
			 	pageCount()
	        });
		 
		 
//页面初加载，查询事件
		function selectUser(){
			 $http({
				  method  : 'POST',
				  type	  : 'POST',
				  url  	  : 'selectUser.do',
				  headers : { 'Content-Type': 'application/json;charset=utf-8' },
				  data  : {
				   // "pageData" : JSON.stringify($scope.pageData)
					  "pageNo"   : $scope.pageData.pageNo, 
					  "pageSize" : $scope.pageData.pageSize
				  }
				  
				}).success(function(data){
					   if (data.UserData != undefined) {
						   $scope.array = data.UserData;
					   } else {
						   alert(data.UserData)
					   };
				});
			
		};
		
		
//分页共多少条，多少页。
		function pageCount(){
			 $http({
				  method  : 'POST',
				  type	  : 'POST',
				  url  	  : 'pageCount.do',
				}).success(function(data){
					   if (data.success != null || data.success != undefined) {
						   $scope.count = data.success;
						   $scope.page = Math.ceil(data.success/$scope.pageData.pageSize);//计算页数
						   $scope.pageList = calculateIndexes($scope.count,$scope.page,10);
					   } else {
						   alert("分页计算总数失败！");
					   };
				});
		};
		
//分页算法,返回一个indexes的数组，把这个数组返回给pageList集合让其页面中遍历<li>标签。
		function calculateIndexes  (current, length, displayLength) {  
			   var indexes = [];  
			   var start = Math.round(current - displayLength / 2);  
			   var end = Math.round(current + displayLength / 2);  
			    if (start <= 1) {  
			        start = 1;  
			        end = start + displayLength - 1;  
			       if (end >= length - 1) {  
			           end = length - 1;  
			        }  
			    }  
			    if (end >= length - 1) {  
			       end = length;  
			        start = end - displayLength + 1;  
			       if (start <= 1) {  
			           start = 1;  
			        }  
			    }  
			    for (var i = start; i <= end; i++) {  
			        indexes.push(i);  
			    }  
			    return indexes;  
			  };  
			  
		//上一页：减法运算(3->2,2->1... ...)
		$scope.UpPage = function (){
			var _pageNo =  $scope.pageData.pageNo;//获取No页
			if(_pageNo > 1){					  //如果小于1那么就是第一页。（首页）
				$scope.pageData.pageNo = _pageNo-1
				this.PageSelectUser($scope.pageData.pageNo,$scope.pageData.pageSize);
			}
		}
		
		//下一页是：加法运算(1->2,2->3... ...)
		$scope.DownPage = function (){
			var _pageNo =  $scope.pageData.pageNo;	//获取No页
			var _page   =  $scope.page;				//获取最大No页
			if(_pageNo < _page){					//如果No页大于最大No页那么就是尾页（最后一页）
				$scope.pageData.pageNo = _pageNo+1
				this.PageSelectUser($scope.pageData.pageNo,$scope.pageData.pageSize);
			}
		}
		
		//尾页
		$scope.ListPage = function (fristPage,lastPage){		
			if(fristPage == "fristPage"){
				$scope.pageData.pageNo = 1;
				this.PageSelectUser(1,$scope.pageData.pageSize);
			}else{
				//这里调到了尾页，那么NO就要修改为最大页数，size不在此处修改。
				$scope.pageData.pageNo = $scope.page,
				this.PageSelectUser($scope.page,$scope.pageData.pageSize);
			}
		
		}
		
		//指定页面
		$scope.AppointPage = function (){
			var _pages = this.page;
			if(_pages != null || _pages != undefined){
				$scope.pageData.pageNo = _pages;
				this.PageSelectUser($scope.pageData.pageNo,$scope.pageData.pageSize);
			}
		}
		
		//页面初加载，查询事件
		$scope.PageSelectUser = function (SelectNo,SelectSize){
			 $http({
				  method  : 'POST',
				  type	  : 'POST',
				  url  	  : 'selectUser.do',
				  headers : { 'Content-Type': 'application/json;charset=utf-8' },
				  data  : {
					  "pageNo"   : SelectNo, 
					  "pageSize" : SelectSize
				  }
				}).success(function(data){
					   if (data.UserData != undefined) {
						   $scope.array = data.UserData;
					   } else {
						   alert(data.UserData)
					   };
				});
		};
		
		var UserId = null;
		//学生信息修改，显示隐藏
		$scope.ShowHide = function (){
			$scope.selectView = true;
			UserId = this.$$watchers[2].last;
		}
		
		
		//关闭修改DIV
		$scope.UpClose = function (){
			$scope.selectView = false;
			//清空绑定Input上绑定的ng-model的值
			$scope.UpselectData = {
					UpId    : "",
					UpName  : "",
					UpStart : ""
 			}
		}
		
		//Update提交
		$scope.UpdateSubmit = function(){
			$http({
				method    : 'POST',
				  type	  : 'POST',
				  url  	  : 'UpdateUser.do',
				  headers : { 'Content-Type': 'application/json;charset=UTF-8' },
				  data    : {
					"map" : $scope.UpselectData,//JSON.stringify() 
				 "UserId" : UserId
				  }
			}).success(function(data){
				   if (data.UserData == "success") {
					   $scope.selectView = false;
					   selectUser();
				   } else {
					   alert("修改失败！");
				   };
			});
		}
		
		//删除
		$scope.Delete = function(){
			UserId =  this.$$watchers[2].last;
			$http({
				method    : 'POST',
				  type	  : 'POST',
				  url  	  : 'DeleteId.do',
				  headers : { 'Content-Type': 'application/json;charset=UTF-8' },
				  data    : {
				 "UserId" : UserId
				  }
			}).success(function(data){
				   if (data.UserData == "success") {
					   selectUser();
					   pageCount()
				   } else {
					   alert("修改失败！");
				   };
			});
		}
		
		
		
		
		
		
		
		
		
		
	});
