package com.br.free.commerce.services;

import com.br.free.commerce.services.Interface.PedidoService;
import com.br.free.commerce.to.AlterarStatusPedidoTO;
import com.br.free.commerce.to.RegistrarPedidoTO;
import com.free.commerce.entity.Enums.PedidoStatus;
import com.free.commerce.entity.Pedido;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public Pedido buscarPorId(String id) {
        String url = "http://lojacommerce.herokuapp.com/pedido/"+id;

        return restTemplate.getForObject(url,Pedido.class);

    }

    @Override
    public void alterarStatus(String notificationCode, PedidoStatus status,String id) {
        String url = "http://lojacommerce.herokuapp.com/pedido/status/"+id;

        Map<String,String> parameters = new HashMap();

        parameters.put("notificationCode",notificationCode);
        parameters.put("status",status.name());
        AlterarStatusPedidoTO alterarStatusPedidoTO = new AlterarStatusPedidoTO();

        alterarStatusPedidoTO.setNotificationCode(notificationCode);
        alterarStatusPedidoTO.setStatus(status.name());

        restTemplate.postForObject(url,new AlterarStatusPedidoTO(),Pedido.class);

    }

    @Override
    public void confirmaPagamento(String id) {
        String url = "http://lojacommerce.herokuapp.com/pedido/confirmarPagamento/"+id;

        restTemplate.postForObject(url,id,Pedido.class);

    }
}
