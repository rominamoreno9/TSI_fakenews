package com.fakenews.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public class MecanismoExterno extends MecanismoVerificacion implements Serializable {
	
	@Basic
	private String url;
	
	public MecanismoExterno() {
		
	}

	public MecanismoExterno(String descripcion, Boolean habilitado, String url) {
		super(descripcion, habilitado);
		this.url = url;
	}
	
	public MecanismoExterno(Long id, String descripcion, Boolean habilitado, String url) {
		super(id, descripcion, habilitado);
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		MecanismoExterno other = (MecanismoExterno) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
}
