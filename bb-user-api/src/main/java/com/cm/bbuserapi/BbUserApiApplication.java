package com.cm.bbuserapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.cm.*")
@EnableScheduling
@EnableFeignClients
public class BbUserApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BbUserApiApplication.class, args);
	}

}
