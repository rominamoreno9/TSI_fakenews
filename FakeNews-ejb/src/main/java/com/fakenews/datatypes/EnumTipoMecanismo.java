package com.fakenews.datatypes;

public enum EnumTipoMecanismo {
	
	INTERNO("Interno"),
    EXTERNO("Externo"),
    PERIFERICO("Periferico");
    
    private String tipoMecanismoStr;
    
    EnumTipoMecanismo(String tipoMecanismoStr){
        this.tipoMecanismoStr = tipoMecanismoStr;
    }
    
    public String tipoMecanismoStr() {
        return tipoMecanismoStr;
    }
}
