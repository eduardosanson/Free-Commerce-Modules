package com.br.free.commerce.services.Interface;

import com.br.free.commerce.to.CadastrarClienteTO;
import com.br.free.commerce.to.FinalizarCadastroTO;
import com.free.commerce.entity.Administrador;
import com.free.commerce.entity.Cliente;
import com.free.commerce.entity.UserLogin;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by pc on 22/05/2016.
 */
public interface AdminService {
    UserLogin cadastrarAdmin(CadastrarClienteTO cadastrarClienteTO);

    Administrador concluirCadastro(FinalizarCadastroTO cadastroTO);

    Administrador buscarAdmin(Long id);

    void alterarPerfil(Long adminId, MultipartFile file);
}
