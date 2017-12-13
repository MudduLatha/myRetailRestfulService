package com.restful.project.dao;

/**
 * This class will get the name of the product for the given product id by connecting to the end point using rest template.
 * 
 * @author Latha Muddu
 *
 */
public interface ProductDetailsDAO {
	
    	/**
    	 * Gets the product name from the given end point for the given product id.
    	 * 
    	 * @param productId
    	 * 		Unique identifier to identify the product.This cannot be null.
    	 * 
    	 * @return product name for the given product id.
    	 * 
    	 * @throws Exception
    	 * 		Throws exception when the product is not found for the given product id.
    	 */
	public String getProductName(final Long productId) throws Exception;
}
