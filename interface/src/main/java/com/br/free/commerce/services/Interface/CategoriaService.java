package com.br.free.commerce.services.Interface;

import com.br.free.commerce.to.CategoriaTO;
import com.free.commerce.entity.Categoria;

import java.util.List;

/**
 * Created by pc on 12/04/2016.
 */
public interface CategoriaService {

    List <Categoria> buscarTodasCategorias();

    Categoria cadastrarCategoria(CategoriaTO categoriaTO);

    List<Categoria> buscarCategoriasPrincipais();

    Categoria buscarPorId(Long catPaiId);

    Categoria buscarPorNome(String nome);

    Categoria buscarPaiPeloFilho(String id);
}
