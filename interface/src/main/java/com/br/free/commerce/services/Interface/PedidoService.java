package com.br.free.commerce.services.Interface;

import com.br.free.commerce.to.RegistrarPedidoTO;
import com.free.commerce.entity.Enums.PedidoStatus;
import com.free.commerce.entity.Pedido;

/**
 * Created by pc on 02/05/2016.
 */
public interface PedidoService {

    Long registrarPedido(RegistrarPedidoTO registrarPedidoTO);

    Pedido buscarPorId(String id);

    void alterarStatus(String notificationCode, PedidoStatus status, String id);

    void confirmaPagamento(String id);
}
