package com.br.free.commerce.controller;

import com.br.free.commerce.bean.Carrinho;
import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.services.Interface.CarrinhoService;
import com.br.free.commerce.services.Interface.CategoriaService;
import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.to.BuscarProdutoTO;
import com.br.free.commerce.to.ProdutoPage;
import com.br.free.commerce.to.ProdutoTO;
import com.br.free.commerce.to.ProdutoView;
import com.br.free.commerce.util.Page;
import com.free.commerce.entity.Categoria;
import com.free.commerce.entity.Produto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    private static final String PAGE_ACCOUNT = "account-posts";
    private static final String PAGE_CREATE_PRODUCT ="account-create-product";
    private static final String FRAGMENT_CADASTRAR_IMAGEM_PRODUTO ="cadastrar-imagem-produto";
    private static final String PAGINA_CADASTRAR_IMAGEM_PRODUTO ="cadastrar-imagem-produto";
    private static final String MENU_NAME="MENU_NAME";
    private static final String MENU_FRAGMENT="MENU_FRAGMENT";

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    public Carrinho carrinho;

    @Autowired
    private CarrinhoService carrinhoService;

    private static final Logger logger =  Logger.getLogger(ProdutoController.class);


    @RequestMapping(value = "/menu/upload",method = RequestMethod.POST)
    public ResponseEntity associarImagem(@RequestParam(value = "file") MultipartFile file, @RequestParam("produtoId") String produtoId){

        logger.info(file.getOriginalFilename() + "  " + produtoId);

        try {

            produtoService.associarImagem(file,produtoId);

        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST)
    public String cadastrarProduto(@Valid ProdutoTO produtoTO, BindingResult bindingResult,
                                   Model model,@AuthenticationPrincipal CustomUserDetails customUserDetails){

        if(bindingResult.hasErrors()){
            return "redirect:menu/cadastrarProduto";
        }

        model.addAttribute(PAGE_NAME,PAGE_ACCOUNT);
        model.addAttribute(PAGE_FRAGMENT,PAGE_ACCOUNT);

        model.addAttribute(MENU_NAME,PAGINA_CADASTRAR_IMAGEM_PRODUTO);
        model.addAttribute(MENU_FRAGMENT,FRAGMENT_CADASTRAR_IMAGEM_PRODUTO);


        Produto produto = produtoService.cadastrarProduto(customUserDetails.getUserlogin().getLoja(),produtoTO);

        model.addAttribute("produtoId",produto.getId());

        return INDEX;
    }

    @RequestMapping(value = "/editar",method = RequestMethod.POST)
    public String editarProduto(Produto produto){


        produtoService.alterarProduto(produto);

        return "redirect:/store/menu";
    }

    @RequestMapping("/detail/{productId}")
    public String productDetail(@PathVariable("productId") String id, Model model){
        model.addAttribute(PAGE_NAME,PRODUCT_DETAIL_PAGE);
        model.addAttribute(PAGE_FRAGMENT,PRODUCT_DETAIL_FRAGMENT);

        Produto produto = produtoService.buscarProdutoPorId(id);

        model.addAttribute("produto",produto);
//        model.addAttribute("carrinho",carrinho);

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
                             Model model, HttpServletRequest request){
        Produto produto = produtoService.buscarProdutoPorId(produtoId);
        String cookieId="";

        carrinho.addProduto(produto,Integer.parseInt(quantidade));


        Cookie[] cookies = request.getCookies();

        for (Cookie cookie: cookies) {

            if ("JSESSIONID".equalsIgnoreCase(cookie.getName())){
                cookieId = cookie.getValue();
            }

        }

        carrinhoService.SalvarCarrinhoDeComprasDosProdutos(cookieId,produto.getId(),Integer.parseInt(quantidade));

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
