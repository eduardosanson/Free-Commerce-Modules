package com.free.commerce.controller;

import com.free.commerce.entity.CarrinhoDeCompras;
import com.free.commerce.service.interfaces.CarrinhoService;
import com.free.commerce.to.CarrinhoDeComprasTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by pc on 30/04/2016.
 */
@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @RequestMapping(params = {"cokieId"})
    public ResponseEntity<List<CarrinhoDeCompras>> recuperarProdutos(@RequestParam("cookieId") String cookieId){
        List<CarrinhoDeCompras> carrinhos = null;
        try {
            carrinhos = carrinhoService.buscarCarProdutoPeloCookie(cookieId);

            if (carrinhos==null||carrinhos.isEmpty()){
                return new ResponseEntity<List<CarrinhoDeCompras>>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<List<CarrinhoDeCompras>>(carrinhos,HttpStatus.FOUND);


        }catch (Exception e){

            return new ResponseEntity<List<CarrinhoDeCompras>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> salvarCarrinho(@RequestBody CarrinhoDeComprasTO carrinhoDeComprasTO){
        CarrinhoDeCompras carrinhoDeCompras = null;
        try {

            carrinhoService.cadastrarCarrinhoDeCompras(carrinhoDeComprasTO);

            return new ResponseEntity<Object>(HttpStatus.OK);


        }catch (Exception e){

            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
