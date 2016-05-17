package com.br.free.commerce.controller;

import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.to.ResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * Created by pc on 16/05/2016.
 */
@RestController
@RequestMapping("/rest/produto")
public class ProdutoRestController {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value = "/menu/upload", method = RequestMethod.POST)
    public ResponseEntity<ResponseTO> associarImagem(@RequestParam(value = "file") MultipartFile file, @RequestParam("produtoId") String produtoId, HttpSession session) {

        ResponseTO responseTO = new ResponseTO();
        try {


            produtoService.associarImagem(file, produtoId);

        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        responseTO.setCodigo("000");
        responseTO.setMensagem("sucesso");

        return new ResponseEntity(responseTO,HttpStatus.OK);

    }
}
