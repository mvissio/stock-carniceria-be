package com.svcg.StockCustom.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long rolId;

    @NotEmpty
    @Size(max = 45, message = "{validation.rol.username.size}")
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    public Rol() {
    }

    public Rol(String name) {
        this.name = name;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
