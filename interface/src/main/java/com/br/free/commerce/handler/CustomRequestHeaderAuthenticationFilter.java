package com.br.free.commerce.handler;

import com.br.free.commerce.security.CustomAuthenticationManager;
import org.apache.log4j.Logger;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedCredentialsNotFoundException;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pc on 12/05/2016.
 */
@EnableWebMvc
public class CustomRequestHeaderAuthenticationFilter extends RequestHeaderAuthenticationFilter {
    private static final Logger logger = Logger.getLogger(CustomAuthenticationManager.class);
    private final String HEADER = "USER_HEADER";

    public CustomRequestHeaderAuthenticationFilter() {
        super();
        this.setPrincipalRequestHeader(HEADER);

        // This setting returns an HTTP 404 with no error message instead of an HTTP 500 with the "missing header" exception message
        this.setExceptionIfHeaderMissing(false);
    }


    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        logger.debug("Authorizing URI: " + request.getRequestURI());

        try {
            return super.getPreAuthenticatedPrincipal(request);
        } catch(PreAuthenticatedCredentialsNotFoundException e) {
            logger.error("Could not find request header " + HEADER + " in request", e);
            return null;
        }
    }
}
