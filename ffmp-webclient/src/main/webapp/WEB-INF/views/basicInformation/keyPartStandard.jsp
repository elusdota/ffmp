<%--
  Created by IntelliJ IDEA.
  User: suelmer
  Date: 2016/7/16
  Time: 10:25
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
    label.error
    {
        color:Red;
    }
</style>

<section class="content-header">
  <h1>
    重点部位标准管理
  </h1>
  <ol class="breadcrumb">
    <li><a href="index"><i class="fa fa-dashboard"></i> 主页</a></li>
    <li><a href="#"> 基础信息管理</a></li>
    <li class="active"> 重点部位标准管理</li>
  </ol>
</section>

<!-- Main content -->
<section class="content">
  <div class="box box-primary">
    <div class="box-header with-border">
      <sec:authorize access="@userDetailsUtils.isAuthorized('/基础信息管理/重点部位标准管理/创建重点部位标准')">
        <button type="button" id="createKeyPartStandard" class="btn btn-default">创建重点部位标准</button>
      </sec:authorize>
      <sec:authorize access="@userDetailsUtils.isAuthorized('/基础信息管理/重点部位标准管理/修改重点部位标准')">
        <button type="button" id="updateKeyPartStandard" class="btn btn-default" disabled>修改重点部位标准</button>
      </sec:authorize>
      <sec:authorize access="@userDetailsUtils.isAuthorized('/基础信息管理/重点部位标准管理/删除重点部位标准')">
        <button type="button" id="deleteKeyPartStandard" class="btn btn-default" disabled>删除重点部位标准</button>
      </sec:authorize>
    </div>
    <div class="box-body">
      <table id="keyPartStandardTable"
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

    <!--创建重点部位标准/修改重点部位标准-->
    <div class="modal fade" id="keyPartStandardModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">创建重点部位标准</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <form id="keyPartStandardForm" class="form-horizontal">
                            <div class="form-group col-md-6">
                                <label for="name" class="col-md-4 control-label">重点部位名称:<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="name" name="name"
                                           placeholder="重点部位名称" required="required">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="mrrContent" class="col-md-4 control-label">维护管理内容:<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="mrrContent" name="mrrContent"
                                           placeholder="维护管理内容" required="required">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="mrrMethod" class="col-md-4 control-label">维管方式：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="mrrMethod" name="mrrMethod"
                                           placeholder="维管方式" required="required">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="proportion" class="col-md-4 control-label">抽查比例：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="proportion" name="proportion"
                                           placeholder="抽查比例" required="required">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="control" class="col-md-4 control-label">修改控制：<span
                                        class="required">*</span></label>

                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="control" name="control"
                                           placeholder="修改控制" required="required">
                                </div>
                            </div>
                            <input type="hidden" class="form-control" id="keyPartStandardId" name="keyPartStandardId">
                            <input type="hidden" class="form-control" id="keyPartStandardVersion" name="keyPartStandardVersion">
                            <input type="hidden" class="form-control" id="keyPartStandardAccessType"
                                   name="keyPartStandardAccessType">
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-warning" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="createKeyPartStandardBtn">保存</button>
                </div>
            </div>
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
<script src="<c:url value='/js/basicInfo/keyPartStandard.js'/>"></script>