package com.free.commerce.repository;

import com.free.commerce.entity.Loja;
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

    @Override
    Loja findOne(Long aLong);

    @Override
    boolean exists(Long aLong);

    @Override
    Iterable<Loja> findAll();

    @Override
    Iterable<Loja> findAll(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    void delete(Long aLong);

    @Override
    void delete(Loja loja);

    @Override
    void delete(Iterable<? extends Loja> iterable);

    @Override
    void deleteAll();

    @Query("select l from Loja l where l.userLogin = :#{#userLogin} ")
    Loja recuperarLojaPeloUserLogin(@Param("userLogin") UserLogin login);
}
