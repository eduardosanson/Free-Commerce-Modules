package com.free.commerce.repository;

import com.free.commerce.entity.Loja;
import com.free.commerce.entity.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by eduardo.sanson on 21/03/2016.
 */
public interface ProductRepository extends CrudRepository<Produto, Long> {

//    @Query("select p from produto p where p.loja = :#{#loja}")
//    Produto buscarProdutoPorLoja(@Param("loja") Loja loja);

}
