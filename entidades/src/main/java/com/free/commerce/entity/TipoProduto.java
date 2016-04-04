package com.free.commerce.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by pc on 03/04/2016.
 */
@Entity
public class TipoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<ClassificacaoProduto> classificacaoProduto;

    @OneToMany
    private List<Produto> produto;

    @OneToMany
    private List<Cor> cor;

    @OneToMany
    private List<TamanhoNumero> tamanhoNumero;

    @OneToMany
    private List<TamanhoLetra> tamanhoLetra;

    @OneToMany
    private List<Marca> marca;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ClassificacaoProduto> getClassificacaoProduto() {
        return classificacaoProduto;
    }

    public void setClassificacaoProduto(List<ClassificacaoProduto> classificacaoProduto) {
        this.classificacaoProduto = classificacaoProduto;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }

    public List<Cor> getCor() {
        return cor;
    }

    public void setCor(List<Cor> cor) {
        this.cor = cor;
    }

    public List<TamanhoNumero> getTamanhoNumero() {
        return tamanhoNumero;
    }

    public void setTamanhoNumero(List<TamanhoNumero> tamanhoNumero) {
        this.tamanhoNumero = tamanhoNumero;
    }

    public List<TamanhoLetra> getTamanhoLetra() {
        return tamanhoLetra;
    }

    public void setTamanhoLetra(List<TamanhoLetra> tamanhoLetra) {
        this.tamanhoLetra = tamanhoLetra;
    }

    public List<Marca> getMarca() {
        return marca;
    }

    public void setMarca(List<Marca> marca) {
        this.marca = marca;
    }
}
