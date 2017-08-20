<%@ page language="java"    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<jsp:include page="../../../base/innerLayout/inner_top.jsp"/>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <%--查询表单--%>
            <form id="saveForm" cssClass="form-horizontal">
                <input type="hidden" id="userId" name="userId" value="${userId}">

                <div class="form-group">
                    <label class="col-sm-2 control-label">角色：</label>
                    <div class="col-sm-4">
                        <c:forEach items="${selectRolesList}" var="item">
                            <div class="checkbox i-checks">
                                <label>
                                    <input name="roleId[]" type="checkbox" value="${item.id}"
                                           <c:if test="${item.select}">checked=""</c:if>  > <i></i> ${item.name}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                        <button id="saveSubmitBtn" class="btn btn-sm btn-primary" type="button">保存</button>
                    </div>
                </div>

            </form>
        </div>
    </div>

</div>

<script src="${ctx}/resource/auth/js/global.js"></script>

<script type="text/javascript">
    var $saveFrom = $('#saveForm');
    var $saveSubmitBtn = $('#saveSubmitBtn');
    var frameIndex = parent.layer.getFrameIndex(window.name); //获取窗口索引
    $(function () {
        function initForm() {
            $saveFrom.validate();
            $saveSubmitBtn.click(function () {
                if (!$saveFrom.valid()) {
                    return;
                }
                auth_global.formSubmit($saveFrom, '${ctx}/user/saveRolesAjax', function (msg, data) {
                    parent.$table.bootstrapTable('selectPage', 1);
                    parent.layer.close(frameIndex);
                }, function (code, msg, data) {
                    auth_global.commonToast.toastError(msg);
                });
            });
        }

        initForm();
    });
</script>

<jsp:include page="../../../base/innerLayout/inner_bottom.jsp"/>