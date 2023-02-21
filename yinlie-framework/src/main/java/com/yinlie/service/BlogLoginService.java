package com.yinlie.service;

import com.yinlie.domain.ResponseResult;
import com.yinlie.domain.entity.User;

/**
 * @author Oyerlicent
 * @create 2023-01-29 22:29
 **/
public interface BlogLoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}
