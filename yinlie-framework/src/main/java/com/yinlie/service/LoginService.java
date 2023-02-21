package com.yinlie.service;

import com.yinlie.domain.ResponseResult;
import com.yinlie.domain.entity.User;

/**
 * @author Oyerlicent
 * @create 2023-02-10 21:04
 **/
public interface LoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}
