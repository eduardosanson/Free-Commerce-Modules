package com.free.commerce;

import com.free.commerce.entity.*;
import com.free.commerce.repository.*;
import com.free.commerce.service.AdminServiceImpl;
import com.free.commerce.service.CategoriaServiceImpl;
import com.free.commerce.to.AdministradorTO;
import com.free.commerce.to.CategoriaTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration
public class AdminApp {

    private static final Logger log = LoggerFactory.getLogger(AdminApp.class);

	public static void main(String[] args) {
		SpringApplication.run(AdminApp.class, args);
	}

	@Bean
	public CommandLineRunner Init(CorRepository corRepository,
								  MarcaRepository marcaRepository,
								  TamanhoLetraRepository tamanhoLetraRepository,
								  TamanhoNumeroRepository tamanhoNumeroRepository,
								  CategoriaServiceImpl categoriaService) {
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


			Categoria categoria;
			CategoriaTO categoriaTO = new CategoriaTO();


			try {

//
////			Categotia principal
//				String roupasID;
//				categoriaTO.setNome("Calçados, Roupas e Bolsas");
//				categoria = categoriaService.cadastrarCategoria(categoriaTO);
//				roupasID = String.valueOf(categoria.getId());
//
////			Criando subcategoria
//				categoriaTO.setNome("Vestidos");
//				categoriaTO.setCategoriaPaiId(roupasID);
//				String vestidosIds = String.valueOf(categoriaService.cadastrarCategoria(categoriaTO).getId());
//
//				//			Criando subcategoria
//				categoriaTO.setNome("Bermudas");
//				categoriaTO.setCategoriaPaiId(roupasID);
//				String bermudasIds = String.valueOf(categoriaService.cadastrarCategoria(categoriaTO).getId());
//
//				//			Criando subcategoria
//				categoriaTO.setNome("Calças");
//				categoriaTO.setCategoriaPaiId(roupasID);
//				String calcasIds = String.valueOf(categoriaService.cadastrarCategoria(categoriaTO).getId());
//
//				//			Criando subcategoria
//				categoriaTO.setNome("Casacos");
//				categoriaTO.setCategoriaPaiId(roupasID);
//				String casacosIds = String.valueOf(categoriaService.cadastrarCategoria(categoriaTO).getId());
//
//				//			Criando subcategoria
//				categoriaTO.setNome("Blazer");
//				categoriaTO.setCategoriaPaiId(casacosIds);
//				String blazerIds = String.valueOf(categoriaService.cadastrarCategoria(categoriaTO).getId());
//
//				//			Criando subcategoria
//				categoriaTO.setNome("Jaqueta");
//				categoriaTO.setCategoriaPaiId(casacosIds);
//				String jaquetaId = String.valueOf(categoriaService.cadastrarCategoria(categoriaTO).getId());
//
//				categoriaTO.setNome("Sobretudo");
//				categoriaTO.setCategoriaPaiId(casacosIds);
//				String sobreTudoId = String.valueOf(categoriaService.cadastrarCategoria(categoriaTO).getId());
//
//
//				//			Criando subcategoria
//				categoriaTO.setNome("Spatos");
//				categoriaTO.setCategoriaPaiId(roupasID);
//				String sapatosIds = String.valueOf(categoriaService.cadastrarCategoria(categoriaTO).getId());
//
//
//
//				//Categotia principal
//				categoriaTO = new CategoriaTO();
//				categoriaTO.setNome("Acessórios para Veículos");
//				categoria = categoriaService.cadastrarCategoria(categoriaTO);
//				String veiculoID = String.valueOf(categoria.getId());
//
//				//			Criando subcategoria
//				categoriaTO.setNome("Acessorio de Carro");
//				categoriaTO.setCategoriaPaiId(veiculoID);
//				String carroIds = String.valueOf(categoriaService.cadastrarCategoria(categoriaTO).getId());
//
//				//			Criando subcategoria
//				categoriaTO.setNome("Acessorio de Motos");
//				categoriaTO.setCategoriaPaiId(veiculoID);
//				String motosIds = String.valueOf(categoriaService.cadastrarCategoria(categoriaTO).getId());
//
//				//			Criando subcategoria
//				categoriaTO.setNome("GPS");
//				categoriaTO.setCategoriaPaiId(veiculoID);
//				String gpsIds = String.valueOf(categoriaService.cadastrarCategoria(categoriaTO).getId());
//
//
//				//			Criando subcategoria
//				categoriaTO.setNome("Acessorios");
//				categoriaTO.setCategoriaPaiId(gpsIds);
//				String gpsAcessoriosIds = String.valueOf(categoriaService.cadastrarCategoria(categoriaTO).getId());
//
//				//			Criando subcategoria
//				categoriaTO.setNome("Antenas");
//				categoriaTO.setCategoriaPaiId(gpsAcessoriosIds);
//				String gpsAcessoriosAtenasIds = String.valueOf(categoriaService.cadastrarCategoria(categoriaTO).getId());
//
//				//			Criando subcategoria
//				categoriaTO.setNome("Bateria e carregadores");
//				categoriaTO.setCategoriaPaiId(gpsAcessoriosIds);
//				String gpsAcessoriosAtenasBateriaeCarregadoresIds = String.valueOf(categoriaService.cadastrarCategoria(categoriaTO).getId());
//
//
//				//			Criando subcategoria
//				categoriaTO.setNome("Aparelhos");
//				categoriaTO.setCategoriaPaiId(gpsIds);
//				String gpsAparelhosIds = String.valueOf(categoriaService.cadastrarCategoria(categoriaTO).getId());
//
//
//
//
//
//
//
//
//				//Categotia principal
//				categoriaTO = new CategoriaTO();
//				categoriaTO.setNome("Livros");
//				categoria = categoriaService.cadastrarCategoria(categoriaTO);
//
//
//
//				//Categotia principal
//				categoriaTO = new CategoriaTO();
//				categoriaTO.setNome("Informática");
//				categoria = categoriaService.cadastrarCategoria(categoriaTO);
//
//
//
//
//				//Categotia principal
//				categoriaTO = new CategoriaTO();
//				categoriaTO.setNome("Games");
//				categoria = categoriaService.cadastrarCategoria(categoriaTO);
//
//
//
//
//				//Categotia principal
//				categoriaTO = new CategoriaTO();
//				categoriaTO.setNome("Esportes e Fitness");
//				categoria = categoriaService.cadastrarCategoria(categoriaTO);
//
//
//
//				//Categotia principal
//				categoriaTO = new CategoriaTO();
//				categoriaTO.setNome("Câmeras e Acessorios");
//				categoria = categoriaService.cadastrarCategoria(categoriaTO);
//
//
//
//				//Categotia principal
//				categoriaTO = new CategoriaTO();
//				categoriaTO.setNome("Eletrônicos Áudio e Video");
//				categoria = categoriaService.cadastrarCategoria(categoriaTO);

//				marcaRepository.save(marca);
//				tamanhoLetraRepository.save(tamanhoLetra);
//				corRepository.save(cor);
//				tamanhoNumeroRepository.save(tamanhoNumero);
			}catch (Exception e){
				log.error(e.getMessage());
			}


		};
	}

	@Bean
	public CommandLineRunner adm(AdminServiceImpl adminService) {
		return (args) -> {

			AdministradorTO administradorTO = new AdministradorTO();
			administradorTO.setMatricula("321642132as1d35");
			administradorTO.setLogin("admin@gmail.com");
			administradorTO.setSenha("123");
			administradorTO.setNome("Admin");

			try {

				adminService.criarAdm(administradorTO);

			}catch (Exception e){
				log.error(e.getMessage());
			}


		};
	}


}
