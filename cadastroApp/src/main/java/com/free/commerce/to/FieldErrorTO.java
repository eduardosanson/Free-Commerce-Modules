package com.free.commerce.to;

/**
 * Created by eduardo.sanson on 17/03/2016.
 */
public class FieldErrorTO {

    private String campo;

    private String message;

    public FieldErrorTO() {

    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
