package com.br.free.commerce.controllers;

import com.br.free.commerce.bean.Carrinho;
import com.br.free.commerce.to.BuscarProdutoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by eduardosanson on 09/03/16.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    private static final String PAGE_NAME="home";
    private static final String PAGE_FRAGMENT="home";
    private static final String HOME_URL="/";

    @RequestMapping(value = HOME_URL, method = RequestMethod.GET)
    public String showHome(Model model, BuscarProdutoTO buscarProdutoTO){
        model.addAttribute("pageName", PAGE_NAME);
        model.addAttribute("pageFragment", PAGE_FRAGMENT);

        return "index";
    }

}
