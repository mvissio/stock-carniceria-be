package com.svcg.StockCustom.service.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;

public class OperationDetailDTO {
	
	private Long operationDetailId;
	
	private Long operationId;		
		
	private Long articleId;			
	
	@NotNull
	private Double price;
	
	@NotNull
	private Double amount;

	public OperationDetailDTO() {
		super();
	}

	public OperationDetailDTO(Long operationDetailId, Long operationId, Long articleId, Double price, Double amount) {
		this.operationDetailId = operationDetailId;
		this.operationId = operationId;
		this.articleId = articleId;
		this.price = price;
		this.amount = amount;
	}

	public Long getOperationDetailId() {
		return operationDetailId;
	}

	public void setOperationDetailId(Long operationDetailId) {
		this.operationDetailId = operationDetailId;
	}

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(getOperationDetailId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
		OperationDetailDTO operationDetailDTO = (OperationDetailDTO) obj;
		if (getOperationDetailId() == null || operationDetailDTO.getOperationDetailId() != null) {
			return false;
		}
		return Objects.equals(getOperationDetailId(), operationDetailDTO.getOperationDetailId());
	}
    
}
