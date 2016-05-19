package com.free.commerce.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by pc on 20/03/2016.
 */
@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String path;

    private String nomeDoArquivo;

    private Date registrado;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Imagem){
            Imagem i = (Imagem) obj;
            if (i.getId()==i.getId()){
                return true;
            }else {
                return  false;
            }
        }else {
            return false;
        }
    }
}
