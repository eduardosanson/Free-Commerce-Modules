package com.free.commerce.service.interfaces;

import com.free.commerce.entity.Enums.Administrador;
import com.free.commerce.to.AdministradorTO;

/**
 * Created by pc on 11/04/2016.
 */
public interface AdminService {

    Administrador criarAdm(AdministradorTO administradorTO);
}
