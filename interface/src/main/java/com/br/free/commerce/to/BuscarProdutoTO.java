package com.br.free.commerce.to;

/**
 * Created by pc on 24/04/2016.
 */
public class BuscarProdutoTO {

    private String nome;
    private String page;
    private String size;
    private String cidade;
    private String categoria;
    private String novo;
    private String orderBy;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNovo() {
        return novo;
    }

    public void setNovo(String novo) {
        this.novo = novo;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
