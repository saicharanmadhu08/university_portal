package com.asp.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chatbox")
public class Chatbox {

	
	@Column(name = "sender")
	private String sender;
	@Column(name = "receiver")
	private String receiver;
	@Column(name = "text")
	private String text;
	@Id
	@GeneratedValue
	@Column(name = "textid")
	private int textid;

	public Chatbox() {
	}

	public Chatbox(String sender, String receiver, String text) {
		this.sender = sender;
		this.receiver = receiver;
		this.text = text;
	}

	public int getTextid() {
		return textid;
	}

	public void setTextid(int textid) {
		this.textid = textid;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Chatbox [textid=" + textid + ", sender=" + sender + ", receiver=" + receiver + ", text=" + text + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + textid;
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
		Chatbox other = (Chatbox) obj;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (textid != other.textid)
			return false;
		return true;
	}

}
