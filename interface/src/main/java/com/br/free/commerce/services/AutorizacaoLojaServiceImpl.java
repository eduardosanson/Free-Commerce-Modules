package com.br.free.commerce.services;

import com.br.free.commerce.services.Interface.AutorizacaoLojaService;
import com.free.commerce.entity.Loja;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 04/04/2016.
 */
@Service
public class AutorizacaoLojaServiceImpl implements AutorizacaoLojaService {

    private RestTemplate restService = new RestTemplate();

    @Override
    public List<Loja> buscarLojasPendentes() {
        String url = "http://localhost:8090/v1/loja/pendente";
        List<Loja> lojas=null;

        try{
            lojas = restService.getForObject(url, ArrayList.class);

        }catch (HttpClientErrorException httpError){

            if (httpError.getMessage().contains("404")){
                return null;
            }

        }
        return lojas;
    }

    @Override
    public void cancelarSolicitacao(String lojaId) {
        String url = "http://localhost:8090/v1/cancelar?lojaId="+lojaId;

        restService.getForObject(url,Object.class);
    }

    @Override
    public void autorizarSolicitacao(String lojaId) {
        String url = "http://localhost:8090/v1/autorizar?lojaId="+lojaId;

        Object object = restService.getForObject(url,Object.class);

    }
}
