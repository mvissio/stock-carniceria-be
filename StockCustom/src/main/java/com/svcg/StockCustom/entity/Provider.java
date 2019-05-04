package com.svcg.StockCustom.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "providers")
public class Provider {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "provider_id")
	private Long providerId;

	@NotEmpty
	@Size(max = 100, message = "{validation.provider.name.size}")
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	@NotEmpty
	@Size(max = 255, message = "{validation.provider.address.size}")
	@Column(name = "address", length = 255)
	private String address;
	
	@Size(max = 60)
	@Column(name = "email", length = 60)
	private String email;

	@Column(name = "telephone", nullable = false)
	private String telephone;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	public Provider() {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
}
