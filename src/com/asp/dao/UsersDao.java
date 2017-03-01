package com.asp.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("usersDao")
public class UsersDao {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void create(Users user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
	}

	public boolean exists(String username) {
		Criteria crit = session().createCriteria(Users.class);
		crit.add(Restrictions.idEq(username));
		Users user = (Users) crit.uniqueResult();
		return user != null;
	}
	
	public boolean existEmail(String email) {
		String hql = "select user from Users user where user.email = :email";
		return (Users) session().createQuery(hql).setString("email", email).uniqueResult() != null;
	}
	
	public Users getUserWithEmail(String email) {
		String hql = "select user from Users user where user.email = :email";
		return (Users) session().createQuery(hql).setString("email", email).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Users> getUserDetails(String username) {
		Criteria crit = session().createCriteria(Users.class);

		crit.add(Restrictions.eq("username", username));

		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Users> getAllUsers() {
		Criteria crit = session().createCriteria(Users.class);

		return crit.list();
	}
	
	public Users getUser(String username) {
		Criteria crit = session().createCriteria(Users.class);
		crit.add(Restrictions.idEq(username));
		return (Users)crit.uniqueResult();
	}

	public void resetPassword(String username, String password) {
		String encodedPassword = passwordEncoder.encode(password);
		String hql = "UPDATE Users set password = :encodedPassword WHERE username = :username";
		Query query = session().createQuery(hql);
		query.setParameter("encodedPassword", encodedPassword);
		query.setParameter("username", username);
		query.executeUpdate();
	}

	public Users getUsernameWithFullname(String receivername) {
		String hql = "select user from Users user where user.fullname = :fullname";
		return (Users) session().createQuery(hql).setString("fullname", receivername).uniqueResult();
	}



}
