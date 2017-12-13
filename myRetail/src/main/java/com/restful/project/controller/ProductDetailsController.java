package com.restful.project.controller;

import static com.google.common.base.Preconditions.checkArgument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restful.project.exception.ProductNotFoundException;
import com.restful.project.model.Product;
import com.restful.project.service.ProductDetailsService;

/**
 * This class handles the request to get the product details and also update the product price
 * 
 * @author Latha Muddu
 *
 */
@RestController
@RequestMapping("/")
public class ProductDetailsController {
	
	private static final String PRODUCT_ID_NULL = "Product id cannot be null or less than 0";
	private static final String PRODUCT_NOT_FOUND = "No product found with the given product id";
	
	@Autowired
	private ProductDetailsService productDetailsService;
	
	/**
	 * Implements the rest service to get the details of the product with the help of product id.
	 * 
	 * @param productId
	 * 		Unique identifier to identify the product.This cannot be null.
	 * 
	 * @return {@link Product} object which contains the product details.
	 * 
	 * @throws Exception
	 * 			throws exception when the prodcut id is null.
	 */
	@RequestMapping(value = "products/{productId:[\\d]+}", method = RequestMethod.GET)
	public Product getProductDetails(@PathVariable("productId") Long productId) throws Exception {
	    checkArgument(productId != null, PRODUCT_ID_NULL);
	    Product product = null;	
	    try {
		product = productDetailsService.getProductDetails(productId);
	    } catch(final Exception exception) {
		throw new ProductNotFoundException(PRODUCT_NOT_FOUND);
	    }
	    return product;
	}
	
	/**
	 * Implements the rest service to update the price details for the given product id
	 * 
	 * @param productId
	 * 		Unique identifier to identify the product.This cannot be null.
	 * 
	 * @param product
	 * 		{@link Product} object for which the price needs to be updated.
	 * 
	 * @return {@link String} which has the respective message based on the update.
	 * 
	 * @throws Exception
	 * 		Throws {@link IllegalArgumentException} when the product id null or negative.
	 */
	@RequestMapping(value = "products/{productId}", method = RequestMethod.PUT)
	public String updateProductPriceDetails(@PathVariable("productId") Long productId, @RequestBody Product product)
			throws Exception {
	    checkArgument(productId != null || productId < 0, PRODUCT_ID_NULL);
	    boolean updated = productDetailsService.updateProductPriceDetails(productId, product.getPriceDetails().getPrice());
	    if (updated) {
		return "Price details for the product with id " + productId + " is successfully updated";
	    } else {
		return productId + " is not updated";
	    }
	}
}
