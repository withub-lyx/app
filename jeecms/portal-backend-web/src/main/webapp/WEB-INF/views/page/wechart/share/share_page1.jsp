<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <style type="text/css">



        html, body {
            margin: auto;
            width: 94%;
            height: 100%;
            text-align: center;
        }

        iframe
        {
            margin: 0px 0px;
            width: 100%;
            height: 100%;
        }
    </style>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
    <title>${artict.title}</title>
    <script src="${ctx}/resource/js/jquery.min.js?v=2.1.4"></script>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

    <style type="text/css">
        img{
            max-width:100%;
        }
    </style>


</head>
<body>

${artict.content}
<script>

    $(function(){

        var commonAjax = function (url, successCallback, errorCallback, data, method) {
            if (!method) {
                method = "POST";
            }
            $.ajax({
                "url": url,
                "method": method,
                "data": data,
                //contentType:"application/json",
                "dataType": "JSON",
                success: function (data) {
                    if (data.code == 0) {
                        if (successCallback && typeof successCallback == 'function') {
                            successCallback(data.msg,data.data);
                        }
                    } else {
                        if (errorCallback && typeof errorCallback == 'function') {
                            errorCallback(data.code,data.msg, data.data);
                        }
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    if (errorCallback && typeof errorCallback == 'function') {
                        errorCallback(null,textStatus+errorThrown);
                    }
                }
            });
        };


        commonAjax("${ctx}/front/share/getWechatParam",function(msg ,data){
            wx.config({
                debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: data.appId, // 必填，公众号的唯一标识
                timestamp: data.timestamp, // 必填，生成签名的时间戳
                nonceStr: data.nonceStr, // 必填，生成签名的随机串
                signature:data.signature,// 必填，签名，见附录1
                jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
            });


            wx.ready(function(){


                var shareIcon = $('img:eq(0)').attr('src');
                if(shareIcon && shareIcon.indexOf("http://")==-1 && shareIcon.indexOf("https://")==-1){
                    shareIcon = window.location.protocol+"//"+window.location.hostname+ shareIcon;
                }
                //alert(shareIcon);




                wx.onMenuShareTimeline({
                    title: '${artict.title}', // 分享标题
                    link: window.location.href, // 分享链接
                    imgUrl: shareIcon, // 分享图标
                    success: function () {
                        new Image().src="${ctx}/front/log/sharePengYouQuan";
                        window.location.href='${artict.jumpUrl}';
                    },
                    cancel: function () {
                    }
                });





                wx.onMenuShareAppMessage({
                    title: '${artict.title}', // 分享标题
                    desc: '${artict.title}', // 分享描述
                    link: window.location.href, // 分享链接
                    imgUrl: shareIcon, // 分享图标
                    //type: '', // 分享类型,music、video或link，不填默认为link
                    //dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
                    success: function () {
                        new Image().src="${ctx}/front/log/sharePengYou";
                        window.location.href='${artict.jumpUrl}';
                    },
                    cancel: function () {
                        // 用户取消分享后执行的回调函数
                    }
                });

                //$('title').html('-'+$('title').html());
            });

            wx.error(function(res){

                alert('[error1:]' + JSON.stringify(res));
            });
        },function(code ,msg ,data){
            alert("[error2:]"+msg);
        },{
            url:window.location.href
        } );
    });

</script>
</body>
</html>
