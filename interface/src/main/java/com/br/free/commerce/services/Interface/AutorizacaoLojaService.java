package com.br.free.commerce.services.Interface;

import com.free.commerce.entity.AutorizacaoLoja;
import com.free.commerce.entity.Loja;

import java.util.List;

/**
 * Created by pc on 04/04/2016.
 */
public interface AutorizacaoLojaService {

    List<Loja> buscarLojasPendentes();

    void cancelarSolicitacao(String lojaId);

    void autorizarSolicitacao(String lojaId);

    List<AutorizacaoLoja> buscarautorizacao(int pagina,int limite);

    List<AutorizacaoLoja> buscarautorizacaoPorStatus(String status,int pagina,int limite);

    List<AutorizacaoLoja> buscarautorizacaoPorStatusENome(String status, String nome,int pagina,int limite);
}
