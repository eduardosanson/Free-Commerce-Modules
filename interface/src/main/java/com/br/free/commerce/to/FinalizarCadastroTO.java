package com.br.free.commerce.to;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;

/**
 * Created by pc on 01/05/2016.
 */
public class FinalizarCadastroTO {

    @NotBlank
    @NotNull
    @NotEmpty
    private String telefone;

    @NotBlank
    @NotNull
    @NotEmpty
    private String nome;

    @NotBlank
    @NotNull
    @NotEmpty
    private String sobreNome;

    @NotBlank
    @NotNull
    @NotEmpty
    @CPF
    private String cpf;

    @NotBlank
    @NotNull
    @NotEmpty
    private String cep;

    @NotBlank
    @NotNull
    @NotEmpty
    private String rua;

    @NotBlank
    @NotNull
    @NotEmpty
    private String bairro;

    @NotBlank
    @NotNull
    @NotEmpty
    private String numero;

    @NotBlank
    @NotNull
    @NotEmpty
    private String complemento;

    @NotBlank
    @NotNull
    @NotEmpty
    private String uf;

    @NotBlank
    @NotNull
    @NotEmpty
    private  String cidade;

    private String email;

    public FinalizarCadastroTO() {
    }

    public FinalizarCadastroTO(String telefone, String nome, String sobreNome, String cpf, String cep, String rua, String bairro, String numero, String complemento, String uf, String cidade) {
        this.telefone = telefone;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.cpf = cpf;
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
        this.uf = uf;
        this.cidade = cidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
