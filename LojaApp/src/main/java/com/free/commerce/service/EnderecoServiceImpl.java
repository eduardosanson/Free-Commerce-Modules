package com.free.commerce.service;

import com.free.commerce.entity.Endereco;
import com.free.commerce.repository.EnderecoRepository;
import com.free.commerce.repository.LojaRepository;
import com.free.commerce.service.interfaces.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by eduardosanson on 05/03/16.
 */
@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private LojaRepository lojaRepository;

    @Override
    public List<String> cidadesCadastradas() {
        return lojaRepository.recuperarCidadesDeLojasCadastradas();
    }
}
