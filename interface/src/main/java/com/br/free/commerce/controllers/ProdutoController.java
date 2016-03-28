package com.br.free.commerce.controllers;

import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.to.ProdutoTO;
import com.free.commerce.entity.Produto;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by eduardo.sanson on 23/03/2016.
 */
@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private static final String PRODUCT_DETAIL_PAGE = "product-detail";
    private static final String PRODUCT_DETAIL_FRAGMENT = "product-detail";
    private static final String PAGE_NAME="pageName";
    private static final String PAGE_FRAGMENT="pageFragment";
    private static final String INDEX = "index";

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(method = RequestMethod.POST)
    public String cadastrarProduto(@Valid ProdutoTO produtoTO, BindingResult bindingResult,
                                   Model model,@AuthenticationPrincipal CustomUserDetails customUserDetails){

        if(bindingResult.hasErrors()){
            return "redirect:menu/cadastrarProduto";
        }

        produtoService.cadastrarProduto(customUserDetails.getUserlogin().getLoja(),produtoTO);

        return "redirect:"+ LojaController.MENU;
    }

    @RequestMapping("/detail/{productId}")
    public String productDetail(@PathVariable("productId") String id, Model model){
        model.addAttribute(PAGE_NAME,PRODUCT_DETAIL_PAGE);
        model.addAttribute(PAGE_FRAGMENT,PRODUCT_DETAIL_FRAGMENT);

        Produto produto = produtoService.buscarProdutoPorId(id);

        model.addAttribute("produto",produto);

        return INDEX;
    }

}
