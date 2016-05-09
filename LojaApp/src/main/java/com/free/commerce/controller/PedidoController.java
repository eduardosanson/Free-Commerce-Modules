package com.free.commerce.controller;

import com.free.commerce.entity.Cliente;
import com.free.commerce.entity.Pedido;
import com.free.commerce.service.interfaces.PedidoService;
import com.free.commerce.to.RegistrarPedidoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by pc on 02/05/2016.
 */
@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping
    public ResponseEntity registrarPedido(@RequestBody RegistrarPedidoTO registrarPedidoTO){

        try{
            pedidoService.registrarPedido(registrarPedidoTO);

            return new ResponseEntity(HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @RequestMapping("/cliente")
    public ResponseEntity<List<Pedido>> buscarpedidosDeCliente(@RequestParam("clienteId") String clienteId){


        List<Pedido> pedidos =pedidoService.buscarPedidoDeCliente(clienteId);

        if (pedidos==null||pedidos.isEmpty()){
            return new ResponseEntity<List<Pedido>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Pedido>>(pedidos,HttpStatus.FOUND);



    }

}
