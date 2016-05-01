package com.free.commerce.entity;

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

    @OneToOne(mappedBy ="userLogin",cascade = CascadeType.PERSIST)
    private Loja loja;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Administrador administrador;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Cliente cliente;

    @Override
    public String toString() {
        return "UserLogin{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
