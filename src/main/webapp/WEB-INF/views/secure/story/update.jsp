<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>

<%@ page import="com.bouncytracker.domain.model.StoryCategory" %>
<%@ page import="com.bouncytracker.domain.model.StoryStatus"  %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="messages" />

<html>
<head>
	<title><fmt:message key="page.secure.story.update.title" /></title>
</head>
<body>

	<h1><fmt:message key="page.secure.story.update.title" /></h1>
	<c:url value="/secure/story/update" var="formAction" />
	<form:form commandName="story" action="${formAction}">
		<form:hidden path="id" />
		<form:hidden path="project.id" />
		<table>
			<tbody>
				<tr class="even prop">
					<td class="name"><fmt:message key="story.category" /></td>
					<td class="value">
						<form:select path="category">
							<c:forEach items="<%=StoryCategory.values() %>" var="storyCategory">
								<form:option value="${storyCategory.category}">
									 <fmt:message key="StoryCategory.${storyCategory}" />
								</form:option>
							</c:forEach>
						</form:select>
					</td>
					<td><form:errors path="category" class="error" /></td>
				</tr>
				<tr class="odd prop">
					<td class="name"><fmt:message key="story.label" /></td>
					<td class="value">
						<form:input path="label" />
					</td>
					<td><form:errors path="label" class="error" /></td>
				</tr>
				<tr class="even prop">
					<td class="name"><fmt:message key="story.description" /></td>
					<td class="value"><form:textarea path="description" /></td>
					<td><form:errors path="description" class="error" /></td>
				</tr>
				<tr class="odd prop">
					<td class="name"><fmt:message key="story.priority" /></td>
					<td class="value"><form:input path="priority" /></td>
					<td><form:errors path="priority" class="error" /></td>
				</tr>
				<tr class="even prop">
					<td class="name"><fmt:message key="story.status" /></td>
					<td class="value">
						<form:select path="status">
							<c:forEach items="<%=StoryStatus.values() %>" var="storyStatus">
								<form:option value="${storyStatus.status}">
									 <fmt:message key="StoryStatus.${storyStatus}" />
								</form:option>
							</c:forEach>
						</form:select>
					</td>
					<td><form:errors path="status" class="error" /></td>
				</tr>
				<tr class="odd prop">
					<td class="name"><fmt:message key="story.startDate" /></td>
					<td class="value">
						<form:input id="startDate" path="startDate" />
						<script type="text/javascript">
							$(function() {
								$("#startDate").datepicker();
							});
						</script>	
					</td>
					<td><form:errors path="startDate" class="error" /></td>
				</tr>
				<tr class="even prop">
					<td class="name"><fmt:message key="story.completionDate" /></td>
					<td class="value">
						<form:input id="completionDate" path="completionDate" />
						<script type="text/javascript">
							$(function() {
								$("#completionDate").datepicker();
							});
						</script>	
					</td>
					<td><form:errors path="completionDate" class="error" /></td>
				</tr>
			</tbody>
		</table>
		<div class="buttons">
			<input type="submit" value="<fmt:message key='form.submit' />" />
		</div>
	</form:form>

</body>
</html>