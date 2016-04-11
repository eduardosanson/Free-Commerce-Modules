package com.br.free.commerce.controllers;

import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.services.Interface.AutorizacaoLojaService;
import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.services.Interface.StoreService;
import com.br.free.commerce.to.ProdutoPage;
import com.br.free.commerce.to.ProdutoTO;
import com.br.free.commerce.to.StoreForm;
import com.br.free.commerce.util.Page;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.Produto;
import com.free.commerce.entity.UserLogin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eduardosanson on 13/03/16.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {


    private static final String INDEX="index";
    private static final String PAGE_NAME="pageName";
    private static final String PAGE_FRAGMENT="pageFragment";
    private static final String MENU_NAME="MENU_NAME";
    private static final String MENU_FRAGMENT="MENU_FRAGMENT";
    private static final String MENU_NAME_HOME="aceitar-solicitacoes";
    private static final String MENU_FRAGMENT_HOME="aceitar-solicitacoes";
    private static final String PAGE_ACCOUNT = "admin-posts";
    private static final String PAGE_ACCOUNT_FRAGMENT = "admin-posts";
    private static final String PAGE_CREATE_CATEGORY ="fragments/cadastrar-categoria";
    private static final String FRAGMENT_CREATE_CATEGORY ="cadastrar-categoria";

    @Autowired
    private StoreService storeService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private AutorizacaoLojaService autorizacaoLojaService;

    private Logger logger = Logger.getLogger(AdminController.class);


    @RequestMapping(value = "/menu",method = RequestMethod.GET)
    public String loginAdmin(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails){

        //customUserDetails.getUserlogin();

        model.addAttribute(PAGE_NAME,PAGE_ACCOUNT);
        model.addAttribute(PAGE_FRAGMENT,PAGE_ACCOUNT_FRAGMENT);

        model.addAttribute(MENU_NAME,MENU_NAME_HOME);
        model.addAttribute(MENU_FRAGMENT,MENU_FRAGMENT_HOME);

        List<Loja> lojas = autorizacaoLojaService.buscarLojasPendentes();
        model.addAttribute("lojas",lojas);

        return INDEX;
    }

    @RequestMapping("/menu/novasSolicitacoes")
    public String mostrarSolicitacoes(Model model){
        logger.info("usando ajax de create product");

        List<Loja> lojas = autorizacaoLojaService.buscarLojasPendentes();
        model.addAttribute("lojas",lojas);


        return "fragments/"+ MENU_NAME_HOME +" :: " + MENU_FRAGMENT_HOME;
    }

    @RequestMapping(value = "/menu/criarCategoria")
    public String criarCategoria (Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails){

        logger.info("Menu de criação de categoria");


        return PAGE_CREATE_CATEGORY + " :: " + FRAGMENT_CREATE_CATEGORY;
    }

    @RequestMapping("/menu/autorizarSolicitacao/{lojaId}")
    public String autorizarSolicitacao(@PathVariable String lojaId, Model model){
        logger.info("usando ajax de create product");

        autorizacaoLojaService.autorizarSolicitacao(lojaId);


        return "redirect:/admin/menu";
    }

    @RequestMapping("/menu/cancelarSolicitacao/{lojaId}")
    public String cancelarSolicitacao(@PathVariable String lojaId, Model model){
        logger.info("usando ajax de create product");

        autorizacaoLojaService.cancelarSolicitacao(lojaId);

        return "redirect:/admin/menu";
    }

}
