package com.br.free.commerce.handler;

import com.br.free.commerce.controller.ClienteController;
import com.free.commerce.entity.Enums.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by pc on 04/04/2016.
 */
@Service
public class AuthenticationSuccessHandlerImpl extends SimpleUrlAuthenticationSuccessHandler {

    private static String CLIENTE_CONTROLLER="/cliente";

    private String urlRequested ="";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        urlRequested =CLIENTE_CONTROLLER + ClienteController.FINALIZAR_COMPRA;

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();

        request.getRequestURI();
        if(roles.contains("ROLE_" + Role.STORE.name())){
            response.sendRedirect("/store/menu");

        }
        if (roles.contains("ROLE_" + Role.ADMIN.name())){
            response.sendRedirect("/admin/menu");

        }

        DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");

        if (defaultSavedRequest !=null && urlRequested.equalsIgnoreCase(defaultSavedRequest.getRequestURI())){
            response.sendRedirect(urlRequested);
        }else if (roles.contains("ROLE_" + Role.CLIENT.name())){
            response.sendRedirect("/cliente/menu");
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }


}
