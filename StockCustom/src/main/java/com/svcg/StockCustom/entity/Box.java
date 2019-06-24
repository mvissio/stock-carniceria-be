package com.svcg.StockCustom.entity;


import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "box")
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boxId")
    private Long boxId;

    @Column(name = "cash_open")
    private Double cashOpen;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_open")
    private Date dateOpen;

    @Column(name = "cash_close")
    private String cashClose;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_close")
    private Date dateClose;

    @Column(name = "open", nullable = false)
    private boolean open;


    @Column(name = "detail_close")
    @Size( max = 255, message = "{validation.rol.name.size}")
    private String detailClose;

    @Transient
    private List<Operation> operationList = new ArrayList<>();


    public Box() {
    }

    public Long getBoxId() {
        return boxId;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }

    public Double getCashOpen() {
        return cashOpen;
    }

    public void setCashOpen(Double cashOpen) {
        this.cashOpen = cashOpen;
    }

    public Date getDateOpen() {
        return dateOpen;
    }

    public void setDateOpen(Date dateOpen) {
        this.dateOpen = dateOpen;
    }

    public String getCashClose() {
        return cashClose;
    }

    public void setCashClose(String cashClose) {
        this.cashClose = cashClose;
    }

    public Date getDateClose() {
        return dateClose;
    }

    public void setDateClose(Date dateClose) {
        this.dateClose = dateClose;
    }

    public String getDetailClose() {
        return detailClose;
    }

    public void setDetailClose(String detailClose) {
        this.detailClose = detailClose;
    }

    public List<Operation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}

