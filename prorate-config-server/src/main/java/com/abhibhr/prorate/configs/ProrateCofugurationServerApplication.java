package com.abhibhr.prorate.configs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ProrateCofugurationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProrateCofugurationServerApplication.class, args);
	}

}
