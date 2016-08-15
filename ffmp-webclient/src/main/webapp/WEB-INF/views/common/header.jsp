<%--
  Created by IntelliJ IDEA.
  User: suelmer
  Date: 2016/5/30
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header class="main-header">
    <!-- Logo -->
    <a href="index" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>消</b>v</span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>建筑消防设施维护保养管理系统</b>version-1.0</span>
    </a>

    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>
        <!-- Navbar Right Menu -->
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <li class="dropdown user user-menu">
                    <a href="javascript:" class="dropdown-toggle" data-toggle="dropdown">
                        <c:if test="${not empty username}">
                            <span class="hidden-xs"><c:out value="${username}"/></span>
                        </c:if>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <p>
                                这是系统web端
                                <small>欢迎使用</small>
                            </p>
                        </li>
                        <!-- Menu Footer-->
                        <li class="user-footer">
                            <div class="pull-left">
                                <%--<a href="#" class="btn btn-default btn-flat">个人信息</a>--%>
                                <button type="button" id="personalInformation" class="btn btn-default pull-left">个人信息
                                </button>
                            </div>
                            <div class="pull-right">
                                <form name='loginForm'
                                      action="<c:url value='/j_spring_security_logout'/>">
                                    <input class="btn btn-default" type="submit" value="退出登录"/>
                                </form>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>

    </nav>
</header>
<%--左侧导航菜单 ，包含logo和侧边栏--%>
<aside class="main-sidebar">
    <section class="sidebar">
        <ul class="sidebar-menu" id="navigantion">
        </ul>
    </section>
</aside>
<div class="row" id="tips">
</div>
