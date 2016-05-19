package com.free.commerce.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.OrderBy;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Eduardo on 20/03/2016.
 */
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricaoTetcnica;

    private String descricao;

    private Double preco;

    private String identificadorDoProduto;

    @OneToOne
    private Loja loja;

    private Date registrado;

    @OneToMany(targetEntity = Imagem.class,cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Imagem> imagens = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private Imagem imagemPrincipal;

    @OneToOne
    private Categoria categoria;

    private boolean novo;

    private Integer quantidade;

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricaoTetcnica='" + descricaoTetcnica + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", identificadorDoProduto='" + identificadorDoProduto + '\'' +
                ", loja=" + loja +
                ", registrado=" + registrado +
                ", imagens=" + imagens +
                ", imagemPrincipal=" + imagemPrincipal +
                ", categoria=" + categoria +
                ", novo=" + novo +
                ", quantidade=" + quantidade +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Produto){
            Produto p = (Produto) obj;

            if (p.getId()!=null && p.getId()==this.id){
                return true;
            }else {
                return false;
            }

        }else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(String.valueOf(id));
    }

    public boolean isNovo() {
        return novo;
    }

    public void setNovo(boolean novo) {
        this.novo = novo;
    }

    public Imagem getImagemPrincipal() {
        return imagemPrincipal;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setImagemPrincipal(Imagem imagemPrincipal) {
        this.imagemPrincipal = imagemPrincipal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getIdentificadorDoProduto() {
        return identificadorDoProduto;
    }

    public void setIdentificadorDoProduto(String identificadorDoProduto) {
        this.identificadorDoProduto = identificadorDoProduto;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public Date getRegistrado() {
        return registrado;
    }

    public void setRegistrado(Date registrado) {
        this.registrado = registrado;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
