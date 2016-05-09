package com.br.free.commerce.security;

import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.services.UserDetailsServiceImpl;
import com.free.commerce.entity.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eduardo.sanson on 09/05/2016.
 */
@Service
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(username);

        List<GrantedAuthority> listGrantAuthority = new ArrayList<GrantedAuthority>();
        listGrantAuthority.add(new SimpleGrantedAuthority("ROLE_" + user.getUserlogin().getPermissao()));

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password, listGrantAuthority);
        usernamePasswordAuthenticationToken.setDetails(user);

        return usernamePasswordAuthenticationToken;
    }

    private UserDetails createUserDetails(UserLogin usuario) {
        List<GrantedAuthority> listGrantAuthority = new ArrayList<GrantedAuthority>();
        listGrantAuthority.add(new SimpleGrantedAuthority("ROLE_" + usuario.getPermissao()));
        UserDetails user = new CustomUserDetails(usuario, passwordEncoder,listGrantAuthority);
        return user;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
