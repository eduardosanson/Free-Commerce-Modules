package com.br.free.commerce.controllers;

import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.services.Interface.AutorizacaoLojaService;
import com.br.free.commerce.services.Interface.CategoriaService;
import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.to.CadastrarClienteTO;
import com.br.free.commerce.to.CategoriaTO;
import com.free.commerce.entity.Categoria;
import com.free.commerce.entity.Loja;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private AutorizacaoLojaService autorizacaoLojaService;

    @Autowired
    private CategoriaService categoriaService;

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

        model.addAttribute(PAGE_NAME,PAGE_CREATE_CATEGORY);
        model.addAttribute(PAGE_FRAGMENT,FRAGMENT_CREATE_CATEGORY);

        model.addAttribute(MENU_NAME,PAGE_CREATE_CATEGORY);
        model.addAttribute(MENU_FRAGMENT,FRAGMENT_CREATE_CATEGORY);

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


        Categoria categoriaPai = categoriaService.buscarPorId(idCategoriaPai);
        model.addAttribute("categorias",categoriaPai.getFilhos());
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
            Categoria categoriaPai = categoriaService.buscarPorId(catPaiId);
            model.addAttribute("categorias",categoriaPai.getFilhos());


            return "fragments/"+PAGE_CREATE_CATEGORY +" :: " + RESULT_BLOCK+blockAtual;

        }catch (Exception e){
            e.printStackTrace();
            return "falha";

        }
    }


}
