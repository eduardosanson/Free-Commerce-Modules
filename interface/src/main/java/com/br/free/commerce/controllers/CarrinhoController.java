package com.br.free.commerce.controllers;

import com.br.free.commerce.bean.Carrinho;
import com.br.free.commerce.to.BuscarProdutoTO;
import com.br.free.commerce.to.RegistrarPedidoTO;
import com.free.commerce.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by pc on 30/04/2016.
 */
@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

    private static final String PAGE_CARRINHO="carrinho";
    private static final String FRAGMENT_CARRINHO="carrinho";
    private static final String PAGE_NAME ="pageName";
    private static final String FRAGMENT_NAME="pageFragment";
    private static final String CARRINHO_HEADER_PAGE ="carrinho-header";
    private static final String CARRINHO_HEADER_PFRAGMENT ="carrinho-header";

    @Autowired
    private Carrinho carrinho;

    @RequestMapping(method = RequestMethod.GET)
    public String showCarrinhoHeader(Model model, BuscarProdutoTO buscarProdutoTO, RegistrarPedidoTO registrarPedidoTO){
        model.addAttribute(PAGE_NAME,PAGE_CARRINHO );
        model.addAttribute(FRAGMENT_NAME,FRAGMENT_CARRINHO);

        return "index";
    }

    @RequestMapping(value = "/remover/{produtoId}",method = RequestMethod.GET)
    public String retirarDoCarrinho(Model model, @PathVariable("produtoId") Long id){

        Produto produto = new Produto();
        produto.setId(id);
        carrinho.removerProduto(produto);

        return "fragments/"+ CARRINHO_HEADER_PAGE + " :: " + CARRINHO_HEADER_PFRAGMENT;
    }

    @RequestMapping(value = "/somar/{produtoId}",method = RequestMethod.GET)
    public String somarNoCarrinho(Model model, @PathVariable("produtoId") Long id){

        Produto produto = new Produto();
        produto.setId(id);
        carrinho.somarQuantidadeProduto(produto);

        return "fragments/"+ CARRINHO_HEADER_PAGE + " :: " + CARRINHO_HEADER_PFRAGMENT;
    }

    @RequestMapping(value = "/subtrair/{produtoId}",method = RequestMethod.GET)
    public String reduzirNoCarrinho(Model model, @PathVariable("produtoId") Long id){

        Produto produto = new Produto();
        produto.setId(id);
        carrinho.diminuirQuantidadeProduto(produto);

        return "fragments/"+ CARRINHO_HEADER_PAGE + " :: " + CARRINHO_HEADER_PFRAGMENT;
    }


}
