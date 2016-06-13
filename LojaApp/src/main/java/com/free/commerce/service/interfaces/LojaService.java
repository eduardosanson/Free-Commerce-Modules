package com.free.commerce.service.interfaces;

import com.free.commerce.entity.*;
import com.free.commerce.to.CadastrarLojaTO;

import java.util.List;

/**
 * Created by eduardosanson on 05/03/16.
 */
public interface LojaService {

    Loja realizarCadastroCompleto(CadastrarLojaTO cadastrarLojaTO);

    Loja login(UserLogin userLogin);

    Loja recuperarPorId(Long id);

    Loja recuperarPorCpfOuCnpj(String cpfOuCnpj);

    Loja buscarLojasPendentesDeAutorizacao();

    Loja recuperarPorIdDeProduto(String id);

    void alterarPerfil(Long clienteId, Imagem imagem);

    Loja atualizar(Loja loja);

}
