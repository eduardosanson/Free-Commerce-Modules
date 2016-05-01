package com.free.commerce.to;

import com.free.commerce.entity.Produto;
import org.springframework.data.domain.Sort;

/**
 * Created by pc on 26/03/2016.
 */
public class ProdutoPage {

    private Iterable<Produto> produtos;

    private int totalElements;

    private int totalPages;

    private int numberOfElements;


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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }
}
