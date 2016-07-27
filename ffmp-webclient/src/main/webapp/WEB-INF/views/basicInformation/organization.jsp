<%--
  Created by IntelliJ IDEA.
  User: jiangliang
  Date: 2016/7/15
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
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
      组织机构管理
      <small>Version 1.0</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="#"><i class="fa fa-cogs"></i> 基础信息管理</a></li>
      <li class="active">组织机构管理</li>
    </ol>
  </section>
  <!-- Main content -->
  <section class="content">
    <div class="row" class="col-lg-12">
      <div class="form-group col-lg-12">
        <div class="box-footer">
          <sec:authorize access="@userDetailsUtils.isAuthorized('/基础信息管理/组织机构管理/创建机构')">
            <button type="button" id="createOrganization" class="btn btn-default">创建机构</button>
          </sec:authorize>
          <sec:authorize access="@userDetailsUtils.isAuthorized('/基础信息管理/组织机构管理/修改机构')">
            <button type="button" id="updateOrganization" class="btn btn-default pull-right">修改机构</button>
          </sec:authorize>
        </div>
        <div id="organization"/>
      </div>
    </div>
  </section>
  <!-- 模态框（Model） -->
  <div class="modal fade" id="organizationModel" tabindex="-1" role="dialog"
       aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">
            组织机构
          </h4>
        </div>
        <div class="modal-body">
          <form class="form-horizontal" id="organizationForm">
            <div class="form-group" hidden="true">
              <input type="text" class="form-control" id="organizationId" name="organizationId"/>
            </div>
            <div class="form-group">
              <label for="name" class="col-lg-4 control-label">组织机构名称：<span
                      class="required">*</span></label>

              <div class="col-lg-8">
                <input type="text" class="form-control" id="name" name="name" placeholder="组织机构名称"
                       required="required">
              </div>
            </div>
            <div class="form-group">
              <label for="type" class="col-lg-4 control-label">组织机构类型：<span
                      class="required">*</span></label>

              <div class="col-lg-8">
                <select class="form-control m-bot15" id="type" name="type" required="required">
                  <option value="0">部门</option>
                  <option value="1">分公司</option>
                  <option value="2">维保小组</option>
                </select>
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
<script src="<c:url value='/js/system/organization.js'/>"></script>

