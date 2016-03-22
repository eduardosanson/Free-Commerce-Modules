package com.br.free.commerce.services;

import com.br.free.commerce.InterfaceApplication;
import com.br.free.commerce.services.Interface.ProdutoService;
import com.br.free.commerce.to.ProdutoCadastroTo;
import com.br.free.commerce.to.ProdutoTO;
import com.free.commerce.entity.Foto;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.Produto;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

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

    @Override
    public Produto cadastrarProduto(Loja loja, ProdutoTO produtoTO) {
        ProdutoCadastroTo produtoCadastroTo;

        produtoCadastroTo = criarProdutoDeCadastroTo(produtoTO);
        cadastrar(loja,produtoCadastroTo);
        return null;
    }

    private ProdutoCadastroTo criarProdutoDeCadastroTo(ProdutoTO produtoTO) {

        return null;
    }

    private void cadastrar(Loja loja,ProdutoCadastroTo produtoCadastroTo) {
        try {
            String requestUrl = url+ip+port+service+loja.getId();

            Produto produto = restTemplate.postForObject(requestUrl,)

        }catch (Exception e){
            e.printStackTrace();
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
}
