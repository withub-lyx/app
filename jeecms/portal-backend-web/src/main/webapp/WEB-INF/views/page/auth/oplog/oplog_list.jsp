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
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>搜索条件查询</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <!-- Example Events -->
                    <div>
                        <%--查询表单--%>
                        <form id="queryForm" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">登录名：</label>
                                <div class="col-sm-4">
                                    <input name="detail" type="text" placeholder="请输入详细信息" class="form-control">
                                </div>
                            </div>

                            <div class="form-group ">
                                <label class="col-sm-2 control-label">详细信息：</label>
                                <div class="col-sm-4">
                                    <input name="detail" type="text" placeholder="请输入错误" class="form-control">
                                </div>
                            </div>
                            <div class="form-group ">
                                <label class="col-sm-2 control-label">详细错误：</label>
                                <div class="col-sm-4">
                                    <input name="exceptoinDetail" type="text" placeholder="请输入错误" class="form-control">
                                </div>
                            </div>

                            <div class="form-group ">
                                <label class="col-sm-2 control-label">类型：</label>
                                <div class="input-group col-sm-4">
                                    <select name="type" data-placeholder="选择类型" class="chosen-select" style="width:350px;" tabindex="2">
                                        <option value="">请选择类型</option>
                                        <c:forEach var="item" items="${typeList}">
                                            <option value="${item}" hassubinfo="true">${item}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group ">
                                <label class="col-sm-2 control-label">用户：</label>
                                <div class="input-group col-sm-4">
                                    <select name="operator" data-placeholder="选择用户" class="chosen-select" style="width:350px;" tabindex="2">
                                        <option value="">请选择用户</option>
                                        <c:forEach var="item" items="${userses}">
                                            <option value="${item.loginname}" hassubinfo="true">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <%--                     <div class="form-group">
                                                     <label class="control-label sr-only">是否可用：</label>
                                                     <select name="isok" class="form-control">
                                                         <option value="">--是否可用--</option>
                                                         <option value="1">可用</option>
                                                         <option value="0">不可用</option>
                                                     </select>
                                                 </div>--%>

                            <div class="form-group">
                                <div class="  col-sm-offset-2 col-sm-2">
                                    <button id="queryBtn" class="btn btn-primary " type="button" style="margin-top: 5px;">查询</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- End Example Events -->
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
    var $queryForm = $("#queryForm");
    var $queryBtn = $('#queryBtn');
    $(function(){
        function init(){
            auth_global.tableUtil.initTable($table,{
                url: '${ctx}/opLog/pageJson',
                pageSize: 50,
                pageList: [50, 100, 200, 400],
                queryParams: function (params) {
                    console.log($.extend(params, $queryBtn.data('queryParam')));
                    return $.extend(params, $queryBtn.data('queryParam'));
                },
                columns: [
                    {
                        field: 'id',
                        title: 'id'
                        //visible: false,//不可见
                    },
                    {
                        field: 'type',
                        title: '类型',
                        align: 'center'
                    }
                    ,
                    {
                        field: 'operator',
                        title: '操作人',
                        align: 'center'
                    }
                    ,
                    {
                        field: 'operateTimeStr',
                        title: '操作时间',
                        align: 'center'
                    }
                    ,
                    {
                        field: 'detail',
                        title: '详细',
                        align: 'center'
                    }
                    ,
                    {
                        field: 'exceptoinDetail',
                        title: '详细错误',
                        align: 'center'
                    }
                    , {//操作
                        field: 'operate',
                        title: '操作',
                        align: 'center',
                        //events: operateEvents,
                        formatter: function (value, row, index) {
                            var array=[];
                            array.push('<button  type="button" class="detail btn btn-primary btn-xs">详细</button>');

                            return array.join('');
                        },
                        events: {
                            'click .detail': function (e, value, row, index) {
                                detailFuntion(row.id);
                            }
                        }
                    }]
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

        function detailFuntion(id){
            auth_global.commonLayer({
                title:"详细信息",
                content:'${ctx}/opLog/detailPage?id='+id
            });
        }
        init();
    });

</script>
<jsp:include page="../../../base/innerLayout/inner_bottom.jsp"/>