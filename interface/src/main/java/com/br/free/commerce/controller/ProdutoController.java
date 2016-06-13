package com.br.free.commerce.controller;

import com.br.free.commerce.bean.Carrinho;
import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.services.EnderecoServiceImpl;
import com.br.free.commerce.services.Interface.CarrinhoService;
import com.br.free.commerce.services.Interface.CategoriaService;
import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.services.MensagemServiceImpl;
import com.br.free.commerce.to.*;
import com.br.free.commerce.util.Page;
import com.free.commerce.entity.Categoria;
import com.free.commerce.entity.Mensagem;
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
import java.util.*;

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

    @Autowired
    private MensagemServiceImpl mensagemService;

    @Autowired
    private EnderecoServiceImpl enderecoService;


    private static final Logger logger =  Logger.getLogger(ProdutoController.class);


    @RequestMapping(value = "/menu/upload",method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> associarImagem(@RequestParam(value = "file") List<MultipartFile> files, @RequestParam("produtoId") String produtoId){


        logger.info(files  + "  " + produtoId);

        try {
                produtoService.associarImagem(files,produtoId);

        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<String>("{\"response\" : \"sucesso\"}",HttpStatus.OK);
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

    @RequestMapping(value = "/editar/{id}",method = RequestMethod.POST)
    public String editarProduto(Produto produto){


        produtoService.alterarProduto(produto);

        return "redirect:/store/menu";
    }

    @RequestMapping("/detail/{productId}")
    public String productDetail(@PathVariable("productId") String id, CadastrarMensagemTO cadastrarMensagemTO,
                                ResponderMensagemTO responderMensagemTO, Model model,MensagemLoginTO mensagemLoginTO,
                                MensagemCadastroRapidoTO mensagemCadastroRapidoTO){
        int pagina = 0;
        int limite = 5;
        model.addAttribute(PAGE_NAME,PRODUCT_DETAIL_PAGE);
        model.addAttribute(PAGE_FRAGMENT,PRODUCT_DETAIL_FRAGMENT);

        Produto produto = produtoService.buscarProdutoPorId(id);

        cadastrarMensagemTO.setProdutoId(Long.valueOf(id));


        List<Categoria> categorias = new ArrayList<>();
        Categoria categoria = produto.getCategoria();

        while (categoria != null){
            categorias.add(categoria);
            categoria = categoria.getPai();
        }
        Collections.sort(categorias,(c1,c2) -> c1.getId().compareTo(c2.getId()));

        List<Mensagem> mensagens = mensagemService.recuperarMensagensPorProduto(Long.valueOf(id),pagina,limite);

        mensagens.stream().sorted((m1,m2)-> m1.getReistrado().compareTo(m2.getReistrado()));

        ProdutoPage produtos = produtoService.recuperarProdutosDeLoja(produto.getLoja(),
                "1","9");

        int qtdDeMensagens = mensagemService.recuperarMensagensPorProdutoList(produto.getId()).size();
        int qtdDePaginas =  qtdDeMensagens%limite>0?(qtdDeMensagens/limite)+1 : qtdDeMensagens/limite;

        Page page = new Page();
        page.setPaginaAtual(pagina);
        page.setQtdElementosPorPagina(limite);
        page.setTotalDePaginas(qtdDePaginas);


        responderMensagemTO.setProdutoId(produto.getId());
        model.addAttribute("mensagens",mensagens);
        model.addAttribute("categoriasRelacionadas",categorias);
        model.addAttribute("produto",produto);
        model.addAttribute("page",page);
        model.addAttribute("outrosProdutos",
                Optional
                        .ofNullable(produtos.getProdutos())
                        .filter(a -> a.remove(produto)).get());

        return INDEX;
    }

    @RequestMapping(value = "/responderMensagem",method = RequestMethod.POST)
    public String responderMensagem(ResponderMensagemTO responderMensagemTO,Model model){

        mensagemService.responderMensagem(responderMensagemTO);

        return "redirect:/produto/detail/"+responderMensagemTO.getProdutoId();

    }

    @RequestMapping(value = "/maisMensagens",method = RequestMethod.GET)
    public String maisMensagens(@RequestParam("produtoId") Long produtoId,
                                @RequestParam("paginaAtual") int paginaAtual,
                                Model model){
        int limiteDePaginas = 5;
        paginaAtual = paginaAtual-1;

        int qtdDeMensagens = mensagemService.recuperarMensagensPorProdutoList(produtoId).size();
        int qtdDePaginas =  qtdDeMensagens%limiteDePaginas>0?(qtdDeMensagens/limiteDePaginas)+1 : qtdDeMensagens/limiteDePaginas;

        Page page = new Page();
        page.setPaginaAtual(paginaAtual-1);
        page.setQtdElementosPorPagina(limiteDePaginas);
        page.setTotalDePaginas(qtdDePaginas);

        List<Mensagem> mensagens = mensagemService.recuperarMensagensPorProduto(produtoId,paginaAtual,5);
        Produto produto = produtoService.buscarProdutoPorId(produtoId.toString());

        model.addAttribute("mensagens",mensagens);
        model.addAttribute("page",page);
        model.addAttribute("produto",produto);

        return "fragments/mensagens :: mensagens";

    }

    @RequestMapping(method = RequestMethod.GET)
    public String mostrarProdutos(@RequestParam(value = "categoria",defaultValue = "") String categoria,
                                  @RequestParam(value = "pagina",defaultValue = "1") String pagina,
                                  BuscarProdutoTO buscarProdutoTO, Model model){
        model.addAttribute(PAGE_NAME,PRODUCTS_SHOW_PAGE);
        model.addAttribute(PAGE_FRAGMENT,PRODUCTS_SHOW_FRAGMENT);
        ProdutoPage produtoPage;

        buscarProdutoTO.setSize(quantidadeDeProdutoPorPagina);
        buscarProdutoTO.setPage(pagina);
        buscarProdutoTO.setCategoria(categoria);

        if ("".equalsIgnoreCase(categoria)){
            produtoPage = produtoService.buscarProdutos(buscarProdutoTO);
        }else {
            produtoPage = produtoService.buscarProdutos(buscarProdutoTO);
            buscarProdutoTO.setPage(pagina);
        }

        Page page = criarPagina(buscarProdutoTO.getPage(), produtoPage);


        List<ProdutoView> produtosViews = criarProdutoDeVisualizacao(produtoPage);

        model.addAttribute("produtoPage",produtosViews);
        model.addAttribute("produtos",produtoPage);
        model.addAttribute("page",page);
        model.addAttribute("nomeProcurado",buscarProdutoTO.getNome());
        model.addAttribute("categoriaProcurada",categoria);

        prepararCidadesECategorias(model);


        return INDEX;
    }

    private List<ProdutoView> criarProdutoDeVisualizacao(ProdutoPage produtoPage) {

        List<ProdutoView> produtosViews = new ArrayList<>();

        for (Produto produto : produtoPage.getProdutos()) {

            ProdutoView produtoView = new ProdutoView();
            boolean aindaTemCategoriaParaBuscar=true;
            produtoView.setProduto(produto);
            produtoView.getCategorias().add(produto.getCategoria());
            Categoria categoria= produto.getCategoria();

            do{

                categoria = categoriaService.buscarPaiPeloFilho(String.valueOf(Optional.of(categoria).get().getId()));
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

    @RequestMapping(value = "/{pageNumber}")
    public String showProductsPage(@PathVariable String pageNumber,
                                   @RequestParam(value = "nomeProduto",defaultValue = "") String nomeProduto ,
                                   @RequestParam(value = "categoria",defaultValue = "") String categoria ,
                                   Model model){

        model.addAttribute(PAGE_NAME,PRODUCTS_SHOW_PAGE);
        model.addAttribute(PAGE_FRAGMENT,PRODUCTS_SHOW_FRAGMENT);
        ProdutoPage produtos;
        BuscarProdutoTO buscarProdutoTO = new BuscarProdutoTO();
        buscarProdutoTO.setNome(nomeProduto);
        buscarProdutoTO.setPage(pageNumber);
        buscarProdutoTO.setSize(quantidadeDeProdutoPorPagina);
        buscarProdutoTO.setCategoria(categoria);

        if ("".equalsIgnoreCase(categoria)){
            produtos = produtoService.buscarProdutos(buscarProdutoTO);
        }else {
            produtos = produtoService.buscarProdutos(buscarProdutoTO);
        }


        Page page = criarPagina(pageNumber, produtos);


        model.addAttribute("produtoPage",produtos);
        model.addAttribute("page",page);

        prepararCidadesECategorias(model);

        return "fragments/" + PAGE_LISTA_PRODUTOS + " :: " + FRAGMENT_LISTA_PRODUTOS;
    }

    private void prepararCidadesECategorias(Model model) {
        List<Categoria> categorias = categoriaService.buscarCategoriasPrincipais();
        List<String> cidades = enderecoService.cidadesEmLojasCadastradas();
        model.addAttribute("categorias",categorias);
        model.addAttribute("cidades",cidades);
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
