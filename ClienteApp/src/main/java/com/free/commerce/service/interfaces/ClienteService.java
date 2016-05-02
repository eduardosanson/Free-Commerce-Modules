package com.free.commerce.service.interfaces;

import com.free.commerce.entity.Cliente;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.UserLogin;
import com.free.commerce.to.CadastrarClienteTO;
import com.free.commerce.to.StoreForm;

/**
 * Created by eduardosanson on 05/03/16.
 */
public interface ClienteService {

    Cliente realizarCadastroCompleto(CadastrarClienteTO cadastrarClienteTO);

    Cliente recuperarProID(Long id);


}