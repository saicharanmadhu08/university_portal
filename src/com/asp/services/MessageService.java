package com.asp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asp.dao.Messages;
import com.asp.dao.MessagesDao;

@Service("messageService")
public class MessageService {
	
	@Autowired
	private MessagesDao messagesDao;
	
	public void sendMessage(Messages message) {
		messagesDao.sendMessage(message);
	}

	public List<Messages> getSentMessages(String username) {
		return messagesDao.getSentMessages(username);
	}

	public List<Messages> getInboxMessages(String receiverName) {
		return messagesDao.getInboxMessages(receiverName);
	}

}
