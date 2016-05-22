package com.free.commerce.controller;

import com.free.commerce.entity.Administrador;
import com.free.commerce.entity.Imagem;
import com.free.commerce.entity.Loja;
import com.free.commerce.service.interfaces.AdminService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by eduardosanson on 05/03/16.
 */

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    private static final Logger logger =Logger.getLogger(AdminController.class);
//
//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<UserLogin> cadastraAdm(@Valid @RequestBody CadastrarLojaTO cadastrarLojaTO, BindingResult bindingResult){
//
//        if(bindingResult.hasErrors()){
//            logger.info(cadastrarLojaTO);
//
//            return new ResponseEntity<UserLogin>(HttpStatus.BAD_REQUEST);
//        }else{
//
//            Loja loja = lojaService.realizarCadastroCompleto(cadastrarLojaTO);
//
//            return efetuarLogin(cadastrarLojaTO.getEmail());
//        }
//    }

    @RequestMapping("/{adminId}")
    public ResponseEntity<Administrador> buscarPorId(@PathVariable("adminId") Long adminId){
        Administrador administrador = null;

        try {

              administrador = adminService.recuperarPorId(adminId);

            if (administrador==null){
                return new ResponseEntity<Administrador>(HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<Administrador>(administrador,HttpStatus.FOUND);
            }

        }catch (Exception e){
            e.printStackTrace();

            return new ResponseEntity<Administrador>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/perfil",method = RequestMethod.PUT)
    public ResponseEntity salvarPerfil(@RequestParam("adminId") long adminId, @RequestBody Imagem imagem){

        adminService.alterarPerfil(adminId,imagem);

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

}
