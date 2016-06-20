package com.free.commerce.service.interfaces;

import com.free.commerce.entity.Categoria;
import com.free.commerce.to.CategoriaTO;

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

    List<Categoria> buscarCategoriaPelaDescricao(String descricao);

    List<String> categoriasAssociadas(List<Categoria> categorias);
}
