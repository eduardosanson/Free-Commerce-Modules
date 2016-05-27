package com.free.commerce.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.OrderBy;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.List;

/**
 * Created by pc on 03/04/2016.
 */
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @OrderBy(clause = "descricao desc")
    private String descricao;

    @OneToMany(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_pai",updatable = false)
    @OrderBy(clause = "descricao asc")
    @JsonBackReference
    private List<Categoria> filhos;

    @ManyToOne
    @JoinColumn(name = "categoria_pai",nullable = true)
    @org.hibernate.annotations.ForeignKey(name = "fk_categoria_categoria")
    private Categoria pai;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Categoria> getFilhos() {
        return filhos;
    }

    public void setFilhos(List<Categoria> filhos) {
        this.filhos = filhos;
    }

    public Categoria getPai() {
        return pai;
    }

    public void setPai(Categoria pai) {
        this.pai = pai;
    }
}
