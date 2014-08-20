/*
 * Copyright 2007-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springwebservices.ws.embedded.service;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springwebservices.product.schema.beans.GetProductRequest;
import org.springwebservices.product.schema.beans.Product;
import org.springwebservices.product.schema.beans.ProductResponse;

/**
 * 
 * @author john.watters
 *
 */
@Endpoint
public class SampleEndpoint {

	public final static String NAMESPACE = "http://www.springwebservices.org/product/schema/beans";
	public final static String GET_PERSONS_REQUEST = "get-product-request";

	/**
	 * 
	 * @param code
	 * @return
	 */
	@PayloadRoot(localPart = GET_PERSONS_REQUEST, namespace = NAMESPACE)
	public @ResponsePayload ProductResponse getProducts(@RequestPayload GetProductRequest  code) {
		ProductResponse productResponse = new ProductResponse();
		Product product = new Product();
		product.setCode("Code1");
		product.setPrice(100.00);
		product.setDescription("test");
		productResponse.getProduct().add(product);
		return productResponse;
	}

}
