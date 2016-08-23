<%--
  Created by IntelliJ IDEA.
  User: jiangliang
  Date: 2016/7/23
  Time: 15:12
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
      客户信息管理
      <small></small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="#"><i class="fa fa-wrench"></i> 客户信息管理</a></li>
    </ol>
  </section>
  <!-- Main content -->
  <section class="content">
    <div class="row">
      <div class="box-footer">
        <sec:authorize access="@userDetailsUtils.isAuthorized('/客户信息管理/注册客户基本信息')">
          <button type="button" id="createCustomer" class="btn btn-default pull-left">注册客户</button>
        </sec:authorize>
        <sec:authorize access="@userDetailsUtils.isAuthorized('/客户信息管理/修改客户基本信息')">
          <button type="button" id="updateCustomer" class="btn btn-default pull-left">修改客户</button>
        </sec:authorize>
      </div>
      <table id="customerTable"
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
  <!-- 模态框（Model） -->
  <div class="modal fade" id="customerModel" tabindex="-1" role="dialog"
       aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">
            客户
          </h4>
        </div>
        <div class="modal-body">
          <div class="row">
            <form class="form-horizontal" id="customerForm">
                <div class="form-group" hidden="true">
                    <input type="text" class="form-control" id="id" name="id"/>
                </div>
              <div class="form-group col-md-6">
                <label for="name" class="col-lg-6 control-label">客户名称：<span
                        class="required">*</span></label>

                <div class="col-lg-6">
                  <input type="text" class="form-control" id="name" name="name" placeholder="客户名称"
                         required="required">
                </div>
              </div>
              <div class="form-group col-md-6">
                <label for="contect" class="col-lg-4 control-label">联系人：<span
                        class="required">*</span></label>

                <div class="col-lg-8">
                  <input type="text" class="form-control" id="contect" name="contect" placeholder="联系人"
                         required="required">
                </div>
              </div>
              <div class="form-group col-md-6">
                <label for="telephone" class="col-lg-6 control-label">联系电话：<span
                        class="required">*</span></label>

                <div class="col-lg-6">
                  <input type="text" class="form-control" id="telephone" name="telephone"
                         placeholder="联系电话"
                         required="required">
                </div>
              </div>
              <div class="form-group col-md-6">
                <label for="address" class="col-lg-4 control-label">地址：<span
                        class="required">*</span></label>

                <div class="col-lg-8">
                  <input type="text" class="form-control" id="address" name="address"
                         placeholder="地址"
                         required="required">
                </div>
              </div>
              <div class="form-group col-md-6">
                <label for="email" class="col-lg-4 control-label">邮箱：<span
                        class="required">*</span></label>

                <div class="col-lg-8">
                  <input type="text" class="form-control required email" id="email"
                         name="email"
                         placeholder="邮箱">
                </div>
              </div>
              <div class="form-group col-md-6">
                <label for="taxId" class="col-lg-4 control-label">税号：<span
                        class="required">*</span></label>

                <div class="col-lg-8">
                  <input type="text" class="form-control required" id="taxId"
                         name="taxId"
                         placeholder="税号">
                </div>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" id="submitData">提交</button>
            <button type="button" class="btn btn-primary" id="giveupData">放弃</button>
          </div>
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
<script src="<c:url value='/bower_components/bootstrap-table/dist/locale/bootstrap-table-zh-CN.min.js'/>"></script>
<script src="<c:url value='/bower_components/AdminLTE/plugins/datepicker/bootstrap-datepicker.js'/>"></script>
<script src="<c:url value='/bower_components/AdminLTE/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js'/>"></script>
<script src="<c:url value='/bower_components/tableExport.jquery.plugin/tableExport.min.js'/>"></script>
<script src="<c:url value='/bower_components/jquery.base64.js/jquery.base64.js'/>"></script>
<script src="<c:url value='/js/customer/customer.js'/>"></script>




