package com.br.free.commerce.services.Interface;

import com.br.free.commerce.to.BuscarClienteTO;
import com.br.free.commerce.to.CadastrarClienteTO;
import com.br.free.commerce.to.FinalizarCadastroTO;
import com.free.commerce.entity.Cliente;
import com.free.commerce.entity.UserLogin;

/**
 * Created by pc on 01/05/2016.
 */
public interface ClienteService {

    UserLogin cadastrarCliente(CadastrarClienteTO cadastrarClienteTO);

    Cliente concluirCadastro(FinalizarCadastroTO cadastroTO);

    Cliente buscarCliente(BuscarClienteTO buscarClienteTO);
}
