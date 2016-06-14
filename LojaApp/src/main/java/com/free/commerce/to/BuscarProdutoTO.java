package com.free.commerce.to;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 12/06/2016.
 */
public class BuscarProdutoTO {

    private String cidade;
    private String categoria;
    private String nome;
    private String novo;
    private String orderBy;
    private List<String> categorias = new ArrayList<>();

    public String getNovo() {
        return novo;
    }

    public boolean isNovo(){
        if ("true".equalsIgnoreCase(novo)){
            return true;
        }else{
            return false;
        }
    }

    public void setNovo(String novo) {
        if (isNull(novo)){
            this.novo = iniciarVariavel();
        } else {
            this.novo = novo;
        }
    }


    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        if (isNull(cidade)){
            this.cidade = iniciarVariavel();
        }else {
            this.cidade=cidade;
        }
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        if (isNull(categoria)){
            this.categoria = iniciarVariavel();
        }else if (!"".equalsIgnoreCase(categoria)){
            this.categoria = "%"+categoria+"%";
        }else {
            this.categoria = categoria;
        }
    }

    private String iniciarVariavel() {
        return "";
    }

    private boolean isNull(String value) {
        return "null".equalsIgnoreCase(value) || value == null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (isNull(nome)){
            this.nome = "%%";
        } else {
            this.nome = "%"+nome+"%";
        }
    }


    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        if (isNull(orderBy)){
            this.orderBy = iniciarVariavel();
        }else {
            this.orderBy = orderBy;
        }
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }
}
