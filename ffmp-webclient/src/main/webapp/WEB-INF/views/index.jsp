<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>建筑消防设施维护保养管理系统</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="<c:url value='/bower_components/bootstrap/dist/css/bootstrap.min.css'/>">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<c:url value='/bower_components/font-awesome/css/font-awesome.min.css'/>">
    <!-- Ionicons -->
    <link rel="stylesheet" href="<c:url value='/bower_components/Ionicons/css/ionicons.min.css'/>">
    <!-- jvectormap -->
    <link rel="stylesheet" href="<c:url value='/bower_components/AdminLTE/plugins/jvectormap/jquery-jvectormap-1.2.2.css'/>">
    <!-- Theme style -->
    <link rel="stylesheet" href="<c:url value='/bower_components/AdminLTE/dist/css/AdminLTE.min.css'/>">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
    folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="<c:url value='/bower_components/AdminLTE/dist/css/skins/_all-skins.min.css'/>">
    <!-- iCheck -->
    <link rel="stylesheet" href="<c:url value='/bower_components/AdminLTE/plugins/iCheck/square/blue.css'/>">
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="common/header.jsp"></jsp:include>
    <div class="content-wrapper" id="main-content">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                home
                <small>Version 2.0</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">Dashboard</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                 这是主页
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>
    <jsp:include page="common/footer.jsp"></jsp:include>
</div>
<!-- ./wrapper -->
<script src="<c:url value='/bower_components/jquery/dist/jquery.min.js'/>"></script>
<script src="<c:url value='/bower_components/bootstrap/dist/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/bower_components/AdminLTE/plugins/fastclick/fastclick.js'/>"></script>
<!-- AdminLTE App -->
<script src="<c:url value='/bower_components/AdminLTE/dist/js/app.min.js'/>"></script>
<!-- Sparkline -->
<script src="<c:url value='/bower_components/AdminLTE/plugins/sparkline/jquery.sparkline.min.js'/>"></script>
<!-- jvectormap -->
<script src="<c:url value='/bower_components/AdminLTE/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js'/>"></script>
<script src="<c:url value='/bower_components/AdminLTE/plugins/jvectormap/jquery-jvectormap-world-mill-en.js'/>"></script>
<!-- SlimScroll 1.3.0 -->
<script src="<c:url value='/bower_components/AdminLTE/plugins/slimScroll/jquery.slimscroll.min.js'/>"></script>
<!-- ChartJS 1.0.1 -->
<script src="<c:url value='/bower_components/AdminLTE/plugins/chartjs/Chart.min.js'/>"></script>
<%--<script src="<c:url value='/js/dist/js/app.js'/>"></script>--%>
<script src="<c:url value='/bower_components/AdminLTE/dist/js/demo.js'/>"></script>
<script src="<c:url value='/js/common/navigantion.js'/>"></script>
<script src="<c:url value='/js/common/index.js'/>"></script>
<![endif]-->
</body>
</html>
