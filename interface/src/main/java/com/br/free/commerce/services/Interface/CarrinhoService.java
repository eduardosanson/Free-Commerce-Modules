package com.br.free.commerce.services.Interface;

import com.free.commerce.entity.CarrinhoDeCompras;

import java.util.List;

/**
 * Created by pc on 30/04/2016.
 */
public interface CarrinhoService {

    void SalvarCarrinhoDeComprasDosProdutos(String cookieId, Long produtoId, int quantidade);

    List<CarrinhoDeCompras> recuperarProdutosDeCarrinho(String cookieId);
}
