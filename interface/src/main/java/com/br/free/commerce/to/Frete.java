package com.br.free.commerce.to;

import ognl.IntHashMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc on 26/05/2016.
 */
public class Frete {

    private String valor;

    private Long produtoId;

    private Integer real;

    private Integer moeda;

    private Map<Long,Double> produtoFrete = new HashMap();

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Double getValorDouble() {
        return new Double(valor.replace(",", "."));
    }

    public Map<Long, Double> getProdutoFrete() {
        return produtoFrete;
    }

    public void setProdutoFrete(Map<Long, Double> produtoFrete) {
        this.produtoFrete = produtoFrete;
    }

    public Double valorPorProduto(Long produtoId){
        return produtoFrete.get(produtoId);
    }

    public void addFreteProduto(Long produtoId,Double valor){
        produtoFrete.put(produtoId,valor);

    }

    public Double freteTotal(){
        Double valorTotal =0.;
        for (Long l :produtoFrete.keySet()) {
            valorTotal = valorTotal + produtoFrete.get(l);
        }

        return valorTotal;
    }

    public Integer getMoeda() {
        return moeda;
    }

    public void setMoeda(Integer moeda) {
        this.moeda = moeda;
    }

    public Integer getReal() {
        return real;
    }

    public void setReal(Integer real) {
        this.real = real;
    }
}
