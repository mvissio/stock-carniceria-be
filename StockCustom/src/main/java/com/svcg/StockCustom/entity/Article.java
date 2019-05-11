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
	@Size(max = 100, message = "{validation.rol.username.size}")
	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Size(max = 100, message = "{validation.rol.username.size}")
	@Column(name = "brand", length = 100)
	private String brand;

	@Size(max = 255, message = "{validation.rol.username.size}")
	@Column(name = "description", length = 255)
	private String description;

	@Column(name = "expiration_date")
	private Date expirationDate;
	
	@Column(name = "current_amount")
	private int currentAmount;
	
	@Column(name = "create_date", nullable = false)
	private Date createDate;

	@Column(name = "disabled_date")
	private Date disabledDate;

	@Column(name = "disabled")
	private boolean disabled;

	public Article() {
	}

	public Article(Long measurementUnitId, Long articleId, String name,
			String brand, String description, Date createDate,
			Date disabledDate, Date expirationDate, int currentAmount,
			boolean disabled) {
		super();
		this.articleId = articleId;
		this.measurementUnitId = measurementUnitId;
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.createDate = createDate;
		this.disabledDate = disabledDate;
		this.expirationDate = expirationDate;
		this.currentAmount = currentAmount;
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

	public int getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(int currentAmount) {
		this.currentAmount = currentAmount;
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

	
	
}
