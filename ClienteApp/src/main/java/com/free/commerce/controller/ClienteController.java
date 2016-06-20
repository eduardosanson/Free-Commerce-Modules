package com.free.commerce.controller;

import com.free.commerce.entity.Cliente;
import com.free.commerce.entity.Imagem;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.UserLogin;
import com.free.commerce.service.interfaces.ClienteService;
import com.free.commerce.service.interfaces.LoginService;
import com.free.commerce.to.CadastrarClienteTO;
import com.free.commerce.to.FinalizarCadastroTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by pc on 01/05/2016.
 */
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private LoginService loginService;

    private static final Logger logger =Logger.getLogger(ClienteController.class);

    @RequestMapping
    public ResponseEntity<Cliente> buscarCliente(@RequestParam(value = "clienteId",defaultValue = "0") Long id,
                                                 @RequestParam(value = "cpf",defaultValue = "") String cpf){
        if (id==0){
            return new ResponseEntity<Cliente>(clienteService.encontrarPeloCpf(cpf),HttpStatus.OK);
        }

        return new ResponseEntity<Cliente>(clienteService.recuperarProID(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserLogin> cadastraLoja(@Valid @RequestBody CadastrarClienteTO cadastrarClienteTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            logger.info(cadastrarClienteTO);

            return new ResponseEntity<UserLogin>(HttpStatus.BAD_REQUEST);
        }else{

            Cliente cliente = clienteService.realizarCadastroCompleto(cadastrarClienteTO);

            return efetuarLogin(cadastrarClienteTO.getLogin());
        }
    }

    @RequestMapping(params = "login", method = RequestMethod.GET)
    public ResponseEntity<UserLogin> efetuarLogin(@RequestParam("login") String login){
        UserLogin userLogin = null;
        Loja loja=null;
        logger.info("Iniciando Login para: " + login);

        userLogin = loginService.recuperarPorEmail(login);

        if(userLogin == null){

            return new ResponseEntity<UserLogin>(userLogin,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<UserLogin>(userLogin,HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable("id") Long id){
        return new ResponseEntity<Cliente>(clienteService.recuperarProID(id),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/concluirCadastro")
    @ResponseBody
    public ResponseEntity<Cliente> concluirCadastro(@Valid @RequestBody FinalizarCadastroTO cadastrarClienteTO ,BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            logger.info(cadastrarClienteTO);

            return new ResponseEntity<Cliente>(HttpStatus.BAD_REQUEST);
        }else{

            Cliente cliente = clienteService.concluirCadastro(cadastrarClienteTO);

            return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/perfil",method = RequestMethod.PUT)
    public ResponseEntity salvarPerfil(@RequestParam("clienteId") long clienteId, @RequestBody Imagem imagem){

        clienteService.alterarPerfil(clienteId,imagem);

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity atualizar(@RequestBody Cliente cliente){

        if (cliente==null || cliente.getId()==null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Cliente cli = clienteService.recuperarProID(cliente.getId());

        if (cli==null){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        clienteService.atualizar(cliente);

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

}
