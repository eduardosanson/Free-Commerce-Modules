package com.free.commerce.to;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eduardo.sanson on 17/03/2016.
 */
public class ValidationErrorTO {

    public static List<FieldErrorTO> exactlyErroMessage(BindingResult bindingResult){
        List<FieldErrorTO> errors = new ArrayList<FieldErrorTO>();
        FieldErrorTO error;

        List<FieldError>  fieldErrors = bindingResult.getFieldErrors();

        for (FieldError fieldError : fieldErrors ) {
            error = new FieldErrorTO();
            error.setCampo(fieldError.getField());
            error.setMessage(fieldError.getDefaultMessage());
            errors.add(error);
        }

        return errors;
    }
}
