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
@Component("postsDao")
public class PostsDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	public void createPost(Posts post) {
		session().save(post);
	}

	@SuppressWarnings("unchecked")
	public List<Posts> getAllPosts() {
		Criteria crit = session().createCriteria(Posts.class);
		crit.addOrder(Order.desc("postID"));		
		return crit.list();
	}

	

	@SuppressWarnings("unchecked")
	public List<Posts> getProfilePosts(String username) {
		Criteria crit = session().createCriteria(Posts.class);
		crit.add(Restrictions.eq("username", username));
		crit.addOrder(Order.desc("postID"));	
		return crit.list();
	}

	public void updateLikes(Likes likes) {
		session().save(likes);
	}

	@SuppressWarnings("unchecked")
	public List<Likes> getLikesInfo(int postid) {
		Criteria crit = session().createCriteria(Likes.class);
		crit.add(Restrictions.eq("postid", postid));
		crit.addOrder(Order.desc("likesid"));	
		return crit.list();
	}

	public boolean checkLikers(Likes likes) {
		Criteria crit = session().createCriteria(Likes.class);
		crit.add(Restrictions.eq("likername", likes.getLikername()));
		crit.add(Restrictions.eq("postid", likes.getPostid()));
		Likes like = (Likes) crit.uniqueResult();
		return like != null;
	}

	public Posts getPost(int postID) {
		Criteria crit = session().createCriteria(Posts.class);
		crit.add(Restrictions.idEq(postID));
		Posts post = (Posts) crit.uniqueResult();
		return post;
	}

	public void doComment(Comments comments) {
		session().save(comments);		
	}

	@SuppressWarnings("unchecked")
	public List<Comments> getComments(int postid) {
		Criteria crit = session().createCriteria(Comments.class);
		crit.add(Restrictions.eq("postid", postid));
		crit.addOrder(Order.asc("commentsid"));	
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Comments> checkIfCommentsExists(int postid) {
		Criteria crit = session().createCriteria(Comments.class);
		crit.add(Restrictions.eq("postid", postid));
		crit.setMaxResults(1);
		List<Comments> results = crit.list();
		return results;	
		
	}



}
