<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

                    <%--<div class="ibox-tools">--%>
                        <%--<a class="collapse-link">--%>
                            <%--<i class="fa fa-chevron-down"></i>--%>
                        <%--</a>--%>
                    <%--</div>--%>
                </div>
                <div class="ibox-content">
                    <div class="row row-lg">
                        <div class="col-sm-12">
                            <!-- Example Events -->
                            <div>
                                查询表单
                                <form id="queryForm" class="form-inline">
                                    <div class="form-group">
                                        <label class="control-label sr-only">状态：</label>
                                        <%--<input name="title" type="text" placeholder="请输入标题" class="form-control">--%>


                                        <select class="form-control" name="status">
                                            <option value="">所有状态</option>
                                            <c:forEach items="${statusSelect}" var="item">
                                                <option value="${item.code}">${item.desc}</option>
                                            </c:forEach>
                                        </select>

                                    </div>


                                    <div class="form-group">
                                        <button id="queryBtn" class="btn btn-primary " type="button"
                                                style="margin-top: 5px;">查询
                                        </button>
                                    </div>
                                </form>
                                工具栏
                                <%--<div id="tableToolbar" class="btn-group hidden-xs" role="group">--%>

                                    <%--<button id="addBtn" type="button" class="btn btn-outline btn-default">--%>
                                        <%--<i class="glyphicon glyphicon-plus" aria-hidden="true"></i>--%>
                                    <%--</button>--%>
                                    <%--<button id="removeBtn" type="button" class="btn btn-outline btn-default" disabled>--%>
                                        <%--<i class="glyphicon glyphicon-trash" aria-hidden="true"></i>--%>
                                    <%--</button>--%>

                                <%--</div>--%>


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
    var $layerCloseBtn = $('#layerCloseBtn');
    var $queryForm = $("#queryForm");
    var $queryBtn = $('#queryBtn');
    $(function () {
        function init() {
            auth_global.tableUtil.initTable($table, {
                url: '${ctx}/backend/matchResult/pageJson',
                toolbar: '#tableToolbar',//外部工具条,
                queryParams: function (params) {
                    return $.extend(params, $queryBtn.data('queryParam'));
                },
                //showToggle: true,//切换试图按钮
                columns: [
                    {
                        field: 'id',
                        title: 'id',
                        visible: false,//不可见
                    },
//                    {
//                        field: 'state',//checkbox
//                        checkbox: true,
//                        align: 'center',
//                        valign: 'middle'
//                    },


                    {//操作
                        field: 'operate',
                        title: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var array = [];
                            array.push('<a class="view" href="javascript:void(0)" title="更新">详情</a>');


                            if(row.status!=0) {
                                array.push('</br>');
                                array.push('<button class="btn-primary updatestatus" data-weixinuser-id="'+row.id+'"  data-status="0">审核通过</button>');
                            }





                            if(row.status!=2) {
                                array.push('</br>');
                                array.push('<button class="btn-warning updatestatus" data-weixinuser-id="'+row.id+'" data-status="2">不合法</button>');
                            }




                            if(row.status!=3) {
                                array.push('</br>');
                                array.push('<button class="btn-danger updatestatus" data-weixinuser-id="'+row.id+'" data-status="3">禁用</button>');
                            }



                            return array.join('');
                        },
                        events: {
                            'click .view': function (e, value, row, index) {
                                viewMethod(row.weiChatId);
                            },
                            'click .remove': function (e, value, row, index) {
                                deleteMethod([row.id]);
                            },
                            'click .showqrcode': function (e, value, row, index) {
                                var url = location.protocol + "//" + location.host + "${ctx}" + "/front/share/" + row.id;

                                var linkBtn = '<a href="' + url + '"  target="_blank">点击打开</a><br>';

                                var imgUrl = location.protocol + "//" + location.host + "${ctx}/front/qrcode/get?content=" + encodeURIComponent(url);
                                layer.tips('<div style="word-break:break-all;">' + linkBtn + url + '<img  style="width: 100px;height: 100px;" src="' + imgUrl + '"/>' + '</div>', this, {
                                    tips: [1, '#3595CC'],
                                    time: 20000
                                });
                            }
                        }
                    },






                    {
                        field: 'weiChatId',
                        title: '微信ID',
                        align: 'center'
                        //visible: false,//不可见
                    }
                    ,
                    {
                        field: 'age',
                        title: '年龄',
                        align: 'center'
                    }
                    ,
                    {
                        field: 'nickname',
                        title: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;昵称&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
                        align: 'center'
                    }
                    , {
                        field: 'sexDesc',
                        title: '性别',
                        align: 'center'
                    }
                    ,
                    {
                        field: 'provinceDesc',
                        title: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;省份&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
                        align: 'center'
                    },
                    {
                        field: 'cityDesc',
                        title: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;城市&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
                        align: 'center'
                    },
                    {
                        field: 'schoolDesc',
                        title: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学校&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
                        align: 'center'
                    }, {//操作
                        field: 'contactQrCodeImgUrl',
                        title: '微信二维码',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var array = [];

                            array.push('<a target="_blank" href="'+value+'">  <img style="height: 50px;width: 50px;" src="'+value+'"></img>    </a>');

                            return array.join('');
                        }
                    },
                    {
                        field: 'createTimeDesc',
                        title: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;创建时间&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
                        align: 'center'
                    },
                    {
                        field: 'update_timeDesc',
                        title: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;更新时间&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
                        align: 'center'
                    }



                    ,
                    {
                        field: 'province_id_after_schoolDesc',
                        title: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;毕业后省去向&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
                        align: 'center'
                    },
                    {
                        field: 'city_id_after_school_desc',
                        title: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;毕业后市去向&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
                        align: 'center'
                    }



                    ,
                    {
                        field: 'introduction',
                        title: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;个人简介&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if(value.length>10){
                                return value.substring(0,10)+'...';
                            }else{
                                return value;
                            }
                        }
                    },
                    {
                        field: 'sayToHer',
                        title: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;对TA说的话&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if(value.length>10){
                                return value.substring(0,10)+'...';
                            }else{
                                return value;
                            }
                        }
                    }
                    , {
                        field: 'photo_img_url',
                        title: '头像',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var array = [];

                            array.push('<a target="_blank" href="'+value+'">  <img style="height: 50px;width: 50px;" src="'+value+'"></img>    </a>');

                            return array.join('');
                        }
                    },
                    {
                        field: 'matchTimes',
                        title: '该用户被匹配的次数',
                        align: 'center'
                    }



                ]
            });


            $('body').on('click','.updatestatus',function(){
                var status = $(this).attr('data-status');
                var userId = $(this).attr('data-weixinuser-id');
                auth_global.commonAjax('${ctx}/backend/matchResult/updatestatusAjax', function (data, msg) {
                    /**
                     * 刷新表格
                     */
                    auth_global.tableUtil.selectPage($table, 1);
                    swal("成功！", "状态更新成功。", "success");
                }, function (code, msg, data) {
                    swal("失败！", msg, "error");
                }, {'status': status,'userId':userId});
            });
//            $table.on('check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table  refresh.bs.table post-body.bs.table ', function () {
//                $removeBtn.prop('disabled', !auth_global.tableUtil.getSelections($table).length);
//            });


            $layerCloseBtn.click(function () {
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
                auth_global.tableUtil.selectPage($table, 1);
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
                    auth_global.tableUtil.selectPage($table, 1);
                    swal("成功！", "删除成功。", "success");
                }, function (code, msg, data) {
                    swal("失败！", msg, "error");
                }, {'ids': ids});
            });
        }

        function viewMethod(weichatUserId) {
            var url = '${ctx}/front/collectinfo/showInfo?backendWeiChatUserId='+weichatUserId;
            var title = '详情';

            auth_global.commonLayer({
                area: ['40%', '100%'],
                title: title,
                content: url
            });
        }


        init();


    });

</script>
<jsp:include page="../../../base/innerLayout/inner_bottom.jsp"/>