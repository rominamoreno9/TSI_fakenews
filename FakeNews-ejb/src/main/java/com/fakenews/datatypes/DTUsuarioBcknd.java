package com.fakenews.datatypes;

import java.io.Serializable;

import javax.persistence.Basic;

public class DTUsuarioBcknd implements Serializable{
	
	private String email;

	private String nickname;

    private String telefono;
    
    private String nombre;
    
    private String password;
    
    private EnumRoles rol;

	public DTUsuarioBcknd(String email, String nickname, String telefono, String nombre, String password) {
		this.email = email;
		this.nickname = nickname;
		this.telefono = telefono;
		this.nombre	  = nombre;
		this.password = password;
	}

	public DTUsuarioBcknd() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EnumRoles getRol() {
		return rol;
	}

	public void setRol(EnumRoles rol) {
		this.rol = rol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
