package com.restful.project.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.restful.project.dao.ProductDetailsDAOImpl;
import com.restful.project.model.PriceDetails;
import com.restful.project.model.Product;
import com.restful.project.model.ProductPriceDetails;
import com.restful.project.repository.ProductPriceDetailsRepository;
import com.restful.project.service.ProductDetailsService;

/**
 * This class tests whether the product details are retrieved as expected.
 * 
 * @author Latha Muddu
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDetailsServiceTest {
	
	@InjectMocks
	private ProductDetailsService productDetailsService;
	@Mock
	private ProductDetailsDAOImpl productDetailsDAOImpl;
	@Mock
	private ProductPriceDetailsRepository productPriceDetailsRepository;

	private static final Long PRODUCT_ID = (long) 13860428;
	private final PriceDetails priceDetails = new PriceDetails((float) 529.9, "USD");
	private final Product mockProduct = new Product(PRODUCT_ID, "The Big Lebowski (Blu-ray)", priceDetails);

	/**
	 * Expects {@link ProductDetailsService#getProductDetails(Long)} to run successfully when a valid product id is passed.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetProductDetails() throws Exception {
	    ProductPriceDetails productPriceDetails = new ProductPriceDetails();
	    productPriceDetails.setCurrency(priceDetails.getCurrency());
	    productPriceDetails.setPrice(priceDetails.getPrice());
	    productPriceDetails.setProductID(PRODUCT_ID);

	    Mockito.when(productDetailsDAOImpl.getProductName(PRODUCT_ID)).thenReturn(mockProduct.getProductName());
	    when(productPriceDetailsRepository.getProductPrice(PRODUCT_ID)).thenReturn(productPriceDetails);

	    Product result = productDetailsService.getProductDetails(PRODUCT_ID);
	    assertEquals(result.getPriceDetails().getCurrency(), mockProduct.getPriceDetails().getCurrency());
	    assertEquals(result.getPriceDetails().getPrice(), mockProduct.getPriceDetails().getPrice());
	    assertEquals(result.getProductId(), mockProduct.getProductId());
	    assertEquals(result.getProductName(), mockProduct.getProductName());
	}
}
