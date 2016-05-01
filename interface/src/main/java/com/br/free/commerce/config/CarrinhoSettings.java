package com.br.free.commerce.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pc on 27/03/2016.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "carrinho")
public class CarrinhoSettings {

    private String service;

    private String version;

    private String context;

    private String buscarCarrinhoDeCompras;


    @Override
    public String toString() {
        return "CarrinhoSettings{" +
                "service='" + service + '\'' +
                ", version='" + version + '\'' +
                ", context='" + context + '\'' +
                ", buscarCarrinhoDeCompras='" + buscarCarrinhoDeCompras + '\'' +
                '}';
    }


    public String getBuscarCarrinhoDeCompras() {
        return buscarCarrinhoDeCompras;
    }

    public void setBuscarCarrinhoDeCompras(String buscarCarrinhoDeCompras) {
        this.buscarCarrinhoDeCompras = buscarCarrinhoDeCompras;
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

    private String getUrlCompleta(){
        return service+version+context;
    }

    public String salvarCarrinhoDeCompras(){
        return getUrlCompleta();
    }

    public String buscarCarrinhoDeCompras(String cookieId){
        return String.format(getUrlCompleta() + buscarCarrinhoDeCompras,cookieId);
    }


}
