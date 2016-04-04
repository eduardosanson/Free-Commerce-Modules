package com.free.commerce.controller;

import com.free.commerce.entity.Cor;
import com.free.commerce.entity.Marca;
import com.free.commerce.entity.TamanhoLetra;
import com.free.commerce.entity.TamanhoNumero;
import com.free.commerce.service.interfaces.PropriedadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by pc on 04/04/2016.
 */
@RestController
@RequestMapping("/propriedades")
public class PropriedadesController {

    @Autowired
    private PropriedadeService propriedadeService;

    @RequestMapping(value = "/cores",method = RequestMethod.GET)
    public ResponseEntity<List<Cor>> buscarTodasAsCores(){

        List<Cor> cores = propriedadeService.buscarTodasCores();

        if (cores==null ||cores.isEmpty()){
            return new ResponseEntity<List<Cor>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Cor>>(cores,HttpStatus.FOUND);

    }

    @RequestMapping(value = "/marcas",method = RequestMethod.GET)
    public ResponseEntity<List<Marca>> buscarTodasAsMarcas(){

        List<Marca> marcas = propriedadeService.buscarTodasMarcas();

        if (marcas==null ||marcas.isEmpty()){
            return new ResponseEntity<List<Marca>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Marca>>(marcas,HttpStatus.FOUND);

    }

    @RequestMapping(value = "/tamanhoLetra",method = RequestMethod.GET)
    public ResponseEntity<List<TamanhoLetra>> buscarTodosTamanhoLetra(){

        List<TamanhoLetra> tamanhoLetras = propriedadeService.buscarTodosTamanhoLetras();

        if (tamanhoLetras==null ||tamanhoLetras.isEmpty()){
            return new ResponseEntity<List<TamanhoLetra>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<TamanhoLetra>>(tamanhoLetras,HttpStatus.FOUND);

    }

    @RequestMapping(value = "/tamanhoNumero",method = RequestMethod.GET)
    public ResponseEntity<List<TamanhoNumero>> buscarTodosTamanhoNumeros(){

        List<TamanhoNumero> tamanhoNumeros = propriedadeService.buscarTodosTamanhoNumeros();

        if (tamanhoNumeros==null ||tamanhoNumeros.isEmpty()){
            return new ResponseEntity<List<TamanhoNumero>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<TamanhoNumero>>(tamanhoNumeros,HttpStatus.FOUND);

    }
}
