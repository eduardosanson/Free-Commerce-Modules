package com.free.commerce.controller;

import com.free.commerce.entity.Categoria;
import com.free.commerce.exception.RegraDeNegocioException;
import com.free.commerce.exception.enuns.RegraDeNegocioEnum;
import com.free.commerce.service.interfaces.CategoriaService;
import com.free.commerce.to.CategoriaTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.Param;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Categoria> cadastrarCategoria(@Valid @RequestBody CategoriaTO categoriaTO, BindingResult bindingResult){

        Categoria categoria = null;
        if (bindingResult.hasErrors()){
            return new ResponseEntity<Categoria>(HttpStatus.BAD_REQUEST);
        }

        try{
            categoria = categoriaService.cadastrarCategoria(categoriaTO);

        }catch (RegraDeNegocioException re){
            if (re.getTipoErro()== RegraDeNegocioEnum.VALOR_JA_CADASTRADO){
                return new ResponseEntity<Categoria>(HttpStatus.CONFLICT);
            }

        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Categoria>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<Categoria>(categoria,HttpStatus.OK);
    }

    @RequestMapping(params = {"principal"})
    public ResponseEntity<List<Categoria>> buscarCategorias(@RequestParam("principal") boolean principal){
        List<Categoria> categorias;

        if (principal==true){

            try{

                categorias = categoriaService.buscarCategoriasPrincipais();
                if (categorias ==null|| categorias.isEmpty()){

                    return new ResponseEntity<List<Categoria>>(HttpStatus.NOT_FOUND);
                }

                return new ResponseEntity<List<Categoria>>(categorias,HttpStatus.OK);
            }catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<List<Categoria>>(HttpStatus.INTERNAL_SERVER_ERROR);

            }

        }else {
         return new ResponseEntity<List<Categoria>>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(path = {"/{id}"})
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable("id") Long id){
        Categoria categoria =null;
        try {
            categoria = categoriaService.buscarCategoriaPorId(id);
            if (categoria==null){
                return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<Categoria>(categoria,HttpStatus.FOUND);
            }

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Categoria>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(params = {"nome"})
    public ResponseEntity<Categoria> buscarPeloNome(@PathParam("nome") String nome){
        Categoria categoria =null;
        try {
            categoria = categoriaService.buscarPorNome(nome);
            if (categoria==null){
                return new ResponseEntity<Categoria>(HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<Categoria>(categoria,HttpStatus.FOUND);
            }

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Categoria>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
