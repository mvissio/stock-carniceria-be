package com.svcg.StockCustom.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "aditional_atr_type_person")
public class AditionalAtrTypePerson implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "fk_aditional_atr", nullable = false)
    private AditionalAtribute aditionalAtrType;

    @Id
    @ManyToOne
    @JoinColumn(name = "fk_type_person", nullable = false)
    private TypePerson typePerson;

    @Column(name = "create_date")
    private Date create;

    @Column(name = "updated_date")
    private Date update;

    public AditionalAtrTypePerson() {
    }

    public AditionalAtribute getAditionalAtr() {
        return aditionalAtrType;
    }

    public void setAditionalAtr(AditionalAtribute aditionalAtr) {
        this.aditionalAtrType = aditionalAtr;
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