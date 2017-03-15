<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/admin/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/validate/main.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/core/jquery.cms.validate.js"></script>
<script type="text/javascript">
$(function(){
	$("#addForm").cmsvalidate();
});
</script>
</head>
<body>
<div id="content">
	<h3 class="admin_link_bar">
		<jsp:include page="inc.jsp"></jsp:include>
	</h3>
	<form:form method="post" modelAttribute="user" id="addForm">
	<table width="800" cellspacing="0" cellPadding="0">
		<thead><tr><td colspan="2">添加用户功能</td></tr></thead>
		<tr>
			<td class="rightTd" width="200px">用户名(必须是英文):</td>
			<td class="leftTd"><form:input path="username" size="30"/><form:errors cssClass="errorContainer" path="username"/></td>
		</tr>
		<tr>
			<td class="rightTd">显示名称(可以是中文):</td><td class="leftTd"><form:input path="nickname" size="30"/><form:errors cssClass="errorContainer" path="nickname"/></td>
		</tr>
		<tr>
			<td class="rightTd">用户密码:</td><td><form:password path="password" size="30"/><form:errors cssClass="errorContainer" path="password"/></td>
		</tr>
		<tr>
			<td class="rightTd">确认密码:</td><td><input type="password" id="confirmPwd" name="confirmPwd" size="30"/></td>
		</tr>
		<tr>
			<td class="rightTd">联系电话:</td><td><form:input path="phone" size="30"/></td>
		</tr>
		<tr>
			<td class="rightTd">电子邮件:</td><td><form:input path="email" size="30"/><form:errors cssClass="errorContainer" path="email"/></td>
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
					${role.name }<input type="checkbox" name="roleIds" value="${role.id }"/>
				</c:forEach>
			<%--<form:checkboxes  items="${roles}" itemLabel="name" itemValue="id" path="roleIds"/>--%>
		</td>
	</tr>
		<tr>
			<td class="rightTd">用户组:</td>
			<td>
					 <c:forEach var="group" items="${groups }">
                        ${group.name }<input type="checkbox" name="groupIds" value="${group.id }"/>
                    </c:forEach>
				<%--<form:checkboxes items="${groups }" path="groupIds" itemLabel="name" itemValue="id"/>--%>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="centerTd"><input type="submit" value="添加用户"/><input type="reset"/></td>
		</tr>
	</table>
	</form:form>
</div>
</body>
</html>