package com.free.commerce.to;

import com.free.commerce.entity.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 03/04/2016.
 */
public class CadastrarTipoProdutoTO {

    private List<Cor> cores;

    private List<TamanhoLetra> tamanhoLetras;

    private List<TamanhoNumero> tamanhoNumeros;

    private List<Marca> marcas;

    private List<ClassificacaoProduto> classificacaoProdutos;

    public List<ClassificacaoProduto> getClassificacaoProdutos() {
        return classificacaoProdutos;
    }

    public void setClassificacaoProdutos(List<ClassificacaoProduto> classificacaoProdutos) {
        this.classificacaoProdutos = classificacaoProdutos;
    }

    public List<Marca> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<Marca> marcas) {
        this.marcas = marcas;
    }

    public List<Cor> getCores() {
        return cores;
    }

    public void setCores(List<Cor> cores) {
        this.cores = cores;
    }

    public List<TamanhoLetra> getTamanhoLetras() {
        return tamanhoLetras;
    }

    public void setTamanhoLetras(List<TamanhoLetra> tamanhoLetras) {
        this.tamanhoLetras = tamanhoLetras;
    }

    public List<TamanhoNumero> getTamanhoNumeros() {
        return tamanhoNumeros;
    }

    public void setTamanhoNumeros(List<TamanhoNumero> tamanhoNumeros) {
        this.tamanhoNumeros = tamanhoNumeros;
    }

}
