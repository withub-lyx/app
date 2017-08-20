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
                                        <label class="control-label sr-only">标题：</label>
                                        <input name="title" type="text" placeholder="请输入标题" class="form-control">
                                    </div>


                                    <div class="form-group">
                                        <button id="queryBtn" class="btn btn-primary " type="button" style="margin-top: 5px;">查询</button>
                                    </div>
                                </form>
                                <%--工具栏--%>
                                <div id="tableToolbar" class="btn-group hidden-xs" role="group">

                                        <button id="addBtn" type="button" class="btn btn-outline btn-default">
                                            <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                                        </button>
                                        <button id="removeBtn" type="button" class="btn btn-outline btn-default" disabled>
                                            <i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
                                        </button>
                                    <button id="layerCloseBtn" type="button" class="btn btn-outline btn-default" >
                                        关闭弹窗
                                    </button>

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

<script src="${ctx}/resource/auth/js/global.js"></script>
<script type="text/javascript">
    var $table = $('#userTable');
    var $addBtn = $('#addBtn');
    var $removeBtn = $('#removeBtn');
    var $layerCloseBtn = $('#layerCloseBtn');
    var $queryForm = $("#queryForm");
    var $queryBtn = $('#queryBtn');
    $(function(){
        function init(){
            auth_global.tableUtil.initTable($table,{
                url: '${ctx}/backend/article/pageJson',
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
                        field: 'title',
                        title: '标题',
                        align: 'center'
                    }
                    ,
                    {
                        field: 'creator',
                        title: '创建人',
                        align: 'center'
                    }
                    ,
                    {
                        field: 'createDateStr',
                        title: '创建时间',
                        align: 'center'
                    }
                    ,
                    {
                        field: 'updateDateStr',
                        title: '修改时间',
                        align: 'center'
                    }
                    , {
                        field: 'showFront',
                        title: '是否可用',
                        align: 'center',
                        formatter:function(value ,row ,index ){
                            //true
                            if(value==1){
                                return '<span class="label label-primary">前台显示</span>';

                            }else if(value ==0){//false
                                return '<span class="label label-danger">前台不显示</span>';
                            }else{
                                return '<span class="label label-warning">未知</span>';
                            }
                        }
                    }
                    ,
                    {
                        field: 'jumpUrl',
                        title: '跳转后的页面',
                        align: 'center'
                    },{//操作
                        field: 'operate',
                        title: '操作',
                        align: 'center',
                        //events: operateEvents,
                        formatter: function (value, row, index) {
                            var array=[];
                                                        array.push('<a class="update" href="javascript:void(0)" title="更新"><i class="glyphicon glyphicon-pencil"></i></a>');
                                                    array.push('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="remove" href="javascript:void(0)" title="删除"><i class="glyphicon glyphicon-remove"></i></a>');
                                                    array.push('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="showqrcode" href="javascript:void(0)"><i class="glyphicon glyphicon-qrcode"></i></a>');

                            return array.join('');
                        },
                        events: {
                            'click .update': function (e, value, row, index) {
                                saveMethod(row.id);
                            },
                            'click .remove': function (e, value, row, index) {
                                deleteMethod([row.id]);
                            },
                            'click .showqrcode': function (e, value, row, index) {
                                var url = location.protocol + "//" + location.host + "${ctx}" + "/front/share/" + row.id;

                                var linkBtn='<a href="'+url+'"  target="_blank">点击打开</a><br>';

                                var imgUrl = location.protocol + "//" + location.host + "${ctx}/front/qrcode/get?content=" + encodeURIComponent(url);
                                layer.tips('<div style="word-break:break-all;">'+linkBtn+url+'<img  style="width: 100px;height: 100px;" src="'+imgUrl+'"/>'+'</div>', this, {
                                    tips: [1, '#3595CC'],
                                    time: 20000
                                });
                            }
                        }
                    }
                   ]
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
            $layerCloseBtn.click(function(){
                layer.closeAll();
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
                auth_global.commonAjax('${ctx}/backend/article/deleteAjax', function (data, msg) {
                    auth_global.tableUtil.selectPage($table,1);
                    swal("成功！", "删除成功。", "success");
                }, function (code, msg, data) {
                    swal("失败！", msg, "error");
                }, {'ids': ids});
            });
        }

        function saveMethod(id) {
            var url = '${ctx}/backend/article';
            var title = '';
            if (id) {
                url += '/editPage?id=' + id;
                title='修改';
            }else{
                url += '/addPage';
                title='添加';
            }

            auth_global.commonLayer({
                area: ['100%', '100%'],
                title:title,
                content:url
            });
        }



        init();



    });

</script>
<jsp:include page="../../../base/innerLayout/inner_bottom.jsp"/>