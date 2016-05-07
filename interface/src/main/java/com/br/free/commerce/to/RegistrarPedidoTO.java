package com.br.free.commerce.to;

import java.util.List;

/**
 * Created by pc on 02/05/2016.
 */
public class RegistrarPedidoTO {

    private List<ProdutoPedido> produtoPedido;

    private String clienteId;

    public List<ProdutoPedido> getProdutoPedido() {
        return produtoPedido;
    }

    public void setProdutoPedido(List<ProdutoPedido> produtoPedido) {
        this.produtoPedido = produtoPedido;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }
}
