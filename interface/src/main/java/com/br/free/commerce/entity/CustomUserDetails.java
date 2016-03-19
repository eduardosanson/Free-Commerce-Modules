package com.br.free.commerce.entity;

import com.free.commerce.entity.UserLogin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

/**
 * Created by eduardosanson on 05/03/16.
 */

public class CustomUserDetails extends User{

    private UserLogin userlogin;

    public CustomUserDetails(UserLogin username, PasswordEncoder encoder, Collection<? extends GrantedAuthority> authorities) {
        super(username.getLogin(), encoder.encode(username.getSenha()), authorities);
        this.userlogin = username;
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
}
