package com.free.commerce.entity.Enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pc on 02/05/2016.
 */
@JsonFormat(shape= JsonFormat.Shape.OBJECT)
public enum ItemPedidoStatus {
    ENTREGUE("ENTREGUE"),
    CANCELADO("CANCELADO"),
    ESTORNADO("ESTORNADO"),
    ESTRAVIADO("ESTRAVIADO"),
    AGUARDANDO_PAGAMENTO("AGUARDANDO PAGAMENTO"),
    ENVIADO("ENVIADO"),
    PAGO("PAGO");

    private String descricao;

    ItemPedidoStatus(String descricao) {
        this.descricao = descricao;
    }

    @JsonProperty("descricao")
    public String getDescricao() {
        return descricao;
    }

    @JsonProperty("value")
    public String value(){
        return this.name();
    }



}
