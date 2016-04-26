package com.free.commerce.to;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by pc on 11/04/2016.
 */
public class CategoriaTO {

    @NotBlank
    @NotNull
    private String nome;

    private String categoriaPaiId;

    private boolean principal;

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public String getCategoriaPaiId() {
        return categoriaPaiId;
    }

    public void setCategoriaPaiId(String categoriaPaiId) {
        this.categoriaPaiId = categoriaPaiId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
