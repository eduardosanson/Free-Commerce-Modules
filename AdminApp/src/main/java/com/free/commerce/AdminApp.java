package com.free.commerce;

import com.free.commerce.entity.Cor;
import com.free.commerce.entity.Marca;
import com.free.commerce.entity.TamanhoLetra;
import com.free.commerce.entity.TamanhoNumero;
import com.free.commerce.repository.CorRepository;
import com.free.commerce.repository.MarcaRepository;
import com.free.commerce.repository.TamanhoLetraRepository;
import com.free.commerce.repository.TamanhoNumeroRepository;
import com.mangofactory.swagger.plugin.EnableSwagger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableSwagger
@EnableAutoConfiguration
public class AdminApp {

    private static final Logger log = LoggerFactory.getLogger(AdminApp.class);

	public static void main(String[] args) {
		SpringApplication.run(AdminApp.class, args);
	}

	@Bean
	public CommandLineRunner demo(CorRepository corRepository, MarcaRepository marcaRepository, TamanhoLetraRepository tamanhoLetraRepository, TamanhoNumeroRepository tamanhoNumeroRepository) {
		return (args) -> {
			// save a couple of customers

			Cor cor = new Cor();
			cor.setNome("Azul");


			Marca marca = new Marca();
			marca.setNome("Nike");

			TamanhoLetra tamanhoLetra = new TamanhoLetra();
			tamanhoLetra.setNumeroLetra("M");

			TamanhoNumero tamanhoNumero = new TamanhoNumero();
			tamanhoNumero.setNome("40");


			try {

				marcaRepository.save(marca);
				tamanhoLetraRepository.save(tamanhoLetra);
				corRepository.save(cor);
				tamanhoNumeroRepository.save(tamanhoNumero);
			}catch (Exception e){
				log.error(e.getMessage());
			}


		};
	}


}
