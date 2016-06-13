package com.free.commerce.util;

import com.free.commerce.entity.Produto;
import com.free.commerce.repository.ProductRepository;
import com.free.commerce.to.BuscarProdutoTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by pc on 12/06/2016.
 */
public class BuscarProdutoResolver {

    private BuscarProdutoTO buscarProdutoTO;

    private Pageable pageable;

    private ProductRepository repository;

    public BuscarProdutoResolver(BuscarProdutoTO buscarProdutoTO, Pageable pageable, ProductRepository repository) {
        this.buscarProdutoTO = buscarProdutoTO;
        this.pageable = pageable;
        this.repository = repository;
    }

    public Page<Produto> buscarProdutos(){

        return isOrderByPrecoDesc();

    }

    private Page<Produto> isOrderByPrecoDesc() {
        
        if ("".equalsIgnoreCase(buscarProdutoTO.getCategoria())
                && "".equalsIgnoreCase(buscarProdutoTO.getCidade())
                && "".equalsIgnoreCase(buscarProdutoTO.getNovo())
                && isPrecoDesc()) {
            return repository.findByNomeLikeOrderByPrecoDesc(buscarProdutoTO.getNome(), pageable);
        }
        return isOrderByPrecoAsc();
    }

    private boolean isPrecoDesc() {
        return "precodesc".equalsIgnoreCase(buscarProdutoTO.getOrderBy());
    }

    private Page<Produto> isOrderByPrecoAsc() {
        
        if ("".equalsIgnoreCase(buscarProdutoTO.getCategoria())
                && "".equalsIgnoreCase(buscarProdutoTO.getCidade())
                && "".equalsIgnoreCase(buscarProdutoTO.getNovo())
                && isPrecoAsc()) {
            return repository.findByNomeLikeOrderByPrecoAsc(buscarProdutoTO.getNome(), pageable);
        }
        return isOrderByRegistradoDesc();
    }

    private boolean isPrecoAsc() {
        return "precoasc".equalsIgnoreCase(buscarProdutoTO.getOrderBy());
    }

    private Page<Produto> isOrderByRegistradoDesc() {
        
        if ("".equalsIgnoreCase(buscarProdutoTO.getCategoria())
                && "".equalsIgnoreCase(buscarProdutoTO.getCidade())
                && "".equalsIgnoreCase(buscarProdutoTO.getNovo())
                && isRegistradoDesc()) {
            return repository.findByNomeLikeOrderByRegistradoDesc(buscarProdutoTO.getNome(), pageable);
        }
        return isByNovo();
    }

    private boolean isRegistradoDesc() {
        return "RegistradoDesc".equalsIgnoreCase(buscarProdutoTO.getOrderBy());
    }

    private Page<Produto> isByNovo() {
        
        if ("".equalsIgnoreCase(buscarProdutoTO.getCategoria())
                && "".equalsIgnoreCase(buscarProdutoTO.getCidade())
                && hasNovo()
                && hasOrderBy()) {
            return repository.findByNomeLikeOrderByRegistradoDesc(buscarProdutoTO.getNome(), pageable);
        }
        return isByNovoOrderByPrecoDesc();
    }

    private boolean hasNovo() {
        return !"".equalsIgnoreCase(buscarProdutoTO.getNovo());
    }

    private Page<Produto> isByNovoOrderByPrecoDesc() {
        
        if ("".equalsIgnoreCase(buscarProdutoTO.getCategoria())
                && "".equalsIgnoreCase(buscarProdutoTO.getCidade())
                && hasNovo()
                && isPrecoDesc()) {
            return repository.findByNovoAndNomeLikeOrderByPrecoDesc(buscarProdutoTO.isNovo(), buscarProdutoTO.getNome(), pageable);
        }
        return isByNovoOrderByPrecoAsc();
    }

    private Page<Produto> isByNovoOrderByPrecoAsc() {
        if ("".equalsIgnoreCase(buscarProdutoTO.getCategoria())
                && "".equalsIgnoreCase(buscarProdutoTO.getCidade())
                && hasNovo()
                && isPrecoAsc()) {
            return repository.findByNovoAndNomeLikeOrderByPrecoAsc(buscarProdutoTO.isNovo(), buscarProdutoTO.getNome(), pageable);
        }
        return isByNovoRegistradoDesc();
    }

    private Page<Produto> isByNovoRegistradoDesc() {
        if ("".equalsIgnoreCase(buscarProdutoTO.getCategoria())
                && "".equalsIgnoreCase(buscarProdutoTO.getCidade())
                && hasNovo()
                && isRegistradoDesc()) {
            return repository.findByNovoAndNomeLikeOrderByRegistradoDesc(buscarProdutoTO.isNovo(), buscarProdutoTO.getNome(), pageable);
        }
        return isByCidadeRegistradoDesc();
    }

    private Page<Produto> isByCidadeRegistradoDesc() {
        if ("".equalsIgnoreCase(buscarProdutoTO.getCategoria())
                && hasCidade()
                && "".equalsIgnoreCase(buscarProdutoTO.getNovo())
                && isRegistradoDesc()) {
            return repository.findByNomeLikeAndLojaEnderecoCidadeOrderByRegistradoDesc(buscarProdutoTO.getNome(), buscarProdutoTO.getCidade(), pageable);
        }
        return isByCidade();
    }

    private boolean hasCidade() {
        return !"".equalsIgnoreCase(buscarProdutoTO.getCidade());
    }

    private Page<Produto> isByCidade() {
        if (!hasCategoria()
                && hasCidade()
                && "".equalsIgnoreCase(buscarProdutoTO.getNovo())
                && "".equalsIgnoreCase(buscarProdutoTO.getNovo())
                && !hasOrderBy()) {
            return repository.findByLojaEnderecoCidadeAndNomeLike(buscarProdutoTO.getCidade(), buscarProdutoTO.getNome(), pageable);
        }
        return isByCategoria();
    }

    private boolean hasOrderBy() {
        return !"".equalsIgnoreCase(buscarProdutoTO.getOrderBy());
    }

    private Page<Produto> isByCategoria() {
        if (hasCategoria()
                && !hasCidade()
                && "".equalsIgnoreCase(buscarProdutoTO.getNovo())
                && !hasOrderBy()) {
            return repository.findByCategoriaPaiDescricaoLikeAndNomeLike(buscarProdutoTO.getCategoria(), buscarProdutoTO.getNome(), pageable);
        }
        return isByCategoriaAndCidade();
    }

    private boolean hasCategoria() {
        return !"".equalsIgnoreCase(buscarProdutoTO.getCategoria());
    }

    private Page<Produto> isByCategoriaAndCidade() {
        
        if (hasCategoria()
                && hasCidade()
                && !hasNovo()
                && !hasOrderBy()) {
            return repository.findByNomeLikeAndLojaEnderecoCidadeAndCategoriaPaiDescricaoLike(buscarProdutoTO.getNome(),buscarProdutoTO.getCidade(),buscarProdutoTO.getCategoria(),pageable);
        }
        return isByCidadePrecoDesc();
    }

    private Page<Produto> isByCidadePrecoDesc() {
        
        if (!hasCategoria()
                && hasCidade()
                && !hasNovo()
                && isPrecoDesc()) {
            return repository.findByLojaEnderecoCidadeAndNomeLikeOrderByPrecoDesc(buscarProdutoTO.getCidade(),buscarProdutoTO.getNome(),pageable);
        }
        return isByCidadePrecoAsc();
    }

    private Page<Produto> isByCidadePrecoAsc() {
        if (!hasCategoria()
                && hasCidade()
                && !hasNovo()
                && isPrecoAsc()) {
            return repository.findByLojaEnderecoCidadeAndNomeLikeOrderByPrecoAsc(buscarProdutoTO.getCidade(),buscarProdutoTO.getNome(),pageable);
        }
        return isByNovoAndCidadePrecoDesc();
    }

    private Page<Produto> isByNovoAndCidadePrecoDesc() {
        if (!hasCategoria()
                && hasCidade()
                && hasNovo()
                && isPrecoDesc()) {
            return repository.findByNovoAndLojaEnderecoCidadeAndNomeLikeOrderByPrecoDesc(buscarProdutoTO.isNovo(),buscarProdutoTO.getCidade(),buscarProdutoTO.getNome(),pageable);
        }
        return isByNovoAndCidadePrecoAsc();
    }

    private Page<Produto>  isByNovoAndCidadePrecoAsc() {
        
        if (!hasCategoria()
                && hasCidade()
                && hasNovo()
                && isPrecoAsc()) {
            return repository.findByNovoAndLojaEnderecoCidadeAndNomeLikeOrderByPrecoAsc(buscarProdutoTO.isNovo(),buscarProdutoTO.getCidade(),buscarProdutoTO.getNome(),pageable);
        }
        return isByNovoAndCidadeRegistradoDesc();
    }

    private Page<Produto> isByNovoAndCidadeRegistradoDesc() {
        
        if (!hasCategoria()
                && hasCidade()
                && hasNovo()
                && isRegistradoDesc()) {
            return repository.findByNovoAndLojaEnderecoCidadeAndNomeLikeOrderByRegistradoDesc(buscarProdutoTO.isNovo(),buscarProdutoTO.getCidade(),buscarProdutoTO.getNome(),pageable);
        }
        return isByNovoAndCategoriaPrecoDesc();
    }

    private Page<Produto> isByNovoAndCategoriaPrecoDesc() {
        
        if (hasCategoria()
                && !hasCidade()
                && hasNovo()
                && isPrecoDesc()) {
            return repository.findByCategoriaPaiDescricaoLikeAndNovoAndNomeLikeOrderByPrecoDesc(buscarProdutoTO.getCategoria(),buscarProdutoTO.isNovo(),buscarProdutoTO.getNome(),pageable);
        }
        return isByCategoriaPrecoDesc();
    }

    private  Page<Produto> isByCategoriaPrecoDesc() {
        
        if (hasCategoria()
                && !hasCidade()
                && !hasNovo()
                && isPrecoDesc()) {
            return repository.findByCategoriaPaiDescricaoLikeAndNomeLikeOrderByPrecoDesc(buscarProdutoTO.getCategoria(),buscarProdutoTO.getNome(),pageable);
        }
        return isByCategoriaPrecoAsc();
    }

    private Page<Produto> isByCategoriaPrecoAsc() {
        
        if (hasCategoria()
                && !hasCidade()
                && !hasNovo()
                && isPrecoAsc()) {
            return repository.findByCategoriaPaiDescricaoLikeAndNomeLikeOrderByPrecoAsc(buscarProdutoTO.getCategoria(),buscarProdutoTO.getNome(),pageable);
        }
        return isByCategoriaAndNovoPrecoAsc();
    }

    private Page<Produto> isByCategoriaAndNovoPrecoAsc() {
        
        if (hasCategoria()
                && !hasCidade()
                && hasNovo()
                && isPrecoAsc()) {
            return repository.findByCategoriaPaiDescricaoLikeAndNovoAndNomeLikeOrderByPrecoAsc(buscarProdutoTO.getCategoria(),buscarProdutoTO.isNovo(),buscarProdutoTO.getNome(),pageable);
        }
        return isByCidadeAndNovoPrecoDesc();
    }

    private Page<Produto> isByCidadeAndNovoPrecoDesc() {
        
        if (!hasCategoria()
                && hasCidade()
                && hasNovo()
                && isPrecoDesc()) {
            return repository.findByLojaEnderecoCidadeAndNovoAndNomeLikeOrderByPrecoDesc(buscarProdutoTO.getCidade(),buscarProdutoTO.isNovo(),buscarProdutoTO.getNome(),pageable);
        }
        return isByCidadeAndNovoPrecoAsc();
    }

    private Page<Produto> isByCidadeAndNovoPrecoAsc() {
        
        if (!hasCategoria()
                && hasCidade()
                && hasNovo()
                && isPrecoAsc()) {
            return repository.findByLojaEnderecoCidadeAndNovoAndNomeLikeOrderByPrecoAsc(buscarProdutoTO.getCidade(),buscarProdutoTO.isNovo(),buscarProdutoTO.getNome(),pageable);

        }
        return isByCidadeAndNovoRegistradoDesc();
    }

    private Page<Produto> isByCidadeAndNovoRegistradoDesc() {
        
        if (!hasCategoria()
                && hasCidade()
                && hasNovo()
                && isRegistradoDesc()) {
            return repository.findByLojaEnderecoCidadeAndNovoAndNomeLikeOrderByRegistradoDesc(buscarProdutoTO.getCidade(),buscarProdutoTO.isNovo(),buscarProdutoTO.getNome(),pageable);
        }
        return isByCidadeAndCategoriaPrecoDesc();
    }

    private Page<Produto> isByCidadeAndCategoriaPrecoDesc() {
        
        if (hasCategoria()
                && hasCidade()
                && !hasNovo()
                && isPrecoDesc()) {
            return repository.findByNomeLikeAndLojaEnderecoCidadeAndCategoriaPaiDescricaoLikeOrderByPrecoDesc(buscarProdutoTO.getNome(),buscarProdutoTO.getCidade(),buscarProdutoTO.getCategoria(),pageable);
        }
        return isByCidadeAndCategoriaPrecoAsc();
    }

    private Page<Produto> isByCidadeAndCategoriaPrecoAsc() {
        
        if (hasCategoria()
                && hasCidade()
                && !hasNovo()
                && isPrecoAsc()) {
            return repository.findByNomeLikeAndLojaEnderecoCidadeAndCategoriaPaiDescricaoLikeOrderByPrecoAsc(buscarProdutoTO.getNome(),buscarProdutoTO.getCidade(),buscarProdutoTO.getCategoria(),pageable);
        }
        return isByCidadeAndCategoriaRegistradoDesc();
    }

    private Page<Produto> isByCidadeAndCategoriaRegistradoDesc() {
        
        if (hasCategoria()
                && hasCidade()
                && !hasNovo()
                && isRegistradoDesc()) {
            return repository
                    .findByNomeLikeAndLojaEnderecoCidadeAndCategoriaPaiDescricaoLikeOrderByRegistradoDesc(buscarProdutoTO.getNome(),
                            buscarProdutoTO.getCidade(),buscarProdutoTO.getCategoria(),pageable);
        }
        return isByCidadeAndCategoriaAndNovoPrecoDesc();
    }

    private Page<Produto> isByCidadeAndCategoriaAndNovoPrecoDesc() {
        
        if (hasCategoria()
                && hasCidade()
                && hasNovo()
                && isPrecoDesc()) {
            return repository
                    .findByNomeLikeAndLojaEnderecoCidadeAndCategoriaPaiDescricaoLikeAndNovoOrderByPrecoDesc(buscarProdutoTO.getNome(),
                            buscarProdutoTO.getCidade(),buscarProdutoTO.getCategoria(),buscarProdutoTO.isNovo(),pageable);
        }
        return isByCidadeAndCategoriaAndNovoPrecoAsc();
    }

    private Page<Produto> isByCidadeAndCategoriaAndNovoPrecoAsc() {
        if (hasCategoria()
                && hasCidade()
                && hasNovo()
                && isPrecoAsc()) {
            return repository.findByNomeLikeAndLojaEnderecoCidadeAndCategoriaPaiDescricaoLikeAndNovoOrderByPrecoAsc(buscarProdutoTO.getNome(),
                    buscarProdutoTO.getCidade(),buscarProdutoTO.getCategoria(),buscarProdutoTO.isNovo(),pageable);
        }
        return isByCategoriaRegistradoDesc();
    }

    private Page<Produto> isByCategoriaRegistradoDesc() {
        if (hasCategoria()
                && hasCidade()
                && hasNovo()
                && isRegistradoDesc()) {
            return repository.findByCategoriaPaiDescricaoLikeAndNomeLikeOrderByRegistradoDesc(buscarProdutoTO.getCategoria(),buscarProdutoTO.getNome(),pageable);
        }
        return ByNovo();
    }

    private Page<Produto> ByNovo() {
        if (!hasCategoria()
                && !hasCidade()
                && hasNovo()
                && hasOrderBy()) {
            return repository.findByNovoAndNomeLike(buscarProdutoTO.isNovo(),buscarProdutoTO.getNome(),pageable);
        }
        return ByNome();
    }

    private Page<Produto> ByNome() {
        if (!hasCategoria()
                && !hasCidade()
                && !hasNovo()
                && !hasOrderBy()) {
            return repository.findByNomeLike(buscarProdutoTO.getNome(),pageable);
        }
        return ByCidadeAndCategoriaAndNovo();
    }

    private Page<Produto> ByCidadeAndCategoriaAndNovo() {
        if (hasCategoria()
                && hasCidade()
                && hasNovo()
                && !hasOrderBy()) {
            return repository.findByNomeLikeAndLojaEnderecoCidadeAndCategoriaPaiDescricaoLikeAndNovo(buscarProdutoTO.getNome(),
                    buscarProdutoTO.getCidade(),buscarProdutoTO.getCategoria(),buscarProdutoTO.isNovo(),pageable);
        }
        return null;
    }

}
