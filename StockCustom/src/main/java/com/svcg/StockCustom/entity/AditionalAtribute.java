package com.svcg.StockCustom.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "aditional_atributes")
public class AditionalAtribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "atr_key")
    private String key;
    @Column(name = "atr_value")
    private String value;
    @Column(name = "description")
    private String description;
    @Column(name = "create_date")
    private Date create;
    @Column(name = "upadate_date")
    private Date update;
   @OneToMany(mappedBy = "aditionalAtrType", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private Set<AditionalAtrTypePerson> aditionalAtrTypes= new HashSet<>();;

    public AditionalAtribute() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public Set<AditionalAtrTypePerson> getAditionalAtrTypes() {
        return aditionalAtrTypes;
    }

    public void setAditionalAtrTypes(Set<AditionalAtrTypePerson> aditionalAtrTypes) {
        this.aditionalAtrTypes = aditionalAtrTypes;
    }
}