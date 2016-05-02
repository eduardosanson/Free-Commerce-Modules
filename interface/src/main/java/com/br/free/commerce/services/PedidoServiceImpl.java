package com.br.free.commerce.services;

import com.br.free.commerce.services.Interface.PedidoService;
import com.br.free.commerce.to.RegistrarPedidoTO;
import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pc on 02/05/2016.
 */
public class PedidoServiceImpl implements PedidoService {

    private static final Logger LOGGER = Logger.getLogger(PedidoServiceImpl.class);

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void registrarPedido(RegistrarPedidoTO registrarPedidoTO) {
        String url = "http://localhost:8090/v1/controller";

        try {

            restTemplate.postForObject(url,registrarPedidoTO,Object.class);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
