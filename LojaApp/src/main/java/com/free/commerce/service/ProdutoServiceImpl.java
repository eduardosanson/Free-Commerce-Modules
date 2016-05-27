package com.free.commerce.service;

import com.free.commerce.entity.Categoria;
import com.free.commerce.entity.Imagem;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.Produto;
import com.free.commerce.repository.FotoRepository;
import com.free.commerce.repository.ImageRepository;
import com.free.commerce.repository.LojaRepository;
import com.free.commerce.repository.ProductRepository;
import com.free.commerce.service.interfaces.CategoriaRepository;
import com.free.commerce.service.interfaces.CategoriaService;
import com.free.commerce.service.interfaces.ProdutoService;
import com.free.commerce.to.ProdutoTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by eduardo.sanson on 21/03/2016.
 */
@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private FotoRepository fotoRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private static Logger logger = Logger.getLogger(ProdutoServiceImpl.class);

    @Override
    public Page<Produto> recuperarProdutoPorLoja(Loja loja, Pageable pageable) {

        Page<Produto> produtos = repository.buscarProdutoPorLoja(loja,pageable);

        return produtos;
    }

    @Override
    public Produto CadastrarProduto(ProdutoTO produtoTO, Long lojaId) {

        Loja loja = lojaRepository.findOne(lojaId);
        Produto produto = criarProduto(produtoTO);
        produto.setIdentificadorDoProduto(loja.getId()+"|" + getMilis() + "|"+ random());
        produto.setLoja(loja);

        try{
            logger.info("iniciando persistencia de porduto: Produto: " + produto);
            Categoria categoria = associarCategoria(produtoTO);

            produto.setCategoria(categoria);

            Produto p = repository.save(produto);

            logger.info("produto persistido com sucesso " + produto.getId());

            return p;
        }catch (Exception e){
            e.printStackTrace();
            logger.info("Erro ao persistir produto: " + e.getMessage());

            return null;
        }

    }

    private Categoria associarCategoria(ProdutoTO produtoTO) {

        Categoria categoria = new Categoria();

        if (produtoTO.getCategoriaId()!=null && produtoTO.getCategoriaId()!=""){
            categoria = categoriaRepository.findOne(Long.parseLong(produtoTO.getCategoriaId()));
            return categoria;
        }else {
            return null;
        }
    }

    @Override
    public Produto buscarPorId(Long id) {

        Produto produto = null;

        try{
            produto = repository.findOne(id);

        }catch (Exception e){
            e.printStackTrace();
        }finally {

            return produto;
        }
    }

    @Override
    public Page<Produto> buscarProdutosParecidosPorNome(String nome,Pageable pageable) {
        Page<Produto> produtos = repository.buscarProdutoParecidosPorNome(nome,pageable);

        return produtos;
    }

    @Override
    public Produto alterarProduto(Produto produto) {
        Produto produtoPersistido = repository.findOne(produto.getId());

        return repository.save(updateProduto(produto, produtoPersistido));
    }

    @Override
    public void deletarImagem(long produtoId, long imagemId) {
        Produto produto = repository.findOne(produtoId);
        List<Imagem> imagems = produto.getImagens();
        produto.setImagens(null);
        produto = repository.save(produto);

        imagems.stream()
                .filter(i -> i.getId() == imagemId)
                .findAny()
                .ifPresent(i -> {
                    imagems.remove(i);
                    imageRepository.delete(i);
                });

        produto.setImagens(imagems);
        repository.save(produto);
    }

    private Produto updateProduto(Produto produto, Produto produtoPersistido) {
        produtoPersistido.setDescricao(produto.getDescricao());
        produtoPersistido.setDescricaoTetcnica(produto.getDescricaoTetcnica());
        produtoPersistido.setPreco(produto.getPreco());
        produtoPersistido.setNome(produto.getNome());
        produtoPersistido.setNovo(produto.isNovo());
        produtoPersistido.setQuantidade(produto.getQuantidade());

            produto.getImagens().stream()
                    .filter(i -> i.getId() == 0)
                    .forEach(i -> produtoPersistido.getImagens().add(imageRepository.save(i)));

        return produtoPersistido;
    }

    private boolean possuiImagemParaAlterar(Produto produto) {
        return produto.getImagens()!=null && !produto.getImagens().isEmpty();
    }

    private Produto merge(Produto produto, Produto produtoPersistido) {



        return produtoPersistido;
    }

    private Produto criarProduto(ProdutoTO produtoTO) {
        Produto produto = new Produto();
        produto.setNome(produtoTO.getNome());
        produto.setDescricao(produtoTO.getDescricao());
        produto.setDescricaoTetcnica(produtoTO.getDescricaoTetcnica());
        produto.setPreco(produtoTO.getPreco());
        produto.setRegistrado(new Date());
        produto.setImagens(produtoTO.getImagems());
        produto.setImagemPrincipal(produtoTO.getImagemPrincipal());
        produto.setNovo(produtoTO.isNovo());
        produto.setQuantidade(produtoTO.getQuantidade());

        return produto;
    }

    private Long getMilis(){
        Calendar cal;

        cal = new GregorianCalendar();

        return cal.getTimeInMillis();
    }

    private int random(){
        Random r = new Random(1000);

        return r.nextInt();
    }

}
