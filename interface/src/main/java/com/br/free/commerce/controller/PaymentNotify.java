package com.br.free.commerce.controller;

import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import com.br.free.commerce.services.Interface.PedidoService;
import com.br.free.commerce.services.PagamentoServiceImpl;
import com.free.commerce.entity.Enums.PaymentStatus;
import com.free.commerce.entity.Enums.PedidoStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by pc on 18/06/2016.
 */
@RestController
@RequestMapping("/paymentNotifyController")
public class PaymentNotify {

    @Autowired
    private PagamentoServiceImpl pagamentoService;

    @Autowired
    private PedidoService pedidoService;

    private Logger logger = Logger.getLogger(PaymentNotify.class);

    @RequestMapping(value = "/notify",method = RequestMethod.POST)
    public ResponseEntity receberNotificacao(@RequestParam String notificationCode, @RequestParam String notificationType){
        logger.info("Recebendo notificationCode: " + notificationCode);
        try {
            Transaction transaction = pagamentoService.SearchTransactionByCode(notificationCode);
            PedidoStatus pedidoStatus = PedidoStatus.getPedidoEstatus(PaymentStatus.getPaymentStatus(transaction.getStatus().name()));
            pedidoService.alterarStatus(transaction.getCode(),pedidoStatus,transaction.getReference());
        } catch (PagSeguroServiceException e) {
            logger.error("Recebendo notificationCode: " + notificationCode + " erro " + e.getMessage() + " causa " +e.getCause());
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }

        logger.info("sucesso notificationCode: " + notificationCode);

        return new ResponseEntity(HttpStatus.OK);

    }
}
