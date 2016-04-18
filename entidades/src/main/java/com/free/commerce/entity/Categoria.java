package com.free.commerce.entity;

import org.hibernate.annotations.Type;

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

    @ManyToMany(targetEntity = Categoria.class)
    private List<Categoria> categorias;

    @Type(type = "true_false")
    @Column(name = "principal")
    private boolean principal;

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

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }
}
