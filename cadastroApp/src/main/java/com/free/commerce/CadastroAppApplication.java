package com.free.commerce;

import com.free.commerce.entity.Cliente;
import com.free.commerce.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableAutoConfiguration
public class CadastroAppApplication {

    private static final Logger log = LoggerFactory.getLogger(CadastroAppApplication.class);

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
    public CommandLineRunner demo(ClienteRepository repository) {
        return (args) -> {
            // save a couple of customers


            repository.save(new Cliente("eduardo","teste","cpf1234","54611",null,null,null));
            repository.save(new Cliente("eduardo","teste","cpf1235","54611",null,null,null));
            repository.save(new Cliente("eduardo","teste","cpf1236","54611",null,null,null));
            repository.save(new Cliente("eduardo","teste","cpf1237","54611",null,null,null));
            repository.save(new Cliente("eduardo","teste","cpf1238","54611",null,null,null));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Cliente customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Cliente customer = repository.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");

            log.info("");
        };
    }
}
