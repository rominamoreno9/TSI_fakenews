package com.fakenews.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fakenews.datatypes.DTMecanismoVerificacion;

@Entity
@NamedQueries({
    @NamedQuery(name = MecanismoVerificacion.getMecanismosVerificacionHabilitados, 
    		query = "SELECT a FROM MecanismoVerificacion a WHERE a.habilitado = TRUE")})
public abstract class MecanismoVerificacion implements Serializable {
	
	public final static String getMecanismosVerificacionHabilitados = "MecanismoVerificacion.getMecanismosVerificacionHabilitados";
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Basic
	private String descripcion;
	
	@Basic
	private Boolean habilitado;
	
	public MecanismoVerificacion() {
		
	}

	public MecanismoVerificacion(Long id, String descripcion, Boolean habilitado) {
		this.id = id;
		this.descripcion = descripcion;
		this.habilitado = habilitado;
	}
	
	public MecanismoVerificacion(String descripcion, Boolean habilitado) {
		this.descripcion = descripcion;
		this.habilitado = habilitado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((habilitado == null) ? 0 : habilitado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		MecanismoVerificacion other = (MecanismoVerificacion) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (habilitado == null) {
			if (other.habilitado != null)
				return false;
		} else if (!habilitado.equals(other.habilitado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
