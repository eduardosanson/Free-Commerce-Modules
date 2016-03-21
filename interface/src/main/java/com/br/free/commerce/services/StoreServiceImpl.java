package com.br.free.commerce.services;

import com.br.free.commerce.services.Interface.StoreService;
import com.br.free.commerce.to.StoreForm;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.UserLogin;
import com.sun.jndi.toolkit.url.Uri;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
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

    public StoreServiceImpl() {
        this.template = new RestTemplate();
    }

    @Override
    public UserLogin cadastrar(StoreForm storeForm) {
        logger.info("inicio de cadastro de cliente: " + storeForm.getEmail());
        String requestUrl;
        Map<String, StoreForm> map = new HashMap<String, StoreForm>();

        UserLogin user= null;
        try{
            requestUrl = url+ip+port+service;
            logger.info("Chamando a url: " + requestUrl);
            map.put("storeForm",storeForm);

            user = template.postForObject(requestUrl,storeForm,UserLogin.class,map);

        }catch (Exception e){

            e.printStackTrace();
            logger.info("erro na comunicação");

        }

        return user;
    }
}
