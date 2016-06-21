package com.br.free.commerce.services;

import com.br.free.commerce.config.ProdutoSettings;
import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.to.BuscarProdutoTO;
import com.br.free.commerce.to.ProdutoCadastroTo;
import com.br.free.commerce.to.ProdutoPage;
import com.br.free.commerce.to.ProdutoTO;
import com.br.free.commerce.util.ImagemProcessor;
import com.free.commerce.entity.Imagem;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.Produto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * Created by pc on 21/03/2016.
 */
@Service
public class ProdutoServiceImpl implements ProdutoService {

    private RestTemplate restTemplate = new RestTemplate();

    private static String PATH_DEFAULT_DE_PRODUTO = "img/products/";
    private static String FOTO_NOME_DEFAULT = "semfoto.jpg";
    private static String PASTA_DAS_LOJAS = "lojas/";

    @Autowired
    private ProdutoSettings produtoControlerApiConfig;

    private static Logger logger = Logger.getLogger(ProdutoServiceImpl.class);

    @Override
    public Produto cadastrarProduto(Loja loja, ProdutoTO produtoTO) {
        ProdutoCadastroTo produtoCadastroTo;

        produtoCadastroTo = criarProdutoDeCadastroTo(loja, produtoTO);

        return cadastrar(loja, produtoCadastroTo);
    }

    @Override
    public ProdutoPage recuperarProdutosDeLoja(Loja loja, String page, String size) {
        Integer pageIdice = new Integer(page)- 1;
        Integer pageSize = new Integer(size)- 1;

        produtoControlerApiConfig
                .buildURL()
                .paramSize(pageSize.toString())
                .paramPage(pageIdice.toString())
                .paramLojaId(loja.getId().toString())
                .paramQtdMaior("-1")
                .paramStatus("All");


        logger.info( produtoControlerApiConfig.callBuildURL());
        ProdutoPage produtoPage = null;
        try {

            produtoPage = restTemplate.getForObject(produtoControlerApiConfig.callBuildURL(), ProdutoPage.class);

            logger.info(produtoPage);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return produtoPage;
    }

    @Override
    public Produto buscarProdutoPorId(String id) {
        Produto produto = null;
        try {
            logger.info("chamando buscar produto: " + produtoControlerApiConfig.buscarProdutoPorId(id));

            produto = restTemplate.getForObject(produtoControlerApiConfig.buscarProdutoPorId(id), Produto.class);

            logger.info("sucesso ao buscarProduto");

        } catch (Exception e) {
            logger.error("Erro ao buscar produto: " + e.getMessage());
        } finally {

            return produto;
        }

    }

    @Override
    public ProdutoPage buscarProdutos(BuscarProdutoTO buscarProdutoTO) {
        Integer pageIdice = new Integer(buscarProdutoTO.getPage())-1;
        Integer pageSize = new Integer(buscarProdutoTO.getSize());

        produtoControlerApiConfig
                .buildURL()
                .paramSize(pageSize.toString())
                .paramPage(pageIdice.toString())
                .paramProdutoNome(buscarProdutoTO.getNome())
                .paramNovo(buscarProdutoTO.getNovo())
                .paramOrderBy(buscarProdutoTO.getOrderBy())
                .paramCategoria(buscarProdutoTO.getCategoria())
                .paramCidade(buscarProdutoTO.getCidade());


        logger.info(produtoControlerApiConfig.callBuildURL());

        ProdutoPage produtoPage = null;

        produtoPage = restTemplate.getForObject(produtoControlerApiConfig.callBuildURL(), ProdutoPage.class);

        logger.info(produtoPage);

        return produtoPage;
    }



    @Override
    public void alterarProduto(Produto produto) {

        try {

            restTemplate.put(produtoControlerApiConfig.getUrlComConexto(),produto, Produto.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void associarImagem(List<MultipartFile> files, String produtoId) {

        Produto produto = buscarProdutoPorId(produtoId);

        List<Imagem> imagens = new ArrayList<>();

        for (MultipartFile file: files ) {
            Imagem imagem;
            imagem = ImagemProcessor.processor(PASTA_DAS_LOJAS+produtoId,criarNomeCriarNomeDeImagem(),file);
            imagens.add(imagem);
        }

        produto.getImagens().addAll(imagens);

        alterarProduto(produto);

    }

    @Override
    public void deletarImagem(long produtoId, long imagemId) {
        try {

            restTemplate.delete(produtoControlerApiConfig.getUrlComConexto()+"?produtoId="+produtoId + "&imagemId="+imagemId);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private ProdutoCadastroTo criarProdutoDeCadastroTo(Loja loja, ProdutoTO produtoTO) {
        String nomeDaPastaDaImagem = "loja/" + String.valueOf(loja.getId());

        ProdutoCadastroTo produtoCadastroTo = new ProdutoCadastroTo();
        produtoCadastroTo.setPreco(Double.parseDouble(produtoTO.getPreco()));
        produtoCadastroTo.setDescricao(produtoTO.getDescricao());
        produtoCadastroTo.setDescricaoTetcnica(produtoTO.getDescricao());
        produtoCadastroTo.setNome(produtoTO.getNome());
        produtoCadastroTo.setCategoriaId(produtoTO.getCategoriaId());
        produtoCadastroTo.setNovo(produtoTO.isNovo());
        produtoCadastroTo.setQuantidade(produtoTO.getQuantidade());

        return produtoCadastroTo;
    }

    private Produto cadastrar(Loja loja, ProdutoCadastroTo produtoCadastroTo) {
        try {
            logger.info("Iniciando cadastro de Produto Url: " +  produtoControlerApiConfig.
                                                        cadastrarProduto(loja.getId().toString()));

            Map<String, ProdutoCadastroTo> produtoCadastroToMap = new HashMap<String, ProdutoCadastroTo>();
            produtoCadastroToMap.put("ProdutoTO", produtoCadastroTo);

            Produto produto = restTemplate.postForObject(
                    produtoControlerApiConfig.cadastrarProduto(loja.getId().toString()),
                    produtoCadastroTo, Produto.class, produtoCadastroToMap);
            logger.info("produto cadastrado com sucesso: " + produto.getId());
            return produto;

        } catch (Exception e) {
            logger.info("erro ao cadastrar: Motivo: " + e.getMessage());
            return null;
        }
    }



    private String criarNomeCriarNomeDeImagem() {
        Random r = new Random();

        int valorInteiro = r.nextInt(10000000);
        String valor = null;

        if (valorInteiro < 0) {
            valorInteiro = valorInteiro * -1;
        }

        return String.valueOf(valorInteiro);
    }

}
