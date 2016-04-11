package com.free.commerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.free.commerce.entity.Enums.Administrador;
import com.free.commerce.entity.Enums.Role;

import javax.persistence.*;

/**
 * Created by eduardosanson on 05/03/16.
 */
@Entity
@Table(name = "USER")
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String login;

    @Column
    private String senha;

    @Column
    @Enumerated(EnumType.STRING)
    private Role permissao;

    @OneToOne(mappedBy ="userLogin")
    private Loja loja;

    @OneToOne
    private Administrador administrador;

    @Override
    public String toString() {
        return "UserLogin{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Role getPermissao() {
        return permissao;
    }

    public void setPermissao(Role permissao) {
        this.permissao = permissao;
    }
}
