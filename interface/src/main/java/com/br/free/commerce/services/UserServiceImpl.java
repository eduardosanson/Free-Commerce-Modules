package com.br.free.commerce.services;

import com.br.free.commerce.services.Interface.LoginService;
import com.free.commerce.entity.UserLogin;
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
    public UserLogin recuperarPorEmail(String email) {
        loginService = new RestTemplate();
        UserLogin userLogin;

        try{
            System.out.println("http://localhost:8090/v1/loja?login="+ email);
            userLogin = loginService.getForObject("http://localhost:8090/v1/loja?login="+ email,UserLogin.class);
            System.out.println(userLogin);

        }catch (HttpClientErrorException e){
            e.printStackTrace();
            return null;
        }

        return userLogin;
    }
}
