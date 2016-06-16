package com.br.free.commerce.services;

import com.br.free.commerce.services.Interface.PedidoService;
import com.br.free.commerce.to.RegistrarPedidoTO;
import com.free.commerce.entity.Pedido;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pc on 02/05/2016.
 */
@Service
public class PedidoServiceImpl implements PedidoService {

    private static final Logger LOGGER = Logger.getLogger(PedidoServiceImpl.class);

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public Long registrarPedido(RegistrarPedidoTO registrarPedidoTO) {
        String url = "http://lojacommerce.herokuapp.com/pedido";



        return restTemplate.postForObject(url,registrarPedidoTO,Long.class);

    }
}
