package com.pruebatecnica.ventas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.pruebatecnica.lib.repository")
@EntityScan(basePackages = "com.pruebatecnica.lib.entity")
@ComponentScan(basePackages = {"com.pruebatecnica.lib.utils", "com.pruebatecnica.ventas"})
public class MsVentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsVentasApplication.class, args);
	}

}
