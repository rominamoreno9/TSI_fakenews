package com.fakenews.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public class MecanismoInterno extends MecanismoVerificacion implements Serializable {
	
	@Basic
	private String codigoInterno; 
	
	public MecanismoInterno() {
		
	}

	public MecanismoInterno(String descripcion, Boolean habilitado) {
		super(descripcion, habilitado);
	}
	
	public MecanismoInterno(Long id, String descripcion, Boolean habilitado) {
		super(id, descripcion, habilitado);
	}

	public MecanismoInterno(Long id, String descripcion, Boolean habilitado, String codigoInterno) {
		super(id, descripcion, habilitado);
		this.codigoInterno = codigoInterno;
	}
	
	public MecanismoInterno(String descripcion, Boolean habilitado, String codigoInterno) {
		super(descripcion, habilitado);
		this.codigoInterno = codigoInterno;
	}

	public String getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((codigoInterno == null) ? 0 : codigoInterno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MecanismoInterno other = (MecanismoInterno) obj;
		if (codigoInterno == null) {
			if (other.codigoInterno != null)
				return false;
		} else if (!codigoInterno.equals(other.codigoInterno))
			return false;
		return true;
	}	
	
}
