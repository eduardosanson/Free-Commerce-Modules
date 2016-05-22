package com.free.commerce.service.interfaces;

import com.free.commerce.entity.Imagem;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.UserLogin;
import com.free.commerce.to.CadastrarLojaTO;

/**
 * Created by eduardosanson on 05/03/16.
 */
public interface LojaService {

    Loja realizarCadastroCompleto(CadastrarLojaTO cadastrarLojaTO);

    Loja login(UserLogin userLogin);

    Loja recuperarPorId(Long id);

    Loja buscarLojasPendentesDeAutorizacao();

    Loja recuperarPorIdDeProduto(String id);

    public void alterarPerfil(Long clienteId, Imagem imagem);
}
