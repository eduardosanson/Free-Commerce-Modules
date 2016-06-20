package com.free.commerce.service;

import com.free.commerce.repository.ClienteRepository;
import com.free.commerce.repository.EnderecoRepository;
import com.free.commerce.repository.LojaRepository;
import com.free.commerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pc on 20/06/2016.
 */
@Service
public class StaticServices {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private LojaRepository lojaRepository;


    public long lugares(){
        return enderecoRepository.countCidades();
    }

    public long vendedores(){
        return lojaRepository.count();
    }

    public long clientes(){
        return clienteRepository.count();
    }

    public long items(){
        return produtoRepository.count();
    }
}
