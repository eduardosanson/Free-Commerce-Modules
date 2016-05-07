package com.free.commerce.service;

import com.free.commerce.entity.Cliente;
import com.free.commerce.entity.Endereco;
import com.free.commerce.entity.Enums.Role;
import com.free.commerce.entity.UserLogin;
import com.free.commerce.repository.ClienteRepository;
import com.free.commerce.repository.UserRepository;
import com.free.commerce.service.interfaces.ClienteService;
import com.free.commerce.to.CadastrarClienteTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by eduardosanson on 05/03/16.
 */
@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository repository;

    private RestTemplate restTemplate = new RestTemplate();

    private static final Logger LOGGER = Logger.getLogger(CategoriaServiceImpl.class);

    @Override
    public Cliente recuperarProID(Long id) {
        String url = "http://localhost:8085/v1/cliente/"+id;
        Cliente cliente = null;

        try {
            cliente = restTemplate.getForObject(url,Cliente.class);

        }catch (Exception e){
            e.printStackTrace();
        }
        return cliente;
    }



}

