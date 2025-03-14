package com.ms.CF_DD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CfDdApplication {

	public static void main(String[] args) {
		SpringApplication.run(CfDdApplication.class, args);
	}

}
