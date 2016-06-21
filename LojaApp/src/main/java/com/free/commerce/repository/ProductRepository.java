package com.free.commerce.repository;

import com.free.commerce.entity.Enums.AutorizacaoStatus;
import com.free.commerce.entity.Loja;
import com.free.commerce.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by eduardo.sanson on 21/03/2016.
 */
public interface ProductRepository extends PagingAndSortingRepository<Produto, Long>{

    @Query("select p from Produto p where p.loja = :#{#loja}")
    Page<Produto> buscarProdutoPorLoja(@Param("loja") Loja loja, Pageable pageable);

    @Query("select p from Produto p where p.nome LIKE CONCAT('%',:nome,'%')")
    Page<Produto> buscarProdutoParecidosPorNome(@Param("nome") String nome, Pageable pageable);

    @Query("select p from Produto p " +
            " inner join p.categoria c " +
            " inner join c.pai cpai " +
            " where cpai.descricao like CONCAT('%',:categoria,'%')")
    Page<Produto> buscarProdutoPorCategoria(@Param("categoria") String categoria, Pageable pageable);

    @Query("select p from Produto p order by p.registrado desc")
    List<Produto> recuperarUltimosProdutosCadastrados(Pageable pageable);

    /** Buscas por valores de compra
     *  Mais Caro
     *  Mais Barato
     *  Adicionado Recentemente Novos
     *  Todos
     */

    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndNomeLikeIgnoreCaseOrderByPrecoDesc(List<AutorizacaoStatus> status, int quantidade,String nome, Pageable pageable);

    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndNomeLikeIgnoreCaseOrderByPrecoAsc(List<AutorizacaoStatus> status, int quantidade,String nome,Pageable pageable);

    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndNomeLikeIgnoreCaseOrderByRegistradoDesc(List<AutorizacaoStatus> status, int quantidade,String nome, Pageable pageable);

    /*******     Fim Buscas por valores de compra     *******/


    /** Buscas por Estado do Produto
     *  Novos
     *  Usados
     */

    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndNovoAndNomeLikeIgnoreCase(List<AutorizacaoStatus> status, int quantidade,boolean novo,String nome, Pageable pageable);

    /*******     Fim Buscas por Estado do Produto     *******/

    /** Buscas por valores de compra e Estado do Produto
     *  Mais Caro Novo ou Usado
     *  Mais Barato Novo ou Usado
     *  Adicionado Recentemente Novos ou velhos
     *  Todos
     */

    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndNovoAndNomeLikeIgnoreCaseOrderByPrecoDesc(List<AutorizacaoStatus> status, int quantidade,boolean novo,String nome, Pageable pageable);

    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndNovoAndNomeLikeIgnoreCaseOrderByPrecoAsc(List<AutorizacaoStatus> status, int quantidade,boolean novo,String nome, Pageable pageable);

    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndNovoAndNomeLikeIgnoreCaseOrderByRegistradoDesc(List<AutorizacaoStatus> status, int quantidade,boolean novo,String nome, Pageable pageable);


    /*******   Fim Buscas por Estado do Produto e valores de compra  *******/

    /** busca por cidade, categoria e ambos**/

    //Por cidade
    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndLojaEnderecoCidadeAndNomeLikeIgnoreCase(List<AutorizacaoStatus> status, int quantidade,String cidade,String nome, Pageable pageable);

    //Por categoria
    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndCategoriaDescricaoInAndNomeLikeIgnoreCase(List<AutorizacaoStatus> status, int quantidade,List<String> categorias, String nome, Pageable pageable);

    //Por cidade e Categoria
    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndNomeLikeIgnoreCaseAndLojaEnderecoCidadeAndCategoriaDescricaoIn(List<AutorizacaoStatus> status, int quantidade,String nome,
                                                                                  String cidade,
                                                                                  List<String> categorias,
                                                                                  Pageable pageable);
    /** Ordenados por preco e mais atuais**/

    //Por cidade ordenado por maior preco
    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndLojaEnderecoCidadeAndNomeLikeIgnoreCaseOrderByPrecoDesc(List<AutorizacaoStatus> status, int quantidade,String cidade,String nome, Pageable pageable);

    //Por cidade ordenado por Menor preco
    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndLojaEnderecoCidadeAndNomeLikeIgnoreCaseOrderByPrecoAsc(List<AutorizacaoStatus> status, int quantidade,String cidade, String nome, Pageable pageable);


    /*****FIM Ordenados por preco e mais atuais *******************/

    /** Produtos novos ou usados ordenados por:
     * maior preco
     * menor preco
     * adicionados recentemente
     **/
    //novo ou usado ordenado por maior Preco
    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndNovoAndLojaEnderecoCidadeAndNomeLikeIgnoreCaseOrderByPrecoDesc(List<AutorizacaoStatus> status, int quantidade,boolean novo, String cidade,String nome, Pageable pageable);

    //novo ou usado ordenado por menor preco
    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndNovoAndLojaEnderecoCidadeAndNomeLikeIgnoreCaseOrderByPrecoAsc(List<AutorizacaoStatus> status, int quantidade,boolean novo, String cidade, String nome, Pageable pageable);

    //novo ou usado ordenado por adicionado recentemente
    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndNovoAndLojaEnderecoCidadeAndNomeLikeIgnoreCaseOrderByRegistradoDesc(List<AutorizacaoStatus> status, int quantidade,boolean novo, String cidade, String nome, Pageable pageable);
    /** FIM Produtos novos ou usados ordenados  **/

    /** buscar Por Categoria com ordenaçao  **/

    //Por categoria novo ou usado ordenado por maior Preco
    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndCategoriaDescricaoInAndNovoAndNomeLikeIgnoreCaseOrderByPrecoDesc(List<AutorizacaoStatus> status, int quantidade,List<String> categorias,boolean novo,String nome, Pageable pageable);

    //Por categoria ordenado por maior Preco
    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndCategoriaDescricaoInAndNomeLikeIgnoreCaseOrderByPrecoDesc(List<AutorizacaoStatus> status, int quantidade,List<String> categorias,String nome, Pageable pageable);

    //Por categoria ordenado por menor Preco
    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndCategoriaDescricaoInAndNomeLikeIgnoreCaseOrderByPrecoAsc(List<AutorizacaoStatus> status, int quantidade,List<String> categorias, String nome, Pageable pageable);

    //Por categoria novo ou usado ordenado por maior Preco
    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndCategoriaDescricaoInAndNovoAndNomeLikeIgnoreCaseOrderByPrecoAsc(List<AutorizacaoStatus> status, int quantidade,List<String> categorias,boolean novo,String nome, Pageable pageable);

    //Por categoria ordenado por adicionado recentemente
    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndCategoriaDescricaoInAndNomeLikeIgnoreCaseOrderByRegistradoDesc(List<AutorizacaoStatus> status, int quantidade,List<String> categorias,String nome, Pageable pageable);

    /** FIM buscar Por Categoria com ordenaçao  **/

    /** buscar Por cidade com ordenaçao  **/

    //Por cidade maior preco ou usado
    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndLojaEnderecoCidadeAndNovoAndNomeLikeIgnoreCaseOrderByPrecoDesc(List<AutorizacaoStatus> status, int quantidade,String cidade, boolean novo,String nome, Pageable pageable);

    //Por cidade menor preco novo ou usado
    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndLojaEnderecoCidadeAndNovoAndNomeLikeIgnoreCaseOrderByPrecoAsc(List<AutorizacaoStatus> status, int quantidade,String cidade, boolean novo,String nome,Pageable pageable);

    //Por cidade adicionado recentemente novo ou usado
    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndLojaEnderecoCidadeAndNovoAndNomeLikeIgnoreCaseOrderByRegistradoDesc(List<AutorizacaoStatus> status, int quantidade,String cidade, boolean novo,String nome,Pageable pageable);


    /** buscar Por Categoria com ordenaçao  **/


    /**FIM buscar Por cidade com ordenaçao  **/

    //Por cidade e Categoria ordernado por maior preco
    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndNomeLikeIgnoreCaseAndLojaEnderecoCidadeAndCategoriaDescricaoInOrderByPrecoDesc(List<AutorizacaoStatus> status, int quantidade,String nome,
                                                                                  String cidade,
                                                                                  List<String> categorias,
                                                                                  Pageable pageable);

    //Por cidade e Categoria ordernado por menor preco
    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndNomeLikeIgnoreCaseAndLojaEnderecoCidadeAndCategoriaDescricaoInOrderByPrecoAsc(List<AutorizacaoStatus> status, int quantidade,String nome,
                                                                                                 String cidade,
                                                                                                 List<String> categorias,
                                                                                                 Pageable pageable);

    //Por cidade e Categoria ordernado por adicionando recentemente
    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndNomeLikeIgnoreCaseAndLojaEnderecoCidadeAndCategoriaDescricaoInOrderByRegistradoDesc(List<AutorizacaoStatus> status, int quantidade,String nome,
                                                                                                String cidade,
                                                                                                List<String> categorias,
                                                                                                Pageable pageable);

    //Por cidade e Categoria ordernado por maior preco Novo ou usado
    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndNomeLikeIgnoreCaseAndLojaEnderecoCidadeAndCategoriaDescricaoInAndNovoOrderByPrecoDesc(List<AutorizacaoStatus> status, int quantidade,String nome,
                                                                                                 String cidade,
                                                                                                 List<String> categorias,
                                                                                                 boolean novo,
                                                                                                 Pageable pageable);

    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndNomeLikeIgnoreCaseAndLojaEnderecoCidadeAndCategoriaDescricaoInAndNovoOrderByPrecoAsc(List<AutorizacaoStatus> status, int quantidade,String nome,
                                                                                                        String cidade,
                                                                                                        List<String> categorias,
                                                                                                        boolean novo,
                                                                                                        Pageable pageable);

    /** FIM busca por cidade, categoria e ambos  **/


    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndNomeLikeIgnoreCaseAndLojaEnderecoCidadeOrderByRegistradoDesc(List<AutorizacaoStatus> status, int quantidade,String nome, String cidade, Pageable pageable);

    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndNomeLikeIgnoreCase(List<AutorizacaoStatus> status, int quantidade,String nome, Pageable pageable);

    Page<Produto> findByLojaAutorizacaoLojaStatusInAndQuantidadeGreaterThanAndNomeLikeIgnoreCaseAndLojaEnderecoCidadeAndCategoriaDescricaoInAndNovo(List<AutorizacaoStatus> status, int quantidade,String nome, String cidade, List<String> categorias, boolean novo, Pageable pageable);
}
