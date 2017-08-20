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
                            <div class="col-sm-12">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        详细信息
                                    </div>
                                    <div class="panel-body">
                                        <p>${detail}</p>
                                    </div>
                                </div>
                            </div>


                            <div class="col-sm-12">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">
                                        详细错误信息
                                    </div>
                                    <div class="panel-body">
                                        <p>${exceptoinDetail}</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <!-- End Panel Other -->
        </div>
    </div>
</div>

<script src="${ctx}/resource/auth/js/global.js"></script>

<jsp:include page="../../../base/innerLayout/inner_bottom.jsp"/>