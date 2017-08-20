<%@ page language="java"    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<jsp:include page="../../../base/innerLayout/inner_top.jsp"/>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <%--查询表单--%>
            <form:form modelAttribute="vo" id="saveForm" cssClass="form-horizontal">
                <form:hidden path="appid"/>

                <div class="form-group">
                    <label class="col-sm-4 control-label"><span style="color:Red;">*</span>应用名称：</label>
                    <div class="col-sm-8">
                        <form:input path="appname" type="text" placeholder="请输入应用名称" class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-4 control-label"><span style="color:Red;">*</span>应用名称(中文)：</label>
                    <div class="col-sm-8">
                        <form:input path="appnamecn" type="text" placeholder="请输入应用名称(中文)" class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-4 control-label"><span style="color:Red;">*</span>负责人：</label>
                    <div class="col-sm-8">
                        <form:input path="appuser" type="text" placeholder="请输入负责人" class="form-control"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-4 control-label"><span style="color:Red;">*</span>访问地址：</label>
                    <div class="col-sm-8">
                        <form:input path="appurl" type="text" placeholder="请输入访问地址" class="form-control"/>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-sm-4 control-label"><span style="color:Red;">*</span>应用类型：</label>
                    <div class="col-sm-8">
                        <form:select path="apptype" items="${appTypes}" itemLabel="desc" itemValue="code" cssClass="form-control" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-4 control-label"><span style="color:Red;">*</span>所属事业部：</label>
                    <div class="col-sm-8">
                        <form:select path="appclassid" items="${appClassIds}" itemLabel="desc" itemValue="code" cssClass="form-control" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label"><span style="color:Red;">*</span>是否可用：</label>
                    <div class="col-sm-8">
                        <form:select path="appstatus" items="${isOkSelect}" itemLabel="desc" itemValue="code" cssClass="form-control" />
                    </div>
                </div>

                <div class="col-sm-offset-4 col-sm-8" >
                    <table data-toggle="table" >
                        <thead>
                        <tr>
                            <th></th>
                            <th>授权</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${appFuncs}">
                            <tr>
                                <td>
                                    <c:set var="has" value="false"/>
                                    <c:forEach var="tempFun" items="${funcs}">
                                        <c:if test="${tempFun == item.code}">
                                            <c:set var="has" value="true"/>
                                        </c:if>
                                    </c:forEach>
                                    <input name="funList[]" type="checkbox" value="${item.code}" <c:if test="${has}"> checked="checked" </c:if>>
                                </td>
                                <td>${item.desc}</td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>





                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                        <button id="saveSubmitBtn" class="btn btn-sm btn-primary" type="button">保存</button>
                    </div>
                </div>





            </form:form>
        </div>
    </div>

</div>

<script src="${ctx}/resource/auth/js/global.js"></script>

<script type="text/javascript">
    var $saveFrom = $('#saveForm');
    var $id_input = $('#id');
    var $saveSubmitBtn = $('#saveSubmitBtn');
    var frameIndex = parent.layer.getFrameIndex(window.name); //获取窗口索引
    $(function () {
        function initForm() {
            $saveFrom.validate({
                rules: {
                    appname: {
                        required: true,
                    },
                    appnamecn: {
                        required: true
                    },
                    appuser: {
                        required: true
                    },
                    appurl: {
                        required: true
                    }
                },
                messages: {
                    appname: {
                        required: auth_global.validateErrorIcon + "必填",
                    },
                    appnamecn: {
                        required: auth_global.validateErrorIcon + "必填"
                    },
                    appuser: {
                        required: auth_global.validateErrorIcon + "必填"
                    },
                    appurl: {
                        required: auth_global.validateErrorIcon + "必填",
                    }
                }
            });


            $saveSubmitBtn.click(function () {
                if (!$saveFrom.valid()) {
                    return;
                }


                var url = '';
                if($id_input.val()){
                    url = '${ctx}/apps/editAjax';
                }else{
                    url = '${ctx}/apps/addAjax';
                }

                auth_global.formSubmit($saveFrom,url,function(msg, data){
                    parent.$table.bootstrapTable('selectPage', 1);
                    parent.layer.close(frameIndex);
                },function(code,msg ,data){
                    auth_global.commonToast.toastError(msg);
                });
            });
        }
        initForm();
    });
</script>

<jsp:include page="../../../base/innerLayout/inner_bottom.jsp"/>