package com.free.commerce.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by pc on 03/04/2016.
 */
@Entity
public class ProdutoPropriedade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Produto produto;

    @OneToOne
    private Cor cor;

    @OneToOne
    private TamanhoNumero tamanhoNumero;

    @OneToOne
    private TamanhoLetra tamanhoLetra;

    @OneToOne
    private Marca marca;

    private Integer quantidade;

    @OneToOne
    private Categoria categoria;

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public TamanhoNumero getTamanhoNumero() {
        return tamanhoNumero;
    }

    public void setTamanhoNumero(TamanhoNumero tamanhoNumero) {
        this.tamanhoNumero = tamanhoNumero;
    }

    public TamanhoLetra getTamanhoLetra() {
        return tamanhoLetra;
    }

    public void setTamanhoLetra(TamanhoLetra tamanhoLetra) {
        this.tamanhoLetra = tamanhoLetra;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
