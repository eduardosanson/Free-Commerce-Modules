package com.br.free.commerce.controller;

import com.br.free.commerce.services.Interface.CategoriaService;
import com.br.free.commerce.services.Interface.ClienteService;
import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.to.BuscarProdutoTO;
import com.br.free.commerce.to.CadastrarClienteTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by eduardosanson on 09/03/16.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    private static final String PAGE_NAME="home";
    private static final String PAGE_FRAGMENT="home";

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

    @RequestMapping(method = RequestMethod.POST)
    public String test(MultipartFile file){

        logger.info(file);

        return INDEX;

    }


}
