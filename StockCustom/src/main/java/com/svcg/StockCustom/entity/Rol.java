package com.svcg.StockCustom.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Long rolId;

    @NotEmpty
    @Size(max = 45, message = "{validation.rol.username.size}")
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PersonRol> personRoles = new HashSet<>();

    public Rol() {
    }

    public Rol(String nombre) {
        this.nombre = nombre;
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<PersonRol> getPersonRoles() {
        return personRoles;
    }

    public void setPersonRoles(Set<PersonRol> personRoles) {
        this.personRoles = personRoles;
    }
}
