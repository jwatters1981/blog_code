package org.springwebservices.integration.server;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springwebservices.product.schema.beans.GetProductRequest;

public class ColumnAppender extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		JaxbDataFormat jaxb = new JaxbDataFormat(GetProductRequest.class
				.getPackage().getName());

		from(
				"spring-ws:rootqname:{http://www.springwebservices.org/product/schema/beans}get-product-request?endpointMapping=#endpointMapping")
				.unmarshal(jaxb).process(new Processor() {

					@Override
					public void process(Exchange exchange) throws Exception {
			            GetProductRequest request = exchange.getIn().getBody(GetProductRequest.class);
			            String result = request.getName() +"_APPEND"; 
			            exchange.getOut().setBody(result);
					}
				}).convertBodyTo(String.class).to("file://C:\\test");

	}

}
