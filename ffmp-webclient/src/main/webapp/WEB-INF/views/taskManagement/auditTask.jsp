<%--
  Created by IntelliJ IDEA.
  User: jiangliang
  Date: 2016/8/18
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="<c:url value='/bower_components/bootstrap-table/dist/bootstrap-table.min.css'/>">
<link rel="stylesheet" href="<c:url value='/bower_components/AdminLTE/plugins/datepicker/datepicker3.css'/>">
<style type="text/css">
    *
    label.error
    {
        color:Red;
    }
</style>
<section class="content-header">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            任务审核
            <small></small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-wrench"></i> 维管工作管理</a></li>
            <li class="active">任务审核</li>
        </ol>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <form class="form-horizontal" id="searchForm">
                <div class="form-group" hidden="true">
                    <input type="text" class="form-control" id="id" name="id" value="${id}"/>
                </div>
                <div class="form-group" hidden="true">
                    <input type="text" class="form-control" id="proid" name="proid"/>
                </div>
                <div class="form-group col-md-6">
                    <label for="name" class="col-lg-6 control-label">任务名称：</label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control" id="name" name="name" placeholder="任务名称">
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label for="maintenanceProject" class="col-lg-6 control-label">所属项目：</label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control" id="maintenanceProject" name="maintenanceProject"
                               placeholder="所属项目">
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label for="repairnumber" class="col-lg-6 control-label">报修单编号：</label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control" id="repairnumber" name="repairnumber"
                               placeholder="报修单编号">
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label for="customer" class="col-lg-6 control-label">所属客户：</label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control" id="customer" name="customer"
                               placeholder="所属项目">
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label for="description" class="col-lg-6 control-label">任务说明：</label>

                    <div class="col-lg-6">
                        <textarea class="form-control" rows="3" id="description" name="description"
                                  placeholder="任务说明"/>
                    </div>
                </div>
            </form>
        </div>
    </section>
    <div class="row">
        <label for="name" class="col-lg-6 control-label">材料列表：</label>
        <table id="materialTable"
               data-toolbar="#toolbar"
               data-show-refresh="true"
               data-show-toggle="true"
               data-show-columns="true"
               data-show-export="true"
               data-minimum-count-columns="2"
               data-show-pagination-switch="true"
               data-single-select="true"
               data-pagination="true"
               data-id-field="id"
               data-show-footer="true"
               data-page-list="[5,10, 25, 50, 100, ALL]">
        </table>
    </div>
    <div class="row">
        <label for="name" class="col-lg-6 control-label">设备列表：</label>
        <table id="equipmentTable"
               data-toolbar="#toolbar"
               data-show-refresh="true"
               data-show-toggle="true"
               data-show-columns="true"
               data-show-export="true"
               data-minimum-count-columns="2"
               data-show-pagination-switch="true"
               data-single-select="true"
               data-pagination="true"
               data-id-field="id"
               data-show-footer="true"
               data-page-list="[5,10, 25, 50, 100, ALL]">
        </table>
    </div>
</section>
<script src="<c:url value='/bower_components/bootstrap-table/dist/bootstrap-table.js'/>"></script>
<script src="<c:url value='/bower_components/bootstrap-table/dist/extensions/export/bootstrap-table-export.min.js'/>"></script>
<script src="<c:url value='/bower_components/bootstrap-table/dist/locale/bootstrap-table-zh-CN.min.js'/>"></script>
<script src="<c:url value='/bower_components/AdminLTE/plugins/datepicker/bootstrap-datepicker.js'/>"></script>
<script src="<c:url value='/bower_components/AdminLTE/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js'/>"></script>
<script src="<c:url value='/bower_components/tableExport.jquery.plugin/tableExport.min.js'/>"></script>
<script src="<c:url value='/bower_components/jquery.base64.js/jquery.base64.js'/>"></script>
<script src="<c:url value='/js/taskManagement/auditTask.js'/>"></script>
</body>
</html>
