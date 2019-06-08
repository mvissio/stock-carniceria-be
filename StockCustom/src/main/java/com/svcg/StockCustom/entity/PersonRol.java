package com.svcg.StockCustom.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "person_rol")
public class PersonRol implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "fk_Person", nullable = false)
    private Person person;

    @Id
    @ManyToOne
    @JoinColumn(name = "fk_roles", nullable = false)
    private Rol rol;

    @Column(name = "create_date")
    private Date create;

    @Column(name = "updated_date")
    private Date update;

    public PersonRol() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }
}
