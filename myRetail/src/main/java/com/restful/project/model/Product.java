package com.restful.project.model;

/**
 * This class holds the details about the product.
 * 
 * @author Latha Muddu
 *
 */
public class Product {
	private Long productId;
	private String productName;
	private PriceDetails priceDetails;
	
	public Product() {
	    super();
	}
	
	public Product(Long productId, String productName, PriceDetails priceDetails) {
	    super();
	    this.productId = productId;
	    this.productName = productName;
	    this.priceDetails = priceDetails;
	}

	public Long getProductId() {
	    return productId;
	}

	public void setProductId(Long productId) {
	    this.productId = productId;
	}

	public String getProductName() {
	    return productName;
	}

	public void setProductName(String productName) {
	    this.productName = productName;
	}
	
	public PriceDetails getPriceDetails() {
	    return priceDetails;
	}
	
	public void setPriceDetails(PriceDetails priceDetails) {
	    this.priceDetails = priceDetails;
	}
}
