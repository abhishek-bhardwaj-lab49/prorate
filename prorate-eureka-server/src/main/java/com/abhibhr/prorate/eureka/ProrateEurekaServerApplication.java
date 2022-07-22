package com.abhibhr.prorate.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ProrateEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProrateEurekaServerApplication.class, args);
	}

}
