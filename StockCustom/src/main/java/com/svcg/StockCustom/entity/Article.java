package com.svcg.StockCustom.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "articles")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_id")
	private Long articleId;

	@NotNull
	@Column(name = "measurement_unit_id")
	private Long measurementUnitId;
	
	@NotNull
	@Column(name = "category_id")
	private Long categoryId;

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
	
	//el stock actual del producto. deberia ser nullable = false ??
	//caso de la carne va a poner por ejemplo 1,5 kg ???
	
	@Column(name = "current_quantity")
	private Double currentQuantity;

	/*
	 * este es el precio que tiene el producto al momento de ser vendido se
	 * diferencia del precio en el detalle operacion que nos sirve para dejar un
	 * registro a que precio se vendio en el momento de la compra si se
	 * actualiza el precio en el articulo, no se actualiza en el detalle de la
	 * operacion
	 */

	@Column(name = "current_price", nullable = false)
	private Double currentPrice;

	@Column(name = "create_date", nullable = false)
	private Date createDate;

	@Column(name = "disabled_date")
	private Date disabledDate;

	@Column(name = "disabled")
	private Boolean disabled;

	@Column(name = "code_article")
	private Long codeArticle;
	
	

	public Article() {
		
	}
	

	public Article(Long articleId, Long measurementUnitId, Long categoryId,
			@NotEmpty @Size(max = 100, message = "{validation.rol.username.size}") String name,
			@Size(max = 100, message = "{validation.rol.username.size}") String brand,
			@Size(max = 255, message = "{validation.rol.username.size}") String description, 
			Double currentQuantity, Double currentPrice, Date createDate, Date disabledDate, Boolean disabled, Long codeArticle) {
		super();
		this.articleId = articleId;
		this.measurementUnitId = measurementUnitId;
		this.categoryId = categoryId;
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.currentQuantity = currentQuantity;
		this.currentPrice = currentPrice;
		this.createDate = createDate;
		this.disabledDate = disabledDate;
		this.disabled = disabled;
		this.codeArticle= codeArticle;
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
	
	public Boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
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
		
	public Double getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(Double currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	public Double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	

	public Long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(Long codeArticle) {
		this.codeArticle = codeArticle;
	}

	@Override
	public String toString() {
		return "id: " + articleId + " name: " + name + " brand: " + brand + " current quantity: " +  currentQuantity;
				}
	
	

}
