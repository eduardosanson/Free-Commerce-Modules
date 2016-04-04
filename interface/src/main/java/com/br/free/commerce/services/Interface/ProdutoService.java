package com.br.free.commerce.services.Interface;

import com.br.free.commerce.to.ProdutoPage;
import com.br.free.commerce.to.ProdutoTO;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.Produto;

/**
 * Created by pc on 21/03/2016.
 */
public interface ProdutoService {

    Produto cadastrarProduto(Loja loja, ProdutoTO produtoTO);

    ProdutoPage recuperarProdutosDeLoja(Loja loja,String pagina,String itemPorPagina);

    Produto buscarProdutoPorId(String id);
}
