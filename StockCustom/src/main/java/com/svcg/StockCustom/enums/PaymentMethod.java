package com.svcg.StockCustom.enums;

public enum PaymentMethod {
	// credit card
	CC("CC"), CASH("CASH");

	private String paymentMethodName;

	PaymentMethod(String paymentMethodName) {
		this.paymentMethodName = paymentMethodName;
	}

	public String paymentMethodName() {
		return paymentMethodName;
	}

}
