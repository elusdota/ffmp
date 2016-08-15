<%--
  Created by IntelliJ IDEA.
  User: jiangliang
  Date: 2016/8/12
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<link rel="stylesheet" href="<c:url value='/bower_components/bootstrap/dist/css/bootstrap.min.css'/>">
<section class="content-header">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            个人信息
            <small></small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-user"></i> 安全管理</a></li>
            <li class="active">个人信息</li>
        </ol>
    </section>
    <!-- Main content -->
    <section class="content">
        <div class="row" class="col-lg-12">
            <div class="form-group">
                <div class="row col-lg-6">
                    <form class="form-horizontal" id="saveForm">
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
                            <label for="name" class="col-lg-4 control-label">账户名称：<span
                                    class="required">*</span></label>

                            <div class="col-lg-8">
                                <input type="text" class="form-control" id="name" name="name" placeholder="账户名称"
                                       readonly>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" id="submitUpdateData">提交</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</section>
<script src="<c:url value='/bower_components/jquery-validation/dist/jquery.validate.js'/>"></script>
<script src="<c:url value='/bower_components/jquery-validation/dist/additional-methods.js'/>"></script>
<script src="<c:url value='/bower_components/jquery-validation/src/localization/messages_zh.js'/>"></script>
<script src="<c:url value='/js/system/information.js'/>"></script>