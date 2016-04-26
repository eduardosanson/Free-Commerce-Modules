package com.br.free.commerce.to;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by pc on 21/03/2016.
 */
public class ProdutoTO {


    @NotBlank
    private String nome;

    @NotBlank
    private String preco;

    private String descricaoTetcnica;

    private String descricao;

    private String categoriaId;

    public String getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
    }

    private MultipartFile fotoPrincipal;

    private MultipartFile file;

    private MultipartFile file2;

    private MultipartFile file3;

    public MultipartFile getFotoPrincipal() {
        return fotoPrincipal;
    }

    public void setFotoPrincipal(MultipartFile fotoPrincipal) {
        this.fotoPrincipal = fotoPrincipal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricaoTetcnica() {
        return descricaoTetcnica;
    }

    public void setDescricaoTetcnica(String descricaoTetcnica) {
        this.descricaoTetcnica = descricaoTetcnica;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile2() {
        return file2;
    }

    public void setFile2(MultipartFile file2) {
        this.file2 = file2;
    }

    public MultipartFile getFile3() {
        return file3;
    }

    public void setFile3(MultipartFile file3) {
        this.file3 = file3;
    }
}
