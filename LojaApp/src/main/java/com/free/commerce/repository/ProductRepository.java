package com.free.commerce.repository;

import com.free.commerce.entity.Loja;
import com.free.commerce.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by eduardo.sanson on 21/03/2016.
 */
public interface ProductRepository extends CrudRepository<Produto, Long> {

    @Query("select p from Produto p where p.loja = :#{#loja}")
    Page<Produto> buscarProdutoPorLoja(@Param("loja") Loja loja, Pageable pageable);

    @Query("select p from Produto p where p.nome LIKE CONCAT('%',:nome,'%')")
    Page<Produto> buscarProdutoParecidosPorNome(@Param("nome") String nome, Pageable pageable);



}
