<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<form action="<%=request.getContextPath()%>/processLogin" method="post">
	<table id="login-table">
		<tr class="prop">
			<td class="name">
				<label for="j_username"><fmt:message key="user.email" /></label>
			</td>
			<td class="value">
				<input id="j_username" type="text" name="j_username" />
			</td>
		</tr>
		<tr class="prop">
			<td class="name">
				<label for="j_password"><fmt:message key="user.password" /></label>
			</td>
			<td class="value">
				<input id="j_password" type="password" name="j_password" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" id="signin" value="<fmt:message key="page.index.signin" />" />
				<div id="remember-me">
					<input type="checkbox" name="_spring_security_remember_me"><fmt:message key="page.index.rememberme" />
				</div>
			</td>
		</tr>
	</table>
</form>

<script type="text/javascript">
	$(document).ready(function(){
		$("#j_username").focus();
	});	
</script>	
