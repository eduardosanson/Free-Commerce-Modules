package com.br.free.commerce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;

@SpringBootApplication
public class InterfaceApplication extends WebMvcConfigurerAdapter{

	public static String ROOT = "upload-dir";

	public static void main(String[] args) {
		SpringApplication.run(InterfaceApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return (String[] args) -> {
			new File(ROOT).mkdir();
		};
	}


}
