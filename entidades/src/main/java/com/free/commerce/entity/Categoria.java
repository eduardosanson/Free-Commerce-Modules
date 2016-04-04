package com.free.commerce.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by pc on 03/04/2016.
 */
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    @OneToMany
    private List<TipoProduto> tipoProdutos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TipoProduto> getTipoProdutos() {
        return tipoProdutos;
    }

    public void setTipoProdutos(List<TipoProduto> tipoProdutos) {
        this.tipoProdutos = tipoProdutos;
    }
}
