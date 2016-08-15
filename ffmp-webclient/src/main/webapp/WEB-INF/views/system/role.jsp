<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<link rel="stylesheet" href="<c:url value='/bower_components/bootstrap/dist/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/bower_components/bootstrap-treeview/src/css/bootstrap-treeview.css'/>">
<section class="content-header">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            角色管理
            <small>/small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-cogs"></i> 系统管理</a></li>
            <li class="active">角色管理</li>
        </ol>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row" class="col-lg-12">
            <div class="form-group col-lg-6">
                <div class="box-footer">
                    <sec:authorize access="@userDetailsUtils.isAuthorized('/系统管理/角色管理/创建角色')">
                        <button type="button" id="createRole" class="btn btn-default">创建角色</button>
                    </sec:authorize>
                    <sec:authorize access="@userDetailsUtils.isAuthorized('/系统管理/角色管理/修改角色名称')">
                        <button type="button" id="updateRole" class="btn btn-default pull-right">修改角色</button>
                    </sec:authorize>
                </div>
                <div id="roles"/>
            </div>
            <div class="form-group col-lg-6">
                <div class="box-footer">
                    <sec:authorize access="@userDetailsUtils.isAuthorized('/系统管理/角色管理/为角色分配权限')">
                        <button type="button" id="anth" class="btn btn-default">角色分配权限</button>
                    </sec:authorize>
                </div>
                <div id="grantedAuthorities"/>
            </div>
        </div>
    </section>
    <!-- 模态框（Model） -->
    <div class="modal fade" id="roleModel" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">
                        角色
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="roleForm">
                        <div class="form-group" hidden="true">
                            <input type="text" class="form-control" id="roleId" name="roleId"/>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-lg-4 control-label">角色名称：<span
                                    class="required">*</span></label>

                            <div class="col-lg-8">
                                <input type="text" class="form-control" id="name" name="name" placeholder="角色名称"
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
</section>
<script src="<c:url value='/bower_components/jquery-validation/dist/jquery.validate.js'/>"></script>
<script src="<c:url value='/bower_components/jquery-validation/dist/additional-methods.js'/>"></script>
<script src="<c:url value='/bower_components/jquery-validation/src/localization/messages_zh.js'/>"></script>
<script src="<c:url value='/bower_components/bootstrap-treeview/src/js/bootstrap-treeview.js'/>"></script>
<script src="<c:url value='/js/system/role.js'/>"></script>
