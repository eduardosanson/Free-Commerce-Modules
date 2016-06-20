package com.free.commerce.repository;

import com.free.commerce.entity.Endereco;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by pc on 20/06/2016.
 */
public interface EnderecoRepository extends CrudRepository<Endereco,Long>{

    @Query(value = "select count(distinct(e.cidade)) from Loja l\n" +
            "inner join Endereco e on e.id = l.endereco_id" , nativeQuery = true)
    Integer countCidades();
}
