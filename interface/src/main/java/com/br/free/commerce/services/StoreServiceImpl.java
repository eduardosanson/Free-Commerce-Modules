package com.br.free.commerce.services;

import com.br.free.commerce.services.Interface.StoreService;
import com.br.free.commerce.to.CadastrarLojaTO;
import com.br.free.commerce.util.ImagemProcessor;
import com.free.commerce.entity.Imagem;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.Pedido;
import com.free.commerce.entity.UserLogin;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by pc on 19/03/2016.
 */
@Service
public class StoreServiceImpl implements StoreService {

    private RestTemplate template;

    private static final Logger logger = Logger.getLogger(StoreServiceImpl.class);

    private static String protocol ="http://";
    private static String domain ="lojacommerce.herokuapp.com";
    private static String service="/loja";

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
            requestUrl = protocol + domain +service;
            logger.info("Chamando a protocol: " + requestUrl);
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
        String url = "http://lojacommerce.herokuapp.com/loja/perfil?lojaId="+lojaId;

        Imagem imagem = ImagemProcessor.processor(PASTA_LOJA_PERFIL+lojaId,"PERFIL",file);

        template.put(url,imagem);
    }

    @Override
    public Loja buscarLoja(Long id) {
        String url = "http://lojacommerce.herokuapp.com/loja/"+id;
        return template.getForObject(url,Loja.class);
    }

    @Override
    public Loja buscarLojaPorCpfOuCnpj(String cpfOuCnpj) {
        String url = "http://lojacommerce.herokuapp.com/loja?cpfOuCnpj="+cpfOuCnpj;
        return template.getForObject(url,Loja.class);
    }

    @Override
    public List<Pedido> minhasSolicitacoes(Long id) {
        String url = "http://lojacommerce.herokuapp.com/pedido/loja?lojaId="+id;
        List<Pedido> pedidos=null;
        try {
            pedidos = template.getForObject(url,List.class);
        }catch (HttpClientErrorException httpE){

            Optional.of(httpE).ifPresent(n->{
                if(n.getMessage().contains("404 Not Found")){
                    logger.warn("Cliente sem pedidos");
                }else {
                    logger.error("Erro http não mapeado");
                }
            });
        }

        catch (Exception e){
            e.printStackTrace();

        }finally {

            return pedidos;
        }
    }
}
