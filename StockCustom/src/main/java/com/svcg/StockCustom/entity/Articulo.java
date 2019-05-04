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
@Table(name = "articulos")
public class Articulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "articulo_id")
	private Long articuloId;

	@Column(name = "unidad_medida_id")
	private Long idCategory;

	@NotEmpty
	@Size(max = 100, message = "{validation.rol.username.size}")
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Size(max = 100, message = "{validation.rol.username.size}")
	@Column(name = "marca", length = 100)
	private String marca;

	@Size(max = 255, message = "{validation.rol.username.size}")
	@Column(name = "descripcion", length = 255)
	private String descripcion;

	@Column(name = "fecha_alta", nullable = false)
	private Date fechaAlta;

	@Column(name = "fecha_baja")
	private Date fechaBaja;

	@Column(name = "fecha_vencimiento")
	private Date fechaVencimiento;

	@Column(name = "cantidad_actual")
	private int cantidadActual;

	@Column(name = "baja")
	private boolean baja;

	public Articulo() {
	}

	public Articulo(Long articuloId, Long idCategory, String nombre,
			String marca, String descripcion, Date fechaAlta, Date fechaBaja,
			Date fechaVencimiento, int cantidadActual, boolean baja) {
		super();
		this.articuloId = articuloId;
		this.idCategory = idCategory;
		this.nombre = nombre;
		this.marca = marca;
		this.descripcion = descripcion;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
		this.fechaVencimiento = fechaVencimiento;
		this.cantidadActual = cantidadActual;
		this.baja = baja;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public int getCantidadActual() {
		return cantidadActual;
	}

	public void setCantidadActual(int cantidadActual) {
		this.cantidadActual = cantidadActual;
	}

	public Long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public boolean isBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public Long getArticuloId() {
		return articuloId;
	}

	public void setArticuloId(Long articuloId) {
		this.articuloId = articuloId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
