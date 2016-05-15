package com.br.free.commerce.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by pc on 02/05/2016.
 */
@Service
public class CustomHttp403ForbiddenEntryPoint extends Http403ForbiddenEntryPoint {

    private static String CLIENTE_CONTROLLER="/cliente";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException, ServletException {



            response.sendRedirect("/public/login");

    }
}
