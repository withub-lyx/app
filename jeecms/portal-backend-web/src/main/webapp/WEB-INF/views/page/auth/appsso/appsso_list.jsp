<%@ page language="java"    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<jsp:include page="../../../base/innerLayout/inner_top.jsp"/>
<!-- 自定义js -->
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
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
                    <%--查询表单--%>
                    <form id="queryForm" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">应用ID：</label>
                            <div class="col-sm-3">
                                <input name="appid" type="number" placeholder="appid" class="form-control">
                                <span class="help-block m-b-none">请输入应用ID</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-1 control-label">授权目录：</label>
                            <div class="col-sm-3">
                                <input name="host" type="text" placeholder="HOST" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-1 col-sm-8">
                                <button id="queryBtn" class="btn btn-sm btn-white" type="button">查询</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
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
                                <%--工具栏--%>
                                <div id="tableToolbar" class="btn-group hidden-xs" role="group">
                                    <button id="addBtn" type="button" class="btn btn-outline btn-default">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                                    </button>
                                    <button id="removeBtn" type="button" class="btn btn-outline btn-default" disabled>
                                        <i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
                                    </button>
                                </div>
                                <table id="table2" class="table table-hover" data-striped="true"></table>
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
    var $table = $('#table2');
    var $addBtn = $('#addBtn');
    var $removeBtn = $('#removeBtn');
    var $queryForm = $("#queryForm");
    var $queryBtn = $('#queryBtn');
    $(function(){
        function init(){
            auth_global.tableUtil.initTable($table,{
                url: '${ctx}/appsso/pageJson',
                toolbar: '#tableToolbar',//外部工具条,
                queryParams: function (params) {
                    return $.extend(params, $queryBtn.data('queryParam'));
                },
                columns: [{
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
                        field: 'appName',
                        title: '应用'
                        //sortable:true,
                    }

                    , {
                        field: 'appType',
                        title: '应用类型'
                    }, {
                        field: 'host',
                        title: '授权目录'
                    }, {
                        field: 'logouturl',
                        title: '登出地址'
                    }, {
                        field: 'keyvalue',
                        title: 'TOKEN加密的密钥'
                    }, {//操作
                        field: 'operate',
                        title: '操作',
                        align: 'center',
                        //events: operateEvents,
                        formatter: function (value, row, index) {
                            return [
                                '<a class="update" href="javascript:void(0)" title="更新">',
                                '<i class="glyphicon glyphicon-pencil"></i>',
                                '</a>' +
                                "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                                '<a class="remove" href="javascript:void(0)" title="删除">',
                                '<i class="glyphicon glyphicon-remove"></i>',
                                '</a>'
                            ].join('');
                        },
                        events: {
                            'click .update': function (e, value, row, index) {
                                saveMethod(row.id);
                            },
                            'click .remove': function (e, value, row, index) {
                                deleteMethod([row.id]);
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
                var $inputs = $queryForm.find('input');
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
                auth_global.commonAjax('${ctx}/appsso/delete', function (msg,data ) {
                    auth_global.tableUtil.selectPage($table,1);
                    swal("成功！", "删除成功。", "success");
                }, function (code, msg, data) {
                    swal("失败！", msg, "error");
                }, {'ids': ids});
            });
        }

        function saveMethod(id) {
            var url = '${ctx}/appsso/savePage';
            var title = '';
            if (id) {
                url += '?id=' + id;
                title='修改';
            }else{
                title='添加';
            }

            auth_global.commonLayer({
                title:title,
                content:url
            });
        }
        init();
    });

</script>
<jsp:include page="../../../base/innerLayout/inner_bottom.jsp"/>