package com.free.commerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by eduardosanson on 05/03/16.
 */

@Entity
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    private String nomeEmpresa;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    private UserLogin userLogin;

    @Column(nullable = false,unique = true)
    private String cnpjOuCpf;

    @Column(nullable = false)
    private String telefone;

    @Column
    private String nomeJuridico;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private Endereco endereco;

    @OneToMany(mappedBy = "loja")
    @JsonIgnore
    private List<Produto> produtos;

    @OneToOne
    private Imagem perfil;

    @Column
    @Type(type="timestamp")
    private Date registrado;

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Imagem getPerfil() {
        return perfil;
    }

    public void setPerfil(Imagem perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Loja{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", nomeEmpresa='" + nomeEmpresa + '\'' +
                ", userLogin=" + userLogin +
                ", cnpjOuCpf='" + cnpjOuCpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", nomeJuridico='" + nomeJuridico + '\'' +
                ", endereco=" + endereco +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCnpjOuCpf() {
        return cnpjOuCpf;
    }

    public void setCnpjOuCpf(String cnpjOuCpf) {
        this.cnpjOuCpf = cnpjOuCpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeJuridico() {
        return nomeJuridico;
    }

    public void setNomeJuridico(String nomeJuridico) {
        this.nomeJuridico = nomeJuridico;
    }

    public Date getRegistrado() {
        return registrado;
    }

    public void setRegistrado(Date registrado) {
        this.registrado = registrado;
    }
}
