package com.svcg.StockCustom.service.dto;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CategoryDTO {
	
	private Long categoryId;

	@NotEmpty
	@Size(max = 100)
	private String name;
	
	@Size(max = 255)
	private String description;

	@Temporal(TemporalType.DATE)
	private Date createDate;

	@Temporal(TemporalType.DATE)
	private Date disabledDate;

	private Boolean disabled;

	public CategoryDTO() {
		super();
	}

	public CategoryDTO(@NotEmpty @Size(max = 100) String name, 
			@Size(max = 255) String description, Date createDate, Date disabledDate, Boolean disabled) {
		this.name = name;
		this.description = description;
		this.createDate = createDate;
		this.disabledDate = disabledDate;
		this.disabled = disabled;
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

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getCategoryId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
		CategoryDTO CategoryDTO = (CategoryDTO) obj;
		if (getCategoryId() == null || CategoryDTO.getCategoryId() != null) {
			return false;
		}
		return Objects.equals(getCategoryId(), CategoryDTO.getCategoryId());
	}

	@Override
	public String toString() {
		return "CategoryDTO [categoryId=" + categoryId + ", createDate=" + createDate + ", description=" + description
				+ ", disabled=" + disabled + ", disabledDate=" + disabledDate + ", name=" + name + "]";
	}
    
}
