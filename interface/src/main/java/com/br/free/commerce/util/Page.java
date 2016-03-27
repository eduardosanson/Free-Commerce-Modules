package com.br.free.commerce.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 26/03/2016.
 */
public class Page {

    private int qtdElementosPorPagina;

    private int paginaAtual;

    private int primeiroElementodaPagina;

    private int totalDePaginas;

    public void setQtdElementosPorPagina(int qtdElementosPorPagina) {
        this.qtdElementosPorPagina = qtdElementosPorPagina;
    }

    public int getQtdElementosPorPagina() {
        return qtdElementosPorPagina;
    }

    public int getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(int paginaAtual) {
        this.paginaAtual = paginaAtual;
    }

    public int getPrimeiroElementodaPagina() {
        if(this.paginaAtual==1){

            return this.qtdElementosPorPagina-qtdElementosPorPagina;
        }else {
            return (this.qtdElementosPorPagina*paginaAtual)-qtdElementosPorPagina;
        }
    }

    public int getUltimoelemento() {
        if(this.paginaAtual==1){

            return this.qtdElementosPorPagina;
        }else {
            return (this.qtdElementosPorPagina*paginaAtual);
        }
    }

    public List<Integer> getPrimeiraMetade() {

        List<Integer> valor = new ArrayList<Integer>();

        int limiteDePaginas =5;

        if (limiteDePaginas<totalDePaginas){
            if (isPrimeiraPagina()){

                for (int i =1;i<=limiteDePaginas;i++){
                    valor.add(i);
                }
            } else if (isSegundaPagina()){

                for (int i =2;i<=limiteDePaginas+1;i++){
                    valor.add(i-1);
                }

            }else {

                if (isMaiorQueOLimiteDePAgina(limiteDePaginas)){
                    int i =paginaAtual;

                    for (;i<=limiteDePaginas+paginaAtual;i++){
                        valor.add(-i);
                    }
                }else if (possuiQuantidadeDePaginas(limiteDePaginas)){

                    if (paginaAtual+(limiteDePaginas-2)<=totalDePaginas){

                        int pontoInicial =paginaAtual-1;
                        int limite = (limiteDePaginas-1) +pontoInicial;
                        for (int i=pontoInicial;i<=limite;i++){

                            valor.add(i);
                        }
                    }else {
                        int quantasFaltamParaAcabar = totalDePaginas-paginaAtual;

                        int pontoInicial = 1+ paginaAtual - (limiteDePaginas-quantasFaltamParaAcabar);
                        int limiteDeExecucao = totalDePaginas;
                        for (int i=pontoInicial;i<=limiteDeExecucao;i++){
                            valor.add(i);
                        }

                    }
                }
            }

        }else {
            int pontoInicial;


            for (int i = 1;i<totalDePaginas+1;i++){
                valor.add(i);
            }
        }


        return valor;
    }

    private boolean possuiQuantidadeDePaginas(int limiteDePaginas) {
        return totalDePaginas>=limiteDePaginas;
    }

    private boolean isMaiorQueOLimiteDePAgina(int limiteDePaginas) {
        return paginaAtual-totalDePaginas>=limiteDePaginas;
    }

    private boolean isSegundaPagina() {
        return paginaAtual==2;
    }

    private boolean isPrimeiraPagina() {
        return paginaAtual==1;
    }

    public int getTotalDePaginas() {
        return totalDePaginas;
    }

    public void setTotalDePaginas(int totalDePaginas) {
        this.totalDePaginas = totalDePaginas;
    }
}
