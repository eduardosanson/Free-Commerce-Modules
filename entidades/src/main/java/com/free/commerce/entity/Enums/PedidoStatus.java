package com.free.commerce.entity.Enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pc on 02/05/2016.
 */
public enum PedidoStatus {
    AGUARDANDO_PAGAMENTO,PAGAMENTO_EFETUADO,CANCELADO,ESTORNADO,PEDIDO_ENTREGUE;

}
