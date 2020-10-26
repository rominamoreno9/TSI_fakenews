package com.fakenews.datatypes;

public enum EnumHechoEstado {
	
	A_COMPROBAR("A comprobar"),
    NUEVO("Nuevo"),
    EN_PROCESO("En proceso"),
    VERIFICADO("Verificado"),
    PUBLICADO("Publicado"),
    CANCELADO("Cancelado");
   
    private String estadoStr;
    
    EnumHechoEstado(String estadoStr){
        this.estadoStr = estadoStr;
    }
    
    public String estadoStr() {
        return estadoStr;
    }
}
