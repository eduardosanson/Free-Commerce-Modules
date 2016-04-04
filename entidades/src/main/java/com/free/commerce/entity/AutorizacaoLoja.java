package com.free.commerce.entity;

import com.free.commerce.entity.Enums.AutorizacaoStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pc on 04/04/2016.
 */
@Entity
public class AutorizacaoLoja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = Loja.class)
    private Loja loja;

    @Column
    @Enumerated(EnumType.STRING)
    private AutorizacaoStatus status;

    @Column(nullable = false)
    private Date registrado;

    private Date dataAutorizacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public AutorizacaoStatus getStatus() {
        return status;
    }

    public void setStatus(AutorizacaoStatus status) {
        this.status = status;
    }

    public Date getRegistrado() {
        return registrado;
    }

    public void setRegistrado(Date registrado) {
        this.registrado = registrado;
    }

    public Date getDataAutorizacao() {
        return dataAutorizacao;
    }

    public void setDataAutorizacao(Date dataAutorizacao) {
        this.dataAutorizacao = dataAutorizacao;
    }
}
