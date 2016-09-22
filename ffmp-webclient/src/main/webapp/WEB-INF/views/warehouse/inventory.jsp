<%--
  Created by IntelliJ IDEA.
  User: jiangliang
  Date: 2016/6/27
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="<c:url value='/bower_components/AdminLTE/plugins/datepicker/datepicker3.css'/>">
<link rel="stylesheet" href="<c:url value='/bower_components/bootstrap-table/dist/bootstrap-table.min.css'/>">
<section class="content-header">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            备件库存管理
            <small>s</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-bank"></i> 仓库管理</a></li>
            <li class="active">备件库存管理</li>
        </ol>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="box-footer">
                <sec:authorize access="@userDetailsUtils.isAuthorized('/仓库管理/备件库存管理/备件库存查询')">
                    <form class="form-horizontal" id="inboundsForm">
                        <div class="form-group col-md-6">
                            <label for="name" class="col-lg-6 control-label">备件名称：</label>

                            <div class="col-lg-6">
                                <input type="text" class="form-control" id="name" name="name" placeholder="备件名称"
                                       required="required">
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="type" class="col-lg-4 control-label">类型：</label>

                            <div class="col-lg-8">
                                <input type="text" class="form-control" id="type" name="type" placeholder="类型"
                                       required="required">
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="manufacturer" class="col-lg-6 control-label">生产厂家：</label>

                            <div class="col-lg-6">
                                <input type="text" class="form-control" id="manufacturer" name="manufacturer"
                                       placeholder="生产厂家"
                                       required="required">
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="model" class="col-lg-4 control-label">型号：</label>

                            <div class="col-lg-8">
                                <input type="text" class="form-control" id="model" name="model"
                                       placeholder="型号">
                            </div>
                        </div>
                    </form>
                    <button type="button" id="queryInventory" class="btn btn-default pull-right">查询</button>
                </sec:authorize>
            </div>
            <table id="inventoryTable"
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
            <div id="page-selection"></div>
        </div>
    </section>
</section>
<script src="<c:url value='/bower_components/jquery-validation/dist/jquery.validate.js'/>"></script>
<script src="<c:url value='/bower_components/jquery-validation/dist/additional-methods.js'/>"></script>
<script src="<c:url value='/bower_components/jquery-validation/src/localization/messages_zh.js'/>"></script>
<script src="<c:url value='/bower_components/bootstrap-table/dist/bootstrap-table.js'/>"></script>
<script src="<c:url value='/bower_components/bootstrap-table/dist/extensions/export/bootstrap-table-export.min.js'/>"></script>
<script src="<c:url value='/bower_components/bootstrap-table/dist/locale/bootstrap-table-zh-CN.min.js'/>"></script>
<script src="<c:url value='/bower_components/tableExport.jquery.plugin/tableExport.min.js'/>"></script>
<script src="<c:url value='/bower_components/jquery.base64.js/jquery.base64.js'/>"></script>
<script src="<c:url value='/bower_components/AdminLTE/plugins/datepicker/bootstrap-datepicker.js'/>"></script>
<script src="<c:url value='/bower_components/AdminLTE/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js'/>"></script>
<script src="<c:url value='/js/inventory/inventory.js'/>"></script>
