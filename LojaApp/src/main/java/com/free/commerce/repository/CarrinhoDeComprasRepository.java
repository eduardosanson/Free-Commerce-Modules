package com.free.commerce.repository;

import com.free.commerce.entity.CarrinhoDeCompras;
import com.free.commerce.entity.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by pc on 27/04/2016.
 */
public interface CarrinhoDeComprasRepository extends CrudRepository<CarrinhoDeCompras,Long> {

    @Query("select c from CarrinhoDeCompras c where c.jsessionId = :#{#sessioId}")
    List<CarrinhoDeCompras> buscarCarrinhoPorCookieId(@Param("sessioId") String jsessionId);

    @Query("select c from CarrinhoDeCompras c where c.jsessionId = :#{#sessioId} and c.produto= :#{#produto}")
    CarrinhoDeCompras buscarCarrinhoPorCookieIdEProduto(@Param("sessioId") String jsessionId,@Param("produto") Produto produto);
}
