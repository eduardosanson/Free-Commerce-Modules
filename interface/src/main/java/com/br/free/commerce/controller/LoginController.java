package com.br.free.commerce.controller;

import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.services.UserDetailsServiceImpl;
import com.br.free.commerce.to.CadastrarMensagemTO;
import com.br.free.commerce.to.MensagemLoginTO;
import com.free.commerce.entity.Mensagem;
import com.free.commerce.entity.Produto;
import com.free.commerce.entity.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by eduardo.sanson on 17/03/2016.
 */
@Controller
@RequestMapping
public class LoginController {

    private static final String LOGIN="index";
    private static final String PAGE_NAME="pageName";
    private static final String PAGE_FRAGMENT="pageFragment";
    private static final String PAGE_LOGIN="login";
    private static final String FRAGMENT_LOGIN="login";
    private static final String PAGE_LOGIN_PARA_FINALIZAR_COMPRA="login-compra";
    private static final String FRAGMENT_LOGIN_PARA_FINALIZAR_COMPRA="login-compra";

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private MensagemController mensagemController;


    @RequestMapping("/public/login")
    public String loginPage(Model model){
        model.addAttribute(PAGE_NAME,PAGE_LOGIN);
        model.addAttribute(PAGE_FRAGMENT,FRAGMENT_LOGIN);

        return LOGIN;
    }

    @RequestMapping(value = "/public/login/mensagem",method = RequestMethod.POST)
    public String loginParaEnviarMensagem(MensagemLoginTO mensagemLoginTO, HttpServletRequest request, Model model,@AuthenticationPrincipal CustomUserDetails userDetails){
        CadastrarMensagemTO cadastrarMensagemTO = new CadastrarMensagemTO();
        String urlDaRequisicao = request.getRequestURI();
        String erroAoLogar = "Login ou Senha não conferem";


        try {

            userDetails = (CustomUserDetails) userDetailsServiceImpl.loadUserByUsername(mensagemLoginTO.getLogin());
        }catch (AuthenticationException e){
            model.addAttribute("loginError",erroAoLogar);
            return "redirect:/produto/detail/"+mensagemLoginTO.getProdutoId();
        }


        if(!userDetails.getUserlogin().getSenha().equalsIgnoreCase(mensagemLoginTO.getSenha())){
            model.addAttribute("loginError",erroAoLogar);
            return "redirect:/produto/detail/"+mensagemLoginTO.getProdutoId();
        }

       userDetailsServiceImpl.login(request,mensagemLoginTO.getLogin(),mensagemLoginTO.getSenha());


        Mensagem mensagem = new Mensagem();
        mensagem.setCliente(userDetails.getUserlogin().getCliente());

        mensagem.setPergunta(mensagemLoginTO.getMensagem());

        cadastrarMensagemTO.setProdutoId(mensagemLoginTO.getProdutoId());
        cadastrarMensagemTO.setMensagem(mensagem);

        return mensagemController.cadastrarMensagem(cadastrarMensagemTO,model,userDetails);
    }

    @RequestMapping("/login")
    public String loginParaFinalizarCompra(Model model){
        model.addAttribute(PAGE_NAME,PAGE_LOGIN_PARA_FINALIZAR_COMPRA);
        model.addAttribute(PAGE_FRAGMENT,FRAGMENT_LOGIN_PARA_FINALIZAR_COMPRA);

        return LOGIN;
    }

//    @RequestMapping("/logout")
//    public String logout(HttpSession session, @AuthenticationPrincipal CustomUserDetails customUserDetails, Model model){
//        session.invalidate();
//
//        return "redirect:/";
//    }

    @RequestMapping(params = "error")
    public String loginError(@RequestParam("error") String login, Model model){
        model.addAttribute("loginError",true);
        return loginPage(model);
    }

}
