package com.free.commerce.to;

import com.free.commerce.entity.Produto;

import java.util.List;

/**
 * Created by pc on 27/04/2016.
 */
public class CarrinhoDeComprasTO {

    private String idExterno;

    private Long produtoId;

    private int quantidade;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(String idExterno) {
        this.idExterno = idExterno;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }
}
