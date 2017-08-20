<%@ page language="java"    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

                                <form id="teamUserForm" class="form-inline">
                                    <input type="hidden" name="teamId" value="${teamId}">
                                    <div class="form-group">
                                        <label class="font-noraml">选择用户</label>
                                        <div class="input-group">
                                            <select data-placeholder="选择省份" class="chosen-select" multiple style="width:350px;" tabindex="4" name="userIds[]">
                                                <option value="">请选择省份</option>
                                                <c:forEach var="item" items="${users}">
                                                    <option
                                                            <c:if test="${fn:contains(userCheckString, item.id)}">
                                                                selected
                                                            </c:if>


                                                            value="${item.id}" hassubinfo="true">${item.name}_${item.loginname}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-4 col-sm-8">
                                            <button id="saveSubmitBtn" class="btn btn-sm btn-primary" type="button">保存</button>
                                        </div>
                                    </div>
                                </form>
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

<link href="${ctx}/resource/css/plugins/chosen/chosen.css" rel="stylesheet">
<script src="${ctx}/resource/js/plugins/chosen/chosen.jquery.js"></script>
<script type="text/javascript">
    var $table = $('#listTable');
    $(function(){
        choosenInit();


        var $teamUserForm = $('#teamUserForm');
        var $saveSubmitBtn = $('#saveSubmitBtn');

        var frameIndex = parent.layer.getFrameIndex(window.name); //获取窗口索引
        $saveSubmitBtn.click(function(){
            auth_global.formSubmit($teamUserForm,'${ctx}/team/teamUserSaveAjax',function(msg, data){
                parent.$table.bootstrapTable('selectPage', 1);
                parent.layer.close(frameIndex);
            },function(code,msg ,data){
                auth_global.commonToast.toastError(msg);
            });
        });






        function choosenInit(){
            var config = {
                '.chosen-select': {},
                '.chosen-select-deselect': {
                    allow_single_deselect: true
                },
                '.chosen-select-no-single': {
                    disable_search_threshold: 10
                },
                '.chosen-select-no-results': {
                    no_results_text: 'Oops, nothing found!'
                },
                '.chosen-select-width': {
                    width: "95%"
                }
            }
            for (var selector in config) {
                $(selector).chosen(config[selector]);
            }
        }
    });

</script>
<jsp:include page="../../../base/innerLayout/inner_bottom.jsp"/>