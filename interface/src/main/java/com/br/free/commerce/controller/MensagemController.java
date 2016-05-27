package com.br.free.commerce.controller;

import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.services.MensagemServiceImpl;
import com.br.free.commerce.to.CadastrarMensagemTO;
import com.free.commerce.entity.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by pc on 24/05/2016.
 */
@Controller
@RequestMapping("/mensagem")
public class MensagemController {

    @Autowired
    private MensagemServiceImpl mensagemService;

    @RequestMapping(method = RequestMethod.POST)
    public String cadastrarMensagem(CadastrarMensagemTO cadastrarMensagemTO, Model model, @AuthenticationPrincipal CustomUserDetails userDetails){

        if (ehClienteQueEstasEnviandoAPergunta(userDetails)){
            enviarPergunta(cadastrarMensagemTO, userDetails);
        }else {
            model.addAttribute("cadastrarMensagemTO",cadastrarMensagemTO);
            model.addAttribute("showModal",true);
        }

        return "redirect:/produto/detail/"+cadastrarMensagemTO.getProdutoId();
    }

    private boolean ehClienteQueEstasEnviandoAPergunta(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return userDetails !=null && userDetails.getUserlogin() !=null
                && userDetails.getUserlogin().getCliente()!=null;
    }

    private void enviarPergunta(CadastrarMensagemTO cadastrarMensagemTO, @AuthenticationPrincipal CustomUserDetails userDetails) {
        cadastrarMensagemTO.getMensagem().setCliente(userDetails.getUserlogin().getCliente());
        mensagemService.cadastrarMensagem(cadastrarMensagemTO.getProdutoId(),cadastrarMensagemTO.getMensagem());
    }

    public List<Mensagem> buscarMensagens(Long produtoId){
        return mensagemService.recuperarMensagensPorProduto(produtoId,0,5);
    }

}
