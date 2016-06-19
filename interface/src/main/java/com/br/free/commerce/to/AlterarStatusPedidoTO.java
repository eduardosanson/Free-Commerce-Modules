package com.br.free.commerce.to;

import com.free.commerce.entity.Enums.PedidoStatus;

/**
 * Created by pc on 18/06/2016.
 */
public class AlterarStatusPedidoTO {

    private String notificationCode;

    private String status;

    public String getNotificationCode() {
        return notificationCode;
    }

    public void setNotificationCode(String notificationCode) {
        this.notificationCode = notificationCode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
