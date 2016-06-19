package com.br.free.commerce.to;

import java.math.BigDecimal;

/**
 * Created by pc on 19/06/2016.
 */
public class CarrinhoTO {

    private String valorPorProduto;

    private String valorCarrinho;

    private int quantideDoProduto;

    public String getValorPorProduto() {
        return valorPorProduto;
    }

    public void setValorPorProduto(String valorPorProduto) {
        this.valorPorProduto = valorPorProduto;
    }

    public String getValorCarrinho() {
        return valorCarrinho;
    }

    public void setValorCarrinho(String valorCarrinho) {
        this.valorCarrinho = valorCarrinho;
    }

    public int getQuantideDoProduto() {
        return quantideDoProduto;
    }

    public void setQuantideDoProduto(int quantideDoProduto) {
        this.quantideDoProduto = quantideDoProduto;
    }
}
