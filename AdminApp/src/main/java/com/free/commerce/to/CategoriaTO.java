package com.free.commerce.to;

import com.free.commerce.entity.TipoProduto;

/**
 * Created by pc on 11/04/2016.
 */
public class CategoriaTO {

    private String nome;

    private TipoProduto tipoProduto;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
}
