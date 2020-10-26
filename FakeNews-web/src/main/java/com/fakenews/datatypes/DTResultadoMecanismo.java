package com.fakenews.datatypes;

import java.io.Serializable;

public class DTResultadoMecanismo implements Serializable {
	
	private EnumTipoCalificacion resultado;	
	
	public DTResultadoMecanismo() {
	}

	public DTResultadoMecanismo(EnumTipoCalificacion resultado) {
		this.resultado = resultado;
	}

	public EnumTipoCalificacion getResultado() {
		return resultado;
	}

	public void setResultado(EnumTipoCalificacion resultado) {
		this.resultado = resultado;
	}
	
	
}
