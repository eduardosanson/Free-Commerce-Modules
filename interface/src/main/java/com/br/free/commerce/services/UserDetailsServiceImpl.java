package com.br.free.commerce.services;

import com.br.free.commerce.entity.CustomUserDetails;
import com.br.free.commerce.exception.RegraDeNegocioException;
import com.free.commerce.entity.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by eduardo.sanson on 17/03/2016.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws AuthenticationServiceException {

        Optional<UserLogin> userLogin = null;
        try {
            userLogin = Optional.ofNullable(userService.recuperarPorEmail(username));
        } catch (RegraDeNegocioException e) {
            e.printStackTrace();
        }

        if(!userLogin.isPresent()){
            throw new AuthenticationServiceException("Usuário não encontrado");
        }
        UserDetails user = createUserDetails(userLogin.get());

//        Function<UserLogin, UserDetails> criarUsuario = this::createUserDetails;
//        Function<UserLogin, UserDetails> criarUsuario = u -> createUserDetails(u);
//        processarUsuarioGenerico(criarUsuario, userLogin.get());
//        UserDetails user = createUserDetails(userLogin.get());

        return user;
    }

    private UserDetails createUserDetails(UserLogin usuario) {
        List<GrantedAuthority> listGrantAuthority = new ArrayList<GrantedAuthority>();
        listGrantAuthority.add(new SimpleGrantedAuthority("ROLE_" + usuario.getPermissao()));
        UserDetails user = new CustomUserDetails(usuario, passwordEncoder,listGrantAuthority);
        return user;
    }


    private UserDetails processarUsuarioGenerico(Function<UserLogin, UserDetails> metodo, UserLogin userLogin) {
        UserDetails userDetails = metodo.apply(userLogin);
        //grava no banco
        return userDetails;
    }
}


