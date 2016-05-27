package com.free.commerce.controller;

import com.free.commerce.entity.Mensagem;
import com.free.commerce.entity.Produto;
import com.free.commerce.service.MensagemService;
import com.free.commerce.service.interfaces.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by pc on 24/05/2016.
 */
@RestController
@RequestMapping("/mensagem")
public class MensagemController {

    @Autowired
    private MensagemService mensagemService;

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value = "/produto/{produtoId}",method = RequestMethod.POST)
    public ResponseEntity<Mensagem> enviarMensagem(@RequestBody Mensagem mensagem, @PathVariable Long produtoId){

        Produto produto = produtoService.buscarPorId(produtoId);

        mensagem.setProduto(produto);
        mensagem.setReistrado(new Date());
        return new ResponseEntity<Mensagem>(mensagemService.salvaMensagem(mensagem), HttpStatus.CREATED);
   }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Mensagem> salvarMensagem(@RequestBody Mensagem mensagem){

        mensagemService.salvaMensagem(mensagem);

        return new ResponseEntity<Mensagem>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Mensagem> salvarMensagem(@PathVariable("id") Long id){

        mensagemService.deletarMensagem(id);

        return new ResponseEntity<Mensagem>(HttpStatus.OK);
    }

    @RequestMapping(value = "/produto/{produtoId}/list",method = RequestMethod.GET)
    public ResponseEntity<List<Mensagem>> buscarMensagemPorPruduto(@PathVariable("produtoId") Long id){

        return new ResponseEntity<List<Mensagem>>(mensagemService.buscarPorProduto(id),HttpStatus.OK);
    }

    @RequestMapping(value = "/produto/{produtoId}",method = RequestMethod.GET)
    public ResponseEntity<List<Mensagem>> buscarMensagemPorPruduto(@PathVariable("produtoId") Long id,
                                                                   @RequestParam("pagina") int pagina,
                                                                   @RequestParam("limite") int limite){

        return new ResponseEntity<List<Mensagem>>(mensagemService.buscarPorProdutoPageando(id, new PageRequest(pagina,limite)),HttpStatus.OK);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseEntity<Mensagem> buscarPorId(@PathVariable Long id){

        return new ResponseEntity<Mensagem>(mensagemService.buscarPorId(id),HttpStatus.OK);
    }
}
