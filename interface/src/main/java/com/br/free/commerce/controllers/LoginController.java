package com.br.free.commerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by eduardo.sanson on 17/03/2016.
 */
@Controller
@RequestMapping("/public/login")
public class LoginController {

    private static final String LOGIN="index";
    private static final String PAGE_NAME="pageName";
    private static final String PAGE_FRAGMENT="pageFragment";
    private static final String PAGE_LOGIN="login";
    private static final String FRAGMENT_LOGIN="login";

    @RequestMapping
    public String loginPage(Model model){
        model.addAttribute(PAGE_NAME,PAGE_LOGIN);
        model.addAttribute(PAGE_FRAGMENT,FRAGMENT_LOGIN);

        return LOGIN;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session,Model model){
        session.invalidate();

        return "redirect:/";
    }

}
