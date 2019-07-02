package com.svcg.StockCustom.service.dto;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDTO {
	private Long userId;

    @NotEmpty
    @Size(max = 45)
    private String username;

    @NotEmpty
    @Size(max = 60)
    private String password;

    private Boolean enabled;

    @Email
    @NotBlank
    private String email;

    private RolDTO rolDTO;
    
	public UserDTO() {
		super();
	}

	public UserDTO(Long userId, @NotEmpty @Size(max = 45) String username, @NotEmpty @Size(max = 60) String password,
			@Email @NotBlank String email) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RolDTO getRol() {
		return rolDTO;
	}

	public void setRol(RolDTO rolDTO) {
		this.rolDTO = rolDTO;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getUserId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
		UserDTO userDTO = (UserDTO) obj;
		if (getUserId() == null || userDTO.getUserId() != null) {
			return false;
		}
		return Objects.equals(getUserId(), userDTO.getUserId());
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", username=" + username + ", password=" + password + ", enabled="
				+ enabled + ", email=" + email + ", rol=" + rolDTO + "]";
	}
    
    
}
