package com.free.commerce.to;

import com.free.commerce.entity.Loja;
import com.free.commerce.entity.UserLogin;

/**
 * Created by eduardo.sanson on 17/03/2016.
 */
public class LoginResponse extends Response {

    private UserLogin userLogin;

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }
}
