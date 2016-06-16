package com.br.free.commerce.services;

import com.br.free.commerce.exception.RegraDeNegocioException;
import com.br.free.commerce.exception.enuns.RegraDeNegocioEnum;
import com.br.free.commerce.services.Interface.LoginService;
import com.free.commerce.entity.UserLogin;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by eduardo.sanson on 17/03/2016.
 */
@Service
public class UserServiceImpl implements LoginService{

    private RestTemplate loginService;

    @Override
    public UserLogin recuperarPorEmail(String email) throws RegraDeNegocioException {
        loginService = new RestTemplate();
        UserLogin userLogin = null;

        try{
            System.out.println("http://lojacommerce.herokuapp.com/loja?login="+ email);
            userLogin = loginService.getForObject("http://lojacommerce.herokuapp.com/loja?login="+ email,UserLogin.class);
            System.out.println(userLogin);

        }catch (Exception e){
            if (e.getMessage().contains(HttpStatus.NOT_FOUND.toString())){
             throw new RegraDeNegocioException(RegraDeNegocioEnum.NAO_ENCONTRADO);
            }
        }

        return userLogin;
    }
}
