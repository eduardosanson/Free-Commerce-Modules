package com.free.commerce.service.interfaces;

import com.free.commerce.entity.AutorizacaoLoja;
import com.free.commerce.entity.Enums.AutorizacaoStatus;
import com.free.commerce.entity.Loja;
import org.springframework.data.domain.Pageable;
import scala.util.parsing.combinator.testing.Str;

import java.util.List;

/**
 * Created by pc on 04/04/2016.
 */
public interface AutorizacaoService {

    AutorizacaoLoja solicitarAutorizacao(String lojaId);

    AutorizacaoLoja atualizarAutorizacao(AutorizacaoStatus status,String lojaId);

    AutorizacaoLoja buscarAutorizacaoLoja(String lojaId);

    List<Loja> buscarLojasPendentesDeAutorizacao();

    List<AutorizacaoLoja> buscarAutorizacoes(Pageable pageable);

    List<AutorizacaoLoja> buscarPorStatus(String status,Pageable pageable);

    List<AutorizacaoLoja> buscarPelosNomesDeLojasEStatus(String nome, String status, Pageable pageable);

    List<AutorizacaoLoja> buscarPelosNomesDeLojas(String nome,Pageable pageable);
}
