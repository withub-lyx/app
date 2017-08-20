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
        .lazur-bg {
            background-color: #FFC1C1;
            color: #ffffff;
        }

        .btn-primary:hover, .btn-primary:focus, .btn-primary:active, .btn-primary.active, .open .dropdown-toggle.btn-primary {
            background-color: #676a6c;
            border-color: #676a6c;
            color: #FFFFFF;
        }

        .btn-primary {
            background-color: #676a6c;
            border-color: #676a6c;
            color: #FFFFFF;
        }

        .yellow-bg {
            background-color: #f3f3f4;
            color: #CD2626;
        }


        .navy-bg {
            background-color: #f3f3f4;
        }

    </style>
</head>

<body class="gray-bg">
<row >
    <div class="wrapper wrapper-content">
        <div class="row animated fadeInRight">

            <c:if test="${nowLoginUser.status==0}">
                <div class="col-sm-12">
                    <div class="alert" style="color: black;background-color: #FFC1C1;font-size: 13px;text-align: justify;">
                        您填写的个人信息已通过审核，我们会按照条件匹配推送给别的小伙伴，以下是目前给你匹配到相对合适的人选。
                    </div>
                </div>
            </c:if>


            <c:if test="${nowLoginUser.status==1}">
                <div class="col-sm-12">
                    <%--alert-warning --%>
                    <div class="alert" style="color: black;background-color: #FFC1C1;font-size: 13px;text-align: justify;">
                        <i class="glyphicon glyphicon-info-sign"></i>您填写的信息正在审核当中，需要通过审核才能被别的小伙伴看到，如有需要，请在自定义菜单中查看/修改您的个人信息，以下是按照您的条件匹配到的小伙伴。
                    </div>
                </div>
            </c:if>
            <div class="col-sm-12">
                <div class="widget-head-color-box navy-bg p-lg text-center" style="padding:30px 0px 0px 0px;">
                    <div class="m-b-md">
                        <h2 class="font-bold no-margins" style="color: #676a6c;">
                            ${recommendUser.nickName}
                        </h2>
                    </div>
                    <img  style="border-radius: 3%;" src="${ctx}${recommendUser.photoImg}" class="img-responsive center-block img-circle circle-border m-b-md" alt="profile">
                </div>
                <div class="widget-text-box">

                    <h4><strong>TA的昵称:</strong></h4>
                    <p>&nbsp;&nbsp;&nbsp;&nbsp;${recommendUser.nickName}</p>


                    <h4><strong>TA的年龄:</strong></h4>
                    <p> &nbsp;&nbsp;&nbsp;&nbsp;${recommendUser.age} </p>


                    <h4><strong>TA所在城市:</strong></h4>
                    <p>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-map-marker"></i> ${province.provincename}  &nbsp;&nbsp;&nbsp; ${city.cityname} &nbsp;&nbsp;&nbsp;&nbsp;</p>

                    <h4><strong>TA所在读／毕业高校:</strong></h4>
                    <p> &nbsp;&nbsp;&nbsp;&nbsp;${school.name}</p>

                    <h4><strong>TA毕业后去向:</strong></h4>
                    <p>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-map-marker"></i> ${province_id_after_school.provincename}  &nbsp;&nbsp;&nbsp; ${city_id_after_school.cityname} &nbsp;&nbsp;&nbsp;&nbsp;</p>



                    <h4>
                        <strong>关于TA:</strong>
                    </h4>
                    <p style="word-break:break-all;text-align: justify;">
                        &nbsp;&nbsp;&nbsp;&nbsp;${recommendUser.introduction}
                    </p>
                    <h4>
                        <strong>TA想对你说:</strong>
                    </h4>
                    <p style="word-break:break-all;text-align: justify;">
                        &nbsp;&nbsp;&nbsp;&nbsp; ${recommendUser.sayToHer}
                    </p>
                </div>
            </div>

            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <%--<div class="ibox-title">--%>
                        <%--<h5>TA的微信二维码</h5>--%>
                    <%--</div>--%>
                    <div>

                        <div class="ibox-content no-padding border-left-right">
                            <div class="widget-text-box">
                                <p style="word-break:break-all;">
                                    <i class="glyphicon glyphicon-info-sign"></i>长按二维码进行扫描，去联系有缘的TA.
                                </p>
                            </div>

                            <img alt="image" style="width: 100%;text-align: center;" class="img-responsive" src="${ctx}${recommendUser.contactQrCode}">
                        </div>
                    </div>
                </div>
            </div>

            <div class="col-sm-12">
                <div class="widget yellow-bg p-lg text-center">
                    <div class="m-b-md">
                        <i class="fa fa-warning fa-4x"></i>
                        <h3 class="font-bold no-margins">
                            注意事项
                            <%--text-left--%>
                        </h3>
                        <p class="font-bold no-margins" style="text-align: justify;">
                            您在一周内只能主动匹配一个TA，不要贪心哦，请自行联系对方。本平台对信息的虚实无法进行担保，请予以理解，与匹配对象交流过程中，请自行注意安全，高危互动请验证对方身份。
                        </p>
                    </div>
                </div>
            </div>
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
