<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/main.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/validate/main.css"/>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.validate.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#addForm").cmsvalidate();
        });
    </script>
</head>
<body>
<div id="content">
    <h3 class="admin_link_bar">
        <jsp:include page="inc.jsp"/>
    </h3>
    <form:form method="post" modelAttribute="user" id="addForm">
        <table width="800" cellspacing="0" cellPadding="0">
            <form:hidden path="id"/><form:hidden path="username"/>
            <form:hidden path="password"/>
            <thead>
            <tr>
                <td colspan="2">修改用户-->${user.username}</td>
            </tr>
            </thead>
            <tr>
                <td class="rightTd">显示名称(可以是中文):</td>
                <td class="leftTd"><form:input path="nickname" size="30"/><form:errors path="nickname" cssClass="errorContainer"/></td>
            </tr>
            <tr>
                <td class="rightTd">联系电话:</td>
                <td><form:input path="phone" size="30"/></td>
            </tr>
            <tr>
                <td class="rightTd">电子邮件:</td>
                <td><form:input path="email" size="30"/><form:errors path="email" cssClass="errorContainer"/></td>
            </tr>
            <tr>
                <td class="rightTd">状态:</td>
                <td>
                    <form:select path="status">
                        <form:option value="0">停用</form:option>
                        <form:option value="1">启用</form:option>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td class="rightTd">角色:</td>
                <td>
                    <c:forEach var="role" items="${roles }">
                        ${role.name }<input type="checkbox" name="roleIds" value="${role.id }"
                                            <c:if test="${fn:contains(userRoles,role)}">checked="checked"</c:if>
                            />
                    </c:forEach>
                        <%--<form:checkboxes  items="${roles}" itemLabel="name" itemValue="id" path=" "/>--%>
                </td>
            </tr>
            <tr>
                <td class="rightTd">用户组:</td>
                <td>
                    <c:forEach var="group" items="${groups }">
                        ${group.name }<input type="checkbox" name="groupIds" value="${group.id }"
                                             <c:if test="${fn:contains(userGroups,group)}">checked="checked"</c:if>
                            />
                    </c:forEach>
                        <%--<form:checkboxes items="${groups }" path="groupIds" itemLabel="name" itemValue="id"/>--%>
                </td>
            </tr>
            <tr>
                <td colspan="2" class="centerTd"><input type="submit" value="修改用户"/><input type="reset"/></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>