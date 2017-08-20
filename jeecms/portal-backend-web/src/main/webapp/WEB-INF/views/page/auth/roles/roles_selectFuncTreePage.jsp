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
        <div class="col-sm-offset-3 col-sm-6  col-md-offset-3 col-md-6  col-lg-offset-3 col-lg-6">
            <!-- Panel Other -->
            <div class="ibox float-e-margins border-bottom">
                <div class="ibox-title">
                    <h5>菜单树</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-down"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row row-lg">
                            <div class="col-sm-offset-3 col-sm-6">
                                <button id="roleFuncSaveBtn" style="" class="btn btn-primary" type="button">保存</button>
                                <button id="flushFuncBtn" style="" class="btn btn-primary" type="button">刷新</button>
                                <button id="expendAllBtn" style="" class="btn btn-primary" type="button">展开全部</button>
                                <button id="classAllBtn" style="" class="btn btn-primary" type="button">收缩全部</button>
                                <div id="treeContent"></div>
                            </div>
                    </div>
                </div>
            </div>
            <!-- End Panel Other -->
        </div>
    </div>
</div>
<script src="${ctx}/resource/js/plugins/jsTree/jstree.min.js"></script>
<script src="${ctx}/resource/auth/js/global.js"></script>


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


查询所有被checkbox选中的id
$treeContent.jstree().get_selected()
--%>
<script type="text/javascript">

    var roleId = '${roleId}';
    var $treeContent = $('#treeContent');

    var frameIndex = parent.layer.getFrameIndex(window.name); //获取窗口索引

    var $roleFuncSaveBtn = $('#roleFuncSaveBtn');
    var $flushFuncBtn = $('#flushFuncBtn');
    var $expendAllBtn= $('#expendAllBtn');
    var $classAllBtn = $('#classAllBtn');


    $(function(){
        $treeContent.jstree({
            'core': {
                'data': {
                    'url': function (node) {//childrenJson
                        return 'childrenJson?roleId='+roleId;
                    },
                    'data': function (node) {
                        return {'id': node.id};
                    }
                }
            },
            'plugins': ['types', 'dnd',"checkbox"],
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
            }
        });

//        $treeContent.on("load_all.jstree", function (xxxx) {
//            //console.log("load_all.jstree      "+(xxxx))
//
//        });
//        $treeContent.on("ready.jstree", function (xxxx) {
//            //console.log("--------ready.jstree      ")
//            //console.log(xxxx)
//            //console.log("--------ready.jstree      ")
//
//        });

    });
    $flushFuncBtn.on("click",function(){
        $treeContent.jstree().refresh ();
    });
    $expendAllBtn.on("click",function(){
        $treeContent.jstree().open_all();
    });
    $classAllBtn.on("click",function(){
        $treeContent.jstree().close_all();
    });

    $roleFuncSaveBtn.on('click',function(){
        var selectList = $treeContent.jstree().get_selected();


        var resultMap = {};
        $.each(selectList,function(index,item){
            var authCode = 0;
            var tagList = item.split('_');
            var func=tagList[0];
            var buttonCode=tagList[1];
            if(resultMap[func] && buttonCode){
                resultMap[func]=parseInt(resultMap[func])+parseInt(buttonCode);
            }else if(resultMap[func] && !buttonCode){

            }else if(!resultMap[func]  && buttonCode){
                resultMap[func]=buttonCode;
            }else if(!resultMap[func]  && !buttonCode){
                resultMap[func]=0;
            }
        });


        for(var key in resultMap){
            var i=0;


            console.log('key='+key)
            var parentKey = $treeContent.jstree().get_parent(key) ;
            while(parentKey!='ROOT'   && parentKey!='#'){
                console.log(parentKey);
                if(!resultMap[parentKey]){
                    resultMap[parentKey]=0;
                }
                parentKey = $treeContent.jstree().get_parent(parentKey);

                if(i++>50){
                    break;
                }
            }
        }
        //console.log(resultMap);



        var submitData={};
        var index = 0 ;
        submitData.roleId=roleId;
        for(var key in resultMap){

            if(key=='ROOT'){
                continue;
            }
            submitData['roleFunc['+index+'].roleid']=roleId;
            submitData['roleFunc['+index+'].funcid']=key;
            submitData['roleFunc['+index+'].buttonauth']=resultMap[key];
            index++;
        }


        auth_global.commonAjax('${ctx}/roles/saveRoleFuncAjax?id='+roleId, function (msg,data) {
            parent.layer.close(frameIndex);
        }, function (code,msg,data) {
            swal("失败！", msg, "error");
        }, submitData , 'POST');



    });
</script>
<jsp:include page="../../../base/innerLayout/inner_bottom.jsp"/>