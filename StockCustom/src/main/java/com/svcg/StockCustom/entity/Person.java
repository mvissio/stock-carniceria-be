package com.svcg.StockCustom.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "document")
    private Integer document;
    @Column(name = "create_date")
    private Date create;
    @Column(name = "upadate_date")
    private Date update;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TypePerson> typePersons = new HashSet<>();

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Operation operation;

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getDocument() {
        return document;
    }

    public void setDocument(Integer document) {
        this.document = document;
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

    public Set<TypePerson> getTypePersons() {
        return typePersons;
    }

    public void setTypePersons(Set<TypePerson> typePersons) {
        this.typePersons = typePersons;
    }
}