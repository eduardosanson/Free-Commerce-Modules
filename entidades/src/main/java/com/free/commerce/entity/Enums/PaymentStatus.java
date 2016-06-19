package com.free.commerce.entity.Enums;

import com.free.commerce.entity.Enums.PedidoStatus;
import com.free.commerce.entity.Pedido;

import java.util.stream.Stream;

/**
 * Created by pc on 18/06/2016.
 */
public enum PaymentStatus {

    AVAILABLE("Disponivel", PedidoStatus.AGUARDANDO_PAGAMENTO),REFUNDED("Devolvida",PedidoStatus.CANCELADO),PAID("Paga",PedidoStatus.PAGAMENTO_EFETUADO),IN_DISPUTE("Em disputa",PedidoStatus.ESTORNADO);

    private String descricao;
    private PedidoStatus status;

    PaymentStatus(String descricao,PedidoStatus status) {
        this.descricao = descricao;
        this.status=status;
    }

    public static PaymentStatus getPaymentStatus(String status){
        PaymentStatus[] paymentStatuses = PaymentStatus.values();

        for (PaymentStatus n: paymentStatuses) {

            if (n.name().equalsIgnoreCase(status)){
                return n;
            }
        }

        return null;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }
}
