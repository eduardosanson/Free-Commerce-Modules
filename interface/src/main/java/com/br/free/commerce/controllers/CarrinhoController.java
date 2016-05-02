package com.br.free.commerce.controllers;

import com.br.free.commerce.to.BuscarProdutoTO;
import com.br.free.commerce.to.RegistrarPedidoTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(method = RequestMethod.GET)
    public String showHome(Model model, BuscarProdutoTO buscarProdutoTO, RegistrarPedidoTO registrarPedidoTO){
        model.addAttribute(PAGE_NAME,PAGE_CARRINHO );
        model.addAttribute(FRAGMENT_NAME,FRAGMENT_CARRINHO);

        return "index";
    }


}
