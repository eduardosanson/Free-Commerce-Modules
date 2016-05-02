package com.free.commerce.service;

import com.free.commerce.entity.Cliente;
import com.free.commerce.entity.Endereco;
import com.free.commerce.entity.Enums.Role;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.UserLogin;
import com.free.commerce.repository.ClienteRepository;
import com.free.commerce.repository.UserRepository;
import com.free.commerce.service.interfaces.ClienteService;
import com.free.commerce.to.CadastrarClienteTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eduardosanson on 05/03/16.
 */
@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private UserRepository userRepository;

    private static final Logger LOGGER = Logger.getLogger(CategoriaServiceImpl.class);

    @Override
    public Cliente realizarCadastroCompleto(CadastrarClienteTO cadastrarClienteTO) {

        Cliente cliente = null;
        Endereco endereco = criaEndereco(cadastrarClienteTO);
        cliente = criarCliente(cadastrarClienteTO);
        UserLogin login = criarLogin(cadastrarClienteTO);

        if (endereco.getCep()!=null && "".equalsIgnoreCase(endereco.getCep())){
            cliente.setEndereco(endereco);
        }
        cliente.setUserLogin(login);


        LOGGER.info("Iniciando cadastro de Cliente");

        try {

            cliente = repository.save(cliente);
            login.setCliente(cliente);
            userRepository.save(login);


        }catch (Exception e){
            e.printStackTrace();
            return cliente;

        }

        return cliente;
    }

    @Override
    public Cliente recuperarProID(Long id) {
        return repository.findOne(id);
    }

    private UserLogin criarLogin(CadastrarClienteTO cadastrarClienteTO) {
        UserLogin userLogin = new UserLogin();
        userLogin.setLogin(cadastrarClienteTO.getLogin());
        userLogin.setSenha(cadastrarClienteTO.getSenha());
        userLogin.setPermissao(Role.CLIENT);
        userLogin = userRepository.save(userLogin);
        return userLogin;
    }

    private Cliente criarCliente(CadastrarClienteTO cadastrarClienteTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(cadastrarClienteTO.getNome());
        cliente.setCpf(cadastrarClienteTO.getCpf());
        cliente.setTelefone(cadastrarClienteTO.getTelefone());
        cliente.setSobrenome(cadastrarClienteTO.getSobreNome());
        return cliente;
    }


    private Endereco criaEndereco(CadastrarClienteTO cadastrarClienteTO) {
        Endereco endereco = new Endereco();
        endereco.setCep(cadastrarClienteTO.getCep());
        endereco.setBairro(cadastrarClienteTO.getBairro());
        endereco.setCidade(cadastrarClienteTO.getCidade());
        endereco.setComplemento(cadastrarClienteTO.getComplemento());
        endereco.setNome(cadastrarClienteTO.getNomeDaRua());
        endereco.setNumero(cadastrarClienteTO.getNumero());
        return endereco;
    }



}

