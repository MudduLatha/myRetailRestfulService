package com.restful.project.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.client.HttpClientErrorException;

import com.restful.project.dao.ProductDetailsDAO;
import com.restful.project.dao.ProductDetailsDAOImpl;
import com.restful.project.exception.ProductNotFoundException;

/**
 * Thsi class tests whether the product name is correctly retrieved or not.
 * 
 * @author Latha Muddu.
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProductDetailsDAOImpl.class, loader = AnnotationConfigContextLoader.class)
@TestPropertySource(locations = "classpath:application.properties")
@SpringBootTest
public class ProductDetailsDAOImplTest {
	@Mock
	private ProductDetailsDAOImpl productDetailsDAOImpl;
	
	private static final Long PRODUCT_ID = (long) 13860428;
	private static final String NAME = "The Big Lebowski (Blu-ray)";
	
	/**
	 * Expects {@link ProductDetailsDAO#getProductName(Long)} to run successfully when a valid product id is passed.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetProductName() throws Exception {
	    Mockito.when(productDetailsDAOImpl.getProductName(PRODUCT_ID)).thenReturn(NAME);
	    String result = productDetailsDAOImpl.getProductName(PRODUCT_ID);
	    assertEquals(NAME, result);
	}
	
	/**
	 * Expects {@link ProductDetailsDAO#getProductName(Long)} to throw {@link ProductNotFoundException} when product name is not found.
	 * 
	 * @throws Exception
	 */
	@Test(expected = ProductNotFoundException.class)
	public void testFetProductNameWhenException() throws Exception {
	    Mockito.when(productDetailsDAOImpl.getProductName(PRODUCT_ID)).thenThrow(new ProductNotFoundException("Product not found with the given product id"));
	    productDetailsDAOImpl.getProductName(PRODUCT_ID);
	}
	
	/**
	 * Expects {@link ProductDetailsDAO#getProductName(Long)} to throw {@link HttpClientErrorException} when product is not found.
	 * 
	 * @throws Exception
	 */
	@Test(expected = HttpClientErrorException.class)
	public void testFetProductNameWhenProductNameIsNull() throws Exception {
	    Mockito.when(productDetailsDAOImpl.getProductName(PRODUCT_ID)).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
	    productDetailsDAOImpl.getProductName(PRODUCT_ID);
	}
}
