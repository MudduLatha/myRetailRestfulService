package com.restful.project.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.restful.project.controller.ProductDetailsController;
import com.restful.project.model.PriceDetails;
import com.restful.project.model.Product;
import com.restful.project.service.ProductDetailsService;

/**
 * This class tests the rest service to function as expected
 * 
 * @author Latha Muddu
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductDetailsController.class, secure = false)
public class ProductDetailsControllerTest {
	
	@InjectMocks
	private ProductDetailsController productDetailsController;
	@MockBean
	private ProductDetailsService productDetailsService;
	
	@Autowired
	private MockMvc mockMvc;
	
	PriceDetails priceInfo = new PriceDetails((float) 99.9, "USD");
	private static final Long PRODUCT_ID = (long) 13860428;
	private static final Long NEGATIVE_PRODUCT_ID = (long) -1;
	private static final String EXPECTED_VALUE = "{\"productId\":13860428,\"productName\":\"The Big Lebowski (Blu-ray)\",\"priceDetails\":{\"price\":99.9,\"currency\":\"USD\"}}";
	Product mockProduct = new Product(PRODUCT_ID, "The Big Lebowski (Blu-ray)", priceInfo);
	
	/**
	 * Expects {@link ProductDetailsController#getProductDetails(Long)} to run successfully when a valid product id is passed.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetProductDetails() throws Exception {
	    Mockito.when(productDetailsService.getProductDetails(Mockito.anyLong())).thenReturn(mockProduct);
	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/" + PRODUCT_ID)
			.accept(MediaType.APPLICATION_JSON);
	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	    assertEquals(EXPECTED_VALUE, result.getResponse().getContentAsString());
	}
	
	/**
	 * Expects {@link ProductDetailsController#getProductDetails(Long)} to throw exception when product id is null.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetProductDetailsWhenProductIdIsNull() throws Exception {
	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/" + null)
			.accept(MediaType.APPLICATION_JSON);
	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	    assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), result.getResponse().getStatus());
	}
	
	/**
	 * Expects {@link ProductDetailsController#updateProductPriceDetails(Long, Product)} to run successfully when valid product id and price details are passed.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateProductPriceDetails() throws Exception {
	    Mockito.when(productDetailsService.updateProductPriceDetails(Mockito.anyLong(), Mockito.anyFloat())).thenReturn(true);
	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/" + PRODUCT_ID)
			.accept(MediaType.APPLICATION_JSON).content(EXPECTED_VALUE).contentType(MediaType.APPLICATION_JSON);
	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	    MockHttpServletResponse response = result.getResponse();
	    assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	/**
	 * Expects {@link ProductDetailsController#updateProductPriceDetails(Long, Product)} to run successfully when valid product id and price details are passed.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateProductPriceDetailsWhenProductIdIsNull() throws Exception {
	    Mockito.when(productDetailsService.updateProductPriceDetails(Mockito.anyLong(), Mockito.anyFloat())).thenReturn(true);
	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/" + null)
			.accept(MediaType.APPLICATION_JSON).content(EXPECTED_VALUE).contentType(MediaType.APPLICATION_JSON);
	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	    MockHttpServletResponse response = result.getResponse();
	    assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), response.getStatus());
	}
	
	/**
	 * Expects {@link ProductDetailsController#updateProductPriceDetails(Long, Product)} to run successfully when valid product id and price details are passed.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateProductPriceDetailsWhenProductIdIsNegative() throws Exception {
	    Mockito.when(productDetailsService.updateProductPriceDetails(Mockito.anyLong(), Mockito.anyFloat())).thenReturn(true);
	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/" + NEGATIVE_PRODUCT_ID)
			.accept(MediaType.APPLICATION_JSON).content(EXPECTED_VALUE).contentType(MediaType.APPLICATION_JSON);
	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	    MockHttpServletResponse response = result.getResponse();
	    assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), response.getStatus());
	}
}
