package com.asp.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class Posts {
	@Id
	@GeneratedValue
	@Column(name = "postid")
	private int postID;
	@Column(name = "username")
	private String username;
	@Column(name = "text")
	private String text;
	@Column(name = "likes")
	private int likes ;
	@Column(name = "shares")
	private int shares;
	@Column(name="category")
	private String category;

	public Posts() {

	}
	
	public Posts(String username, String text) {
		this.username = username;
		this.text = text;
	}

	public Posts(String username, String text, String category) {
		this.username = username;
		this.text = text;
		this.category = category;
	}

	public int getPostID() {
		return postID;
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getShares() {
		return shares;
	}

	public void setShares(int shares) {
		this.shares = shares;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + likes;
		result = prime * result + postID;
		result = prime * result + shares;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posts other = (Posts) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (likes != other.likes)
			return false;
		if (postID != other.postID)
			return false;
		if (shares != other.shares)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Posts [postID=" + postID + ", username=" + username + ", text=" + text + ", likes=" + likes
				+ ", shares=" + shares + ", category=" + category + "]";
	}
	
	

		
}
