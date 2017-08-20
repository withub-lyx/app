<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script src="${ctx}/resource/js/jquery.min.js?v=2.1.4"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/resource/css/plugins/webuploader/webuploader.css">
    <script src="${ctx}/resource/js/plugins/webuploader/webuploader.min.js"></script>
</head>
<body>

<div id="uploader">
    <!--用来存放文件信息-->
    <div id="thelist"></div>
    <div class="btns">
        <div id="picker">选择文件</div>
        <button id="ctlBtn" class="btn btn-default">开始上传</button>
    </div>
</div>


<script type="text/javascript">
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
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/jpg,image/jpeg,image/png'
        }
        , auto: true
    });
    var $webuploader_pick = $picker.find('.webuploader-pick');

    uploader.on('uploadSuccess', function (file, response) {
        $webuploader_pick.text(file.name + JSON.stringify(response));
    });

    uploader.on('uploadError', function (file) {
        alert('上传出错');
    });

    uploader.on('uploadComplete', function (file) {
        var files = uploader.getFiles();
        $.each(files, function (index, file) {
            uploader.removeFile(file);
        });
    });

</script>
</body>
</html>
