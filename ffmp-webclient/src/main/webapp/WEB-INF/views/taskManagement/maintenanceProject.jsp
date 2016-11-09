<%--
  Created by IntelliJ IDEA.
  User: jiangliang
  Date: 2016/7/15
  Time: 15:41
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
      项目信息管理
      <small></small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="#"><i class="fa fa-wrench"></i> 维管工作管理</a></li>
      <li class="active">项目信息管理</li>
    </ol>
  </section>
  <!-- Main content -->
  <section class="content">
    <div class="row">
      <div class="box-footer">
        <sec:authorize access="@userDetailsUtils.isAuthorized('/维管工作管理/项目信息管理/创建项目')">
          <button type="button" id="createProject" class="btn btn-default pull-left">创建项目</button>
        </sec:authorize>
        <sec:authorize access="@userDetailsUtils.isAuthorized('/维管工作管理/项目信息管理/查看项目详细信息')">
          <button type="button" id="queryProject" class="btn btn-default pull-left">查看项目信息</button>
        </sec:authorize>
        <%--<sec:authorize access="@userDetailsUtils.isAuthorized('/维管工作管理/项目信息管理/设备录入')">--%>
          <%--<button type="button" id="allocationEquipment" class="btn btn-default pull-left">设备录入</button>--%>
        <%--</sec:authorize>--%>
          <%--<sec:authorize access="@userDetailsUtils.isAuthorized('/维管工作管理/项目信息管理/巡检标准')">--%>
              <button type="button" id="allocationInspections" class="btn btn-default pull-left">巡检标准</button>
          <%--</sec:authorize>--%>
          <button type="button" id="updateProject" class="btn btn-default pull-left">修改项目</button>
          <button type="button" id="endProject" class="btn btn-default pull-left">终止项目</button>
      </div>
      <table id="projectTable"
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
<script src="<c:url value='/js/taskManagement/project.js'/>"></script>



