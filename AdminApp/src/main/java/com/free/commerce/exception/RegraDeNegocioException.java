package com.free.commerce.exception;

import com.free.commerce.exception.enuns.RegraDeNegocioEnum;

/**
 * Created by pc on 04/04/2016.
 */
public class RegraDeNegocioException extends Exception {


    private RegraDeNegocioEnum tipoErro;

    public RegraDeNegocioException(RegraDeNegocioEnum tipoErro) {
        super(tipoErro.getMensagem());
        this.tipoErro = tipoErro;
    }

    public RegraDeNegocioEnum getTipoErro() {
        return tipoErro;
    }

    public void setTipoErro(RegraDeNegocioEnum tipoErro) {
        this.tipoErro = tipoErro;
    }
}
