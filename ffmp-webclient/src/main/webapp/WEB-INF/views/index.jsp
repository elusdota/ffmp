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
                    <div id="map" style="width:100%;height:768px"></div>
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
<script src="http://api.map.baidu.com/api?v=2.0&ak=d5C8F51QjuheQAZxGN34oEc0s3zdvP2S" type="text/javascript"></script>
<script type="text/javascript">
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
            overlay(data);
        }, error: function (XMLHttpRequest) {
            $("#tips").html(XMLHttpRequest.responseText).appendTo("body");
            $("#message").modal("show");
        }
    });

    var map = new BMap.Map('map');
    map.centerAndZoom(new BMap.Point(102.73, 25.04), 13);
    map.enableScrollWheelZoom(true);
    map.addControl(new BMap.NavigationControl());
    map.addControl(new BMap.ScaleControl());
    map.addControl(new BMap.OverviewMapControl());
    map.addControl(new BMap.MapTypeControl());
    // 创建地址解析器实例
    var myGeo = new BMap.Geocoder();

    function overlay(data_info){
        for(var i=0;i<data_info.total;i++){
            (function(e) {
                setTimeout(function() {
                    var addr = data_info.rows[e].address;
                    var content = "项目名称："+data_info.rows[e].name+"<br>"+
                            "项目地址："+addr;
                    myGeo.getPoint(addr, function(point){
                        if (point) {
                            var marker = new BMap.Marker(new BMap.Point(point.lng, point.lat));  // 创建标注
                            map.addOverlay(marker); // 将标注添加到地图中
                            addClickHandler(content,marker);
                        }
                    }, "昆明市");
                }, 400);
            })(i);
        }
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
