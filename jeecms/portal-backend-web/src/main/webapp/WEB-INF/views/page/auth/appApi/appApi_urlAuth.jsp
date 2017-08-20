<%@ page language="java"    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"  %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<jsp:include page="../../../base/innerLayout/inner_top.jsp"/>
<!-- 自定义js -->
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <!-- Panel Other -->
            <div class="ibox float-e-margins border-bottom">
                <div class="ibox-title">
                    <h5>表格</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-down"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row row-lg">
                        <div class="col-sm-12">
                            <!-- Example Events -->
                            <div>

                                <form id="addUrlForm" class="form-horizontal">
                                    <input type="hidden" name="appid" value="${appId}">
                                    <div class="form-group">
                                        <label class="col-sm-3 control-label">URL：</label>
                                        <div class="col-sm-6">
                                            <input name="url"  type="text" placeholder="请输入URL" class="form-control" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-3 col-sm-6">
                                            <button  id="addUrlBtn" class="btn btn-primary" type="button">添加URL</button>
                                        </div>
                                    </div>
                                </form>
                                <table id="listTable" class="table table-hover" data-striped="true"></table>
                            </div>
                            <!-- End Example Events -->
                        </div>

                    </div>
                </div>
            </div>
            <!-- End Panel Other -->
        </div>
    </div>
</div>

<script src="${ctx}/resource/auth/js/global.js"></script>
<script type="text/javascript">
    var appId= '${appId}';
    var $table = $('#listTable');
    var $addUrlForm = $("#addUrlForm");
    var $addUrlBtn = $("#addUrlBtn");
    $(function(){
        function init(){
            auth_global.tableUtil.initTable($table,{
                url: '${ctx}/appApi/urlAuthpageJson',
                queryParams: function () {
                    return {
                        appid:appId
                    };
                },
                columns: [
                    {
                        field: 'id',
                        title: 'id',
                        visible: false,//不可见
                    },
                    {
                        field: 'appid',
                        title: 'appid',
                        visible: false,//不可见
                    },
                    {
                        field: 'url',
                        title: 'URL',
                        align: 'center'
                    }
                    , {//操作
                        field: 'operate',
                        title: '操作',
                        align: 'center',
                        //events: operateEvents,
                        formatter: function (value, row, index) {
                            var array=[];
                        ''
                        <%--<shiro:hasPermission name="auth_user:8">--%>
                                                    array.push('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="remove" href="javascript:void(0)" title="删除"><i class="glyphicon glyphicon-remove"></i></a>');
                        <%--</shiro:hasPermission>--%>

                            return array.join('');
                        },
                        events: {
                            'click .remove': function (e, value, row, index) {
                                deleteMethod([row.id]);
                            }
                        }
                    }]
            });



            $addUrlForm.validate({
                rules: {
                    "url": {
                        required: true
                    }
                },
                messages: {
                    "url": {
                        required: auth_global.validateErrorIcon + "必填"
                    }
                }
            });

            $addUrlBtn.click(function(){
                if (!$addUrlForm.valid()) {
                    return;
                }
                auth_global.formSubmit($addUrlForm,'${ctx}/appApi/saveApiUrlAjax',function(msg,data){
                    auth_global.tableUtil.refresh($table);
                },function(code,msg ,data){
                    auth_global.commonToast.toastError(msg);
                });
            });
        };
        function deleteMethod(ids) {
            auth_global.commonAjax('${ctx}/appApi/deleteUrlAjax', function (data, msg) {
                auth_global.tableUtil.refresh($table);
                swal("成功！", "删除成功。", "success");
            }, function (code, msg, data) {
                swal("失败！", msg, "error");
            }, {'ids': ids});
        }
        init();
    });

</script>
<jsp:include page="../../../base/innerLayout/inner_bottom.jsp"/>