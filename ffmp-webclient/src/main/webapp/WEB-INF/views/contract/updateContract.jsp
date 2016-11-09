<%--
  Created by IntelliJ IDEA.
  User: suelmer
  Date: 2016/7/18
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="<c:url value='/bower_components/bootstrap-table/dist/bootstrap-table.min.css'/>">
<link rel="stylesheet" href="<c:url value='/bower_components/AdminLTE/plugins/datepicker/datepicker3.css'/>">
<link rel="stylesheet" href="<c:url value='/bower_components/select2/dist/css/select2.min.css'/>">
<link rel="stylesheet" href="<c:url value='/bower_components/select2-bootstrap-theme/dist/select2-bootstrap.min.css'/>">
<style type="text/css">
    *
    label.error
    {
        color:Red;
    }
</style>

<section class="content-header">
    <h1>
        修改合同信息
    </h1>
    <ol class="breadcrumb">
        <li><a href="index"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="javascript:;" data-url="contract/contract"> 合同管理</a></li>
        <li class="active"> 修改合同信息</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="box box-info">
        <div class="box-header with-border">
            <div class="row">
                <form class="form-horizontal" id="update_ContractForm">
                    <input type="hidden" class="form-control" id="contractId" name="contractId" value="${param.id}"/>
                    <div class="form-group col-md-6">
                        <label for="name" class="col-md-4 control-label">合同名称：<span
                                class="required">*</span></label>

                        <div class="col-md-8">
                            <input type="text" class="form-control" id="name" name="name" placeholder="合同名称"
                                   required="required">
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="customer" class="col-md-4 control-label">客户名称：</label>

                        <div class="col-md-8">
                            <select class="form-control select2 "  id="customer" data-placeholder="选择客户名称" style="width: 100%;">
                            </select>
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
                            <input type="text" class="form-control" id="managerTel" name="managerTel"
                                   placeholder="负责人电话" required="required">
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="agent" class="col-md-4 control-label">经办人：<span
                                class="required">*</span></label>

                        <div class="col-md-8">
                            <input type="text" class="form-control" id="agent" name="agent"
                                   placeholder="经办人" required="required">
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="amount" class="col-md-4 control-label">合同金额：<span
                                class="required">*</span></label>

                        <div class="col-md-8">

                            <div class="input-group">
                                <input type="text" class="form-control required number" id="amount" name="amount"
                                       placeholder="合同金额" >

                                <div class="input-group-addon">元</div>
                            </div>
                        </div>
                    </div>
                    <%--<div class="form-group col-md-6">--%>
                        <%--<label for="taxNO" class="col-md-4 control-label">税号：<span--%>
                                <%--class="required">*</span></label>--%>

                        <%--<div class="col-md-8">--%>
                            <%--<input type="text" class="form-control" id="taxNO" name="taxNO"--%>
                                   <%--placeholder="税号" required="required">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group col-md-6 date">
                        <label for="expiry" class="col-md-4 control-label">合同效期：<span
                                class="required">*</span></label>

                        <div class="col-md-8">
                            <input type="text" data-date-format="yyyy-mm-dd"
                                   class="form-control required datepicker"
                                   data-provide="datepicker" id="expiry">
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="contractType" class="col-md-4 control-label">合同类别：<span
                                class="required">*</span></label>

                        <div class="col-md-8">
                            <select class="form-control" id="contractType" required="required">
                                <option disabled="disabled" selected="selected">--请选择合同类别--</option>
                                <option value="自营--维保">自营--维保</option>
                                <option value="自营--检测">自营--检测</option>
                                <option value="合作--维保">合作--维保</option>
                                <option value="合作--检测">合作--检测</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="address" class="col-md-4 control-label">项目地址：<span
                                class="required">*</span></label>

                        <div class="col-md-8">
                            <input type="text" class="form-control" id="address" name="address"
                                   placeholder="项目地址" required="required">
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="content" class="col-md-4 control-label">合同内容：</label>

                        <div class="col-md-8">
                                    <textarea class="form-control" rows="2" id="content" name="content"
                                              placeholder="合同内容"></textarea>
                        </div>
                    </div>
                    <button type="reset" class="btn btn-warning hidden" id="resetContract">重置</button>
                </form>
            </div>
        </div>
        <div class="box-body">
            <div><h4>付款方式：</h4></div>
            <div class="table-responsive">
                <table id="paymentTable" data-unique-id="id"></table>
            </div>
        </div>
        <div class="box-footer clearfix">
            <button type="button"  class="btn btn-danger"  id="cancelBtn">取消</button>
            <button type="button" class="btn btn-primary pull-right" id="saveContractdBtn">保存</button>
        </div>
    </div>

    <!-- 模态框（Model） -->
    <div id="paymentModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">修改付款方式</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <form role="form" id="paymentMethodForm" class="form-horizontal">

                            <div class="form-group col-md-6 date">
                                <label for="paymentDate" class="col-md-4 control-label">付款时间：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <input type="text" data-date-format="yyyy-mm-dd"
                                           class="form-control required datepicker"
                                           data-provide="datepicker" id="paymentDate" name="paymentDate">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="paymentAmount" class="col-md-4 control-label">付款金额：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <div class="input-group">
                                        <input type="text" class="form-control required number" id="paymentAmount" name="paymentAmount"
                                               placeholder="付款金额" >
                                        <div class="input-group-addon">元</div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="receipt" class="col-md-4 control-label">票据类型: <span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <select class="form-control required" id="receipt">
                                        <option disabled="disabled" selected="selected">--请选择票据类型--</option>
                                        <option value="普票">普票</option>
                                        <option value="专票">专票</option>
                                        <option value="收据">收据</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="confirmation" class="col-md-4 control-label">确认收款：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="confirmation" id="confirmationYes" value="true" >
                                            是
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="confirmation" id="confirmationNo" value="false">
                                            否
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-12">
                                <div class="col-md-offset-10 col-md-2">
                                    <input type="hidden" class="form-control" id="paymentMethodId" name="paymentMethodId"/>
                                    <button type="reset" class="btn btn-warning hidden" id="resetPaymentMethod">重置</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal"  id="cancel">取消</button>
                    <button type="button" class="btn btn-primary pull-right" id="updatePaymentBtn">确定</button>
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
<script src="<c:url value='/bower_components/select2/dist/js/i18n/zh-CN.js'/>"></script>
<script src="<c:url value='/bower_components/select2/dist/js/select2.full.min.js'/>"></script>
<script src="<c:url value='/js/contract/updateContract.js'/>"></script>