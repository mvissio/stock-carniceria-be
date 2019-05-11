package com.svcg.StockCustom.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
	@Size(max = 100, message = "{validation.rol.username.size}")
	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "symbol", nullable = false, length = 2)
	private String symbol;

	@Column(name = "baja")
	private Boolean baja;

	@Column(name = "create_date", nullable = false)
	private Date createDate;

	@Column(name = "disabled_date")
	private Date disabledDate;

	@Column(name = "disabled")
	private boolean disabled;
	
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

	public Boolean getBaja() {
		return baja;
	}

	public void setBaja(Boolean baja) {
		this.baja = baja;
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
