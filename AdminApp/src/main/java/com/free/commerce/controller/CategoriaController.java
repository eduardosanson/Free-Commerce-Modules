package com.free.commerce.controller;

import com.free.commerce.entity.Categoria;
import com.free.commerce.service.interfaces.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by pc on 11/04/2016.
 */
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping
    public ResponseEntity<List<Categoria>> buscarCategoria(){

        List<Categoria> categorias = categoriaService.buscarCategoria();

        if (categorias==null||categorias.isEmpty()){
            return new ResponseEntity<List<Categoria>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Categoria>>(categorias,HttpStatus.OK);
    }

//    public
}
