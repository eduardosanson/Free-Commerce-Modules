package com.free.commerce.service.interfaces;

import com.free.commerce.entity.Loja;
import com.free.commerce.entity.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by eduardo.sanson on 21/03/2016.
 */
public interface ProdutoService {

    Produto recuperarProdutoPorLoja(Loja loja);

}
