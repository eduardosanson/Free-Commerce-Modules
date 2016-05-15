package com.br.free.commerce.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pc on 27/03/2016.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "produto")
public class ProdutoSettings {

    private String service;

    private String version;

    private String context;

    private String cadastrar;

    private String buscarProdutoPorId;

    private String buscarProdutosPorLoja;

    private String buscarPorNomeParecido;

    private String salvarCarrinhoDeCompras;

    private String buscarCarrinhoDeCompras;


    @Override
    public String toString() {
        return "ProdutoSettings{" +
                "service='" + service + '\'' +
                ", version='" + version + '\'' +
                ", context='" + context + '\'' +
                ", cadastrar='" + cadastrar + '\'' +
                ", buscarProdutoPorId='" + buscarProdutoPorId + '\'' +
                ", buscarProdutosPorLoja='" + buscarProdutosPorLoja + '\'' +
                ", buscarPorNomeParecido='" + buscarPorNomeParecido + '\'' +
                ", salvarCarrinhoDeCompras='" + salvarCarrinhoDeCompras + '\'' +
                ", buscarCarrinhoDeCompras='" + buscarCarrinhoDeCompras + '\'' +
                '}';
    }

    public String getSalvarCarrinhoDeCompras() {
        return salvarCarrinhoDeCompras;
    }

    public void setSalvarCarrinhoDeCompras(String salvarCarrinhoDeCompras) {
        this.salvarCarrinhoDeCompras = salvarCarrinhoDeCompras;
    }

    public String getBuscarCarrinhoDeCompras() {
        return buscarCarrinhoDeCompras;
    }

    public void setBuscarCarrinhoDeCompras(String buscarCarrinhoDeCompras) {
        this.buscarCarrinhoDeCompras = buscarCarrinhoDeCompras;
    }

    public String getBuscarPorNomeParecido() {
        return buscarPorNomeParecido;
    }

    public void setBuscarPorNomeParecido(String buscarPorNomeParecido) {
        this.buscarPorNomeParecido = buscarPorNomeParecido;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCadastrar() {
        return cadastrar;
    }

    public void setCadastrar(String cadastrar) {
        this.cadastrar = cadastrar;
    }

    public String getBuscarProdutoPorId() {
        return buscarProdutoPorId;
    }

    public void setBuscarProdutoPorId(String buscarProdutoPorId) {
        this.buscarProdutoPorId = buscarProdutoPorId;
    }

    public String getBuscarProdutosPorLoja() {
        return buscarProdutosPorLoja;
    }

    public void setBuscarProdutosPorLoja(String buscarProdutosPorLoja) {
        this.buscarProdutosPorLoja = buscarProdutosPorLoja;
    }

    public String getUrlProduto(){
        return service+version+context;
    }

    public String buscarProdutoPorId(String id){
        return getUrlProduto()+buscarProdutoPorId+id;
    }

    public String buscarProdutosPorLojaPaginandoOReTornoUrl(String lojaId,String pagina,String qtdItemPorPagina){
        return String.format(getUrlProduto()+buscarProdutosPorLoja,lojaId,pagina,qtdItemPorPagina);
    }

    public String buscarProdutosPorNomeParecido(String pagina,String qtdItemPorPagina,String nome){
        return String.format(getUrlProduto()+buscarPorNomeParecido, pagina,qtdItemPorPagina,nome);
    }

    public String cadastrarProduto(String lojaId){
        return getUrlProduto()+cadastrar + lojaId;
    }

    public String salvarCarrinhoDeCompras(){
        return getUrlProduto() + salvarCarrinhoDeCompras;
    }

    public String buscarCarrinhoDeCompras(String cookieId){
        return String.format(getUrlProduto() + buscarCarrinhoDeCompras,cookieId);
    }


}
