package com.free.commerce.repository;

import com.free.commerce.entity.Categoria;
import org.springframework.context.Lifecycle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by pc on 03/04/2016.
 */
public interface CategoriaRepository extends CrudRepository<Categoria,Long> {

    @Query("select c from Categoria c where c.nome = :#{#categoriaNome} ")
    Categoria buscarPeloNome(@Param("categoriaNome") String categoriaNome);

    @Query("select c from Categoria c where c.principal = true ")
    List<Categoria> buscarPrincipais();
}
