<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>xxx</title>
    <meta name="keywords" content="后台页面">
    <meta name="description" content="单页面">

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
</head>

<body>
<row>
    <div class="col-sm-12 col-xs-12">
        <div class="contact-box">
            <a href="javascript:void(0);">
                <div class="col-sm-4 col-xs-4">
                    <div class="text-center">
                        <img alt="image" class="img-circle m-t-xs img-responsive" src="${ctx}/resource/img/head.png">

                        <div class="m-t-xs font-bold">CTO</div>
                    </div>
                </div>
            </a>

            <div class="col-sm-8 col-xs-8"><a href="javascript:void(0);">
                <h3><strong>奔波儿灞</strong></h3>

                <p><i class="fa fa-map-marker"></i> 上海·上海</p>
            </a>
                <address>
                    <strong>复旦大学</strong><br>
                    E-mail:xxx@qq.com<br>
                    Weibo:http://weibo.com/xxx<br>
                    <abbr title="Phone">Tel:</abbr> 13012860000
                </address>
            </div>
            <div class="clearfix"></div>

        </div>
    </div>
    <div class="col-sm-12 col-xs-12">
        <div class="contact-box">
            <a href="javascript:void(0);">
                <div class="col-sm-4 col-xs-4">
                    <div class="text-center">
                        <img alt="image" class="img-circle m-t-xs img-responsive" src="${ctx}/resource/img/head.png">

                        <div class="m-t-xs font-bold">CTO</div>
                    </div>
                </div>
            </a>

            <div class="col-sm-8 col-xs-8"><a href="javascript:void(0);">
                <h3><strong>奔波儿灞</strong></h3>

                <p><i class="fa fa-map-marker"></i> 上海·上海</p>
            </a>
                <address>
                    <strong>复旦大学</strong><br>
                    E-mail:xxx@qq.com<br>
                    Weibo:http://weibo.com/xxx<br>
                    <abbr title="Phone">Tel:</abbr> 13012860000
                </address>
            </div>
            <div class="clearfix"></div>

        </div>
    </div>
    <div class="col-sm-12 col-xs-12">
        <div class="contact-box">
            <a href="javascript:void(0);">
                <div class="col-sm-4 col-xs-4">
                    <div class="text-center">
                        <img alt="image" class="img-circle m-t-xs img-responsive" src="${ctx}/resource/img/head.png">

                        <div class="m-t-xs font-bold">CTO</div>
                    </div>
                </div>
            </a>

            <div class="col-sm-8 col-xs-8"><a href="javascript:void(0);">
                <h3><strong>奔波儿灞</strong></h3>

                <p><i class="fa fa-map-marker"></i> 上海·上海</p>
            </a>
                <address>
                    <strong>复旦大学</strong><br>
                    E-mail:xxx@qq.com<br>
                    Weibo:http://weibo.com/xxx<br>
                    <abbr title="Phone">Tel:</abbr> 13012860000
                </address>
            </div>
            <div class="clearfix"></div>

        </div>
    </div>
</row>


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
<script type="text/javascript">
    $('#birthday_input .input-group.date').datepicker({
        startView: 1,
        todayBtn: "linked",
        keyboardNavigation: false,
        forceParse: false,
        autoclose: true,
        format: "yyyy-mm-dd"
    });
</script>


</body>
</html>
