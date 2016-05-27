package com.br.free.commerce.bean;

import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.to.Frete;
import com.free.commerce.entity.Produto;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by pc on 25/04/2016.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Carrinho {

    private Map<Produto,Integer> conteudo = new HashMap<Produto,Integer>();

    private ProdutoService produtoService;

    private Frete frete = new Frete();

    public void addProduto(Produto produto,Integer quantidade){
        if(conteudo.containsKey(produto)){
            conteudo.put(produto,conteudo.get(produto)+quantidade);

        }else {
            conteudo.put(produto,quantidade);
        }

    }

    public Frete getFrete() {
        return frete;
    }

    public void setFrete(Frete frete) {
        this.frete = frete;
    }

    public Set<Produto> getProdutos(){
        return conteudo.keySet();
    }

    public Map<Produto,Integer> getConteudo(){
        return conteudo;
    }

    public void removerProduto(Produto produto){
        conteudo.remove(produto);
    }

    public void diminuirQuantidadeProduto(Produto produto){

        if(conteudo.containsKey(produto)){
            int quantidade = conteudo.get(produto);

            if (quantidade == 1){
                this.removerProduto(produto);
            }else {
                conteudo.put(produto,quantidade-1);
            }
        }
    }

    public void somarQuantidadeProduto(Produto produto){

        if(conteudo.containsKey(produto)){
            int quantidade = conteudo.get(produto);

            this.addProduto(produto,1);
        }
    }

    public void limparCarrinho(){
        conteudo.clear();
    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "conteudo=" + conteudo.toString() +
                '}';
    }

    public double getCustoTotal ()  {
        double custoTotal =  0 ;
        for  ( Produto produto : conteudo.keySet ())  {
            custoTotal += produto.getPreco() * conteudo.get(produto);
        }
        return custoTotal;
    }

    public Integer totalNoCarrinho(){
        Integer total=0;
        for  ( Produto produto : conteudo.keySet ())  {
                total =total+ conteudo.get(produto);
        }

        return total;
    }


}
