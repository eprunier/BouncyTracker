<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="messages" />

<html>
<head>
	<title><fmt:message key="page.secure.project.create.title" /></title>
</head>
<body>

	<h1><fmt:message key="page.secure.project.create.title" /></h1>
	<form:form commandName="project">
		<form:hidden path="user.email"/>
		<table>
			<tbody>
				<tr class="odd prop">
					<td class="name"><fmt:message key="project.name" /></td>
					<td class="value"><form:input path="name" /></td>
					<td><form:errors path="name" class="error" /></td>
				</tr>
				<tr class="even prop">
					<td class="name"><fmt:message key="project.description" /></td>
					<td class="value">
						<form:textarea path="description" />
					</td>
					<td><form:errors path="description" class="error" /></td>
				</tr>
				<tr class="odd prop">
					<td class="name">
						<fmt:message key="project.startDate" /><br />
						<font style="font-weight: normal">(<fmt:message key="project.startDate.info" />)</font>
					</td>
					<td class="value">
						<form:input path="startDate" id="startDate" />
						<script type="text/javascript">
							$(function() {
								$("#startDate").datepicker();
							});
						</script>	
					</td>
					<td><form:errors path="startDate" class="error" /></td>
				</tr>
				<tr class="even prop">
					<td class="name"><fmt:message key="project.iterationLength" /></td>
					<td class="value">
						<!--<form:input path="iterationLength" size="2" />--> 
						<form:select path="iterationLength">
							<form:option value="1">1</form:option>
							<form:option value="2">2</form:option>
							<form:option value="3">3</form:option>
							<form:option value="4">4</form:option>
						</form:select>
						<fmt:message key="project.iterationLength.weeks" />
					</td>
					<td><form:errors path="iterationLength" class="error" /></td>
				</tr>
			</tbody>
		</table>
		<div class="buttons">
			<input type="submit" value="<fmt:message key='form.submit' />" />
		</div>
	</form:form>

</body>
</html>