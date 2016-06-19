package com.br.free.commerce.controller;

import com.br.free.commerce.bean.Carrinho;
import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.to.BuscarProdutoTO;
import com.br.free.commerce.to.CarrinhoTO;
import com.br.free.commerce.to.Frete;
import com.br.free.commerce.to.RegistrarPedidoTO;
import com.br.free.commerce.util.NumberUtil;
import com.free.commerce.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by pc on 30/04/2016.
 */
@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

    private static final String PAGE_CARRINHO="carrinho";
    private static final String FRAGMENT_CARRINHO="carrinho";
    private static final String PAGE_NAME ="pageName";
    private static final String FRAGMENT_NAME="pageFragment";
    private static final String CARRINHO_HEADER_PAGE ="carrinho-header";
    private static final String CARRINHO_HEADER_PFRAGMENT ="carrinho-header";
    private static final String SEM_PRODUTO_NO_ESTOQUE_PAGE = "sem-produto";
    private static final String SEM_PRODUTO_NO_ESTOQUE_FRAGMENT = "sem-produto";

    @Autowired
    private Carrinho carrinho;

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(method = RequestMethod.GET)
    public String showCarrinhoHeader(Model model, BuscarProdutoTO buscarProdutoTO, RegistrarPedidoTO registrarPedidoTO, Frete frete){
        model.addAttribute(PAGE_NAME,PAGE_CARRINHO );
        model.addAttribute(FRAGMENT_NAME,FRAGMENT_CARRINHO);

        return "index";
    }

    @RequestMapping(value = "/remover/{produtoId}",method = RequestMethod.GET)
    public String retirarDoCarrinho(Model model, @PathVariable("produtoId") Long id){

        Produto produto = new Produto();
        produto.setId(id);
        carrinho.removerProduto(produto);

        return "fragments/"+ CARRINHO_HEADER_PAGE + " :: " + CARRINHO_HEADER_PFRAGMENT;
    }

    @RequestMapping(value = "/somar/{produtoId}",method = RequestMethod.GET)
    public String somarNoCarrinho(Model model, @PathVariable("produtoId") Long id){

        Produto produto = produtoService.buscarProdutoPorId(id.toString());
        int quantidadeDisponivel = produto.getQuantidade();
        int quantidadeNoCarrinho = carrinho.getConteudo().get(produto) != null ? carrinho.getConteudo().get(produto) : 0;
        quantidadeDisponivel = quantidadeDisponivel - quantidadeNoCarrinho;

        if (quantidadeDisponivel<=0){
            model.addAttribute("detail","/produto/detail/"+id);
            return "fragments/"+ SEM_PRODUTO_NO_ESTOQUE_PAGE + " :: " + SEM_PRODUTO_NO_ESTOQUE_FRAGMENT;

        }

        carrinho.somarQuantidadeProduto(produto);

        return "fragments/"+ CARRINHO_HEADER_PAGE + " :: " + CARRINHO_HEADER_PFRAGMENT;
    }

    @RequestMapping(value = "/subtrair/{produtoId}",method = RequestMethod.GET)
    public String reduzirNoCarrinho(Model model, @PathVariable("produtoId") Long id){

        Produto produto = new Produto();
        produto.setId(id);
        carrinho.diminuirQuantidadeProduto(produto);

        return "fragments/"+ CARRINHO_HEADER_PAGE + " :: " + CARRINHO_HEADER_PFRAGMENT;
    }

    @RequestMapping(value = "/adicionarFrete",method = RequestMethod.POST)
    public String addValorFrete(Frete frete,Model model){

        String valor = frete.getReal()+"."+frete.getMoeda();

        carrinho.getFrete().addFreteProduto(frete.getProdutoId(),new Double(valor));

        return "redirect:/carrinho";
    }

    @RequestMapping(value = "/quantidade/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Integer quantidadeDeProdutoPorId(@PathVariable("id") Long id){
        Produto produto = new Produto();
        produto.setId(id);

        return carrinho.getConteudo().get(produto);

    }

    @RequestMapping(value = "/custo/produto/{id}",method = RequestMethod.GET)
    @ResponseBody
    public BigDecimal custoTotalDoProduto(@PathVariable("id") Long id){
        Produto produto = new Produto();
        produto.setId(id);

        Double valor = new Double(0);

        for (Produto p :carrinho.getConteudo().keySet()) {

            if (p.getId()==id){
                valor = p.getPreco() * carrinho.getConteudo().get(p);

            }
        }
        return NumberUtil.formatToMoney(valor);

    }

    @RequestMapping(value = "/custo/{id}",method = RequestMethod.GET)
    @ResponseBody
    public BigDecimal custoTotalDoCarrinho(@PathVariable("id") Long id){
        Produto produto = new Produto();
        produto.setId(id);

        Double valor = new Double(0);

        for (Produto p :carrinho.getConteudo().keySet()) {

                valor = valor + (p.getPreco() * carrinho.getConteudo().get(p));

        }
        return NumberUtil.formatToMoney(valor);

    }

    @RequestMapping(value = "/produto/{id}.json",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<CarrinhoTO> carrinhoJson(@PathVariable("id") Long id) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(500);
        CarrinhoTO carrinhoTO = new CarrinhoTO();
        Produto produto = new Produto();
        produto.setId(id);

        Double valor = new Double(0);

        for (Produto p :carrinho.getConteudo().keySet()) {

            valor = valor + (p.getPreco() * carrinho.getConteudo().get(p));

        }

        Double valorTotalProduto = new Double(0);

        for (Produto p :carrinho.getConteudo().keySet()) {

            if (p.getId()==id){
                valorTotalProduto = p.getPreco() * carrinho.getConteudo().get(p);

            }
        }
        carrinhoTO.setValorPorProduto(NumberUtil.formatToMoney(valorTotalProduto).toString());
        carrinhoTO.setValorCarrinho(NumberUtil.formatToMoney(valor).toString());
        carrinhoTO.setQuantideDoProduto(carrinho.getConteudo().get(produto));
        return new ResponseEntity<CarrinhoTO>(carrinhoTO, HttpStatus.OK);

    }


}
