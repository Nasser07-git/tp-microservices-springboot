package com.esmt.m223isi.microservices.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ApiGatewayConfiguration {

	  @Bean
	  public RouteLocator gatewayRouter (RouteLocatorBuilder builder) {
	    //Function<PredicateSpec, Buildable<Route>> routeFunction=
	      //  p-> p.path("/get").uri("http://httpbin.org:80");
	        
		  return builder.routes()
				  .route(p-> p
						  .path("/get")
						  .filters(f->f
								  .addRequestHeader("myHeaderParam", "HeaderParamValue")
								  .addRequestParameter("MyParameter", "ParamValue"))
						  .uri("lb://currency-exchange")) 
				  .route(p -> p
						  .path("/currency-exchange/**")
						  .uri("lb://currency-exchange"))
				  .route(p -> p
						  .path("/currency-conversion/**")
						  .uri("lb://currency-conversion"))
				  .build();
	  }
}
