package com.br.free.commerce.services;

import com.br.free.commerce.services.Interface.StoreService;
import com.br.free.commerce.to.CadastrarLojaTO;
import com.br.free.commerce.util.ImagemProcessor;
import com.free.commerce.entity.Imagem;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.UserLogin;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc on 19/03/2016.
 */
@Service
public class StoreServiceImpl implements StoreService {

    private RestTemplate template;

    private static final Logger logger = Logger.getLogger(StoreServiceImpl.class);

    private static String url ="http://";
    private static String ip ="localhost";
    private static String port =":8090";
    private static String service="/v1/loja";

    private static String PASTA_LOJA_PERFIL="loja/perfil/";

    public StoreServiceImpl() {
        this.template = new RestTemplate();
    }

    @Override
    public UserLogin cadastrar(CadastrarLojaTO cadastrarLojaTO) {
        logger.info("inicio de cadastro de cliente: " + cadastrarLojaTO.getEmail());
        String requestUrl;
        Map<String, CadastrarLojaTO> map = new HashMap<String, CadastrarLojaTO>();

        UserLogin user= null;
        try{
            requestUrl = url+ip+port+service;
            logger.info("Chamando a url: " + requestUrl);
            map.put("cadastrarLojaTO", cadastrarLojaTO);

            user = template.postForObject(requestUrl, cadastrarLojaTO,UserLogin.class,map);

        }catch (Exception e){

            e.printStackTrace();
            logger.info("erro na comunicação");

        }

        return user;
    }

    @Override
    public void alterarPerfil(Long lojaId, MultipartFile file) {
        String url = "http://localhost:8090/v1/loja/perfil?lojaId="+lojaId;

        Imagem imagem = ImagemProcessor.processor(PASTA_LOJA_PERFIL+lojaId,"PERFIL",file);

        template.put(url,imagem);
    }

    @Override
    public Loja buscarLoja(Long id) {
        String url = "http://localhost:8090/v1/loja/"+id;
        return template.getForObject(url,Loja.class);
    }
}
