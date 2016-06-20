package com.br.free.commerce.controller;

import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.services.EnderecoServiceImpl;
import com.br.free.commerce.services.Interface.CategoriaService;
import com.br.free.commerce.services.Interface.ClienteService;
import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.to.BuscarProdutoTO;
import com.br.free.commerce.to.CadastrarClienteTO;
import com.free.commerce.entity.Categoria;
import com.free.commerce.entity.Cliente;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by eduardosanson on 09/03/16.
 */
@Controller
@RequestMapping("/")
@SessionAttributes(
        { "cadastrarClienteTO" })
public class HomeController {

    private static final String PAGE_NAME="home";
    private static final String PAGE_FRAGMENT="home";

    private static final String HOME_URL="/";
    private static final String INDEX="index";

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
        List<Categoria> categorias = categoriaService.buscarCategoriasPrincipais();

        BuscarProdutoTO buscarProduto = new BuscarProdutoTO();
        buscarProduto.setOrderBy("RegistradoDesc");
        buscarProduto.setPage("1");
        buscarProduto.setSize("5");


        model.addAttribute("cidades",cidades);
        model.addAttribute("categoriasPrincipais",categorias.stream().limit(8).toArray());
        model.addAttribute("novosProdutos",produtoService.buscarProdutos(buscarProduto));

        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String test(MultipartFile file){

        logger.info(file);

        return INDEX;

    }

    @RequestMapping("/total/Users")
    @ResponseBody
    public Long totalDeUsuarios(){
        RestTemplate template = new RestTemplate();
        return template.getForObject("http://adminappcommerce.herokuapp.com/staticController/total/Users",Long.class);
    }

    @RequestMapping("/total/sellers")
    public Long totalDeVendedores(){
        RestTemplate template = new RestTemplate();
        return template.getForObject("http://adminappcommerce.herokuapp.com/staticController/total/sellers",Long.class);
    }


    @RequestMapping("/total/items")
    public Long totaoDeProdutos(){
        RestTemplate template = new RestTemplate();
        return template.getForObject("http://adminappcommerce.herokuapp.com/staticController/total/items",Long.class);
    }


    @RequestMapping("/total/location")
    public Long totaoDeLugares(){
        RestTemplate template = new RestTemplate();
        return template.getForObject("http://adminappcommerce.herokuapp.com/staticController/total/location",Long.class);
    }



}
