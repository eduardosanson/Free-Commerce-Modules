package com.free.commerce.service;

import com.free.commerce.entity.Enums.Role;
import com.free.commerce.entity.UserLogin;
import com.free.commerce.repository.UserRepository;
import com.free.commerce.service.interfaces.UserLoginService;
import com.free.commerce.to.UserLoginTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pc on 11/04/2016.
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserLogin inserirLoginAdm(UserLoginTO userLoginTO) {
        UserLogin userLogin = criarLogin(userLoginTO);

        repository.save(userLogin);
        return null;
    }

    private UserLogin criarLogin(UserLoginTO userLoginTO){
        UserLogin userLogin = new UserLogin();
        userLogin.setSenha(userLoginTO.getSenha());
        userLogin.setLogin(userLoginTO.getLogin());
        userLogin.setPermissao(Role.ADMIN);
        userLogin.setAdministrador(userLoginTO.getAdministrador());
        return userLogin;
    }
}
