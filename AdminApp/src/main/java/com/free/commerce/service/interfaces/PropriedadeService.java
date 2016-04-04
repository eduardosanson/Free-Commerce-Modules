package com.free.commerce.service.interfaces;

import com.free.commerce.entity.Cor;
import com.free.commerce.entity.Marca;
import com.free.commerce.entity.TamanhoLetra;
import com.free.commerce.entity.TamanhoNumero;

import java.util.List;

/**
 * Created by pc on 04/04/2016.
 */
public interface PropriedadeService {

    List<Cor> buscarCoresPorIds(List<String> ids);

    List<Marca> buscarMarcasPorIds(List<String> ids);

    List<TamanhoLetra> buscarTamanhoLetrasPorIds(List<String> ids);

    List<TamanhoNumero> buscarTamanhoNumerosPorIds(List<String> ids);

    List<Cor> buscarTodasCores();

    List<Marca> buscarTodasMarcas();

    List<TamanhoLetra> buscarTodosTamanhoLetras();

    List<TamanhoNumero> buscarTodosTamanhoNumeros();
}
