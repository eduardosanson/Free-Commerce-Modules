package com.free.commerce.service;

import com.free.commerce.entity.*;
import com.free.commerce.exception.RegraDeNegocioException;
import com.free.commerce.exception.enuns.RegraDeNegocioEnum;
import com.free.commerce.repository.*;
import com.free.commerce.service.interfaces.PropriedadeService;
import com.free.commerce.service.interfaces.SetorService;
import com.free.commerce.to.CadastrarCategoriaTO;
import com.free.commerce.to.CadastrarClasificaoProdutoTO;
import com.free.commerce.to.CadastrarSetorTO;
import com.free.commerce.to.CadastrarTipoProdutoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 03/04/2016.
 */
@Service
public class SetorServiceImpl implements SetorService{

    @Autowired
    private SetorRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;

    @Autowired
   private PropriedadeService propriedadeService;

    @Override
    public Setor cadastrarSetor(CadastrarSetorTO cadastrarSetorTO) throws RegraDeNegocioException {
        List<Categoria> categorias;
        List<TipoProduto> tipoProdutos;

        try{
            Setor setor = criarSetor(cadastrarSetorTO.getSetorNome());

            Categoria categoria = criarCategoria(cadastrarSetorTO.getCategoriaNome());

            CadastrarTipoProdutoTO cadastrarTipoProdutoTO = criarCadastrarTipoProdutoTO(cadastrarSetorTO);

            TipoProduto tipoProduto = criarTipoProduto(cadastrarTipoProdutoTO);

            categoria = categoriaRepository.findOne(categoria.getId());

            tipoProdutos = categoria.getTipoProdutos();

            if (tipoProduto !=null && !tipoProdutos.isEmpty()){
                tipoProdutos.add(tipoProduto);
                categoria.setTipoProdutos(tipoProdutos);
            }


            setor = repository.findOne(setor.getId());

            categorias = setor.getCategorias();

            if (categorias !=null && !categorias.isEmpty()){
                categorias.add(categoria);
                setor.setCategorias(categorias);
            }
            return repository.save(setor);

        }catch (DataIntegrityViolationException dve) {
            if (dve.getRootCause().getMessage().contains("Duplicate entry")){
                throw new RegraDeNegocioException(RegraDeNegocioEnum.VALOR_JA_CADASTRADO);

            }


        }

            return null;


    }

    private CadastrarTipoProdutoTO criarCadastrarTipoProdutoTO(CadastrarSetorTO cadastrarSetorTO) {
        CadastrarTipoProdutoTO cadastrarTipoProdutoTO = new CadastrarTipoProdutoTO();

        if (cadastrarSetorTO!=null && cadastrarSetorTO.getClassificacaoProdutoNome()!=null && !cadastrarSetorTO.getClassificacaoProdutoNome().isEmpty()){
            List<ClassificacaoProduto> classificacaoProdutos = criarClassificacaoProduto(cadastrarSetorTO.getClassificacaoProdutoNome());
            cadastrarTipoProdutoTO.setClassificacaoProdutos(classificacaoProdutos);
        }


        if (cadastrarSetorTO.getCoresId()!=null && !cadastrarSetorTO.getCoresId().isEmpty()){
            cadastrarTipoProdutoTO.setCores(propriedadeService.buscarCoresPorIds(cadastrarSetorTO.getCoresId()));
        }
        if (cadastrarSetorTO.getMarcasId()!=null && !cadastrarSetorTO.getMarcasId().isEmpty()){
            cadastrarTipoProdutoTO.setMarcas(propriedadeService.buscarMarcasPorIds(cadastrarSetorTO.getMarcasId()));
        }
        if (cadastrarSetorTO.getTamanhoLetraId()!=null && !cadastrarSetorTO.getTamanhoLetraId().isEmpty()){
            cadastrarTipoProdutoTO.setTamanhoLetras(propriedadeService.buscarTamanhoLetrasPorIds(cadastrarSetorTO.getTamanhoLetraId()));
        }
        if (cadastrarSetorTO.getTamanhoNumeroId()!=null && !cadastrarSetorTO.getTamanhoNumeroId().isEmpty()){
            cadastrarTipoProdutoTO.setTamanhoNumeros(propriedadeService.buscarTamanhoNumerosPorIds(cadastrarSetorTO.getTamanhoNumeroId()));
        }

        return cadastrarTipoProdutoTO;
    }

    private List<ClassificacaoProduto> criarClassificacaoProduto(List<String> classificacaoProdutoNome) {
        List<ClassificacaoProduto> classificacaoProdutoList = new ArrayList<ClassificacaoProduto>();
        ClassificacaoProduto classificacaoProduto;

        for (String nome:classificacaoProdutoNome) {
            classificacaoProduto = new ClassificacaoProduto();
            classificacaoProduto.setNome(nome);
            classificacaoProdutoList.add(classificacaoProduto);

        }
        return classificacaoProdutoList;
    }

    private TipoProduto criarTipoProduto(CadastrarTipoProdutoTO cadastrarTipoProdutoTO) {
        boolean salvar=false;
        TipoProduto tipoProduto = new TipoProduto();
        if (cadastrarTipoProdutoTO.getCores()!=null && !cadastrarTipoProdutoTO.getCores().isEmpty()){
            tipoProduto.setCor(cadastrarTipoProdutoTO.getCores());
            salvar =true;
        }
        if (cadastrarTipoProdutoTO.getTamanhoNumeros()!=null && !cadastrarTipoProdutoTO.getTamanhoNumeros().isEmpty()){
            tipoProduto.setTamanhoNumero(cadastrarTipoProdutoTO.getTamanhoNumeros());
            salvar =true;
        }
        if (cadastrarTipoProdutoTO.getTamanhoLetras()!=null && !cadastrarTipoProdutoTO.getTamanhoLetras().isEmpty()){
            tipoProduto.setTamanhoLetra(cadastrarTipoProdutoTO.getTamanhoLetras());
            salvar =true;
        }
        if (cadastrarTipoProdutoTO.getMarcas()!=null && !cadastrarTipoProdutoTO.getMarcas().isEmpty()){
            tipoProduto.setMarca(cadastrarTipoProdutoTO.getMarcas());
            salvar =true;
        }
        if (cadastrarTipoProdutoTO.getClassificacaoProdutos()!=null && !cadastrarTipoProdutoTO.getClassificacaoProdutos().isEmpty()){
            tipoProduto.setClassificacaoProduto(cadastrarTipoProdutoTO.getClassificacaoProdutos());
            salvar =true;
        }

        if (salvar==true){
            return tipoProdutoRepository.save(tipoProduto);

        }else {
            return null;
        }

    }

    private Categoria criarCategoria(String categoriaNome) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaNome);
        return categoriaRepository.save(categoria);
    }

    private Setor criarSetor(String s) {
        Setor setor = new Setor();
        setor.setNome(s);
        return repository.save(setor);
    }

    @Override
    public Categoria cadastrarCategoria(CadastrarCategoriaTO cadastrarCategoriaTO) {
        return null;
    }

    @Override
    public TipoProduto cadastrarTipoProduto(CadastrarTipoProdutoTO cadastrarTipoProdutoTO) {
        return null;
    }

    @Override
    public ClassificacaoProduto cadastrarClassificacaoProduto(CadastrarClasificaoProdutoTO cadastrarClasificaoProdutoTO) {
        return null;
    }
}
