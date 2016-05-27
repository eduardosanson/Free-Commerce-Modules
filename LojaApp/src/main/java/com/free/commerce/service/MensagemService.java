package com.free.commerce.service;

import com.free.commerce.entity.Mensagem;
import com.free.commerce.entity.Produto;
import com.free.commerce.repository.MensagemRepositroy;
import com.free.commerce.service.interfaces.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sun.misc.resources.Messages_sv;

import java.util.Date;
import java.util.List;

/**
 * Created by pc on 24/05/2016.
 */
@Service
public class MensagemService {

    @Autowired
    private MensagemRepositroy mensagemRepositroy;

    public Mensagem salvaMensagem(Mensagem mensagem){

        return mensagemRepositroy.save(mensagem);
    }

    public void deletarMensagem(Long id){
        Mensagem m = mensagemRepositroy.findOne(id);
        mensagemRepositroy.delete(m);
    }

    public List<Mensagem> buscarPorProduto(Long id){

        return mensagemRepositroy.buscarPorProduto(id);
    }

    public Mensagem buscarPorId(Long id){

        return mensagemRepositroy.findOne(id);
    }

    public List<Mensagem> buscarPorProdutoPageando(Long id, Pageable page){

        return mensagemRepositroy.buscarPorProdutoPAgeando(id,page);
    }

}
