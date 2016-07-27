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

    <jsp:include page="common/include.jsp" />

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="common/header.jsp"/>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper" id="main-content">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                主页
                <small>欢迎您</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 主页</a></li>
                <li class="active">主页</li>
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
<script src="<c:url value='/bower_components/AdminLTE/plugins/fastclick/fastclick.js'/>"></script>
<!-- AdminLTE App -->
<script src="<c:url value='/bower_components/AdminLTE/dist/js/app.min.js'/>"></script>
<script src="<c:url value='/bower_components/AdminLTE/dist/js/demo.js'/>"></script>
<script src="<c:url value='/js/common/navigantion.js'/>"></script>
<script src="<c:url value='/js/common/index.js'/>"></script>

</body>
</html>
