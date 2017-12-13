package com.restful.project.repository;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.restful.project.model.ProductPriceDetails;

/**
 * This interface would connect with the cassandra database and do all the CRUD operations with the given details and return the respective details
 * 
 * @author Latha Muddu
 *
 */
public interface ProductPriceDetailsRepository extends CrudRepository<ProductPriceDetails, String> {
	
    	/**
    	 * Gets all the price details for the product.
    	 * 
    	 * @param productId
    	 * 		Unique identifier to identify the product.This cannot be null.
    	 * 
    	 * @return {@link ProductPriceDetails} object where all the price details.
    	 */
	@Query("SELECT productID,currency,price FROM productprices WHERE productID=?0")
	public ProductPriceDetails getProductPrice(final Long productId);
	
	/**
	 * Updates the price details for the product with the given price.
	 * 
	 * @param productID
	 * 		Unique identifier to identify the product.This cannot be null.
	 * 
	 * @param price
	 * 	Value of the price that needs to be update for the product.
	 * 
	 * @return true if the price is updated successfully otherwise returns false.
	 */
	@Query("UPDATE productprices SET price=?1 WHERE productID=?0 IF EXISTS")
	public boolean updateProductPrice(Long productID, Float price);
}
