package com.fakenews.datatypes;

import java.io.Serializable;

public class DTLoginCitizenRequest implements Serializable{
	
	private String mail;
	private String token_id;
	private String token_firebase;
	
	public DTLoginCitizenRequest() {
		
	}

	public DTLoginCitizenRequest(String mail, String token_id) {
		this.mail = mail;
		this.token_id = token_id;
	}
	
	public DTLoginCitizenRequest(String mail, String token_id, String token_firebase) {
		this.mail = mail;
		this.token_id = token_id;
		this.token_firebase = token_firebase;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getToken_id() {
		return token_id;
	}

	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}

	public String getToken_firebase() {
		return token_firebase;
	}

	public void setToken_firebase(String token_firebase) {
		this.token_firebase = token_firebase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((token_id == null) ? 0 : token_id.hashCode());
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
		DTLoginCitizenRequest other = (DTLoginCitizenRequest) obj;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (token_id == null) {
			if (other.token_id != null)
				return false;
		} else if (!token_id.equals(other.token_id))
			return false;
		return true;
	}
	
}
