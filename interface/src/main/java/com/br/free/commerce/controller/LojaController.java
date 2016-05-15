package com.br.free.commerce.controller;

import com.br.free.commerce.bean.Carrinho;
import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.services.Interface.CategoriaService;
import com.br.free.commerce.services.Interface.PedidoService;
import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.services.Interface.StoreService;
import com.br.free.commerce.to.*;
import com.br.free.commerce.util.MaskUtil;
import com.br.free.commerce.util.Page;
import com.free.commerce.entity.Categoria;
import com.free.commerce.entity.Produto;
import com.free.commerce.entity.UserLogin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private static final String PAGE_CREATE_PRODUCT ="account-create-product";
    private static final String FRAGMENT_CREATE_PRODUCT ="account-create-product";
    private static final String PAGE_CHANGE_PASSWORD ="store-change-password";
    private static final String FRAGMENT_CHANGE_PASSWORD ="store-change-password";
    private static final String PAGE_CHANGE_ADDRESS ="store-change-address";
    private static final String FRAGMENT_CHANGE_ADDRESS ="store-change-address";
    private static final String PAGE_CHANGE_REGISTRATION ="store-change-registration";
    private static final String FRAGMENT_CHANGE_REGISTRATION ="store-change-registration";
    private static final String PAGE_PRODUCT ="productStorePage";
    private static final String FRAGMENT_PRODUCT ="storeProductPage";
    private static final String quantidadeDeProdutoPorPagina="5";
    private static final String FRAGMENT_CADASTRAR_IMAGEM_PRODUTO ="cadastrar-imagem-produto";
    private static final String PAGINA_CADASTRAR_IMAGEM_PRODUTO ="cadastrar-imagem-produto";

    @Autowired
    private StoreService storeService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private Carrinho carrinho;

    private Logger logger = Logger.getLogger(LojaController.class);


    @RequestMapping(value = "/menu/teste",method = RequestMethod.GET)
    public String test2(Model model){
        model.addAttribute(PAGE_NAME,PAGE_ACCOUNT);
        model.addAttribute(PAGE_FRAGMENT,PAGE_ACCOUNT);

        model.addAttribute(MENU_NAME,"teste");
        model.addAttribute(MENU_FRAGMENT,"teste");

        return INDEX;

    }

    @RequestMapping(method = RequestMethod.GET)
    public String showForm(Model model,CadastrarLojaTO cadastrarLojaTO){
        model.addAttribute(PAGE_NAME,PAGE_REGISTRATION);
        model.addAttribute(PAGE_FRAGMENT,REGISTRATION_FRAGMENT);
        return INDEX;
    }

    @RequestMapping(value = "/form",method = RequestMethod.POST)
    public String singUp(@Valid CadastrarLojaTO cadastrarLojaTO, BindingResult bindingResult, Model model, HttpServletRequest request){
        model.addAttribute(PAGE_NAME,PAGE_REGISTRATION);
        model.addAttribute(PAGE_FRAGMENT,REGISTRATION_FRAGMENT);

        cadastrarLojaTO.setTelefone(MaskUtil.removeTelefoneMask(cadastrarLojaTO.getTelefone()));

            if (bindingResult.hasErrors()){
                System.out.println("ocorreu um erro");
                return INDEX;
            }
            UserLogin user = storeService.cadastrar(cadastrarLojaTO);

            if (user==null){
                return INDEX;
            }else {
                authenticateUserAndSetSession(user, request);
                return "redirect:"+MENU;
            }
    }

    @RequestMapping("/menu")
    public String login(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails){

        model.addAttribute(PAGE_NAME,PAGE_ACCOUNT);
        model.addAttribute(PAGE_FRAGMENT,PAGE_ACCOUNT);

        model.addAttribute(MENU_NAME,MENU_NAME_HOME);
        model.addAttribute(MENU_FRAGMENT,MENU_FRAGMENT_HOME);

        ProdutoPage produtos = produtoService.recuperarProdutosDeLoja(customUserDetails.getUserlogin().getLoja(),
                "1",quantidadeDeProdutoPorPagina);

        Page page = criarPagina("1", produtos);

        if (produtos==null){
            produtos = new ProdutoPage();
        }

        model.addAttribute("produtoPage",produtos);
        model.addAttribute("page",page);

        return INDEX;
    }

    @RequestMapping("/menu/createProduct")
    public String showCreateProduct(Model model,ProdutoTO produtoTO){
        logger.info("usando ajax de create product");
        List<Categoria> categoriasPrincipais = categoriaService.buscarCategoriasPrincipais();
        model.addAttribute("categoriasPrincipais",categoriasPrincipais);
        model.addAttribute(PAGE_NAME,PAGE_ACCOUNT);
        model.addAttribute(PAGE_FRAGMENT,PAGE_ACCOUNT);

        model.addAttribute(MENU_NAME,PAGE_CREATE_PRODUCT);
        model.addAttribute(MENU_FRAGMENT,FRAGMENT_CREATE_PRODUCT);

        return INDEX;
    }

    @RequestMapping(value = "/menu/showMyProducts/{pageNumber}")
    public String showMyProducts(@PathVariable String pageNumber, Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails){

        ProdutoPage produtos = produtoService.
                recuperarProdutosDeLoja(customUserDetails.getUserlogin().getLoja(),
                pageNumber,quantidadeDeProdutoPorPagina);

        Page page = criarPagina(pageNumber, produtos);

        if (produtos==null){
            produtos = new ProdutoPage();
        }

        model.addAttribute("produtoPage",produtos);
        model.addAttribute("page",page);

        return "fragments/"+MENU_NAME_HOME + " :: " + MENU_FRAGMENT_HOME;
    }

    @RequestMapping(value = "/menu/showMyProductsPage/{pageNumber}")
    public String showMyProductsPage(@PathVariable String pageNumber, Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails){

        ProdutoPage produtos = produtoService.
                recuperarProdutosDeLoja(customUserDetails.getUserlogin().getLoja(),
                        pageNumber,quantidadeDeProdutoPorPagina);

        Page page = criarPagina(pageNumber, produtos);

        model.addAttribute("produtoPage",produtos);
        model.addAttribute("page",page);

        return "fragments/"+PAGE_PRODUCT + " :: " + FRAGMENT_PRODUCT;
    }


    private Page criarPagina(String pagina, ProdutoPage produtos) {
        Page page = new Page();
        if (produtos!=null){
            page.setQtdElementosPorPagina(produtos.getNumberOfElements());
            page.setPaginaAtual(Integer.parseInt(pagina));
            page.setTotalDePaginas(produtos.getqtdPages());
        }
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
    public String cadastrarProduto(@Valid ProdutoTO produtoTO, BindingResult bindingResult, Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        model.addAttribute(PAGE_NAME,PAGE_ACCOUNT);
        model.addAttribute(PAGE_FRAGMENT,PAGE_ACCOUNT);

        model.addAttribute(MENU_NAME,PAGINA_CADASTRAR_IMAGEM_PRODUTO);
        model.addAttribute(MENU_FRAGMENT,FRAGMENT_CADASTRAR_IMAGEM_PRODUTO);

        Produto produto = produtoService.cadastrarProduto(customUserDetails.getUserlogin().getLoja(),produtoTO);
        model.addAttribute("produtoId",produto.getId());

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

    @RequestMapping("/solicitarPedido")
    public String criarPedido(@AuthenticationPrincipal CustomUserDetails userDetails){

        if (carrinho.getConteudo()!=null){
            RegistrarPedidoTO registrarPedidoTO = CriarRegistrarPedido();
            registrarPedidoTO.setClienteId(String.valueOf(userDetails.getUserlogin().getCliente().getId()));
            pedidoService.registrarPedido(registrarPedidoTO);
        }


        return "redirect:../cliente/menu/meusPedidos";
    }

    private RegistrarPedidoTO CriarRegistrarPedido() {
        RegistrarPedidoTO registrarPedidoTO = new RegistrarPedidoTO();
        List<ProdutoPedido> produtosPedidos = new ArrayList<>();
        Map<Produto,Integer> produtoEQuantidade = carrinho.getConteudo();
        for (Produto produto:produtoEQuantidade.keySet()) {
            ProdutoPedido produtoPedido = new ProdutoPedido();
            Integer quatidade = produtoEQuantidade.get(produto);
            produtoPedido.setProdutoId(String.valueOf(produto.getId()));
            produtoPedido.setQuatidade(String.valueOf(quatidade));
            produtosPedidos.add(produtoPedido);
        }
        registrarPedidoTO.setProdutoPedido(produtosPedidos);

        return registrarPedidoTO;

    }

}
