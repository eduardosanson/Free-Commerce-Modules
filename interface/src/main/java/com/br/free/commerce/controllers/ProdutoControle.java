package com.br.free.commerce.controllers;

import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.to.ProdutoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by eduardo.sanson on 23/03/2016.
 */
@Controller
@RequestMapping("/produto")
public class ProdutoControle {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(method = RequestMethod.POST)
    public String cadastrarProduto(@Valid ProdutoTO produtoTO, BindingResult bindingResult,
                                   Model model,@AuthenticationPrincipal CustomUserDetails customUserDetails){

        if(bindingResult.hasErrors()){
            return "redirect:menu/cadastrarProduto";
        }

        produtoService.cadastrarProduto(customUserDetails.getUserlogin().getLoja(),produtoTO);

        return "redirect:"+StoreController.MENU;
    }

}
