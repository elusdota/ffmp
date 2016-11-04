<%--
  Created by IntelliJ IDEA.
  User: jiangliang
  Date: 2016/7/15
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="<c:url value='/bower_components/bootstrap-table/dist/bootstrap-table.min.css'/>">
<link rel="stylesheet" href="<c:url value='/bower_components/AdminLTE/plugins/datepicker/datepicker3.css'/>">
<link rel="stylesheet" href="<c:url value='/bower_components/select2/dist/css/select2.min.css'/>">
<link rel="stylesheet" href="<c:url value='/bower_components/select2-bootstrap-theme/dist/select2-bootstrap.min.css'/>">
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
            创建项目
            <small></small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-wrench"></i> 维管工作管理</a></li>
            <li class="active">项目信息管理</li>
            <li class="active">创建项目</li>
        </ol>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <form class="form-horizontal" id="saveForm">
                <div class="form-group col-md-4">
                    <label for="name" class="col-md-6 control-label">项目名称：<span
                            class="required">*</span></label>

                    <div class="col-md-6">
                        <input type="text" class="form-control" id="name" name="name" placeholder="项目名称"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="organization" class="col-md-6 control-label">所属维保小组：<span
                            class="required">*</span></label>

                    <div class="col-md-6">
                        <%--<select class="form-control select2" id="organization" data-placeholder="所属维保小组">--%>
                            <%--<option></option>--%>
                        <%--</select>--%>
                        <input type="text" class="col-md-6 form-control" id="organization" name="organization"
                               placeholder="所属维保小组"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="customer" class="col-md-6 control-label">所属客户：<span
                            class="required">*</span></label>

                    <div class="col-md-6">
                        <select class="form-control select2" id="customer" data-placeholder="选择客户名称">
                        </select>
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="address" class="col-md-6 control-label">地址：<span
                            class="required">*</span></label>

                    <div class="col-md-6">
                        <input type="text" class="form-control" id="address" name="address" placeholder="地址"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="area" class="col-md-6 control-label">建筑总面积：<span
                            class="required">*</span></label>

                    <div class="col-md-6">
                        <div class="input-group">
                            <input type="text" class="form-control required number" id="area" name="area"
                                   placeholder="建筑总面积"
                                   required="required">
                            <span class="input-group-addon">m²</span>
                        </div>
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="totalHeight" class="col-md-6 control-label">建筑总高度：<span
                            class="required">*</span></label>

                    <div class="col-md-6">
                        <div class="input-group">
                            <input type="text" class="form-control required number" id="totalHeight" name="totalHeight"
                                   placeholder="建筑总高度"
                                   required="required">
                            <span class="input-group-addon">m</span>
                        </div>
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="floors" class="col-md-6 control-label">楼层：<span
                            class="required">*</span></label>

                    <div class="col-md-6">
                        <input type="text" class="form-control" id="floors" name="floors" placeholder="楼层"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="nature" class="col-md-6 control-label">使用性质：<span
                            class="required">*</span></label>

                    <div class="col-md-6">
                        <input type="text" class="form-control" id="nature" name="nature" placeholder="使用性质"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="manager" class="col-md-6 control-label">消防安全管理人：<span
                            class="required">*</span></label>

                    <div class="col-md-6">
                        <input type="text" class="form-control" id="manager" name="manager" placeholder="消防安全管理人"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="managerTelephone" class="col-md-6 control-label">联系电话：<span
                            class="required">*</span></label>

                    <div class="col-md-6">
                        <input type="text" class="form-control" id="managerTelephone" name="managerTelephone"
                               placeholder="联系电话"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="equipmentCase" class="col-md-6 control-label">消防设施情况：<span
                            class="required">*</span></label>

                    <div class="col-md-6">
                        <input type="text" class="form-control" id="equipmentCase" name="equipmentCase"
                               placeholder="消防设施情况"
                               required="required">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label class="control-label col-md-6">建筑投入使用日期：<span
                            class="required">*</span></label>

                    <div class="input-daterange col-md-6">
                        <input type="text" data-date-format="yyyy-mm-dd" class="form-control datepicker"
                               data-provide="datepicker" id="inputDate">
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label for="days" class="col-md-6 control-label">巡检日期：<span
                            class="required">*</span></label>

                    <div class="col-md-6">
                        <input type="text" class="form-control required digits" id="days" name="days"
                               placeholder=">0，<=28"
                               required="required">
                    </div>
                </div>
            </form>
            <div class="modal-footer">
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
<script src="<c:url value='/bower_components/select2/dist/js/i18n/zh-CN.js'/>"></script>
<script src="<c:url value='/bower_components/select2/dist/js/select2.full.min.js'/>"></script>
<script src="<c:url value='/js/taskManagement/createProject.js'/>"></script>



