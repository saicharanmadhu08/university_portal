package com.asp.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="messages")
public class Messages {
	
	@Id
    @GeneratedValue
    @Column(name = "messageid")
	private int messageID;
	@Column(name = "username")
	private String username;
	@Column(name = "receivername")
	private String receivername;
	@Column(name = "subject")
	private String subject;
	@Column(name = "message")
	private String message;
	
	public Messages() {

	}

	public Messages(String username, String receiverName, String subject, String message) {
		this.username = username;
		this.receivername = receiverName;
		this.subject = subject;
		this.message = message;
	}

	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReceiverName() {
		return receivername;
	}

	public void setReceiverName(String receiverName) {
		this.receivername = receiverName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + messageID;
		result = prime * result + ((receivername == null) ? 0 : receivername.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		Messages other = (Messages) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (messageID != other.messageID)
			return false;
		if (receivername == null) {
			if (other.receivername != null)
				return false;
		} else if (!receivername.equals(other.receivername))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
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
		return "Messages [messageID=" + messageID + ", username=" + username + ", receiverName=" + receivername
				+ ", subject=" + subject + ", message=" + message + "]";
	}
	
	
	

}
