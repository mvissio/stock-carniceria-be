package com.svcg.StockCustom.enums;

public enum RolName {
	SUPERADMINISTRADOR("SUPERADMINISTRADOR"),
    ADMINISTRADOR("ADMINISTRADOR"),
    USUARIO("USUARIO");

    private final String name;


    RolName(String name) {
        this.name = name;
    }

    public static boolean existsRol(String rolStatus) {
        boolean existRol = false;
        for (RolName rolName : RolName.values()) {
            if (rolName.name.equals(rolStatus)) {
                existRol = true;
            }

        }
        return existRol;
    }
}
