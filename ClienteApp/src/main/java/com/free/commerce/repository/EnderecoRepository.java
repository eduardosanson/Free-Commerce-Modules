package com.free.commerce.repository;

import com.free.commerce.entity.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

/**
 * Created by eduardosanson on 05/03/16.
 */
public interface EnderecoRepository extends CrudRepository<Endereco,Long> {
}
