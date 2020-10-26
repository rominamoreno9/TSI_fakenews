package com.fakenews.datatypes;

import java.io.Serializable;

public class DTMailRequest implements Serializable {

	private String mail;

	public DTMailRequest() {
	}
	
	public DTMailRequest(String mail) {
		this.mail = mail;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
