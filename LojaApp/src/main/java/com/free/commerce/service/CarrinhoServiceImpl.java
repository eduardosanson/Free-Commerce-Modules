package com.free.commerce.service;

import com.free.commerce.entity.CarrinhoDeCompras;
import com.free.commerce.entity.Produto;
import com.free.commerce.repository.CarrinhoDeComprasRepository;
import com.free.commerce.service.interfaces.CarrinhoService;
import com.free.commerce.service.interfaces.ProdutoService;
import com.free.commerce.to.CarrinhoDeComprasTO;
import com.free.commerce.to.ProdutoDeCarrinhoDeCompraTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pc on 27/04/2016.
 */
@Service
public class CarrinhoServiceImpl implements CarrinhoService {

    @Autowired
    private CarrinhoDeComprasRepository carrinhoDeComprasRepository;

    @Autowired
    private ProdutoService produtoService;

    @Override
    public List<CarrinhoDeCompras> buscarCarProdutoPeloCookie(String cookieId) {
        List<CarrinhoDeCompras> carrinhos = null;

        try {
            carrinhos = carrinhoDeComprasRepository.buscarCarrinhoPorCookieId(cookieId);
        }catch (Exception e){
            e.getMessage();
        }

        return carrinhos;
    }

    @Override
    public void cadastrarCarrinhoDeCompras(CarrinhoDeComprasTO carrinhoDeComprasTO) {
        CarrinhoDeCompras carrinhoDeCompras = null;
        Produto produto = null;

        try {
            produto = produtoService.buscarPorId(carrinhoDeComprasTO.getProdutoId());
            carrinhoDeCompras = carrinhoDeComprasRepository.buscarCarrinhoPorCookieIdEProduto(carrinhoDeComprasTO.getIdExterno(),produto);
            if (carrinhoDeCompras!=null){
                carrinhoDeCompras.setQuantidade(carrinhoDeCompras.getQuantidade()+carrinhoDeComprasTO.getQuantidade());
            }else {
                carrinhoDeCompras = ConverterTOCarrinhoDeCompras(carrinhoDeComprasTO,produto);
            }

            carrinhoDeComprasRepository.save(carrinhoDeCompras);
        }catch (Exception e){

        }


    }

    @Override
    public CarrinhoDeCompras recuperarProdutoDeCarrinhoDeCompras(ProdutoDeCarrinhoDeCompraTO produtoDeCarrinhoDeCompraTO) {
        Produto produto;
        CarrinhoDeCompras carrinhoDeCompras = null;
        try {

            produto = produtoService.buscarPorId(Long.parseLong(produtoDeCarrinhoDeCompraTO.getProdutoId()));
            carrinhoDeCompras = carrinhoDeComprasRepository.buscarCarrinhoPorCookieIdEProduto(produtoDeCarrinhoDeCompraTO.getjSessionId(),produto);

            return carrinhoDeCompras;
        }catch (Exception e){
            e.printStackTrace();
            return carrinhoDeCompras;

        }
    }

    private CarrinhoDeCompras ConverterTOCarrinhoDeCompras(CarrinhoDeComprasTO carrinhoDeComprasTO,Produto produto) {
        CarrinhoDeCompras carrinhoDeCompras = new CarrinhoDeCompras();
        carrinhoDeCompras.setJsessionId(carrinhoDeComprasTO.getIdExterno());
        carrinhoDeCompras.setQuantidade(carrinhoDeComprasTO.getQuantidade());
        carrinhoDeCompras.setProduto(produto);

        return carrinhoDeCompras;

    }
}
