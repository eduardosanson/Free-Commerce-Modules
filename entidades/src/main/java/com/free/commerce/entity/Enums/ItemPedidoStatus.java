package com.free.commerce.entity.Enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.free.commerce.entity.ItemPedido;

/**
 * Created by pc on 02/05/2016.
 */
@JsonFormat(shape= JsonFormat.Shape.OBJECT)
public enum ItemPedidoStatus {
    CANCELADO("CANCELADO"),
    ESTORNADO("ESTORNADO"),
    AGUARDANDO_PAGAMENTO("AGUARDANDO PAGAMENTO"),
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


    public static ItemPedidoStatus getItemPedido(PedidoStatus status) {
        ItemPedidoStatus itemPedidoStatus=null;

        if (PedidoStatus.AGUARDANDO_PAGAMENTO.name().equalsIgnoreCase(status.name())){
            itemPedidoStatus = ItemPedidoStatus.AGUARDANDO_PAGAMENTO;

        }else if (PedidoStatus.PAGAMENTO_EFETUADO.name().equalsIgnoreCase(status.name())){
            itemPedidoStatus = ItemPedidoStatus.PAGO;
            }else if (CANCELADO.name().equalsIgnoreCase(ItemPedidoStatus.CANCELADO.name()));
        else if (PedidoStatus.CANCELADO.name().equalsIgnoreCase(status.name())){
            itemPedidoStatus = ItemPedidoStatus.CANCELADO;
        }else if (PedidoStatus.ESTORNADO.name().equalsIgnoreCase(status.name())){
            itemPedidoStatus = ItemPedidoStatus.ESTORNADO;

        }
        return itemPedidoStatus;



    }
}
