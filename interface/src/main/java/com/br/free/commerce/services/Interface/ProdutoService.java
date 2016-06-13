package com.br.free.commerce.services.Interface;

import com.br.free.commerce.to.BuscarProdutoTO;
import com.br.free.commerce.to.ProdutoPage;
import com.br.free.commerce.to.ProdutoTO;
import com.free.commerce.entity.CarrinhoDeCompras;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.Produto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by pc on 21/03/2016.
 */
public interface ProdutoService {

    Produto cadastrarProduto(Loja loja, ProdutoTO produtoTO);

    ProdutoPage recuperarProdutosDeLoja(Loja loja,String pagina,String itemPorPagina);

    Produto buscarProdutoPorId(String id);

    ProdutoPage buscarProdutos(BuscarProdutoTO buscarProdutoTO);

    void alterarProduto(Produto produto);

    void associarImagem(List<MultipartFile> file,String produtoId);

    void deletarImagem(long produtoId, long imagemId);


}
