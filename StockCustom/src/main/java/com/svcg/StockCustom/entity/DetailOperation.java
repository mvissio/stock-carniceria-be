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

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "operation_detail")
public class DetailOperation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "operation_detail_id")
	private Long operationDetailId;
	
	@Column(name = "operation_id")
	private Long operationId;		
	
	@Column(name = "precio")
	private double precio;
	
	@Column(name = "cantidad")
	private double cantidad;
	
	

	public DetailOperation() {
	}

	
	

}
