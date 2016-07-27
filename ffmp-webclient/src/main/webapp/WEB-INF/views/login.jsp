<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>消防服务管理系统-系统用户登录</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

  <%@ include file="/WEB-INF/views/common/include.jsp" %>
  <style>
    .login-page, .register-page {
      background: #818181 none repeat scroll 0 0;
    }
  </style>
</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="#"><b>消防服务管理系统</b>V1.0</a>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">人和/智诚/卓越/善行/悦动</p>

    <form name='loginForm'
          action="<c:url value='/j_spring_security_check' />" method='POST'>
      <div class="form-group has-feedback">
        <input type="text" class="form-control"  name='username' placeholder="用户名">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type='password' name='password' class="form-control" placeholder="密码">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
       <div class="form-group has-feedback">
        <div class="row">
          <div class="col-md-6">
            <input type="text" class="form-control" placeholder="验证码">
          </div>
          <div class="col-md-6">
             <%--<img src="./captcha"  id="kaptchaImage" style="cursor:pointer" />--%>
             <a href="javascript:void(0)">看不清?换一张</a>
          </div>
      </div>
      </div>
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
            <label>
              <input type="checkbox"> 记住我
            </label>
          </div>
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
          <button name="submit" type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
        </div>
        <!-- /.col -->
      </div>
      <c:if test="${not empty error}">
        <div class="alert alert-warning alert-dismissible">
          <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
          <h4><i class="icon fa fa-warning"></i> 警告!</h4>
          <c:out value="${error}"/>
        </div>
      </c:if>
    </form> 

    <a href="#">忘记密码</a><br>
    <!-- <a href="register.html" class="text-center">Register a new membership</a> -->

  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- iCheck -->
<script src="<c:url value='/bower_components/AdminLTE/plugins/iCheck/icheck.min.js'/>"></script>
<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
  });
</script>
</body>
</html>
