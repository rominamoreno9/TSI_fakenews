package com.fakenews.datatypes;

import java.io.Serializable;

public class DTCantHechosEstado implements Serializable{
	
	private String estado;
	private int cantidad;
	
	
	public DTCantHechosEstado() {
		
	}


	public DTCantHechosEstado(String estado, int cantidad) {
		this.estado = estado;
		this.cantidad = cantidad;
	}
	
	public DTCantHechosEstado(EnumHechoEstado estado, int cantidad) {
		this.estado = estado.estadoStr();
		this.cantidad = cantidad;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
