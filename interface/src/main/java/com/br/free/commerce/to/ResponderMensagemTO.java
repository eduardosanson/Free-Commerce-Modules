package com.br.free.commerce.to;

/**
 * Created by pc on 26/05/2016.
 */
public class ResponderMensagemTO {

    private Long produtoId;

    private String resposta;

    private Long mensagemId;

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
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
