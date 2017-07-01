<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%-- <link rel="stylesheet" href="<%=path%>/css/LongUserPage.css" type="text/css" /> --%>
<title>Login Page</title>
</head>
<body ng-app="loginApp" ng-controller="loginController" >
	<div class = "loginClass">
	<form class="form-horizontal" ng-submit="submitLogins(UserData)" novalidate="novalidate" >
	   <div class="control-group">
			<label class="control-label" >Name:</label>
			<div>
			<input type = "text" placeholder="账号" id = "Name" ng-model="UserData.userName">
			</div>
	   </div>
	   <div class="control-group">
    		<label class="control-label" for="inputPassword">Password</label>
   	    	<div class="controls">
        	<input type="password" id="inputPassword" placeholder="Password" ng-model = "UserData.Password">
       </div>
       <div class="control-group">
      	 	<div class="controls">
     	 	<label class="checkbox">
        	<input type="checkbox"> Remember me
         	</label>
      	 	<button type="submit" class="btn">Sign in</button>
    		</div>
 	  </div>
  	  </div>
	</form>
	</div>
</body>
	<script type="text/javascript" language="javascript" src="<%=path%>/js/LongUserPage.js" charset="UTF-8"></script> 
</html>