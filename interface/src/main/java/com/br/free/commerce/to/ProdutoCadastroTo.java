package com.br.free.commerce.to;

import com.free.commerce.entity.Imagem;
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

    private Imagem imagemPrincipal;

    private List<Imagem> imagems;

    private String CategoriaId;

    private boolean novo;

    private Integer quantidade;

    public boolean isNovo() {
        return novo;
    }

    public void setNovo(boolean novo) {
        this.novo = novo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoriaId() {
        return CategoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        CategoriaId = categoriaId;
    }

    public Imagem getImagemPrincipal() {
        return imagemPrincipal;
    }

    public void setImagemPrincipal(Imagem imagemPrincipal) {
        this.imagemPrincipal = imagemPrincipal;
    }

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

    public List<Imagem> getImagems() {
        return imagems;
    }

    public void setImagems(List<Imagem> imagems) {
        this.imagems = imagems;
    }
}
