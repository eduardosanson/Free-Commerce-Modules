package com.free.commerce.service;

import com.free.commerce.entity.AutorizacaoLoja;
import com.free.commerce.entity.Enums.AutorizacaoStatus;
import com.free.commerce.entity.Loja;
import com.free.commerce.repository.AutorizacaoLojaRepository;
import com.free.commerce.repository.LojaRepository;
import com.free.commerce.service.interfaces.AutorizacaoService;
import com.free.commerce.service.interfaces.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by pc on 04/04/2016.
 */
@Service
public class AutorizacaoServiceImpl implements AutorizacaoService {

    @Autowired
    private AutorizacaoLojaRepository autorizacaoLojaRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private LojaService lojaService;

    @Override
    public AutorizacaoLoja solicitarAutorizacao(String lojaId) {
        Loja loja = lojaRepository.findOne(Long.parseLong(lojaId));
        AutorizacaoLoja autorizacao = new AutorizacaoLoja();
        autorizacao.setLoja(loja);
        autorizacao.setStatus(AutorizacaoStatus.PENDENTE_AUTORIZACAO);
        autorizacao.setRegistrado(new Date());

        return autorizacaoLojaRepository.save(autorizacao);
    }

    @Override
    public AutorizacaoLoja atualizarAutorizacao(AutorizacaoStatus status,String lojaId) {
        Loja loja = lojaService.recuperarPorId(Long.parseLong(lojaId));
        AutorizacaoLoja autorizacao = autorizacaoLojaRepository.recuperarAutorizacaoPorLoja(loja);
        autorizacao.setStatus(status);
        if (status==AutorizacaoStatus.AUTORIZADO){
            autorizacao.setDataAutorizacao(new Date());
        }
        return autorizacaoLojaRepository.save(autorizacao);
    }

    @Override
    public AutorizacaoLoja buscarAutorizacaoLoja(String lojaId) {
        return null;
    }

    @Override
    public List<Loja> buscarLojasPendentesDeAutorizacao() {
        return autorizacaoLojaRepository.recuperarLojasPendentesDeAutorizacao();
    }
}
