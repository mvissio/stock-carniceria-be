package com.svcg.StockCustom.service.dto;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MeasurementUnitDTO {
    private Long measurementUnitId;

	@NotEmpty
	@Size(max = 100)
	private String name;

	private String symbol;

	@Temporal(TemporalType.DATE)
	private Date createDate;

	@Temporal(TemporalType.DATE)
	private Date disabledDate;

	private Boolean disabled;
	
	public MeasurementUnitDTO() {
		super();
	}

	public MeasurementUnitDTO(Long measurementUnitId, @NotEmpty @Size(max = 100) String name, String symbol,
			Date createDate, Date disabledDate, Boolean disabled) {
		this.measurementUnitId = measurementUnitId;
		this.name = name;
		this.symbol = symbol;
		this.createDate = createDate;
		this.disabledDate = disabledDate;
		this.disabled = disabled;
	}

	public Long getMeasurementUnitId() {
		return measurementUnitId;
	}

	public void setMeasurementUnitId(Long measurementUnitId) {
		this.measurementUnitId = measurementUnitId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
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
        return Objects.hashCode(getMeasurementUnitId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MeasurementUnitDTO measurementUnitDTO = (MeasurementUnitDTO) obj;
        if (getMeasurementUnitId() == null || measurementUnitDTO.getMeasurementUnitId() != null) {
            return false;
        }
        return Objects.equals(getMeasurementUnitId(), measurementUnitDTO.getMeasurementUnitId());
    }

	@Override
	public String toString() {
		return "MeasurementUnitDTO [createDate=" + createDate + ", disabled=" + disabled + ", disabledDate="
				+ disabledDate + ", measurementUnitId=" + measurementUnitId + ", name=" + name + ", symbol=" + symbol
				+ "]";
	}

	
    
    
    
}
