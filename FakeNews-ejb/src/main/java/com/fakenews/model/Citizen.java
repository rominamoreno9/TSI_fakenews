package com.fakenews.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name = Citizen.getSuscriptedCitizens, 
    		query = "SELECT a FROM Citizen a WHERE a.suscripto = TRUE")})
public class Citizen extends Usuario implements Serializable {
	
	public final static String getSuscriptedCitizens = "Citizen.getSuscriptedCitizens";
	
	@Basic
	private Boolean suscripto;
	
	@Basic
	private String deviceToken;
	
	@OneToMany(targetEntity = Hecho.class, fetch = FetchType.LAZY)
	private List<Hecho> hechos; 
	
	public Citizen() {
		
	}

	public Citizen(String email, String nickname, String telefono, String nombre, Boolean suscripto) {
		super(email, nickname, telefono,nombre);
		this.suscripto = suscripto;
	}
	
	public Citizen(String email, String nickname, String telefono, String nombre, 
			Boolean suscripto, String deviceToken) {
		super(email, nickname, telefono,nombre);
		this.suscripto = suscripto;
		this.deviceToken = deviceToken;
	}
	
	public Citizen(String email, String nombre) {
		super(email, email, "", nombre);
		this.suscripto = false;
	}
	
	public Citizen(String email, String nombre, String deviceToken) {
		super(email, email, "", nombre);
		this.suscripto = false;
		this.deviceToken = deviceToken;
	}

	public Boolean getSuscripto() {
		return suscripto;
	}

	public void setSuscripto(Boolean suscripto) {
		this.suscripto = suscripto;
	}

	public List<Hecho> getHechos() {
		return hechos;
	}

	public void setHechos(List<Hecho> hechos) {
		this.hechos = hechos;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
			
}
