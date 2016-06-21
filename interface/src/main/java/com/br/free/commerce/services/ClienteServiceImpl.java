package com.br.free.commerce.services;

import com.br.free.commerce.services.Interface.ClienteService;
import com.br.free.commerce.to.CadastrarClienteTO;
import com.br.free.commerce.to.FinalizarCadastroTO;
import com.br.free.commerce.util.ImagemProcessor;
import com.free.commerce.entity.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by pc on 19/03/2016.
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    private RestTemplate template;

    private static final Logger logger = Logger.getLogger(ClienteServiceImpl.class);

    private static String PASTA_CLIENTE_PERFIL="cliente/perfil/";

    private static String protocol ="http://";
    private static String domain ="clienteapp.herokuapp.com";
    private static String service="/cliente";

    public ClienteServiceImpl() {
        this.template = new RestTemplate();
    }

    @Override
    public UserLogin cadastrarCliente(CadastrarClienteTO cadastrarClienteTO) {
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
    public void alterarDadosDeCliente(Cliente cliente) {
        logger.info("inicio de cadastro de cliente: " + cliente.getEmail());
        String requestUrl;
        Map<String, CadastrarClienteTO> map = new HashMap<String, CadastrarClienteTO>();

        UserLogin user= null;
        try{
            requestUrl = protocol + domain +service;
            URI uri = new URI(requestUrl);
            logger.info("Chamando a protocol: " + requestUrl + " put");

            template.put(uri,cliente);

        }catch (Exception e){

            e.printStackTrace();
            logger.info("erro na comunicação");

        }
    }

    @Override
    public Cliente concluirCadastro(FinalizarCadastroTO cadastroTO) {
        logger.info("Concluindo cadastro do cliente : " + cadastroTO.getEmail());
        String requestUrl;

        Cliente cliente= null;
            requestUrl = "http://clienteapp.herokuapp.com/cliente/concluirCadastro";
            logger.info("Chamando a protocol: " + requestUrl);

            cliente = template.postForObject(requestUrl,cadastroTO,Cliente.class);

        return cliente;
    }

    @Override
    public Cliente buscarCliente(Long id) {
        String url ="http://clienteapp.herokuapp.com/cliente?clienteId="+id;

        Cliente cliente = template.getForObject(url,Cliente.class);
        return cliente;
    }

    @Override
    public List<Pedido> meusPedidos(Long clienteId) {
        String url = "http://lojacommerce.herokuapp.com/pedido/cliente?clienteId="+clienteId;
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

    @Override
    public void alterarPerfil(Long clienteId, MultipartFile file) {
        String url = "http://clienteapp.herokuapp.com/cliente/perfil?clienteId="+clienteId;

        Imagem imagem = ImagemProcessor.processor(PASTA_CLIENTE_PERFIL+clienteId,"PERFIL",file);

        template.put(url,imagem);
    }

    @Override
    public Cliente buscarLojaPorCpfOuCnpj(String cpf) {
        String url = "http://clienteapp.herokuapp.com/cliente?cpf="+cpf;
        return template.getForObject(url,Cliente.class);
    }


}
