<%--
  Created by IntelliJ IDEA.
  User: jiangliang
  Date: 2016/7/15
  Time: 16:07
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
            项目信息
            <small></small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-wrench"></i> 维管工作管理</a></li>
            <li class="active">项目信息管理</li>
            <li class="active">项目信息</li>
        </ol>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <form class="form-horizontal" id="searchForm">
                <div class="form-group" hidden="true">
                    <input type="text" class="form-control" id="id" name="id" value="${id}"/>
                </div>
                <div class="form-group col-md-4">
                    <label for="name" class="col-lg-6 control-label">项目名称：</label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control" id="name" name="name" placeholder="项目名称"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="organization" class="col-lg-6 control-label">所属维保小组：</label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control" id="organization" name="organization"
                               placeholder="所属维保小组"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="customer" class="col-lg-6 control-label">所属客户：<span
                            class="required">*</span></label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control" id="customer" name="customer" placeholder="所属客户"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="address" class="col-lg-6 control-label">地址：<span
                            class="required">*</span></label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control" id="address" name="address" placeholder="地址"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="area" class="col-lg-6 control-label">建筑总面积：<span
                            class="required">*</span></label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control required digits" id="area" name="area"
                               placeholder="建筑总面积"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="totalHeight" class="col-lg-6 control-label">建筑总高度：<span
                            class="required">*</span></label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control required digits" id="totalHeight" name="totalHeight"
                               placeholder="建筑总高度"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="floors" class="col-lg-6 control-label">楼层：<span
                            class="required">*</span></label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control" id="floors" name="floors" placeholder="楼层"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="nature" class="col-lg-6 control-label">使用性质：<span
                            class="required">*</span></label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control" id="nature" name="nature" placeholder="使用性质"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="manager" class="col-lg-6 control-label">消防安全管理人：<span
                            class="required">*</span></label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control" id="manager" name="manager" placeholder="消防安全管理人"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="managerTelephone" class="col-lg-6 control-label">联系电话：<span
                            class="required">*</span></label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control" id="managerTelephone" name="managerTelephone"
                               placeholder="联系电话"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="equipmentCase" class="col-lg-6 control-label">消防设施情况：<span
                            class="required">*</span></label>

                    <div class="col-lg-6">
                        <input type="text" class="form-control" id="equipmentCase" name="equipmentCase"
                               placeholder="消防设施情况"
                               required="required">
                    </div>
                </div>
            </form>
        </div>
        <div class="row">
            <label for="name" class="col-lg-6 control-label">设备列表：</label>
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
<script src="<c:url value='/js/taskManagement/projectInformation.js'/>"></script>




