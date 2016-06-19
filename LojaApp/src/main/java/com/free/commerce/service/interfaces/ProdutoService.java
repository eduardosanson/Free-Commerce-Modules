package com.free.commerce.service.interfaces;

import com.free.commerce.entity.Loja;
import com.free.commerce.entity.Produto;
import com.free.commerce.to.BuscarProdutoTO;
import com.free.commerce.to.ProdutoTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by eduardo.sanson on 21/03/2016.
 */
public interface ProdutoService {

    Page<Produto> recuperarProdutoPorLoja(Loja loja, Pageable pageable);

    Produto CadastrarProduto(ProdutoTO produtoTO,Long lojaId);

    Produto buscarPorId(Long id);

    Page<Produto> buscarProdutosParecidosPorNome(String nome,Pageable pageable);

    Page<Produto> buscarProdutosPorCategoria(String categoria,Pageable pageable);

    Produto alterarProduto(Produto produto);

    void deletarImagem(long produtoId, long imagemId);

    List<Produto> recuperarUltimosCincoProduto();

    Page<Produto> buscarProdutosMaisBaratos();

    Page<Produto> buscarProdutosPorCidade(String cidade);

    Page<Produto> buscarProdutosPorCidadeECategoria(String cidade, String categoria);

    Page<Produto> buscarProdutos(BuscarProdutoTO buscarProdutoTO,Pageable pageable);

    void salvar(Produto produto);
}
