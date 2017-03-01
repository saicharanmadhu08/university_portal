<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<sf:form id="details" method="post" action="${pageContext.request.contextPath}/creatingAccount" commandName="users">
	<table>
		<tr>
			<td>Full Name</td>
			<td><sf:input type="text" path="fullname" name="fullname" /><br>
				<div class="error"><sf:errors  path="fullname"></sf:errors></div></td>
		</tr>
		<tr>
			<td>username</td>
			<td><sf:input type="text" path="username" name="username" /><br>
			<div class="error"><sf:errors  path="username"></sf:errors></div></td>
			
		</tr>
		<tr>
			<td>email</td>
			<td><sf:input type="text" path="email" id="email" name="email" /><br>
			<div class="error"><sf:errors  path="email"></sf:errors></div></td>
		</tr>
		<tr>
			<td>phone</td>
			<td><sf:input type="text" path="phone" id="phone" name="phone" /><br>
			<div class="error"><sf:errors  path="phone"></sf:errors></div></td>
		</tr>
		<tr>
			<td>carrier</td>
			<td><sf:select type="text" path="carrier" id="carrier" name="carrier" >
 				 <option value="at&t">AT&T</option>
			     <option value="tmobile">T-Mobile</option>
 				 <option value="verizon">Verizon</option>
 				 <option value="sprint">Sprint</option>
 				 <option value="virginMobile">Virgin Mobile</option>
			     <option value="tmobile">T-Mobile</option>
 				 <option value="tracfone">Tracfone</option>
 				 <option value="metropcs">Metro PCS</option>
			</sf:select><br>
			<div class="error"><sf:errors  path="carrier"></sf:errors></div></td>
		</tr>
		<tr>
			<td>password</td>
			<td><sf:input id="password" type="password" path="password" name="password" /><br>
			<div class="error"><sf:errors  path="password"></sf:errors></div></td>
		</tr>
		<tr>
			<td>confirm password</td>
			<td><input id="confirmpass" type="password" /><br>
			<div id="matchpass" class="error"></div></td>
		</tr>
		<tr>
			<td><input type="submit" value="Create Account" /></td>
		</tr>
		<tr>
			<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
		</tr>
	</table>

</sf:form>


<script type="text/javascript">
	function onLoad() {

		$("#password").keyup(checkPasswordsMatch);
		$("#confirmpass").keyup(checkPasswordsMatch);
		$("#email").val(email);
		$("#details").submit(canSubmit);
	}
	
	function canSubmit() {
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();
		
		if(password != confirmpass) {
			return false;
		}
		else {
			return true;
		}
	}

	function checkPasswordsMatch() {
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();

		if (password.length > 3 || confirmpass.length > 3) {

			if (password == confirmpass) {
				$("#matchpass").text("Passwords Matched");
				$("#matchpass").addClass("success");
				$("#matchpass").removeClass("error");
			} else {
				$("#matchpass").text("Passwords did not match");
				$("#matchpass").addClass("error");
				$("#matchpass").removeClass("success");
			}
		}
	}
	var getUrlParameter = function getUrlParameter(sParam) {
	    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
	        sURLVariables = sPageURL.split('&'),
	        sParameterName,
	        i;

	    for (i = 0; i < sURLVariables.length; i++) {
	        sParameterName = sURLVariables[i].split('=');

	        if (sParameterName[0] === sParam) {
	            return sParameterName[1] === undefined ? true : sParameterName[1];
	        }
	    }
	};
	var email = getUrlParameter('email');
	$(document).ready(onLoad);
</script>