package com.asp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("chatboxDao")
public class ChatboxDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	public void sendChatText(Chatbox chatbox) {
		session().save(chatbox);
	}
	@SuppressWarnings("unchecked")
	public List<Chatbox> getSentChatTexts(String sender, String receiver) {
		Criteria crit = session().createCriteria(Chatbox.class);
		crit.add(Restrictions.eq("sender", sender));
		crit.add(Restrictions.eq("receiver", receiver));
		crit.addOrder(Order.desc("textid"));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Chatbox> getReceivedChatTexts(String sendertemp, String receivertemp) {
		Criteria crit = session().createCriteria(Chatbox.class);
		crit.add(Restrictions.eq("sender", sendertemp));
		crit.add(Restrictions.eq("receiver", receivertemp));
		crit.addOrder(Order.desc("textid"));
		return crit.list();
	}

}
