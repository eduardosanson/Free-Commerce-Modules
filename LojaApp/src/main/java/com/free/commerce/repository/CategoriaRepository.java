package com.free.commerce.repository;

import com.free.commerce.entity.Categoria;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by pc on 24/05/2016.
 */
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

    List<Categoria> findByDescricaoLikeIgnoreCase(String dscricao);
}
