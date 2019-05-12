package com.svcg.StockCustom.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "operation_detail")
public class DetailOperation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "operation_detail_id")
	private Long operationDetailId;
	
	@Column(name = "operation_id")
	private Long operationId;		
		
	@Column(name = "article_id", nullable = false)
	private Long articleId;			
	
	@Column(name = "precio")
	private double precio;
	
	@Column(name = "cantidad")
	private double cantidad;
	
	
	public DetailOperation() {
	}


	public DetailOperation(Long operationDetailId, Long operationId,
			Long articleId, double precio, double cantidad) {
		super();
		this.operationDetailId = operationDetailId;
		this.operationId = operationId;
		this.articleId = articleId;
		this.precio = precio;
		this.cantidad = cantidad;
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


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public double getCantidad() {
		return cantidad;
	}


	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	

	
	

}
