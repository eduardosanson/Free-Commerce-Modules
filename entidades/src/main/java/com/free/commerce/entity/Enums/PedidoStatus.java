package com.free.commerce.entity.Enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pc on 02/05/2016.
 */
@JsonFormat(shape= JsonFormat.Shape.OBJECT)
public enum PedidoStatus {
    AGUARDANDO_PAGAMENTO,PAGAMENTO_EFETUADO,CANCELADO,ESTORNADO,PEDIDO_ENTREGUE;

    @JsonProperty("value")
    public String value(){
        return this.name();
    }

}
