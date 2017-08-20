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
                    <label class="col-sm-4 control-label"><span style="color:Red;">*</span>组名称：</label>
                    <div class="col-sm-8">
                        <form:input path="name" type="text" placeholder="请输入组名称" class="form-control"/>
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
                    name: {
                        required: true,
                    }
                },
                messages: {
                    name: {
                        required: auth_global.validateErrorIcon + "必填",
                    }
                }
            });


            $saveSubmitBtn.click(function () {
                if (!$saveFrom.valid()) {
                    return;
                }


                var url = '';
                if($id_input.val()){
                    url = '${ctx}/team/editAjax';
                }else{
                    url = '${ctx}/team/addAjax';
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