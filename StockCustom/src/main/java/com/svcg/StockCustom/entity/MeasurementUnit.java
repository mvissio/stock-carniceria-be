package com.svcg.StockCustom.entity;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "measurement_units")
public class MeasurementUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "measurement_unit_id")
	private Long measurementUnitId;

	@NotEmpty
	@Size(max = 100)
	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Size(max = 5)
	@Column(name = "symbol", nullable = false, length = 5)
	private String symbol;

	@Temporal(TemporalType.DATE)
	@Column(name = "create_date", nullable = false)
	private Date createDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "disabled_date")
	private Date disabledDate;

	@Column(name = "disabled")
	private Boolean disabled;
	
	public MeasurementUnit() {
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

	
}
