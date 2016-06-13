package com.br.free.commerce.controller;

import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.services.EnderecoServiceImpl;
import com.br.free.commerce.services.Interface.CategoriaService;
import com.br.free.commerce.services.Interface.ClienteService;
import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.to.BuscarProdutoTO;
import com.br.free.commerce.to.CadastrarClienteTO;
import com.free.commerce.entity.Cliente;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

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
    private static final String PAGINA_PROFILE="profileForm";
    private static final String FRAGMENTO_PROFILE="profileForm";
    private static final String PROFILE_LOJA_URL="/store/menu/prfileFoto";
    private static final String PROFILE_CLIENTE_URL="/cliente/menu/prfileFoto";
    private static final String PROFILE_ADMIN_URL="/admin/menu/prfileFoto";

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private EnderecoServiceImpl enderecoService;

    private Logger logger = Logger.getLogger(LojaController.class);



    @RequestMapping(value = HOME_URL, method = RequestMethod.GET)
    public String showHome(Model model, CadastrarClienteTO cadastrarClienteTO, BuscarProdutoTO buscarProdutoTO){
        model.addAttribute("pageName", PAGE_NAME);
        model.addAttribute("pageFragment", PAGE_FRAGMENT);

        List<String> cidades = enderecoService.cidadesEmLojasCadastradas();

        model.addAttribute("cidades",cidades);

        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String test(MultipartFile file){

        logger.info(file);

        return INDEX;

    }



}
