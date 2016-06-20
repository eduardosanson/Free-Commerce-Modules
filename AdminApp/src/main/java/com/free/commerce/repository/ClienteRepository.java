package com.free.commerce.repository;

import com.free.commerce.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by pc on 20/06/2016.
 */
public interface ClienteRepository extends CrudRepository<Cliente,Long>{


    long count();
}
