<%@ page language="java"    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<jsp:include page="../../../base/innerLayout/inner_top.jsp"/>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <%--查询表单--%>
            <form:form modelAttribute="vo" id="saveForm" cssClass="form-horizontal">
                <form:hidden path="id"/>

                <div class="form-group">
                    <label class="col-sm-4 control-label"><span style="color:Red;">*</span>应用：</label>
                    <div class="col-sm-8">
                        <form:select path="appid" items="${appSelectList}" itemLabel="appname" itemValue="appid" cssClass="form-control" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-4 control-label"><span style="color:Red;">*</span>IP白名单：</label>
                    <div class="col-sm-8">
                        <form:input path="ips" type="text" placeholder="请输入IP白名单" class="form-control"/>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-sm-4 control-label"><span style="color:Red;">*</span>秘钥：</label>
                    <div class="col-sm-8">
                        <form:input path="securitykey" type="text" placeholder="请输入秘钥" class="form-control"/>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-sm-4 control-label"><span style="color:Red;">*</span>回调URL：</label>
                    <div class="col-sm-8">
                        <form:input path="appcallback" type="text" placeholder="请输入回调URL" class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-4 control-label"><span style="color:Red;">*</span>是否可用：</label>
                    <div class="col-sm-8">
                        <form:select path="status" items="${appApiStatusSelectList}" itemLabel="desc" itemValue="code" cssClass="form-control" />
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                        <button id="saveSubmitBtn" class="btn btn-sm btn-primary" type="button">保存</button>
                    </div>
                </div>





            </form:form>
        </div>
    </div>

</div>

<script src="${ctx}/resource/auth/js/global.js"></script>

<script type="text/javascript">
    var $saveFrom = $('#saveForm');
    var $id_input = $('#id');
    var $saveSubmitBtn = $('#saveSubmitBtn');
    var frameIndex = parent.layer.getFrameIndex(window.name); //获取窗口索引
    $(function () {
        function initForm() {
            $saveFrom.validate({
                rules: {
                    appid: {
                        required: true,
                    },
                    ips: {
                        required: true
                    },
                    securitykey: {
                        required: true
                    },
                    appcallback: {
                        required: true,
                    },
                    status: {
                        required: true,
                    }
                },
                messages: {
                    appid: {
                        required: auth_global.validateErrorIcon + "必填",
                    },
                    ips: {
                        required: auth_global.validateErrorIcon + "必填"
                    },
                    securitykey: {
                        required: auth_global.validateErrorIcon + "必填"
                    },
                    appcallback: {
                        required: auth_global.validateErrorIcon + "必填"
                    },
                    status: {
                        required: auth_global.validateErrorIcon + "必填"
                    }
                }
            });


            $saveSubmitBtn.click(function () {
                if (!$saveFrom.valid()) {
                    return;
                }


                var url = '';
                if($id_input.val()){
                    url = '${ctx}/appApi/editAjax';
                }else{
                    url = '${ctx}/appApi/addAjax';
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