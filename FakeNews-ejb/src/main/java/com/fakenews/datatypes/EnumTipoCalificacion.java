package com.fakenews.datatypes;

public enum EnumTipoCalificacion {
	VERDADERO("Verdadero"),
    VERD_A_MEDIAS("Verdadero a medias"),
    INFLADO("Inflado"),
    ENGANOSO("Engañoso"),
    FALSO("Falso"),
    RIDICULO("Ridículo"),
	ASYNC("async"),
	ERROR("Error");
   
    private String tipoCalificacionStr;
    
    EnumTipoCalificacion(String tipoCalificacionStr){
        this.tipoCalificacionStr = tipoCalificacionStr;
    }
    
    public String tipoCalificacionStr() {
        return tipoCalificacionStr;
    }
}
