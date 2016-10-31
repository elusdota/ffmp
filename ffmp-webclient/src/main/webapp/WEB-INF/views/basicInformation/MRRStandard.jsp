<%--
  Created by IntelliJ IDEA.
  User: suelmer
  Date: 2016/7/16
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="<c:url value='/bower_components/AdminLTE/plugins/datepicker/datepicker3.css'/>">
<link rel="stylesheet" href="<c:url value='/bower_components/bootstrap-table/dist/bootstrap-table.min.css'/>">
<style type="text/css">
    *
    label.error {
        color: Red;
    }
</style>
<section class="content-header">
    <h1>
        设施维管标准管理
    </h1>
    <ol class="breadcrumb">
        <li><a href="index"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="#"> 基础信息管理</a></li>
        <li class="active"> 设施维管标准管理</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="box box-primary">
        <div class="box-header with-border">
            <%--<sec:authorize access="@userDetailsUtils.isAuthorized('/基础信息管理/设施维管标准管理/创建设施维管标准')">--%>
                <%--<button type="button" id="createMrrStandard" class="btn btn-default">创建设施维管标准</button>--%>
            <%--</sec:authorize>--%>
                <button type="button" id="createMrrStandardName" class="btn btn-default">创建设施名称</button>
                <button type="button" id="createMrrStandard" class="btn btn-default">创建维护管理项目</button>

                <button type="button" id="updateMrrStandardName" class="btn btn-default" disabled>修改设施名称</button>
                <button type="button" id="updateMrrStandard" class="btn btn-default" disabled>修改维护管理项目</button>

                <button type="button" id="deleteMrrStandardName" class="btn btn-default" disabled>删除设施名称</button>
                <button type="button" id="deleteMrrStandard" class="btn btn-default" disabled>删除维护管理项目</button>
            <%--<sec:authorize access="@userDetailsUtils.isAuthorized('/基础信息管理/设施维管标准管理/修改设施维管标准')">--%>
                <%--<button type="button" id="updateMrrStandard" class="btn btn-default hidden">修改设施维管标准</button>--%>
            <%--</sec:authorize>--%>
            <%--<sec:authorize access="@userDetailsUtils.isAuthorized('/基础信息管理/设施维管标准管理/删除设施维管标准')">--%>
                <%--<button type="button" id="deleteMrrStandard" class="btn btn-default hidden">删除设施维管标准</button>--%>
            <%--</sec:authorize>--%>
        </div>
        <div class="box-body">
            <table id="mrrstandardTable"
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
    <!-- 设施名称 模态框（Model） -->
    <div id="createMrrStandardNameModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span--%>
                    <%--aria-hidden="true">&times;</span></button>--%>
                    <h4 class="modal-title">创建设施名称</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <form class="form-horizontal" id="mrrStandardNameForm">
                            <div class="row">
                                <div class="form-group col-md-6">
                                    <label for="parent_code" class="col-md-4 control-label">上一级编码：</label>

                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="parent_code" name="parent_code"
                                               placeholder="编码"
                                               readonly>
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="parent_name" class="col-md-4 control-label">设施名称：</label>

                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="parent_name" name="parent_name"
                                               placeholder="设施名称"
                                               readonly>
                                    </div>
                                </div>
                            </div>
                            <hr/>
                            <div class="form-group col-md-6">
                                <label for="code" class="col-md-4 control-label">编码：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <div class="input-group" id="codeDiv">
                                        <span class="input-group-addon" id="pcode"></span>
                                        <input type="text" class="form-control" id="code" name="code" placeholder="编码"
                                               required="required">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="name" class="col-md-4 control-label">设施名称：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="name" name="name" placeholder="设施名称"
                                           required="required">
                                </div>
                            </div>
                            <input type="hidden" class="form-control" id="MRRStandardId" name="MRRStandardId">
                            <button type="reset" class="btn btn-warning hidden" id="resetMrrStandardName">重置</button>

                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="createMrrStandardNameBtn">创建</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
    </div>
    <!-- /.modal-dialog -->

    <!-- 维护管理项目 模态框（Model） -->
    <div id="createMrrStandardModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span--%>
                    <%--aria-hidden="true">&times;</span></button>--%>
                    <h4 class="modal-title">创建维护管理项目</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <form class="form-horizontal" id="mrrStandardForm">
                            <div class="row">
                                <div class="form-group col-md-6">
                                    <label for="parent_code_content" class="col-md-4 control-label">上一级编码：</label>

                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="parent_code_content" name="parent_code_content"
                                               placeholder="编码"
                                               readonly>
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="parent_name_content" class="col-md-4 control-label">设施名称：</label>

                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="parent_name_content" name="parent_name_content"
                                               placeholder="设施名称"
                                               readonly>
                                    </div>
                                </div>
                            </div>
                            <hr/>
                            <div class="form-group col-md-6">
                                <label for="code_content" class="col-md-4 control-label">编码：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <div class="input-group" id="codeDiv_content">
                                        <span class="input-group-addon" id="pcode_content"></span>
                                        <input type="text" class="form-control" id="code_content" name="code_content" placeholder="编码"
                                               required="required">
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="name_content" class="col-md-4 control-label">设施名称：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="name_content" name="name_content" placeholder="设施名称"
                                           required="required">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="mrrMethod" class="col-md-4 control-label">维保方式：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <select class="form-control" name="mrrMethod" id="mrrMethod">
                                        <option disabled="disabled" selected="selected">--请选择维保方式--</option>
                                        <option value="日常巡查">日常巡查</option>
                                        <option value="单项检查">单项检查</option>
                                        <option value="维护保养">维护保养</option>
                                        <option value="联动检查">联动检查</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="jobContent" class="col-md-4 control-label">工作内容：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <textarea class="form-control" rows="2" id="jobContent" name="jobContent"
                                              placeholder="工作内容"
                                              required="required"></textarea>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="proportion" class="col-md-4 control-label">抽查比例：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <input type="text" class="form-control required number" id="proportion" name="proportion"
                                           placeholder="抽查比例" >
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="lifetime" class="col-md-4 control-label">使用年限：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <div class="input-group">
                                        <input type="text" class="form-control required digits" id="lifetime" name="lifetime"
                                               placeholder="如果为0则无使用年限">
                                        <span class="input-group-addon">年</span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="changetime" class="col-md-4 control-label">检修年限：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <div class="input-group">
                                        <input type="text" class="form-control required digits" id="changetime" name="changetime"
                                               placeholder="如果为0则无检修年限">
                                        <span class="input-group-addon">年</span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="maturity" class="col-md-4 control-label">期限类型：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <select class="form-control" name="maturity" id="maturity">
                                        <%--<option disabled="disabled" selected="selected">--请选择期限类型--</option>--%>
                                        <option value="生产日期" selected="selected">生产日期</option>
                                        <option value="投入使用日期">投入使用日期</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inspection" class="col-md-4 control-label">检查方法：</label>

                                <div class="col-md-8">
                                    <textarea rows="2" class="form-control" id="inspection" name="inspection"
                                           placeholder="检查方法"></textarea>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="remark" class="col-md-4 control-label">备注：</label>

                                <div class="col-md-8">
                                    <textarea rows="2" class="form-control" id="remark" name="remark"
                                           placeholder="备注"></textarea>
                                </div>
                            </div>
                            <button type="reset" class="btn btn-warning hidden" id="resetMrrStandard">重置</button>
                        </form>
                    </div>
                    <div class="row">
                        <div class="box box-warning">
                            <div class="box-header with-border">
                                <h3 class="box-title">技术要求</h3>
                            </div>
                            <div class="box-body">
                                <form role="form" id="techniqueForm" class="form-horizontal">
                                    <div class="form-group col-md-6">
                                        <label for="tName" class="col-md-4 control-label">检查内容:<span
                                                class="required">*</span></label>

                                        <div class="col-md-8">
                                            <input type="text" class="form-control" id="tName" name="tName"
                                                   placeholder="检查内容" required="required">
                                        </div>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="tType" class="col-md-4 control-label">性质类别:<span
                                                class="required">*</span></label>

                                        <div class="col-md-8">
                                            <input type="text" class="form-control" id="tType" name="tType"
                                                   placeholder="性质类别" required="required">
                                        </div>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="description" class="col-md-4 control-label">技术规范：</label>

                                        <div class="col-md-8">
                                        <textarea rows="2" class="form-control" id="description" name="description"
                                                  placeholder="技术规范"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <div class="col-md-offset-10 col-md-2">
                                            <button type="reset" class="btn btn-warning" id="resetTechnique">重置</button>
                                            <button type="button" class="btn btn-primary" id="addTechnique">添加</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="box-footer">
                                <table id="techniqueTable"></table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="createMrrStandardBtn">创建</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
    </div>
    <!-- /.modal-dialog -->

    <!--技术要求-->
    <div id="techniqueModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">技术要求</h4>
                </div>
                <div class="modal-body">
                    <div id="techniqueContent"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-info" data-dismiss="modal">关闭</button>
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
<%--<script src="<c:url value='/bower_components/jquery.inputmask/dist/min/inputmask/inputmask.min.js'/>"></script>--%>
<%--<script src="<c:url value='/bower_components/jquery.inputmask/dist/min/inputmask/jquery.inputmask.min.js'/>"></script>--%>
<%--<script src="<c:url value='/bower_components/jquery.inputmask/dist/min/inputmask/inputmask.extensions.min.js'/>"></script>--%>
<script src="<c:url value='/js/basicInfo/mrrStandard.js'/>"></script>
<script src="<c:url value='/js/basicInfo/updateMrrStandard.js'/>"></script>