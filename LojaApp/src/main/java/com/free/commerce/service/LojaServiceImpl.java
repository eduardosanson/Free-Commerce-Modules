package com.free.commerce.service;

import com.free.commerce.entity.*;
import com.free.commerce.entity.Enums.Role;
import com.free.commerce.repository.EnderecoRepository;
import com.free.commerce.repository.ImagemRepository;
import com.free.commerce.repository.LojaRepository;
import com.free.commerce.repository.UserRepository;
import com.free.commerce.service.interfaces.*;
import com.free.commerce.to.CadastrarLojaTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private AutorizacaoService autorizacaoService;

    @Autowired
    private ImagemRepository imagemRepository;



    private static final Logger logger = Logger.getLogger(LojaServiceImpl.class);

    @Override
    public Loja realizarCadastroCompleto(CadastrarLojaTO cadastrarLojaTO) {
        Endereco endereco = criaEndereco(cadastrarLojaTO);
        Loja loja = criarLoja(cadastrarLojaTO);
        UserLogin login = criarLogin(cadastrarLojaTO);
        loja.setEndereco(endereco);
        loja.setUserLogin(login);
        loja.setRegistrado(new Date());


        logger.info("Iniciando cadastro de Cliente");

        try {

            loja = repository.save(loja);
            autorizacaoService.solicitarAutorizacao(String.valueOf(loja.getId()));

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

    @Override
    public Loja recuperarPorCpfOuCnpj(String cpfOuCnpj) {
        return repository.recuperarLojaPeloCpfOuCnpj(cpfOuCnpj);
    }

    @Override
    public Loja buscarLojasPendentesDeAutorizacao() {
        return null;
    }

    @Override
    public Loja recuperarPorIdDeProduto(String id) {
        Produto produto = null;
        Loja loja = null;

        try{

            produto = produtoService.buscarPorId(Long.parseLong(id));
            if (produto!=null){

                loja = repository.recuperarLojaPeloProduto(produto);
            }
        }catch (Exception e){
            logger.warn(e.getMessage());
        }

        return loja;
    }

    @Override
    public void alterarPerfil(Long lojaId, Imagem imagem) {
        logger.info("Alterando perfil");
        Optional.ofNullable(imagem).ifPresent(n->{
            Loja loja = repository.findOne(lojaId);
            loja.setPerfil(imagemRepository.save(n));
            repository.save(loja);
        });
    }

    @Override
    public Loja atualizar(Loja loja) {
        return repository.save(loja);
    }



    private UserLogin criarLogin(CadastrarLojaTO cadastrarLojaTO) {
        UserLogin login = new UserLogin();
        login.setLogin(cadastrarLojaTO.getEmail());
        login.setSenha(cadastrarLojaTO.getPassword());
        login.setPermissao(Role.STORE);
        return login;
    }

    private Loja criarLoja(CadastrarLojaTO cadastrarLojaTO) {
        Loja loja = new Loja();
        loja.setNome(cadastrarLojaTO.getNomeDaEmpresa());
        loja.setNomeEmpresa(cadastrarLojaTO.getNomeJuridico());
        loja.setCnpjOuCpf(cadastrarLojaTO.getCpfOuCnpj());
        loja.setEmail(cadastrarLojaTO.getEmail());
        loja.setTelefone(cadastrarLojaTO.getTelefone());
        return loja;
    }

    private Endereco criaEndereco(CadastrarLojaTO cadastrarLojaTO) {
        Endereco endereco = new Endereco();
        endereco.setCep(cadastrarLojaTO.getCep());
        endereco.setBairro(cadastrarLojaTO.getBairro());
        endereco.setCidade(cadastrarLojaTO.getCidade());
        endereco.setComplemento(cadastrarLojaTO.getComplemento());
        endereco.setNome(cadastrarLojaTO.getRua());
        endereco.setNumero(cadastrarLojaTO.getNumero());
        return endereco;
    }


    }
