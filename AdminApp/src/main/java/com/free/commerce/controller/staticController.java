package com.free.commerce.controller;

import com.free.commerce.repository.ClienteRepository;
import com.free.commerce.repository.EnderecoRepository;
import com.free.commerce.repository.LojaRepository;
import com.free.commerce.repository.ProdutoRepository;
import com.free.commerce.service.StaticServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pc on 20/06/2016.
 */
@RestController
@RequestMapping("/staticController")
public class staticController {

    @Autowired
    private StaticServices staticServices;

    @RequestMapping("/total/Users")
    public ResponseEntity<Long> totalDeUsuarios(){
        staticServices.clientes();
        return new ResponseEntity<Long>(staticServices.clientes(), HttpStatus.OK);
    }

    @RequestMapping("/total/sellers")
    public ResponseEntity<Long> totalDeVendedores(){
        return new ResponseEntity<Long>(staticServices.vendedores(), HttpStatus.OK);

    }


    @RequestMapping("/total/items")
    public ResponseEntity<Long> totaoDeProdutos(){
        return new ResponseEntity<Long>(staticServices.items(), HttpStatus.OK);
    }


    @RequestMapping("/total/location")
    public ResponseEntity<Long> totaoDeLugares(){
        return new ResponseEntity<Long>(staticServices.lugares(), HttpStatus.OK);
    }
}
