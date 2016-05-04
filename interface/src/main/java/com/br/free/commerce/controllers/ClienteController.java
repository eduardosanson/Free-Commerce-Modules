package com.br.free.commerce.controllers;

import com.br.free.commerce.bean.Carrinho;
import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.services.Interface.*;
import com.br.free.commerce.to.*;
import com.br.free.commerce.util.Page;
import com.free.commerce.entity.Cliente;
import com.free.commerce.entity.Endereco;
import com.free.commerce.entity.UserLogin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
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


    private static final String PAGE_NAME="pageName";
    private static final String PAGE_FRAGMENT="pageFragment";
    private static final String PAGE_CLIENTE="pagina-cliente";
    private static final String HOME_URL="/";
    private static final String INDEX="index";
    public static final String MENU="/store/menu";
    private static final String PAGE_REGISTRATION="registration";
    private static final String REGISTRATION_FRAGMENT="registration";
    private static final String MENU_NAME="MENU_NAME";
    private static final String MENU_FRAGMENT="MENU_FRAGMENT";
    private static final String MENU_FRAGMENT_HOME="carrinho";
    private static final String MENU_NAME_HOME="carrinho";
    private static final String FRAGMENTO_CADASTRO_DADOS_PRINCIPAIS ="cliente-cadastro-dados-principais" ;
    private static final String MENU_CADASTRO_DADOS_PRINCIPAIS = "cliente-cadastro-dados-principais";

    @Autowired
    private Carrinho carrinho;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    private static final Logger LOGGER = Logger.getLogger(ClienteController.class);

    @RequestMapping("/menu")
    public String login(@AuthenticationPrincipal CustomUserDetails customUserDetails,Model model, FinalizarCadastroTO cadastroTO){

        //customUserDetails.getUserlogin();

        model.addAttribute(PAGE_NAME,PAGE_CLIENTE);
        model.addAttribute(PAGE_FRAGMENT,PAGE_CLIENTE);

        model.addAttribute(MENU_NAME,MENU_NAME_HOME);
        model.addAttribute(MENU_FRAGMENT,MENU_FRAGMENT_HOME);

        return INDEX;
    }

    @RequestMapping("/menu/finalizarCompras")
    public String finalizarCompras(@AuthenticationPrincipal CustomUserDetails userDetails,Model model,FinalizarCadastroTO cadastroTO){

        LOGGER.info(userDetails.getUserlogin().getCliente());

        Cliente cliente = userDetails.getUserlogin().getCliente();

        model.addAttribute(PAGE_NAME,PAGE_CLIENTE);
        model.addAttribute(PAGE_FRAGMENT,PAGE_CLIENTE);

        if (cliente !=null){
            if (cliente.getCpf()==null||cliente.getEnderecoEntrega()==null){

                return "redirect:/cliente/menu/cadastrarDadosPrincipais";

            }
            model.addAttribute("cadastroTO",cadastroTO);

        }else {

            model.addAttribute(MENU_NAME,MENU_NAME_HOME);
            model.addAttribute(MENU_FRAGMENT,MENU_FRAGMENT_HOME);
        }


        return INDEX;
    }

    @RequestMapping("/registrarPedido")
    public String registrarSolicitacao(RegistrarPedidoTO registrarPedidoTO, Model model){

        try {
            pedidoService.registrarPedido(registrarPedidoTO);

        }catch (Exception e){
            e.printStackTrace();

        }

        return null;
    }

    @RequestMapping("/menu/cadastrarDadosPrincipais")
    public String dadosPricipais(@AuthenticationPrincipal CustomUserDetails userDetails, Model model, FinalizarCadastroTO cadastroTO){

        cadastroTO = inserirDadosDoBanco(userDetails,cadastroTO);

        model.addAttribute(PAGE_NAME,PAGE_CLIENTE);
        model.addAttribute(PAGE_FRAGMENT,PAGE_CLIENTE);

        model.addAttribute(MENU_NAME,MENU_CADASTRO_DADOS_PRINCIPAIS);
        model.addAttribute(MENU_FRAGMENT,FRAGMENTO_CADASTRO_DADOS_PRINCIPAIS);
        model.addAttribute("cadastroTO",cadastroTO);


        return INDEX;
    }

    @RequestMapping(value = "/menu/finalizarCadastro",method = RequestMethod.POST)
    public String finalizarCadastro(@AuthenticationPrincipal CustomUserDetails userDetails, Model model, FinalizarCadastroTO cadastroTO){

        cadastroTO = inserirDadosDoBanco(userDetails,cadastroTO);

        model.addAttribute(PAGE_NAME,PAGE_CLIENTE);
        model.addAttribute(PAGE_FRAGMENT,PAGE_CLIENTE);
        model.addAttribute(MENU_NAME,MENU_CADASTRO_DADOS_PRINCIPAIS);
        model.addAttribute(MENU_FRAGMENT,FRAGMENTO_CADASTRO_DADOS_PRINCIPAIS);
        model.addAttribute("cadastroTO",cadastroTO);

        try {

            clienteService.concluirCadastro(cadastroTO);
        }catch (Exception e){
            e.printStackTrace();
        }

        return INDEX;
    }

    private FinalizarCadastroTO inserirDadosDoBanco(CustomUserDetails userDetails, FinalizarCadastroTO cadastroTO) {
        Cliente cliente = userDetails.getUserlogin().getCliente();

        Endereco endereco = new Endereco();
        if(cliente.getEnderecoEntrega()!=null){

            endereco.setUf(cliente.getEnderecoEntrega().getUf());
            endereco.setCep(cliente.getEnderecoEntrega().getCep());
            endereco.setNome(cliente.getEnderecoEntrega().getNome());
            endereco.setBairro(cliente.getEnderecoEntrega().getBairro());
            endereco.setNumero(cliente.getEnderecoEntrega().getNumero());
            endereco.setComplemento(cliente.getEnderecoEntrega().getComplemento());
            endereco.setCidade(cliente.getEnderecoEntrega().getCidade());

        }

        FinalizarCadastroTO finalizarCadastroTO = new FinalizarCadastroTO(cliente.getTelefone(),cliente.getNome(),cliente.getSobrenome(),
                cliente.getCpf(),endereco.getCep(),endereco.getNome(),endereco.getBairro(),endereco.getNumero(),
                endereco.getComplemento(),endereco.getUf(),endereco.getCidade());
        return finalizarCadastroTO;
    }


}
