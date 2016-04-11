package com.free.commerce.service;

import com.free.commerce.entity.Categoria;
import com.free.commerce.repository.CategoriaRepository;
import com.free.commerce.service.interfaces.CategoriaService;
import com.free.commerce.to.CategoriaTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pc on 03/04/2016.
 */
@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;

    private static Logger logger = Logger.getLogger(CategoriaServiceImpl.class);

    @Override
    public Categoria cadastrarCategoria(CategoriaTO categoriaTO) {
        Categoria categoria = criarCategoria(categoriaTO);

        try{
            categoria = categoriaRepository.save(categoria);

        }catch (Exception e){
            logger.info("Erro ao cadastrar categoria");

        }
        return categoria;
    }

    private Categoria criarCategoria(CategoriaTO categoriaTO) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaTO.getNome());

        return categoria;
    }

    @Override
    public List<Categoria> buscarCategoria() {

        return (List<Categoria>) categoriaRepository.findAll();
    }
}
