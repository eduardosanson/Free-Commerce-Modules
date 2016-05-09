package com.br.free.commerce.services.Interface;


import com.br.free.commerce.exception.RegraDeNegocioException;
import com.free.commerce.entity.UserLogin;

/**
 * Created by eduardosanson on 05/03/16.
 */
public interface LoginService {

    UserLogin recuperarPorEmail(String email) throws RegraDeNegocioException;
}
