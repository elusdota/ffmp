<%--
  Created by IntelliJ IDEA.
  User: jiangliang
  Date: 2016/8/11
  Time: 12:14
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
            基础信息管理
            <small>Version 2.0</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-share-alt"></i> 基础信息管理</a></li>
            <li class="active">职工管理</li>
            <li class="active">创建职工</li>
        </ol>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <form class="form-horizontal" id="saveForm">
                <div class="form-group col-md-4">
                    <label for="name" class="col-lg-6 control-label">职工名称：<span
                            class="required">*</span></label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control" id="name" name="name" placeholder="职工名称"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="code" class="col-lg-6 control-label">编号：<span
                            class="required">*</span></label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control" id="code" name="code"
                               placeholder="编号"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="sex" class="col-lg-6 control-label">性别：<span
                            class="required">*</span></label>
                    <div class="col-lg-6">
                        <select class="col-lg-1 combobox form-control" name="sex" id="sex" readonly>
                            <option VALUE="男">男</option>
                            <option VALUE="女">女</option>
                        </select>
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="cardid" class="col-lg-6 control-label">身份证：<span
                            class="required">*</span></label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control" id="cardid" name="cardid" placeholder="身份证"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="phone" class="col-lg-6 control-label">电话：<span
                            class="required">*</span></label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control required digits" id="phone" name="phone"
                               placeholder="电话"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="email" class="col-lg-6 control-label">邮箱：<span
                            class="required">*</span></label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control required email" id="email" name="email"
                               placeholder="邮箱">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="role" class="col-lg-6 control-label">职务：<span
                            class="required">*</span></label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control" id="role" name="role" placeholder="职务"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="certificate" class="col-lg-6 control-label">证书：</label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control" id="certificate" name="certificate" placeholder="证书">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="professional" class="col-lg-6 control-label">职称：</label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control" id="professional" name="professional" placeholder="职称"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="date" class="col-lg-6 control-label">入职时间：<span
                            class="required">*</span></label>

                    <div class="col-lg-6">
                        <input type="text" data-date-format="yyyy-mm-dd" class="form-control datepicker"
                               data-provide="datepicker" data-date-end-date="0d" id="date" required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="serving" class="col-lg-6 control-label">是否离职：</label>

                    <div class="col-lg-6 checkbox">
                        <select class="col-lg-1 combobox form-control" name="work" id="work" readonly>
                            <option VALUE="true">在职</option>
                            <option VALUE="false">离职</option>
                        </select>
                    </div>
                </div>
            </form>
        </div>
        <div id="page-selection"></div>
        <div class="modal-footer">
            <button type="button" id="save" class="btn btn-default pull-left">保存</button>
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
<script src="<c:url value='/js/basicInfo/createEmployee.js'/>"></script>



