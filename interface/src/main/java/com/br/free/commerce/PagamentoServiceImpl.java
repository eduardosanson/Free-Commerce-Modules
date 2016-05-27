package com.br.free.commerce;

import br.com.uol.pagseguro.domain.PaymentRequest;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.enums.DocumentType;
import br.com.uol.pagseguro.enums.MetaDataItemKey;
import br.com.uol.pagseguro.enums.ShippingType;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;
import com.br.free.commerce.bean.Carrinho;
import com.free.commerce.entity.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by pc on 22/05/2016.
 */
@Service
public class PagamentoServiceImpl {

    public String gerarTokenPagamento(Carrinho carrinho, UserLogin userLogin,
                                      String idPedido, String urlDeRedirecionamento) throws PagSeguroServiceException {
        PaymentRequest paymentRequest = new PaymentRequest();
        Endereco endereco = userLogin.getCliente().getEnderecoEntrega();

        carrinho
                .getConteudo()
                .keySet()
                .stream().forEach(n->{
            int qtd = carrinho.getConteudo().get(n);
            paymentRequest.addItem(n.getIdentificadorDoProduto(), // identificação no meu site
                   n.getNome(), // nome do produto
                    Integer.valueOf(qtd), // quantidade de produto
                    formatToMoney(n.getPreco()), // preço do produto
                    new Long(1000), //peso unitário em gramas
                    null); //valor unitário do frete
        });

        paymentRequest.setShippingAddress("BRA", //
                endereco.getUf(), //
                endereco.getCidade(), //
                endereco.getBairro(), //
                endereco.getCep(), //
                endereco.getNome(), //
                endereco.getNumero(), //
                endereco.getComplemento());


        paymentRequest.setShippingType(ShippingType.SEDEX); // tipo de entrega
//
        paymentRequest.setShippingCost(this.formatToMoney(carrinho.getFrete().freteTotal()));

        char[] telefone = userLogin.getCliente().getTelefone().toCharArray();
        String ddd = "";
        String telefoneSemDDD ="";
        for (int i =0;i<telefone.length;i++ ){

            if (i<2){
                ddd = ddd+ telefone[i];
            }else {
                telefoneSemDDD = telefoneSemDDD+ telefone[i];
            }
        }


        paymentRequest.setSender(userLogin.getCliente().getNome(), //
                userLogin.getLogin(), //
                ddd, //
                telefoneSemDDD, //
                DocumentType.CPF, //
                userLogin.getCliente().getCpf());

        paymentRequest.setCurrency(Currency.BRL);

        // Sets a reference code for this payment request, it's useful to
        // identify this payment in future notifications
        paymentRequest.setReference(idPedido);

//        paymentRequest.setNotificationURL("http://www.meusite.com.br/notification");

        paymentRequest.setRedirectURL(urlDeRedirecionamento);
//
//        // Another way to set checkout parameters
//        paymentRequest.addParameter("senderBornDate", //
//                "07/05/1981");


            Boolean onlyCheckoutCode = false;


            return  paymentRequest.register(PagSeguroConfig.getAccountCredentials(), onlyCheckoutCode);

    }

    public BigDecimal formatToMoney(Double number) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.isParseBigDecimal();

        return new BigDecimal(df.format(number).replace(",",".")); // dj_segfault

    }


}
