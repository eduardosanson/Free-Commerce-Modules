package com.free.commerce.controller;

import com.free.commerce.service.interfaces.EnderecoService;
import com.free.commerce.service.interfaces.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by pc on 13/06/2016.
 */
@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @RequestMapping("/cidades")
    public ResponseEntity<List> getCidades(){
        return new ResponseEntity<List>(enderecoService.cidadesCadastradas(),HttpStatus.OK);
    }
}
