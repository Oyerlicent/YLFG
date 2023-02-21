package com.yinlie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yinlie.domain.ResponseResult;
import com.yinlie.domain.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2023-02-02 21:17:09
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);
}

