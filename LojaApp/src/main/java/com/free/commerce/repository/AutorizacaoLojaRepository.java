package com.free.commerce.repository;

import com.free.commerce.entity.AutorizacaoLoja;
import com.free.commerce.entity.Enums.AutorizacaoStatus;
import com.free.commerce.entity.Loja;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by pc on 04/04/2016.
 */
public interface AutorizacaoLojaRepository extends CrudRepository<AutorizacaoLoja,Long> {

    @Query("select l from AutorizacaoLoja a join a.loja l where a.status = " +"'PENDENTE_AUTORIZACAO'")
    List<Loja> recuperarLojasPendentesDeAutorizacao();

    @Query("select a from AutorizacaoLoja a  where a.loja= :#{#loja} ")
    AutorizacaoLoja recuperarAutorizacaoPorLoja(@Param("loja") Loja loja);



}
