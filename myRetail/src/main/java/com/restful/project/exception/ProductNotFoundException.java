package com.restful.project.exception;

/**
 * This class will throws the product not found exception with the given message.
 * 
 * @author Latha Muddu
 *
 */
public class ProductNotFoundException extends Exception{
    	/**
    	 * This method will throw the exception with the custom message.
    	 * 
    	 * @param message
    	 * 		Custom message for the exception.
    	 */
	public ProductNotFoundException(String message) {
	    super(message);
	}
}