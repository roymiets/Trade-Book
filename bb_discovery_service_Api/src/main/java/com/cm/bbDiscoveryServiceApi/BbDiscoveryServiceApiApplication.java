package com.cm.bbDiscoveryServiceApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BbDiscoveryServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbDiscoveryServiceApiApplication.class, args);
	}

}
