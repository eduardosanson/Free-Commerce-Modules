package com.free.commerce.service.interfaces;

import com.free.commerce.entity.UserLogin;
import com.free.commerce.to.UserLoginTO;

/**
 * Created by pc on 11/04/2016.
 */
public interface UserLoginService {

    UserLogin inserirLoginAdm(UserLoginTO userLoginTO);
}
