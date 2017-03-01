<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%> 
<c:if test="${param.emailSendingFailed!=null}">
		<p class="error">Email Sending Failed. Please try again!!!</p>
	</c:if>
	<sf:form method="post" action="${pageContext.request.contextPath}/sendUsernameToEmail" commandName="university">
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