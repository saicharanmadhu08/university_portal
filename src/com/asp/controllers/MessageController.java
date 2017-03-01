package com.asp.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asp.dao.Messages;
import com.asp.dao.Users;
import com.asp.services.MessageService;
import com.asp.services.UserService;

@Controller
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailSender mailSender;
	
	
	
	private Messages messages;
	@RequestMapping(value="/sendMessage", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> sendMessage(Principal principal, @RequestBody Map<String, Object> data){
		
		String receivername = (String)data.get("receivername");
		String username= principal.getName();
		String subject = (String)data.get("subject");
		String message = (String)data.get("messageContent");
		
		String receiverUsername = userService.getUsernameWithFullname(receivername).getUsername();
		messages = new Messages(username, receivername, subject, message);
		messageService.sendMessage(messages);
		
		String phone = userService.getUser(receiverUsername).getPhone();
		String carrier = userService.getUser(receiverUsername).getCarrier();
		
		String receivingEmail;
		
			if(carrier.equalsIgnoreCase("at&t")){
				receivingEmail = phone+"@txt.att.net";
			}else if(carrier.equalsIgnoreCase("tmobile")){
				receivingEmail = phone+"@tmomail.net";
			}else if(carrier.equalsIgnoreCase("verizon")){
				receivingEmail = phone+"@vtext.com";
			}else if(carrier.equalsIgnoreCase("sprint")){
				receivingEmail = phone+"@messaging.sprintpcs.com";
			}else if(carrier.equalsIgnoreCase("virginmobile")){
				receivingEmail = phone+"@vmobl.com";
			}else if(carrier.equalsIgnoreCase("tracfone")){
				receivingEmail = phone+"@mmst5.tracfone.com";
			}else if(carrier.equalsIgnoreCase("metropcs")){
				receivingEmail = phone+"@mymetropcs.com";
			}else{
				receivingEmail = phone+"@txt.att.net";
			}
			
			
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setFrom("saicharan9177@gmail.com");
			mail.setTo(receivingEmail);
			mail.setSubject(subject);
			mail.setText(message);
			
			try {
				mailSender.send(mail);
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Can't send message");
			}
		
		Map<String, Object> success = new HashMap<String, Object>();
		data.put("Data", "Message Sent");

		return success;
	}
	
	
	@RequestMapping(value = "/getSentMessages", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getSentMessages(Principal principal){
		String username = principal.getName();
		List<Messages> showSentMessages = null;
		showSentMessages = messageService.getSentMessages(username);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("showSentMessages", showSentMessages);
		return data;
	}
	
	@RequestMapping(value = "/getInboxMessages", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getInboxMessages(Principal principal){
		String username =  principal.getName();
		Users users = userService.getUser(username);
		String receivername = users.getFullname();		
		
		List<Messages> showInboxMessages = null;
		showInboxMessages = messageService.getInboxMessages(receivername);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("showInboxMessages", showInboxMessages);
		return data;
	}
	
	
}
