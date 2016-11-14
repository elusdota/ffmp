<%--
  Created by IntelliJ IDEA.
  User: jiangliang
  Date: 2016/7/17
  Time: 0:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="<c:url value='/bower_components/bootstrap-table/dist/bootstrap-table.min.css'/>">
<link rel="stylesheet" href="<c:url value='/bower_components/AdminLTE/plugins/datepicker/datepicker3.css'/>">
<section class="content-header">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            待完成任务
            <small></small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-wrench"></i> 维管工作管理</a></li>
            <li class="active">待完成任务</li>
        </ol>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="box-footer">
                <sec:authorize access="@userDetailsUtils.isAuthorized('/维管工作管理/待完成任务/创建任务')">
                    <button type="button" id="createTask" class="btn btn-default pull-left">创建任务</button>
                </sec:authorize>
                <sec:authorize access="@userDetailsUtils.isAuthorized('/维管工作管理/待完成任务/查看任务')">
                    <button type="button" id="viewTask" class="btn btn-default pull-left">查看任务</button>
                </sec:authorize>
                <sec:authorize access="@userDetailsUtils.isAuthorized('/维管工作管理/待完成任务/申请材料')">
                    <button type="button" id="materialsTask" class="btn btn-default pull-left">申请材料</button>
                </sec:authorize>
                <sec:authorize access="@userDetailsUtils.isAuthorized('/维管工作管理/待完成任务/维保负责人审核')">
                    <button type="button" id="repairAuditTask" class="btn btn-default pull-left">维保负责人审核</button>
                </sec:authorize>
                <sec:authorize access="@userDetailsUtils.isAuthorized('/维管工作管理/待完成任务/任务审核')">
                    <button type="button" id="auditTask" class="btn btn-default pull-left">总监审核</button>
                </sec:authorize>
                <sec:authorize access="@userDetailsUtils.isAuthorized('/维管工作管理/待完成任务/报修转任务')">
                    <button type="button" id="conversionTask" class="btn btn-default pull-left">报修转任务</button>
                </sec:authorize>
            </div>
            <table id="taskTable"
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
    <div class="modal fade" id="conversionModel" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">
                        报修单
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <table id="repairFormTable"
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
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="insert">确定</button>
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
<script src="<c:url value='/bower_components/AdminLTE/plugins/datepicker/bootstrap-datepicker.js'/>"></script>
<script src="<c:url value='/bower_components/AdminLTE/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js'/>"></script>
<script src="<c:url value='/bower_components/tableExport.jquery.plugin/tableExport.min.js'/>"></script>
<script src="<c:url value='/bower_components/jquery.base64.js/jquery.base64.js'/>"></script>
<script src="<c:url value='/js/taskManagement/runTask.js'/>"></script>



