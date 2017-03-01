package com.asp.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "likes")
public class Likes {
	
	@Id
	@GeneratedValue
	@Column(name = "likesid")
	private int likesid;
	@Column(name = "postid")
	private int postid;
	@Column(name = "likername")
	private String likername;
	
	public Likes() {

	}

	public Likes(int postid, String likername) {
		this.postid = postid;
		this.likername = likername;
	}

	public int getLikesid() {
		return likesid;
	}

	public void setLikesid(int likesid) {
		this.likesid = likesid;
	}

	public int getPostid() {
		return postid;
	}

	public void setPostid(int postid) {
		this.postid = postid;
	}

	public String getLikername() {
		return likername;
	}

	public void setLikername(String likername) {
		this.likername = likername;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((likername == null) ? 0 : likername.hashCode());
		result = prime * result + likesid;
		result = prime * result + postid;
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
		Likes other = (Likes) obj;
		if (likername == null) {
			if (other.likername != null)
				return false;
		} else if (!likername.equals(other.likername))
			return false;
		if (likesid != other.likesid)
			return false;
		if (postid != other.postid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Likes [likesid=" + likesid + ", postid=" + postid + ", likername=" + likername + "]";
	}
	
	
	
	
	

}
