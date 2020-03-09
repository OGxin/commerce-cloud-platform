package com.ozx.ozxshopbasicsspringcloudeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class OzxShopBasicsSpringcloudEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OzxShopBasicsSpringcloudEurekaApplication.class, args);
	}

}
