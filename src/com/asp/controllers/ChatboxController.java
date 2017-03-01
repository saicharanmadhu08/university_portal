package com.asp.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asp.dao.Chatbox;
import com.asp.services.ChatboxService;

@Controller
public class ChatboxController {
	
	
	private Chatbox chatbox;
	@Autowired
	ChatboxService chatboxService;
	
	@RequestMapping(value="/sendChatText", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> sendChatText(Principal principal, @RequestBody Map<String, Object> data){
		String receiver = (String) data.get("profilename");
		String sender = principal.getName();
		String text = (String) data.get("chatText");
		
		chatbox = new Chatbox(sender, receiver, text);
		chatboxService.sendChatText(chatbox);

		Map<String, Object> success = new HashMap<String, Object>();
		data.put("Data", "Message Sent");

		return success;
	}
	
	@RequestMapping(value = "/getChatTexts", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getChatTexts(Principal principal,@RequestParam("profilename")String profilename){
		String sender = principal.getName();
		String receiver = profilename;
		List<Chatbox> showSentChatMessages = null;
		List<Chatbox> showReceivedChatMessages = null;
		showSentChatMessages = chatboxService.getSentChatTexts(sender,receiver);
		showReceivedChatMessages = chatboxService.getReceivedChatTexts(sender,receiver);
		List<Chatbox> newList = new ArrayList<Chatbox>(showSentChatMessages);
		newList.addAll(showReceivedChatMessages);
		Collections.sort(newList, new Comparator<Chatbox>() {

			@Override
			public int compare(Chatbox chat1, Chatbox chat2) {
				 if (chat1.getTextid() > chat2.getTextid())
			            return 1;
			        if (chat1.getTextid() < chat2.getTextid())
			            return -1;
			        return 0;
			}
		});
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("showAllChats",newList);
		return data;
	}
	

}
