package com.fakenews.datatypes;

import java.io.Serializable;
import java.util.List;

import com.fakenews.model.Hecho;

public class DTHechosPag implements Serializable{
	
	private List <Hecho> hechos;
	
	private int cantElementos;

	public DTHechosPag() {
	
	}

	public DTHechosPag(List<Hecho> hechos, int cantElementos) {
		this.hechos = hechos;
		this.cantElementos = cantElementos;
	}

	public List<Hecho> getHechos() {
		return hechos;
	}

	public void setHechos(List<Hecho> hechos) {
		this.hechos = hechos;
	}

	public int getCantElementos() {
		return cantElementos;
	}

	public void setCantElementos(int cantElementos) {
		this.cantElementos = cantElementos;
	}
	
}
