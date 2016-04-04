package com.free.commerce.service.interfaces;

import com.free.commerce.entity.Loja;
import com.free.commerce.entity.UserLogin;
import com.free.commerce.to.StoreForm;

/**
 * Created by eduardosanson on 05/03/16.
 */
public interface LojaService {

    Loja realizarCadastroCompleto(StoreForm storeForm);

    Loja login(UserLogin userLogin);

    Loja recuperarPorId(Long id);

    Loja buscarLojasPendentesDeAutorizacao();
}
