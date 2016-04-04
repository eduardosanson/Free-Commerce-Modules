package com.free.commerce.to;

import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * Created by pc on 03/04/2016.
 */
public class CadastrarSetorTO {

    @NotBlank
    private String setorNome;

    private String categoriaNome;

    private List<String> classificacaoProdutoNome;

    private List<String> coresId;

    private List<String> marcasId;

    private List<String> tamanhoLetraId;

    private List<String> tamanhoNumeroId;

    public List<String> getClassificacaoProdutoNome() {
        return classificacaoProdutoNome;
    }

    public void setClassificacaoProdutoNome(List<String> classificacaoProdutoNome) {
        this.classificacaoProdutoNome = classificacaoProdutoNome;
    }

    public List<String> getCoresId() {
        return coresId;
    }

    public void setCoresId(List<String> coresId) {
        this.coresId = coresId;
    }

    public List<String> getMarcasId() {
        return marcasId;
    }

    public void setMarcasId(List<String> marcasId) {
        this.marcasId = marcasId;
    }

    public List<String> getTamanhoLetraId() {
        return tamanhoLetraId;
    }

    public void setTamanhoLetraId(List<String> tamanhoLetraId) {
        this.tamanhoLetraId = tamanhoLetraId;
    }

    public List<String> getTamanhoNumeroId() {
        return tamanhoNumeroId;
    }

    public void setTamanhoNumeroId(List<String> tamanhoNumeroId) {
        this.tamanhoNumeroId = tamanhoNumeroId;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }

    public String getSetorNome() {
        return setorNome;
    }

    public void setSetorNome(String setorNome) {
        this.setorNome = setorNome;
    }
}
