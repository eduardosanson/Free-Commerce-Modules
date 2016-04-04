package com.free.commerce.controller;

import com.free.commerce.entity.Loja;
import com.free.commerce.entity.UserLogin;
import com.free.commerce.service.interfaces.AutorizacaoService;
import com.free.commerce.service.interfaces.LoginService;
import com.free.commerce.service.interfaces.LojaService;
import com.free.commerce.to.StoreForm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    public ResponseEntity<UserLogin> cadastraLoja(@Valid @RequestBody StoreForm storeForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            logger.info(storeForm);

            return new ResponseEntity<UserLogin>(HttpStatus.BAD_REQUEST);
        }else{

            Loja loja = lojaService.realizarCadastroCompleto(storeForm);

            return efetuarLogin(storeForm.getEmail());
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

}
