<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path = request.getContextPath();%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
<script type="text/javascript" language="javascript" src="<%=path%>/js/index.js" charset="UTF-8"></script> 
 
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

</head>
<body ng-app="AddUser" ng-controller="uerController">



	<p>AngualerJs经典Form表单应用</p>
	<div>
		<form name = "UserForm" ng-submit = "processForm()" novalidate="novalidate">
		学号：<input type="text" ng-model = "AddUser.UserId" name ="UserId">
		姓名：<input type="text" ng-model = "AddUser.UserName" name ="UserName">
		学生Start:<input type="text" ng-model = "AddUser.UserStart" name ="UserStart">
        <button type="submit">提交</button>
		</form>
	</div>
	
	<pre>{{AddUser}}</pre>
	<pre>{{myTxt}}</pre>
<br>
	<div>
		<table>
			<tr>
				<td>学号</td>
				<td>姓名</td>
				<td>学生Start</td>
			</tr>
			<tr ng-repeat="a in array">
				<td>{{a.id}}</td>
				<td>{{a.nickname}}</td>
				<td>{{a.state}}</td>
			</tr>
		</table>
	</div>
</body>
 <script type="text/javascript" language="javascript" src="<%=path%>/js/indexAngualerjs.js" charset="UTF-8">
 
 </script>
</html>