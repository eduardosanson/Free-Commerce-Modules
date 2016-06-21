package com.br.free.commerce.controller;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import com.br.free.commerce.bean.Carrinho;
import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.exception.RegraDeNegocioException;
import com.br.free.commerce.services.Interface.CategoriaService;
import com.br.free.commerce.services.Interface.PedidoService;
import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.services.Interface.StoreService;
import com.br.free.commerce.services.UserDetailsServiceImpl;
import com.br.free.commerce.services.UserServiceImpl;
import com.br.free.commerce.to.*;
import com.br.free.commerce.util.MaskUtil;
import com.br.free.commerce.util.Page;
import com.free.commerce.entity.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URISyntaxException;
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
    private static final String MENU_EDITAR_PRODUTO="editar-produto";
    private static final String FRAGMENTO_EDITAR_PRODUTO="editar-produto";
    private static final String MENU_EDITAR_IMAGEM="editar-imagem";
    private static final String FRAGMENTO_EDITAR_IMAGEM="editar-imagem";
    private static final String PAGINA_SOLICITACAO_PEDIDO="solicitacao-pedido";
    private static final String FRAGMENTO_SOLICITACAO_PEDIDO="solicitacao-pedido";

    private static final String TOKEN_PAG_SEGURO="A457261FBEEE4D51AF3D6997AC12F720";

    private static final String URL_ALTERAR_FOTO_PERFIL="/menu/profileFoto";
    private static final String CONTEXTO="/store";


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

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private ProdutoService getProdutoService;

    @Autowired
    private UserServiceImpl userService;

    private Logger logger = Logger.getLogger(LojaController.class);

    @RequestMapping("/menu/alterarsenha")
    public String alterarSenha(AlterarSenhaTO alterarSenhaTO,Model model){
        model.addAttribute(PAGE_NAME,PAGE_ACCOUNT);
        model.addAttribute(PAGE_FRAGMENT,PAGE_ACCOUNT);
        model.addAttribute(MENU_NAME,PAGE_CHANGE_PASSWORD);
        model.addAttribute(MENU_FRAGMENT,FRAGMENT_CHANGE_PASSWORD);

        return INDEX;
    }
    @RequestMapping(value = "/menu/alterarsenha",method = RequestMethod.POST)
    public String alterarSenha(AlterarSenhaTO alterarSenhaTO,
                               @AuthenticationPrincipal CustomUserDetails customUserDetails,
                               Model model) throws URISyntaxException {

        model.addAttribute(PAGE_NAME,PAGE_ACCOUNT);
        model.addAttribute(PAGE_FRAGMENT,PAGE_ACCOUNT);
        model.addAttribute(MENU_NAME,PAGE_CHANGE_PASSWORD);
        model.addAttribute(MENU_FRAGMENT,FRAGMENT_CHANGE_PASSWORD);

        if (alterarSenhaTO.getSenhaAtual().equalsIgnoreCase(customUserDetails.getUserlogin().getSenha())){
            UserLogin userlogin = customUserDetails.getUserlogin();
            userlogin.setSenha(alterarSenhaTO.getNovaSenha());
            storeService.alterarLogin(userlogin);
        }else {
            model.addAttribute("erroSenha","Senha atual não confere");
        }

        return INDEX;


    }

    @RequestMapping(value = "/menu/imagem/delete/{produtoId}")
    public @ResponseBody ResponseEntity deletarImagem(@RequestParam("key") long imagemId,@PathVariable("produtoId") long produtoId){
        logger.info("deletando imagem de produto");

        produtoService.deletarImagem(produtoId,imagemId);

        return ResponseEntity.ok("{\"response\":\"sucesso\"}");

    }

    @RequestMapping(value = "menu/editar/produto/{produtoId}")
    public String editarProduto(Model model,@PathVariable("produtoId") String produtoId, Produto produto){

        model.addAttribute(PAGE_NAME,PAGE_ACCOUNT);
        model.addAttribute(PAGE_FRAGMENT,PAGE_ACCOUNT);

        model.addAttribute(MENU_NAME,MENU_EDITAR_PRODUTO);
        model.addAttribute(MENU_FRAGMENT,FRAGMENTO_EDITAR_PRODUTO);

        produto = produtoService.buscarProdutoPorId(produtoId);

        model.addAttribute("produto",produto);

        return INDEX;
    }

    @RequestMapping(value = "menu/editar/produto/imagem/{produtoId}")
    public String editarImagemDeProduto(Model model,@PathVariable("produtoId") String produtoId, Produto produto){

        model.addAttribute(PAGE_NAME,PAGE_ACCOUNT);
        model.addAttribute(PAGE_FRAGMENT,PAGE_ACCOUNT);

        model.addAttribute(MENU_NAME,MENU_EDITAR_IMAGEM);
        model.addAttribute(MENU_FRAGMENT,FRAGMENTO_EDITAR_IMAGEM);

        produto = produtoService.buscarProdutoPorId(produtoId);

        model.addAttribute("produto",produto);
        model.addAttribute("produtoId",produto.getId());


        Imagem[] imagens = new Imagem[produto.getImagens().size()];

        for (int i=0;i <imagens.length;i++){
            imagens[i] = produto.getImagens().get(i);

        }



        String[] imagenView = new String[produto.getImagens().size()];
        String[] key = new String[imagenView.length];
        ImagemView view = new ImagemView();
        List<InitialPreviewConfig> initialPreviewConfigs = new ArrayList<>();
        for (int i = 0; i<imagens.length ; i++) {
            InitialPreviewConfig initialPreviewConfig = new InitialPreviewConfig();
            String path = produto.getImagens().get(i).getPath().startsWith("/") ? produto.getImagens().get(i).getPath() : "/" + produto.getImagens().get(i).getPath();
            imagenView[i] = "<img style='height:160px' src='" + path + "'/>";
            key[i] = String.valueOf(produto.getImagens().get(i).getId());
            initialPreviewConfig.setWidth("120px");
            initialPreviewConfig.setUrl("/store/menu/imagem/delete/"+produtoId);
            initialPreviewConfig.setKey(key[i]);
            initialPreviewConfigs.add(initialPreviewConfig);

        }

        model.addAttribute("imagens",imagens);
        model.addAttribute("initialPreview",imagenView);
        model.addAttribute("initialPreviewConfig",initialPreviewConfigs);


        return INDEX;
    }

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
    public String singUp(@Valid CadastrarLojaTO cadastrarLojaTO, BindingResult bindingResult,
                         Model model, HttpServletRequest request,ProdutoTO produtoTO){
        model.addAttribute(PAGE_NAME,PAGE_REGISTRATION);
        model.addAttribute(PAGE_FRAGMENT,REGISTRATION_FRAGMENT);

        CPFValidator cpfValidator = new CPFValidator();
        CNPJValidator cnpjValidator = new CNPJValidator();

        cadastrarLojaTO.setTelefone(MaskUtil.removeTelefoneMask(cadastrarLojaTO.getTelefone()));
        cadastrarLojaTO.setCep(MaskUtil.removeCepMask(cadastrarLojaTO.getCep()));
        cadastrarLojaTO.setCpfOuCnpj(MaskUtil.removeCpfOuCnpjMask(cadastrarLojaTO.getCpfOuCnpj()));

        if (cadastrarLojaTO.getCpfOuCnpj().length()<12){
            try {
                cpfValidator.assertValid(cadastrarLojaTO.getCpfOuCnpj());
            }catch (InvalidStateException e){
                model.addAttribute("cpfOuCpjErro","CPF inválido");
                return INDEX;
            }
        }else {
            try {
                cnpjValidator.assertValid(cadastrarLojaTO.getCpfOuCnpj());
            }catch (InvalidStateException e){
                model.addAttribute("cpfOuCpjErro","CNPJ inválido");
                return INDEX;
            }
        }

        if(!cadastrarLojaTO.isAceitoTermosECondicoes()){
            model.addAttribute("termosECondicoesErro","Falta aceitar os termos e condições");
            return INDEX;
        }

            if (bindingResult.hasErrors()){
                System.out.println("ocorreu um erro");
                return INDEX;
            }

        if (!cadastrarLojaTO.getEmail().equalsIgnoreCase(cadastrarLojaTO.getConfirmacaoEmail())){
            model.addAttribute("emailErro","E-mails passados estão diferentes");
            return INDEX;
        }

        if (!cadastrarLojaTO.getPassword().equalsIgnoreCase(cadastrarLojaTO.getConfirmacaoPassword())){
            model.addAttribute("passwordErro","senhas passadas estão diferentes");
            return INDEX;
        }

        try {
                UserLogin userLogin = userService.recuperarPorEmail(cadastrarLojaTO.getEmail());

                if (userLogin!=null){
                    model.addAttribute("emailErro","E-mail já se encontra cadastrado");
                    return INDEX;
                }

            }catch (RegraDeNegocioException e) {
                logger.info("Usuario não cadastrado");
            }

        try {

            storeService.buscarLojaPorCpfOuCnpj(cadastrarLojaTO.getCpfOuCnpj());
            model.addAttribute("cpfOuCpjErro","Cpf ou cnpj já cadastrado");
        }catch (HttpClientErrorException err){
            logger.warn("busca por cpf ou cnpj não retornou sucesso");
        }


        UserLogin user = storeService.cadastrar(cadastrarLojaTO);

            if (user==null){
                return INDEX;
            }else {
                userDetailsService.login(request,user.getLogin(),user.getSenha());
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
        model.addAttribute("profileFotoSendFormUrl",CONTEXTO+URL_ALTERAR_FOTO_PERFIL);

        return INDEX;
    }

    @RequestMapping(value = URL_ALTERAR_FOTO_PERFIL, method = RequestMethod.POST)
    public String alterarFotoPerfilUrl(@RequestParam("avatar") MultipartFile imagem, @AuthenticationPrincipal CustomUserDetails userDetails){

        if (imagem.isEmpty()){
            return "redirect:/store/menu";
        }

        storeService.alterarPerfil(userDetails.getUserlogin().getLoja().getId(),imagem);

        userDetails.getUserlogin().setLoja(storeService.buscarLoja(userDetails.getUserlogin().getLoja().getId()));

        return "redirect:/store/menu";
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

        model.addAttribute(PAGE_NAME,PAGE_ACCOUNT);
        model.addAttribute(PAGE_FRAGMENT,PAGE_ACCOUNT);

        model.addAttribute(MENU_NAME,MENU_NAME_HOME);
        model.addAttribute(MENU_FRAGMENT,MENU_FRAGMENT_HOME);


        ProdutoPage produtos = produtoService.
                recuperarProdutosDeLoja(customUserDetails.getUserlogin().getLoja(),
                pageNumber,quantidadeDeProdutoPorPagina);

        Page page = criarPagina(pageNumber, produtos);

        if (produtos==null){
            produtos = new ProdutoPage();
        }

        model.addAttribute("produtoPage",produtos);
        model.addAttribute("page",page);

        return INDEX;
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

    @RequestMapping(value = "/menu/solicitacoes")
    public String verificarSolicitacoes(Model model, @AuthenticationPrincipal CustomUserDetails userDetails){

        model.addAttribute(PAGE_NAME,PAGE_ACCOUNT);
        model.addAttribute(PAGE_FRAGMENT,PAGE_ACCOUNT);

        model.addAttribute(MENU_NAME,PAGINA_SOLICITACAO_PEDIDO);
        model.addAttribute(MENU_FRAGMENT,FRAGMENTO_SOLICITACAO_PEDIDO);

        List<Pedido> pedidos = storeService.minhasSolicitacoes(userDetails.getUserlogin().getLoja().getId());

        model.addAttribute("pedidos",pedidos);


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

    public String responderMensagem(ResponderMensagemTO responderMensagemTO){

        return "redirect:/produto/detail/"+ responderMensagemTO.getProdutoId();
    }

    @RequestMapping(value = "/menu/alterarSenha",method = RequestMethod.POST)
    public String alterarSenha(AlterarSenhaTO alterarSenhaTO){
        return "redirect:/store/menu";
    }





}
