package com.br.free.commerce.to;

import com.free.commerce.entity.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 26/03/2016.
 */
public class ProdutoPage {

    private Iterable<Produto> produtos;

    private int totalElements;

    private int totalPages;

    private int numberOfElements;

    @Override
    public String toString() {
        return "ProdutoPage{" +
                "produtos=" + produtos +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                '}';
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Iterable<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Iterable<Produto> produtos) {
        this.produtos = produtos;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public List<Integer> getTotalPages() {
        List<Integer> valor = new ArrayList<Integer>();
        for (int i = 1; i<=this.totalPages;i++){
            valor.add(i);
        }

        return valor;
    }

    public int getqtdPages(){
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
