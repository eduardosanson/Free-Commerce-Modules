package com.br.free.commerce.interceptor;

import com.br.free.commerce.bean.Carrinho;
import com.br.free.commerce.controllers.ProdutoController;
import com.br.free.commerce.services.Interface.ProdutoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by pc on 26/04/2016.
 */
public class Interceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = Logger.getLogger(Interceptor.class);

    @Autowired
    private ProdutoService produtoService;


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.info("add interceptor");

        Carrinho carrinho = (Carrinho) request.getSession().getAttribute("scopedTarget.carrinho");

        request.getCookies();


        if (carrinho==null){
            carrinho = new Carrinho();
        }

        if (modelAndView!=null&&modelAndView.getModel()!=null){
            modelAndView.getModel().put("carrinho",carrinho);
        }
//        modelAndView.addObject("carrinho",carrinho);
        super.postHandle(request, response, handler, modelAndView);
    }



}
