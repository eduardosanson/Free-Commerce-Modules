package com.br.free.commerce.services;

import com.br.free.commerce.InterfaceApplication;
import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.to.ProdutoCadastroTo;
import com.br.free.commerce.to.ProdutoTO;
import com.free.commerce.entity.Foto;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.Produto;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * Created by pc on 21/03/2016.
 */
@Service
public class ProdutoServiceImpl implements ProdutoService{

    private RestTemplate restTemplate = new RestTemplate();

    private static String url ="http://";
    private static String ip ="localhost";
    private static String port =":8090";
    private static String service="/v1/produto/";

    private static Logger logger = Logger.getLogger(ProdutoServiceImpl.class);

    @Override
    public Produto cadastrarProduto(Loja loja, ProdutoTO produtoTO) {
        ProdutoCadastroTo produtoCadastroTo;

        produtoCadastroTo = criarProdutoDeCadastroTo(loja,produtoTO);

        return cadastrar(loja,produtoCadastroTo);
    }

    private ProdutoCadastroTo criarProdutoDeCadastroTo(Loja loja,ProdutoTO produtoTO) {
        ProdutoCadastroTo produtoCadastroTo = new ProdutoCadastroTo();
        produtoCadastroTo.setPreco(produtoTO.getPreco());
        produtoCadastroTo.setDescricao(produtoTO.getDescricao());
        produtoCadastroTo.setDescricaoTetcnica(produtoTO.getDescricao());

        List<Foto> fotos = new ArrayList<Foto>();
        List<MultipartFile> files = new ArrayList<MultipartFile>();
        files.add(produtoTO.getFile());
        files.add(produtoTO.getFile2());
        files.add(produtoTO.getFile3());

        for (MultipartFile f: files) {
            String nomeDoArquivo = criarNome();
            fotos.add(imageProcessor(String.valueOf(loja.getId()),nomeDoArquivo,f));

        }


        return produtoCadastroTo;
    }

    private Produto cadastrar(Loja loja,ProdutoCadastroTo produtoCadastroTo) {
        try {
            logger.info("Iniciando cadastro de Produto");
            String requestUrl = url+ip+port+service+loja.getId();
            Map<String,ProdutoCadastroTo> produtoCadastroToMap = new HashMap<String,ProdutoCadastroTo>();
            produtoCadastroToMap.put("ProdutoTO",produtoCadastroTo);

            Produto produto = restTemplate.postForObject(requestUrl,produtoCadastroToMap,Produto.class,produtoCadastroTo);
            logger.info("produto cadastrado com sucesso: " + produto.getId());
            return produto;

        }catch (Exception e){
            e.printStackTrace();
            logger.info("erro ao cadastrar: Motivo: " + e.getMessage());
            return null;
        }
    }

    private Foto imageProcessor(String nomePasta, String nomeArquivo, MultipartFile file) {
        String pathCompleto = InterfaceApplication.ROOT + "/" +nomePasta +"/"+nomeArquivo;
        Foto foto = new Foto();

        if (!file.isEmpty()) {
            try {
                File fileRoot = new File(pathCompleto);
                FileOutputStream outputStream = new FileOutputStream(fileRoot);
                BufferedOutputStream stream = new BufferedOutputStream(outputStream);

                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
                foto.setRegistrado(new Date());
                foto.setNomeDoArquivo(nomeArquivo);
                foto.setPath(pathCompleto);
                return foto;
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            return null;
        }
    }

    private String criarNome(){
        Random r = new Random(100000);

        return String.valueOf(r.nextInt());
    }

}
