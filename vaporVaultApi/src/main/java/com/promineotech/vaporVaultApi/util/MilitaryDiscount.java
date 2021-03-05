package com.promineotech.vaporVaultApi.util;

public enum MilitaryDiscount {

	none(.00),
	military(.10);
	
private double discount;
	
MilitaryDiscount(double discount) {
		this.discount = discount;
	}
	
	public double getDiscount() {
		return discount;
	}
}
