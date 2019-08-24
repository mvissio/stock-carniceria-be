package com.svcg.StockCustom.service.dto;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ArticleDTO {
	private Long articleId;

	@NotNull
	private Long measurementUnitId;
	
	@NotNull
	private Long categoryId;

	@NotEmpty
	@Size(max = 100)
	private String name;

	@Size(max = 100)
	private String brand;

	@Size(max = 255)
	private String description;		
	
	private Double currentQuantity;

	private Double currentPrice;

	private Date createDate;

	private Date disabledDate;

	private Boolean disabled;
	
	private String code;
	
	public ArticleDTO() {
		super();
	}

	public ArticleDTO(@NotNull Long measurementUnitId, @NotNull Long categoryId, @NotEmpty @Size(max = 100) String name,
			@Size(max = 100) String brand, Double currentQuantity, Double currentPrice, Date createDate, String code) {
		super();
		this.measurementUnitId = measurementUnitId;
		this.categoryId = categoryId;
		this.name = name;
		this.brand = brand;
		this.currentQuantity = currentQuantity;
		this.currentPrice = currentPrice;
		this.createDate = createDate;
		this.code = code;
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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
		

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getArticleId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
		ArticleDTO articleDTO = (ArticleDTO) obj;
		if (getArticleId() == null || articleDTO.getArticleId() != null) {
			return false;
		}
		return Objects.equals(getArticleId(), articleDTO.getArticleId());
	}

	@Override
	public String toString() {
		return "ArticleDTO [articleId=" + articleId + ", measurementUnitId=" + measurementUnitId + ", categoryId="
				+ categoryId + ", name=" + name + ", brand=" + brand + ", description=" + description
				+ ", currentQuantity=" + currentQuantity + ", currentPrice="
				+ currentPrice + ", createDate=" + createDate + ", disabledDate=" + disabledDate + ", disabled="
				+ disabled + ", code=" + code + "]";
	}
    
}
