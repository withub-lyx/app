<%@ page language="java"    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<style type="text/css">
    div{
        width:100%;
    }


    .edui-button.edui-for-135editor .edui-button-wrap .edui-button-body .edui-icon{
        background-image: url("http://static.135editor.com/img/icons/editor-135-icon.png") !important;
        background-size: 85%;
        background-position: center;
        background-repeat: no-repeat;
    }
</style>



<jsp:include page="../../../base/innerLayout/inner_top.jsp"/>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <%--查询表单--%>
            <form:form modelAttribute="vo" id="saveForm" cssClass="form-horizontal">
                <form:hidden path="id"/>
                <%--<form:hidden path="content"/>--%>
                <form:textarea path="content" cssStyle="display:none;"></form:textarea>

                <div class="form-group">
                    <label class="col-sm-2 control-label"><span style="color:Red;">*</span>标题：</label>
                    <div class="col-sm-10">
                        <form:input path="title" type="text" placeholder="请输入标题" class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label"><span style="color:Red;">*</span>分享后跳转地址：</label>
                    <div class="col-sm-10">
                        <form:input path="jumpUrl" type="text" placeholder="请输入分享后跳转地址" class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label"><span style="color:Red;">*</span>前台是否展示：</label>
                    <div class="col-sm-10">
                        <form:select path="showFront" items="${isOkSelect}" itemLabel="desc" itemValue="code" cssClass="form-control" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label"><span style="color:Red;">*</span>是否可用：</label>
                    <div class="col-sm-10">
                        <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
                    </div>
                </div>




                <div class="form-group">
                    <div class="col-sm-offset-2">
                        <button id="saveSubmitBtn" class="btn btn-sm btn-primary" type="button">保存</button>
                    </div>
                </div>

            </form:form>
        </div>
    </div>


<script src="${ctx}/resource/auth/js/global.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/resource/utf8-jsp/ueditor.config.js?xxx=dfdf"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/resource/utf8-jsp/ueditor.all.js?1=asdfasdfadsfasdfa"> </script>
<script type="text/javascript" charset="utf-8" src="${ctx}/resource/utf8-jsp/lang/zh-cn/zh-cn.js"></script>



<script type="text/javascript">
    var $saveFrom = $('#saveForm');
    var $contentInput = $('#content');
    var $id_input = $('#id');
    var $saveSubmitBtn = $('#saveSubmitBtn');
    var frameIndex = parent.layer.getFrameIndex(window.name); //获取窗口索引
    $(function () {


        //实例化编辑器
        //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
        var ue = UE.getEditor('editor');

        ue.addListener( 'ready', function( editor ) {
            if(${isEdit}) {
                UE.getEditor('editor').setContent($contentInput.val(), false);
            }

        } );






        function initForm() {
            $saveFrom.validate({
                rules: {
                    title: {
                        required: true,
                    },
                    jumpUrl: {
                        required: true
                    },
                    content: {
                        required: true
                    }
                },
                messages: {
                    title: {
                        required: auth_global.validateErrorIcon + "必填",
                    },
                    jumpUrl: {
                        required: auth_global.validateErrorIcon + "必填"
                    },
                    content: {
                        required: auth_global.validateErrorIcon + "必填"
                    }
                }
            });


            $saveSubmitBtn.click(function () {
                $contentInput.val(UE.getEditor('editor').getContent());

                if (!$saveFrom.valid()) {
                    return;
                }


                var url = '';
                if($id_input.val()){
                    url = '${ctx}/backend/article/editAjax';
                }else{
                    url = '${ctx}/backend/article/addAjax';
                }

                auth_global.formSubmit($saveFrom,url,function(msg, data){
                    parent.$table.bootstrapTable('selectPage', 1);
                    parent.layer.close(frameIndex);
                },function(code,msg ,data){
                    auth_global.commonToast.toastError(msg);
                });
            });
        }
        initForm();
    });
</script>

<jsp:include page="../../../base/innerLayout/inner_bottom.jsp"/>