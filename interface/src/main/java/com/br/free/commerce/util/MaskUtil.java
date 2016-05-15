package com.br.free.commerce.util;

/**
 * Created by ulyss on 15/05/2016.
 */
public class MaskUtil {

    public static String removeTelefoneMask(String telefone){
        return telefone.replace("()-","");
    }
}
