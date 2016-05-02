package com.br.free.commerce.to;

import java.util.Map;

/**
 * Created by pc on 02/05/2016.
 */
public class RegistrarPedidoTO {

    private Map<String,String> produtoQuantidade;

    private String clienteId;

    public Map<String, String> getProdutoQuantidade() {
        return produtoQuantidade;
    }

    public void setProdutoQuantidade(Map<String, String> produtoQuantidade) {
        this.produtoQuantidade = produtoQuantidade;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }
}
