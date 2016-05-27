package com.free.commerce.repository;

import com.free.commerce.entity.Mensagem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by pc on 24/05/2016.
 */
public interface MensagemRepositroy extends CrudRepository<Mensagem,Long>{

    @Query("select m from Mensagem m where m.produto.id = :#{#produtoId} order by m.Reistrado desc")
    List<Mensagem> buscarPorProduto(@Param("produtoId") Long produtoId);

    @Query("select m from Mensagem m where m.produto.id = :#{#produtoId} order by m.Reistrado desc")
    List<Mensagem> buscarPorProdutoPAgeando(@Param("produtoId") Long produtoId,Pageable pageable);


}
