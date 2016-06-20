package com.free.commerce.controller;

import com.free.commerce.entity.CarrinhoDeCompras;
import com.free.commerce.entity.Categoria;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.Produto;
import com.free.commerce.service.interfaces.CarrinhoService;
import com.free.commerce.service.interfaces.CategoriaService;
import com.free.commerce.service.interfaces.LojaService;
import com.free.commerce.service.interfaces.ProdutoService;
import com.free.commerce.to.BuscarProdutoTO;
import com.free.commerce.to.CarrinhoDeComprasTO;
import com.free.commerce.to.ProdutoPage;
import com.free.commerce.to.ProdutoTO;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pc on 21/03/2016.
 */
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private LojaService lojaService;

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(params = "lojaId", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Produto> cadastrarProduto(@RequestParam("lojaId") Long lojaId, @RequestBody ProdutoTO produtoTO){

        Produto produto = produtoService.CadastrarProduto(produtoTO,lojaId);


        return new ResponseEntity<Produto>(produto,HttpStatus.CREATED);
    }

    @RequestMapping(params = {"lojaId","page"},method = RequestMethod.GET)
    public ResponseEntity<Page<Produto>> recuperarProdutos(@RequestParam("lojaId") String lojaId, @RequestParam("page") String page){

        Loja loja = lojaService.recuperarPorId(Long.parseLong(lojaId));
        Page<Produto> produtos = produtoService.recuperarProdutoPorLoja(loja,pegarPagina(page,"3"));


        return new ResponseEntity<Page<Produto>>(produtos ,HttpStatus.OK);
    }

    @RequestMapping(params = {"lojaId","page","size"},method = RequestMethod.GET)
    public ResponseEntity<ProdutoPage> recuperarProdutosQuery(@RequestParam("lojaId") String lojaId,
                                                                    @RequestParam("page") String first,
                                                                    @RequestParam("size") String last){

        Loja loja = lojaService.recuperarPorId(Long.parseLong(lojaId));
        Page<Produto> produtos = produtoService.recuperarProdutoPorLoja(loja,pegarPagina(first,last));

        ProdutoPage produtoPage = criarProdutoPage(produtos);

        return new ResponseEntity<ProdutoPage>(produtoPage ,HttpStatus.OK);
    }

    @RequestMapping(value = "/lastFive",method = RequestMethod.GET)
    public ResponseEntity<List> ultimosCinco(){

        return new ResponseEntity<List>(produtoService.recuperarUltimosCincoProduto(),HttpStatus.OK);
    }

    @RequestMapping
    public ResponseEntity<ProdutoPage> buscarProduto(@RequestParam(value = "cidade",defaultValue = "")String cidade,
                                                    @RequestParam(value = "categoria",defaultValue = "") String categoria,
                                                    @RequestParam(value = "produtoNome",defaultValue = "") String nome,
                                                    @RequestParam(value = "novo",defaultValue = "") String novo,
                                                    @RequestParam(value = "order",defaultValue = "") String orderBy,
                                                    @RequestParam(value = "page") Integer pagina,
                                                    @RequestParam(value = "size") Integer size,
                                                    @RequestParam(value = "qtd",defaultValue = "0") String qtd){
        BuscarProdutoTO buscarProdutoTO = new BuscarProdutoTO();
        int quantd=0;
        if (qtd.startsWith("-")){
            quantd = -1;
        }else {
            quantd = Integer.parseInt(qtd);
        }

        if (!"".equalsIgnoreCase(categoria)){
            List<String> categoriasRelacionadas = categoriaService.categoriasAssociadas(categoriaService.buscarCategoriaPelaDescricao(categoria));
            buscarProdutoTO.setCategorias(categoriasRelacionadas);
        }

        buscarProdutoTO.setCategoria(categoria);
        buscarProdutoTO.setCidade(cidade);
        buscarProdutoTO.setNome(nome);
        buscarProdutoTO.setNovo(novo);
        buscarProdutoTO.setOrderBy(orderBy);
        buscarProdutoTO.setQuantidade(quantd);

        return new ResponseEntity(criarProdutoPage(produtoService.buscarProdutos(buscarProdutoTO,new PageRequest(pagina,size))),HttpStatus.OK);

    }

    @RequestMapping(params = "produtoId",method = RequestMethod.GET)
    public ResponseEntity<Produto> buscarProdutoPorId(@RequestParam("produtoId") Long id){

        Produto produto = null;

        produto = produtoService.buscarPorId(id);

        if (produto==null){
            return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Produto>(produto,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Produto> alterarProduto(@RequestBody Produto produto){

        try {

            return new ResponseEntity<Produto>(produtoService.alterarProduto(produto),HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Produto>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity alterarProduto(@RequestParam("produtoId") long produtoId, @RequestParam("imagemId") long imagemId){

        try {
            produtoService.deletarImagem(produtoId,imagemId);
            return new ResponseEntity<Produto>(HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Produto>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    private ProdutoPage criarProdutoPage(Page<Produto> produtos) {
        ProdutoPage produtoPage = new ProdutoPage();
        produtoPage.setProdutos(produtos.getContent());
        produtoPage.setTotalElements(produtos.getNumberOfElements());
        produtoPage.setTotalPages(produtos.getTotalPages());
        produtoPage.setNumberOfElements(produtos.getNumberOfElements());
        return produtoPage;
    }

    private Pageable pegarPagina(String page,String limite){
        return new PageRequest(Integer.parseInt(page),Integer.parseInt(limite));
    }


}
