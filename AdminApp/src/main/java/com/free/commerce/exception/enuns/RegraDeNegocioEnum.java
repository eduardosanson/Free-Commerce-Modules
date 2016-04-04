package com.free.commerce.exception.enuns;

/**
 * Created by pc on 04/04/2016.
 */
public enum RegraDeNegocioEnum {

    VALOR_JA_CADASTRADO("5002","Valor já foi cadastrado");

    RegraDeNegocioEnum(String codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }

    private String codigo;

    private String mensagem;

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
