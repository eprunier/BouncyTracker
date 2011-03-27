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

	<h1><fmt:message key="page.secure.account.show.title" /></h1>
	<form:form commandName="user">
		<table>
			<tbody>
				<tr class="odd prop">
					<td class="name"><fmt:message key="user.firstName" /></td>
					<td class="value"><form:input path="firstName" size="20" maxlength="60" /></td>
					<td><form:errors path="firstName" class="error" /></td>
				</tr>
				<tr class="even prop">
					<td class="name"><fmt:message key="user.lastName" /></td>
					<td class="value"><form:input path="lastName" size="20" maxlength="60" /></td>
					<td><form:errors path="lastName" class="error" /></td>
				</tr>
				<tr class="odd prop">
					<td class="name"><fmt:message key="user.email" /></td>
					<td class="value"><form:input path="email" size="20" maxlength="100" /></td>
					<td><form:errors path="email" class="error" /></td>
				</tr>
				<tr class="even prop">
					<td class="name"><fmt:message key="user.password" /></td>
					<td class="value"><form:password path="password" size="20" maxlength="32" /></td>
					<td><form:errors path="password" class="error" /></td>
				</tr>
				<tr class="odd prop">
					<td class="name"><fmt:message key="user.verifyPassword" /></td>
					<td class="value"><form:password path="verifyPassword" size="20" maxlength="32" /></td>
					<td><form:errors path="verifyPassword" class="error" /></td>
				</tr>
			</tbody>
		</table>
		<br />
		<input type="submit" value="<fmt:message key='form.submit' />" />
	</form:form>

</body>
</html>