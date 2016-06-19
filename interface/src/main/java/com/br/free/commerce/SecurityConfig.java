package com.br.free.commerce;

import com.br.free.commerce.handler.AuthenticationSuccessHandlerImpl;
import com.br.free.commerce.handler.CustomAccessDeniedHandler;
import com.br.free.commerce.handler.CustomHttp403ForbiddenEntryPoint;
import com.br.free.commerce.services.UserDetailsServiceImpl;
import com.free.commerce.entity.Enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.PostConstruct;

/**
 * Created by eduardosanson on 09/03/16.
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private CustomHttp403ForbiddenEntryPoint http403ForbiddenEntryPoint;

    @PostConstruct
    public void init(){
        userDetailsService.setPasswordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(http403ForbiddenEntryPoint).accessDeniedHandler(accessDeniedHandler)
         .and().authorizeRequests().
                antMatchers("/","/public/**","/login","/paymentNotifyController/**").
                permitAll()
        .and().
                authorizeRequests().
                antMatchers("/store/menu/**").hasAnyAuthority("ROLE_" + Role.STORE)
        .and().
                authorizeRequests().
                antMatchers("/admin/menu/").hasAnyAuthority("ROLE_" + Role.ADMIN)
        .and().
                authorizeRequests().
                antMatchers("/cliente/menu/**").hasAnyAuthority("ROLE_" + Role.CLIENT)
        .and().
                formLogin().
                loginPage("/public/login").successHandler(authenticationSuccessHandler)
        .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/public/login/logout")).logoutSuccessUrl("/").
        and().csrf().ignoringAntMatchers("/produto/menu/upload","/store/menu/imagem/**","/paymentNotifyController/**");

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
        handler.setUseReferer(true);
        return handler;
    }


}
