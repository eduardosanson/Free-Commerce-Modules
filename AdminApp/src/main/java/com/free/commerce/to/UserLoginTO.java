package com.free.commerce.to;

import com.free.commerce.entity.Enums.Administrador;

/**
 * Created by pc on 11/04/2016.
 */
public class UserLoginTO {

    private String login;

    private String senha;

    private Administrador administrador;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
}
