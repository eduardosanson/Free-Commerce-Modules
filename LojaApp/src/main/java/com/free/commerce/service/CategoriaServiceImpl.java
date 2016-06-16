package com.free.commerce.service;

import com.free.commerce.entity.Categoria;
import com.free.commerce.repository.CategoriaRepository;
import com.free.commerce.service.interfaces.CategoriaService;
import com.free.commerce.to.CategoriaTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by pc on 12/04/2016.
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    private RestTemplate restTemplate;

    private static Logger logger = Logger.getLogger(CategoriaServiceImpl.class);

    @PostConstruct
    public void creatRest(){
        restTemplate = new RestTemplate();
    }

    @Override
    public List<Categoria> buscarTodasCategorias() {
        String url = "http://adminap.herokuapp.com/categoria";
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
        String url = "http://adminap.herokuapp.com/categoria";

        Categoria categoria = restTemplate.postForObject(url,categoriaTO,Categoria.class);
        return categoria;
    }

    @Override
    public List<Categoria> buscarCategoriasPrincipais() {
        String url = "http://adminap.herokuapp.com/categoria?principal=true";
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
        String url = "http://adminap.herokuapp.com/categoria/"+catPaiId;
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
        String url = "http://adminap.herokuapp.com/categoria?nome="+nome;
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
    public Categoria buscarCategoriaPelaDescricao(String descricao){
        return repository.findByDescricaoLike("%"+descricao+"%");
    }

    @Override
    public List<String> categoriasAssociadas(Categoria categoria){
        List<String> categorias = new ArrayList<>();

        if (categoria!=null){
            categoria.setPai(null);
        }
        while (trata(categoria,categorias)!=null){

        }
        return categorias;
    }


    public Categoria trata(Categoria categoria, List<String> categoriaDescricao) {
        if (categoria !=null) {
            int size = categoria.getFilhos() != null || !categoria.getFilhos().isEmpty() ? categoria.getFilhos().size() : 0;

            if (size == 0) {
                categoriaDescricao.add(categoria.getDescricao());
                if (categoria.getPai()==null){
                    return null;
                }
                for (int f = 0; f < categoria.getPai().getFilhos().size(); f++) {
                    if (categoria.getPai().getFilhos().get(f).getDescricao().equalsIgnoreCase(categoria.getDescricao())) {
                        categoria.getPai().getFilhos().remove(categoria);
                    }
                }
                return categoria.getPai();
            }

            for (int i = 0; i < size; i++) {
                return this.trata(categoria.getFilhos().get(i), categoriaDescricao);
            }
        }

        return null;

    }


}
