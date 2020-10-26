package com.fakenews.datatypes;

import java.io.Serializable;

import com.fakenews.model.MecanismoExterno;
import com.fakenews.model.MecanismoInterno;
import com.fakenews.model.MecanismoPeriferico;

public class DTMecanismoVerificacion implements Serializable{
	
	private Long id;
	
	private String descripcion;
	
	private Boolean habilitado;
	
	private String usuario;
	
	private String password;
	
	private String url;
	
	private String codigoInterno;
	
	private EnumTipoMecanismo mecanismo;
	
	public DTMecanismoVerificacion(Long id, String descripcion, Boolean habilitado, String usuario, String password,
			String url, EnumTipoMecanismo mecanismo) {
		this.id = id;
		this.descripcion = descripcion;
		this.habilitado = habilitado;
		this.usuario = usuario;
		this.password = password;
		this.url = url;
		this.mecanismo = mecanismo;
	}

	public DTMecanismoVerificacion(String descripcion, Boolean habilitado, String usuario, String password, String url,
			EnumTipoMecanismo mecanismo) {
		this.descripcion = descripcion;
		this.habilitado = habilitado;
		this.usuario = usuario;
		this.password = password;
		this.url = url;
		this.mecanismo = mecanismo;
	}
	
	public DTMecanismoVerificacion(MecanismoPeriferico mecanismo) {
		System.out.println("DTMecanismo Verificacion periferico");
		this.id = mecanismo.getId();
		this.descripcion = mecanismo.getDescripcion();
		this.habilitado = mecanismo.getHabilitado();
		this.usuario = mecanismo.getUsuario();
		this.password = mecanismo.getPassword();
		this.mecanismo = EnumTipoMecanismo.PERIFERICO;
	}
	
	public DTMecanismoVerificacion(MecanismoExterno mecanismo) {
		this.id = mecanismo.getId();
		this.descripcion = mecanismo.getDescripcion();
		this.habilitado = mecanismo.getHabilitado();
		this.url = mecanismo.getUrl();
		this.mecanismo = EnumTipoMecanismo.EXTERNO;
	}

	public DTMecanismoVerificacion(MecanismoInterno mecanismo) {
		this.id = mecanismo.getId();
		this.descripcion = mecanismo.getDescripcion();
		this.habilitado = mecanismo.getHabilitado();
		this.codigoInterno = mecanismo.getCodigoInterno();
		this.mecanismo = EnumTipoMecanismo.INTERNO;
	}

	public DTMecanismoVerificacion() {
		
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public EnumTipoMecanismo getMecanismo() {
		return mecanismo;
	}

	public void setMecanismo(EnumTipoMecanismo mecanismo) {
		this.mecanismo = mecanismo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}
			
}
