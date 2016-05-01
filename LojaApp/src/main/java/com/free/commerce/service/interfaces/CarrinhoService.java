package com.free.commerce.service.interfaces;

import com.free.commerce.entity.CarrinhoDeCompras;
import com.free.commerce.entity.Produto;
import com.free.commerce.to.CarrinhoDeComprasTO;
import com.free.commerce.to.ProdutoDeCarrinhoDeCompraTO;

import java.util.List;

/**
 * Created by pc on 27/04/2016.
 */
public interface CarrinhoService {

    List<CarrinhoDeCompras> buscarCarProdutoPeloCookie(String cookieId);

    void cadastrarCarrinhoDeCompras(CarrinhoDeComprasTO carrinhoDeComprasTO);

    CarrinhoDeCompras recuperarProdutoDeCarrinhoDeCompras(ProdutoDeCarrinhoDeCompraTO produtoDeCarrinhoDeCompraTO);
}
