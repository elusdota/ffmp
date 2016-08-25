<%--
  Created by IntelliJ IDEA.
  User: jiangliang
  Date: 2016/7/17
  Time: 1:12
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
      创建任务
      <small></small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="#"><i class="fa fa-wrench"></i> 维管工作管理</a></li>
      <li class="active">待完成任务</li>
      <li class="active">创建任务</li>
    </ol>
  </section>
  <!-- Main content -->
  <section class="content">
    <div class="row">
      <form class="form-horizontal" id="saveForm">
        <div class="form-group col-md-6">
          <label for="name" class="col-lg-6 control-label">任务名称：<span
                  class="required">*</span></label>

          <div class="col-lg-6">
            <input type="text" class="form-control" id="name" name="name" placeholder="任务名称"
                   required="required">
          </div>
        </div>
        <div class="form-group col-md-6">
          <label for="maintenanceProject" class="col-lg-6 control-label">项目编号：<span
                  class="required">*</span></label>

          <div class="col-lg-6">
            <input type="text" class="form-control" id="maintenanceProject" name="maintenanceProject" placeholder="项目编号"
                   required="required">
          </div>
        </div>
        <div class="form-group col-md-6">
          <label for="repairnumber" class="col-lg-6 control-label">报修单编号：</label>

          <div class="col-lg-6">
            <input type="text" class="form-control" id="repairnumber" name="repairnumber" placeholder="报修单编号">
          </div>
        </div>
        <div class="form-group col-md-6">
          <label class="control-label col-lg-3">执行时间：<span
                  class="required">*</span></label>

          <div class="input-group input-daterange col-lg-9">
            <input type="text" data-date-format="yyyy-mm-dd" class="form-control datepicker"
                   data-provide="datepicker" id="startdate">
            <span class="input-group-addon">to</span>
            <input type="text" data-date-format="yyyy-mm-dd" class="form-control datepicker"
                   data-provide="datepicker" id="enddate">
          </div>
        </div>
        <div class="form-group col-md-6">
          <label for="description" class="col-lg-6 control-label">任务说明：</label>

          <div class="col-lg-6">
            <textarea class="form-control" rows="3"  id="description" name="description" placeholder="任务说明"/>
          </div>
        </div>
      </form>
      <div class="box-footer">
        <button type="button" id="save" class="btn btn-default pull-right">保存</button>
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
<script src="<c:url value='/js/taskManagement/createTask.js'/>"></script>




