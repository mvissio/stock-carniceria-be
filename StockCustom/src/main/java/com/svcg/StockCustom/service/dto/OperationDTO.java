package com.svcg.StockCustom.service.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.svcg.StockCustom.enums.OperationStatus;
import com.svcg.StockCustom.enums.OperationType;
import com.svcg.StockCustom.enums.PaymentMethod;

public class OperationDTO {
	private Long operationId;

    private Long clientId;

    private Long providerId;

    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDateTime;

    @Temporal(TemporalType.DATE)
    private Date disabledDate;

    private Boolean disabled;

    private OperationType operationType;

    private PaymentMethod paymentMethod;

    private OperationStatus operationStatus;

    private Long boxId;

    private Double total;

    private Double subTotal;

    private Double discount;

	private List<OperationDetailDTO> operationDetails = new ArrayList<>();

	public OperationDTO(Long operationId, Date createDate, OperationType operationType, PaymentMethod paymentMethod,
			OperationStatus operationStatus, Long boxId, Double total) {
		this.operationId = operationId;
		this.createDate = createDate;
		this.operationType = operationType;
		this.paymentMethod = paymentMethod;
		this.operationStatus = operationStatus;
		this.boxId = boxId;
		this.total = total;
	}
	
	public OperationDTO() {
		super();
	}

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
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

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
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

	public Long getBoxId() {
		return boxId;
	}

	public void setBoxId(Long boxId) {
		this.boxId = boxId;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public List<OperationDetailDTO> getOperationDetails() {
		return operationDetails;
	}

	public void setOperationDetails(List<OperationDetailDTO> operationDetails) {
		this.operationDetails = operationDetails;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(getOperationId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
		OperationDTO operationDTO = (OperationDTO) obj;
		if (getOperationId() == null || operationDTO.getOperationId() != null) {
			return false;
		}
		return Objects.equals(getOperationId(), operationDTO.getOperationId());
	}
    
}
