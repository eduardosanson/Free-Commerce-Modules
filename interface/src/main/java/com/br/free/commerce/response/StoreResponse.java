package com.br.free.commerce.response;

import com.br.free.commerce.to.StoreForm;

/**
 * Created by eduardo.sanson on 14/03/2016.
 */
public class StoreResponse {

    private StoreForm form;

    private String mensagem;

    private String codigo;

    public StoreForm getForm() {
        return form;
    }

    public void setForm(StoreForm form) {
        this.form = form;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
