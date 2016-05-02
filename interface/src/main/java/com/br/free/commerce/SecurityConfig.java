package com.br.free.commerce;

import com.free.commerce.entity.Enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by eduardosanson on 09/03/16.
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    @Autowired
    private UserDetailsService UserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/","/public/**").
                permitAll()
        .and().
                authorizeRequests().
                antMatchers("/store/menu/**").hasAnyAuthority("ROLE_" + Role.STORE)
        .and().
                authorizeRequests().
                antMatchers("/admin/menu/**").hasAnyAuthority("ROLE_" + Role.ADMIN)
        .and().
                authorizeRequests().
                antMatchers("/cliente/menu/**").hasAnyAuthority("ROLE_" + Role.CLIENT)
        .and().
                formLogin().
                loginPage("/public/login").successHandler(authenticationSuccessHandler)
        .and().
                exceptionHandling().accessDeniedPage("/");

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(UserDetailsService).passwordEncoder(passwordEncoder);
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

}
