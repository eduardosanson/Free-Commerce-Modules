package com.br.free.commerce.to;

import com.free.commerce.entity.Categoria;
import com.free.commerce.entity.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 25/04/2016.
 */
public class ProdutoView {

    private Produto produto;

    private List<Categoria> categorias;

    public ProdutoView() {
        this.categorias = new ArrayList<>();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}
