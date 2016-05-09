package com.free.commerce.to;

import com.free.commerce.entity.Foto;
import com.free.commerce.entity.Loja;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import scala.util.parsing.combinator.testing.Str;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

/**
 * Created by pc on 21/03/2016.
 */
public class ProdutoTO {

    @NotBlank
    private String nome;

    private String descricaoTetcnica;

    private String descricao;

    private List<Foto> fotos;

    private Foto fotoPrincipal;

    @NotBlank
    private Double preco;

    private String categoriaId;

    private Integer quantidade;

    private boolean novo;

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
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Foto getFotoPrincipal() {
        return fotoPrincipal;
    }

    public void setFotoPrincipal(Foto fotoPrincipal) {
        this.fotoPrincipal = fotoPrincipal;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
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

}
