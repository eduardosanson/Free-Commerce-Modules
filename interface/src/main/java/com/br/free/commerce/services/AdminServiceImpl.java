package com.br.free.commerce.services;

import com.br.free.commerce.services.Interface.AdminService;
import com.br.free.commerce.to.CadastrarClienteTO;
import com.br.free.commerce.to.FinalizarCadastroTO;
import com.br.free.commerce.util.ImagemProcessor;
import com.free.commerce.entity.*;
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
public class AdminServiceImpl implements AdminService {

    private RestTemplate template;

    private static final Logger logger = Logger.getLogger(AdminServiceImpl.class);

    private static String PASTA_CLIENTE_PERFIL="admin/perfil/";

    private static String protocol ="http://";
    private static String domain ="adminappcommerce.herokuapp.com";
    private static String service="/v1/admin";

    public AdminServiceImpl() {
        this.template = new RestTemplate();
    }

    @Override
    public UserLogin cadastrarAdmin(CadastrarClienteTO cadastrarClienteTO) {
        logger.info("inicio de cadastro de cliente: " + cadastrarClienteTO.getLogin());
        String requestUrl;
        Map<String, CadastrarClienteTO> map = new HashMap<String, CadastrarClienteTO>();

        UserLogin user= null;
        try{
            requestUrl = protocol + domain +service;
            logger.info("Chamando a protocol: " + requestUrl);
            map.put("storeForm",cadastrarClienteTO);

            user = template.postForObject(requestUrl,cadastrarClienteTO,UserLogin.class,map);

        }catch (Exception e){

            e.printStackTrace();
            logger.info("erro na comunicação");

        }

        return user;
    }

    @Override
    public Administrador concluirCadastro(FinalizarCadastroTO cadastroTO) {
//        logger.info("Concluindo cadastro do cliente : " + cadastroTO.getEmail());
//        String requestUrl;
//
//        Cliente cliente= null;
//            requestUrl = "http://localhost:8085/v1/cliente/concluirCadastro";
//            logger.info("Chamando a protocol: " + requestUrl);
//
//            cliente = template.postForObject(requestUrl,cadastroTO,Cliente.class);

        return new Administrador();
    }

    @Override
    public Administrador buscarAdmin(Long id) {
        String uri = protocol + domain +service+"/"+id;

        Administrador administrador = template.getForObject(uri,Administrador.class);
        return administrador;
    }



    @Override
    public void alterarPerfil(Long adminId, MultipartFile file) {
        String uri = protocol + domain +service+"/perfil?adminId="+adminId;

        Imagem imagem = ImagemProcessor.processor(PASTA_CLIENTE_PERFIL+adminId,"PERFIL",file);

        template.put(uri,imagem);
    }


}
