package com.free.commerce.controller;

import com.free.commerce.entity.Setor;
import com.free.commerce.exception.RegraDeNegocioException;
import com.free.commerce.exception.enuns.RegraDeNegocioEnum;
import com.free.commerce.service.interfaces.SetorService;
import com.free.commerce.to.CadastrarSetorTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by pc on 04/04/2016.
 */
@RestController
@RequestMapping("/setor")
public class SetorController {

    @Autowired
    private SetorService setorService;

    private static Logger logger = Logger.getLogger(SetorController.class);

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Setor> cadastrarSetor(@Valid @RequestBody CadastrarSetorTO cadastrarSetorTO, BindingResult bindingResult){
        Setor setor = new Setor();


        if (bindingResult.hasErrors()){
            logger.error("Requisição mal feita");
            return new ResponseEntity<Setor>(HttpStatus.BAD_REQUEST);
        }
        try{
            setor = setorService.cadastrarSetor(cadastrarSetorTO);

        }catch (RegraDeNegocioException re){
            if (re.getTipoErro().equals(RegraDeNegocioEnum.VALOR_JA_CADASTRADO)){
                return new ResponseEntity<Setor>(HttpStatus.CONFLICT);
            }

        }

        return new ResponseEntity<Setor>(setor, HttpStatus.OK);
    }

    @RequestMapping(value = "id",method = RequestMethod.GET)
    public ResponseEntity<Setor> buscarPorId(@RequestParam("id") String id){
        Setor setor = new Setor();

        return new ResponseEntity<Setor>(setor,HttpStatus.OK);

    }
}
