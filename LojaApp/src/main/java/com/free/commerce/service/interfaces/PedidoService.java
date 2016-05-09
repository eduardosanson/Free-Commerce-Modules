package com.free.commerce.service.interfaces;

import com.free.commerce.entity.Cliente;
import com.free.commerce.entity.Pedido;
import com.free.commerce.to.RegistrarPedidoTO;

import java.util.List;

/**
 * Created by pc on 02/05/2016.
 */
public interface PedidoService {

    void registrarPedido(RegistrarPedidoTO registrarPedidoTO);

    List<Pedido> buscarPedidoDeCliente(String clienteId);
}
