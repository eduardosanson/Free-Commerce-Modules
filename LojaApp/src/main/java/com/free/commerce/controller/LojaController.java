package com.free.commerce.controller;

import com.free.commerce.entity.Imagem;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.UserLogin;
import com.free.commerce.service.interfaces.AutorizacaoService;
import com.free.commerce.service.interfaces.LoginService;
import com.free.commerce.service.interfaces.LojaService;
import com.free.commerce.to.CadastrarLojaTO;
import com.free.commerce.to.ResponderMensagemTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by eduardosanson on 05/03/16.
 */

@RestController
@RequestMapping(value = "/loja")
public class LojaController extends WebMvcConfigurerAdapter {

    @Autowired
    private LojaService lojaService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private AutorizacaoService autorizacaoService;

    private MessageSource messageSource;

    private static final Logger logger =Logger.getLogger(LojaController.class);

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserLogin> cadastraLoja(@Valid @RequestBody CadastrarLojaTO cadastrarLojaTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            logger.info(cadastrarLojaTO);

            return new ResponseEntity<UserLogin>(HttpStatus.BAD_REQUEST);
        }else{

            Loja loja = lojaService.realizarCadastroCompleto(cadastrarLojaTO);

            return efetuarLogin(cadastrarLojaTO.getEmail());
        }
    }

    @RequestMapping(params = "login", method = RequestMethod.GET)
    public ResponseEntity<UserLogin> efetuarLogin(@RequestParam("login") String login){
        UserLogin userLogin = null;
        Loja loja=null;

        logger.info("Iniciando Login para: " + login);

        userLogin = loginService.recuperarPorEmail(login);

        if(userLogin == null){

            return new ResponseEntity<UserLogin>(userLogin,HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<UserLogin>(userLogin,HttpStatus.OK);
   }

    @RequestMapping(value = "/pendente",method = RequestMethod.GET)
    public ResponseEntity<List<Loja>> buscarLojasPendentesDeAutorizacao(){

        List<Loja> lojas = new ArrayList<Loja>();
        lojas = autorizacaoService.buscarLojasPendentesDeAutorizacao();

        if (lojas==null || lojas.isEmpty()){
            return new ResponseEntity<List<Loja>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Loja>>(lojas,HttpStatus.FOUND);

    }

    @RequestMapping(params = {"produtoId"})
    public ResponseEntity<Loja> buscarPorProduto(@RequestParam("produtoId") String produtoId){
        Loja loja = null;

        try {

            if (produtoId==null || produtoId==""){
                return new ResponseEntity<Loja>(HttpStatus.BAD_REQUEST);
            }

            loja = lojaService.recuperarPorIdDeProduto(produtoId);

            if (loja==null){
                return new ResponseEntity<Loja>(HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<Loja>(loja,HttpStatus.FOUND);
            }

        }catch (Exception e){
            e.printStackTrace();

            return new ResponseEntity<Loja>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping("/{lojaId}")
    public ResponseEntity<Loja> buscarPorId(@PathVariable("lojaId") Long lojaId){
        Loja loja = null;

        try {

              loja = lojaService.recuperarPorId(lojaId);

            if (loja==null){
                return new ResponseEntity<Loja>(HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<Loja>(loja,HttpStatus.FOUND);
            }

        }catch (Exception e){
            e.printStackTrace();

            return new ResponseEntity<Loja>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/perfil",method = RequestMethod.PUT)
    public ResponseEntity salvarPerfil(@RequestParam("lojaId") long lojaId, @RequestBody Imagem imagem){

        lojaService.alterarPerfil(lojaId,imagem);

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity atualizar(@RequestBody Loja loja){

        if (loja==null || loja.getId()==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Loja l = lojaService.recuperarPorId(loja.getId());

        if (l==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        lojaService.atualizar(loja);

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }


}
