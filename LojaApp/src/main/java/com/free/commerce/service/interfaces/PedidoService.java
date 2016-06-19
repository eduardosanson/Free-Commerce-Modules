package com.free.commerce.service.interfaces;

import com.free.commerce.entity.Cliente;
import com.free.commerce.entity.Enums.PedidoStatus;
import com.free.commerce.entity.Pedido;
import com.free.commerce.to.RegistrarPedidoTO;

import java.util.List;

/**
 * Created by pc on 02/05/2016.
 */
public interface PedidoService {

    Pedido registrarPedido(RegistrarPedidoTO registrarPedidoTO);

    List<Pedido> buscarPedidoDeCliente(String clienteId);

    List<Pedido> buscarSolicitacaoLoja(Long lojaId);

    Pedido buscarPeloId(Long id);

    void aterarStatus(Long id, PedidoStatus status, String notificationCode);

    void confirmarPagamentos(Long id);
}
