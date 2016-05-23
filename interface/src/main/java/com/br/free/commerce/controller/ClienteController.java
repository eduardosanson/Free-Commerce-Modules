package com.br.free.commerce.controller;

import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import com.br.free.commerce.PagamentoServiceImpl;
import com.br.free.commerce.bean.Carrinho;
import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.exception.RegraDeNegocioException;
import com.br.free.commerce.exception.enuns.RegraDeNegocioEnum;
import com.br.free.commerce.services.Interface.ClienteService;
import com.br.free.commerce.services.Interface.LoginService;
import com.br.free.commerce.services.Interface.PedidoService;
import com.br.free.commerce.services.UserDetailsServiceImpl;
import com.br.free.commerce.to.CadastrarClienteTO;
import com.br.free.commerce.to.FinalizarCadastroTO;
import com.br.free.commerce.to.ProdutoPedido;
import com.br.free.commerce.to.RegistrarPedidoTO;
import com.free.commerce.entity.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private static final String MENU_CADASTRO_DADOS_PRINCIPAIS_PARA_COMPRAS = "dados-principais-finalizar-compra";
    private static final String FRAGMENTO_CADASTRO_DADOS_PRINCIPAIS_PARA_COMPRAS = "dados-principais-finalizar-compra";
    private static final String MENU_PEDIDOS_CLIENTE = "pedidos-de-cliente";
    private static final String FRAGMENTO_PEDIDOS_CLIENTE = "pedidos-de-cliente";
    private static final String PAGE_CADASTRO="quickSignupPrincipal";
    private static final String FRAGMENT_CADASTRO="quickSignupPrincipal";

    public static final String CONTEXTO="/cliente";
    public static final String FINALIZAR_COMPRA="/menu/finalizarCompras";
    public static final String ALTERAR_FOTO_PERFIL_URL="/menu/prfileFoto";
    public static String PERFL_FOTO_PATH="";
    public static String URL_REDIRECIONANMENTO="http://localhost:8080/cliente/menu";



    @Autowired
    private Carrinho carrinho;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private PagamentoServiceImpl pagamentoServiceImpl;

    private static final Logger LOGGER = Logger.getLogger(ClienteController.class);

    @RequestMapping("/menu")
    public String login(@AuthenticationPrincipal CustomUserDetails userDetails, Model model,
                        FinalizarCadastroTO cadastroTO, HttpSession session){

        //customUserDetails.getUserlogin();

        model.addAttribute(PAGE_NAME,PAGE_CLIENTE);
        model.addAttribute(PAGE_FRAGMENT,PAGE_CLIENTE);

        model.addAttribute(MENU_NAME,MENU_PEDIDOS_CLIENTE);
        model.addAttribute(MENU_FRAGMENT,FRAGMENTO_PEDIDOS_CLIENTE);
        List<Pedido> pedidos = clienteService.meusPedidos(userDetails.getUserlogin().getCliente().getId());
        model.addAttribute("pedidos",pedidos);

        model.addAttribute("profileFotoSendFormUrl",CONTEXTO+ALTERAR_FOTO_PERFIL_URL);
        model.addAttribute("profileFoto","/img/people/user.png");

        return INDEX;
    }

    @RequestMapping(value = ALTERAR_FOTO_PERFIL_URL, method = RequestMethod.POST)
    public String alterarFotoPerfilUrl(@RequestParam("avatar") MultipartFile imagem, @AuthenticationPrincipal CustomUserDetails userDetails){

        clienteService.alterarPerfil(userDetails.getUserlogin().getCliente().getId(),imagem);
        userDetails.getUserlogin().setCliente(clienteService.buscarCliente(userDetails.getUserlogin().getCliente().getId()));

        return "redirect:/cliente/menu";
    }

    @RequestMapping(FINALIZAR_COMPRA)
    public String finalizarCompras(@AuthenticationPrincipal CustomUserDetails userDetails,Model model,FinalizarCadastroTO cadastroTO){

        LOGGER.info(userDetails.getUserlogin().getCliente());

        Cliente cliente = userDetails.getUserlogin().getCliente();

        model.addAttribute(PAGE_NAME,PAGE_CLIENTE);
        model.addAttribute(PAGE_FRAGMENT,PAGE_CLIENTE);

        if (cliente !=null){
            if (cliente.getNome()==null||cliente.getTelefone()==null||cliente.getCpf()==null||cliente.getEnderecoEntrega()==null){

                return "redirect:/cliente/menu/finalizarCadastro/paraCompra";

            }else {
                return "redirect:/cliente/menu/solicitarPedido";
            }

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

        model.addAttribute(MENU_NAME,FRAGMENTO_CADASTRO_DADOS_PRINCIPAIS);
        model.addAttribute(MENU_FRAGMENT,FRAGMENTO_CADASTRO_DADOS_PRINCIPAIS);
        model.addAttribute("cadastroTO",cadastroTO);


        return INDEX;
    }

    @RequestMapping(value = "/menu/finalizarCadastro",method = RequestMethod.POST)
    public String finalizarCadastro(@AuthenticationPrincipal CustomUserDetails userDetails, Model model, FinalizarCadastroTO cadastroTO){


        model.addAttribute(PAGE_NAME,PAGE_CLIENTE);
        model.addAttribute(PAGE_FRAGMENT,PAGE_CLIENTE);
        model.addAttribute(MENU_NAME,MENU_CADASTRO_DADOS_PRINCIPAIS);
        model.addAttribute(MENU_FRAGMENT,FRAGMENTO_CADASTRO_DADOS_PRINCIPAIS);
        model.addAttribute("cadastroTO",cadastroTO);

        try {
            cadastroTO.setEmail(userDetails.getUserlogin().getLogin());
            userDetails.getUserlogin().setCliente(clienteService.concluirCadastro(cadastroTO));
        }catch (Exception e){
            e.printStackTrace();
        }

        return INDEX;
    }

    @RequestMapping(value = "/menu/finalizarCadastro/paraCompra",method = RequestMethod.GET)
    public String finalizarCadastroEFinalizarCompragetformulario(@AuthenticationPrincipal CustomUserDetails userDetails, Model model, FinalizarCadastroTO cadastroTO){

        cadastroTO = inserirDadosDoBanco(userDetails,cadastroTO);

        model.addAttribute(PAGE_NAME,PAGE_CLIENTE);
        model.addAttribute(PAGE_FRAGMENT,PAGE_CLIENTE);

        model.addAttribute(MENU_NAME,MENU_CADASTRO_DADOS_PRINCIPAIS_PARA_COMPRAS);
        model.addAttribute(MENU_FRAGMENT,FRAGMENTO_CADASTRO_DADOS_PRINCIPAIS_PARA_COMPRAS);
        model.addAttribute("cadastroTO",cadastroTO);



        return INDEX;
    }

    @RequestMapping(value = "/menu/finalizarCadastro/paraCompra",method = RequestMethod.POST)
    public String finalizarCadastroEFinalizarCompra(@AuthenticationPrincipal CustomUserDetails userDetails, Model model, FinalizarCadastroTO cadastroTO) throws PagSeguroServiceException {


        model.addAttribute(PAGE_NAME,PAGE_CLIENTE);
        model.addAttribute(PAGE_FRAGMENT,PAGE_CLIENTE);
        model.addAttribute(MENU_NAME,MENU_CADASTRO_DADOS_PRINCIPAIS_PARA_COMPRAS);
        model.addAttribute(MENU_FRAGMENT,FRAGMENTO_CADASTRO_DADOS_PRINCIPAIS_PARA_COMPRAS);
        model.addAttribute("cadastroTO",cadastroTO);

        try {
            cadastroTO.setEmail(userDetails.getUserlogin().getLogin());
            userDetails.getUserlogin().setCliente(clienteService.concluirCadastro(cadastroTO));
        }catch (Exception e){
            e.printStackTrace();
        }

        return criarPedido(userDetails);
    }

    @RequestMapping("/menu/solicitarPedido")
    public String criarPedido(@AuthenticationPrincipal CustomUserDetails userDetails) throws PagSeguroServiceException {

        if (carrinho.getConteudo()!=null){
            RegistrarPedidoTO registrarPedidoTO = CriarRegistrarPedido();
            registrarPedidoTO.setClienteId(String.valueOf(userDetails.getUserlogin().getCliente().getId()));
            Long pedidoId = pedidoService.registrarPedido(registrarPedidoTO);

            String urlPagSeguro = pagamentoServiceImpl.gerarTokenPagamento(carrinho,userDetails.getUserlogin(),String.valueOf(pedidoId),URL_REDIRECIONANMENTO);
            carrinho.limparCarrinho();
            return "redirect:"+urlPagSeguro;
        }


        return "redirect:/cliente/menu/meusPedidos";
    }

    @RequestMapping(value = "/menu/meusPedidos")
    public String meusPedidos(@AuthenticationPrincipal CustomUserDetails userDetails, Model model){

        model.addAttribute(PAGE_NAME,PAGE_CLIENTE);
        model.addAttribute(PAGE_FRAGMENT,PAGE_CLIENTE);
        model.addAttribute(MENU_NAME,MENU_PEDIDOS_CLIENTE);
        model.addAttribute(MENU_FRAGMENT,FRAGMENTO_PEDIDOS_CLIENTE);

        List<Pedido> pedidos = clienteService.meusPedidos(userDetails.getUserlogin().getCliente().getId());
        model.addAttribute("pedidos",pedidos);

        return INDEX;

    }

    @RequestMapping(value = "/form",method = RequestMethod.GET)
    public String loginPage(Model model,CadastrarClienteTO cadastrarClienteTO){
        model.addAttribute(PAGE_NAME,PAGE_CADASTRO);
        model.addAttribute(PAGE_FRAGMENT,FRAGMENT_CADASTRO);

        return INDEX;
    }


    @RequestMapping(value = "/form",method = RequestMethod.POST)
    public String singUp(@Valid CadastrarClienteTO cadastrarClienteTO, BindingResult bindingResult,
                         Model model, HttpServletRequest request,RedirectAttributes redirectAttrs,
                         HttpServletResponse response){
        model.addAttribute(PAGE_NAME,PAGE_CLIENTE);
        model.addAttribute(PAGE_FRAGMENT,PAGE_CLIENTE);

        model.addAttribute(MENU_NAME,MENU_NAME_HOME);
        model.addAttribute(MENU_FRAGMENT,MENU_FRAGMENT_HOME);

        if (bindingResult.hasErrors()){
            System.out.println("ocorreu um erro");
            return "redirect:/";
        }

        UserLogin user = null;
        try {
            user = loginService.recuperarPorEmail(cadastrarClienteTO.getLogin());
        } catch (RegraDeNegocioException e) {
            if (e.getTipoErro().equals(RegraDeNegocioEnum.NAO_ENCONTRADO)){
                user = clienteService.cadastrarCliente(cadastrarClienteTO);
                redirectAttrs.addAttribute("username",user.getLogin());
                redirectAttrs.addAttribute("password",user.getSenha());
                userDetailsServiceImpl.login(request,user.getLogin(),user.getSenha());
            }


            return "redirect:/cliente/menu";
        }

        if (user!=null){
            redirectAttrs.addFlashAttribute("quickSingErro",true).
                    addFlashAttribute("errorMessage","Usuário já cadastrado");
        }
        return "redirect:/";

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
