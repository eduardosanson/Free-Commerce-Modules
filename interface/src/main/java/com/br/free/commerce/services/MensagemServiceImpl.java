package com.br.free.commerce.services;

import com.br.free.commerce.to.ResponderMensagemTO;
import com.free.commerce.entity.Mensagem;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 25/05/2016.
 */
@Service
public class MensagemServiceImpl {

    RestTemplate restTemplate = new RestTemplate();

    public Mensagem cadastrarMensagem(Long produtoId, Mensagem mensagemRequest){
        String url = "http://localhost:8090/v1/mensagem/produto/"+produtoId;

        Mensagem mensagemResponse= restTemplate.postForObject(url,mensagemRequest,Mensagem.class);

        return mensagemResponse;
    }

    public void alterarMesagem(Mensagem mensagem){
        String url = "http://localhost:8090/v1/mensagem";

        restTemplate.put(url,mensagem);
    }

    public List<Mensagem> recuperarMensagensPorProduto(Long produtoId,int pagina,int limite){
        String url = "http://localhost:8090/v1/mensagem/produto/"+produtoId+"?pagina="+pagina+"&limite="+limite;

        List<Mensagem> mensagens =  restTemplate.getForEntity(url,ArrayList.class).getBody();

        return mensagens;
    }
    public List<Mensagem> recuperarMensagensPorProdutoList(Long produtoId){
        String url = "http://localhost:8090/v1/mensagem/produto/"+produtoId+"/list";

        List<Mensagem> mensagens =  restTemplate.getForEntity(url,ArrayList.class).getBody();

        return mensagens;
    }


    public Mensagem recuperarMensagenPorId(Long Id){
        String url = "http://localhost:8090/v1/mensagem/"+Id;

        Mensagem mensagens =  restTemplate.getForEntity(url,Mensagem.class).getBody();

        return mensagens;
    }

    public void responderMensagem(ResponderMensagemTO responderMensagemTO){
        Mensagem mensagem = this.recuperarMensagenPorId(responderMensagemTO.getMensagemId());

        mensagem.setResposta(responderMensagemTO.getResposta());
        mensagem.setRespondida(true);

        this.alterarMesagem(mensagem);
    }


}
