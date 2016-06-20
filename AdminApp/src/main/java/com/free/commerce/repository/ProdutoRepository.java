package com.free.commerce.repository;

import com.free.commerce.entity.Produto;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by pc on 20/06/2016.
 */
public interface ProdutoRepository extends CrudRepository<Produto,Long>{

    long count();
}
