package com.restful.project.model;

/**
 * This class holds the details about the product price.
 * 
 * @author Latha Muddu
 *
 */
public class PriceDetails {
	private Float price;
	private String currency;
	
	public PriceDetails() {
	    super();
	}
	
	public PriceDetails(Float price, String currency) {
	    super();
	    this.price = price;
	    this.currency = currency;
	}
	
	public Float getPrice() {
	    return price;
	}
	
	public void setPrice(Float price) {
	    this.price = price;
	}
	
	public String getCurrency() {
	    return currency;
	}
	
	public void setCurrency(String currency) {
	    this.currency = currency;
	}
}
