package com.free.commerce;

import com.free.commerce.entity.Cliente;
import com.free.commerce.entity.Endereco;
import com.free.commerce.entity.Enums.Role;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.UserLogin;
import com.free.commerce.repository.ClienteRepository;
import com.free.commerce.repository.LojaRepository;
import com.free.commerce.service.interfaces.AutorizacaoService;
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

import java.util.Date;

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
    public CommandLineRunner demo(LojaRepository repository, AutorizacaoService autorizacaoService) {
        return (args) -> {
            // save a couple of customers

            try{
                Loja loja = new Loja();
                UserLogin login = new UserLogin();
                Endereco endereco = new Endereco();

                endereco.setNome("Rua Capitão Resende");
                endereco.setBairro("Cachambi");
                endereco.setCep("20780190");
                endereco.setCidade("Rio de Janeiro");
                endereco.setNumero("150");

                login.setLogin("eduardo@gmail.com");
                login.setSenha("123");
                login.setPermissao(Role.STORE);

                loja.setCnpjOuCpf("10468330739");
                loja.setEmail("eduardo@gmail.com");
                loja.setEndereco(endereco);
                loja.setNome("OAS");
                loja.setNomeEmpresa("Grupo Empreiteira LTDA");
                loja.setTelefone("219855531620");
                loja.setUserLogin(login);
                loja.setRegistrado(new Date());

                Loja loja1 = repository.save(loja);

                autorizacaoService.solicitarAutorizacao(String.valueOf(loja1.getId()));


                // fetch all customers
                log.info("Customers found with findAll():");
                log.info("-------------------------------");
                for (Loja customer : repository.findAll()) {
                    log.info(customer.toString());
                }
                log.info("");

                // fetch an individual customer by ID
                Loja customer = repository.findOne(1L);
                log.info("Customer found with findOne(1L):");
                log.info("--------------------------------");
                log.info(customer.toString());
                log.info("");

                // fetch customers by last name
                log.info("--------------------------------------------");

                log.info("");

            }catch (Exception e){

            }

        };
    }
}
