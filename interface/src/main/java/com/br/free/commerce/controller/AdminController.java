package com.br.free.commerce.controller;

import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.services.Interface.*;
import com.br.free.commerce.to.AlterarSenhaTO;
import com.br.free.commerce.to.CategoriaTO;
import com.br.free.commerce.util.Page;
import com.free.commerce.entity.AutorizacaoLoja;
import com.free.commerce.entity.Categoria;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.UserLogin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URISyntaxException;
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
    private static final String PAGE_CREATE_CATEGORY ="cadastrar-categoria";
    private static final String FRAGMENT_CREATE_CATEGORY ="cadastrar-categoria";
    private static final String RESULT_BLOCK = "resultBlock";
    private static final String BUTTON_ENVIAR_CATEGORIA = "enviarCategoria";
    private static final String FRAGMENT_LOJA_DATA = "lojaData";

    private static final String FRAGMENT_CHANGE_PASSWORD="change-password";
    private static final String PAGE_CHANGE_PASSWORD="change-password";

    private static final String URL_ALTERAR_FOTO_PERFIL="/menu/profileFoto";
    private static final String CONTEXTO="/admin";

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private AutorizacaoLojaService autorizacaoLojaService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private StoreService storeService;

    private int limiteDeLojasPorPagina=5;
    private int paginaInicial=0;

    private Logger logger = Logger.getLogger(AdminController.class);

    @RequestMapping("/menu/alterarsenha")
    public String alterarSenha(AlterarSenhaTO alterarSenhaTO, Model model){
        model.addAttribute(PAGE_NAME,PAGE_ACCOUNT);
        model.addAttribute(PAGE_FRAGMENT,PAGE_ACCOUNT_FRAGMENT);
        model.addAttribute(MENU_NAME,PAGE_CHANGE_PASSWORD);
        model.addAttribute(MENU_FRAGMENT,FRAGMENT_CHANGE_PASSWORD);

        return INDEX;
    }
    @RequestMapping(value = "/menu/alterarsenha",method = RequestMethod.POST)
    public String alterarSenha(AlterarSenhaTO alterarSenhaTO,
                               @AuthenticationPrincipal CustomUserDetails customUserDetails,
                               Model model) throws URISyntaxException {

        model.addAttribute(PAGE_NAME,PAGE_ACCOUNT);
        model.addAttribute(PAGE_FRAGMENT,PAGE_ACCOUNT_FRAGMENT);
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


    @RequestMapping(value = "/menu",method = RequestMethod.GET)
    public String loginAdmin(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails){

        //customUserDetails.getUserlogin();

        model.addAttribute(PAGE_NAME,PAGE_ACCOUNT);
        model.addAttribute(PAGE_FRAGMENT,PAGE_ACCOUNT_FRAGMENT);

        model.addAttribute(MENU_NAME,MENU_NAME_HOME);
        model.addAttribute(MENU_FRAGMENT,MENU_FRAGMENT_HOME);

        List<AutorizacaoLoja> autorizacao = autorizacaoLojaService.buscarautorizacao(paginaInicial,limiteDeLojasPorPagina);
        model.addAttribute("autorizacoes",autorizacao);
        model.addAttribute("profileFotoSendFormUrl",CONTEXTO+URL_ALTERAR_FOTO_PERFIL);

        int qtdDeAutorizacoes = autorizacao.size();
        int qtdDePaginas =  qtdDeAutorizacoes%limiteDeLojasPorPagina>0?(qtdDeAutorizacoes/limiteDeLojasPorPagina)+1 : qtdDeAutorizacoes/limiteDeLojasPorPagina;

        Page page = new Page();
        page.setPaginaAtual(paginaInicial);
        page.setQtdElementosPorPagina(limiteDeLojasPorPagina);
        page.setTotalDePaginas(qtdDePaginas);

        model.addAttribute("page",page);

        return INDEX;
    }

    @RequestMapping(value = URL_ALTERAR_FOTO_PERFIL, method = RequestMethod.POST)
    public String alterarFotoPerfilUrl(@RequestParam("avatar") MultipartFile imagem, @AuthenticationPrincipal CustomUserDetails userDetails){

        if (imagem.isEmpty()){
            return "redirect:/admin/menu";
        }

        adminService.alterarPerfil(userDetails.getUserlogin().getAdministrador().getId(),imagem);

        userDetails.getUserlogin().setAdministrador(adminService.buscarAdmin(userDetails.getUserlogin().getAdministrador().getId()));

        return "redirect:/admin/menu";
    }

    @RequestMapping("/menu/novasSolicitacoes")
    public String mostrarSolicitacoes(Model model){
        logger.info("usando ajax de create product");

        List<AutorizacaoLoja> autorizacao = autorizacaoLojaService.buscarautorizacao(paginaInicial,limiteDeLojasPorPagina);
        model.addAttribute("autorizacoes",autorizacao);

        int qtdDeAutorizacoes = autorizacao.size();
        int qtdDePaginas =  qtdDeAutorizacoes%limiteDeLojasPorPagina>0?(qtdDeAutorizacoes/limiteDeLojasPorPagina)+1 : qtdDeAutorizacoes/limiteDeLojasPorPagina;

        Page page = new Page();
        page.setPaginaAtual(paginaInicial);
        page.setQtdElementosPorPagina(limiteDeLojasPorPagina);
        page.setTotalDePaginas(qtdDePaginas);

        model.addAttribute("page",page);


        return "fragments/"+ MENU_NAME_HOME +" :: " + MENU_FRAGMENT_HOME;
    }

    @RequestMapping("/menu/novasSolicitacoes/porStatus")
    public String mostrarSolicitacoesPorStatus(@RequestParam("status") String status , Model model){
        logger.info("usando ajax de create product");

        List<AutorizacaoLoja> autorizacao = autorizacaoLojaService.buscarautorizacaoPorStatus(status,paginaInicial,limiteDeLojasPorPagina);
        model.addAttribute("autorizacoes",autorizacao);
        model.addAttribute("status",status);

        int qtdDeAutorizacoes = autorizacao.size();
        int qtdDePaginas =  qtdDeAutorizacoes%limiteDeLojasPorPagina>0?(qtdDeAutorizacoes/limiteDeLojasPorPagina)+1 : qtdDeAutorizacoes/limiteDeLojasPorPagina;

        Page page = new Page();
        page.setPaginaAtual(paginaInicial);
        page.setQtdElementosPorPagina(limiteDeLojasPorPagina);
        page.setTotalDePaginas(qtdDePaginas);

        model.addAttribute("page",page);

        return "fragments/"+ MENU_NAME_HOME +" :: " + MENU_FRAGMENT_HOME;
    }

    @RequestMapping("/menu/novasSolicitacoes/porStatusEnomePaginas")
    public String mostrarSolicitacoesPorStatusEnomePaginas(@RequestParam("status") String status ,
                                                           @RequestParam("nome") String nome,
                                                           @RequestParam("paginaAtual") int paginaAtual,
                                                           Model model){
        List<AutorizacaoLoja> autorizacao = autorizacaoLojaService.buscarautorizacaoPorStatusENome(status,nome,paginaAtual-1,limiteDeLojasPorPagina);
        model.addAttribute("autorizacoes",autorizacao);
        model.addAttribute("status",status);

        int qtdDeAutorizacoes = autorizacao.size();
        int qtdDePaginas =  qtdDeAutorizacoes%limiteDeLojasPorPagina>0?(qtdDeAutorizacoes/limiteDeLojasPorPagina)+1 : qtdDeAutorizacoes/limiteDeLojasPorPagina;

        Page page = new Page();
        page.setPaginaAtual(paginaAtual);
        page.setQtdElementosPorPagina(limiteDeLojasPorPagina);
        page.setTotalDePaginas(qtdDePaginas);

        model.addAttribute("page",page);

        return "fragments/"+ MENU_NAME_HOME +" :: " + FRAGMENT_LOJA_DATA;
    }

    @RequestMapping("/menu/novasSolicitacoes/porStatusEnome")
    public String mostrarSolicitacoesPorStatusEnome(@RequestParam("status") String status,@RequestParam("nome") String nome , Model model){
        logger.info("usando ajax de create product");

        List<AutorizacaoLoja> autorizacao = autorizacaoLojaService.buscarautorizacaoPorStatusENome(status,nome,paginaInicial,limiteDeLojasPorPagina);
        model.addAttribute("autorizacoes",autorizacao);
        model.addAttribute("status",status);

        int qtdDeAutorizacoes = autorizacao.size();
        int qtdDePaginas =  qtdDeAutorizacoes%limiteDeLojasPorPagina>0?(qtdDeAutorizacoes/limiteDeLojasPorPagina)+1 : qtdDeAutorizacoes/limiteDeLojasPorPagina;

        Page page = new Page();
        page.setPaginaAtual(paginaInicial);
        page.setQtdElementosPorPagina(limiteDeLojasPorPagina);
        page.setTotalDePaginas(qtdDePaginas);

        model.addAttribute("page",page);

        return "fragments/"+ MENU_NAME_HOME +" :: " + FRAGMENT_LOJA_DATA;
    }

    @RequestMapping(value = "/menu/criarCategoria")
    public String showCategoria (Model model, CategoriaTO categoriaTO){

        logger.info("Menu de criação de categoria");
        int qtdSubcategorias=0;

        List<Categoria> categoriasPrincipais = categoriaService.buscarCategoriasPrincipais();

        model.addAttribute("categoriasPrincipais",categoriasPrincipais);

        return "fragments/"+PAGE_CREATE_CATEGORY + " :: " + FRAGMENT_CREATE_CATEGORY;
    }

    @RequestMapping(value = "/criarCategoriaPrincipal",method = RequestMethod.POST)
    public String cadastrarCategoria(@Valid CategoriaTO categoriaTO,BindingResult bindingResult,Model model,@AuthenticationPrincipal CustomUserDetails customUserDetails){

        categoriaTO.setPrincipal(true);
        categoriaService.cadastrarCategoria(categoriaTO);

        model.addAttribute(PAGE_NAME,PAGE_ACCOUNT);
        model.addAttribute(PAGE_FRAGMENT,PAGE_ACCOUNT_FRAGMENT);
        model.addAttribute(MENU_FRAGMENT,FRAGMENT_CREATE_CATEGORY);
        model.addAttribute(MENU_NAME,PAGE_CREATE_CATEGORY);

        List<Categoria> categoriasPrincipais = categoriaService.buscarCategoriasPrincipais();

        model.addAttribute("categoriasPrincipais",categoriasPrincipais);


        return "index";
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

    @RequestMapping(value = "menu/subCategoria", params ={"passoAtual","nextBlock","idCategoriaPai"} )
    public String mostrarSubCAtegoria(@RequestParam("passoAtual") int passoAtual,
                                      @RequestParam("nextBlock") int nextBlock,
                                      @RequestParam("idCategoriaPai") Long idCategoriaPai, Model model){


        List<Categoria> categoriaFilhas = categoriaService.buscarFilhasPorId(idCategoriaPai);
        model.addAttribute("categorias",categoriaFilhas);
        model.addAttribute("nextBlock",nextBlock);
        model.addAttribute("passoAtual",passoAtual);

        return "fragments/"+PAGE_CREATE_CATEGORY +" :: " + RESULT_BLOCK+nextBlock;
    }

    @RequestMapping(value = "cadastrarCategoriasFilhas", params = {"catPaiId","categoriaNova","blockAtual"})
    public String cadastrarCategoriasFilhas(@RequestParam("catPaiId") Long catPaiId,
                                            @RequestParam("categoriaNova") String categoriaNova,
                                            @RequestParam("blockAtual") int blockAtual,Model model){
        CategoriaTO categoriaTO = new CategoriaTO();

        categoriaTO.setNome(categoriaNova);
        categoriaTO.setCategoriaPaiId(String.valueOf(catPaiId));
        categoriaTO.setPrincipal(false);

        try {
            Categoria categoria = categoriaService.buscarPorNome(categoriaNova);
            if (categoria!=null){
                model.addAttribute("message","Categoria já foi cadastrada");
                return "fragments/"+PAGE_CREATE_CATEGORY +" :: " + "categoriaModal";
            }
            categoriaService.cadastrarCategoria(categoriaTO);
            List<Categoria> categoriasFilhas = categoriaService.buscarFilhasPorId(catPaiId);
            model.addAttribute("categorias",categoriasFilhas);


            return "fragments/"+PAGE_CREATE_CATEGORY +" :: " + RESULT_BLOCK+blockAtual;

        }catch (Exception e){
            e.printStackTrace();
            return "falha";

        }
    }


}
