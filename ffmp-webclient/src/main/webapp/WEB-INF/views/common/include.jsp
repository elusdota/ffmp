<%--
  Created by IntelliJ IDEA.
  User: suelmer
  Date: 2016/5/31
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<!-- 这个文件中集中存放引入的css文件-->
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="<c:url value='/bower_components/bootstrap/dist/css/bootstrap.min.css'/>">
<!-- Font Awesome -->
<link rel="stylesheet" href="<c:url value='/bower_components/font-awesome/css/font-awesome.min.css'/>">
<!-- Ionicons -->
<link rel="stylesheet" href="<c:url value='/bower_components/Ionicons/css/ionicons.min.css'/>">
<!-- Theme style -->
<link rel="stylesheet" href="<c:url value='/bower_components/AdminLTE/dist/css/AdminLTE.min.css'/>">
<!-- AdminLTE Skins. Choose a skin from the css/skins
folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="<c:url value='/bower_components/AdminLTE/dist/css/skins/_all-skins.min.css'/>">
<!-- iCheck -->
<link rel="stylesheet" href="<c:url value='/bower_components/AdminLTE/plugins/iCheck/square/blue.css'/>">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="<c:url value='/bower_components/html5shiv/dist/html5shiv.min.js'/>"></script>
<script src="<c:url value='/bower_components/respond/dest/respond.min.js'/>"></script>
<![endif]-->

<!-- jQuery 2.2.0 -->
<script src="<c:url value='/bower_components/jquery/dist/jquery.min.js'/>"></script>
<!-- Bootstrap 3.3.6 -->
<script src="<c:url value='/bower_components/AdminLTE/bootstrap/js/bootstrap.min.js'/>"></script>
