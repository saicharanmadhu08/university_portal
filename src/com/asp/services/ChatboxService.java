package com.asp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asp.dao.Chatbox;
import com.asp.dao.ChatboxDao;

@Service("chatboxService")
public class ChatboxService {
	
	@Autowired
	private ChatboxDao chatboxDao;
	
	public void sendChatText(Chatbox chatbox) {
		chatboxDao.sendChatText(chatbox);
	}

	public List<Chatbox> getSentChatTexts(String sender, String receiver) {
			return chatboxDao.getSentChatTexts(sender,receiver);
	}

	public List<Chatbox> getReceivedChatTexts(String sender, String receiver) {
		String sendertemp = receiver;
		String receivertemp = sender;
		return chatboxDao.getReceivedChatTexts(sendertemp,receivertemp);
	}

}
