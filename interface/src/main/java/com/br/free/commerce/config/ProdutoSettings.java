package com.br.free.commerce.config;

import com.free.commerce.entity.Produto;
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

    private String categoria;

    private String nome;

    private String page;

    private String size;

    private String urlFinal;

    private String novo;

    private String orderBy;

    private String cidade;

    private String lojaId;


    @Override
    public String toString() {
        return "ProdutoSettings{" +
                "service='" + service + '\'' +
                ", version='" + version + '\'' +
                ", context='" + context + '\'' +
                ", cadastrar='" + cadastrar + '\'' +
                ", buscarProdutoPorId='" + buscarProdutoPorId + '\'' +
                ", buscarProdutosPorLoja='" + buscarProdutosPorLoja + '\'' +
                ", buscarProdutos='" + buscarPorNomeParecido + '\'' +
                ", salvarCarrinhoDeCompras='" + salvarCarrinhoDeCompras + '\'' +
                ", buscarCarrinhoDeCompras='" + buscarCarrinhoDeCompras + '\'' +
                ", categoria='" + categoria + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }

    public String getLojaId() {
        return lojaId;
    }

    public void setLojaId(String lojaId) {
        this.lojaId = lojaId;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
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

    public String getUrlFinal() {
        return urlFinal;
    }

    public void setUrlFinal(String urlFinal) {
        this.urlFinal = urlFinal;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getUrlComConexto(){
        return service+version+context;
    }

    public String buscarProdutoPorId(String id){
        return getUrlComConexto()+buscarProdutoPorId+id;
    }

    public String buscarProdutosPorLojaPaginandoOReTornoUrl(String lojaId,String pagina,String qtdItemPorPagina){
        return String.format(getUrlComConexto()+buscarProdutosPorLoja,lojaId,pagina,qtdItemPorPagina);
    }

    public String buscarProdutosPorNomeParecido(String pagina,String qtdItemPorPagina,String nome){
        return String.format(getUrlComConexto()+buscarPorNomeParecido, pagina,qtdItemPorPagina,nome);
    }

    public String cadastrarProduto(String lojaId){
        return getUrlComConexto()+cadastrar + lojaId;
    }

    public String salvarCarrinhoDeCompras(){
        return getUrlComConexto() + salvarCarrinhoDeCompras;
    }

    public String buscarCarrinhoDeCompras(String cookieId){
        return String.format(getUrlComConexto() + buscarCarrinhoDeCompras,cookieId);
    }

    public String path(){
        return service+version+context;
    }

    public ProdutoSettings buildURL(){
        urlFinal = path();
        return this;
    }

    public ProdutoSettings paramSize(String size){
        whichParam(size,this.size);
        return this;
    }

    public ProdutoSettings paramPage(String page){
        whichParam(page,this.page);
        return this;
    }

    public ProdutoSettings paramProdutoNome(String nome){
        whichParam(nome,this.nome);
        return this;
    }

    public ProdutoSettings paramCategoria(String categoria){
        whichParam(categoria,this.categoria);
        return this;
    }

    public ProdutoSettings paramCidade(String cidade){
        whichParam(cidade,this.cidade);
        return this;
    }

    public ProdutoSettings paramOrderBy(String orderBy){
        whichParam(orderBy,this.orderBy);
        return this;
    }

    public ProdutoSettings paramNovo(String novo){
        whichParam(novo,this.novo);
        return this;
    }

    public ProdutoSettings paramLojaId(String lojaId){
        whichParam(lojaId,this.lojaId);
        return this;
    }

    public String callBuildURL(){
        if (urlFinal==null){
            throw new IllegalAccessError();
        }else {
            return urlFinal;
        }
    }

    private void whichParam(String param,String internalParam) {
        if (path().equalsIgnoreCase(urlFinal)){
            urlFinal = urlFinal + "?" + internalParam + param;
        }else {
            urlFinal = urlFinal + "&" + internalParam + param;
        }
    }


}
