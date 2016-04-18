package com.free.commerce.service;

import com.free.commerce.entity.Categoria;
import com.free.commerce.exception.RegraDeNegocioException;
import com.free.commerce.exception.enuns.RegraDeNegocioEnum;
import com.free.commerce.repository.CategoriaRepository;
import com.free.commerce.service.interfaces.CategoriaService;
import com.free.commerce.to.CategoriaTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Categoria cadastrarCategoria(CategoriaTO categoriaTO) throws RegraDeNegocioException {
        Categoria categoria = criarCategoria(categoriaTO);


        if (categoriaRepository.buscarPeloNome(categoria.getNome())!=null){
            throw new RegraDeNegocioException(RegraDeNegocioEnum.VALOR_JA_CADASTRADO);
        }

        categoria = categoriaRepository.save(categoria);

        associarCategoriaPai(categoriaTO,categoria);

        return categoria;
    }

    private void associarCategoriaPai(CategoriaTO categoriaTO,Categoria categoria) {
        Categoria categoriaPai =null;
        List<Categoria> categorias = new ArrayList<>();

        if (categoriaTO.getCategoriaPaiId()!=null && categoriaTO.getCategoriaPaiId()!=""
                &&!categoriaTO.getCategoriaPaiId().isEmpty()){
            categoriaPai = categoriaRepository.findOne(Long.parseLong(categoriaTO.getCategoriaPaiId()));
            categoriaPai.getCategorias().add(categoria);
            categoriaRepository.save(categoriaPai);

        }
    }

    private Categoria criarCategoria(CategoriaTO categoriaTO) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaTO.getNome());
        categoria.setPrincipal(categoriaTO.isPrincipal());

        return categoria;
    }

    @Override
    public List<Categoria> buscarCategoria() {

        return (List<Categoria>) categoriaRepository.findAll();
    }

    @Override
    public List<Categoria> buscarCategoriasPrincipais() {
        return categoriaRepository.buscarPrincipais();
    }

    @Override
    public Categoria buscarCategoriaPorId(Long id) {
        Categoria categoria=null;

        categoria = categoriaRepository.findOne(id);

        return categoria;
    }

    @Override
    public Categoria buscarPorNome(String nome) {
        Categoria categoria=null;

        categoria = categoriaRepository.buscarPeloNome(nome);

        return categoria;
    }
}
