package com.restful.project.model;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 * This class holds all the details about the product in order to map it to the product prices table in cassandra.
 * 
 * @author Latha Muddu
 *
 */
@Table(value = "productprices")
public class ProductPriceDetails {
	@PrimaryKey
	private Long productID;
	private Float price;
	private String currency;

	public Long getProductID() {
	    return productID;
	}

	public void setProductID(Long productID) {
	    this.productID = productID;
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
