package com.fakenews.datatypes;

import java.io.Serializable;

public class DTCheckerCalificacion implements Serializable{
	
	private EnumTipoCalificacion calificacion;
	
	private int cantidad;

	public DTCheckerCalificacion() {

	}

	public DTCheckerCalificacion(EnumTipoCalificacion calificacion, int cantidad) {

		this.calificacion = calificacion;
		this.cantidad = cantidad;
	}

	public EnumTipoCalificacion getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(EnumTipoCalificacion calificacion) {
		this.calificacion = calificacion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
