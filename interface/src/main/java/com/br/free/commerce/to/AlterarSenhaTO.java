package com.br.free.commerce.to;

/**
 * Created by pc on 11/06/2016.
 */
public class AlterarSenhaTO {

    private String senhaAtual;

    private String novaSenha;

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getSenhaAtual() {

        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }
}
