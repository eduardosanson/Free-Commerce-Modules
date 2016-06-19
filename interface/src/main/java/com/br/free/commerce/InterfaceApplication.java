package com.br.free.commerce;

import com.br.free.commerce.interceptor.Interceptor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

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

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/paymentNotifyController/notify").allowedOrigins("https://sandbox.pagseguro.uol.com.br");
			}
		};
	}

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload-dir/**")
				.addResourceLocations("/upload-dir/", "file:upload-dir/");
	}

	@Bean
	public WebMvcConfigurerAdapter adapter() {
		return new WebMvcConfigurerAdapter() {

			@Override
			public void addInterceptors(InterceptorRegistry registry) {

				System.out.println("Adding interceptors");
				registry.addInterceptor(new Interceptor());
				super.addInterceptors(registry);
			}
		};
	}

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

}
