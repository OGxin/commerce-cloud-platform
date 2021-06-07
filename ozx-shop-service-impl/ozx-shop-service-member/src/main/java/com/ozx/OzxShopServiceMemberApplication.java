package com.ozx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.ozx.ozxshopservicemember.mapper")
@EnableSwagger2
@EnableFeignClients
@EnableCircuitBreaker
public class OzxShopServiceMemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(OzxShopServiceMemberApplication.class, args);
	}

}
