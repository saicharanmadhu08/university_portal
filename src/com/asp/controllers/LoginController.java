package com.asp.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asp.dao.FormValidationGroup;
import com.asp.dao.University;
import com.asp.dao.Users;
import com.asp.services.UniversityService;
import com.asp.services.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService ;
	
	@Autowired
	private UniversityService universityService;
	
	@Autowired
	private MailSender mailSender;
	
	@RequestMapping("/login")
	public String showLogin(Model model) {
		model.addAttribute("university", new University());
		return "login";
	}

	@RequestMapping("/createNewaccount")
	public String createNewaccount(Model model) {
		model.addAttribute("users", new Users());
		return "createAccount";
	}
	
	
	@RequestMapping(value="/creatingAccount", method=RequestMethod.POST)
	public String creatingAccount(@Validated(value=FormValidationGroup.class)  Users user,BindingResult result) {
		
		boolean emailCheckInUsers = userService.emailExists(user.getEmail());
		if(emailCheckInUsers) {
			result.rejectValue("email", "DuplicateKey.user.username", "This email already exists!");
			return "createAccount";
		}
		if(result.hasErrors()) {
			return "createAccount";
		}
		
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);
		if(userService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username", "This username already exists!");
			return "createAccount";
		}
		
		try {
			userService.create(user);
		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username", "This username already exists!");
			return "createAccount";
		}
		
		return "accountcreated";
	}
	
	@RequestMapping("/forgot")
	public String showForgot(Model model) {
		model.addAttribute("university", new University());
		return "forgot";
	}
	
	@RequestMapping("/emailUsername")
	public String emailUsername() {
		return "emailSent";
	}
	
	@RequestMapping("/changePassword")
	public String showResetPassword(Model model) {
		model.addAttribute("users", new Users());
		return "resetpassword";
	}
	
	@RequestMapping("/loggedout")
	public String showLoggedOut(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout=true";
	}
	
	@RequestMapping(value="/sendEmail", method=RequestMethod.POST)
	public String sendEmail(@Validated(value=FormValidationGroup.class)  University university,BindingResult result){
		if(result.hasErrors()) {
			return "login";
		}
		
		boolean emailCheckInUniversity = universityService.exists(university.getEmail());
		boolean emailCheckInUsers = userService.emailExists(university.getEmail());
		if(emailCheckInUniversity){
			if(emailCheckInUsers){
				result.rejectValue("email", "DuplicateKey.university.email", "This email already exists!");
				return "login";
			}else{
				
				SimpleMailMessage mail = new SimpleMailMessage();
				mail.setFrom("saicharan9177@gmail.com");
				mail.setTo(university.getEmail());
				mail.setSubject("Creating Account");
				mail.setText("Click the link to create account. http://localhost:8080/university-portal//createNewaccount?email="+university.getEmail());
				
				try {
					mailSender.send(mail);
					return "emailSent";
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Can't send message");
					return "redirect:/login?emailSendingFailed=true";
				}

			}
		}else{
			result.rejectValue("email", "DuplicateKey.university.email", "This email doesn't match in our database!");
			return "login";
		}
	}
	
	@RequestMapping(value="/sendUsernameToEmail", method=RequestMethod.POST)
	public String getForgotUsername(@Validated(value=FormValidationGroup.class)  University university,BindingResult result){
		if(result.hasErrors()) {
			return "forgot";
		}
		boolean emailCheckInUniversity = universityService.exists(university.getEmail());
		boolean emailCheckInUsers = userService.emailExists(university.getEmail());
		if(emailCheckInUniversity){
			if(!emailCheckInUsers){
				result.rejectValue("email", "DuplicateKey.university.email", "This email doesn't exist!");
				return "forgot";
			}else{
				
				Users user = userService.getUserWithEmail(university.getEmail());
				String username= user.getUsername();
				SimpleMailMessage mail = new SimpleMailMessage();
				mail.setFrom("saicharan9177@gmail.com");
				mail.setTo(university.getEmail());
				mail.setSubject("Change Password");
				mail.setText("This is your username ->"+username +"\nClick link to reset password \nhttp://localhost:8080/university-portal/changePassword?username="+username);
				
				try {
					mailSender.send(mail);
					return "emailSent";
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Can't send message");
					return "redirect:/forgot?emailSendingFailed=true";
				}

			}
		}else{
			result.rejectValue("email", "DuplicateKey.university.email", "This email doesn't match in our database!");
			return "forgot";
		}
	}
	
	@RequestMapping(value="/resetpassword", method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> resetPassword(@RequestBody Map<String, Object> data, Principal principal){
		
		String username=(String) data.get("username");
		String password = (String) data.get("password");
		String user = principal.getName();
		userService.resetPassword(username,password);
		userService.resetPassword(user,password);
		Map<String, Object> success = new HashMap<String, Object>();
		success.put("Data", "Password Updated");
		return success;
	}
}
