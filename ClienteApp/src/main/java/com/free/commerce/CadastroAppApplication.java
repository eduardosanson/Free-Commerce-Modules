package com.free.commerce;

import com.free.commerce.entity.Cliente;
import com.free.commerce.entity.Endereco;
import com.free.commerce.entity.Enums.Role;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.UserLogin;
import com.free.commerce.repository.ClienteRepository;
import com.free.commerce.service.ClienteServiceImpl;
import com.free.commerce.service.interfaces.ClienteService;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableSwagger
@EnableAutoConfiguration
public class CadastroAppApplication {

    private static final Logger log = LoggerFactory.getLogger(CadastroAppApplication.class);

    private SpringSwaggerConfig springSwaggerConfig;

	public static void main(String[] args) {
		SpringApplication.run(CadastroAppApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/continueFileUpload").allowedOrigins("http://localhost:8080");
			}
		};
	}

    @Bean
    public CommandLineRunner demo(ClienteServiceImpl clienteService) {
        return (args) -> {


        };
    }
}
