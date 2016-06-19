package com.free.commerce.service;

import com.free.commerce.entity.Enums.ItemPedidoStatus;
import com.free.commerce.entity.Enums.PedidoStatus;
import com.free.commerce.entity.ItemPedido;
import com.free.commerce.entity.Pedido;
import com.free.commerce.entity.Produto;
import com.free.commerce.repository.ItemPedidoRepository;
import com.free.commerce.repository.PedidoRepository;
import com.free.commerce.service.interfaces.ClienteService;
import com.free.commerce.service.interfaces.PedidoService;
import com.free.commerce.service.interfaces.ProdutoService;
import com.free.commerce.to.ProdutoPedido;
import com.free.commerce.to.RegistrarPedidoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

/**
 * Created by pc on 02/05/2016.
 */
@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PedidoRepository pedidoRepository;


    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Override
    public Pedido registrarPedido(RegistrarPedidoTO registrarPedidoTO) {
        Pedido pedido = new Pedido();
        List<ItemPedido> itemPedidos = new ArrayList<>();
        Double valorTotal=0.;

        pedido.setRegistrado(new Date());
        pedido.setCliente(clienteService.recuperarProID(Long.parseLong(registrarPedidoTO.getClienteId())));

        pedido = pedidoRepository.save(pedido);

        for (ProdutoPedido produtoPedido:registrarPedidoTO.getProdutoPedido()) {
            ItemPedido itemPedido;
            Produto produto = produtoService.buscarPorId(Long.parseLong(produtoPedido.getProdutoId()));
            itemPedido = criarItemPedido(produto,produtoPedido.getQuatidade());
            itemPedido.setStatus(ItemPedidoStatus.AGUARDANDO_PAGAMENTO);
            itemPedido.setPedido(pedido);
            itemPedidos.add(itemPedido);
            itemPedidoRepository.save(itemPedido);

            valorTotal = valorTotal + (produto.getPreco()* Integer.parseInt(produtoPedido.getQuatidade()));
        }
        pedido.setValor(valorTotal);
        pedido.setStatus(PedidoStatus.AGUARDANDO_PAGAMENTO);

        pedido.setItemPedido(itemPedidos);

       return pedidoRepository.save(pedido);

    }

    @Override
    public List<Pedido> buscarPedidoDeCliente(String clienteId) {
        List<Pedido> pedidos=null;
        try {
            pedidos = pedidoRepository.buscarPedidosPorCliente(Long.parseLong(clienteId));
        }catch (Exception e){
            e.printStackTrace();
        }
        return pedidos;
    }

    @Override
    public List<Pedido> buscarSolicitacaoLoja(Long lojaId) {
        return pedidoRepository.buscarSolicitacaoDeLoja(lojaId);
    }

    private ItemPedido criarItemPedido(Produto produto,String quantidade){
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(Integer.parseInt(quantidade));
        itemPedido.setStatus(ItemPedidoStatus.AGUARDANDO_PAGAMENTO);
        itemPedido.setRegistrado(new Date());

        return itemPedido;
    }

    @Override
    public Pedido buscarPeloId(Long id) {
        return pedidoRepository.findOne(id);
    }

    @Override
    public void aterarStatus(Long id,PedidoStatus status, String notificationCode) {
        Pedido pedido = pedidoRepository.findOne(id);

        if (pedido==null){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        pedido.setStatus(status);
        pedido.setCodigoDeNotificacao(notificationCode);

        for (ItemPedido itemPedido: pedido.getItemPedido()) {
            itemPedido.setStatus(ItemPedidoStatus.getItemPedido(status));
        }

        pedidoRepository.save(pedido);
    }

    @Override
    public void confirmarPagamentos(Long id){
        Pedido pedido = pedidoRepository.findOne(id);

        for (ItemPedido itemPedido:pedido.getItemPedido()) {
            Produto produto = itemPedido.getProduto();
            int quantidadeEmEstoque = produto.getQuantidade();
            int quantidadeComprada = itemPedido.getQuantidade();
            int quantidadeFinal = quantidadeEmEstoque - quantidadeComprada;
            quantidadeFinal = quantidadeFinal < 0 ? 0 : quantidadeFinal;
            produto.setQuantidade(quantidadeFinal);
            produtoService.salvar(produto);
        }
    }
}
