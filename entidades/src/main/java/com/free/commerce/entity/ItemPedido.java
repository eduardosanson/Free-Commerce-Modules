package com.free.commerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.free.commerce.entity.Enums.ItemPedidoStatus;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pc on 02/05/2016.
 */
@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Produto produto;

    @Column
    @Enumerated(EnumType.STRING)
    private ItemPedidoStatus status;

    private Integer quantidade;

    private Date registrado;

    @OneToOne
    @JsonIgnore
    private Pedido pedido;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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

    public ItemPedidoStatus getStatus() {
        return status;
    }

    public void setStatus(ItemPedidoStatus status) {
        this.status = status;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Date getRegistrado() {
        return registrado;
    }

    public void setRegistrado(Date registrado) {
        this.registrado = registrado;
    }
}
