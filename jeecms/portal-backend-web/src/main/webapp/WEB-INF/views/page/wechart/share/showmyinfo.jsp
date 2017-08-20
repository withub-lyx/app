<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>个人信息</title>
    <meta name="keywords" content="个人信息">
    <meta name="description" content="个人信息">

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
            background-color: #fffefe;
            color: #CD2626;
        }
    </style>
</head>

<body class="gray-bg">
<row >
    <div class="wrapper wrapper-content">
        <div class="row animated fadeInRight">


            <div class="col-sm-12">
                <div class="widget-text-box">


                    <h4><strong>当前状态:</strong></h4>
                    <p>
                        <c:choose>
                            <c:when test="${myinfo.status==0}">
                                    <span class="badge badge-primary" style="white-space: normal;margin-left: 10px;" >${myinfo.statusDesc}</span></p>
                            </c:when>

                            <c:when test="${myinfo.status!=0}">
                                <span class="badge badge-danger" style="white-space: normal;margin-left: 10px;" >${myinfo.statusDesc}</span></p>
                            </c:when>

                </c:choose>

                    <h4><strong>昵称:</strong></h4>
                    <p>&nbsp;&nbsp;&nbsp;&nbsp;${myinfo.nickName}</p>


                    <h4><strong>年龄:</strong></h4>
                    <p> &nbsp;&nbsp;&nbsp;&nbsp;${myinfo.age} </p>


                    <h4><strong>所在城市:</strong></h4>
                    <p>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-map-marker"></i> ${province.provincename}  &nbsp;&nbsp;&nbsp; ${city.cityname} &nbsp;&nbsp;&nbsp;&nbsp;</p>

                    <h4><strong>在读／毕业高校:</strong></h4>
                    <p> &nbsp;&nbsp;&nbsp;&nbsp;${school.name}</p>

                    <h4><strong>毕业后去向:</strong></h4>
                    <p>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-map-marker"></i> ${province_id_after_school.provincename}  &nbsp;&nbsp;&nbsp; ${city_id_after_school.cityname} &nbsp;&nbsp;&nbsp;&nbsp;</p>



                    <h4>
                        <strong>关于我:</strong>
                    </h4>
                    <p style="word-break:break-all;text-align: justify;">
                        &nbsp;&nbsp;&nbsp;&nbsp;${myinfo.introduction}
                    </p>
                    <h4>
                        <strong>我想对TA说:</strong>
                    </h4>
                    <p style="word-break:break-all;text-align: justify;">
                        &nbsp;&nbsp;&nbsp;&nbsp; ${myinfo.sayToHer}
                    </p>

                    <h4>
                        <strong>形象照:</strong>
                    </h4>
                    <p style="word-break:break-all;text-align: center;">
                        <%--style="margin-left: 10px;"--%>
                        <img src="${ctx}${myinfo.photoImg}" class="img-responsive center-block" alt="profile" >
                    </p>


                    <h4>
                        <strong>微信二维码:</strong>
                    </h4>
                    <p style="word-break:break-all;text-align: center;">
                        <%--style="margin-left: 10px;"--%>
                        <img src="${ctx}${myinfo.contactQrCode}" class="img-responsive center-block" alt="profile" >
                    </p>


                    <%--是否是后台查看--%>
                    <c:if test="${!isBackend}">
                        <h5>
                            <button onclick="window.location.href='${ctx}/front/collectinfo/index?edit=true';" type="button" class="btn btn-primary btn-sm btn-block">修改信息</button>
                            <button onclick="window.location.href='${ctx}/front/collectinfo/search_result';" type="button" class="btn btn-primary btn-sm btn-block">匹配TA</button>
                            <button id="changeState"  type="button" class="btn btn-primary btn-sm btn-block">

                                <c:choose>
                                    <c:when test="${myinfo.status==3}">激活</c:when>
                                    <c:otherwise>禁用</c:otherwise>
                                </c:choose>
                                </button>
                        </h5>
                    </c:if>
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


    <c:choose>
    <c:when test="${myinfo.status==3}">
            $('#changeState').click(function () {
                swal({
                    title: "您确定要激活么",
                    text: "激活后你的信息将重新回到匹配库，匹配合适的TA",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确认",
                    cancelButtonText: "取消",
                    closeOnConfirm: false
                }, function () {
                    window.location.href='${ctx}/front/collectinfo/showInfo?changeStatus=true';
                });
            });
    </c:when>
    <c:otherwise>


            $('#changeState').click(function () {
                swal({
                    title: "您确定要禁用么",
                    text: "禁用后你的信息将被移出匹配库，小伙伴们不会再看到你的信息",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确认",
                    cancelButtonText: "取消",
                    closeOnConfirm: false
                }, function () {
                    window.location.href='${ctx}/front/collectinfo/showInfo?changeStatus=true';
                });
            });


    </c:otherwise>
    </c:choose>


</script>


</body>
</html>
