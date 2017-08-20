<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>匹配结果</title>
    <meta name="keywords" content="匹配结果">
    <meta name="description" content="匹配结果">

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${ctx}/resource/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx}/resource/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx}/resource/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${ctx}/resource/css/animate.css" rel="stylesheet">
    <link href="${ctx}/resource/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="${ctx}/resource/css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <link href="${ctx}/resource/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <!-- Sweet Alert -->
    <link href="${ctx}/resource/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${ctx}/resource/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${ctx}/resource/css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="${ctx}/resource/css/plugins/datapicker/datepicker3.css" rel="stylesheet">


    <style type="text/css">
        .btn-primary {
            background-color: #676a6c;
            border-color: #676a6c;
            color: #FFFFFF;
        }


        .btn-primary:hover, .btn-primary:focus, .btn-primary:active, .btn-primary.active, .open .dropdown-toggle.btn-primary {
            background-color: #676a6c;
            border-color: #676a6c;
            color: #FFFFFF;
        }
    </style>
</head>

<body class="gray-bg" >
<div class="middle-box text-center animated fadeInDown" style="height: 100%;">
    <h3 class="font-bold" style="margin-top:45%">没有找到TA！</h3>
    <div class="error-desc">
        暂时还没有合适的TA,请稍候再试
    </div>
    <h5>
        <button type="button" class="btn btn-w-m btn-primary"  onclick="window.location.href='${ctx}/front/collectinfo/index';">返回</button>
    </h5>
</div>


<!-- 全局js -->
<script src="${ctx}/resource/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx}/resource/js/plugins/jqueryForm/jquery.form.js"></script>
<script src="${ctx}/resource/js/plugins/toastr/toastr.min.js"></script>
<script src="${ctx}/resource/js/bootstrap.min.js?v=3.3.6"></script>
<!-- layer javascript -->
<%--<scripssopt src="${ctx}/resource/js/plugins/layer/layer.min.js"></script>--%>
<script src="${ctx}/resource/js/plugins/layer/layer3_0_1.min.js"></script>
<!-- Sweet alert -->
<script src="${ctx}/resource/js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="${ctx}/resource/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${ctx}/resource/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="${ctx}/resource/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${ctx}/resource/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${ctx}/resource/js/plugins/validate/messages_zh.min.js"></script>
<script src="${ctx}/resource/js/content.js?v=1.0.0"></script>
<script src="${ctx}/resource/js/plugins/iCheck/icheck.min.js"></script>
<!-- Data picker -->
<script src="${ctx}/resource/js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/resource/js/plugins/chosen/chosen.jquery.js"></script>
<script src="${ctx}/resource/auth/js/global.js"></script>



</body>
</html>
