package com.br.free.commerce.services;

import com.br.free.commerce.services.Interface.CategoriaService;
import com.br.free.commerce.to.CategoriaTO;
import com.free.commerce.entity.Categoria;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 12/04/2016.
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    private RestTemplate restTemplate;

    private static Logger logger = Logger.getLogger(CategoriaServiceImpl.class);

    @PostConstruct
    public void creatRest(){
        restTemplate = new RestTemplate();
    }

    @Override
    public List<Categoria> buscarTodasCategorias() {
        String url = "http://localhost:8089/v1/categoria";
        List<Categoria> categorias = null;

        logger.info("Buscando categorias para a url: " + url );

        try{

            categorias = restTemplate.getForObject(url, ArrayList.class);

        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }

        return categorias;
    }

    @Override
    public Categoria cadastrarCategoria(CategoriaTO categoriaTO) {
        String url = "http://localhost:8089/v1/categoria";

        Categoria categoria = restTemplate.postForObject(url,categoriaTO,Categoria.class);
        return categoria;
    }

    @Override
    public List<Categoria> buscarCategoriasPrincipais() {
        String url = "http://localhost:8089/v1/categoria?principal=true";
        List<Categoria> categorias = null;

        logger.info("Buscando categorias principais para a url: " + url );

        try{

            categorias = restTemplate.getForObject(url, ArrayList.class);

        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }

        return categorias;
    }

    @Override
    public Categoria buscarPorId(Long catPaiId) {
        String url = "http://localhost:8089/v1/categoria/"+catPaiId;
        Categoria categoria = null;

        logger.info("Buscando categorias principais para a url: " + url );

        try{

            categoria = restTemplate.getForObject(url, Categoria.class);

        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }

        return categoria;
    }

    @Override
    public Categoria buscarPorNome(String nome) {
        String url = "http://localhost:8089/v1/categoria?nome="+nome;
        Categoria categoria = null;

        logger.info("Buscando categorias principais para a url: " + url );

        try{

            categoria = restTemplate.getForObject(url, Categoria.class);

        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }

        return categoria;
    }

    @Override
    public Categoria buscarPaiPeloFilho(String id) {
        String url = " http://localhost:8089/v1/categoria?filho=" + id;

        Categoria categoria = null;

        logger.info("Buscando categorias principais para a url: " + url );

        try{

            categoria = restTemplate.getForObject(url, Categoria.class);

        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }

        return categoria;

    }
}
