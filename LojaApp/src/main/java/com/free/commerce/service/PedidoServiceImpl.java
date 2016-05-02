package com.free.commerce.service;

import com.free.commerce.entity.Enums.ItemPedidoStatus;
import com.free.commerce.entity.ItemPedido;
import com.free.commerce.entity.Pedido;
import com.free.commerce.entity.Produto;
import com.free.commerce.repository.PedidoRepository;
import com.free.commerce.service.interfaces.ClienteService;
import com.free.commerce.service.interfaces.PedidoService;
import com.free.commerce.service.interfaces.ProdutoService;
import com.free.commerce.to.RegistrarPedidoTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by pc on 02/05/2016.
 */
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PedidoRepository pedidoRepository;


    @Autowired
    private ClienteService clienteService;

    @Override
    public void registrarPedido(RegistrarPedidoTO registrarPedidoTO) {
        Pedido pedido = criarPedido(registrarPedidoTO);
        List<ItemPedido> itemPedidos = new ArrayList<>();
        Map<String,String> produtoMap = registrarPedidoTO.getProdutoQuantidade();
        Double valorTotal=0.;

        Iterator<String> produtoIdIterator =produtoMap.keySet().iterator();

        do{
            ItemPedido itemPedido;
            String produtoId = produtoIdIterator.next();
            String quantidade = produtoMap.get(produtoId);
            Produto produto = produtoService.buscarPorId(Long.parseLong(produtoId));
            itemPedido = criarItemPedido(produto,quantidade);
            itemPedidos.add(itemPedido);
            valorTotal = valorTotal + (produto.getPreco()* Integer.parseInt(quantidade));
        }while (produtoIdIterator.hasNext());

        pedido.getItemPedido().addAll(itemPedidos);
        pedido.setValor(valorTotal);
        pedido.setRegistrado(new Date());
        pedido.setCliente(clienteService.recuperarProID(Long.parseLong(registrarPedidoTO.getClienteId())));

        pedidoRepository.save(pedido);

    }

    private Pedido criarPedido(RegistrarPedidoTO registrarPedidoTO) {

        return null;
    }

    private ItemPedido criarItemPedido(Produto produto,String quantidade){
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(Integer.parseInt(quantidade));
        itemPedido.setStatus(ItemPedidoStatus.ABERTO);
        itemPedido.setRegistrado(new Date());

        return itemPedido;
    }
}
