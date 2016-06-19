package com.free.commerce.entity.Enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pc on 02/05/2016.
 */
public enum PedidoStatus {
    AGUARDANDO_PAGAMENTO,PAGAMENTO_EFETUADO,CANCELADO,ESTORNADO,PEDIDO_ENTREGUE;

    public static PedidoStatus getPedidoEstatus(PaymentStatus status){
        PedidoStatus[] pedidoStatus = PedidoStatus.values();

        for (PedidoStatus n: pedidoStatus) {

            if (n.name().equalsIgnoreCase(status.getStatus().name())){
                return n;
            }
        }

        return null;
    }

    public static PedidoStatus getPedidoEstatusByString(String status){
        PedidoStatus[] pedidoStatus = PedidoStatus.values();

        for (PedidoStatus n: pedidoStatus) {

            if (n.name().equalsIgnoreCase(status)){
                return n;
            }
        }

        return null;
    }

}
