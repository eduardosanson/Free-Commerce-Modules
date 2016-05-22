package com.free.commerce.service;

import com.free.commerce.entity.Administrador;
import com.free.commerce.entity.Imagem;
import com.free.commerce.entity.Loja;
import com.free.commerce.repository.AdministradorRepository;
import com.free.commerce.repository.ImagemRepository;
import com.free.commerce.service.interfaces.AdminService;
import com.free.commerce.service.interfaces.UserLoginService;
import com.free.commerce.to.AdministradorTO;
import com.free.commerce.to.UserLoginTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by pc on 11/04/2016.
 */
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdministradorRepository repository;

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private ImagemRepository imagemRepository;

    private static final Logger LOGGER = Logger.getLogger(AdminServiceImpl.class);

    @Override
    public Administrador criarAdm(AdministradorTO administradorTO) {

        Administrador administrador = criarAdministrador(administradorTO);


        UserLoginTO userLoginTO = new UserLoginTO();
        userLoginTO.setLogin(administradorTO.getLogin());
        userLoginTO.setSenha(administradorTO.getSenha());
        userLoginTO.setAdministrador(administrador);

        LOGGER.info("iniciando adesao do userLogin");
        userLoginService.inserirLoginAdm(userLoginTO);

        return administrador;
    }


    @Override
    public void alterarPerfil(Long adminId, Imagem imagem) {
        LOGGER.info("Alterando perfil");
        Optional.ofNullable(imagem).ifPresent(n->{
            Administrador administrador = repository.findOne(adminId);
            administrador.setPerfil(imagemRepository.save(n));
            repository.save(administrador);
        });
    }

    @Override
    public Administrador recuperarPorId(Long id) {
        return repository.findOne(id);
    }

    private Administrador criarAdministrador(AdministradorTO administradorTO){
        Administrador administrador = new Administrador();
        administrador.setNome(administradorTO.getNome());
        administrador.setMatricula(administradorTO.getMatricula());
        return administrador;
    }
}
