package com.br.free.commerce.interceptor;

import com.br.free.commerce.bean.Carrinho;
import com.br.free.commerce.bean.Count;
import com.br.free.commerce.services.Interface.ProdutoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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

        try {
            Carrinho carrinho = (Carrinho) request.getSession().getAttribute("scopedTarget.carrinho");

            request.getCookies();


            if (carrinho==null){
                carrinho = new Carrinho();
            }

            if (modelAndView!=null&&modelAndView.getModel()!=null){
                modelAndView.getModel().put("carrinho",carrinho);
            }

            modelAndView.getModel().put("count",new Count());
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }

//        modelAndView.addObject("carrinho",carrinho);
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return super.preHandle(request, response, handler);
    }
}
