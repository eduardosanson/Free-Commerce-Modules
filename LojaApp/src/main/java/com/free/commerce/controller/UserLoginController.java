package com.free.commerce.controller;

import com.free.commerce.entity.UserLogin;
import com.free.commerce.service.interfaces.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by eduardo.sanson on 09/05/2016.
 */
@RestController
@RequestMapping("/userLogin")
public class UserLoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<UserLogin> buscarPorEmail(@RequestParam("email") String email){

        try {
            UserLogin user = loginService.recuperarPorEmail(email);
            if (user==null){
                return new ResponseEntity<UserLogin>(HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<UserLogin>(user,HttpStatus.FOUND);
            }

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<UserLogin>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
