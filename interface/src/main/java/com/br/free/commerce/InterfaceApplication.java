package com.br.free.commerce;

import com.br.free.commerce.interceptor.Interceptor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

}
