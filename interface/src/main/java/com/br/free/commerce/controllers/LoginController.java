package com.br.free.commerce.controllers;

import com.br.free.commerce.entity.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by eduardo.sanson on 17/03/2016.
 */
@Controller
@RequestMapping
public class LoginController {

    private static final String LOGIN="index";
    private static final String PAGE_NAME="pageName";
    private static final String PAGE_FRAGMENT="pageFragment";
    private static final String PAGE_LOGIN="login";
    private static final String FRAGMENT_LOGIN="login";
    private static final String PAGE_LOGIN_PARA_FINALIZAR_COMPRA="login-compra";
    private static final String FRAGMENT_LOGIN_PARA_FINALIZAR_COMPRA="login-compra";

    @RequestMapping("/public/login")
    public String loginPage(Model model){
        model.addAttribute(PAGE_NAME,PAGE_LOGIN);
        model.addAttribute(PAGE_FRAGMENT,FRAGMENT_LOGIN);

        return LOGIN;
    }

    @RequestMapping("/login")
    public String loginParaFinalizarCompra(Model model){
        model.addAttribute(PAGE_NAME,PAGE_LOGIN_PARA_FINALIZAR_COMPRA);
        model.addAttribute(PAGE_FRAGMENT,FRAGMENT_LOGIN_PARA_FINALIZAR_COMPRA);

        return LOGIN;
    }

//    @RequestMapping("/logout")
//    public String logout(HttpSession session, @AuthenticationPrincipal CustomUserDetails customUserDetails, Model model){
//        session.invalidate();
//
//        return "redirect:/";
//    }

    @RequestMapping(params = "error")
    public String loginError(@RequestParam("error") String login, Model model){
        model.addAttribute("loginError",true);
        return loginPage(model);
    }

}
