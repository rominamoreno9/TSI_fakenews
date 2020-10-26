package com.fakenews.datatypes;

import java.io.Serializable;

public class DTAsignarHecho implements Serializable{
	
	private Long idHecho;
	private String mail;
	
	
	public DTAsignarHecho() {
		
	}

	public DTAsignarHecho(Long idHecho, String mail) {
		this.idHecho = idHecho;
		this.mail = mail;
	}

	public Long getIdHecho() {
		return idHecho;
	}

	public void setIdHecho(Long idHecho) {
		this.idHecho = idHecho;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
