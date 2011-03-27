<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="messages" />

<%@ page import="com.bouncytracker.domain.model.StoryCategory"  %>
<% 
	pageContext.setAttribute("categories", StoryCategory.getCategoryMap());
%>

<html>
<head>
	<title><fmt:message key="project" /> [${project.name}]</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/secure/project/show.css' />" />
	<script type="text/javascript" src="<c:url value='/resources/js/secure/project/show.js' />"></script>
</head>
<body>

	<div class="actions">
		<ul class="actions">
			<li><input type="button" onclick="updateProject('${project.id}');" value="<fmt:message key='action.update' />" /></li>
			<li><input type="button" onclick="deleteProject('${project.id}');" value="<fmt:message key='action.delete' />" /></li>
		</ul>
	</div>
	<h1>
		${project.name}
	</h1>
	<c:if test="${not empty project.description}">
		<div>
			<c:out value="${project.description}" />
		</div>
	</c:if>
	
	<div id="timeblock-container">
		<input type="hidden" id="projectId" value="${project.id}" />
		<div id="todo" class="timeblock">
			<h2>
				<fmt:message key="page.secure.project.show.todo" />
				<span class="action">
					[<a href="<%=request.getContextPath()%>/secure/story/create/${project.id}"><fmt:message key="page.secure.project.show.newStory" /></a>]
				</span>
			</h2>		
			<div class="sortable">
				<c:forEach items="${todo}" var="story" varStatus="status">
					<jsp:include page="../../../include/story.jsp">
						<jsp:param value="${story.id}" name="storyId" />
						<jsp:param value="${story.label}" name="storyLabel" />
						<jsp:param value="${story.description}" name="storyDescription" />
						<jsp:param value="${categories[story.category]}" name="storyCategory" />
						<jsp:param value="${story.status}" name="storyStatus" />
					</jsp:include>
				</c:forEach>
			</div>
		</div>
	
		<div id="current-iteration" class="timeblock">
			<h2><fmt:message key="page.secure.project.show.currentStories" /></h2>
			<div class="stories">
				<c:forEach items="${currentStories}" var="story" varStatus="status">
					<jsp:include page="../../../include/story.jsp">
						<jsp:param value="${story.id}" name="storyId"/>
						<jsp:param value="${story.label}" name="storyLabel" />
						<jsp:param value="${story.description}" name="storyDescription"/>
						<jsp:param value="${categories[story.category]}" name="storyCategory"/>
						<jsp:param value="${story.status}" name="storyStatus"/>
					</jsp:include>
				</c:forEach>
			</div>
		</div>
		
		<div id="iteration-completed" class="timeblock">
			<h2><fmt:message key="page.secure.project.show.completedStories" /></h2>
			<div class="stories">
				<c:forEach items="${completedStories}" var="story" varStatus="status">
					<jsp:include page="../../../include/story.jsp">
						<jsp:param value="${story.id}" name="storyId"/>
						<jsp:param value="${story.label}" name="storyLabel" />
						<jsp:param value="${story.description}" name="storyDescription"/>
						<jsp:param value="${categories[story.category]}" name="storyCategory"/>
						<jsp:param value="${story.status}" name="storyStatus"/>
					</jsp:include>
				</c:forEach>
			</div>
		</div>
		
		<br style="clear: both;" />
	</div>
	
</body>
</html>
