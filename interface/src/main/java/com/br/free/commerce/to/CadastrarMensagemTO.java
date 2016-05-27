package com.br.free.commerce.to;

import com.free.commerce.entity.Mensagem;

/**
 * Created by pc on 25/05/2016.
 */
public class CadastrarMensagemTO {

    private Long produtoId;

    private Mensagem mensagem;

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }
}
