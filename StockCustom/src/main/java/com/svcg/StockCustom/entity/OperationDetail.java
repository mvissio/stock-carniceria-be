package com.svcg.StockCustom.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "operation_detail")
public class OperationDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "operation_detail_id")
	private Long operationDetailId;
	
	@Column(name = "operation_id")
	private Long operationId;		
		
	@Column(name = "article_id", nullable = false)
	private Long articleId;			
	
	@NotNull
	@Column(name = "price")
	private double price;
	
	@NotNull
	@Column(name = "amount")
	private double amount;
	
	
	public OperationDetail() {
	}


	public OperationDetail(Long operationDetailId, Long operationId,
			Long articleId, double price, double amount) {
		super();
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


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}

}
