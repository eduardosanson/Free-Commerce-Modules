package com.free.commerce.repository;

import com.free.commerce.entity.Cliente;
import com.free.commerce.entity.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by pc on 02/05/2016.
 */
public interface PedidoRepository extends CrudRepository<Pedido,Long> {

    @Query("select p from Pedido p where p.cliente.id = :id")
    List<Pedido> buscarPedidosPorCliente(@Param("id") Long id);
}
