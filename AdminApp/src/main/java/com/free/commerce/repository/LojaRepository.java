package com.free.commerce.repository;

import com.free.commerce.entity.Loja;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by pc on 20/06/2016.
 */
public interface LojaRepository extends CrudRepository<Loja,Long>{

    long count();

}
