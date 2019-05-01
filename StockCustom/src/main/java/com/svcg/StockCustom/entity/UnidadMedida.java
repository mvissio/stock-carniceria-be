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
@Table(name = "unidad_medidas")
public class UnidadMedida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "unidad_medida_id")
	private Long UnidadMedidaId;

	@NotEmpty
	@Size(max = 100, message = "{validation.rol.username.size}")
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name = "simbolo", nullable = false, length = 2)
	private String simbolo;

	@Column(name = "baja")
	private Boolean baja;

	@Column(name = "fecha_baja")
	private Date fechaBaja;

	@Column(name = "fecha_alta", nullable = false)
	private Date fechaAlta;

	public UnidadMedida() {
	}

	public UnidadMedida(Long unidadMedidaId, String nombre, String simbolo,
			Boolean baja, Date fechaBaja, Date fechaAlta) {
		super();
		UnidadMedidaId = unidadMedidaId;
		this.nombre = nombre;
		this.simbolo = simbolo;
		this.baja = baja;
		this.fechaBaja = fechaBaja;
		this.fechaAlta = fechaAlta;
	}

	public Long getUnidadMedidaId() {
		return UnidadMedidaId;
	}

	public void setUnidadMedidaId(Long unidadMedidaId) {
		UnidadMedidaId = unidadMedidaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public Boolean getBaja() {
		return baja;
	}

	public void setBaja(Boolean baja) {
		this.baja = baja;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

}
