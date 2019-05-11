package com.svcg.StockCustom.entity;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "clients")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id")
	private Long clientId;

	@NotEmpty
	@Size(max = 45, message = "{validation.client.name.size}")
	@Column(name = "name", nullable = false, length = 45)
	private String name;

	@NotEmpty
	@Size(max = 45, message = "{validation.client.surname.size}")
	@Column(name = "surname", nullable = false, length = 45)
	private String surname;

	@Size(max = 60)
	@Column(name = "email", length = 60)
	private String email;

	@Column(name = "telephone", nullable = false)
	private String telephone;

	@Column(name = "create_date", nullable = false)
	private Date createDate;

	@Column(name = "disabled_date")
	private Date disabledDate;

	@Column(name = "disabled")
	private boolean disabled;
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(Long clientId, String name, String surname, String email,
			String telephone, Date createDate, Date disabledDate,
			boolean disabled) {
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

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	
}
