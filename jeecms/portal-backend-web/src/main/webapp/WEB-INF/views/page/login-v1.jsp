<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="menutag" uri="http://www.portal.com/menutag" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>后台管理系统</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link href="${ctx}/resource/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx}/resource/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/resource/css/animate.css" rel="stylesheet">
    <link href="${ctx}/resource/css/style.css?v=4.1.0" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <script>if (window.top !== window.self) {
        window.top.location = window.location;
    }</script>
</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>

            <h1 class="logo-name" style="color: #f3f3f4">V1</h1>

        </div>
        <h3>高校联盟后台管理系统</h3>

        <form class="m-t" role="form" action="/login" method="post">
            <div class="form-group">
                <input name="username" type="text" class="form-control" placeholder="用户名" required="">
            </div>
            <div class="form-group">
                <input name="password" type="password" class="form-control" placeholder="密码" required="">
            </div>
            <span class="text-left" style="color: #ed5565;padding-bottom: 10px;">${error}</span>
            <button type="submit" class="btn btn-primary block full-width m-b" style="margin-top: 10px;">登 录</button>


       <%--     <p class="text-muted text-center"><a href="login.html#">
                <small>忘记密码了？</small>
            </a> | <a href="register.html">注册一个新账号</a>
            </p>--%>

        </form>
    </div>
</div>

<!-- 全局js -->
<script src="${ctx}/resource/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx}/resource/js/bootstrap.min.js?v=3.3.6"></script>
</body>

</html>
