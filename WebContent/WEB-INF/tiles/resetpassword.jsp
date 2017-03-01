<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div id="resetPassword" >
	<table>
		<tr>
			<td>username</td>
			<td><input type="text" id ="username"  name="username" disabled="disabled" /></td>
		</tr>
		<tr>
			<td>password</td>
			<td><input id="password" type="password"  name="password" /><br>
			<div class="error"><sf:errors  path="password"></sf:errors></div></td>
		</tr>
		<tr>
			<td>confirm password</td>
			<td><input id="confirmpass" type="password" /><br>
			<div id="matchpass" class="error"></div></td>
		<tr>
			<td><input type="submit" id="changePassword" value="Change Password" onclick="resetpassword();"/></td>
		</tr>
		<tr>
			<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
		</tr>
	</table>

</div>
<div id="successUpdate" hidden="true">
	<p><a href="http://localhost:8080/university-portal/login">Click here</a> to login
</div>
<script type="text/javascript">
	function onLoad() {

		$("#password").keyup(checkPasswordsMatch);
		$("#confirmpass").keyup(checkPasswordsMatch);
		$("#username").val(username);
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
				$("#changePassword").attr("disabled",disabled);
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
	
	function resetpassword(){
		var password = $("#password").val();
		var passwordLength = password.length;
		if(passwordLength==''){
			$("#matchpass").text("Password cannot be blank");
		}else if(passwordLength<8||passwordLength>15){
			$("#matchpass").text("Password length should be between 8 and 15");
		}else if(/^\\w{8,}$/.test(password)){
			$("#matchpass").text("Username can only consist of numbers, letters and the underscore character.");
		}else{
			$.ajax({
				type: 'POST',
				contentType: 'application/json',
				url: "http://localhost:8080/university-portal/resetpassword",
				data: JSON.stringify({"password": password,"username":username}),
				success: function (success) 
		        { onSuccess() },
				error: function (err)
				{ onFailure()}
				
			});
		}
		
	}
	
	function onSuccess(){
		$("#resetPassword").hide();
		$("#successUpdate").show();
		
	}
	function onFailure(){
		alert("Failed to update");
	}
	var username = getUrlParameter('username');
	$(document).ready(onLoad);
</script>
