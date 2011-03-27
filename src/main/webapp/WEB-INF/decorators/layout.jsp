<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>

<fmt:bundle basename="messages" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">
<head>
	<title><decorator:title default="BouncyTracker" /></title>
	<script type="text/javascript" src="<c:url value='/resources/jquery/jquery-1.5.1.min.js' />"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/layout.css' />" />
	<decorator:head />	
</head>
<body> 
    <div id="page-container"> 
        <div id="page-header"> 
           	<h1>BouncyTracker</h1>
        </div>
		<div id="page-nav">
			<div id="menu">
				<ul>
					<li><a href="<%=request.getContextPath()%>/"><fmt:message key="layout.menu.home" /></a></li>
					<li><a href="<%=request.getContextPath()%>/register"><fmt:message key="layout.menu.register" /></a></li>					
				</ul>
			</div>           	
        </div> 
        <div id="page-content"> 
            <decorator:body/> 
        </div> 
        <div id="page-footer"> 
        	<span>Copyright &copy; 2010 - 2011 <a href="http://bouncytracker.com">BouncyTracker.com</a></span>
        </div> 
    </div> 
</body> 
</html>
