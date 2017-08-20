<%@ page language="java"    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<jsp:include page="../../../base/innerLayout/inner_top.jsp"/>

<style>
    .jstree-open > .jstree-anchor > .fa-folder:before {
        content: "\f07c";
    }

    .jstree-default .jstree-icon.none {
        width: 0;
    }
</style>


<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-3">
            <!-- Panel Other -->
            <div class="ibox float-e-margins border-bottom">
                <div class="ibox-title">
                    <h5>菜单</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-down"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row row-lg">
                        <div class="col-sm-3">
                            <!-- Example Events -->
                            <div>
                                <%--工具栏--%>
<%--                                <div id="tableToolbar" class="btn-group hidden-xs" role="group">
                                    <button id="addBtn" type="button" class="btn btn-outline btn-default">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"></i>
                                    </button>
                                    <button id="removeBtn" type="button" class="btn btn-outline btn-default" disabled>
                                        <i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
                                    </button>
                                </div>--%>
                                <div id="treeContent"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End Panel Other -->
        </div>

        <div class="col-sm-5">
            <!-- Panel Other -->
            <div class="ibox float-e-margins border-bottom">
                <div class="ibox-title">
                    <h5>详细设置</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-down"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row row-lg">


                        <form class="form-horizontal" id="funcFrom">

                            <input type="hidden" id="parid"  name="parid" >
                            <input type="hidden" id="id"  name="id" >


                            <div class="form-group">
                                <label class="col-sm-3 control-label">父菜单：</label>
                                <div class="col-sm-6">
                                    <input type="text"  class="form-control"  id="parentFunc" readonly="true" value="">
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-3 control-label">菜单名：</label>
                                <div class="col-sm-6">
                                    <input type="text" placeholder="请输入菜单名称" class="form-control" name="name" id="name">
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-sm-3 control-label">菜单码：</label>
                                <div class="col-sm-6">
                                    <input type="text" placeholder="请输入菜单代码" class="form-control" name="code" id="code">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否有效：</label>
                                <div class="col-sm-6">
                                    <select class="form-control" name="isok" id="isok">
                                        <c:forEach items="${isOkSelectList}" var="item">
                                            <option value="${item.code}">${item.desc}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">链接：</label>
                                <div class="col-sm-6">
                                    <input type="text" placeholder="请输入链接地址" class="form-control" name="url" id="url">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">图标：</label>
                                <div class="col-sm-6">
                                    <%--<input type="text" placeholder="请输入图标样式" class="form-control" name="icon" id="icon">--%>
                                    <input type="hidden" id="icon" name="icon" value="glyphicon glyphicon-th-list">
                                    <icon id="iconShow" class="glyphicon glyphicon-th-list"></icon>
                                    <button id="selectIconBtn" class=" btn btn-primary" type="button">选择图标</button>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">排序：</label>
                                <div class="col-sm-6">
                                    <input type="number" placeholder="请输入菜单排序" class="form-control" name="ordernum" id="ordernum">
                                </div>
                            </div>


                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-6">
                                    <button style="display: none;" id="addFuncBtn" class="btn btn-primary" type="button">添加</button>
                                    <button style="display: none;" id="editFuncBtn" class="btn btn-warning" type="button">修改</button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
            <!-- End Panel Other -->
        </div>


        <div class="col-sm-4">
            <!-- Panel Other -->
            <div class="ibox float-e-margins border-bottom">
                <div class="ibox-title">
                    <h5 id="buttonTableTitle">按钮设置</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-down"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row row-lg">


                        <form id="addOpperBtnForm" class="form-horizontal">
                            <input type="hidden" name="id">
                            <input type="hidden" name="func">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">名称：</label>
                                <div class="col-sm-6">
                                    <input name="name"  type="text" placeholder="请输入菜单排序" class="form-control" >
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">启用：</label>
                                <div class="col-sm-6">
                                    <select name="isok"  class="form-control">
                                        <c:forEach items="${isOkSelectList}" var="item">
                                            <option value="${item.code}">${item.desc}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">授权码：</label>
                                <div class="col-sm-6">
                                    <input  name="buttonCode"  type="number" placeholder="授权CODE" class="form-control" >
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-6">
                                    <button  id="addOpperBtn"  style="display: none;"  class="btn btn-primary" type="button">添加新按钮</button>
                                    <button  id="updateOpperBtn"  style="display: none;"  class="btn btn-primary" type="button">修改按钮</button>
                                </div>
                            </div>
                        </form>




                        <table id="buttonTable" class="table table-hover" data-striped="true"></table>
                    </div>
                </div>
            </div>
            <!-- End Panel Other -->
        </div>
    </div>
</div>
<script src="${ctx}/resource/js/plugins/jsTree/jstree.min.js"></script>
<script src="${ctx}/resource/auth/js/global.js"></script>
<script type="text/javascript">
 /*   var $table = $('#roleTable');
    var $addBtn = $('#addBtn');
    var $removeBtn = $('#removeBtn');
    var $queryForm = $("#queryForm");
    var $queryBtn = $('#queryBtn');
    $(function(){
        function init(){
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
                auth_global.commonAjax('${ctx}/roles/deleteAjax', function (data, msg) {
                    auth_global.tableUtil.selectPage($table,1);
                    swal("成功！", "删除成功。", "success");
                }, function (code, msg, data) {
                    swal("失败！", msg, "error");
                }, {'ids': ids});
            });
        }

        function saveMethod(id) {
            var url = '${ctx}/roles/savePage';
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
    });*/

</script>


<%--
https://www.jstree.com/demo_filebrowser/index.php
$treeContent.jstree().refresh()

获取当前选中节点
$treeContent.jstree().get_selected()

刷新指定的子节点
$treeContent.jstree().refresh_node("4677f261-0176-4fce-b2e8-c7f07abb685d")

展开所有子节点
$treeContent.jstree().open_all()

关闭所有子节点
$treeContent.jstree().close_all()

获得父节点id
$treeContent.jstree().get_parent('590f53af-ca0e-41b3-9ebf-181bba67a3e0')

根据id获取当前节点
$treeContent.jstree().get_node('2e121f9e-c26d-49a8-b86b-f51c446c9c87')
--%>
<script type="text/javascript">

    var $funcFrom = $('#funcFrom');
    var $treeContent = $('#treeContent');
    var $parid=$('#parid');
    var $id=$('#id');
    var $name=$('#name');
    var $code=$('#code');
    var $isok=$('#isok');
    var $url=$('#url');
    var $icon=$('#icon');
    var $ordernum=$('#ordernum');
    var $parentFunc=$('#parentFunc');



    var $addFuncBtn=$('#addFuncBtn');
    var $editFuncBtn=$('#editFuncBtn');


    var $buttonTable = $('#buttonTable');


    var $buttonTableTitle = $('#buttonTableTitle');





    var $addOpperBtn = $('#addOpperBtn');
    var $updateOpperBtn = $('#updateOpperBtn');
    var $addOpperBtnForm = $('#addOpperBtnForm');
    var $operbuttonIdInput =  $addOpperBtnForm.find('[name="id"]');
    var $operbuttonFuncInput =  $addOpperBtnForm.find('[name="func"]');
    var $operbuttonNameInput =  $addOpperBtnForm.find('[name="name"]');
    var $operbuttonIsOkSelect =  $addOpperBtnForm.find('[name="isok"]');
    var $operbuttonButtonCodeInput =  $addOpperBtnForm.find('[name="buttonCode"]');



    var $iconShow = $('#iconShow');
    var $selectIconBtn = $('#selectIconBtn');

    $(function(){
        $funcFrom.validate({
            rules: {
                name: {
                    required: true,
                },
                code: {
                    required: true,
                },
                isok: {
                    required: true,
                },
                icon: {
                    required: true,
                },
                ordernum: {
                    required: true,
                },
                appid: {
                    required: true,
                }
            },
            messages: {
                name: {
                    required: auth_global.validateErrorIcon + "必填"
                },
                code: {
                    required: auth_global.validateErrorIcon + "必填"
                },
                isok: {
                    required: auth_global.validateErrorIcon + "必填"
                },
                icon: {
                    required: auth_global.validateErrorIcon + "必填"
                },
                ordernum: {
                    required: auth_global.validateErrorIcon + "必填"
                },
                appid: {
                    required: auth_global.validateErrorIcon + "必填"
                }
            }
        });





        $addOpperBtnForm.validate({
            rules: {
                "name": {
                    required: true,
                },
                "isok": {
                    required: true,
                },
                "buttonCode": {
                    required: true,
                }
            },
            messages: {
                "name": {
                    required: auth_global.validateErrorIcon + "必填"
                },
                "isok": {
                    required: auth_global.validateErrorIcon + "必填"
                },
                "buttonCode": {
                    required: auth_global.validateErrorIcon + "必填"
                }
            }
        });










        $treeContent.jstree({
            'core': {
                'data': {
                    'url': function (node) {//childrenJson
                        return node.id === '#' ?
                                'rootsJson' :
                                'childrenJson';
                    },
                    'data': function (node) {
                        console.log(node);
                        return {'id': node.id};
                    }
                }
            },
            'plugins': ['types', 'dnd',"contextmenu"],
            'types': {
                'default': {
                    'icon': 'fa fa-folder'
                },
                'html': {
                    'icon': 'fa fa-file-code-o'
                },
                'svg': {
                    'icon': 'fa fa-file-picture-o'
                },
                'css': {
                    'icon': 'fa fa-file-code-o'
                },
                'img': {
                    'icon': 'fa fa-file-image-o'
                },
                'js': {
                    'icon': 'fa fa-file-text-o'
                }
            },
            'contextmenu' : {
                'items' : function(node) {
                    var tmp = $.jstree.defaults.contextmenu.items();
                    console.log(tmp);
                    var _create = {
                        label: '新建子节点',
                        action: function (data) {
                            console.log('新建');
                            var ref = $.jstree.reference(data.reference);
                            var nowNode = ref.get_node(data.reference);
                            console.log(nowNode);
                            addChildrenNode(nowNode);
                            console.log('新建');
                        }
                    };
                    var _remove = {
                        label: '删除该节点',
                        action: function (data) {
                            console.log('删除该节点');
                            var ref = $.jstree.reference(data.reference);
                            var nowNode = ref.get_node(data.reference);
                            console.log(nowNode);
                            deleteSelectNode(nowNode);
                            console.log('删除该节点');
                        }
                    }
                    tmp.create=_create;
                    tmp.remove=_remove;
//                    if(this.get_type(node) === "file") {
//                        delete tmp.create;
//                    }
                    delete  tmp.rename;
                    delete  tmp.ccp;
                    return tmp;
                }
            }

        });

        $treeContent.on("select_node.jstree", function (p1, p2,p3) {
            console.log(p2.node.id);
            resetEditComponent(p2.node.id);


            auth_global.tableUtil.refresh($buttonTable);


            $buttonTableTitle.text($treeContent.jstree().get_node($treeContent.jstree().get_selected()[0]).text+'[按钮设置]');





        });

        function resetEditComponent(id){
            switch (id){
                case 'ROOT':{

                    editRoot();
                    console.log('根节点');
                    break;
                };
                default:{

                    editExistFunc(id);
                    console.log('非根节点');
                    break;
                }
            }
        }

        function editRoot(){
            $parid.val('ROOT');
            $id.val('');
            $name.val('');
            $code.val('');
            $isok.val(1);
            $url.val('');
            $icon.val('glyphicon glyphicon-th-list');
            $ordernum.val('');
            $parentFunc.val('无');

            //operbutten设置
            $operbuttonFuncInput.val("");

            buttonState.root();
        }
        function editExistFunc(id){
            buttonState.edit();
            auth_global.commonAjax('${ctx}/func/getItemInfoById', function (msg,data) {
                $parid.val(data.parid);
                $id.val(data.id);
                $name.val(data.name);
                $code.val(data.code);
                $isok.val(data.isok);
                $url.val(data.url);
                $icon.val(data.icon);
                $iconShow.attr('class',data.icon);
                $ordernum.val(data.ordernum);

                //operbutten设置
                $operbuttonFuncInput.val(data.id);


                //查询并设置下一次的buttonCode
                auth_global.commonAjax('${ctx}/func/nextBtnAuth', function (msg,data) {
                    $operbuttonButtonCodeInput.val(data);
                }, function (code,msg,data) {
                    swal("失败！", msg, "error");
                }, {
                    "func": data.id
                }, 'GET');



                //查询并设置下一次的butoncode
            }, function (code,msg,data) {
                swal("失败！", msg, "error");
            }, {
                "id": id
            }, 'GET');

            auth_global.commonAjax('${ctx}/func/getItemInfoById', function (msg,data) {
                $parentFunc.val(data.name);
            }, function (code,msg,data) {
                alert('出错' + msg);
            }, {
                "id": $treeContent.jstree().get_parent(id)
            }, 'GET');
        }

        /**
         *
         * 添加子节点
         */
        function addChildrenNode(nowSelectedNode){
            var pId = nowSelectedNode.id;
            $parid.val(pId);
            $id.val('');
            $name.val('');
            $code.val('');
            $isok.val(1);
            $url.val('');
            $icon.val('glyphicon glyphicon-th-list');
            $ordernum.val('');
            if(pId=='ROOT'){
                $parentFunc.val('无');
            }else{
                $parentFunc.val(nowSelectedNode.text);
            }

            buttonState.add();
        }

        var buttonState={
            add:function(){
                $addFuncBtn.show();
                $editFuncBtn.hide();
                /**
                 * 把添加按钮的按钮显示出来
                 **/
                $addOpperBtn.show();
                $updateOpperBtn.hide();
            },
            edit:function(){
                $addFuncBtn.hide();
                $editFuncBtn.show();
                /**
                 * 把添加按钮的按钮显示出来
                 **/
                $addOpperBtn.show();

                $updateOpperBtn.hide();
            },
            root:function(){
                $addFuncBtn.hide();
                $editFuncBtn.hide();
                /**
                 * 把添加按钮的按钮干掉
                 **/
                $addOpperBtn.hide();

                $updateOpperBtn.hide();
            }
        };

        $addFuncBtn.click(function(){
            if (!$funcFrom.valid()) {
                return;
            }
            auth_global.formSubmit($funcFrom,'${ctx}/func/saveFuncAjax',function(msg,data){
                var pId = $parid.val();
                $treeContent.jstree().refresh_node(pId)
            },function(code,msg ,data){
                auth_global.commonToast.toastError(msg);
            });
        });
        $editFuncBtn.click(function(){
            if (!$funcFrom.valid()) {
                return;
            }
            auth_global.formSubmit($funcFrom,'${ctx}/func/saveFuncAjax',function(msg,data){
                var pId = $parid.val();
                $treeContent.jstree().refresh_node(pId);
            },function(code,msg ,data){
                auth_global.commonToast.toastError(msg);
            });
        });

        /**
         * 删除指定节点
         */
        function deleteSelectNode(nowSelectedNode){
            var nowId = nowSelectedNode.id;
            auth_global.commonAjax('${ctx}/func/deleteFuncByIdAjax', function (data, msg) {
                console.log('刷新了'+nowId);
                var flushItemId = $treeContent.jstree().get_parent(nowId);
                if(!flushItemId || flushItemId=='#'){
                    flushItemId = 'ROOT';
                }
                $treeContent.jstree().refresh_node(flushItemId)
            }, function (code, msg, data) {
                swal("失败！", msg, "error");
            }, {'id': nowId});
        }






     $buttonTable.bootstrapTable({
         queryParams: function (params) {
             return {
                 id: $treeContent.jstree().get_selected()[0]
             };
         },
         columns: [
             {
                 field: 'id',
                 title: 'id',
                 visible: false,//不可见
             },
             {
                 field: 'func',
                 title: 'func',
                 visible: false,//不可见
             },
             {
                 field: 'name',
                 title: '按钮名称'
             }, {
                 field: 'isok',
                 title: '是否有效',
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
             }, {
                 field: 'buttonCode',
                 title: '授权码'
             }, {//操作
                 field: 'operate',
                 title: '操作',
                 align: 'center',
                 formatter: function (value, row, index) {
                     console.log(row);
//                     if(row.buttonCode<9){
//                         return '';
//                     }
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
                         //saveMethod(row.id);

                         console.log(row)
                        $operbuttonIdInput.val(row.id);
                        $operbuttonFuncInput.val(row.func);
                        $operbuttonNameInput.val(row.name);
                        $operbuttonIsOkSelect.val(row.isok);
                        $operbuttonButtonCodeInput.val(row.buttonCode);


                         $updateOpperBtn.show();
                         $addOpperBtn.hide();
                     },
                     'click .remove': function (e, value, row, index) {
                         deleteBtnOpperBtnByIdAjax(row.id);
                         auth_global.tableUtil.refresh($buttonTable);
                     }
                 }
             }
         ],
             url: '${ctx}/func/queryButtons',
         method: 'get',//获取数据方式 get/post
         mobileResponsive: true,
         //height: "400",//固定高度
         pageSize: "ALL",//每页大小
         showFooter: false,//页脚是否显示
         showRefresh: true,//显示刷新按钮
         showToggle: true,//切换试图按钮
         striped: true
     });





        $updateOpperBtn.click(function(){
            saveOpperBtnAjax();
        });
        $addOpperBtn.click(function(){
            saveOpperBtnAjax();
        });


        function saveOpperBtnAjax(){
            if (!$addOpperBtnForm.valid()) {
                return;
            }
            auth_global.formSubmit($addOpperBtnForm,'${ctx}/func/saveOpperBtnAjax',function(msg,data){
                cleanOperBtnForm();
                auth_global.tableUtil.refresh($buttonTable);
            },function(code,msg ,data){
                auth_global.commonToast.toastError(msg);
            });
        }

        function deleteBtnOpperBtnByIdAjax(id){
            <%--alert(2333)--%>
            <%--auth_global.formSubmit($addOpperBtnForm,'${ctx}/func/deleteOpperBtnByIdAjax?id='+id,function(msg,data){--%>
                <%--auth_global.tableUtil.refresh($buttonTable);--%>
            <%--},function(code,msg ,data){--%>
                <%--auth_global.commonToast.toastError(msg);--%>
            <%--});--%>

            auth_global.commonAjax('${ctx}/func/deleteOpperBtnByIdAjax', function (data, msg) {
                auth_global.tableUtil.refresh($buttonTable);
            }, function (code, msg, data) {
                swal("失败！", msg, "error");
                auth_global.tableUtil.refresh($buttonTable);
            }, {'id': id});
        }


        function cleanOperBtnForm(){
            $operbuttonIdInput.val("");
            //$operbuttonFuncInput.val("");
            $operbuttonNameInput.val("");
            $operbuttonIsOkSelect.val("1");
            $operbuttonButtonCodeInput.val("");
            $updateOpperBtn.hide();
            $addOpperBtn.show();
        }






        $selectIconBtn.click(function(){
            auth_global.commonLayer({
                title:'选择图标',
                content:'${ctx}/func/selectIconPage'
            })
        });

    });
</script>
<jsp:include page="../../../base/innerLayout/inner_bottom.jsp"/>