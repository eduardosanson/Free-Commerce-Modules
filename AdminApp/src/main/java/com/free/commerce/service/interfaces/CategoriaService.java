package com.free.commerce.service.interfaces;

import com.free.commerce.entity.Categoria;
import com.free.commerce.exception.RegraDeNegocioException;
import com.free.commerce.to.CategoriaTO;

import java.util.List;

/**
 * Created by pc on 03/04/2016.
 */
public interface CategoriaService {

    Categoria cadastrarCategoria(CategoriaTO categoriaTO) throws RegraDeNegocioException;

    List<Categoria> buscarCategoria();

    List<Categoria> buscarCategoriasPrincipais();

    Categoria buscarCategoriaPorId(Long id);

    Categoria buscarPorNome(String nome);

}
