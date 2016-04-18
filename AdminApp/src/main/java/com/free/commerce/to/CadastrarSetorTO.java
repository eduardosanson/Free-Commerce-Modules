package com.free.commerce.to;

import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * Created by pc on 03/04/2016.
 */
public class CadastrarSetorTO {

    @NotBlank
    private String setorNome;

    public String getSetorNome() {
        return setorNome;
    }

    public void setSetorNome(String setorNome) {
        this.setorNome = setorNome;
    }
}
