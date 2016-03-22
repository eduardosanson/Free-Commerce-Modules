package com.br.free.commerce.to;

import com.free.commerce.entity.Foto;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * Created by pc on 22/03/2016.
 */
public class ProdutoCadastroTo {

    @NotBlank
    private String nome;

    private String descricaoTetcnica;

    private String descricao;

    @NotBlank
    private Double preco;

    private List<Foto> fotos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricaoTetcnica() {
        return descricaoTetcnica;
    }

    public void setDescricaoTetcnica(String descricaoTetcnica) {
        this.descricaoTetcnica = descricaoTetcnica;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }
}
