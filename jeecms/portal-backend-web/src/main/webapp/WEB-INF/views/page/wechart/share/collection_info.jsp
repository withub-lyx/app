<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <c:choose>
        <c:when test="${isEdit}">
            <title>修改信息</title>
            <script type="text/javascript">
                var isEdit=true;
            </script>
        </c:when>
        <c:otherwise>
            <title>我的信息</title>
            <script type="text/javascript">
                var isEdit=false;
            </script>
        </c:otherwise>
    </c:choose>
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

        .btn-primary , .btn-primary.disabled, .btn-primary.disabled:hover, .btn-primary.disabled:focus, .btn-primary.disabled:active, .btn-primary.disabled.active, .btn-primary[disabled], .btn-primary[disabled]:hover, .btn-primary[disabled]:focus, .btn-primary[disabled]:active, .btn-primary.active[disabled], fieldset[disabled] .btn-primary, fieldset[disabled] .btn-primary:hover, fieldset[disabled] .btn-primary:focus, fieldset[disabled] .btn-primary:active, fieldset[disabled] .btn-primary.active {
            background-color: #676a6c;
            border-color: #676a6c;
            color: #FFFFFF;
        }



        .yellow-bg {
            background-color: #fffefe;
            color: #CD2626;
        }


    </style>
    <%--<link rel="stylesheet" type="text/css" href="${ctx}/resource/css/plugins/webuploader/webuploader.css">--%>


    <style>

        .webuploader-container {
            position: relative;
        }

        .webuploader-element-invisible {
            position: absolute !important;
            clip: rect(1px 1px 1px 1px); /* IE6, IE7 */
            clip: rect(1px, 1px, 1px, 1px);
        }

        .webuploader-pick-disable {
            opacity: 0.6;
            pointer-events: none;
        }


    </style>
</head>

<body>
<div class="col-sm-12">
    <div class="ibox float-e-margins">


        <h1>填写个人资料</h1>

        <h4><strong>请认真填写如下信息</strong></h4>

        <p><i class="glyphicon glyphicon-info-sign" style="text-align: justify;"></i>我们会在收集信息以后，将合适的TA推荐给你。</p>
        <h5 style="text-align: justify;">
            严禁代替他人填写信息，请确保您填写的信息真实可靠，本平台将人工审核信息的完整度和规范度，审核成功后方可在系统中显示。
        </h5>

        <form id="form">
            <input type="hidden" name="id" value="${weixinUser.id}">

            <div class="widget lazur-bg p-xl" style="color:black;">
                <h4>
                    1.昵称
                </h4>

                <div class="form-group">
                    <input maxlength="10" id="nickName" name="nickName" type="text" class=" form-control m-b" value="${weixinUser.nickName}">
                </div>
            </div>




            <div class="widget lazur-bg p-xl" style="color:black;">
                <h4>
                    2.年龄
                </h4>

                <select id="age" name="age" class="form-control m-b" >
                    <c:forEach items="${ageScope}" var="item">
                        <option value="${item}"  <c:if test="${weixinUser.age==item}">selected="selected"</c:if>  >${item}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="widget lazur-bg p-xl" style="color:black;">
                <h4>
                    3.性别
                </h4>

                <select id="sex"  name="sex" class="form-control m-b" >

                    <c:forEach items="${sexSelect}" var="item">
                        <option value="${item.code}"   <c:if test="${weixinUser.sex==item.code}">selected="selected"</c:if>     >${item.cnName}</option>
                    </c:forEach>
                </select>
            </div>


            <div class="widget lazur-bg p-xl" style="color:black;">
                <h4>
                    4.所在省市
                </h4>

                <select id="provinceId" name="provinceId" class="form-control m-b" >
                    <c:forEach items="${provinceSelect}" var="item">
                        <option value="${item.provinceid}"  <c:if test="${weixinUser.provinceId==item.provinceid}">selected="selected"</c:if>     >${item.provincename}</option>
                    </c:forEach>
                </select>

                <select id="cityId" name="cityId" class="form-control m-b" >

                </select>
            </div>



            <div class="widget lazur-bg p-xl" style="color:black;">
                <h4>
                    5.毕业后去向
                </h4>

                <select id="province_id_after_school" name="province_id_after_school" class="form-control m-b" >
                    <c:forEach items="${provinceSelect}" var="item">
                        <option value="${item.provinceid}"  <c:if test="${weixinUser.province_id_after_school==item.provinceid}">selected="selected"</c:if>     >${item.provincename}</option>
                    </c:forEach>
                </select>

                <select id="city_id_after_school" name="city_id_after_school" class="form-control m-b" >

                </select>
            </div>


            <div class="widget lazur-bg p-xl" style="color:black;">

                <h4>
                    6.在读／毕业高校
                </h4>

                <div class="form-group">
                    <select id="schoolPlaceSelect" class="form-control m-b" name="account">
                        <c:forEach items="${schoolPlaceSelect}" var="item">
                            <option value="${item}"          <c:if test="${schoolPlace==item}">selected="selected"</c:if>          >${item}</option>
                        </c:forEach>
                    </select>

                    <select id="schoolId" name="schoolId" class="form-control m-b" >

                    </select>
                </div>
            </div>


            <div class="widget lazur-bg p-xl" style="color:black;">

                <h4>
                    7.简介(个性、爱好、经历等)
                </h4>

                <div class="form-group">
                    <textarea id="introduction"  name="introduction" class="form-control m-b" style="height:120px;">${weixinUser.introduction}</textarea>
                </div>
            </div>

            <div class="widget lazur-bg p-xl" style="color:black;">

                <h4>
                    8.想对TA说的话
                </h4>

                <div class="form-group">
                    <textarea id="sayToHer" name="sayToHer" class="form-control m-b" style="height:120px;">${weixinUser.sayToHer}</textarea>
                </div>
            </div>

            <div class="widget lazur-bg p-xl" style="color:black;">
                <h4>
                    9.联系方式(上传微信二维码图片)
                </h4>
                <p><i class="glyphicon glyphicon-info-sign"></i>请您将微信二维码图片保存到手机，再进行上传。我们会将您的微信二维码展示给其他小伙伴，等待有缘的TA联系你</p>

                <div class="form-group">
                    <div class="input-group m-b"><span class="input-group-btn">
                        <button id="picker" type="button" class="btn btn-primary">点击上传</button> </span>
                        <input id="contactQrCodeShow"  value="${weixinUser.qrCodeShowName}"  type="text" class="form-control fileUploadName" readonly>
                        <input id="contactQrCode" value="${weixinUser.contactQrCode}" name="contactQrCode" type="hidden" class="form-control fileUploadName" readonly>
                    </div>
                </div>
            </div>



            <div class="widget lazur-bg p-xl" style="color:black;">
                <h4>
                    10.个人照
                </h4>
                <p><i class="glyphicon glyphicon-info-sign"></i>个人照必传，害羞的同学可以上传背影照。</p>

                <div class="form-group">
                    <div class="input-group m-b"><span class="input-group-btn">
                        <button id="photoPicker" type="button" class="btn btn-primary">点击上传</button> </span>
                        <input id="photoImgShow"  value="${weixinUser.photoImgShow}"  type="text" class="form-control fileUploadName" readonly>
                        <input id="photoImg" value="${weixinUser.photoImg}" name="photoImg" type="hidden" class="form-control fileUploadName" readonly>
                    </div>
                </div>
            </div>



            <div class="widget yellow-bg p-lg text-center">
                <div class="m-b-md">
                    <i class="fa fa-warning fa-4x"></i>
                    <h3 class="font-bold no-margins">
                        注意事项
                    </h3>
                    <p class="font-bold no-margins " style="text-align: justify;">
                        请确认您的信息都是真实的，提供虚假信息，造成不良后果的，须承担相应法律责任；本平台对信息的虚实无法进行担保，请予以理解，与匹配对象交流过程中，请自行注意安全，高危互动请验证对方身份。
                    </p>
                </div>
            </div>
        </form>
        <div>

            <div class="user-button">
                <div class="row">
                    <div class="col-sm-12">
                        <button id="submitButton" type="button"
                                class="btn btn-primary btn-sm btn-block"> 提交
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
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
<script src="${ctx}/resource/js/plugins/webuploader/webuploader.min.js"></script>
<script src="${ctx}/resource/auth/js/global.js"></script>
<script type="text/javascript">
    /*
     $('#birthday_input .input-group.date').datepicker({
     startView: 1,
     todayBtn: "linked",
     keyboardNavigation: false,
     forceParse: false,
     autoclose: true,
     format: "yyyy-mm-dd"
     });
     */



    $(function (){
        var cityIdInit="${weixinUser.cityId}";
        var schoolIdInit="${weixinUser.schoolId}";
        var city_id_after_schoolInit="${weixinUser.city_id_after_school}";



        var $provinceSelect=$('#provinceId');
        var $schoolPlaceSelect=$('#schoolPlaceSelect');


        var $cityId=$('#cityId');
        var $schoolId=$('#schoolId');

        var $nickName = $('#nickName');
        var $age = $('#age');
        var $sex = $('#sex');
        var $provinceId = $('#provinceId');
        var $introduction = $('#introduction');
        var $contactQrCodeShow = $('#contactQrCodeShow');
        var $contactQrCode = $('#contactQrCode');
        var $sayToHer = $('#sayToHer');



        var $province_id_after_school = $('#province_id_after_school');
        var $city_id_after_school=$('#city_id_after_school');

        var $submitButton = $('#submitButton');


        var $photoPicker = $('#photoPicker');
        var $photoImgShow = $('#photoImgShow');
        var $photoImg = $('#photoImg');


        var $form=$('#form');
        initContactQrCodeFileUpload();
        initPhotoFileUpload();
        initSelect();
        initSubmitBtn();


        function initSubmitBtn(){
            $submitButton.click(function(){
                if(!validateForm()){
                    return ;
                }

                $submitButton.attr({"disabled": "disabled"});
                auth_global.formSubmit($form,"/front/collectinfo/saveInfo",function(msg ,data){
                    if(isEdit) {
                        window.location.href="${ctx}/front/collectinfo/showInfo";
                    }else{
                        window.location.href="${ctx}/front/collectinfo/search_result";
                    }
                    $submitButton.removeAttr("disabled");
                },function(code ,msg ,data){
                    alert('错误：'+code+'  '+msg)
                    $submitButton.removeAttr("disabled");
                })

            });
        }


        function validateForm(){
            if(!$.trim($nickName.val())) {
                layer.msg('请输入昵称');
                return false;
            }

            if(!$age.val()){
                layer.msg('请输入年龄');
                return false;
            }

            if(!$.trim($introduction.val())){
                layer.msg('请填写自我介绍');
                return false;
            }
            if($.trim($introduction.val()).length<20){
                layer.msg('自我介绍不得少于20字');
                return false;
            }


            if(!$.trim($sayToHer.val())){
                layer.msg('请填写想对TA说的话');
                return false;
            }
            if($.trim($sayToHer.val()).length<20){
                layer.msg('对TA说的话不得少于20字');
                return false;
            }

            if(!$.trim($contactQrCode.val())){
                layer.msg('请上传微信二维码图片');
                return false;
            }
            if($contactQrCode.data('isUploadIng')){
                layer.msg('二维码正在上传中，请稍后再试');
                return false;
            }

            if(!$.trim($photoImg.val())){
                layer.msg('请上传形象照');
                return false;
            }
            if($photoImg.data('isUploadIng')){
                layer.msg('形象照正在上传中，请稍后再试');
                return false;
            }

            return true;
        }


        function initPhotoFileUpload(){
            var $picker = $photoPicker;
            var uploader = WebUploader.create({
                // 不压缩image
                resize: false,
                // swf文件路径
                // swf: BASE_URL + '/js/Uploader.swf',
                // 文件接收服务端。
                server: '${ctx}/front/fileUpload/uploadTest',

                // 选择文件的按钮。可选。
                // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                pick: $picker,
                // 只允许选择图片文件。
//                accept: {
//                    title: 'Images',
//                    extensions: 'gif,jpg,jpeg,bmp,png',
//                    mimeTypes: 'image/jpg,image/jpeg,image/png'
//                }
//                ,


                accept: {
                    title: 'Images',
                    //extensions: 'gif,jpg,jpeg,bmp,png',
                    mimeTypes: 'image/*'
                }
                ,


                auto: true
            });

            uploader.on('startUpload', function () {
                $photoImg.data('isUploadIng',true);
            });

            uploader.on('uploadSuccess', function (file, response) {
                var imgPath = response['_raw'];
                console.log(imgPath);
                $photoImgShow.val(imgPath.substring(imgPath.indexOf('lol_lol')+7,imgPath.length));
                $photoImg.val(imgPath);
                $photoImg.data('isUploadIng',false);
            });



            uploader.on('uploadError', function (file) {
                alert('上传出错');
                $photoImg.data('isUploadIng',false);
            });

            uploader.on('uploadComplete', function (file) {
                var files = uploader.getFiles();
                $.each(files, function (index, file) {
                    uploader.removeFile(file);
                });
            });
        }


        function initContactQrCodeFileUpload(){
            var picker_selector = '#picker';
            var $picker = $(picker_selector);
            var uploader = WebUploader.create({
                // 不压缩image
                resize: false,
                // swf文件路径
                // swf: BASE_URL + '/js/Uploader.swf',
                // 文件接收服务端。
                server: '${ctx}/front/fileUpload/uploadTest',

                // 选择文件的按钮。可选。
                // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                pick: picker_selector,
                // 只允许选择图片文件。
//                accept: {
//                    title: 'Images',
//                    extensions: 'gif,jpg,jpeg,bmp,png',
//                    mimeTypes: 'image/jpg,image/jpeg,image/png'
//                }
//                ,


                accept: {
                    title: 'Images',
                    //extensions: 'gif,jpg,jpeg,bmp,png',
                    mimeTypes: 'image/*'
                }
                ,

                auto: true
            });
            var $webuploader_pick = $picker.find('.webuploader-pick');

            uploader.on('startUpload', function () {
                $contactQrCode.data('isUploadIng',true);
            });

            uploader.on('uploadSuccess', function (file, response) {
                var imgPath = response['_raw'];
                console.log(imgPath);
                $contactQrCodeShow.val(imgPath.substring(imgPath.indexOf('lol_lol')+7,imgPath.length));
                $contactQrCode.val(imgPath);
                $contactQrCode.data('isUploadIng',false);
            });



            uploader.on('uploadError', function (file) {
                alert('上传出错');
                $contactQrCode.data('isUploadIng',false);
            });

            uploader.on('uploadComplete', function (file) {
                var files = uploader.getFiles();
                $.each(files, function (index, file) {
                    uploader.removeFile(file);
                });
            });
        }


        function initSelect(){
            changeCity($provinceSelect.val());
            $provinceSelect.change(function(){
                changeCity($(this).val());
            });

            changeSchool($schoolPlaceSelect.val());
            $schoolPlaceSelect.change(function(){
                changeSchool($(this).val());
            });


            changeCityAfterSchool($province_id_after_school.val());
            $province_id_after_school.change(function(){
                changeCityAfterSchool($(this).val());
            });
        }

        function changeCity(pId){
            auth_global.commonAjax('${ctx}/front/collectinfo/getCityByProvinceAjax', function (msg,data) {
                //console.log(data);
                var citySelectHtml = ''
                for(var i =0;i<data.length;i++) {
                    var selected='';
                    if(cityIdInit && data[i].cityid ==cityIdInit){
                        selected='    selected="selected"    ';
                    }
                    citySelectHtml+='<option'+selected+' value="'+data[i].cityid+'">'+data[i].cityname+'</option>';
                    $cityId.html(citySelectHtml);
                }
            }, function (code, msg, data) {
                swal("ERROR！", msg, "error");
            }, {'provinceId': pId},"GET");
        }

        function changeCityAfterSchool(pId){
            auth_global.commonAjax('${ctx}/front/collectinfo/getCityByProvinceAjax', function (msg,data) {
                //console.log(data);
                var citySelectHtml = ''
                for(var i =0;i<data.length;i++) {
                    var selected='';
                    if(cityIdInit && data[i].cityid ==city_id_after_schoolInit){
                        selected='    selected="selected"    ';
                    }
                    citySelectHtml+='<option'+selected+' value="'+data[i].cityid+'">'+data[i].cityname+'</option>';
                    $city_id_after_school.html(citySelectHtml);
                }
            }, function (code, msg, data) {
                swal("ERROR！", msg, "error");
            }, {'provinceId': pId},"GET");
        }



        function changeSchool(place){
            auth_global.commonAjax('${ctx}/front/collectinfo/getSchoolByPlace', function (msg,data) {
                //console.log(data);
                var citySelectHtml = ''
                for(var i =0;i<data.length;i++) {

                    var selected='';
                    if(cityIdInit && data[i].id ==schoolIdInit){
                        selected='    selected="selected"    ';
                    }
                    citySelectHtml+='<option '+selected+' value="'+data[i].id+'">'+data[i].name+'</option>';
                    $schoolId.html(citySelectHtml);
                }
            }, function (code, msg, data) {
                swal("ERROR！", msg, "error");
            }, {'place': place},"GET");
        }

    });
</script>


</body>
</html>
