package com.br.free.commerce.entity;

import com.free.commerce.entity.Imagem;
import com.free.commerce.entity.UserLogin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by eduardosanson on 05/03/16.
 */

public class CustomUserDetails extends User{

    private UserLogin userlogin;

    private Imagem perfilDefault;

    private String pathFotoPerfil ="";

    public CustomUserDetails(UserLogin username, PasswordEncoder encoder, Collection<? extends GrantedAuthority> authorities) {
        super(username.getLogin(), encoder.encode(username.getSenha()), authorities);
        this.userlogin = username;
        perfilDefault = new Imagem();
        perfilDefault.setNomeDoArquivo("Image-defaul");
        perfilDefault.setPath("/img/people/user.png");

    }

    @Override
    public String toString() {
        return "CustomUserDetails{" +
                "userlogin=" + userlogin +
                '}';
    }

    public UserLogin getUserlogin() {
        return userlogin;
    }

    public void setUserlogin(UserLogin userlogin) {
        this.userlogin = userlogin;
    }

    public String getPathFotoPerfil(){

        Optional.ofNullable(Optional.ofNullable(userlogin).get().getCliente())
                .ifPresent(a->
                        pathFotoPerfil = Optional
                                .ofNullable(a.getPerfil())
                        .orElse(perfilDefault).getPath());

        Optional.ofNullable(Optional.ofNullable(userlogin).get().getLoja())
                .ifPresent(b->
                        pathFotoPerfil = Optional.ofNullable(b.getPerfil())
                        .orElse(perfilDefault).getPath());

        Optional.ofNullable(Optional.ofNullable(userlogin).get().getAdministrador())
                .ifPresent(c-> pathFotoPerfil = Optional.ofNullable(c.getPerfil())
                        .orElse(perfilDefault).getPath());

        return pathFotoPerfil.startsWith("/") ? pathFotoPerfil : "/" + pathFotoPerfil;
    }
}
