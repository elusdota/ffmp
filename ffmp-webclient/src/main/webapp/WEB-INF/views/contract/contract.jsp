<%--
  Created by IntelliJ IDEA.
  User: suelmer
  Date: 2016/7/18
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<section class="content-header">
    <h1>
        合同管理
    </h1>
    <ol class="breadcrumb">
        <li><a href="index"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li class="active"> 合同管理</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="box box-primary">
        <div class="box-header with-border">
            <sec:authorize access="@userDetailsUtils.isAuthorized('/合同管理/创建合同信息')">
                <button type="button" id="createOrganization" class="btn btn-default">创建合同信息</button>
            </sec:authorize>
            <sec:authorize access="@userDetailsUtils.isAuthorized('/合同管理/修改合同信息')">
                <button type="button" id="updateOrganization" class="btn btn-default">修改合同信息</button>
            </sec:authorize>
            <sec:authorize access="@userDetailsUtils.isAuthorized('/合同管理/查询合同信息')">
                <button type="button" id="updateOrganization" class="btn btn-default">查询合同信息</button>
            </sec:authorize>
        </div>
        <div class="box-body">
            <table id="contractTable"
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
                   data-show-footer="false"
                   data-side-pagination="server"
                   data-page-list="[5,10, 25, 50, 100, ALL]">
            </table>
        </div>
        <div class="box-footer clearfix">

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