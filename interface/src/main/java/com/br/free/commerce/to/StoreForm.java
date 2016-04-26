package com.br.free.commerce.to;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;

/**
 * Created by eduardosanson on 13/03/16.
 */
public class StoreForm {

    @NotBlank
    @NotNull
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String password;

    @NotBlank(message = "Defina um nome para sua empresa")
    @NotNull
    private String nomeDaEmpresa;

    @NotBlank
    @NotNull
    private String telefone;

    @NotBlank(message = "Data de criação")
    private String dataDeCriacao;

    @CPF
    private String cpfOuCnpj;

    private String nomeJuridico;

    @NotBlank(message = "obrigatorio")
    @NotNull(message = "obrigatorio")
    private String cep;

    @NotNull
    @NotBlank
    private String nomeDaRua;

    @NotBlank(message = "digite o bairro")
    @NotNull(message = "digite o bairro")
    private String bairro;

    @NotBlank(message = "Obrigatorio")
    @NotNull
    private String numero;

    private String complemento;

    @NotNull(message = "obrigatotio")
    @NotBlank(message = "obrigatotio")
    private String uf;

    @NotBlank
    private  String cidade;

    @Override
    public String toString() {
        return "StoreForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nomeDaEmpresa='" + nomeDaEmpresa + '\'' +
                ", telefone='" + telefone + '\'' +
                ", dataDeCriacao='" + dataDeCriacao + '\'' +
                ", cpfOuCnpj='" + cpfOuCnpj + '\'' +
                ", nomeJuridico='" + nomeJuridico + '\'' +
                ", cep='" + cep + '\'' +
                ", nomeDaRua='" + nomeDaRua + '\'' +
                ", bairro='" + bairro + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", uf='" + uf + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNomeDaEmpresa() {
        return nomeDaEmpresa;
    }

    public void setNomeDaEmpresa(String nomeDaEmpresa) {
        this.nomeDaEmpresa = nomeDaEmpresa;
    }

    public String getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(String dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public String getNomeJuridico() {
        return nomeJuridico;
    }

    public void setNomeJuridico(String nomeJuridico) {
        this.nomeJuridico = nomeJuridico;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNomeDaRua() {
        return nomeDaRua;
    }

    public void setNomeDaRua(String nomeDaRua) {
        this.nomeDaRua = nomeDaRua;
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
