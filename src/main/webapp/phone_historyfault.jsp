<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
    <title>历史记录</title>
	<link rel="icon" href="resources/phone/image/picture.ico" type="image/x-icon"/>
    <link href="resources/phone/CSS/common/ionic.css" rel="stylesheet">
    <link href="resources/phone/CSS/archives_elevator.css" rel="stylesheet">
    <link href="resources/phone/CSS/common/bootstrap.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/ionic/1.3.2/js/ionic.bundle.min.js"></script>
    <script src="resources/phone/JS/common/jquery-3.2.1.min.js"></script>
    <script src="resources/phone/JS/common/bootstrap-paginator.js"></script>

</head>
<body ng-app="App" ng-controller="historyfault_elevator">
	<!-- 页面头部 -->
	<%@ include file="menu_top.jsp" %>

	<!-- 页面主体内容 -->
	<div class = "content has-header scroll scroll-content" style = "overflow-y:auto;bottom:10%;top: 15%;">
		<div style = "color:#808080">
			<ion-list>
				<ion-item ng-repeat="item in elevators"  style="border-color: white;margin: 0px 0px -2%;"
						  item="item">
                    <span style = "font-size:0.8em" class="expiredSts">
                    <span ng-bind="item.manualName">
                    </span><span> 丨 </span>
                    <span ng-bind="item.name"></span>
                	</span>
				</ion-item>
			</ion-list>
		</div>

	</div>


	<!--  
	<div ng-include="'phone_bottom.html'"></div>
	-->
<script src="resources/phone/JS/app.js"></script>
<script src="resources/phone/JS/historyfault_elevator.js"></script>
</body>
</html>