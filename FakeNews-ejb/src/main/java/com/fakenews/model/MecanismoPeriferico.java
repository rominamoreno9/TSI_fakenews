package com.fakenews.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = MecanismoPeriferico.getByUsuario, 
    		query = "SELECT a FROM MecanismoPeriferico a WHERE a.usuario = :username")})
public class MecanismoPeriferico extends MecanismoVerificacion implements Serializable {
	
	public final static String getByUsuario = "MecanismoPeriferico.getByUsuario";
	
	@Basic
	private String usuario;
	
	@Basic
	private String password;
	
	public MecanismoPeriferico() {
		
	}

	public MecanismoPeriferico(String descripcion, Boolean habilitado, String usuario, String password) {
		super(descripcion, habilitado);
		this.usuario = usuario;
		this.password = password;
	}
	
	public MecanismoPeriferico(Long id, String descripcion, Boolean habilitado, String usuario, String password) {
		super(id, descripcion, habilitado);
		this.usuario = usuario;
		this.password = password;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		MecanismoPeriferico other = (MecanismoPeriferico) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
}
