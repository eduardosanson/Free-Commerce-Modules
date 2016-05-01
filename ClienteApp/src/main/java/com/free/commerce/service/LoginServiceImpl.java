package com.free.commerce.service;

import com.free.commerce.entity.Loja;
import com.free.commerce.entity.UserLogin;
import com.free.commerce.repository.UserRepository;
import com.free.commerce.service.interfaces.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eduardosanson on 05/03/16.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserRepository repository;

    @Override
    public void criarLogin(UserLogin login) {

    }

    @Override
    public UserLogin recuperarPorEmail(String email) {
        return repository.getLoginPorEmail(email);
    }
}
