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
@Component("messagesDao")
public class MessagesDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	public void sendMessage(Messages message) {
		session().save(message);
	}

	@SuppressWarnings("unchecked")
	public List<Messages> getSentMessages(String username) {
		Criteria crit = session().createCriteria(Messages.class);
		crit.add(Restrictions.eq("username", username));
		crit.addOrder(Order.desc("messageID"));	
		return crit.list();
		
	}

	@SuppressWarnings("unchecked")
	public List<Messages> getInboxMessages(String receivername) {
		Criteria crit = session().createCriteria(Messages.class);
		crit.add(Restrictions.eq("receivername", receivername));
		crit.addOrder(Order.desc("messageID"));	
		return crit.list();
	}

}
