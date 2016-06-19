package com.br.free.commerce.services;

import br.com.uol.pagseguro.domain.PaymentRequest;
import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.domain.checkout.Checkout;
import br.com.uol.pagseguro.enums.Currency;
import br.com.uol.pagseguro.enums.DocumentType;
import br.com.uol.pagseguro.enums.MetaDataItemKey;
import br.com.uol.pagseguro.enums.ShippingType;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.uol.pagseguro.properties.PagSeguroConfig;
import br.com.uol.pagseguro.service.NotificationService;
import br.com.uol.pagseguro.service.TransactionSearchService;
import com.br.free.commerce.bean.Carrinho;
import com.br.free.commerce.util.NumberUtil;
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
        Endereco endereco = userLogin.getCliente().getEnderecoEntrega();
        Checkout checkout = new Checkout();

        carrinho
                .getConteudo()
                .keySet()
                .stream().forEach(n->{
            int qtd = carrinho.getConteudo().get(n);
            checkout.addItem(n.getIdentificadorDoProduto(), // identificação no meu site
                   n.getNome(), // nome do produto
                    Integer.valueOf(qtd), // quantidade de produto
                    NumberUtil.formatToMoney(n.getPreco()), // preço do produto
                    new Long(1000), //peso unitário em gramas
                    null); //valor unitário do frete
        });

        checkout.setShippingAddress("BRA", //
                endereco.getUf(), //
                endereco.getCidade(), //
                endereco.getBairro(), //
                endereco.getCep(), //
                endereco.getNome(), //
                endereco.getNumero(), //
                endereco.getComplemento());


        checkout.setShippingType(ShippingType.SEDEX); // tipo de entrega
//
        checkout.setShippingCost(NumberUtil.formatToMoney(carrinho.getFrete().freteTotal()));

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


        checkout.setSender(userLogin.getCliente().getNome() + " " + userLogin.getCliente().getSobrenome(), //
                userLogin.getLogin(), //
                ddd, //
                telefoneSemDDD, //
                DocumentType.CPF, //
                userLogin.getCliente().getCpf());

        checkout.setCurrency(Currency.BRL);

        // Sets a reference code for this payment request, it's useful to
        // identify this payment in future notifications
        checkout.setReference(idPedido);

//        paymentRequest.setNotificationURL("http://www.meusite.com.br/notification");

        checkout.setRedirectURL(urlDeRedirecionamento);
//
//        // Another way to set checkout parameters
//        paymentRequest.addParameter("senderBornDate", //
//                "07/05/1981");


            Boolean onlyCheckoutCode = false;


            return  checkout.register(PagSeguroConfig.getAccountCredentials(), onlyCheckoutCode);

    }


    public Transaction SearchTransactionByCode(String notificationCode) throws PagSeguroServiceException {
        return NotificationService.checkTransaction(PagSeguroConfig.getAccountCredentials(),
                notificationCode);
    }


}
