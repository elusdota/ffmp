<%--
  Created by IntelliJ IDEA.
  User: jiangliang
  Date: 2016/7/25
  Time: 15:53
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
            报修单
            <small></small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-wrench"></i> 报修单</a></li>
            <li class="active">报修单</li>
        </ol>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="box-footer">
                <sec:authorize access="@userDetailsUtils.isAuthorized('/报修单/创建报修单')">
                    <button type="button" id="createRepair" class="btn btn-default pull-left">创建报修单</button>
                </sec:authorize>
                <sec:authorize access="@userDetailsUtils.isAuthorized('/报修单/查看维修任务')">
                    <button type="button" id="queryTask" class="btn btn-default pull-left">查看维修任务</button>
                </sec:authorize>
            </div>
            <table id="repairTable"
                   data-toolbar="#toolbar"
                   data-search="true"
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
    <div class="modal fade" id="repairModel" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">
                        报修单
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <form class="form-horizontal" id="repairForm">
                            <div class="form-group col-md-6">
                                <label for="projectNumber" class="col-lg-6 control-label">项目编号：<span
                                        class="required">*</span></label>

                                <div class="col-lg-6">
                                    <input type="text" class="form-control" id="projectNumber" name="projectNumber"
                                           placeholder="项目编号"
                                           required="required">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="person" class="col-lg-4 control-label">报修人：<span
                                        class="required">*</span></label>

                                <div class="col-lg-8">
                                    <input type="text" class="form-control" id="person" name="person" placeholder="报修人"
                                           required="required">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="parts" class="col-lg-6 control-label">故障部位：<span
                                        class="required">*</span></label>

                                <div class="col-lg-6">
                                    <input type="text" class="form-control" id="parts" name="parts"
                                           placeholder="故障部位"
                                           required="required">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="description" class="col-lg-4 control-label">故障描述：</label>

                                <div class="col-lg-8">
                  <textarea class="form-control" rows="3" id="description" name="description"
                            placeholder="故障描述"/>
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
<script src="<c:url value='/js/taskManagement/repairForm.js'/>"></script>




