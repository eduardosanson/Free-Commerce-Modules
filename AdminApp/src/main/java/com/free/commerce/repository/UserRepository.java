package com.free.commerce.repository;

import com.free.commerce.entity.UserLogin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by eduardosanson on 05/03/16.
 */
public interface UserRepository extends CrudRepository<UserLogin,Long> {

    @Query("select u from UserLogin u where u.login = :#{#email}")
    UserLogin getLoginPorEmail(@Param("email") String email);

}
