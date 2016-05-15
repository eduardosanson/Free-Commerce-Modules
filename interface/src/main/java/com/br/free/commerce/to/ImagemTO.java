package com.br.free.commerce.to;

import com.free.commerce.entity.Imagem;

/**
 * Created by pc on 15/05/2016.
 */
public class ImagemTO {
    private Imagem imagem;

    private long produtoId;

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(long produtoId) {
        this.produtoId = produtoId;
    }
}
