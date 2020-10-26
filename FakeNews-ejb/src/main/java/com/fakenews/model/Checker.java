package com.fakenews.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public class Checker extends Usuario implements Serializable {
	
	@Basic
	private String password;

	public Checker() {
		
	}

	public Checker(String email, String nickname, String telefono, String nombre, String password) {
		super(email, nickname, telefono, nombre);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
		
	
}
