package com.free.commerce.controller;

import com.free.commerce.entity.Loja;
import com.free.commerce.entity.Produto;
import com.free.commerce.service.interfaces.LojaService;
import com.free.commerce.service.interfaces.ProdutoService;
import com.free.commerce.to.ProdutoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Produto> cadastrarProduto(@RequestParam("lojaId") Long lojaId, @RequestBody ProdutoTO produtoTO){

        Produto produto = produtoService.CadastrarProduto(produtoTO,lojaId);


        return new ResponseEntity<Produto>(produto,HttpStatus.CREATED);
    }
}
