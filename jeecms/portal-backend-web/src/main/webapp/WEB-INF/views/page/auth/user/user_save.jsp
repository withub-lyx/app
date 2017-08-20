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
                    <label class="col-sm-4 control-label"><span style="color:Red;">*</span>用户名：</label>
                    <div class="col-sm-8">
                        <form:input path="name" type="text" placeholder="请输入用户名" class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-4 control-label"><span style="color:Red;">*</span>登录名：</label>
                    <div class="col-sm-8">
                        <form:input path="loginname" type="text" placeholder="请输入登录名" class="form-control"/>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-sm-4 control-label"><span style="color:Red;">*</span>是否可用：</label>
                    <div class="col-sm-8">
                        <form:select path="isok" items="${isOkSelect}" itemLabel="desc" itemValue="code" cssClass="form-control" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-4 control-label"><span style="color:Red;">*</span>登录密码：</label>
                    <div class="col-sm-8">
                        <form:input path="pwd" type="password" placeholder="请输入密码" class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-4 control-label"><span style="color:Red;">*</span>确认登录密码：</label>
                    <div class="col-sm-8">
                        <input id="pwd_confirm" name="pwd_confirm" placeholder="请确认密码" type="password" class="form-control" value="${vo.pwd}">
                    </div>
                </div>




                <div class="form-group">
                    <label class="col-sm-4 control-label">性别：</label>
                    <div class="col-sm-8">
                        <form:select path="sex" items="${sexSelect}" itemLabel="desc" itemValue="code" cssClass="form-control" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-4 control-label">电话：</label>
                    <div class="col-sm-8">
                        <form:input path="tel" type="text" placeholder="请输入电话" class="form-control"/>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-sm-4 control-label">手机：</label>
                    <div class="col-sm-8">
                        <form:input path="mobilephone" type="text" placeholder="请输入手机" class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-4 control-label">出生日期：</label>
                    <div class="col-sm-8">
                        <form:input   path="birthday" type="text" placeholder="请输入出生日期" class="form-control layer-date" readonly="true"  />
                        <label class="laydate-icon inline demoicon" onclick="laydate({elem: '#birthday'});"></label>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-sm-4 control-label">邮箱：</label>
                    <div class="col-sm-8">
                        <form:input path="email" type="text" placeholder="请输入邮箱" class="form-control"/>
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
                    },
                    loginname: {
                        required: true
                    },
                    isok: {
                        required: true
                    },
                    pwd: {
                        required: true,
                        minlength: 5
                    },
                    pwd_confirm: {
                        required: true,
                        equalTo: "#pwd"
                    },
                    sex: {
                        required: true
                    },
                    tel: {
                    },
                    mobilephone: {
                    },
                    birthday:{
                    },
                    email:{
                        email: true
                    }
                },
                messages: {
                    name: {
                        required: auth_global.validateErrorIcon + "必填",
                    },
                    loginname: {
                        required: auth_global.validateErrorIcon + "必填"
                    },
                    isok: {
                        required: auth_global.validateErrorIcon + "必填"
                    },
                    pwd: {
                        required: auth_global.validateErrorIcon + "必填",
                        minlength: auth_global.validateErrorIcon +"最小长度为5"
                    },
                    pwd_confirm: {
                        required: auth_global.validateErrorIcon + "必填",
                        equalTo: auth_global.validateErrorIcon +"两次密码不一至"
                    },
                    sex: {
                        required: auth_global.validateErrorIcon + "必填"
                    },
                    tel: {
                    },
                    mobilephone: {
                    },
                    birthday:{
                    },
                    email:{
                        email: auth_global.validateErrorIcon + "请填写正确的邮箱"
                    }
                }
            });


            $saveSubmitBtn.click(function () {
                if (!$saveFrom.valid()) {
                    return;
                }


                var url = '';
                if($id_input.val()){
                    url = '${ctx}/user/editAjax';
                }else{
                    url = '${ctx}/user/addAjax';
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