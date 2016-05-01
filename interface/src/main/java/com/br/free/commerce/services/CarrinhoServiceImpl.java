package com.br.free.commerce.services;

import com.br.free.commerce.config.CarrinhoSettings;
import com.br.free.commerce.services.Interface.CarrinhoService;
import com.br.free.commerce.to.CarrinhoDeComprasTO;
import com.free.commerce.entity.CarrinhoDeCompras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 30/04/2016.
 */
@Service
public class CarrinhoServiceImpl implements CarrinhoService {

    @Autowired
    private CarrinhoSettings carrinhoSettings;

    private RestTemplate restTemplate = new RestTemplate();


    @Override
    public void SalvarCarrinhoDeComprasDosProdutos(String cookieId, Long produtoId,int quantidade) {
        String url = carrinhoSettings.salvarCarrinhoDeCompras();
        CarrinhoDeComprasTO carrinhoDeComprasTO = criarCarrinhoDecompresTO(cookieId,produtoId,quantidade);
        try{
            restTemplate.postForEntity(url,carrinhoDeComprasTO,Object.class);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<CarrinhoDeCompras> recuperarProdutosDeCarrinho(String cookieId) {
        List<CarrinhoDeCompras> carrinhos=null;
        String url = carrinhoSettings.buscarCarrinhoDeCompras(cookieId);
        try{
            carrinhos = restTemplate.getForObject(url,ArrayList.class);
        }catch (Exception e){
            e.printStackTrace();

        }
        return carrinhos;
    }

    private CarrinhoDeComprasTO criarCarrinhoDecompresTO(String cookieId, Long produtoId,int quatidade) {
        CarrinhoDeComprasTO carrinhoDeComprasTO = new CarrinhoDeComprasTO();
        carrinhoDeComprasTO.setIdExterno(cookieId);
        carrinhoDeComprasTO.setProdutoId(produtoId);
        carrinhoDeComprasTO.setQuantidade(quatidade);

        return carrinhoDeComprasTO;
    }

}
