package com.free.commerce.controller;

import com.free.commerce.entity.AutorizacaoLoja;
import com.free.commerce.entity.Enums.AutorizacaoStatus;
import com.free.commerce.service.interfaces.AutorizacaoService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping(value = "/autorizacao",method = RequestMethod.GET)
    public ResponseEntity<List> autorizacoes(@RequestParam(value = "status",defaultValue = "") String status,
                                             @RequestParam(value = "nome",defaultValue = "") String nome,
                                             @RequestParam("pagina") int pagina,
                                             @RequestParam("limite") int limite){

        List<AutorizacaoLoja> autorizacoes;
        Pageable pageable = new PageRequest(pagina,limite);

        if (status!=null && !status.equalsIgnoreCase("")){

            if (nome!=null && !nome.equalsIgnoreCase("")){
                autorizacoes = autorizacaoService.buscarPelosNomesDeLojasEStatus(nome,status,pageable);
            }else {

                autorizacoes = autorizacaoService.buscarPorStatus(status,pageable);
            }
        }else if (nome!=null && !nome.equalsIgnoreCase("")){

            autorizacoes = autorizacaoService.buscarPelosNomesDeLojas(nome,pageable);

        }else {

            autorizacoes = autorizacaoService.buscarAutorizacoes(pageable);
        }

        return new ResponseEntity(autorizacoes,HttpStatus.OK);

    }


}
