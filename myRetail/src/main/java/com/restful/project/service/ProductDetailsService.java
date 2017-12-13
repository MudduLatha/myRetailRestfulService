package com.restful.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restful.project.dao.ProductDetailsDAO;
import com.restful.project.model.PriceDetails;
import com.restful.project.model.Product;
import com.restful.project.model.ProductPriceDetails;
import com.restful.project.repository.ProductPriceDetailsRepository;

/**
 * This class will get the product name from the given product end point and gets the product price from cassandra database for the given product id and combines all the details to build the product object
 * @author Latha Muddu
 *
 */
@Service
public class ProductDetailsService {
	
	@Autowired
	private ProductDetailsDAO productDetailsDAO;
	@Autowired
	private ProductPriceDetailsRepository productPriceDetailsRepository;
	
	/**
	 * Gets the product name and the price details for the given product id
	 * 
	 * @param productId
	 * 		Unique identifier to identify the product
	 * 
	 * @return {@link Product} object which contains the product details.
	 * 
	 * @throws Exception
	 * 		throws exception when the product is not found with the given id.
	 */
	public Product getProductDetails(Long productId) throws Exception {
	    String productName = productDetailsDAO.getProductName(productId);
	    ProductPriceDetails productPriceDetails = productPriceDetailsRepository.getProductPrice(productId);
	    return constructProduct(productId, productName, productPriceDetails);
	}
	
	/**
	 * Builds the product object based on the product details.
	 * 
	 * @param productId
	 * 		Unique identifier for the product
	 * 
	 * @param productName
	 * 		This holds the name of the product and is of the type {@link String}
	 * 
	 * @param productPriceDetails
	 * 		This holds all the price details related to the product id.
	 * 
	 * @return {@link Product} object that is built based on the given product details.
	 */
	private Product constructProduct(Long productId, String productName, ProductPriceDetails productPriceDetails) {
	    Product constructedProduct = null;
	    PriceDetails current_price = null;
	    if(productPriceDetails == null) {
		current_price = new PriceDetails(null, null);
		constructedProduct = new Product(productId, productName, current_price);
	    } else {
		current_price = new PriceDetails(productPriceDetails.getPrice(), productPriceDetails.getCurrency());
		constructedProduct = new Product(productId, productName, current_price);
	    }
	    return constructedProduct;	
	}
	
	/**
	 * Updates the product price details for the given product id with the given price value.
	 * 
	 * @param productId
	 * 		Unique identifier for the product.
	 * 
	 * @param price
	 * 	Value of the price that needs to be update for the product.
	 * 
	 * @return true if the price is updated successfully otherwise returns false.
	 * 
	 */
	public boolean updateProductPriceDetails(Long productId, Float price) {
	    return productPriceDetailsRepository.updateProductPrice(productId, price);
	}
}
