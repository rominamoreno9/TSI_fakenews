package com.fakenews.datatypes;

public enum EnumRoles {
	
	CITIZEN("Citizen"),
    CHECKER("Checker"),
    SUBMITTER("Submitter"),
    ADMIN("Admin"),
	ERROR("Error");
  
    private String rolStr;
    
    EnumRoles(String rolStr){
        this.rolStr = rolStr;
    }
    
    public String rolStr() {
        return rolStr;
    }
}
