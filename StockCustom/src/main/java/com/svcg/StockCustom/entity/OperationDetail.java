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
public class OperationDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "operation_detail_id")
	private Long operationDetailId;
	
	@Column(name = "operation_id")
	private Long idOperation;

	@NotEmpty
	@Size(max = 100, message = "{validation.rol.username.size}")
	@Column(name = "name", nullable = false, length = 100)
	private String name;
	
	@NotEmpty
	@Column(name = "date", length = 255)
	private String date;
	
	@Column(name = "enabled")
	@ColumnDefault("0")
	private Boolean enabled;

	//fecha baja
	@Column(name = "delete_date")
	private Date deleteDate;

	//fecha alta
	@Column(name = "create_date", nullable = false)
	private Date createDate;
	
	

	public OperationDetail() {
	}

	
	

}
