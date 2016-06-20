package com.free.commerce.mail;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public class JavaMailApp
{
    public static void main(String[] args) {

        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setStartTLSEnabled(true);
            email.setSmtpPort(587);
            email.setAuthentication("eduardobosanson@gmail.com","nasc2809");
            email.setSSLOnConnect(true);
            email.setSSLCheckServerIdentity(false);
            email.setFrom("eduardobosanson@gmail.com");
            email.setSubject("asdsa");
            email.setMsg("asdwsdas");
            email.addTo("eduardobosanson@gmail.com");

            email.send();

            System.out.print("feito");

        }catch (Exception e){
            e.printStackTrace();

        }


    }
}