<%@ page language="java"    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"  %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<jsp:include page="../../../base/innerLayout/inner_top.jsp"/>
<!-- 自定义js -->
<div class="wrapper wrapper-content animated fadeInRight">
<%--    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>搜索条件查询</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content" style="display:none;">

                </div>
            </div>
        </div>
    </div>--%>
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
                                <%--查询表单--%>
                                <form id="queryForm" class="form-inline">
                                    <div class="form-group">
                                        <label class="control-label sr-only">登录名：</label>
                                        <input name="loginname" type="text" placeholder="请输入登录名" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label sr-only">姓名：</label>
                                        <input name="name" type="text" placeholder="请输入姓名" class="form-control">
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label sr-only">手机：</label>
                                        <input name="mobilephone" type="text" placeholder="请输入手机" class="form-control">
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label sr-only">邮箱：</label>
                                        <input name="email" type="text" placeholder="请输入邮箱" class="form-control">
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label sr-only">是否可用：</label>
                                        <select name="isok" class="form-control">
                                            <option value="">--是否可用--</option>
                                            <option value="1">可用</option>
                                            <option value="0">不可用</option>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <button id="queryBtn" class="btn btn-primary " type="button" style="margin-top: 5px;">查询</button>
                                    </div>
                                </form>
                                <%--工具栏--%>
                                <div id="tableToolbar" class="btn-group hidden-xs" role="group">

                                    <shiro:hasPermission name="auth_user:2">
                                        <button id="addBtn" type="button" class="btn btn-outline btn-default">
                                            <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                                        </button>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="auth_user:8">
                                        <button id="removeBtn" type="button" class="btn btn-outline btn-default" disabled>
                                            <i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
                                        </button>
                                    </shiro:hasPermission>


                                </div>
                                <table id="userTable" class="table table-hover" data-striped="true"></table>
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

<script src="${ctx}/resource/auth/js/global.js?2=2"></script>
<script type="text/javascript">
    var $table = $('#userTable');
    var $addBtn = $('#addBtn');
    var $removeBtn = $('#removeBtn');
    var $queryForm = $("#queryForm");
    var $queryBtn = $('#queryBtn');
    $(function(){
        function init(){
            auth_global.tableUtil.initTable($table,{
                url: '${ctx}/user/pageJson',
                toolbar: '#tableToolbar',//外部工具条,
                queryParams: function (params) {
                    return $.extend(params, $queryBtn.data('queryParam'));
                },
                columns: [
                    {
                        field: 'id',
                        title: 'id',
                        visible: false,//不可见
                    },
                    {
                        field: 'state',//checkbox
                        checkbox: true,
                        align: 'center',
                        valign: 'middle'
                    },
                    {
                        field: 'loginname',
                        title: '登录名',
                        align: 'center'
                    }
                    ,
                    {
                        field: 'name',
                        title: '姓名',
                        align: 'center'
                    }
                    ,
                    {
                        field: 'mobilephone',
                        title: '手机',
                        align: 'center'
                    }
                    ,
                    {
                        field: 'email',
                        title: '邮箱',
                        align: 'center'
                    }
                    , {
                        field: 'isok',
                        title: '是否可用',
                        align: 'center',
                        formatter:function(value ,row ,index ){
                            //true
                            if(value==1){
                                return '<span class="label label-primary">可用</span>';

                            }else if(value ==0){//false
                                return '<span class="label label-danger">不可用</span>';
                            }else{
                                return '<span class="label label-warning">未知</span>';
                            }
                        }
                    }, {//操作
                        field: 'operate',
                        title: '操作',
                        align: 'center',
                        //events: operateEvents,
                        formatter: function (value, row, index) {
                            var array=[];
                        <shiro:hasPermission name="auth_user:4">
                                                        array.push('<a class="update" href="javascript:void(0)" title="更新"><i class="glyphicon glyphicon-pencil"></i></a>');
                        </shiro:hasPermission>
                        ''
                        <shiro:hasPermission name="auth_user:8">
                                                    array.push('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="remove" href="javascript:void(0)" title="删除"><i class="glyphicon glyphicon-remove"></i></a>');
                        </shiro:hasPermission>

                        <shiro:hasPermission name="auth_user:16">
                                                    array.push('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="roleConfig" href="javascript:void(0)" title="角色"><i class="glyphicon glyphicon-user"></i></a>')
                        </shiro:hasPermission>







                            return array.join('');
                        },
                        events: {
                            'click .update': function (e, value, row, index) {
                                saveMethod(row.id);
                            },
                            'click .remove': function (e, value, row, index) {
                                deleteMethod([row.id]);
                            },
                            'click .roleConfig': function (e, value, row, index) {
                                configRoles(row.id);
                            }
                        }
                    }]
            });
            $table.on('check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table  refresh.bs.table post-body.bs.table ', function () {
                $removeBtn.prop('disabled', !auth_global.tableUtil.getSelections($table).length);
            });

            $removeBtn.click(function () {
                var ids = auth_global.tableUtil.getSelections($table);
                deleteMethod(ids);
            });
            $addBtn.click(function () {
                saveMethod(null);
            });

            $queryBtn.click(function () {
                if (!$queryForm.valid()) {
                    return;
                }
                var queryParam = {};
                var $inputs = $queryForm.find('input,select');
                $inputs.each(function () {
                    queryParam[$(this).attr('name')] = $(this).val();
                });
                $queryBtn.data('queryParam', queryParam);
                auth_global.tableUtil.selectPage($table,1);
            });
        };
        function deleteMethod(ids) {
            swal({
                title: "您确定要删除这条信息吗",
                text: "删除后将无法恢复，请谨慎操作！",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                cancelButtonText: "取消",
                confirmButtonText: "删除",
                closeOnConfirm: false
            }, function () {
                auth_global.commonAjax('${ctx}/user/deleteAjax', function (data, msg) {
                    auth_global.tableUtil.selectPage($table,1);
                    swal("成功！", "删除成功。", "success");
                }, function (code, msg, data) {
                    swal("失败！", msg, "error");
                }, {'ids': ids});
            });
        }

        function saveMethod(id) {
            var url = '${ctx}/user';
            var title = '';
            if (id) {
                url += '/editPage?id=' + id;
                title='修改';
            }else{
                url += '/addPage';
                title='添加';
            }

            auth_global.commonLayer({
                title:title,
                content:url
            });
        }


        function configRoles(id) {
            var url = '${ctx}/user/configRolePage?id='+id;
            var title = '角色设置';

            auth_global.commonLayer({
                title:title,
                content:url
            });
        }
        init();
    });

</script>
<jsp:include page="../../../base/innerLayout/inner_bottom.jsp"/>