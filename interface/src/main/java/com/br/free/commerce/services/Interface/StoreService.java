package com.br.free.commerce.services.Interface;

import com.br.free.commerce.to.StoreForm;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.UserLogin;

/**
 * Created by pc on 19/03/2016.
 */
public interface StoreService {

    UserLogin cadastrar(StoreForm form);
}