package com.free.commerce.controller;

import com.free.commerce.entity.Loja;
import com.free.commerce.entity.UserLogin;
import com.free.commerce.service.interfaces.LoginService;
import com.free.commerce.service.interfaces.LojaService;
import com.free.commerce.to.LoginResponse;
import com.free.commerce.to.StoreForm;
import com.free.commerce.to.StoreResponse;
import com.free.commerce.to.ValidationErrorTO;
import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
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

    private MessageSource messageSource;

    private static final Logger logger =Logger.getLogger(LojaController.class);

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<StoreResponse> cadastraLoja(@Valid StoreForm storeForm, BindingResult bindingResult){

        StoreResponse response = new StoreResponse();
        Loja loja =null;

        if(bindingResult.hasErrors()){
            logger.info(storeForm);
            response.setErrors(ValidationErrorTO.exactlyErroMessage(bindingResult));

            return new ResponseEntity<StoreResponse>(response,HttpStatus.BAD_REQUEST);
        }else{

            loja = lojaService.realizarCadastroCompleto(storeForm);
            response.setLoja(loja);

            return new ResponseEntity<StoreResponse>(response,HttpStatus.CREATED);
        }
    }

    @RequestMapping(params = "login", method = RequestMethod.GET)
    public ResponseEntity<UserLogin> efetuarLogin(@RequestParam("login") String login){
        UserLogin userLogin = null;
        Loja loja=null;
        LoginResponse response = new LoginResponse();
        logger.info("Iniciando Login para: " + login);

        userLogin = loginService.recuperarPorEmail(login);

        if(userLogin == null){
            response.setMensagem("Usuario não encontrado");
            return new ResponseEntity<UserLogin>(userLogin,HttpStatus.NOT_FOUND);
        }

        response.setUserLogin(userLogin);

        return new ResponseEntity<UserLogin>(userLogin,HttpStatus.OK);
   }

}
