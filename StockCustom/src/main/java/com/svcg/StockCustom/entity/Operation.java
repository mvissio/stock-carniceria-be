package com.svcg.StockCustom.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	private Date clientId;

	@Column(name = "provider_id")
	private Date providerId;
		
	@Column(name = "create_date", nullable = false)
	private Date createDate;

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
	
	// para evaluar si se paga al contado o con tarjeta
	@Column(name = "operation_status")
	private OperationStatus operationStatus;		

	@Column(name = "total")
	private double total;

	@Column(name = "sub_total")
	private double subTotal;

	@Transient
	private List<DetailOperation> detailOperationlist = new ArrayList<DetailOperation>();

	public Operation() {
	}

	public Operation(Long operationId, Date clientId, Date providerId,
			Date createDate, Date disabledDate, boolean disabled,
			OperationType operationType, PaymentMethod paymentMethod,
			OperationStatus operationStatus, double total, double subTotal,
			List<DetailOperation> detailOperationlist) {
		super();
		this.operationId = operationId;
		this.clientId = clientId;
		this.providerId = providerId;
		this.createDate = createDate;
		this.disabledDate = disabledDate;
		this.disabled = disabled;
		this.operationType = operationType;
		this.paymentMethod = paymentMethod;
		this.operationStatus = operationStatus;
		this.total = total;
		this.subTotal = subTotal;
		this.detailOperationlist = detailOperationlist;
	}




	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public List<DetailOperation> getDetailOperationlist() {
		return detailOperationlist;
	}

	public void setDetailOperationlist(List<DetailOperation> detailOperationlist) {
		this.detailOperationlist = detailOperationlist;
	}

	public Date getClientId() {
		return clientId;
	}

	public void setClientId(Date clientId) {
		this.clientId = clientId;
	}

	public Date getProviderId() {
		return providerId;
	}

	public void setProviderId(Date providerId) {
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

	@Override
	public String toString() {
		return "id: " + this.operationId + " operationType: " + operationType + " paymentMethod: " + paymentMethod + " operationStatus " + operationStatus; 
	}
	
	
	
	

}
