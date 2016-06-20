package com.br.free.commerce.bean;

import com.br.free.commerce.controller.HomeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by pc on 20/06/2016.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Count {

    @Autowired
    private HomeController controller;

    public static Long itmes(){
        RestTemplate template = new RestTemplate();
        return template.getForObject("http://adminappcommerce.herokuapp.com/staticController/total/items",Long.class);
    }

    public static Long users(){
        RestTemplate template = new RestTemplate();
        return template.getForObject("http://adminappcommerce.herokuapp.com/staticController/total/Users",Long.class);
    }

    public static Long locarion(){
        RestTemplate template = new RestTemplate();
        return template.getForObject("http://adminappcommerce.herokuapp.com/staticController/total/location",Long.class);
    }

    public static Long sellers(){
        RestTemplate template = new RestTemplate();
        return template.getForObject("http://adminappcommerce.herokuapp.com/staticController/total/sellers",Long.class);
    }
}
