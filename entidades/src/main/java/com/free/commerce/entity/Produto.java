package com.free.commerce.entity;

import javax.persistence.*;
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

    @OneToMany(targetEntity = Foto.class,cascade = CascadeType.PERSIST)
    private List<Foto> fotos;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Foto fotoPrincipal;

    @OneToOne
    private Categoria categoria;


    private boolean novo;

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
                ", fotos=" + fotos +
                ", fotoPrincipal=" + fotoPrincipal +
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

    public Foto getFotoPrincipal() {
        return fotoPrincipal;
    }

    public void setFotoPrincipal(Foto fotoPrincipal) {
        this.fotoPrincipal = fotoPrincipal;
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

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
