package com.br.free.commerce.to;

import com.br.free.commerce.util.MaskUtil;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by pc on 01/05/2016.
 */
public class CadastrarClienteTO {

    @Email
    @NotBlank
    private String login;

    private String confirmarEmail;

    @NotBlank
    @NotNull
    private String senha;

    private String confirmeSenha;

    private String telefone;

    @NotBlank
    private String nome;

    private String sobreNome;

    private String cpf;

    private String cep;

    private String nomeDaRua;

    private String bairro;

    private String numero;

    private String complemento;

    private String uf;

    private  String cidade;

    private boolean termos = false;

    @Override
    public String toString() {
        return "CadastrarClienteTO{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", telefone='" + telefone + '\'' +
                ", nome='" + nome + '\'' +
                ", sobreNome='" + sobreNome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", cep='" + cep + '\'' +
                ", nomeDaRua='" + nomeDaRua + '\'' +
                ", bairro='" + bairro + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", uf='" + uf + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }

    public String getConfirmeSenha() {
        return confirmeSenha;
    }

    public void setConfirmeSenha(String confirmeSenha) {
        this.confirmeSenha = confirmeSenha;
    }

    public String getConfirmarEmail() {
        return confirmarEmail;
    }

    public void setConfirmarEmail(String confirmarEmail) {
        this.confirmarEmail = confirmarEmail;
    }

    public boolean isTermos() {
        return termos;
    }

    public void setTermos(boolean termos) {
        this.termos = termos;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
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

    public String getTelefone() {
        return MaskUtil.removeTelefoneMask(telefone);
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return MaskUtil.removeCpfOuCnpjMask(cpf);
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return MaskUtil.removeCepMask(cep);
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
