package com.svcg.StockCustom.service.dto;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RolDTO {
	
	private Long rolId;

    @NotEmpty
    @Size(max = 45)
	private String name;
	
	
    
	public RolDTO() {
		super();
	}

	public RolDTO(Long rolId, @NotEmpty @Size(max = 45) String name) {
		this.rolId = rolId;
		this.name = name;
	}

	public Long getRolId() {
		return rolId;
	}

	public void setRolId(Long rolId) {
		this.rolId = rolId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getRolId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
		RolDTO rolDTO = (RolDTO) obj;
		if (getRolId() == null || rolDTO.getRolId() != null) {
			return false;
		}
		return Objects.equals(getRolId(), rolDTO.getRolId());
	}  
    
}
