package com.asp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asp.dao.Comments;
import com.asp.dao.Likes;
import com.asp.dao.Posts;
import com.asp.dao.PostsDao;

@Service("postService")
public class PostService {
	
	@Autowired
	private PostsDao postsdao;
	
	public void createPost(Posts post) {
		postsdao.createPost(post);
	}

	public List<Posts> getAllPosts() {
		return postsdao.getAllPosts();
	}

	public List<Posts> getProfilePosts(String username) {
			return postsdao.getProfilePosts(username);
	}

	public void updateLikes(Likes likes) {
		postsdao.updateLikes(likes);
	}

	public List<Likes> getLikesInfo(int postID) {
		return postsdao.getLikesInfo(postID);
		 
	}

	public boolean checkLikers(Likes likes) {
		return postsdao.checkLikers(likes);
	}

	public Posts getPost(int postID) {
		return postsdao.getPost(postID);
	}

	public void doComment(Comments comments) {
		postsdao.doComment(comments);
	}

	public List<Comments> getComments(int postid) {
		return postsdao.getComments(postid);
	}

	public List<Comments> checkIfCommentsExists(int postid) {
		return postsdao.checkIfCommentsExists(postid);
	}
	


}
