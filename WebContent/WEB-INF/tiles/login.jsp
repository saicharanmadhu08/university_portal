<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%> 

<body onload='document.f.j_username.focus();'>
	<c:if test="${param.logout!=null}">
		<p class="success">SuccessFully Logged Out</p>
	</c:if>

	<h3>Enter your Username and Password to login</h3>

	<c:if test="${param.error!=null}">
		<p class="error">Login failed. Check that your username and
			password are correct.</p>
	</c:if>

	<form name='f' action='${pageContext.request.contextPath}/login' method='POST'>
		<table>
			<tr>
				
				<td><input type='text' name='username' value='' placeholder="Username"></td>
			</tr>
			<tr>
				
				<td><input type='password' name='password' placeholder="Password"/></td>
			</tr>
			<tr>
				<td><input type='checkbox' name='remember-me' />Remember me on this computer.</td>
			</tr>
			<tr>
				<td colspan='2'><button type="submit" class="btn btn-primary">Login</button></td>
			</tr>
			<tr>
				<td ><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
			</tr>
			<tr>
				<td ><a href="${pageContext.request.contextPath}/forgot">Forgot Username or Password??</a></td>
			</tr>
		</table>
	</form>
	<br>
	<br>
	<br>
		<c:if test="${param.emailSendingFailed!=null}">
		<p class="error">Email Sending Failed. Please try again!!!</p>
	</c:if>
	<sf:form method="post" action="${pageContext.request.contextPath}/sendEmail" commandName="university">
		<table>
			<tr>
				<td><sf:input type="text" class="form-control" name ="email" path ="email" placeholder="enter email"/><br>
				<div class="error"><sf:errors  path="email"></sf:errors></div></td>
			</tr>
			<tr>
				<td><button type="submit" class="btn btn-primary">Send Email</button></td>
			</tr>
		</table>
	</sf:form>




</body>