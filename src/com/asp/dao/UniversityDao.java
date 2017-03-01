package com.asp.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("universityDao")
public class UniversityDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	public boolean exists(String email) {
		Criteria crit = session().createCriteria(University.class);
		crit.add(Restrictions.idEq(email));
		University university = (University) crit.uniqueResult();
		return university != null;
	}
}
