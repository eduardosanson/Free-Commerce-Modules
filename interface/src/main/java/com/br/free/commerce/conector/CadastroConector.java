package com.br.free.commerce.conector;

import com.br.free.commerce.response.StoreResponse;
import com.br.free.commerce.to.CadastrarLojaTO;
import org.springframework.web.client.RestTemplate;

/**
 * Created by eduardo.sanson on 14/03/2016.
 */
public class CadastroConector {


    private static String url;

    private String host;

    private String servico;

    private String porta;

    private RestTemplate service;

    public StoreResponse cadastrar(CadastrarLojaTO form){
        service = new RestTemplate();
        StoreResponse response = new StoreResponse();

        try {
         form = service.postForObject("htt://"+host+porta+servico,form,CadastrarLojaTO.class);

         response.setForm(form);
         response.setCodigo("00");
         response.setMensagem("sucesso");

        }catch (Exception e){
            e.printStackTrace();

            response.setForm(null);
            response.setCodigo("100");
            response.setMensagem("Falha");

        }

        return null;
    }
}
