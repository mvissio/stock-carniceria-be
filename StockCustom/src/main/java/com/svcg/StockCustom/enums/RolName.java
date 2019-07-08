package com.svcg.StockCustom.enums;

public enum RolName {
	SYSADMIN("SYSADMIN"),
	ADMIN("ADMIN"),
    USER("USER");

    private final String name;


    RolName(String name) {
        this.name = name;
    }

    public static Boolean existsRol(String rolStatus) {
        Boolean existRol = false;
        for (RolName rolName : RolName.values()) {
            if (rolName.name.equals(rolStatus)) {
                existRol = true;
            }

        }
        return existRol;
    }
}
