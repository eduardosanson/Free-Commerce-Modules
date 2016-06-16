package com.br.free.commerce.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by pc on 13/06/2016.
 */
@Service
public class EnderecoServiceImpl {

    private RestTemplate restTemplate = new RestTemplate();

    public List<String> cidadesEmLojasCadastradas(){

        return restTemplate.getForObject("http://lojacommerce.herokuapp.com/endereco/cidades",List.class);

    }
}
