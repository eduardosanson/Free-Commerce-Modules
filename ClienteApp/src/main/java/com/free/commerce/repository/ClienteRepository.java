package com.free.commerce.repository;

import com.free.commerce.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by eduardosanson on 05/03/16.
 */
public interface ClienteRepository extends CrudRepository<Cliente,Long> {

    Cliente findByCpf(String cpf);



}
