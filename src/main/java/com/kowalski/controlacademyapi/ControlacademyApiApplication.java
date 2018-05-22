package com.kowalski.controlacademyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.kowalski.controlacademyapi.config.property.ApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(ApiProperty.class)
public class ControlacademyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlacademyApiApplication.class, args);
	}
}