package com.svcg.StockCustom.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.svcg.StockCustom.enums.OperationStatus;
import com.svcg.StockCustom.enums.OperationType;
import com.svcg.StockCustom.enums.PaymentMethod;

@Entity
@Table(name = "operation")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operation_id")
    private Long operationId;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "provider_id")
    private Long providerId;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date_time", nullable = false)
    private Date createDateTime;

    @Temporal(TemporalType.DATE)
    @Column(name = "disabled_date")
    private Date disabledDate;

    @Column(name = "disabled")
    private boolean disabled;

    // es una compra, una venta , registro de desperdicioes
    @Column(name = "operation_type")
    private OperationType operationType;

    // para evaluar si se paga al contado o con tarjeta
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    // especificamos si se cancelo o se realizo
    @Column(name = "operation_status")
    private OperationStatus operationStatus;

    @Column(name = "box_id")
    private Long boxId;

    @Column
    private double total;

    @Column(name = "sub_total")
    private double subTotal;

    @Column
    private double discount;

    @Transient
    private List<OperationDetail> operationDetails = new ArrayList<>();

    public Operation() {
    }

    public Operation(Long clientId, Long providerId,
                     Date createDate,
                     OperationType operationType, PaymentMethod paymentMethod,
                     OperationStatus operationStatus, double total, double subTotal,
                     Long boxId, List<OperationDetail> operationDetails) {
        super();
        this.clientId = clientId;
        this.providerId = providerId;
        this.createDate = createDate;
        this.operationType = operationType;
        this.paymentMethod = paymentMethod;
        this.operationStatus = operationStatus;
        this.total = total;
        this.subTotal = subTotal;
        this.operationDetails = operationDetails;
        this.boxId = boxId;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getDisabledDate() {
        return disabledDate;
    }

    public void setDisabledDate(Date disabledDate) {
        this.disabledDate = disabledDate;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public List<OperationDetail> getOperationDetails() {
        return operationDetails;
    }

    public void setOperationDetails(List<OperationDetail> operationDetails) {
        this.operationDetails = operationDetails;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Long getBoxId() {
        return boxId;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }

    @Override
    public String toString() {
        return "Operation [operationId=" + operationId + ", clientId=" + clientId + ", providerId=" + providerId
                + ", createDate=" + createDate + ", createDateTime=" + createDateTime + ", disabledDate=" + disabledDate
                + ", disabled=" + disabled + ", operationType=" + operationType + ", paymentMethod=" + paymentMethod
                + ", operationStatus=" + operationStatus + ", boxId=" + boxId + ", total=" + total + ", subTotal="
                + subTotal + ", discount=" + discount + ", operationDetails=" + operationDetails + "]";
    }

}
