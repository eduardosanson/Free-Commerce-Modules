package com.br.free.commerce.controllers;

import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.services.Interface.CategoriaService;
import com.br.free.commerce.services.Interface.ClienteService;
import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.services.Interface.StoreService;
import com.br.free.commerce.to.CadastrarClienteTO;
import com.br.free.commerce.to.ProdutoPage;
import com.br.free.commerce.to.StoreForm;
import com.br.free.commerce.util.Page;
import com.free.commerce.entity.UserLogin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by pc on 01/05/2016.
 */
@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private static final String INDEX="index";
    public static final String MENU="/store/menu";
    private static final String PAGE_NAME="pageName";
    private static final String PAGE_FRAGMENT="pageFragment";
    private static final String PAGE_REGISTRATION="registration";
    private static final String REGISTRATION_FRAGMENT="registration";
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

    @RequestMapping(method = RequestMethod.GET)
    public String showForm(Model model, StoreForm storeForm){
        model.addAttribute(PAGE_NAME,PAGE_REGISTRATION);
        model.addAttribute(PAGE_FRAGMENT,REGISTRATION_FRAGMENT);
        return INDEX;
    }

    @RequestMapping(value = "/form",method = RequestMethod.POST)
    public String singUp(@Valid CadastrarClienteTO cadastrarClienteTO, BindingResult bindingResult, Model model, HttpServletRequest request){
        model.addAttribute(PAGE_NAME,PAGE_REGISTRATION);
        model.addAttribute(PAGE_FRAGMENT,REGISTRATION_FRAGMENT);


        if (bindingResult.hasErrors()){
            System.out.println("ocorreu um erro");
            return INDEX;
        }
        UserLogin user = clienteService.cadastrarCliente(cadastrarClienteTO);


            return INDEX;

    }

    @RequestMapping("/menu")
    public String login(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails){

        //customUserDetails.getUserlogin();

        model.addAttribute(PAGE_NAME,PAGE_CLIENTE);
        model.addAttribute(PAGE_FRAGMENT,PAGE_CLIENTE);

        model.addAttribute(MENU_NAME,MENU_NAME_HOME);
        model.addAttribute(MENU_FRAGMENT,MENU_FRAGMENT_HOME);


        return INDEX;
    }
}
