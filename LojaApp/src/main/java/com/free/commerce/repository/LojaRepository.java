package com.free.commerce.repository;

import com.free.commerce.entity.Loja;
import com.free.commerce.entity.Produto;
import com.free.commerce.entity.UserLogin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by eduardosanson on 05/03/16.
 */
public interface LojaRepository extends CrudRepository<Loja,Long> {

    @Query("select l from Loja l where l.userLogin = :#{#userLogin} ")
    Loja recuperarLojaPeloUserLogin(@Param("userLogin") UserLogin login);

    @Query("select l from Produto p inner join p.loja l  where p = :#{#produto} ")
    Loja recuperarLojaPeloProduto(@Param("produto")Produto produto);


}
