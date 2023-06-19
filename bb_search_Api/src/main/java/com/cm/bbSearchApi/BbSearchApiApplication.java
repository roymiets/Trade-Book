package com.cm.bbSearchApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BbSearchApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbSearchApiApplication.class, args);
	}

}
