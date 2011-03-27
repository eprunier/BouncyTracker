<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:bundle basename="messages" />

<html>
<head>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/index.css' />" />
</head>
<body>
	<div id="login">
		<div id="login-error">
			<span class="error"><fmt:message key="page.loginFailure.loginError" /></span>		
		</div>
		<jsp:include page="../include/loginForm.jsp" />
	</div>
</body>
</html>
