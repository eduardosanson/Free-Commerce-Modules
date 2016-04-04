package com.free.commerce.service.interfaces;

import com.free.commerce.entity.Categoria;
import com.free.commerce.entity.ClassificacaoProduto;
import com.free.commerce.entity.Setor;
import com.free.commerce.entity.TipoProduto;
import com.free.commerce.exception.RegraDeNegocioException;
import com.free.commerce.to.CadastrarCategoriaTO;
import com.free.commerce.to.CadastrarClasificaoProdutoTO;
import com.free.commerce.to.CadastrarSetorTO;
import com.free.commerce.to.CadastrarTipoProdutoTO;
import org.springframework.stereotype.Service;

/**
 * Created by pc on 03/04/2016.
 */
public interface SetorService {

    Setor cadastrarSetor(CadastrarSetorTO cadastrarSetorTO) throws RegraDeNegocioException;

    Categoria cadastrarCategoria(CadastrarCategoriaTO cadastrarCategoriaTO);

    TipoProduto cadastrarTipoProduto(CadastrarTipoProdutoTO cadastrarTipoProdutoTO);

    ClassificacaoProduto cadastrarClassificacaoProduto(CadastrarClasificaoProdutoTO cadastrarClasificaoProdutoTO);
}
