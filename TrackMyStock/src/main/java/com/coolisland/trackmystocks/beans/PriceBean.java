package com.coolisland.trackmystocks.beans;


/**
 * formats the price appropriately
 * 
 * @author Silvio
 *
 */
public class PriceBean {
	private String price;
	
	public PriceBean() {
		price = "";
	}

	public PriceBean(String price) {
		this.price = price;
	}
	
	public String getPrice() {
		return price;
	}
}
