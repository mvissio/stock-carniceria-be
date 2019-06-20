package com.svcg.StockCustom.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "articles")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_id")
	private Long articleId;

	@Column(name = "measurement_unit_id")
	private Long measurementUnitId;

	@NotEmpty
	@Size(max = 100)
	@Column(name = "name", length = 100 ,nullable = false)
	private String name;

	@Size(max = 100)
	@Column(name = "brand", length = 100)
	private String brand;

	@Size(max = 255)
	@Column(name = "description", length = 255)
	private String description;

	//vencimiento, a futuro podemos hacer un informe de vencimientos futuros
	
	@ColumnDefault("null")
	@Column(name = "expiration_date",nullable = true)
	private Date expirationDate;

	//el stock actual del producto. deberia ser nullable = false ??
	//caso de la carne va a poner por ejemplo 1,5 kg ???
	
	@Column(name = "current_quantity")
	private double currentQuantity;

	/*
	 * este es el precio que tiene el producto al momento de ser vendido se
	 * diferencia del precio en el detalle operacion que nos sirve para dejar un
	 * registro a que precio se vendio en el momento de la compra si se
	 * actualiza el precio en el articulo, no se actualiza en el detalle de la
	 * operacion
	 */

	@Column(name = "current_price", nullable = false)
	private double currentPrice;

	@Column(name = "create_date", nullable = false)
	private Date createDate;

	@Column(name = "disabled_date")
	private Date disabledDate;

	@Column(name = "disabled")
	private boolean disabled;
	
	

	public Article() {
	}
	
	public Article(Long articleId, Long measurementUnitId, String name,
			String brand, String description, Date expirationDate,
			double currentQuantity, double currentPrice, Date createDate,
			Date disabledDate, boolean disabled) {
		super();
		this.articleId = articleId;
		this.measurementUnitId = measurementUnitId;
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.expirationDate = expirationDate;
		this.currentQuantity = currentQuantity;
		this.currentPrice = currentPrice;
		this.createDate = createDate;
		this.disabledDate = disabledDate;
		this.disabled = disabled;
	}




	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getMeasurementUnitId() {
		return measurementUnitId;
	}

	public void setMeasurementUnitId(Long measurementUnitId) {
		this.measurementUnitId = measurementUnitId;
	}
		
	public double getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(double currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Override
	public String toString() {
		return "id: " + articleId + " name: " + name + " brand: " + brand + " current quantity: " +  currentQuantity;
				}
	
	

}
