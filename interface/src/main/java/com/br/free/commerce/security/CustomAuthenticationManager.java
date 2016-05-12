package com.br.free.commerce.security;

import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.services.UserDetailsServiceImpl;
import com.free.commerce.entity.UserLogin;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eduardo.sanson on 09/05/2016.
 */
@Service
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(username);

        List<GrantedAuthority> listGrantAuthority = new ArrayList<GrantedAuthority>();
        listGrantAuthority.add(new SimpleGrantedAuthority("ROLE_" + user.getUserlogin().getPermissao()));

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, password, listGrantAuthority);


        return usernamePasswordAuthenticationToken;
    }




}
