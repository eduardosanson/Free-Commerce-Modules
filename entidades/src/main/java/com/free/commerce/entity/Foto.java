package com.free.commerce.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pc on 20/03/2016.
 */
@Entity
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;

    private String nomeDoArquivo;

    private Date registrado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNomeDoArquivo() {
        return nomeDoArquivo;
    }

    public void setNomeDoArquivo(String nomeDoArquivo) {
        this.nomeDoArquivo = nomeDoArquivo;
    }

    public Date getRegistrado() {
        return registrado;
    }

    public void setRegistrado(Date registrado) {
        this.registrado = registrado;
    }
}
