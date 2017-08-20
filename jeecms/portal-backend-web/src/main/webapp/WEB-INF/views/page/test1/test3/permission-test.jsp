<%@ page language="java"    contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"  %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>




<shiro:hasPermission name="sub_menu_3code:1">
    sub_menu_3code:1<br>
</shiro:hasPermission>

<shiro:hasPermission name="sub_menu_3code:2">
    sub_menu_3code:2<br>
</shiro:hasPermission>

<shiro:hasPermission name="sub_menu_3code:4">
    sub_menu_3code:4<br>
</shiro:hasPermission>

<shiro:hasPermission name="sub_menu_3code:8">
    sub_menu_3code:8<br>
</shiro:hasPermission>

