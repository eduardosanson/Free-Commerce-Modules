package com.free.commerce.controller;

import com.free.commerce.entity.Enums.AutorizacaoStatus;
import com.free.commerce.service.interfaces.AutorizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pc on 04/04/2016.
 */
@RestController
public class AutorizacaoController {

    @Autowired
    private AutorizacaoService autorizacaoService;

    @RequestMapping(value = "/autorizar",method = RequestMethod.GET)
    public ResponseEntity autorizarSolicitacao(@RequestParam("lojaId") String lojaId){

        if (lojaId==null || lojaId==""){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        autorizacaoService.atualizarAutorizacao(AutorizacaoStatus.AUTORIZADO,lojaId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/cancelar",method = RequestMethod.GET)
    public ResponseEntity cancelarSolicitacao(@RequestParam("lojaId") String lojaId){

        if (lojaId==null || lojaId==""){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        autorizacaoService.atualizarAutorizacao(AutorizacaoStatus.CANCELADO,lojaId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/pendente",method = RequestMethod.GET)
    public ResponseEntity deixarSolicitacaoPendente(@RequestParam("lojaId") String lojaId){

        if (lojaId==null || lojaId==""){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

        autorizacaoService.atualizarAutorizacao(AutorizacaoStatus.PENDENTE_AUTORIZACAO,lojaId);

        return new ResponseEntity(HttpStatus.OK);
    }
}
