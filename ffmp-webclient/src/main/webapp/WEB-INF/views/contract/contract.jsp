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
                <button type="button" id="createContract" class="btn btn-default">创建合同信息</button>
            </sec:authorize>
            <sec:authorize access="@userDetailsUtils.isAuthorized('/合同管理/修改合同信息')">
                <button type="button" id="updateContract" class="btn btn-default" disabled>修改合同信息</button>
            </sec:authorize>
            <sec:authorize access="@userDetailsUtils.isAuthorized('/合同管理/查询合同信息')">
                <button type="button" id="deleteContract" class="btn btn-default" disabled>查询合同信息</button>
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

    <!-- 模态框（Model） -->
    <div id="contractModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">创建合同</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <form class="form-horizontal" id="contractForm">
                            <div class="form-group col-md-6">
                                <label for="name" class="col-md-4 control-label">合同名称：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="name" name="name" placeholder="合同名称"
                                           required="required">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="customer" class="col-md-4 control-label">客户名称：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="customer" name="customer" placeholder="客户名称"
                                           required="required">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="manager" class="col-md-4 control-label">负责人：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="manager" name="manager" placeholder="负责人"
                                           required="required">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="managerTel" class="col-md-4 control-label">负责人电话：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <input type="text" class="form-control"   id="managerTel" name="managerTel"
                                              placeholder="负责人电话"  required="required">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="payment" class="col-md-4 control-label">付款方式：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="payment" name="payment"
                                           placeholder="付款方式"
                                           required="required">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="agent" class="col-md-4 control-label">经办人：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="agent" name="agent"
                                           placeholder="经办人"  required="required">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="amount" class="col-md-4 control-label">合同金额：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">

                                    <div class="input-group">
                                        <input type="text" class="form-control required number" id="amount" name="amount"
                                               placeholder="合同金额">
                                        <div class="input-group-addon">元</div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="TaxNO" class="col-md-4 control-label">税号：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="TaxNO" name="TaxNO"
                                           placeholder="税号"  required="required">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="expiry" class="col-md-4 control-label">合同效期：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <input type="text" data-date-format="yyyy-mm-dd" data-date-end-date="0d"  class="form-control datepicker"
                                           data-provide="datepicker" id="expiry">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="contractType" class="col-md-4 control-label">合同类别：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <input  type="text" class="form-control"  id="contractType" name="contractType"
                                           placeholder="合同类别"  required="required">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="address" class="col-md-4 control-label">项目地址：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <input  type="text" class="form-control"  id="address" name="address"
                                            placeholder="项目地址"  required="required">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="content" class="col-md-4 control-label">合同内容：</label>

                                <div class="col-md-8">
                                    <textarea type="text" class="form-control" rows="2"  id="content" name="content"
                                           placeholder="合同内容"></textarea>
                                </div>
                            </div>
                            <button type="reset" class="btn btn-warning hidden" id="resetContract">重置</button>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="createContractdBtn">创建</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
    </div>
    <!-- /.modal-dialog -->
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
<script src="<c:url value='/js/contract/contract.js'/>"></script>