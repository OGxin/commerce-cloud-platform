package com.ozx.ozxshopbasicsxxlssoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class OzxShopBasicsXxlSsoServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OzxShopBasicsXxlSsoServerApplication.class, args);
	}

}