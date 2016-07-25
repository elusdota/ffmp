<%--
  Created by IntelliJ IDEA.
  User: jiangliang
  Date: 2016/6/22
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="<c:url value='/bower_components/bootstrap-table/dist/bootstrap-table.min.css'/>">
<link rel="stylesheet" href="<c:url value='/bower_components/bootstrap-treeview/src/css/bootstrap-treeview.css'/>">
<section class="content-header">
    <h1>
        账户管理
        <small>Version 2.0</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-cogs"></i> 系统管理</a></li>
        <li class="active">账户管理</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="form-group col-lg-6">
            <div class="box-footer">
                <sec:authorize access="@userDetailsUtils.isAuthorized('/系统管理/账户管理/创建账户')">
                    <button type="button" id="createAccount" class="btn btn-default pull-left">创建账户</button>
                </sec:authorize>
                <sec:authorize access="@userDetailsUtils.isAuthorized('/系统管理/账户管理/更改账户密码')">
                    <button type="button" id="updateAccount" class="btn btn-default pull-right">修改账户</button>
                </sec:authorize>
            </div>
            <table id="accountTable"
                   data-toolbar="#toolbar"
                   data-show-refresh="true"
                   data-show-toggle="true"
                   data-search="true"
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
        <div class="form-group col-lg-6">
            <div class="box-footer">
                <sec:authorize access="@userDetailsUtils.isAuthorized('/系统管理/账户管理/为账户分配角色')">
                    <button type="button" id="allocationRole" class="btn btn-default">分配角色</button>
                </sec:authorize>
            </div>
            <div id="roles"/>
        </div>
    </div>
    <!-- 模态框（Model） -->
    <div class="modal fade" id="accountModel" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">
                        账户
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="accountForm">
                        <div class="form-group">
                            <label for="name" class="col-lg-4 control-label">账户名称：<span
                                    class="required">*</span></label>

                            <div class="col-lg-8">
                                <input type="text" class="form-control" id="name" name="name" placeholder="账户名称"
                                       required="required">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" id="submitData">提交</button>
                            <button type="button" class="btn btn-primary" id="giveupData">放弃</button>
                        </div>
                    </form>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
    </div>
    <div class="modal fade" id="accountUpdateModel" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">
                        修改账户密码
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="accountUpdateForm">
                        <div class="form-group" hidden="true">
                            <input type="text" class="form-control" id="accountId" name="accountId"/>
                        </div>
                        <div class="form-group">
                            <label for="password" class="col-lg-4 control-label">原密码：<span
                                    class="required">*</span></label>

                            <div class="col-lg-8">
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="原密码"
                                       required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="newpassword" class="col-lg-4 control-label">新密码：<span
                                    class="required">*</span></label>

                            <div class="col-lg-8">
                                <input type="password" class="form-control" id="newpassword" name="newpassword"
                                       placeholder="新密码"
                                       required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name1" class="col-lg-4 control-label">账户名称：<span
                                    class="required">*</span></label>

                            <div class="col-lg-8">
                                <input type="text" class="form-control" id="name1" name="name1" placeholder="账户名称"
                                       readonly>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" id="submitUpdateData">提交</button>
                            <button type="button" class="btn btn-primary" id="giveupUpdateData">放弃</button>
                        </div>
                    </form>
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
<script src="<c:url value='/bower_components/bootstrap-treeview/src/js/bootstrap-treeview.js'/>"></script>
<script src="<c:url value='/bower_components/bootstrap-table/dist/locale/bootstrap-table-zh-CN.min.js'/>"></script>
<script src="<c:url value='/bower_components/tableExport.jquery.plugin/tableExport.min.js'/>"></script>
<script src="<c:url value='/bower_components/jquery.base64.js/jquery.base64.js'/>"></script>
<script src="<c:url value='/js/system/account.js'/>"></script>
