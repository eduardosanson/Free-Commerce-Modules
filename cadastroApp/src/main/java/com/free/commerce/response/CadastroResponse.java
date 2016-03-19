package com.free.commerce.response;

import com.free.commerce.entity.Loja;

/**
 * Created by eduardosanson on 15/03/16.
 */
public class CadastroResponse {
    private Loja loja;

    private String codigo;

    private String mensagem;

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
