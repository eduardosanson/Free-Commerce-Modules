package com.free.commerce.controller;

import com.free.commerce.entity.Cliente;
import com.free.commerce.entity.Enums.PedidoStatus;
import com.free.commerce.entity.Pedido;
import com.free.commerce.service.interfaces.PedidoService;
import com.free.commerce.to.AlterarStatusPedidoTO;
import com.free.commerce.to.RegistrarPedidoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

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
    public ResponseEntity<Long> registrarPedido(@RequestBody RegistrarPedidoTO registrarPedidoTO){

        try{


            return new ResponseEntity<Long>(pedidoService.registrarPedido(registrarPedidoTO).getId(),HttpStatus.OK);

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

    @RequestMapping("/loja")
    public ResponseEntity<List<Pedido>> buscarSolicitacaoDeLojas(@RequestParam("lojaId") Long lojaId){


        List<Pedido> pedidos =pedidoService.buscarSolicitacaoLoja(lojaId);

        if (pedidos==null||pedidos.isEmpty()){
            return new ResponseEntity<List<Pedido>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Pedido>>(pedidos,HttpStatus.FOUND);

    }

    @RequestMapping(value ="/{id}" ,method = RequestMethod.GET)
    public ResponseEntity<Pedido> pedidoResponseEntity(@PathVariable("id") Long id){

        return new ResponseEntity<Pedido>(pedidoService.buscarPeloId(id),HttpStatus.OK);
    }


    @RequestMapping(value = "/status/{id}",method = RequestMethod.POST)
    public ResponseEntity<Pedido> alterarStatus(@RequestBody AlterarStatusPedidoTO alterarStatusPedidoTO,
                                                @PathVariable("id") Long id){
        try {

            pedidoService.aterarStatus(id,PedidoStatus.getPedidoEstatusByString(alterarStatusPedidoTO.getStatus()),alterarStatusPedidoTO.getNotificationCode());

        }catch (HttpClientErrorException e){

            return new ResponseEntity<Pedido>(e.getStatusCode());
        }

        return new ResponseEntity<Pedido>(HttpStatus.OK);
    }

    @RequestMapping(value = "/confirmarPagamento/{id}",method = RequestMethod.POST)
    public ResponseEntity confirmarPagamento(@PathVariable("id") Long id){

        try {
            pedidoService.confirmarPagamentos(id);
            return  new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
