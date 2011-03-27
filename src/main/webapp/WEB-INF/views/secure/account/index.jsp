<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<fmt:bundle basename="messages" />

<html>
<head>
	<title><fmt:message key="page.secure.account.show.title" /></title>
</head>
<body>

	<h1>
		<fmt:message key="page.secure.account.show.title" />
		<span class="action">[<a href="<%=request.getContextPath()%>/secure/account/update"><fmt:message key="action.edit" /></a>]</span> 
	</h1> 
	<table class="list" style="width: 400px">
		<tbody>
			<tr class="prop odd">
				<td class="name"><fmt:message key="user.firstName" /></td>
				<td class="value">${user.firstName}</td>
			</tr>
			<tr class="prop even">
				<td class="name"><fmt:message key="user.lastName" /></td>
				<td class="value">${user.lastName}</td>
			</tr>
			<tr class="prop odd">
				<td class="name"><fmt:message key="user.email" /></td>
				<td class="value">${user.email}</td>
			</tr>
		</tbody>
	</table>

</body>
</html>