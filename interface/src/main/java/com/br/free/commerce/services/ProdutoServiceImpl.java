package com.br.free.commerce.services;

import com.br.free.commerce.InterfaceApplication;
import com.br.free.commerce.config.ProdutoSettings;
import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.to.ProdutoCadastroTo;
import com.br.free.commerce.to.ProdutoPage;
import com.br.free.commerce.to.ProdutoTO;
import com.br.free.commerce.util.FileName;
import com.free.commerce.entity.Imagem;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.Produto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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


        logger.info(produtoControlerApiConfig.buscarProdutosPorLojaPaginandoOReTornoUrl(loja.getId().toString(),
                    pageIdice.toString(),pageSize.toString()));
        ProdutoPage produtoPage = null;
        try {

            produtoPage = restTemplate.getForObject(produtoControlerApiConfig.
                                buscarProdutosPorLojaPaginandoOReTornoUrl(loja.getId().toString(),
                                    pageIdice.toString(),pageSize.toString()), ProdutoPage.class);

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
    public ProdutoPage buscarPorNomeParecido(String nome, String pagina, String intemProPagina) {
        Integer pageIdice = new Integer(pagina)-1;
        Integer pageSize = new Integer(intemProPagina);


        logger.info(produtoControlerApiConfig.buscarProdutosPorNomeParecido(
                pageIdice.toString(),pageSize.toString(),nome));
        ProdutoPage produtoPage = null;
        try {

            produtoPage = restTemplate.getForObject(produtoControlerApiConfig.buscarProdutosPorNomeParecido(
                            pageIdice.toString(),pageSize.toString(),nome), ProdutoPage.class);

            logger.info(produtoPage);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return produtoPage;
    }

    @Override
    public void alterarProduto(Produto produto) {

        try {

            restTemplate.put(produtoControlerApiConfig.getUrlProduto(),produto, Produto.class);

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
            imagem = imageProcessor(PASTA_DAS_LOJAS+produtoId,criarNomeCriarNomeDeImagem(),file);
            imagens.add(imagem);
        }

        produto.getImagens().addAll(imagens);

        alterarProduto(produto);

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

    private Imagem imageProcessor(String nomePasta, String nomeArquivo, MultipartFile file) {
        String nomeDaPasta = InterfaceApplication.ROOT + "/" + nomePasta + "/";
        FileName fileName = new FileName(file.getOriginalFilename(), '/', '.');
        String pathCompleto = nomeDaPasta + nomeArquivo + "." + fileName.extension();
        Imagem imagem = new Imagem();

        if (!file.isEmpty()) {
            try {
                File pasta = new File(nomeDaPasta);
                Path path = Paths.get(nomeDaPasta);
                if (Files.notExists(path)) {
                    pasta.mkdirs();
                }

                File fileRoot = new File(pathCompleto);
                FileOutputStream outputStream = new FileOutputStream(fileRoot);
                BufferedOutputStream stream = new BufferedOutputStream(outputStream);

                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
                imagem.setRegistrado(new Date());
                imagem.setNomeDoArquivo(nomeArquivo);
                imagem.setPath(pathCompleto);
                return imagem;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
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
