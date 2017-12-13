package com.restful.project.dao;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.restful.project.exception.ProductNotFoundException;

/**
 * {@inheritDoc}
 * @author Latha Muddu
 *
 */
@Service
@PropertySource("classpath:application.properties")
public class ProductDetailsDAOImpl implements ProductDetailsDAO {
	
	private static final String PRODUCT_NOT_FOUND = "No product found with the given product id";
	private static final String PRODUCT = "product";
	private static final String ITEM = "item";
	private static final String PRODUCT_DESCRIPTION = "product_description";
	private static final String TITLE = "title";
	
	@Value("${myRetail.product.name.baseurl}")
	private String productNameUrl;
	
	@Value("${myRetail.product.name.excludeparams}")
	private String productNameParametersUrl;
	
	private RestTemplate restTemplate = new RestTemplate();

	/***
	 * {@inheritDoc}
	 */
	@Override
	public String getProductName(Long productId) throws Exception {
	    String productNameEndPointUrl = productNameUrl + productId + productNameParametersUrl;
	    String productName = null;
	    try {
		productName = restTemplate.getForObject(productNameEndPointUrl, String.class);
	    } catch (HttpClientErrorException httpClientErrorException) {
		throw new ProductNotFoundException(PRODUCT_NOT_FOUND);
	    }
	    if (productName == null) {
		throw new ProductNotFoundException(PRODUCT_NOT_FOUND);
	    }
	    JSONObject requestJsonObject = new JSONObject(productName);
	    return parseJson(requestJsonObject);
	}
	
	/**
	 * This method would parse the json object and builds the product name.
	 * 
	 * @param requestJsonObject
	 * 		Json object which holds the data related to the product name for the given product id.
	 * 
	 * @return {@link String} containing the product name.
	 * 
	 * @throws JSONException
	 * 		throws exception when the json object is not able to be parsed.
	 */
	public String parseJson(JSONObject requestJsonObject) throws JSONException {
	    JSONObject product = requestJsonObject.getJSONObject(PRODUCT);
	    JSONObject item = product.getJSONObject(ITEM);
	    JSONObject productDescription = item.getJSONObject(PRODUCT_DESCRIPTION);
	    return productDescription.getString(TITLE);
	}

}
