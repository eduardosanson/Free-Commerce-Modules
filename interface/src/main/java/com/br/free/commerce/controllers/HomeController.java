package com.br.free.commerce.controllers;

import com.br.free.commerce.bean.Carrinho;
import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.services.Interface.CategoriaService;
import com.br.free.commerce.services.Interface.ClienteService;
import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.to.BuscarProdutoTO;
import com.br.free.commerce.to.CadastrarClienteTO;
import com.br.free.commerce.to.StoreForm;
import com.free.commerce.entity.UserLogin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by eduardosanson on 09/03/16.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    private static final String PAGE_NAME="home";
    private static final String PAGE_FRAGMENT="home";

    private static final String PAGE_NAME_ATTR="pageName";
    private static final String PAGE_FRAGMENT_ATTR="pageFragment";

    private static final String HOME_URL="/";
    private static final String INDEX="index";
    private static final String MENU_NAME="MENU_NAME";
    private static final String MENU_FRAGMENT="MENU_FRAGMENT";
    private static final String MENU_FRAGMENT_HOME="cliente-pagina-inicial";
    private static final String MENU_NAME_HOME="cliente-pagina-inicial";
    private static final String PAGE_CLIENTE="pagina-cliente";


    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    private Logger logger = Logger.getLogger(LojaController.class);



    @RequestMapping(value = HOME_URL, method = RequestMethod.GET)
    public String showHome(Model model, CadastrarClienteTO cadastrarClienteTO, BuscarProdutoTO buscarProdutoTO){
        model.addAttribute("pageName", PAGE_NAME);
        model.addAttribute("pageFragment", PAGE_FRAGMENT);

        return "index";
    }

    @RequestMapping(value = "/cliente/form",method = RequestMethod.POST)
    public String singUp(@Valid CadastrarClienteTO cadastrarClienteTO, BindingResult bindingResult, Model model, HttpServletRequest request){
        model.addAttribute(PAGE_NAME_ATTR,PAGE_CLIENTE);
        model.addAttribute(PAGE_FRAGMENT_ATTR,PAGE_CLIENTE);

        model.addAttribute(MENU_NAME,MENU_NAME_HOME);
        model.addAttribute(MENU_FRAGMENT,MENU_FRAGMENT_HOME);


        if (bindingResult.hasErrors()){
            System.out.println("ocorreu um erro");
            return INDEX;
        }
        UserLogin user = clienteService.cadastrarCliente(cadastrarClienteTO);


        return INDEX;

    }



}
