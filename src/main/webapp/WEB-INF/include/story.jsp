<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="messages" />

<%@ page import="com.bouncytracker.domain.model.StoryStatus" %>
<%
	pageContext.setAttribute("statusCreated", StoryStatus.CREATED.getStatus());
	pageContext.setAttribute("statusStarted", StoryStatus.STARTED.getStatus());
	pageContext.setAttribute("statusCompleted", StoryStatus.COMPLETED.getStatus());
%>
					
<div id="${param.storyId}" class="story">
	<div class="story-header storyCategory-${param.storyCategory} handle">
		<div>
			<span class="label">${param.storyLabel}</span>
			<a class="show-action-link" style="cursor: pointer;">
				+ <fmt:message key="StoryCategory.${param.storyCategory}" />
			</a>
		</div>	
		<div class="actions-panel">							
			<ul class="actions">
				<li><input type="button" onclick="updateStory('${param.storyId}');" value="<fmt:message key='action.update' />" /></li>
				<c:if test="${param.storyStatus == statusCreated}">
					<li><input type="button" onclick="startStory('${param.storyId}');" value="<fmt:message key='action.start' />" /></li>
				</c:if>
				<c:if test="${param.storyStatus == statusStarted}">
					<li><input type="button" onclick="completeStory('${param.storyId}');" value="<fmt:message key='action.complete' />" /></li>
				</c:if>
				<li><input type="button" onclick="deleteStory('${param.storyId}');" value="<fmt:message key='action.delete' />" /></li>
			</ul>
		</div>
	</div>
	<div class="story-body">
		<c:forTokens items="${param.storyDescription}" delims='<%="\n"%>' var="line">
			<c:out value="${line}" /><br />
		</c:forTokens>							
	</div>
</div>
