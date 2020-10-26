package com.fakenews.datatypes;

import java.io.Serializable;

public class DTHechoMecanismo implements Serializable {
	
	private Long idHecho;
	
	private Long idMecanismoVerificacion;
	
	private String codigoInterno;
	
	private EnumTipoCalificacion calificacion;

	public DTHechoMecanismo() {
		
	}

	public DTHechoMecanismo(Long idHecho, Long idMecanismoVerificacion) {
		this.idHecho = idHecho;
		this.idMecanismoVerificacion = idMecanismoVerificacion;
	}

	public DTHechoMecanismo(Long idHecho, Long idMecanismoVerificacion, String codigoInterno) {
		this.idHecho = idHecho;
		this.idMecanismoVerificacion = idMecanismoVerificacion;
		this.codigoInterno = codigoInterno;
	}

	public DTHechoMecanismo(Long idHecho, Long idMecanismoVerificacion, EnumTipoCalificacion calificacion) {
		this.idHecho = idHecho;
		this.idMecanismoVerificacion = idMecanismoVerificacion;
		this.calificacion = calificacion;
	}

	public DTHechoMecanismo(Long idHecho, Long idMecanismoVerificacion, String codigoInterno,
			EnumTipoCalificacion calificacion) {
		this.idHecho = idHecho;
		this.idMecanismoVerificacion = idMecanismoVerificacion;
		this.codigoInterno = codigoInterno;
		this.calificacion = calificacion;
	}

	public Long getIdHecho() {
		return idHecho;
	}

	public void setIdHecho(Long idHecho) {
		this.idHecho = idHecho;
	}

	public Long getIdMecanismoVerificacion() {
		return idMecanismoVerificacion;
	}

	public void setIdMecanismoVerificacion(Long idMecanismoVerificacion) {
		this.idMecanismoVerificacion = idMecanismoVerificacion;
	}

	public EnumTipoCalificacion getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(EnumTipoCalificacion calificacion) {
		this.calificacion = calificacion;
	}

	public String getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}
	
}
