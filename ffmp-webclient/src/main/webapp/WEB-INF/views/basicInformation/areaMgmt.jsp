<%--
  Created by IntelliJ IDEA.
  User: suelmer
  Date: 2016/7/2
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style type="text/css">
    *
    label.error
    {
        color:Red;
    }
</style>

<section class="content-header">
    <h1>
        地区城市管理
    </h1>
    <ol class="breadcrumb">
        <li><a href="index"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li><a href="#"> 基础信息管理</a></li>
        <li class="active"> 地区城市管理</li>
    </ol>
</section>

<!-- Main content -->
<section class="content">
    <div class="box box-primary">
        <div class="row">
            <div class="col-md-12">

                <div class="form-group col-md-3">
                    <label>省/直辖市/自治区</label>
                    <select class="form-control " name="province" id="area-province"
                            onchange="provinceOnChange();">
                    </select>
                </div>
                <div class="form-group col-md-3">
                    <label>州/市</label>
                    <select class="form-control " name="city" id="area-city"
                            onchange="cityOnChange();">
                    </select>
                </div>
                <div class="form-group col-md-3">
                    <label>区/县</label>
                    <select class="form-control " name="county" id="area-county"
                            onchange="countyOnChange();">
                    </select>
                </div>
                <div class="form-group col-md-3">
                    <label>街道/居委会</label>
                    <select class="form-control " name="street" id="area-street">
                    </select>
                </div>
            </div>
        </div>
    </div>
</section>

<script src="<c:url value='/js/system/organization.js'/>"></script>
