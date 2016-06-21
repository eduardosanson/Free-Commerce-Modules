package com.br.free.commerce.services.Interface;

import com.br.free.commerce.to.BuscarClienteTO;
import com.br.free.commerce.to.CadastrarClienteTO;
import com.br.free.commerce.to.FinalizarCadastroTO;
import com.free.commerce.entity.Cliente;
import com.free.commerce.entity.Imagem;
import com.free.commerce.entity.Pedido;
import com.free.commerce.entity.UserLogin;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by pc on 01/05/2016.
 */
public interface ClienteService {

    UserLogin cadastrarCliente(CadastrarClienteTO cadastrarClienteTO);

    void alterarDadosDeCliente(Cliente cliente);

    Cliente concluirCadastro(FinalizarCadastroTO cadastroTO);

    Cliente buscarCliente(Long clienteId);

    List<Pedido> meusPedidos(Long clienteId);

    void alterarPerfil(Long clienteId, MultipartFile file);

    Cliente buscarLojaPorCpfOuCnpj(String cpf);
}
