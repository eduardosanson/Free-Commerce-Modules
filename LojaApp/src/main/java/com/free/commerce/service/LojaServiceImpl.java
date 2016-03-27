package com.free.commerce.service;

import com.free.commerce.entity.Endereco;
import com.free.commerce.entity.Enums.Role;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.UserLogin;
import com.free.commerce.repository.EnderecoRepository;
import com.free.commerce.repository.LojaRepository;
import com.free.commerce.repository.UserRepository;
import com.free.commerce.response.CadastroResponse;
import com.free.commerce.service.interfaces.*;
import com.free.commerce.to.StoreForm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eduardosanson on 05/03/16.
 */
@Service
public class LojaServiceImpl implements LojaService {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LojaRepository repository;

    private static final Logger logger = Logger.getLogger(LojaServiceImpl.class);

    @Override
    public Loja realizarCadastroCompleto(StoreForm storeForm) {
        CadastroResponse response = new CadastroResponse();
        Endereco endereco = criaEndereco(storeForm);
        Loja loja = criarLoja(storeForm);
        UserLogin login = criarLogin(storeForm);
        loja.setEndereco(endereco);
        loja.setUserLogin(login);


        logger.info("Iniciando cadastro de Cliente");

        try {

            loja = repository.save(loja);

        }catch (Exception e){

            e.printStackTrace();

        }
        return loja;
    }

    @Override
    public Loja login(UserLogin userLogin) {
        Loja loja;
        loja = repository.recuperarLojaPeloUserLogin(userLogin);

        System.out.print(loja);
        return loja;
    }

    @Override
    public Loja recuperarPorId(Long id) {
        return repository.findOne(id);
    }

    private UserLogin criarLogin(StoreForm storeForm) {
        UserLogin login = new UserLogin();
        login.setLogin(storeForm.getEmail());
        login.setSenha(storeForm.getPassword());
        login.setPermissao(Role.STORE);
        return login;
    }

    private Loja criarLoja(StoreForm storeForm) {
        Loja loja = new Loja();
        loja.setNome(storeForm.getNomeDaEmpresa());
        loja.setNomeEmpresa(storeForm.getNomeJuridico());
        loja.setCnpjOuCpf(storeForm.getCpfOuCnpj());
        loja.setEmail(storeForm.getEmail());
        loja.setTelefone(storeForm.getTelefone());
        return loja;
    }

    private Endereco criaEndereco(StoreForm storeForm) {
        Endereco endereco = new Endereco();
        endereco.setCep(storeForm.getCep());
        endereco.setBairro(storeForm.getBairro());
        endereco.setCidade(storeForm.getCidade());
        endereco.setComplemento(storeForm.getComplemento());
        endereco.setNome(storeForm.getNomeDaRua());
        endereco.setPais(storeForm.getPais());
        endereco.setNumero(storeForm.getNumero());
        return endereco;
    }


    }
