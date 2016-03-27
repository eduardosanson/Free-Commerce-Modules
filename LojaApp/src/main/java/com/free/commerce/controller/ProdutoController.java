package com.free.commerce.controller;

import com.free.commerce.entity.Loja;
import com.free.commerce.entity.Produto;
import com.free.commerce.service.interfaces.LojaService;
import com.free.commerce.service.interfaces.ProdutoService;
import com.free.commerce.to.ProdutoPage;
import com.free.commerce.to.ProdutoTO;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scala.util.parsing.combinator.testing.Str;

import java.util.Iterator;
import java.util.List;

/**
 * Created by pc on 21/03/2016.
 */
@RestController("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private LojaService lojaService;

    @RequestMapping(params = "lojaId", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "Say Hello To World using Swagger")
    public ResponseEntity<Produto> cadastrarProduto(@RequestParam("lojaId") Long lojaId, @RequestBody ProdutoTO produtoTO){

        Produto produto = produtoService.CadastrarProduto(produtoTO,lojaId);


        return new ResponseEntity<Produto>(produto,HttpStatus.CREATED);
    }

    @RequestMapping(params = {"lojaId","page"},method = RequestMethod.GET)
    public ResponseEntity<Page<Produto>> recuperarProdutos(@RequestParam("lojaId") String lojaId, @RequestParam("page") String page){

        Loja loja = lojaService.recuperarPorId(Long.parseLong(lojaId));
        Page<Produto> produtos = produtoService.recuperarProdutoPorLoja(loja,pegarPagina(page,"3"));


        return new ResponseEntity<Page<Produto>>(produtos ,HttpStatus.OK);
    }

    @RequestMapping(params = {"lojaId","firstIndice","lastIndice"},method = RequestMethod.GET)
    public ResponseEntity<ProdutoPage> recuperarProdutosQuery(@RequestParam("lojaId") String lojaId,
                                                                    @RequestParam("firstIndice") String first,
                                                                    @RequestParam("lastIndice") String last){

        Loja loja = lojaService.recuperarPorId(Long.parseLong(lojaId));
        Page<Produto> produtos = produtoService.recuperarProdutoPorLoja(loja,pegarPagina(first,last));

        ProdutoPage produtoPage = new ProdutoPage();
        produtoPage.setProdutos(produtos.getContent());
        produtoPage.setTotalElements(produtos.getNumberOfElements());
        produtoPage.setTotalPages(produtos.getTotalPages());
        produtoPage.setNumberOfElements(produtos.getNumberOfElements());

        return new ResponseEntity<ProdutoPage>(produtoPage ,HttpStatus.OK);
    }

    private Pageable pegarPagina(String page,String limite){
        return new PageRequest(Integer.parseInt(page),Integer.parseInt(limite));
    }
}
