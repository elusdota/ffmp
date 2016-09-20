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

    <jsp:include page="common/include.jsp"/>

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
            <div class="box box-primary">
                <div class="box-header with-border">

                </div>
                <div class="box-body">
                    <div id="map" style="width:960px;height:768px"></div>
                </div>
                <div class="box-footer clearfix">

                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <jsp:include page="common/footer.jsp"/>
</div>
<!-- ./wrapper -->
<script src="<c:url value='/bower_components/AdminLTE/plugins/fastclick/fastclick.js'/>"></script>
<!-- AdminLTE App -->
<script src="<c:url value='/bower_components/AdminLTE/dist/js/app.min.js'/>"></script>
<script src="<c:url value='/bower_components/AdminLTE/dist/js/demo.js'/>"></script>
<script src="<c:url value='/js/common/navigantion.js'/>"></script>
<script src="<c:url value='/js/common/index.js'/>"></script>
<script src="http://api.map.baidu.com/api?v=2.0&ak=3E99E522ecb83ad1cbf7844fd84c6062" type="text/javascript"></script>
<script type="text/javascript">
    var data_info = {};
    var para = {
        offset: 0,
        limit: 100,
        order: "asc",
        sort: "name",
        search: null
    };

    $.ajax('rest/maintenanceProject/findAll', {
        type: 'POST',
        data: JSON.stringify(para),
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, XMLHttpRequest, jqXHR) {
            data_info = data;
        }, error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });

    var map = new BMap.Map('map');
    map.centerAndZoom(new BMap.Point(102.73, 25.04), 14);
    map.enableScrollWheelZoom();
    map.addControl(new BMap.NavigationControl());
    map.addControl(new BMap.ScaleControl());
    map.addControl(new BMap.OverviewMapControl());
    map.addControl(new BMap.MapTypeControl());
    // 创建地址解析器实例
    var myGeo = new BMap.Geocoder();
    bdGEO();
    function bdGEO() {
        for (var index = 0; index < data_info.total; index++) {
            var add = data_info.rows[index].address;
            geocodeSearch(add, data_info.rows[index].name);
        }

    }
    function geocodeSearch(add, name) {
        myGeo.getPoint(add, function (point) {
            if (point) {
                var pointMap = new BMap.Point(point.lng, point.lat);
                var marker = new BMap.Marker(pointMap);  // 创建标注
                map.addOverlay(marker);               // 将标注添加到地图中
                addClickHandler(name, marker);
            }
        }, "昆明市");
    }
    var opts = {
        width: 250,     // 信息窗口宽度
        height: 80,     // 信息窗口高度
        title: "信息窗口", // 信息窗口标题
        enableMessage: true//设置允许信息窗发送短息
    };


    function addClickHandler(content, marker) {
        marker.addEventListener("click", function (e) {
                    openInfo(content, e)
                }
        );
    }
    function openInfo(content, e) {
        var p = e.target;
        var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
        var infoWindow = new BMap.InfoWindow(content, opts);  // 创建信息窗口对象
        map.openInfoWindow(infoWindow, point); //开启信息窗口
    }
</script>
</body>
</html>
