package com.br.free.commerce.controllers;

import com.br.free.commerce.bean.Carrinho;
import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.services.Interface.CategoriaService;
import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.to.BuscarProdutoTO;
import com.br.free.commerce.to.ProdutoPage;
import com.br.free.commerce.to.ProdutoTO;
import com.br.free.commerce.to.ProdutoView;
import com.br.free.commerce.util.Page;
import com.free.commerce.entity.Categoria;
import com.free.commerce.entity.Produto;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eduardo.sanson on 23/03/2016.
 */
@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private static final String PRODUCT_DETAIL_PAGE = "product-detail";
    private static final String PRODUCT_DETAIL_FRAGMENT = "product-detail";
    private static final String PRODUCTS_SHOW_PAGE ="products-category";
    private static final String PRODUCTS_SHOW_FRAGMENT ="products-category";
    private static final String PAGE_NAME="pageName";
    private static final String PAGE_FRAGMENT="pageFragment";
    private static final String INDEX = "index";
    private static final String PAGE_LISTA_PRODUTOS ="produto-list";
    private static final String FRAGMENT_LISTA_PRODUTOS ="produto-list";
    private static final String CARRINHO_HEADER_PAGE ="carrinho-header";
    private static final String CARRINHO_HEADER_PFRAGMENT ="carrinho-header";
    private static final String quantidadeDeProdutoPorPagina="5";

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private Carrinho carrinho;

    @RequestMapping(method = RequestMethod.POST)
    public String cadastrarProduto(@Valid ProdutoTO produtoTO, BindingResult bindingResult,
                                   Model model,@AuthenticationPrincipal CustomUserDetails customUserDetails){

        if(bindingResult.hasErrors()){
            return "redirect:menu/cadastrarProduto";
        }

        produtoService.cadastrarProduto(customUserDetails.getUserlogin().getLoja(),produtoTO);

        return "redirect:"+ LojaController.MENU;
    }

    @RequestMapping("/detail/{productId}")
    public String productDetail(@PathVariable("productId") String id, Model model){
        model.addAttribute(PAGE_NAME,PRODUCT_DETAIL_PAGE);
        model.addAttribute(PAGE_FRAGMENT,PRODUCT_DETAIL_FRAGMENT);

        Produto produto = produtoService.buscarProdutoPorId(id);

        model.addAttribute("produto",produto);
        model.addAttribute("carrinho",carrinho);

        return INDEX;
    }

    @RequestMapping
    public String mostrarProdutos(BuscarProdutoTO buscarProdutoTO, Model model){
        model.addAttribute(PAGE_NAME,PRODUCTS_SHOW_PAGE);
        model.addAttribute(PAGE_FRAGMENT,PRODUCTS_SHOW_FRAGMENT);


        ProdutoPage produtoPage = produtoService.buscarPorNomeParecido(buscarProdutoTO.getNome(),buscarProdutoTO.getNumeroDaPagina(),quantidadeDeProdutoPorPagina);

        Page page = criarPagina(buscarProdutoTO.getNumeroDaPagina(), produtoPage);


        List<ProdutoView> produtosViews = criarProdutoDeVisualizacao(produtoPage);

        model.addAttribute("produtoPage",produtosViews);
        model.addAttribute("produtos",produtoPage);
        model.addAttribute("page",page);
        model.addAttribute("nomeProcurado",buscarProdutoTO.getNome());


        return INDEX;
    }

    private List<ProdutoView> criarProdutoDeVisualizacao(ProdutoPage produtoPage) {

        List<ProdutoView> produtosViews = new ArrayList<>();
        Categoria categoria=null;
        for (Produto produto : produtoPage.getProdutos()) {

            ProdutoView produtoView = new ProdutoView();
            boolean aindaTemCategoriaParaBuscar=true;
            produtoView.setProduto(produto);
            produtoView.getCategorias().add(produto.getCategoria());

            do{
                categoria = categoriaService.buscarPaiPeloFilho(String.valueOf(produto.getCategoria().getId()));
                if (categoria==null){
                    aindaTemCategoriaParaBuscar=false;
                }else {
                    produtoView.getCategorias().add(categoria);
                }
            }while (aindaTemCategoriaParaBuscar);

            produtosViews.add(produtoView);

        }
        return produtosViews;
    }

    @RequestMapping(value = "/{pageNumber}/{nomeProduto}")
    public String showProductsPage(@PathVariable String pageNumber, @PathVariable String nomeProduto ,Model model){

        model.addAttribute(PAGE_NAME,PRODUCTS_SHOW_PAGE);
        model.addAttribute(PAGE_FRAGMENT,PRODUCTS_SHOW_FRAGMENT);

        ProdutoPage produtos = produtoService.buscarPorNomeParecido(nomeProduto,pageNumber,quantidadeDeProdutoPorPagina);

        Page page = criarPagina(pageNumber, produtos);

        model.addAttribute("produtoPage",produtos);
        model.addAttribute("page",page);

        return "fragments/" + PAGE_LISTA_PRODUTOS + " :: " + FRAGMENT_LISTA_PRODUTOS;
    }

    @RequestMapping(value = "/addAoCarrrinho",params = {"produtoId","quantidade"})
    public String addProduto(@RequestParam("produtoId") String produtoId,
                             @RequestParam("quantidade") String quantidade,
                             Model model){
        Produto produto = produtoService.buscarProdutoPorId(produtoId);

        carrinho.addProduto(produto,Integer.parseInt(quantidade));


        model.addAttribute("carrinho",carrinho);

        return "fragments/"+ CARRINHO_HEADER_PAGE + " :: " + CARRINHO_HEADER_PFRAGMENT;
    }

    private Page criarPagina(String pagina, ProdutoPage produtos) {
        Page page = new Page();
        page.setQtdElementosPorPagina(produtos.getNumberOfElements());
        page.setPaginaAtual(Integer.parseInt(pagina));
        page.setTotalDePaginas(produtos.getqtdPages());
        return page;
    }




}
