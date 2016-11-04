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
    label.error {
        color: Red;
    }
</style>
<section class="content-header">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            巡检标准
            <small></small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-wrench"></i> 维管工作管理</a></li>
            <li class="active">项目信息管理</li>
            <li class="active">巡检标准</li>
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
                    <label for="name" class="col-lg-4 control-label">项目名称：</label>

                    <div class="col-lg-8">
                        <input type="text" class="form-control" readonly id="name" name="name" placeholder="项目名称">
                    </div>
                </div>
            </form>
        </div>
        <div class="row">
            <form class="form-horizontal" id="InspectionForm">
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
                    <label for="type" class="col-md-4 control-label">巡检类型：</label>

                    <div class="col-md-8">
                        <select class="form-control m-bot15" id="type" name="type" required="required">
                            <option value="月度巡检">月度巡检</option>
                            <option value="季度巡检">季度巡检</option>
                            <option value="年度巡检">年度巡检</option>
                        </select>
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
            <div class="box box-primary"></div>
        </div>
        <div class="row">
            <div class="form-group col-md-4">
                <div class="row">
                    <div class="col-md-6">
                        <label for="monthRatio" class="control-label">月度巡检：</label>
                    </div>
                    <div class="col-md-6">
                        <div class="input-group">
                            <input type="text" class="form-control" id="monthRatio" name="monthRatio"
                                   placeholder="巡检比例">
                            <span class="input-group-addon">%</span>
                        </div>
                    </div>
                </div>
                <table id="monthTable"
                       data-single-select="true"
                       data-pagination="true"
                       data-id-field="id"
                       data-page-list="[5,10, 25, 50, 100, ALL]">
                </table>
            </div>
            <div class="form-group col-md-4">
                <div class="row">
                    <div class="col-md-6">
                        <label for="quarterRatio" class="control-label">季度巡检：</label>
                    </div>
                    <div class="col-md-6">
                        <div class="input-group">
                            <input type="text" class="form-control" id="quarterRatio" name="quarterRatio"
                                   placeholder="巡检比例">
                            <span class="input-group-addon">%</span>
                        </div>
                    </div>
                </div>
                <table id="quarterTable"
                       data-single-select="true"
                       data-pagination="true"
                       data-id-field="id"
                       data-page-list="[5,10, 25, 50, 100, ALL]">
                </table>
            </div>
            <div class="form-group col-md-4">
                <div class="row">
                    <div class="col-md-6">
                        <label for="yearRatio" class="control-label">年度巡检：</label>
                    </div>
                    <div class="col-md-6">
                        <div class="input-group">
                            <input type="text" class="form-control" id="yearRatio" name="yearRatio" placeholder="巡检比例">
                            <span class="input-group-addon">%</span>
                        </div>
                    </div>
                </div>
                <table id="yearTable"
                       data-single-select="true"
                       data-pagination="true"
                       data-id-field="id"
                       data-page-list="[5,10, 25, 50, 100, ALL]">
                </table>
            </div>
        </div>
        <div class="row">
            <div class="box-footer">
                <button type="button" id="save" class="btn btn-info pull-left">保存</button>
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
<script src="<c:url value='/js/taskManagement/inspection.js'/>"></script>




