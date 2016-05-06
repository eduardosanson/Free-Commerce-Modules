package com.br.free.commerce.services;

import com.br.free.commerce.services.Interface.ClienteService;
import com.br.free.commerce.services.Interface.StoreService;
import com.br.free.commerce.to.BuscarClienteTO;
import com.br.free.commerce.to.CadastrarClienteTO;
import com.br.free.commerce.to.FinalizarCadastroTO;
import com.br.free.commerce.to.StoreForm;
import com.free.commerce.entity.Cliente;
import com.free.commerce.entity.UserLogin;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc on 19/03/2016.
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    private RestTemplate template;

    private static final Logger logger = Logger.getLogger(ClienteServiceImpl.class);

    private static String url ="http://";
    private static String ip ="localhost";
    private static String port =":8085";
    private static String service="/v1/cliente";

    public ClienteServiceImpl() {
        this.template = new RestTemplate();
    }

    @Override
    public UserLogin cadastrarCliente(CadastrarClienteTO cadastrarClienteTO) {
        logger.info("inicio de cadastro de cliente: " + cadastrarClienteTO.getLogin());
        String requestUrl;
        Map<String, CadastrarClienteTO> map = new HashMap<String, CadastrarClienteTO>();

        UserLogin user= null;
        try{
            requestUrl = url+ip+port+service;
            logger.info("Chamando a url: " + requestUrl);
            map.put("storeForm",cadastrarClienteTO);

            user = template.postForObject(requestUrl,cadastrarClienteTO,UserLogin.class,map);

        }catch (Exception e){

            e.printStackTrace();
            logger.info("erro na comunicação");

        }

        return user;
    }

    @Override
    public Cliente concluirCadastro(FinalizarCadastroTO cadastroTO) {
        logger.info("Concluindo cadastro do cliente : " + cadastroTO.getEmail());
        String requestUrl;

        Cliente cliente= null;
        try{
            requestUrl = "http://localhost:8085/v1/cliente/concluirCadastro";
            logger.info("Chamando a url: " + requestUrl);

            cliente = template.postForObject(requestUrl,cadastroTO,Cliente.class);

        }catch (Exception e){

            e.printStackTrace();
            logger.info("erro na comunicação");

        }

        return cliente;
    }

//    private HttpComponentsClientHttpRequestFactory criarRequestFactory(int connectTimeOut, int readTimeOut){
//
////        HttpComponentsAsyncClientHttpRequestFactory requestFactory = new HttpComponentsAsyncClientHttpRequestFactory();
////        requestFactory.setConnectTimeout(connectTimeOut);
////        requestFactory.setReadTimeout(readTimeOut);
//
//        return requestFactory;

//    }


    @Override
    public Cliente buscarCliente(BuscarClienteTO buscarClienteTO) {
        return null;
    }
}
