package com.cm.bbNotificationApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BbNotificationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbNotificationApiApplication.class, args);
	}

}
