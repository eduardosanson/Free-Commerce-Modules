package com.free.commerce.to;

import com.free.commerce.entity.Loja;
import org.omg.CORBA.Object;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by eduardo.sanson on 17/03/2016.
 */
public class StoreResponse {

    private List<FieldErrorTO> errors;

    private Loja loja;

    public List<FieldErrorTO> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldErrorTO> errors) {
        this.errors = errors;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }
}
