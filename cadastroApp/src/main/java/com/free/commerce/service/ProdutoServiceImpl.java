package com.free.commerce.service;

import com.free.commerce.entity.Loja;
import com.free.commerce.entity.Produto;
import com.free.commerce.repository.LojaRepository;
import com.free.commerce.repository.ProductRepository;
import com.free.commerce.service.interfaces.ProdutoService;
import com.free.commerce.to.ProdutoTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by eduardo.sanson on 21/03/2016.
 */
@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private LojaRepository lojaRepository;

    private static Logger logger = Logger.getLogger(ProdutoServiceImpl.class);

    @Override
    public Produto recuperarProdutoPorLoja(Loja loja) {
        return null;
    }

    @Override
    public Produto CadastrarProduto(ProdutoTO produtoTO, Long lojaId) {

        Loja loja = lojaRepository.findOne(lojaId);
        Produto produto = criarProduto(produtoTO);
        produto.setIdentificadorDoProduto(loja.getId()+"|" + getMilis() + "|"+ random());
        produto.setLoja(loja);

        try{
            logger.info("iniciando persistencia de porduto: Produto: " + produto);
            Produto p = repository.save(produto);

            logger.info("produto persistido com sucesso " + produto.getId());

            return p;
        }catch (Exception e){
            e.printStackTrace();
            logger.info("Erro ao persistir produto: " + e.getMessage());

            return null;
        }



    }

    private Produto criarProduto(ProdutoTO produtoTO) {
        Produto produto = new Produto();
        produto.setNome(produtoTO.getNome());
        produto.setDescricao(produtoTO.getDescricao());
        produto.setDescricaoTetcnica(produtoTO.getDescricaoTetcnica());
        produto.setPreco(produtoTO.getPreco());
        produto.setRegistrado(new Date());
        produto.setFotos(produtoTO.getFotos());

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
