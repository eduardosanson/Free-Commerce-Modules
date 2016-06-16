package com.br.free.commerce.services;

import com.br.free.commerce.services.Interface.AutorizacaoLojaService;
import com.free.commerce.entity.AutorizacaoLoja;
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
//    autorizacao

    @Override
    public List<Loja> buscarLojasPendentes() {
        String url = "http://lojacommerce.herokuapp.com/loja/pendente";
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
        String url = "http://lojacommerce.herokuapp.com/cancelar?lojaId="+lojaId;

        restService.getForObject(url,Object.class);
    }

    @Override
    public void autorizarSolicitacao(String lojaId) {
        String url = "http://lojacommerce.herokuapp.com/autorizar?lojaId="+lojaId;

        Object object = restService.getForObject(url,Object.class);

    }

    @Override
    public List<AutorizacaoLoja> buscarautorizacao(int pagina,int limite) {
        String url = "http://lojacommerce.herokuapp.com/autorizacao?pagina="+pagina+"&limite="+limite;
        List<AutorizacaoLoja> autorizacoes=null;

        try{
            autorizacoes = restService.getForObject(url, ArrayList.class);

        }catch (HttpClientErrorException httpError){

            if (httpError.getMessage().contains("404")){
                return null;
            }

        }
        return autorizacoes;
    }

    @Override
    public List<AutorizacaoLoja> buscarautorizacaoPorStatus(String status,int pagina,int limite) {
        String url = "http://lojacommerce.herokuapp.com/autorizacao?status="+status +"&pagina="+pagina+"&limite="+limite;
        List<AutorizacaoLoja> autorizacoes=null;

        try{
            autorizacoes = restService.getForObject(url, ArrayList.class);

        }catch (HttpClientErrorException httpError){

            if (httpError.getMessage().contains("404")){
                return null;
            }

        }
        return autorizacoes;
    }

    @Override
    public List<AutorizacaoLoja> buscarautorizacaoPorStatusENome(String status, String nome,int pagina,int limite) {
        String url = "http://lojacommerce.herokuapp.com/autorizacao?status="+status+"&nome="+nome + "&pagina="+pagina+"&limite="+limite;
        List<AutorizacaoLoja> autorizacoes=null;

        try{
            autorizacoes = restService.getForObject(url, ArrayList.class);

        }catch (HttpClientErrorException httpError){

            if (httpError.getMessage().contains("404")){
                return null;
            }

        }
        return autorizacoes;
    }
}
