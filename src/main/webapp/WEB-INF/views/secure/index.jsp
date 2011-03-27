<%@ page language="java" errorPage="/WEB-INF/views/error.jsp" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="messages" />

<html>
<head>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/secure/index.css' />" />
	<script type="text/javascript" src="<c:url value='/resources/js/secure/index.js' />"></script>	
</head>
<body>

	<div>
		<div id="projects">
			<h1>
				<fmt:message key="projects" />
				<span class="action">
					[<a href="<%=request.getContextPath()%>/secure/project/create"><fmt:message key="page.secure.project.index.newProject" /></a>]
				</span>
			</h1>
		
			<c:forEach items="${projects}" var="project" varStatus="status">
			<div id="${project.id}" class="project">
				<div class="project-name">
					${project.name}
				</div>
				
				<div  class="info">
					<div>
						<fmt:message key="page.secure.index.currentStories" /> : ${fn:length(project.stories)}
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
			
		<br style="clear: both;" />
	</div>
	
</body>
</html>
