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
            工具借出
            <small></small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-sitemap"></i> 工具管理</a></li>
            <li class="active">工具借出</li>
        </ol>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <sec:authorize access="@userDetailsUtils.isAuthorized('/工具管理/工具借出/查询')">
                <form class="form-horizontal" id="searchForm">
                    <div class="form-group col-md-4">
                        <label for="number" class="col-lg-6 control-label">单据编号：</label>

                        <div class="col-lg-6">
                            <input type="text" class="form-control" id="number" name="number" placeholder="编号"
                                   required="required">
                        </div>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="executor" class="col-lg-6 control-label">操作人：</label>

                        <div class="col-lg-6">
                            <input type="text" class="form-control" id="executor" name="executor" placeholder="操作人"
                                   required="required">
                        </div>
                    </div>
                    <div class="form-group col-md-5">
                        <label class="control-label col-lg-3">日期：</label>

                        <div class="input-group input-daterange col-lg-9">
                            <input type="text" data-date-format="yyyy-mm-dd" class="form-control datepicker"
                                   data-provide="datepicker" data-date-end-date="0d" id="startdate">
                            <span class="input-group-addon">to</span>
                            <input type="text" data-date-format="yyyy-mm-dd" class="form-control datepicker"
                                   data-provide="datepicker" data-date-end-date="0d" id="enddate">
                        </div>
                    </div>
                </form>
            </sec:authorize>
            <div class="box-footer">
                <sec:authorize access="@userDetailsUtils.isAuthorized('/工具管理/工具借出/创建借出')">
                    <button type="button" id="createDispatch" class="btn btn-default pull-left">创建借出</button>
                </sec:authorize>
                <sec:authorize access="@userDetailsUtils.isAuthorized('/工具管理/工具借出/查看')">
                    <button type="button" id="viewDispatch" class="btn btn-default pull-left">查看</button>
                </sec:authorize>
                <sec:authorize access="@userDetailsUtils.isAuthorized('/工具管理/工具借出/查询')">
                    <button type="button" id="queryDispatch" class="btn btn-default pull-right">查询</button>
                </sec:authorize>
            </div>
            <table id="dispatchTable"
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
    <!-- 模态框（Model） -->
    <div class="modal fade" id="dispatchModel" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">
                        工具借出
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <form class="form-horizontal" id="dispatchForm">
                            <div class="form-group col-md-6">
                                <label for="description" class="col-lg-4 control-label">借用人：</label>

                                <div class="col-lg-8">
                                    <input type="text" class="form-control " id="description" name="description"
                                           placeholder="借用人">
                                </div>
                            </div>
                            <table id="inventoryTable"
                                   data-toolbar="#toolbar"
                                   data-show-refresh="true"
                                   data-search="true"
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
                            <div class="form-actions">
                                <div class="form-group col-md-6">
                                    <label for="quantity" class="col-lg-4 control-label">数量：<span
                                            class="required">*</span></label>

                                    <div class="col-lg-8">
                                        <input type="text" class="form-control required digits" id="quantity"
                                               name="quantity"
                                               placeholder="数量">
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <button id="insertTable" type="button"
                                            class="btn btn-warning  btn-small">插入
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="row">
                        <table id="detailsTable" data-url=""></table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="submitData">提交</button>
                        <button type="button" class="btn btn-primary" id="giveupData">放弃</button>
                    </div>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
    </div>
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
<script src="<c:url value='/js/tools/dispatch.js'/>"></script>
