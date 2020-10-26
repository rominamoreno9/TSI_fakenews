package com.fakenews.datatypes;

import java.io.Serializable;

public class DTLoginResponse implements Serializable{
	
	private String jwt;
	private EnumRoles rol;
	private Long idPeriferico;
	
	public DTLoginResponse() {
		
	}
	
	public DTLoginResponse(String jwt, EnumRoles rol) {
		this.jwt = jwt;
		this.rol = rol;
	}
	
	public DTLoginResponse(String jwt) {
		this.jwt = jwt;
	}

	public DTLoginResponse(String jwt, Long idPeriferico) {
		super();
		this.jwt = jwt;
		this.idPeriferico = idPeriferico;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public EnumRoles getRol() {
		return rol;
	}

	public void setRol(EnumRoles rol) {
		this.rol = rol;
	}

	public Long getIdPeriferico() {
		return idPeriferico;
	}

	public void setIdPeriferico(Long idPeriferico) {
		this.idPeriferico = idPeriferico;
	}

}
