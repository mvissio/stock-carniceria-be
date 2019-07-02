package com.svcg.StockCustom.service.dto;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProviderDTO {

	private Long providerId;

	@NotEmpty
	@Size(max = 45)
	private String name;

	@Size(max = 60)
	private String email;

	@NotNull
	private String telephone;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date createDate;

	@Temporal(TemporalType.DATE)
	private Date disabledDate;

	private Boolean disabled;
	
	public ProviderDTO() {
		super();
	}

	public ProviderDTO(Long providerId, String name, String email,
			String telephone, Date createDate, Date disabledDate,
			Boolean disabled) {
		super();
		this.providerId = providerId;
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.createDate = createDate;
		this.disabledDate = disabledDate;
		this.disabled = disabled;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	@Override
	public int hashCode() {
		return Objects.hashCode(getProviderId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
		ProviderDTO providerDTO = (ProviderDTO) obj;
		if (getProviderId() == null || providerDTO.getProviderId() != null) {
			return false;
		}
		return Objects.equals(getProviderId(), providerDTO.getProviderId());
	}

	@Override
	public String toString() {
		return "ProviderDTO [createDate=" + createDate + ", disabled=" + disabled + ", disabledDate=" + disabledDate
				+ ", email=" + email + ", name=" + name + ", providerId=" + providerId + ", telephone=" + telephone
				+ "]";
	}
    
}
