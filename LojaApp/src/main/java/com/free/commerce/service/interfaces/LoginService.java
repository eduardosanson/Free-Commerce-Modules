package com.free.commerce.service.interfaces;

import com.free.commerce.entity.Loja;
import com.free.commerce.entity.UserLogin;

/**
 * Created by eduardosanson on 05/03/16.
 */
public interface LoginService {

    void criarLogin(UserLogin login);

    UserLogin recuperarPorEmail(String email);

    void alterarLogin(UserLogin userLogin);
}
