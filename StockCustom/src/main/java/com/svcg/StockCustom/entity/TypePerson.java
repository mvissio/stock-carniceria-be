package com.svcg.StockCustom.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TypePerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "create_date")
    private Date create;
    @Column(name = "upadate_date")
    private Date update;
    @OneToMany(mappedBy = "typePerson", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AditionalAtrTypePerson> atrTypePersons = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "fk_person", nullable = false)
    private Person person;

    public TypePerson() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Set<AditionalAtrTypePerson> getAtrTypePersons() {
        return atrTypePersons;
    }

    public void setAtrTypePersons(Set<AditionalAtrTypePerson> atrTypePersons) {
        this.atrTypePersons = atrTypePersons;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
