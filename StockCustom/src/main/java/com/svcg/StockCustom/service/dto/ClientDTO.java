package com.svcg.StockCustom.service.dto;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClientDTO {

	private Long clientId;

	@NotEmpty
	@Size(max = 45)
	private String name;

	@NotEmpty
	@Size(max = 45)
	private String surname;

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
	
	public ClientDTO() {
		super();
	}

	public ClientDTO(Long clientId, String name, String surname, String email,
			String telephone, Date createDate, Date disabledDate,
			Boolean disabled) {
		super();
		this.clientId = clientId;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.telephone = telephone;
		this.createDate = createDate;
		this.disabledDate = disabledDate;
		this.disabled = disabled;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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
		return Objects.hashCode(getClientId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
		ClientDTO clientDTO = (ClientDTO) obj;
		if (getClientId() == null || clientDTO.getClientId() != null) {
			return false;
		}
		return Objects.equals(getClientId(), clientDTO.getClientId());
	}

	@Override
	public String toString() {
		return "ClientDTO [clientId=" + clientId + ", createDate=" + createDate + ", disabled=" + disabled
				+ ", disabledDate=" + disabledDate + ", email=" + email + ", name=" + name + ", surname=" + surname
				+ ", telephone=" + telephone + "]";
	}
    
}
