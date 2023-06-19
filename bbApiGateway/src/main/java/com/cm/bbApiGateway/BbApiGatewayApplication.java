package com.cm.bbApiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BbApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbApiGatewayApplication.class, args);
	}

}
