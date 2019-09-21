package com.svcg.StockCustom.service.dto;

public class MonthlyOperationsReportDTO {

	private double totalSale;
	
	private double totalBuy;
		
	public MonthlyOperationsReportDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MonthlyOperationsReportDTO(double totalSale, double totalBuy) {
		super();
		this.totalSale = totalSale;
		this.totalBuy = totalBuy;
	}

	public double getTotalSale() {
		return totalSale;
	}

	public void setTotalSale(double totalSale) {
		this.totalSale = totalSale;
	}

	public double getTotalBuy() {
		return totalBuy;
	}

	public void setTotalBuy(double totalBuy) {
		this.totalBuy = totalBuy;
	}
	
	
	
	
}
