package com.br.free.commerce.controllers;

import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.services.Interface.StoreService;
import com.br.free.commerce.to.ProdutoPage;
import com.br.free.commerce.to.ProdutoTO;
import com.br.free.commerce.to.StoreForm;
import com.br.free.commerce.util.Page;
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
import java.util.*;

/**
 * Created by eduardosanson on 13/03/16.
 */
@Controller
@RequestMapping(value = "/store")
public class LojaController {


    private static final String INDEX="index";
    public static final String MENU="/store/menu";
    private static final String PAGE_NAME="pageName";
    private static final String PAGE_FRAGMENT="pageFragment";
    private static final String PAGE_REGISTRATION="registration";
    private static final String REGISTRATION_FRAGMENT="registration";
    private static final String MENU_NAME="MENU_NAME";
    private static final String MENU_FRAGMENT="MENU_FRAGMENT";
    private static final String MENU_NAME_HOME="store-products";
    private static final String MENU_FRAGMENT_HOME="store-products";
    private static final String PAGE_ACCOUNT = "account-posts";
    private static final String PAGE_ACCOUNT_FRAGMENT = "account-posts";
    private static final String PAGE_CREATE_PRODUCT ="fragments/account-create-product";
    private static final String FRAGMENT_CREATE_PRODUCT ="account-create-product";
    private static final String PAGE_CHANGE_PASSWORD ="store-change-password";
    private static final String FRAGMENT_CHANGE_PASSWORD ="store-change-password";
    private static final String PAGE_CHANGE_ADDRESS ="store-change-address";
    private static final String FRAGMENT_CHANGE_ADDRESS ="store-change-address";
    private static final String PAGE_CHANGE_REGISTRATION ="store-change-registration";
    private static final String FRAGMENT_CHANGE_REGISTRATION ="store-change-registration";
    private static final String  PAGE_PRODUCT ="productStorePage";
    private static final String  FRAGMENT_PRODUCT ="storeProductPage";

    @Autowired
    private StoreService storeService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProdutoService produtoService;

    private Logger logger = Logger.getLogger(LojaController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String showForm(Model model,StoreForm storeForm){
        model.addAttribute(PAGE_NAME,PAGE_REGISTRATION);
        model.addAttribute(PAGE_FRAGMENT,REGISTRATION_FRAGMENT);
        return INDEX;
    }

    @RequestMapping(value = "/form",method = RequestMethod.POST)
    public String singUp(@Valid StoreForm storeForm, BindingResult bindingResult, Model model,HttpServletRequest request){
        model.addAttribute(PAGE_NAME,PAGE_REGISTRATION);
        model.addAttribute(PAGE_FRAGMENT,REGISTRATION_FRAGMENT);


            if (bindingResult.hasErrors()){
                System.out.println("ocorreu um erro");
                return INDEX;
            }
            UserLogin user = storeService.cadastrar(storeForm);

            if (user==null){
                return INDEX;
            }else {
                authenticateUserAndSetSession(user, request);
                return "redirect:"+MENU;
            }
    }

    @RequestMapping("/menu")
    public String login(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails){

        //customUserDetails.getUserlogin();

        model.addAttribute(PAGE_NAME,PAGE_ACCOUNT);
        model.addAttribute(PAGE_FRAGMENT,PAGE_ACCOUNT);

        model.addAttribute(MENU_NAME,MENU_NAME_HOME);
        model.addAttribute(MENU_FRAGMENT,MENU_FRAGMENT_HOME);

        ProdutoPage produtos = produtoService.recuperarProdutosDeLoja(customUserDetails.getUserlogin().getLoja());

        Page page = criarPagina("1", produtos);

        model.addAttribute("produtoPage",produtos);
        model.addAttribute("page",page);

        return INDEX;
    }

    @RequestMapping("/menu/createProduct")
    public String showCreateProduct(Model model,ProdutoTO produtoTO){
        logger.info("usando ajax de create product");


        return PAGE_CREATE_PRODUCT +" :: " + FRAGMENT_CREATE_PRODUCT;
    }

    @RequestMapping(value = "/menu/showMyProducts/{pageNumber}")
    public String showMyProducts(@PathVariable String pageNumber, Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails){

        ProdutoPage produtos = produtoService.recuperarProdutosDeLoja(customUserDetails.getUserlogin().getLoja());

        Page page = criarPagina(pageNumber, produtos);

        model.addAttribute("produtoPage",produtos);
        model.addAttribute("page",page);

        return "fragments/"+MENU_NAME_HOME + " :: " + MENU_FRAGMENT_HOME;
    }

    @RequestMapping(value = "/menu/showMyProductsPage/{pageNumber}")
    public String showMyProductsPage(@PathVariable String pageNumber, Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails){

        ProdutoPage produtos = produtoService.recuperarProdutosDeLoja(customUserDetails.getUserlogin().getLoja());

        Page page = criarPagina(pageNumber, produtos);

        model.addAttribute("produtoPage",produtos);
        model.addAttribute("page",page);

        return "fragments/"+PAGE_PRODUCT + " :: " + FRAGMENT_PRODUCT;
    }

    private Page criarPagina(String pagina, ProdutoPage produtos) {
        Page page = new Page();
        page.setQtdElementosPorPagina(produtos.getNumberOfElements());
        page.setPaginaAtual(Integer.parseInt(pagina));
        page.setTotalDePaginas(produtos.getqtdPages());
        return page;
    }

    @RequestMapping("/menu/changePassword")
    public String showChangePassword(){
        return "fragments/"+ PAGE_CHANGE_PASSWORD + " :: " + FRAGMENT_CHANGE_PASSWORD;
    }

    @RequestMapping("/menu/changeAddress")
    public String showChangeAndress(){
        return "fragments/"+ PAGE_CHANGE_ADDRESS + " :: " + FRAGMENT_CHANGE_ADDRESS;
    }

    @RequestMapping("/menu/changeRegistration")
    public String showChangeRegistration(){
        return "fragments/"+ PAGE_CHANGE_REGISTRATION + " :: " + FRAGMENT_CHANGE_REGISTRATION;
    }

    @RequestMapping(value = "menu/cadastrarProduto", method = RequestMethod.POST)
    public String cadastrarProdutos(@Valid ProdutoTO produtoTO, BindingResult bindingResult, Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        model.addAttribute(PAGE_NAME,PAGE_ACCOUNT);
        model.addAttribute(PAGE_FRAGMENT,PAGE_ACCOUNT);

        model.addAttribute(MENU_NAME,PAGE_CREATE_PRODUCT);
        model.addAttribute(MENU_FRAGMENT,FRAGMENT_CREATE_PRODUCT);

        Produto produto = produtoService.cadastrarProduto(customUserDetails.getUserlogin().getLoja(),produtoTO);



        return INDEX;
    }

    private void authenticateUserAndSetSession(UserLogin user, HttpServletRequest request) {

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + user.getPermissao()));

        try {

            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getLogin(),user.getSenha(),grantedAuthorityList));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
