package com.free.commerce.service.interfaces;

import com.free.commerce.entity.Administrador;
import com.free.commerce.entity.Imagem;
import com.free.commerce.to.AdministradorTO;

/**
 * Created by pc on 11/04/2016.
 */
public interface AdminService {

    Administrador criarAdm(AdministradorTO administradorTO);

    void alterarPerfil(Long adminId, Imagem imagem);

    Administrador recuperarPorId(Long id);


    Administrador atualizar(Administrador administrador);
}
