package com.asp.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comments {
	
	
	@Id
	@GeneratedValue
	@Column(name = "commentsid")
	private int commentsid;
	@Column(name = "postid")
	private int postid;
	@Column(name = "username")
	private String username;
	@Column(name = "text")
	private String text;
	
	public Comments() {
		
	}

	public Comments(int postid, String username, String text) {
		this.postid = postid;
		this.username = username;
		this.text = text;
	}

	public int getCommentsid() {
		return commentsid;
	}

	public void setCommentsid(int commentsid) {
		this.commentsid = commentsid;
	}

	public int getPostid() {
		return postid;
	}

	public void setPostid(int postid) {
		this.postid = postid;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commentsid;
		result = prime * result + postid;
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
		Comments other = (Comments) obj;
		if (commentsid != other.commentsid)
			return false;
		if (postid != other.postid)
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
		return "Comments [commentsid=" + commentsid + ", postid=" + postid + ", username=" + username + ", text=" + text
				+ "]";
	}
	
	
	
	

}
