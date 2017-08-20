<%@ page language="java"    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<jsp:include page="../../../base/innerLayout/inner_top.jsp"/>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <%--查询表单--%>
            <form:form modelAttribute="vo" id="addForm" cssClass="form-horizontal">
                <form:hidden path="id"/>
                <div class="form-group">
                    <label class="col-sm-4 control-label">应用：</label>
                    <div class="col-sm-8">
                        <form:select path="appid" items="${apps}" itemLabel="appname" itemValue="appid"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">授权目录：</label>
                    <div class="col-sm-8">
                        <form:input path="host" type="text" placeholder="请输入授权目录" class="form-control"/>
                            <span class="help-block m-b-none">
                                <i class="fa fa-info-circle"></i><br>
                                例如
                                &nbsp;http://www.111.com.cn<br>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; http://www.111.com.cn:8080<br>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; http://www.111.com.cn/path1<br>
                            </span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label">登出地址：</label>
                    <div class="col-sm-8">
                        <form:input path="logouturl" type="text" placeholder="请输入登出地址" class="form-control"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                        <button id="addSubmitBtn" class="btn btn-sm btn-primary" type="button">添加</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <table id="alreadyAuthTable" class="table table-hover" data-striped="true"></table>
        </div>
    </div>
</div>

<script src="${ctx}/resource/auth/js/global.js"></script>
<script type="text/javascript">
    var $addFrom = $('#addForm');
    var $addSubmitBtn = $('#addSubmitBtn');
    var $alreadyAuthTable = $('#alreadyAuthTable');
    var $appidInput = $('#appid');
    var frameIndex = parent.layer.getFrameIndex(window.name); //获取窗口索引
    $(function () {
        function initForm() {
            $.validator.addMethod("startWithHttpOrHttps", function (value, element) {
                return this.optional(element) || value.startsWith('http://') || value.startsWith('https://');
            }, "请以http://或者https://打头");
            $addFrom.validate({
                rules: {
                    appid: {
                        required: true,
                    },
                    host: {
                        required: true,
                        startWithHttpOrHttps: true
                    },
                    logouturl: {
                        required: true,
                        startWithHttpOrHttps: true
                    }
                },
                messages: {
                    appid: {
                        required: auth_global.validateErrorIcon + "请选择授权应用"
                    },
                    host: {
                        required: auth_global.validateErrorIcon + "授权目录不得为空"
                    },
                    logouturl: {
                        required: auth_global.validateErrorIcon + "请输入登出地址"
                    }
                }
            });


            $addSubmitBtn.click(function () {
                if (!$addFrom.valid()) {
                    return;
                }
                auth_global.formSubmit($addFrom,'${ctx}/appsso/save',function(msg,data){
                    parent.$table.bootstrapTable('selectPage', 1);
                    parent.layer.close(frameIndex);
                },function(code,msg ,data){

                    auth_global.commonToast.toastError(msg);
                });
            });
        }

        initForm();
    });

    $appidInput.change(function(){
        auth_global.tableUtil.refresh($alreadyAuthTable);
    });

    $alreadyAuthTable.bootstrapTable({
        columns: [{
            field: 'host',
            title: '已授权目录'
        }],
        url:'${ctx}/appsso/alreadyAuth',
        method: 'get',//获取数据方式 get/post
        mobileResponsive: true,
        //height: "400",//固定高度
        pageSize: "ALL",//每页大小
        showFooter: false,//页脚是否显示
        showRefresh: true,//显示刷新按钮
        showToggle: true,//切换试图按钮
        striped: true,//行变色
        queryParams: function (params) {
            return $.extend(params, {'appId': $appidInput.val()});
        }
    });
</script>

<jsp:include page="../../../base/innerLayout/inner_bottom.jsp"/>