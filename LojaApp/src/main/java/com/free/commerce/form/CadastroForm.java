package com.free.commerce.form;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by eduardosanson on 05/03/16.
 */
public class CadastroForm {


    @NotBlank
    @Size(min=3 ,max=50)
    private String nome;

    @NotBlank
    private String sobreNome;

    @NotBlank
    private String cpfOuCnpj;

    private String telefone;

    @NotBlank
    private String cep;

    @NotBlank
    private String rua;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank
    private String pais;

    @NotBlank
    private String numero;

    @NotBlank
    private String complemento;


    private String nomeFantasia;

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    private boolean isCliente;

    @Override
    public String toString() {
        return "CadastroForm{" +
                "nome='" + nome + '\'' +
                ", sobreNome='" + sobreNome + '\'' +
                ", cpfOuCnpj='" + cpfOuCnpj + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", pais='" + pais + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", isCliente=" + isCliente +
                '}';
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

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpf(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
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

    public boolean isCliente() {
        return isCliente;
    }

    public void setCliente(boolean cliente) {
        isCliente = cliente;
    }
}
