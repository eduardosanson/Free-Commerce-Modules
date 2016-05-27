package com.free.commerce.to;

/**
 * Created by pc on 26/05/2016.
 */
public class ResponderMensagemTO {

    private Long produtoId;

    private String mensafgem;

    private Long mensagemId;

    public String getMensafgem() {
        return mensafgem;
    }

    public void setMensafgem(String mensafgem) {
        this.mensafgem = mensafgem;
    }

    public Long getMensagemId() {
        return mensagemId;
    }

    public void setMensagemId(Long mensagemId) {
        this.mensagemId = mensagemId;
    }

    public Long getProdutoId() {

        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }
}
