package com.svcg.StockCustom.model;

import java.util.Date;

//clase que no se persiste en la db ya que no es necesario dejar guardado 
//el codigo generado por articulo

public class Code {

	// tipos de codigo, puede ser uno o los dos el que quiera generar en base a
	// los datos
	private String barcode;
	private String qrcode;

	// datos del codigo barra o qr
	private Long articleId;
	private String nameArticle;
	private double price;
	private double weight;

	// fecha de creación del codigo
	private Date createDate;

	public Code() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Code(String barcode, String qrcode, Long articleId,
			String nameArticle, double price, double weight, Date createDate) {
		super();
		this.barcode = barcode;
		this.qrcode = qrcode;
		this.articleId = articleId;
		this.nameArticle = nameArticle;
		this.price = price;
		this.weight = weight;
		this.createDate = createDate;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getNameArticle() {
		return nameArticle;
	}

	public void setNameArticle(String nameArticle) {
		this.nameArticle = nameArticle;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	

}
