package com.br.free.commerce.util;

/**
 * Created by ulyss on 15/05/2016.
 */
public class MaskUtil {

    public static String removeTelefoneMask(String telefone){

        if (telefone!=null){

            return telefone.replaceAll("[^\\d.]","");
        }

        return telefone;

    }

    public static String removeCepMask(String cep){

        if (cep!=null){

            return cep.replace("-","");
        }

        return cep;
    }

    public static String removeCpfOuCnpjMask(String cpfOuCnpj){
        if (cpfOuCnpj!=null){

            return cpfOuCnpj.replaceAll("[^0-9]+","");
        }

        return cpfOuCnpj;

    }
}
