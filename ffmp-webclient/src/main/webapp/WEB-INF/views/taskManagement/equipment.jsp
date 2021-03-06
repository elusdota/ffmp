<%--
  Created by IntelliJ IDEA.
  User: jiangliang
  Date: 2016/7/15
  Time: 16:21
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
            设备录入
            <small></small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-wrench"></i> 维管工作管理</a></li>
            <li class="active">项目信息管理</li>
            <li class="active">设备录入</li>
        </ol>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <form class="form-horizontal" id="searchForm">
                <div class="form-group" hidden="true">
                    <input type="text" class="form-control" id="id" name="id" value="${id}"/>
                </div>
                <div class="form-group col-md-6">
                    <label for="name" class="col-md-4 control-label">项目名称：</label>

                    <div class="col-md-8">
                        <input type="text" class="form-control" readonly id="name" name="name" placeholder="项目名称">
                    </div>
                </div>
            </form>
        </div>
        <div class="row">
            <form class="form-horizontal" id="equipumentForm">
                <div class="form-group col-md-6">
                    <label for="equipumentname" class="col-md-4 control-label">设备名称：<span
                            class="required">*</span></label>

                    <div class="col-md-8">
                        <input type="text" class="form-control" id="equipumentname" name="equipumentname"
                               placeholder="设备名称"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label for="typemax" class="col-md-4 control-label">大类：<span
                            class="required">*</span></label>
                    <div class="col-md-8">
                        <select class="form-control m-bot15 required" name="typemax" id="typemax"
                                required="required"
                                onchange="type_onChange()">
                        </select>
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label for="typemin" class="col-md-4 control-label">小类：<span
                            class="required">*</span></label>
                    <div class="col-md-8">
                        <select class="form-control m-bot15 required" name="typemin" id="typemin"
                                required="required">
                        </select>
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label for="manufacturer" class="col-md-4 control-label">生产厂家：<span
                            class="required">*</span></label>

                    <div class="col-md-8">
                        <input type="text" class="form-control" id="manufacturer" name="manufacturer"
                               placeholder="生产厂家"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label for="model" class="col-md-4 control-label">型号：</label>

                    <div class="col-md-8">
                        <input type="text" class="form-control" id="model" name="model"
                               placeholder="型号">
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label for="nowstate" class="col-md-4 control-label">状态：</label>

                    <div class="col-md-8">
                        <select class="form-control m-bot15" id="nowstate" name="nowstate" required="required">
                            <option value="功能正常">功能正常</option>
                            <option value="外观不完好">外观不完好</option>
                            <option value="组件不齐全">组件不齐全</option>
                            <option value="有障碍阻挡使用">有障碍阻挡使用</option>
                            <option value="标识不可见">标识不可见</option>
                        </select>
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label for="quantity" class="col-md-4 control-label">数量：<span
                            class="required">*</span></label>

                    <div class="col-md-8">
                        <input type="text" class="form-control required digits" id="quantity"
                               name="quantity"
                               placeholder="数量">
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label class="control-label col-md-4">生产日期：<span
                            class="required">*</span></label>
                    <div class="input-daterange col-md-8">
                        <input type="text" data-date-format="yyyy-mm-dd" class="form-control datepicker"
                               data-provide="datepicker" id="productionDate">
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label class="control-label col-md-4">投入使用日期：<span
                            class="required">*</span></label>
                    <div class="input-daterange col-md-8">
                        <input type="text" data-date-format="yyyy-mm-dd" class="form-control datepicker"
                               data-provide="datepicker" id="inputDate">
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <div class="col-md-offset-4 col-md-8">
                        <button id="restForm" type="reset"
                                class="btn btn-danger btn-small">重置
                        </button>
                        <button id="insertTable" type="button"
                                class="btn btn-warning  btn-small">插入
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="row">
            <label for="name" class="col-md-6 control-label">设备列表：</label>
        </div>
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
        <div class="row">
            <div class="box-footer">
                <button type="button" id="save" class="btn btn-info pull-right">保存</button>
            </div>
        </div>
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
<script src="<c:url value='/bower_components/AdminLTE/plugins/datepicker/bootstrap-datepicker.js'/>"></script>
<script src="<c:url value='/bower_components/AdminLTE/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js'/>"></script>
<script src="<c:url value='/bower_components/tableExport.jquery.plugin/tableExport.min.js'/>"></script>
<script src="<c:url value='/bower_components/jquery.base64.js/jquery.base64.js'/>"></script>
<script src="<c:url value='/js/taskManagement/equipment.js'/>"></script>




