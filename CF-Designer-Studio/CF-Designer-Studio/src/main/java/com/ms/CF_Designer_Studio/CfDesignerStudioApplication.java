package com.ms.CF_Designer_Studio;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
public class CfDesignerStudioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CfDesignerStudioApplication.class, args);
	}

}
