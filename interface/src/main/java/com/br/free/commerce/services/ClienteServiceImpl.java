package com.br.free.commerce.services;

import com.br.free.commerce.services.Interface.ClienteService;
import com.br.free.commerce.to.BuscarClienteTO;
import com.br.free.commerce.to.CadastrarClienteTO;
import com.br.free.commerce.to.FinalizarCadastroTO;
import com.br.free.commerce.util.ImagemProcessor;
import com.free.commerce.entity.Cliente;
import com.free.commerce.entity.Imagem;
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
public class ClienteServiceImpl implements ClienteService {

    private RestTemplate template;

    private static final Logger logger = Logger.getLogger(ClienteServiceImpl.class);

    private static String PASTA_CLIENTE_PERFIL="cliente/perfil/";

    private static String url ="http://";
    private static String ip ="localhost";
    private static String port =":8085";
    private static String service="/v1/cliente";

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
            requestUrl = url+ip+port+service;
            logger.info("Chamando a url: " + requestUrl);
            map.put("storeForm",cadastrarClienteTO);

            user = template.postForObject(requestUrl,cadastrarClienteTO,UserLogin.class,map);

        }catch (Exception e){

            e.printStackTrace();
            logger.info("erro na comunicação");

        }

        return user;
    }

    @Override
    public Cliente concluirCadastro(FinalizarCadastroTO cadastroTO) {
        logger.info("Concluindo cadastro do cliente : " + cadastroTO.getEmail());
        String requestUrl;

        Cliente cliente= null;
            requestUrl = "http://localhost:8085/v1/cliente/concluirCadastro";
            logger.info("Chamando a url: " + requestUrl);

            cliente = template.postForObject(requestUrl,cadastroTO,Cliente.class);

        return cliente;
    }

    @Override
    public Cliente buscarCliente(Long id) {
        String url ="http://localhost:8085/v1/cliente?clienteId="+id;

        Cliente cliente = template.getForObject(url,Cliente.class);
        return cliente;
    }

    @Override
    public List<Pedido> meusPedidos(Long clienteId) {
        String url = "http://localhost:8090/v1/pedido/cliente?clienteId="+clienteId;
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
        String url = "http://localhost:8085/v1/cliente/perfil?clienteId="+clienteId;

        Imagem imagem = ImagemProcessor.processor(PASTA_CLIENTE_PERFIL+clienteId,"PERFIL",file);

        template.put(url,imagem);
    }


}
