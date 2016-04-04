package com.free.commerce.service;

import com.free.commerce.entity.Cor;
import com.free.commerce.entity.Marca;
import com.free.commerce.entity.TamanhoLetra;
import com.free.commerce.entity.TamanhoNumero;
import com.free.commerce.repository.CorRepository;
import com.free.commerce.repository.MarcaRepository;
import com.free.commerce.repository.TamanhoLetraRepository;
import com.free.commerce.repository.TamanhoNumeroRepository;
import com.free.commerce.service.interfaces.PropriedadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 04/04/2016.
 */
@Service
public class PropriedadeServiceImpl implements PropriedadeService {

    @Autowired
    private CorRepository corRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private TamanhoNumeroRepository tamanhoNumeroRepository;

    @Autowired
    private TamanhoLetraRepository tamanhoLetraRepository;

    @Override
    public List<Cor> buscarCoresPorIds(List<String> ids) {
        return (List<Cor>) corRepository.findAll(stringToLongId(ids));
    }

    @Override
    public List<Marca> buscarMarcasPorIds(List<String> ids) {
        return (List<Marca>) marcaRepository.findAll(stringToLongId(ids));
    }

    @Override
    public List<TamanhoLetra> buscarTamanhoLetrasPorIds(List<String> ids) {
        return (List<TamanhoLetra>) tamanhoLetraRepository.findAll(stringToLongId(ids));
    }

    @Override
    public List<TamanhoNumero> buscarTamanhoNumerosPorIds(List<String> ids) {
        return (List<TamanhoNumero>) tamanhoNumeroRepository.findAll(stringToLongId(ids));
    }

    @Override
    public List<Cor> buscarTodasCores() {
        return (List<Cor>) corRepository.findAll();
    }

    @Override
    public List<Marca> buscarTodasMarcas() {
        return (List<Marca>) marcaRepository.findAll();
    }

    @Override
    public List<TamanhoLetra> buscarTodosTamanhoLetras() {
        return (List<TamanhoLetra>) tamanhoLetraRepository.findAll();
    }

    @Override
    public List<TamanhoNumero> buscarTodosTamanhoNumeros() {
        return (List<TamanhoNumero>) tamanhoNumeroRepository.findAll();
    }

    private List<Long> stringToLongId(List<String> ids) {
        List<Long> longIds = new ArrayList<Long>();
        for (String id:ids) {
            longIds.add(Long.parseLong(id));

        }
        return longIds;
    }
}
